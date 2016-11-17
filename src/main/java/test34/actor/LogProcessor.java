package test34.actor;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import akka.actor.UntypedActor;
import scala.Option;

/**
 * Created by chin on 11/17/16.
 */
public class LogProcessor extends UntypedActor {


    private DbWriter writer;

    public LogProcessor(DbWriter writer) {
        //this.writer = writer;
        // 监听下级的状态
        ActorRef ref = this.getContext().actorOf(Props.create(DbWriter.class,(Connection)null));
        this.context().watch(ref);
    }

    public void process(String file) {
        System.out.println("process log line: " + file);
        this.writer.write("");
    }

    public static void main(String[] args) {
        LogProcessor proc = new LogProcessor(new DbWriter(null));
        proc.process("");
    }

    @Override
    public void onReceive(Object message) throws Exception {

    }

    /**
     * Actor启动前, 先调用preStart()
     * @throws Exception
     */
    @Override
    public void preStart() throws Exception {
        super.preStart();
    }

    /**
     * actor停止时, 会调用postStop()
     * @throws Exception
     */
    @Override
    public void postStop() throws Exception {
        super.postStop();
    }


    /**
     * actor是单实例,在该实例被回收前
     * @param reason
     * @param message
     * @throws Exception
     */
    @Override
    public void preRestart(Throwable reason, Option<Object> message) throws Exception {
        super.preRestart(reason, message);
    }

    /**
     * 在新的实例生成后, 调用
     * @param reason
     * @throws Exception
     */
    @Override
    public void postRestart(Throwable reason) throws Exception {
        super.postRestart(reason);
    }

    @Override
    public SupervisorStrategy supervisorStrategy() {
        //return super.supervisorStrategy();
        return new OneForOneRestartStrategy();
    }
}
