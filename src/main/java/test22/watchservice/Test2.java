package test22.watchservice;

import io.netty.buffer.ByteBuf;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by chin on 9/26/16.
 */
public class Test2 {

    public static void main(String[] args) throws IOException, InterruptedException {


        Monitor monitor = new Monitor(
                "/home/chin/company/workspace/github/spring-transaction",
                new FileListener() {
                    @Override
                    public void onFileCreated(File file) throws IOException {
                        readFile(file);


                    }

                    @Override
                    public void onFileModify(File file) throws IOException {

                        if (file.getAbsolutePath().startsWith("execlog_")) {
                            //if (String.contains("Hive import complete");
                            //if (String.contains("Submitted application ");

                            //找到application_1474537299530_1167,请求yarn接口,
                        }

                        if (file.getAbsolutePath().startsWith("sqoop_") && file.getAbsolutePath().endsWith(".sh")) {

                        }
                        readFile(file);

                        // 判断文件类型,sh或者是log
                        // 如果是log,读取内容,
                    }
                });

    }

    public static String readFile(File file) throws IOException {


        FileInputStream fis = new FileInputStream(file.getAbsolutePath());
        FileChannel fc = fis.getChannel();
        ByteBuffer bf = ByteBuffer.allocate(1024);
        while (fc.read(bf) != -1) {
            bf.flip();
            while (bf.hasRemaining()) {
                System.out.println((char)bf.get());
            }

            bf.clear();
        }
        return "";
    }
}
