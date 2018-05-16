package test58.strategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by chin on 12/13/16.
 */
public class FtocStrategy implements Application {  //1 实现接口

    public static void main(String[] args) {
        (new ApplicationRunner(new FtocStrategy())).run();
    }   //2 把自己传给Runner


    private InputStreamReader isr;
    private BufferedReader br;
    private boolean isDone = false;

    @Override
    public void init() {
        isr = new InputStreamReader(System.in);
        br = new BufferedReader(isr);
    }

    @Override
    public boolean done() {
        return isDone;
    }

    @Override
    public void idle() {
        String fahrString = readLineAndResultNullIfError();
        if (fahrString == null || fahrString.length()==0)
            isDone = true;
        else {
            double fahr = Double.parseDouble(fahrString);
            double celicus = 5.0/9.0 * (fahr -32);
            System.out.println("F=" + fahr + ", C=" + celicus);
        }
    }

    private String readLineAndResultNullIfError() {
        String s;
        try {
            s = br.readLine();
        } catch (IOException e) {
            s = null;
        }
        return s;
    }


    @Override
    public void cleanup() {
        System.out.println("ftc exit");
    }
}
