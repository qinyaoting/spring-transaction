package test58.strategy;

/**
 * Created by chin on 12/13/16.
 */
public interface Application {
    public void init();
    public void idle();
    public void cleanup();
    public boolean done();
}
