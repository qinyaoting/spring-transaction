package test32.random;

import java.util.Random;

/**
 * Created by chin on 11/16/16.
 */
public class Roulette {
    /* program n_select=1000 times selects one of n=4 elements with weights weight[i].
     * Selections are summed up in counter[i]. For the weights as given in the example
     * below one expects that elements 0,1,2 and 3 will be selected (on average)
     * 200, 150, 600 and 50 times, respectively.  In good agreement with exemplary run.
     */
    static {

    }

    public Roulette() {
        init();
    }

    public void init() {
        int n=4;
        double [] weight = new double [n];
        weight[0]=0.01;
        weight[1]=0.97;
        weight[2]=0.01;
        weight[3]=0.01;
        double max_weight=1.2;
        int  [] counter = new int[n];
        int n_select=1000;

        int index=0;
        boolean notaccepted;
        for (int i=0; i<n_select; i++){
            notaccepted=true;
            while (notaccepted){
                index= (int)(n*Math.random());
                if(Math.random()<weight[index]/max_weight) {notaccepted=false;}
            }
            counter[index]++;
            result[i] = index;
        }
        for (int i=0; i<n; i++){
            System.out.println("counter["+i+"]="+counter[i]);

        }
        /*for (int i=0; i<n_select; i++){
            System.out.print("result:"+result[i] + " -- ");
        }*/
    }

    int  [] result = new int[1000];

    Random ra =new Random();

    public int getRandom() {
        int i = ra.nextInt(1000);
        return result[i];
    }
/* The program uses stochastic acceptance instead of linear (or binary) search.
 * More on http://arxiv.org/abs/1109.3627
 */
}