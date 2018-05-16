package test53.chain;

/**
 * Created by chin on 5/23/16.
 */
public class MainClass {

    public static void main(String[] args) {


        // 如果有继任者, 就由继任者处理
        // 链模式是只有一个角色来处理逻辑(其他角色只是判断是否需要处理), 跟过滤器不同

        Handler handler1 = new ConcreteHandler();
        Handler handler2 = new ConcreteHandler();
        Handler handler3 = new ConcreteHandler();

        handler1.setSuccessor(handler2);
        handler2.setSuccessor(handler3);
        handler1.handleRequest();

    }
}
