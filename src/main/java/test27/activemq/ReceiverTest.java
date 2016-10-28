package test27.activemq;

/**
 * Created by chin on 10/28/16.
 */

        import org.springframework.beans.factory.BeanFactory;
        import org.springframework.beans.factory.xml.XmlBeanFactory;
        import org.springframework.context.ApplicationContext;
        import org.springframework.context.support.ClassPathXmlApplicationContext;
        import org.springframework.core.io.ClassPathResource;
        import org.springframework.core.io.Resource;

public class ReceiverTest {
    public static void main(String[] args) {
        //ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //Receiver receiver = (Receiver) context.getBean("receiver");


        Resource resource = new ClassPathResource("applicationContext.xml");
        BeanFactory factory = new XmlBeanFactory(resource);
        Receiver receiver = (Receiver) factory.getBean("receiver");
        System.out.print(receiver.receiveMessage());
    }
}