package test59.template;

import java.io.IOException;

/**
 * Created by chin on 1/6/17.
 */
public  class AbstractClassTest {

    public static void main(String[] args) throws IOException {
        AbstractClass c;
        c= new ConcreteClassA();
        c.templateMethod();

        c=new ConcreteClassB();
        c.templateMethod();
    }
}
