package test53.chain;

/**
 * Created by chin on 5/23/16.
 */
public class ConcreteHandler extends Handler {
    @Override
    public void handleRequest() {

        if (getSuccessor() != null) {
            System.out.println(this.getClass().toString() + "\t放过请求");
            getSuccessor().handleRequest();
        } else {
            System.out.println(this.getClass().toString() + "\t处理请求....");
        }



    }
}
