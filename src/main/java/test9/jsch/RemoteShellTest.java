package test9.jsch;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.UUID;

/**
 * Created by chin on 8/25/16.
 */
public class RemoteShellTest {

    public static void main(String[] args){


        /**
         * ==========OK,反应慢,,,,,拿到appid,查询状态
         */

        try {
            String user = "root";
            String pass = "hadoop";
            String host = "master";
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, 22);
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);

            session.setPassword(pass);
            session.connect();



            Channel channel = session.openChannel("exec");

            //((ChannelExec)channel).setCommand("cmd.exe /c \"echo %cd%\"");
            //((ChannelExec)channel).setCommand("cmd.exe /c \"bat.exe apache-flume-1.6.0-bin\\bin\\flume-win.bat\"");
            //ssh WINDOWS_SERVER "cmd /C D:\PATH_TO_BAT\BATCHFILE.BAT"
            //((ChannelExec)channel).setCommand("cmd.exe /c \"test.bat\"");
            //((ChannelExec)channel).setCommand("bat.exe /c  C:\\Users\\chin\\apache-flume-1.6.0-bin\\bin\\flume-win.bat");
            //String cmds = (String) "cmd /C powershell C:/Documents and Settings/vvenkata/My         Documents/Hello.ps1";

            String uuid = UUID.randomUUID().toString().replace("-", "");
            System.out.println(uuid);
            //ok
            String command1  = "nohup /opt/sqoop/bin/sqoop import --connect jdbc:oracle:thin:@192.168.16.223:1521:orcl --username sitts --password password --m 1 --table ESB_ATOMIC_SERVICE  --target-dir hdfs://master:9000/user/root/ESB_ATOMIC_SERVICE > /dev/null 2> "+uuid+" &";
            //ok
            String command2  = "nohup /opt/sqoop/bin/sqoop import --connect jdbc:oracle:thin:@192.168.16.223:1521:orcl --username sitts --password password --m 1 --table ESB_ATOMIC_SERVICE --hbase-table hsort  --column-family cf_s  --hbase-row-key SERVICE_ID > /dev/null 2> "+uuid+" &";
            //ok
            String command3  = "nohup /opt/sqoop/bin/sqoop import --connect jdbc:oracle:thin:@192.168.16.223:1521:orcl --username sitts --password password --m 1 --table ESB_ATOMIC_SERVICE --hbase-table hsort  --column-family cf_s  --hbase-row-key SERVICE_ID > /dev/null 2> "+uuid+" &";
            // 生成进程id 到文件里
            String command4 = "echo '#!/bin/sh\necho $$>uuid.pid' > uuid.sh";
            // 给sh文件付权限==============
            String command5 = "chmod 777 uuid.pid";


            String command6  = "nohup /opt/sqoop/bin/sqoop import --connect jdbc:oracle:thin:@192.168.16.223:1521:orcl --username sitts --password password --m 1 --table ESB_ATOMIC_SERVICE --hbase-table hsort  --column-family cf_s  --hbase-row-key SERVICE_ID > /dev/null 2> "+uuid+" &";

            String c7 = "cat 872bc26abb63430ab5c00770999f09c7 | grep 'Submitting tokens for job:'";
            ((ChannelExec)channel).setCommand(c7);
            //((ChannelExec)channel).setCommand(command5);
            // 找到jobid,得到状态
            // 得到进程id,停止任务

            // 先得创建hbase的表

            channel.connect();
            InputStream outputstream_from_the_channel = channel.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(outputstream_from_the_channel));
            String jarOutput;
            System.out.println("1");

            while ((jarOutput = reader.readLine()) != null) {

                System.out.println("Inside while loop");
                System.out.println(jarOutput + "\n");

            }

            System.out.println("2");
            reader.close();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
