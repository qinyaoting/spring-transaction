package test55.singleton;

/**
 * Created by chin on 8/25/16.
 */
public class Test {

    public static void main(String[] args) {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        System.out.println(s1);
        System.out.println(s2);
    }
}
