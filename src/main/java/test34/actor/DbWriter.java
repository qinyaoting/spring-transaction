package test34.actor;

import akka.actor.UntypedActor;

/**
 * Created by chin on 11/17/16.
 */
public class DbWriter extends UntypedActor {

    private Connection conn;

    public DbWriter(Connection conn) {
        this.conn = conn;
    }

    public void write(String s) {
        System.out.println("writing log entry to database:" + s);
    }

    @Override
    public void onReceive(Object message) throws Exception {

    }
}
