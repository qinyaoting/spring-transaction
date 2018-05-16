package test59.template;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by chin on 12/13/16.
 */
public class Ftocraw {

    public static void main(String[] args) throws IOException {

        /*
         * 读取文件内容,把温度从华氏转为摄氏
         *
         * 面向过程的设计  大致分三部分,
         *
         * 一,初始化参数
         *
         * 二,循环获取内容
         *
         * 三,回收资源
         *
         */
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        boolean done = false;
        while (!done) {
            String fahrString = br.readLine();
            if (fahrString == null || fahrString.length()==0)
                done = true;
            else {
                double fahr = Double.parseDouble(fahrString);
                double celicus = 5.0/9.0 * (fahr -32);
                System.out.println("F=" + fahr + ", C=" + celicus);
            }
        }
        System.out.println("ftc exit");

    }
}
