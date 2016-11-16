package test32.random;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chin on 11/15/16.
 */
public class RandomTest {

    public enum Status {SUCCESS,FAILED,EXCEPTION};  //96% 3% 1%

    static Map<Integer,Integer> map = new HashMap<Integer,Integer>(4);


    public static void main(String[] args) {

        Roulette roulette = new Roulette();

        for (int i=0;i<10000;i++) {
           int flag = roulette.getRandom();
            System.out.println(flag);
            if (map.get(flag) != null) {
                int val = map.get(flag).intValue();
                map.put(flag, ++val);
            } else {
                map.put(flag, 1);
            }

        }

        for (int key : map.keySet()) {


            System.out.println("key:" + key + " count:" + map.get(key));

        }


    }



}
