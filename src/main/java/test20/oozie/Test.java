package test20.oozie;

/**
 * Created by chin on 9/22/16.
 */
public class Test {

    public static void main(String[] args) throws CustomExcpetion {


        HttpClientUtil client = new HttpClientUtil("conf");
        String jobid = client.call();
    }
}
