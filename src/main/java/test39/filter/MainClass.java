package test39.filter;

/**
 * Created by chin on 11/25/16.
 *
 * http://www.flyne.org/article/693
 */
public class MainClass {


    // 最简洁的逻辑
    public static void main(String[] args) {
        //需要被过滤的语句
        String msg = "被就业了：)，敏感信息，<script>";

        //实例化处理类
        MsgProcessor mp = new MsgProcessor(msg);
        String r = mp.process();

        System.out.println(r);
    }

}
