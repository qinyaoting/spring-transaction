package test39.filter3;

import test39.filter2.HtmlFilter;
import test39.filter2.SensitiveFilter;

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
        //实例化处理类
        MsgProcessor mp = new MsgProcessor(msg,chain);
        String r = mp.process();

        System.out.println(r);
    }
}
