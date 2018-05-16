package test59.template;

/**
 * Created by chin on 12/13/16.
 */
public abstract class Application {

    private boolean isDone = false;
    protected abstract void init();
    protected abstract void idle();
    protected abstract void cleanup();

    protected void setDone() {
        isDone  =true;
    }
    protected boolean done() {
        return isDone;
    }

    public void run () {
        init();
        while (!done())
            idle();
        cleanup();
    }

}
