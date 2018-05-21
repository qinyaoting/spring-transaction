package test28.queue;

/**
 * Created with IntelliJ IDEA.
 * User: chin
 * Date: 5/21/18
 * Time: 5:46 PM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class Main {

    public static void main(String[] args) {

        Queue queue = new Queue();
        for (int i = 0; i <90; i++) {
            queue.addQueue(new String[]{"name:"+i,"param:"+i});
        }

        queue.start();
        System.out.println("start...");



    }
}
