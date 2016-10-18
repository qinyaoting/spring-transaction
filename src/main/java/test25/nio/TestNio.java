package test25.nio;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by chin on 10/18/16.
 */
public class TestNio {

    public static void main(String[] args) throws IOException {

        NIOUtil util = new NIOUtil();
        String source = "/home/chin/company/conf/os/CentOS-7.0-1406-x86_64-DVD.iso";
        String dest = "/home/chin/company/conf/os-test/CentOS-7.0-1406-x86_64-DVD.iso";
        FileUtils.deleteQuietly(new File(dest));

        long total = 0L;
        for (int i=0;i<20;i++) {
            long st = System.currentTimeMillis();
            util.copyFile(source, dest);
            long end = System.currentTimeMillis();
            System.out.println("take " + (end-st)/1000 +"s") ;//4.1g spend 93s , 45m/s
            total += (end - st);
        }
        System.out.println("avg:" + total/20*1000);



    }
}
