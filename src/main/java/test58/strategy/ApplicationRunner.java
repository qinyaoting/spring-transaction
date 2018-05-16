package test58.strategy;


/**
 * Created by chin on 12/13/16.
 */
public class ApplicationRunner {

    private Application itsApp = null;

    public ApplicationRunner(Application itsApp) {
        this.itsApp = itsApp;
    }

    public void run() {
        itsApp.init();
        while (!itsApp.done())
            itsApp.idle();
        itsApp.cleanup();
    }
}
