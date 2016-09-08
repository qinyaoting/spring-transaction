package test19.reflect;

/**
 * Created by chin on 9/7/16.
 */
public class Person {

    @ReflectField(order = 0)
    public String name;
    @ReflectField(order = 1)
    public int age;
    @ReflectField(order = 2)
    public double height;

    public char sex;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", sex=" + sex +
                '}';
    }
}
