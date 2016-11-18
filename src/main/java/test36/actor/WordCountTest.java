package test36.actor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * Created by chin on 11/18/16.
 *
 *
 * http://www.open-open.com/lib/view/open1418781209480.html
 *
 */
public class WordCountTest {

    public static void main(String[] args) throws InterruptedException {


        //Thread.sleep(22000);

        ActorSystem system = ActorSystem.create("WordCounter");
        ActorRef ref = system.actorOf(Props.create(WordCounterActor.class),"wordCounterActor");
        ref.tell("start", ActorRef.noSender());


        // WordCounterActor读取一个文件,遍历每一行,创建许多actor, 扔给actor,
        // StringCounterActor接受到一行文本后,统计单词, 把数量通知给WordCounterActor, 进行汇总

        // 貌似要处理的文件, 不是通过tell传给WordCounterActor的, WordCounterActor有一些计数器,所以WordCounterActor是一个实例
        // StringCounterActor要创建很多, 实现并行处理
        // WordCounterActor的遍历方法,不就是依次创建吗?用多个线程并发处理,可以把

        // 300w个StringCounterActor 1.9G内存


    }
}
