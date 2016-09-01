package test9.jsch;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by chin on 8/25/16.
 */
public class RemoteTest {

    public static void main(String[] args){


        try {
            String user = "chin";
            String pass = "a12room102";
            String host = "192.168.20.97";
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
            ((ChannelExec)channel).setCommand("powershell.exe /C   apache-flume-1.6.0-bin\\bin\\flume-win.ps1");


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
