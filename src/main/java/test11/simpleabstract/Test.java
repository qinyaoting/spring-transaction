package test11.simpleabstract;

/**
 * Created by chin on 8/30/16.
 */
public class Test
{

    public static void main(String[] args) {
        Arithmetic   a = new Add (1,2);
        System.out.println(a.getResult());

        Arithmetic   b = new Sub (1,2);
        System.out.println(b.getResult());
    }
}
