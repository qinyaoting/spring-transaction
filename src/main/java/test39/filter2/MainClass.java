package test39.filter2;

/**
 * Created with IntelliJ IDEA.
 * User: chin
 * Date: 5/10/18
 * Time: 6:35 PM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class MainClass {

    // 定义一个接口, 实现MsgProcessor SensitiveFilter添加到processor
    public static void main(String[] args) {
        //需要被过滤的语句
        String msg = "被就业了：)，敏感信息，<script>";
        MsgProcessor processor = new MsgProcessor(msg);
        String res = processor.process();
        System.out.println(res);
    }
}
