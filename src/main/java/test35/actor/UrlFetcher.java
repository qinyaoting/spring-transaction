package test35.actor;

import akka.actor.UntypedActor;

/**
 * Created by chin on 11/17/16.
 */
public class UrlFetcher extends UntypedActor {



    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Message) {
            Message work = (Message) message;
            String result = WS.url(work.url);                       // 4
            getSender().tell(new Result(result), getSelf());        // 5
        } else {
            unhandled(message);
        }
    }
}

class WS {
    public static String url(String url) {
        return "";
    }

}
