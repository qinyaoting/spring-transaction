package test19.reflect;

/**
 * Created by chin on 9/7/16.
 */
public class Animal {

    @ReflectField(order = 0)
    public String name;
    @ReflectField(order = 1)
    public int age;
    @ReflectField(order = 2)
    public double height;

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                '}';
    }
}
