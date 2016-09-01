package test16.command;

/**
 * Created by chin on 9/1/16.
 */
public class SkewerCommand extends Command {
    protected SkewerCommand(Cook cook) {
        super(cook);
    }

    @Override
    public void execute() {
        System.out.println("厨子,烤串..");
    }
}
