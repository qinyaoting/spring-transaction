package test59.template;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by chin on 12/13/16.
 */
public class FtocTemplateMethod extends Application {

    /**
     * 错误的使用方法,
     */

    private InputStreamReader isr;
    private BufferedReader br;

    public static void main(String[] args) {
        (new FtocTemplateMethod()).run();
    }

    @Override
    protected void init() {
        isr = new InputStreamReader(System.in);
        br = new BufferedReader(isr);
    }

    @Override
    protected void idle() {
        String fahrString = readLineAndResultNullIfError();
        if (fahrString == null || fahrString.length()==0)
            setDone();
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
    protected void cleanup() {
        System.out.println("ftc exit");
    }
}
