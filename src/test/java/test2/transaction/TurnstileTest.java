package test2.transaction;

import junit.framework.TestCase;
import test55.singleton.Turnstile;

/**
 * Created with IntelliJ IDEA.
 * User: chin
 * Date: 6/4/18
 * Time: 6:51 PM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class TurnstileTest extends TestCase {

    public TurnstileTest(String name) {
        super(name);
    }

    public void setup() {
        Turnstile t = new Turnstile();
        t.reset();
    }

    public void testInit() {
        Turnstile t = new Turnstile();
        assert(t.locked());
        assert(!t.alarm());
    }

    public void testCoin(){
        Turnstile t = new Turnstile();
        t.coin();
        Turnstile t1 = new Turnstile();
        assert(!t1.locked());
        assert (!t1.alarm());
        assertEquals(1,t1.coins());
    }

    public void testCoinAndPass() {
        Turnstile t = new Turnstile();
        t.coin();
        t.pass();

        Turnstile t1 = new Turnstile();
        assert(t1.locked());
        assert (!t1.alarm());
        assertEquals("coins",1, t1.coins());
    }

    public void testTwoCoins() {
        Turnstile t = new Turnstile();
        t.coin();
        t.coin();
        Turnstile t1 = new Turnstile();
//        assert("unlocked", !t1.locked());
        assertEquals("coins",1, t1.coins());
        assertEquals("refunds",1, t1.coins());
        assert(!t1.alarm());
    }

    public void testPass(){
        Turnstile t = new Turnstile();
        t.pass();
        Turnstile t1 = new Turnstile();
//        assert("alarm", t1.alarm());
//        assert ("locked", t1.locked());
    }

    public void testCancelAlarm(){
        Turnstile t = new Turnstile();
        t.pass();
        t.coin();
        Turnstile t1 = new Turnstile();
//        assert("alarm", t1.alarm());
//        assert ("locked", t1.locked());
        assertEquals("coin", 1, t1.coins());
        assertEquals("refund", 0, t1.refunds());
    }

    public void testTwoOperations() {
        Turnstile t = new Turnstile();
        t.coin();
        t.pass();
        t.coin();
//        assert("alarm", t1.alarm());
//        assert ("locked", t1.locked());
        assertEquals("coin", 1, t.coins());
        t.pass();
//        assert("locked", t.locked());
    }

}
