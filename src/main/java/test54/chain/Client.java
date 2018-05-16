package test54.chain;

/**
 * Created by chin on 5/23/16.
 */
public class Client {

    public static void main(String[] args) {


        //先要组装责任链,只有一个人能处理请求

        Handler h1 = new GeneralManager();      //总经理       大于1000
        Handler h2 = new DeptManager();         //部门经理      小于1000,大于500
        Handler h3 = new ProjectManager();      //项目经理      小于500
        h3.setSuccessor(h2);
        h2.setSuccessor(h1);

        //开始测试,参数传给h3开始处理请求
        String test1 = h3.handleFeeRequest(300);
        System.out.println("test1 = " + test1);
        String test2 = h3.handleFeeRequest(300);
        System.out.println("test2 = " + test2);
        System.out.println("---------------------------------------");

        String test3 = h3.handleFeeRequest(700);
        System.out.println("test3 = " + test3);
        String test4 = h3.handleFeeRequest(700);
        System.out.println("test4 = " + test4);
        System.out.println("---------------------------------------");

        String test5 = h3.handleFeeRequest(1500);
        System.out.println("test5 = " + test5);
        String test6 = h3.handleFeeRequest(1500);
        System.out.println("test6 = " + test6);
    }
}
