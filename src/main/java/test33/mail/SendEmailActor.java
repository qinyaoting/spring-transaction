package test33.mail;

import akka.actor.ActorSelection;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.japi.Creator;
import test32.random.Roulette;

import java.util.Random;

/**
 * Created by chin on 11/16/16.
 */
public class SendEmailActor extends UntypedActor {

    Random random = new Random();
    Roulette roulette = new Roulette();

    public static Props props() {
        return Props.create(new Creator<SendEmailActor>() {
            @Override
            public SendEmailActor create() throws Exception {
                return new SendEmailActor();
            }
        });
    }


    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof AbstractTask) {

            AbstractTask task = (AbstractTask)message;
            //System.out.println("receive: ==== " + task.toString());
            //mockSleep();
            callHttp();
            String st = getRandomStatus();
            task.setStatus(st);
            //System.out.println("job done: ==== " + task.toString());

            ActorSelection sumActor = this.getContext().actorSelection("../sumActor");
            sumActor.tell(task, this.getSender());

        }
    }


    public void callHttp() {
        try {
            String response = scala.io.Source.fromURL("http://xw.qq.com/m/news/index.htm","UTF-8").getLines().mkString("\n");
            //System.out.println("response:"+ response.substring(0,12));
        } catch (Exception e) {
            System.out.println("err:"+e.getMessage());
        }
    }

    /**
     * 不能使用线程休眠, actor是共享线程的, 让线程休眠影响效率
     * @throws InterruptedException
     */
    public void mockSleep() throws InterruptedException {
        int max = 10;
        int min = 0;
        int s = random.nextInt(max)%(max-min+1) + min;
        //System.out.println("sleep:" + s);
        Thread.sleep(s);
    }

    /**
     * 随机的状态, FAILED 1%            SUCCESS 97%             EXCEPTION 1%              TIMEOUT 1%
     * @return
     */
    public String getRandomStatus() {
        int flag= roulette.getRandom();
        String msg = "";
        switch (flag) {
            case 0:
                msg = "FAILED";
                break;
            case 1:
                msg = "SUCCESS";
                break;
            case 2:
                msg = "EXCEPTION";
                break;
            case 3:
                msg = "TIMEOUT";
                break;

        }
        return msg;
    }
}
