package test40.guava;

import com.google.common.base.Objects;

/**
 * Created by chin on 11/28/16.
 */
public class Person {

    private int id;
    private String name;
    private double salary;

    public Person(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public Person() {
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("salary", salary)
                .toString();

    }
}
