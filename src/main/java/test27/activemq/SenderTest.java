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
        import org.springframework.core.io.ResourceLoader;

public class SenderTest {
    public static void main(String[] args) throws InterruptedException {
        //ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //Sender sender = (Sender) context.getBean("sender");

        Resource resource = new ClassPathResource("applicationContext.xml");
        BeanFactory factory = new XmlBeanFactory(resource);
        Sender sender = (Sender) factory.getBean("sender");

        for (int i=0;i<1000;i++) {
            Thread.sleep(3000);
            sender.sendInfo();
        }


    }
}