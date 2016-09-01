package test10.job;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Judge {

	/*===================== 判断是否为空相关 ==============================*/

    /**
     * 字符串是否为空
     *
     * @param s
     * @return
     */
    public static boolean empty(String s) {
        return s == null || s.length() < 1;
    }

    /**
     * 判断集合是否为空
     *
     * @param col
     * @return
     */
    public static boolean empty(Collection<?> col) {
        return col == null || col.size() == 0;
    }

    /**
     * 数组是否为空
     *
     * @param arr
     * @return
     */
    public static <T> boolean empty(T[] arr) {
        return arr == null || arr.length == 0;
    }

    /**
     * 当字符串为null,"",或去掉前后的空格后为空时，返回true
     *
     * @param s
     * @return
     */
    public static boolean trimEmpty(String s) {
        return s == null || s.trim().length() < 1;
    }

    /**
     * 是否有null<br>
     * Judge.hasNull("a", null) => true<br>
     * Judge.hasNull(null, null) => true <br>
     * Judge.hasNull("a", "a") => true
     * @param arr
     * @return
     */
    public static boolean hasNull(Object... arr) {
        if (arr == null) {
            return true;
        }
        for (Object obj : arr) {
            if (obj == null) {
                return true;
            }
        }
        return false;
    }

	/*===================== 判断字符串是否是数字相关 ==============================*/

    /**
     * 判断字符串是否都是数字组成 <br />
     * isDigit("34") => true <br />
     * isDigit("34dd") => false
     * @param s
     * @return
     */
    public static boolean isDigit(String s) {
        if(empty(s)) {
            return false;
        }
        return s.matches("[0-9]{1,}");
    }

    /**
     * n是否是2的幂次方，如 4 = 2^2
     * @param n
     * @return
     */
    public static boolean isPowerOfTwo(int n) {
        // 通过查找n的二进制数中1的个数是否为1来判断
        int count = 0; // 1的个数
        while (n != 0) {
            count += n & 0x01;
            n >>= 1;
        }
        return count <= 1; // 0 也是
    }

	/*===================== 判断是否相等相关 ==============================*/

    /**
     * 判断两个对象是否相等<br>
     * 如果直接==，则返回true，如果有一方为null，返回false,其余使用equals判断<br>
     * equals(null, null) = true<br>
     * equals("aa", "aa") = true<br>
     * equals(new String("a"), new String("a")) = true<br>
     * equals(new Judge(), new Judge()) = false
     * @param obj1
     * @param obj2
     * @return
     */
    public static boolean equals(Object obj1, Object obj2) {
//		if (obj1 == obj2) {
//			return true;
//		}
//		// 有一个为null，另一个不为null(如果都为null则obj1==obj2)
//		if (obj1 == null || obj2 == null) {
//			return false;
//		}
//		// 都不为null
//		return obj1.equals(obj2);

		/*============== 下面代码摘自《org.junit.Assert.equalsRegardingNull(Object, Object)》 ====================*/
        if (obj1 == null) {
            return obj2 == null;
        }
        return obj1.equals(obj2);
    }

    /**
     * 比较两个对象的字段值是否相等 <br>
     * 以obj1的字段为基准，找到对应的obj2的字段，使用Judge.equals()进行比较字段值 <br>
     * 注意！所比较的对象的字段需是基本类型或者重写了equals()的类型，否则比较的是地址
     * @param obj1
     * @param obj2
     * @return 若obj1和obj2有一个为null,返回false，若obj1没有字段，返回true
     * @throws Exception
     */
    public static boolean equalsFields(Object obj1, Object obj2) throws Exception{
        if (obj1 == null || obj2 == null) {
            return false;
        }
        Field[] fields = obj1.getClass().getDeclaredFields();
        if (empty(fields)) {
            return true;
        }

        for (Field f : fields) {
            f.setAccessible(true);
            Object v1 = f.get(obj1);


            Field f2;
            try {
                f2 = obj2.getClass().getDeclaredField(f.getName());
            }catch (NoSuchFieldException e) {
                return false;
            }
            f2.setAccessible(true);
            Object v2 = f2.get(obj2);

            if (!equals(v1, v2)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 比较两个数组是否相同
     * @param a1
     * @param a2
     * @return
     */
    public static <T> boolean equals(T[] a1, T[] a2) {
        if (a1 == null && a2 != null) {
            return false;
        }
        if (a2 == null && a1 != null) {
            return false;
        }
        if (a1 == null && a2 == null) {
            return true;
        }
        if (a1.length != a2.length) {
            return false;
        }
        for (int i = 0; i < a1.length; i++) {
            if (a1[i] != a2[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 比较两个数组是否相同，参考自《org.junit.internal.ComparisonCriteria.arrayEquals(String, Object, Object)》
     * @param expecteds
     * @param actuals
     * @return
     */
    public static boolean arrayEquals(Object expecteds, Object actuals) {
        if (expecteds == actuals) {
            return true;
        }
        int length = arraySameLength(expecteds, actuals);
        if (length < 0) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            Object expected = Array.get(expecteds, i);
            Object actual = Array.get(actuals, i);
            boolean equals = false;
            if (isArray(expected) && isArray(actual)) {
                equals = arrayEquals(expected, actual);
            } else {
                equals = equals(expected, actual);
            }
            if (!equals) {
                return false;
            }
        }
        return true;
    }

    /**
     * 两个数组长度是否相同，参考自《org.junit.internal.ComparisonCriteria.assertArraysAreSameLength(Object, Object, String)》
     * @param expecteds
     * @param actuals
     * @return 若长度相同，返回长度，否则返回-1
     */
    public static int arraySameLength(Object expecteds, Object actuals) {
        if (expecteds == null || actuals == null) {
            throw new IllegalArgumentException("array was null");
        }
        int expectedLength = Array.getLength(expecteds);
        int actualLength = Array.getLength(actuals);

        return expectedLength == actualLength ? expectedLength : -1;
    }

    /**
     * 判断arr是否是数组
     * @param arr
     * @return
     */
    public static boolean isArray(Object arr) {
        return arr != null && arr.getClass().isArray();
    }

    /*===================== 金额相关 ==============================*/
    private static Pattern moneyPattern = Pattern.compile("^[+-]?(([1-9][,\\d]*)|(0))(\\.\\d*)?$");

    /**
     * 是否是金额
     * @param s
     * @return
     */
    public static boolean isMoney(String s) {
        if (empty(s)) {
            return false;
        }
        Matcher m = moneyPattern.matcher(s);
        return m.matches();
    }

	/*===================== 中文相关 ==============================*/

    private static Pattern CNPattern = Pattern.compile("[\\u4E00-\\u9FA5]+");

    /**
     * 是否包含中文
     * @param s
     * @return
     */
    public static boolean containsCN(String s) {
        if (empty(s)) {
            return false;
        }
        Matcher m = CNPattern.matcher(s);
        return m.find();
    }

}
