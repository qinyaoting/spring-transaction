package test21.hbase;

import com.google.common.collect.ImmutableMap;
import com.spring4all.spring.boot.starter.hbase.api.RowMapper;
import com.spring4all.spring.boot.starter.hbase.api.TableCallback;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.security.User;
import org.apache.hadoop.security.UserGroupInformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * HBase操作
 * 
 * @author li-xm
 *
 */
public class HbaseTemplate extends com.spring4all.spring.boot.starter.hbase.api.HbaseTemplate {
	private static final Logger logger = LoggerFactory.getLogger(HbaseTemplate.class);
	private volatile Connection connection;
	private volatile ThreadPoolExecutor poolExecutor;
	private final boolean enableSecurity;

	public HbaseTemplate(Configuration configuration, boolean enableSecurity) {
		super(configuration);
		this.enableSecurity = enableSecurity;
	}

	@Override
	public <T> List<T> find(final String tableName, final Scan scan, final RowMapper<T> mapper) {
		return this.execute(tableName, new TableCallback<List<T>>() {
			@Override
			public List<T> doInTable(Table table) throws Throwable {
				int caching = scan.getCaching();
				// 如果caching未设置(默认是1)，将默认配置成5000
				if (caching == 1) {
					scan.setCaching(5000);
				}
				try (ResultScanner scanner = table.getScanner(scan)) {
					List<T> rs = new ArrayList<T>();
					int rowNum = 0;
					for (Result result : scanner) {
						//////
						logger.debug("result:{}", result);
						if (!result.isEmpty()) {
							rs.add(mapper.mapRow(result, rowNum++));
						}
					}
					return rs;
				}
			}
		});
	}

	@Override
	public Connection getConnection() {
		if (null == this.connection) {
			synchronized (this) {
				if (null == this.connection) {
					try {
						poolExecutor = new ThreadPoolExecutor(200, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS,
								new SynchronousQueue<Runnable>());
						poolExecutor.prestartCoreThread();
						if (enableSecurity) {
							this.connection = ConnectionFactory.createConnection(this.getConfiguration(), poolExecutor,
									User.create(UserGroupInformation.getLoginUser()));
						} else {
							this.connection = ConnectionFactory.createConnection(this.getConfiguration(), poolExecutor);
						}
					} catch (IOException e) {
						logger.error("The connection resource pool creation of HBase failed");
					}
				}
			}
		}
		return this.connection;

	}

	public void init() {
		getConnection();
	}

	public void close() throws IOException {
		this.connection.close();
		if (this.poolExecutor != null) {
			this.poolExecutor.shutdown();
			try {
				if (!this.poolExecutor.awaitTermination(10, TimeUnit.SECONDS)) {
					this.poolExecutor.shutdownNow();
				}
			} catch (InterruptedException e) {
				this.poolExecutor.shutdownNow();
			}
		}
	}

	public static void main(String[] args) {

		Map<String,String> map = ImmutableMap.of("hbase.zookeeper.quorum","10.4.108.190:2181",
				"hbase.rootdir","",
				"zookeeper.znode.parent","");
		Configuration configuration = HBaseConfiguration.create();
		map.forEach((k,v)->{
			configuration.set(k,v);
		});

		HbaseTemplate template = new HbaseTemplate(configuration,false);
		//template.saveOrUpdate();
		template.init();
	}

}
