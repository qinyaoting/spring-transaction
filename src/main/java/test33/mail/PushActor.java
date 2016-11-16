package test33.mail;

import akka.actor.*;
import akka.japi.Creator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by chin on 11/16/16.
 */
public class PushActor extends UntypedActor {

    // 需求,比如有10w个需要发送邮件的任务, 发送完成后要统计,发送的开始时间,结束时间, 成功与否,要支持失败重发

    // 10w个邮件内容,初始化到队列里,用actor,取出,发送, 最好有个组的概念, 怎么知道全发完了?

    public static Props props(LinkedBlockingDeque q) {
        return Props.create(new Creator<PushActor>() {
            @Override
            public PushActor create() throws Exception {
                return new PushActor(q);
            }
        });
    }

    private LinkedBlockingDeque queue;

    public PushActor(LinkedBlockingDeque q) {
        this.queue = q;
        start();
    }

    @Override
    public void onReceive(Object message) throws Exception {

    }

    ExecutorService threadPool = Executors.newFixedThreadPool(5);

    public void start() {

        for (int i = 0; i < 5; i++) {
            threadPool.submit(new PushThread(this));
        }
    }

    public class PushThread extends Thread {

        PushActor actor;

        public PushThread(PushActor actor) {
            this.actor = actor;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    //System.out.println(Thread.currentThread().getName() + "===========");
                    Mail mail = (Mail)queue.take();

                    Job job = new Job(mail);
                    final ActorSelection sendEmailActor = actor.getContext().actorSelection("../sendEmailActor");
                    sendEmailActor.tell(job, actor.getSender());
                    //System.out.println(Thread.currentThread().getName() + "-------------"); //5个线程很快就发完了
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



    // 起10个线程，从队列里取，死循环，扔给ａｃｔｏｒ; 跟起一个线程，遍历扔给ａｃｔｏｒ有区别吗？!!!!!一个线程的话,取的操作就没有并发了,.....
    // 都扔给ａｃｔｏｒ，ａｃｔｏｒ
}
