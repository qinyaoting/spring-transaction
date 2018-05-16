package test56.command;

/**
 * Created by chin on 9/1/16.
 */
public class Test {

    public static void main(String[] args) {
        Cook cook = new Cook();
        Command c1 = new RoastChickenWingsCommand(cook);
        Command c2 = new SkewerCommand(cook);
        Command c3 = new RoastChickenWingsCommand(cook);

        Waiter waiter = new Waiter();
        waiter.addOrder(c1);
        waiter.addOrder(c2);
        waiter.addOrder(c3);
        waiter.cancelOrder(c3);
        waiter.startCook();
    }
}
