package test1.enum1;

/**
 * Created by chin on 5/17/16.
 */
public class Test {

    public static void main(String[] args) {
        String name = Color.getName(1);
        System.out.println(name);


        for (Color c: Color.values()) {


            System.out.println(c);
        }
    }
}
