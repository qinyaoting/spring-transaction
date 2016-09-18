package test20.oozie;

import org.apache.oozie.client.OozieClient;
import org.apache.oozie.client.XOozieClient;

import java.util.Properties;

/**
 * Created by chin on 9/12/16.
 */
public class TestOozie {


    public static void main(String[] args) throws Exception {
        //String oozieUrl = "http://master:11000/oozie";
        // OozieClient wc = new OozieClient(oozieUrl);
        // Properties conf = wc.createConfiguration();
        // wc.run(conf);

        /*XOozieClient xOozieClient = new XOozieClient(oozieUrl);
        Properties oozieConf =xOozieClient.createConfiguration();
        oozieConf.setProperty("fs.default.name", "hdfs://master:9000");
        oozieConf.setProperty("mapred.job.tracker", "master:8032");
        oozieConf.setProperty("user.name", "root");
        oozieConf.setProperty("oozie.sqoop.command", "import --connect jdbc:mysql://master:3306/oozie --driver com.mysql.jdbc.Driver --username root  --password root --table WF_JOBS --hbase-table WF_JOBS2  --column-family cf_s  --hbase-row-key id --hbase-create-table ");
        oozieConf.setProperty("queueName", "default");
        oozieConf.setProperty("oozie.libpath", "hdfs://master:9000/user/root/share/lib/sqoop");
        oozieConf.setProperty("oozie.proxysubmission", "true");

        //oozieConf.setProperty("examplesRoot", "examples");
        //oozieConf.setProperty("oozie.wf.application.path", "${nameNode}/user/root/${examplesRoot}/apps/map-reduce");
        //oozieConf.setProperty("outputDir", "map-reduce");


        String[] command = {"import --connect jdbc:mysql://master:3306/oozie --driver com.mysql.jdbc.Driver --username root  --password root --table WF_JOBS --hbase-table WF_JOBS2  --column-family cf_s  --hbase-row-key id --hbase-create-table "};
        String jobid = xOozieClient.submitSqoop(oozieConf,command, null);
        System.out.println(jobid);*/


        //https://github.com/apache/oozie/blob/86c5ebb58c950524c515b83c4793d3e6341e44d2/core/src/test/java/org/apache/oozie/client/TestWorkflowXClient.java

        /**
         * 倒单表---ok
         */
        exesingleJob();

        /**
         * 倒5个表ok,到10个,application manager好像卡死了,分配有问题?
         */
        //exeMultiJob();

    }


    private static void exeMultiJob() throws Exception {
        for (int i = 0; i < 5; i++) {
            String oozieUrl = "http://bdas05:11000/oozie/";
            //int wfCount = MockDagEngineService.INIT_WF_COUNT;
            XOozieClient wc = new XOozieClient(oozieUrl);
            Properties conf = wc.createConfiguration();
            //Path libPath = new Path(getFsTestCaseDir(), "lib");
            // getFileSystem().mkdirs(libPath);
            // System.out.println(libPath.toString());

            //Configuration actionConf = new Configuration(false);
            //actionConf.addResource(new Path("file:///", System.getProperty("oozie.action.conf.xml")));


            conf.setProperty(OozieClient.LIBPATH, "hdfs://hacluster/user/xdata/share/lib/sqoop");
            conf.setProperty(XOozieClient.JT_2, "rsmgr1");
            conf.setProperty(XOozieClient.NN_2, "hdfs://hacluster");
            conf.setProperty("user.name", "xdata");
            conf.setProperty("oozie.proxysubmission", "true");
            conf.setProperty("hbase.zookeeper.quorum", "bdas00,bdas01,bdas02");
            conf.setProperty("hbase.zookeeper.property.clientPort", "2181");
            conf.setProperty("hbase.master", "bdas00");
            conf.setProperty("hbase.rootdir", "hdfs://hacluster/appdt/hbase");
            conf.setProperty("zookeeper.znode.parent", "/hbase");

            // mysql -> hdfs======ok
            //String id= wc.submitSqoop(conf, new String[] {"import", "--connect","jdbc:mysql://192.168.1.187:3306/oozie","--driver","com.mysql.jdbc.Driver","--username","hive","--password","hive","--table" ,"WF_JOBS" ,"--m", "1" ,"--target-dir", "hdfs://hacluster/user/xdata/WF_JOBS222000"},null);
            // oracle -> hdfs == ok
            //String id= wc.submitSqoop(conf, new String[] {"import", "--connect","jdbc:oracle:thin:@192.168.16.223:1521:orcl","--username","sitts","--password","password","--table" ,"ESB_ATOMIC_SERVICE" ,"--m", "1" ,"--target-dir", "hdfs://hacluster/user/xdata/ESB_ATOMIC_SERVICE1111"},null);
            // 失败---------------------------
            String id = wc.submitSqoop(conf, new String[]{"import", "--connect", "jdbc:oracle:thin:@192.168.16.223:1521:orcl", "--username", "sitts", "--password", "password", "--table", "ESB_ATOMIC_SERVICE", "--m", "1",
                    "--hbase-table", "ESB_ATOMIC_SERVICE_" + i, "--column-family", "cf_s", "--hbase-row-key", "SERVICE_ID"}, null);


            System.out.println(id);
        }
    }

    private static void exesingleJob() throws Exception {
        String oozieUrl = "http://bdas05:11000/oozie/";
        XOozieClient wc = new XOozieClient(oozieUrl);
        Properties conf = wc.createConfiguration();
        conf.setProperty(OozieClient.LIBPATH, "hdfs://hacluster/user/xdata/share/lib/sqoop");
        conf.setProperty(XOozieClient.JT_2, "rsmgr1");
        conf.setProperty(XOozieClient.NN_2, "hdfs://hacluster");
        conf.setProperty("user.name", "xdata");
        conf.setProperty("oozie.proxysubmission", "true");
        conf.setProperty("hbase.zookeeper.quorum", "bdas00,bdas01,bdas02");
        conf.setProperty("hbase.zookeeper.property.clientPort", "2181");
        conf.setProperty("hbase.master", "bdas00");
        conf.setProperty("hbase.rootdir", "hdfs://hacluster/appdt/hbase");
        conf.setProperty("zookeeper.znode.parent", "/hbase");

        // mysql -> hdfs======ok
        //String id= wc.submitSqoop(conf, new String[] {"import", "--connect","jdbc:mysql://192.168.1.187:3306/oozie","--driver","com.mysql.jdbc.Driver","--username","hive","--password","hive","--table" ,"WF_JOBS" ,"--m", "1" ,"--target-dir", "hdfs://hacluster/user/xdata/WF_JOBS222000"},null);
        // oracle -> hdfs == ok
        //String id= wc.submitSqoop(conf, new String[] {"import", "--connect","jdbc:oracle:thin:@192.168.16.223:1521:orcl","--username","sitts","--password","password","--table" ,"ESB_ATOMIC_SERVICE" ,"--m", "1" ,"--target-dir", "hdfs://hacluster/user/xdata/ESB_ATOMIC_SERVICE1111"},null);
        // oracle -> hbase =====ok
        String id = wc.submitSqoop(conf, new String[]{"import", "--connect", "jdbc:oracle:thin:@192.168.16.223:1521:orcl", "--username", "sitts", "--password", "password", "--table", "ESB_ATOMIC_SERVICE", "--m", "1",
                "--hbase-table", "ESB_ATOMIC_SERVICE_2", "--column-family", "cf_s", "--hbase-row-key", "SERVICE_ID"}, null);


        // java.lang.NoSuchMethodError: org.apache.hadoop.hbase.HTableDescriptor.addFamily(Lorg/apache/hadoop/hbase/HColumnDescriptor;)V
        /*String id = wc.submitSqoop(conf, new String[]{"import", "--connect", "jdbc:oracle:thin:@192.168.16.223:1521:orcl", "--username", "sitts", "--password", "password", "--table", "ESB_ATOMIC_SERVICE", "--m", "1",
                "--hbase-table", "ESB_ATOMIC_SERVICE_0", "--column-family", "cf_s", "--hbase-row-key", "SERVICE_ID", "--hbase-create-table"}, null);*/


        System.out.println(id);

        // truncate 'ESB_ATOMIC_SERVICE_2'
        // scan 'ESB_ATOMIC_SERVICE_2'
    }
}
