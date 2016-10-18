package test24.kafka;


import java.util.Properties;

/**
 * Created by chin on 10/13/16.
 */
public class SendMessage {

    public static void main(String[] args) throws InterruptedException {


        /*Properties properties = new Properties();
        properties.put("zk.connect", "127.0.0.1:2181");
        properties.put("metadata.broker.list", "localhost:9092");
        properties.put("serializer.class", "kafka.serializer.StringEncoder");

        ProducerConfig producerConfig = new ProducerConfig(properties);
        Producer<String, String> producer = new Producer<String, String>(producerConfig);

        // 构建消息体
        KeyedMessage<String, String> keyedMessage = new KeyedMessage<String, String>("test-topic", "test-message");
        producer.send(keyedMessage);

        Thread.sleep(1000);

        producer.close();*/

    }
}
