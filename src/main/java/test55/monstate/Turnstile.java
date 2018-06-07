package test55.monstate;

/**
 * Created with IntelliJ IDEA.
 * User: chin
 * Date: 6/4/18
 * Time: 6:51 PM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class Turnstile {


    private static boolean isLocked = true;
    private static boolean isAlarming = false;

    private static int itsCoins = 0;
    private static int itsRefunds = 0;
    protected final static Turnstile LOCKED = new Locked();
    protected final static Turnstile UNLOCKED = new Unlocked();
    protected static Turnstile itsState = LOCKED;

    public void reset() {
        lock(true);
        alarm(false);
        itsCoins = 0;
        itsRefunds = 0;
        itsState = LOCKED;
    }

    public boolean locked() {
        return isLocked;
    }

    public boolean alarm() {
        return isAlarming;
    }


    public void coin() {
        itsState.coin();
    }

    public void pass() {
        itsState.pass();
    }

    protected void lock(boolean shouldLock) {
        isLocked = shouldLock;
    }

    protected void alarm(boolean shouldAlarm) {
        isAlarming = shouldAlarm;
    }

    public int coins() {
        return itsCoins;
    }

    public int refunds() {
        return itsRefunds;
    }

    public void deposit() {
        itsCoins++;
    }

    public void refund() {
        itsRefunds++;
    }


}

/**
 * 内部类,
 */
class Locked extends Turnstile {
    public void coin() {
        itsState = UNLOCKED;
        lock(false);
        alarm(false);
        deposit();
    }

    public void pass() {
        alarm(true);
    }
}
class Unlocked extends Turnstile {
    public void coin() {
        refund();
    }
    public void pass() {
        lock(true);
        itsState = LOCKED;
    }
}
