package test7.annotation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by chin on 8/22/16.
 *
 * http://www.cnblogs.com/pepcod/archive/2013/02/16/2913474.html
 */
public class Test {

    public static void main(String[] args) {
        List<Integer> userCases = new ArrayList<Integer>();
        Collections.addAll(userCases, 47, 48, 49, 50);
        trackUserCase(userCases,PasswordUtils.class);
    }

    public static void trackUserCase(List<Integer> useCases, Class<?> cl) {

        for (Method m : cl.getDeclaredMethods()) {
            UserCase uc = m.getAnnotation(UserCase.class);
            if (uc != null) {
                System.out.println("Found Use Case:" + uc.id() + " "
                        + uc.description());
                useCases.remove(new Integer(uc.id()));
            }
        }

        for (int i : useCases) {
            System.out.println("Warning: Missing use case-" + i);
        }
    }
}
