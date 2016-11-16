package test33.mail;

import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.japi.Creator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chin on 11/16/16.
 */
public class SumActor extends UntypedActor {


    public static Props props() {
        return Props.create(new Creator<SumActor>() {
            @Override
            public SumActor create() throws Exception {
                return new SumActor();
            }
        });
    }

    static int max = 100000;
    Map<String,Integer> map = new HashMap<String,Integer>();

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Job) {
            --max;
            Job job = (Job)message;
            String st = job.getStatus();
            if (map.get(st) != null) {
                int val = map.get(st).intValue();
                map.put(st,++val);
            } else {
                map.put(st, 1);
            }

            if (max % 1000 == 0) {
                for (String key : map.keySet()) {
                    System.out.println(key + " --> " + map.get(key));
                }
            }
        }
    }
}
