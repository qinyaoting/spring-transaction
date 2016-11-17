package test34.actor;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chin on 11/17/16.
 */
public class TicketingAgent extends UntypedActor {



    private Map<Integer,Integer> tickets = new HashMap<Integer,Integer>();  //车次,余票数量

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof EventRequest)
            System.out.println("received event request");       //查询车次有多少票
        else if (message instanceof TicketRequest) {          //订票
            TicketRequest request = (TicketRequest) message;

            ActorRef sender = this.getContext().sender();


            if (tickets.get(request.event) >= request.tickets) {
                tickets.put(request.event, tickets.get(request.event) - request.tickets);
                sender.tell("", this.getSelf());
            }

        }
    }
}
