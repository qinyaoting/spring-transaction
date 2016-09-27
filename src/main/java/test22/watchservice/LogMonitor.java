package test22.watchservice;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Observable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chin on 9/26/16.
 */
public class LogMonitor extends Observable {

    public LogMonitor()  {


    }

    public void notif(Object obj) {
        this.setChanged();
        this.notifyObservers(obj);
    }

    public void start()throws IOException, InterruptedException {
        Monitor monitor = new Monitor(
                "/home/chin/company/workspace/github/spring-transaction",
                new FileListener() {
                    @Override
                    public void onFileCreated(File file) throws IOException {

                    }

                    @Override
                    public void onFileModify(File file) throws IOException {

                        if (file.getName().startsWith("execlog_")) {
                            String content  = readFile(file);
                            String jobid = "";
                            boolean isFinished = false;
                            Job job = new Job();
                            Pattern p = Pattern.compile("Submitting tokens for job: (.*)\n\r");
                            Matcher m = p.matcher(content);
                            while(m.find()) {
                                jobid = m.group(1);

                            }
                            // 一有变化,就会notif,会有很多.......log里增加一行,就会调该方法,效率会很低...
                            if (jobid.length() > 0) {
                                Pattern p2 = Pattern.compile("Job (.*) completed successfully\n\r");
                                Matcher m2 = p.matcher(content);
                                while(m2.find()) {
                                    isFinished = true;
                                }
                            }
                            if (jobid.length() > 0) {
                                job.setJobid(jobid);
                                if (isFinished)
                                    job.setStatus(Job.Status.FINISHED);
                                else
                                    job.setStatus(Job.Status.RUNNING);

                                notif(job);
                            }




                        }



                        /*if (file.getAbsolutePath().startsWith("execlog_")) {
                            //if (String.contains("Hive import complete");
                            //if (String.contains("Submitted application ");

                            //找到application_1474537299530_1167,扔给查询状态的定时器,里有个队列,请求yarn接口,-->获得状态--更新map
                        }

                        if (file.getAbsolutePath().startsWith("sqoop_") && file.getAbsolutePath().endsWith(".sh")) {

                        }
                        readFile(file);*/

                        // 判断文件类型,sh或者是log
                        // 如果是log,读取内容,
                    }
                });
    }

    public static String readFile(File file) throws IOException {




        StringBuilder con = new StringBuilder();
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        try {
            String line = br.readLine();
            while (line != null) {
                con.append(line).append("\n\r");
                line = br.readLine();
            }

        } catch (IOException e) {

        }



        /*FileInputStream fis = new FileInputStream(file.getAbsolutePath());
        FileChannel fc = fis.getChannel();
        ByteBuffer bf = ByteBuffer.allocate(1024);
        while (fc.read(bf) != -1) {
            bf.flip();
            while (bf.hasRemaining()) {
                System.out.println((char)bf.get());
            }

            bf.clear();
        }*/
        return con.toString();
    }
}
