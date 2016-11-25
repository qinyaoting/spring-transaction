package test39.filter3;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import test39.filter2.HtmlFilter;
import test39.filter2.SensitiveFilter;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by chin on 11/25/16.
 */
public class MainClass {
    public static void main(String[] args) {
        //需要被过滤的语句
        String msg = "被就业了：），敏感信息，<script>";

        //搞一个过过滤链
        FilterChain chain = new FilterChain();
        chain.addFilter(new HtmlFilter()).addFilter(new SensitiveFilter());

        //实例化处理类,把过滤器链传给MsgProcessor
        MsgProcessor mp = new MsgProcessor(msg,chain);
        String r = mp.process();

        System.out.println(r);



        final File sourceFile = new File("/home/chin/company/conf/os/CentOS-6.6-x86_64-bin-DVD1.iso");
        final File targetFile = new File("/home/chin/company/conf/os/CentOS-6.6-x86_64-bin-DVD1====.iso");
        try
        {
            System.out.println(new Date());
            Files.copy(sourceFile, targetFile);
            System.out.println(new Date());
        }
        catch (IOException fileIoEx)
        {
            System.out.println("ERROR trying to copy file '"+fileIoEx.toString());
        }
    }
}
