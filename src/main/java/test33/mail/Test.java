package test33.mail;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.typesafe.config.Config;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by chin on 11/16/16.
 */
public class Test {



    static LinkedBlockingDeque queue = new LinkedBlockingDeque<>();

    static String groupId = "grp01";
    static String title = "big apple ";
    static String content = "You will get a lot of apple, ";
    static String targetAddr = "abc@xxx.com";
    static String sourceAddr = "xyz@zzz.com";

    public static void main(String[] args) throws InterruptedException {


        for (int i = 0; i < 100000; i++) {
            queue.add(new Mail(groupId, title+i,content+i,targetAddr, sourceAddr));
        }
        System.out.println(queue.size());


        Thread.sleep(30000);

        Config config = com.typesafe.config.ConfigFactory.parseString("akka.loglevel = DEBUG \n akka.actor.debug.lifecycle = on");
        ActorSystem system = ActorSystem.create("Test", config);
        final ActorRef pushActor = system.actorOf(PushActor.props(queue), "pushActor");
        final ActorRef sendEmailActor = system.actorOf(SendEmailActor.props(), "sendEmailActor");
        final ActorRef sumActor = system.actorOf(SumActor.props(), "sumActor");




    }
}
