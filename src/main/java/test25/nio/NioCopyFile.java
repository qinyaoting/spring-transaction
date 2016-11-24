package test25.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by chin on 10/9/15.
 */
public class NioCopyFile {

    public static void main(String[] args) throws IOException {

        String res = "/home/chin/company/data/hadoop/sogou/SogouQ.full.gz";
        String out = "/home/chin/company/data/hadoop/sogou/SogouQ222.full.gz";
        long sta = System.currentTimeMillis();
        nioCopyFile(res,out);
        System.out.println("5g copy spend:" + (System.currentTimeMillis() - sta)/1000);
    }

    public static void nioCopyFile(String resource, String destination) throws IOException {

        FileInputStream fis = new FileInputStream(resource);
        FileOutputStream fos = new FileOutputStream(destination);
        FileChannel readChannel = fis.getChannel();
        FileChannel writeChannel = fos.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (true) {
            buffer.clear();
            int len = readChannel.read(buffer);
            if (len == -1)
                break;

            buffer.flip();
            writeChannel.write(buffer);
        }
        readChannel.close();
        writeChannel.close();
    }
}
