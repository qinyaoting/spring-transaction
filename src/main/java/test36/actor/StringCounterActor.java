package test36.actor;

import akka.actor.ActorRef;
import akka.actor.ActorRefFactory;
import akka.actor.ActorSelection;
import akka.actor.UntypedActor;

/**
 * Created by chin on 11/18/16.
 */
public class StringCounterActor extends UntypedActor {


    @Override
    public void preStart() throws Exception {

    }

    @Override
    public void onReceive(Object message) throws Exception {

        if (message instanceof String) {
            String line = (String) message;
            int len = line.split(" ").length;


            //ref.tell(len, this.getSelf());

            ActorSelection wordCounterActor = this.getContext().actorSelection("akka://WordCounter/user/wordCounterActor");
            //wordCounterActor.tell(len, this.getSender());---ok
            wordCounterActor.tell(len, this.getSelf());


            /**
             * XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
             * XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
             */
            //this.getContext().sender().tell(len, this.getSelf());
            //getSender().tell(len, this.getSelf());

        }


    }
}
