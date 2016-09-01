package test16.command;

/**
 * Created by chin on 9/1/16.
 */
public class RoastChickenWingsCommand extends Command {
    protected RoastChickenWingsCommand(Cook cook) {
        super(cook);
    }

    @Override
    public void execute() {
        System.out.println("厨子,烤鸡翅..");
    }
}
