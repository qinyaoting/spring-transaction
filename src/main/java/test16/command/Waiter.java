package test16.command;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chin on 9/1/16.
 */
public class Waiter {

    List<Command> dishes = new ArrayList<Command>();

    public void addOrder(Command c) {
        dishes.add(c);
    }

    public void cancelOrder(Command c) {
        dishes.remove(c);
    }

    public void startCook() {
        for (Command c: dishes)
            c.execute();
    }
}
