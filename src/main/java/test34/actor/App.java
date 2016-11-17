package test34.actor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * Created by chin on 11/17/16.
 */
public class App {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.apply().create("sys");
        ActorRef ref = system.actorOf(Props.create(TicketingAgent.class),"agent");
        ref.tell(new EventRequest(), ActorRef.noSender());
    }
}
