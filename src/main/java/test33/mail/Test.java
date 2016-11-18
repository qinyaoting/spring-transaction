package test33.mail;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.Config;
import test36.actor.WordCounterActor;

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by chin on 11/16/16.
 */
public class Test {



    static LinkedBlockingDeque<AbstractTask> mailQueue = new LinkedBlockingDeque<AbstractTask>();

    static String title = "big apple ";
    static String content = "You will get a lot of apple, ";
    static String targetAddr = "abc@xxx.com";
    static String sourceAddr = "xyz@zzz.com";


    public static void main(String[] args) throws InterruptedException {


        /*for (int i = 0; i < 100000; i++) {
            mailQueue.add(new MailTask(title+i,content+i,targetAddr, sourceAddr));
        }

        Job job = new Job("job01",mailQueue);
        System.out.println("Job init finished. " + job.getJobId() + " queue:" + mailQueue.size());*/


        //Thread.sleep(10000);

        //Config config = com.typesafe.config.ConfigFactory.parseString("akka.loglevel = DEBUG \n akka.actor.debug.lifecycle = on");
        //ActorSystem system = ActorSystem.create("Test", config);


        ActorSystem system = ActorSystem.create("Test");
        ActorRef sumActor = system.actorOf(Props.create(SumActor.class), "sumActor");
        sumActor.tell("start", ActorRef.noSender());





        /*for (int i = 0; i < 10; i++) {

            threadPool.submit(new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    while (true) {

                        AbstractTask task = mailQueue.take();
                        ActorRef sendEmailActor = system.actorOf(SendEmailActor.props(), "sendEmailActor"+ UUID.randomUUID().toString());
                        sendEmailActor.tell(task, ActorRef.noSender());
                    }
                }
            });
        }*/
    }


    //static ExecutorService threadPool = Executors.newFixedThreadPool(10);


}
