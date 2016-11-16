package test32.random;

/**
 * Created by chin on 11/15/16.
 */
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author hehaifeng
 *
 */
public class HFRandom {

    static Map<Integer , Integer > ds = new HashMap<Integer , Integer> () ;


    private static boolean getRamdon(int random ,  int randomNum) {
        int belance = randomNum - random;
        if(belance >= 0) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

        ds.put(50, 0);
        ds.put(1, 0);
        ds.put(4, 0);
        ds.put(15, 0);
        ds.put(10, 0);
        ds.put(20, 0);


        System.out.println(ds);

        for (int i = 0; i < 10000000; i++) {
            int random = org.apache.commons.lang.math.RandomUtils.nextInt(100);
            random++;
            for (int key : ds.keySet()) {
                if(getRamdon(random, key)){
                    ds.put(key, ds.get(key)+1 );
                    break;
                }else {
                    random -= key;
                }
            }
        }

        System.out.println(ds);


        int sum=0;
        for (Iterator iterator = ds.entrySet().iterator(); iterator.hasNext();) {
            Entry<Integer, Integer> entry = (Entry<Integer, Integer>) iterator.next();
            sum=sum+entry.getValue();
        }

        System.out.println(sum);

    }





}
