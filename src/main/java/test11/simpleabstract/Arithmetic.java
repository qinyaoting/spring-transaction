package test11.simpleabstract;

/**
 * Created by chin on 8/30/16.
 */
public abstract class Arithmetic {

    private int first;
    private int second;

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    public Arithmetic(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public abstract int getResult();
}

