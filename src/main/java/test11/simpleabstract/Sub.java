package test11.simpleabstract;

/**
 * Created by chin on 8/30/16.
 */
public class Sub extends Arithmetic {

    public Sub(int first, int second) {
        super(first, second);
    }

    @Override
    public int getResult() {
        return getFirst() - getSecond();
    }
}
