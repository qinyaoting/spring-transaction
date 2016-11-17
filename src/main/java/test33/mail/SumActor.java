package test33.mail;

import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.japi.Creator;

import java.text.SimpleDateFormat;
import java.util.Date;
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
    Map<String,Integer> jobMap = new HashMap<>();
    static int max = 0;
    static String ctime = "";;
    Map<String,Integer> taskStatusCounter = new HashMap<String,Integer>();

    @Override
    public void onReceive(Object message) throws Exception {

        if (message instanceof Job) {

            Job job = (Job)message;
            jobMap.put(job.getJobId(),job.getTaskSize());
            max = job.getTaskSize();
            System.out.println(max);
            ctime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        }

        else if (message instanceof AbstractTask) {
            --max;
            AbstractTask task = (AbstractTask)message;
            String st = task.getStatus();
            if (taskStatusCounter.get(st) != null) {
                int val = taskStatusCounter.get(st).intValue();
                taskStatusCounter.put(st, ++val);
            } else {
                taskStatusCounter.put(st, 1);
            }

            if (max % 1000 == 0) {
                System.out.println("==================================");
                for (String key : taskStatusCounter.keySet()) {
                    System.out.println(key + " --> " + taskStatusCounter.get(key));
                }
            }


            if (max == 0) {

                System.out.println("all task done");
                System.out.println("ctime:" + ctime);
                System.out.println("etime:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            }

        }
        /*
        1000个http 每100s
         */
    }
}
