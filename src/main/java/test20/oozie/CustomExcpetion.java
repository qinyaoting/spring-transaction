package test20.oozie;

/**
 * Created by chin on 9/22/16.
 */
public class CustomExcpetion extends Exception {

    private String name;

    public CustomExcpetion(String name) {
        this.name = name;
    }
}
