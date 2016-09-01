package test2.enum2;

/**
 * Created by chin on 5/17/16.
 */
public enum Color implements Behaviour {
    ;

    private String name;
    private int index;

    Color(String name, int index) {
        this.name = name;
        this.index = index;
    }

    @Override
    public void print() {
        System.out.println(this.index + ":" + this.name);
    }

    @Override
    public String getInfo() {
        return this.name;
    }
}
