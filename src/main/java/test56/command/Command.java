package test56.command;

/**
 * Created by chin on 9/1/16.
 */
public abstract class Command {

    // 需要把执行命令的人,传到里边
    private Cook cook;

    protected Command(Cook cook) {
        this.cook = cook;
    }

    public abstract void execute();
}
