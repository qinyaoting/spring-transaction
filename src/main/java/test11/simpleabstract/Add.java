package test11.simpleabstract;

/**
 * Created by chin on 8/30/16.
 */
public class Add extends  Arithmetic  {
    public Add  (int first, int second) {
        super(first, second);
    }
    public int getResult() {
        return getFirst() + getSecond();
    }
}

