package test35.actor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActorFactory;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by chin on 11/17/16.
 */
public class Test {

    public static void main(String[] args) {
        getFirstResultActors("abc", Lists.newArrayList("http://baidu.com", "http://google.com", "http://sogou.com"));
    }

    private static String getFirstResultActors(String question, List<String> engines) {
        ActorSystem system = ActorSystem.create("Search");
        AtomicReference<String> result = new AtomicReference<>();

        final ActorRef q = system.actorOf(
                Props.create((UntypedActorFactory) () -> new Querier(question, engines, result)), "master");
        q.tell(new Object(), ActorRef.noSender());      //1

        while(result.get() == null);
        return result.get();
    }
}
