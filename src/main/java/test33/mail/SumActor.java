package test33.mail;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.japi.Creator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by chin on 11/16/16.
 */
public class SumActor extends UntypedActor {


    public SumActor() {
    }

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

        /*if (message instanceof Job) {

            *//*Job job = (Job)message;
            jobMap.put(job.getJobId(),job.getTaskSize());
            max = job.getTaskSize();
            System.out.println("SumActor received: " + max);
            ctime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());*//*
        }

        else if (message instanceof AbstractTask) {
            --max;
            System.out.println("$$$$$$$$$$$$"+max  );
            AbstractTask task = (AbstractTask)message;
            String st = task.getStatus();
            if (taskStatusCounter.get(st) != null) {
                int val = taskStatusCounter.get(st).intValue();
                taskStatusCounter.put(st, ++val);
            } else {
                taskStatusCounter.put(st, 1);
            }

            if (max % 100 == 0) {
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

        } else */

        if (message instanceof String) {
            // 启动任务

            ctime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

            /**
             * 传递不变的数据, 传递可变的有问题......
             */
            /*for(int i=0;i<job.getTaskSize();i++) {
                AbstractTask task = job.getTasks().take();
                ActorRef sendEmailActor = this.getContext().actorOf(SendEmailActor.props(), "sendEmailActor" + UUID.randomUUID().toString());
                sendEmailActor.tell(task, this.getSelf());
            }*/
            max = 1000000;

            for (int i = 0; i < 1000000; i++) {
                ActorRef sendEmailActor = this.getContext().actorOf(Props.create(SendEmailActor.class), "sendEmailActor" + i);
                sendEmailActor.tell("1 2 3", this.getSender());
            }



        } else if (message instanceof  Integer) {
            --max;
            //System.out.println("$$$$$$$$$$$$"+max  );
            if (max % 10 == 0) {
                System.out.println("=================================="+max);

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
