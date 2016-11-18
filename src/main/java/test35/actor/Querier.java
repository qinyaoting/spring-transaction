package test35.actor;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by chin on 11/17/16.
 */
public class Querier extends UntypedActor {
    private String question;
    private List<String> engines;
    private AtomicReference<String> result;

    public Querier(String question, List<String> engines, AtomicReference<String> result) {

        this.question = question;
        this.engines = engines;
        this.result = result;
    }

    @Override public void onReceive(Object message) throws Exception {
        if(message instanceof Result) {
            result.compareAndSet(null, ((Result) message).html);        //6
            getContext().stop(self());
        }
        else {
            for(String base: engines) {         //2
                String url = base + question;
                ActorRef fetcher = this.getContext().actorOf(Props.create(UrlFetcher.class), "fetcher-"+base.hashCode());       //???创建了多个fetcher?
                Message m = new Message(url);
                fetcher.tell(m, self());        //3
            }
        }
    }
}
