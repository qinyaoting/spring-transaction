package test36.actor;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

import java.util.Date;

/**
 * Created by chin on 11/18/16.
 */
public class WordCounterActor extends UntypedActor {

    private boolean running = false;
    private int totalLines = 0;
    private int lineProcessed = 0;
    private int result = 0;



    @Override
    public void onReceive(Object message) throws Exception {


        if (message instanceof String ) {

            if (running) {
                System.out.println("Warning: duplicate start message received");
            } else {
                running = true;
                // 读取文件,遍历每一行,
                for (int i = 0; i < 1000000; i++) {
                    ActorRef ref = getContext().actorOf(Props.create(StringCounterActor.class), "stringCounterActor"+i);
                    ref.tell("1 2 3", this.getSender());
                    totalLines += 1;
                }
            }
        } else if (message instanceof Integer) {

            int words = (int)message;
            //System.out.println("words == " + words);
            result += words;
            lineProcessed += 1;
            if (lineProcessed == totalLines) {
                System.out.println("done..."+ result);
            }

        }


    }
}
