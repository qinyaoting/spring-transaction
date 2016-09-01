package test10.job;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringUtil {

	/* ===================== 字符串显示相关 ============================== */

    /**
     * 浮点数转换成百分比的形式，小数点后总会是2位精度<br />
     * 如float_percent(3.4f) => 3.40%
     *
     * @param f
     * @return
     */
    public static String float_percent(float f) {
        return String.format("%1$.2f%%", f);
    }

    /**
     * 浮点数转换成百分比的形式，小数点后总会是2位精度<br />
     * 如float_percent(3.4) => 3.40%
     *
     * @param d
     * @return
     */
    public static String float_percent(double d) {
        return String.format("%1$.2f%%", d);
    }

	/* ===================== 字符串修改相关 ============================== */

    /**
     * String.subString截取时会将汉字当做一个字节，本方法会按byte来截取， 如substr_byte("he你", 1, 3) =>
     * e￤ , 而"he你".substring(1, 3) => e你
     *
     * @param src
     * @param start_idx
     * @param end_idx
     * @return "" 或 截取后的字符串
     */
    public static String substr_byte(String src, int start_idx, int end_idx) {
        byte[] bytes = src.getBytes();
        if (start_idx < 0 || end_idx < 0 || start_idx > end_idx) {
            return "";
        }
        StringBuilder ret = new StringBuilder();
        for (int i = start_idx; i < end_idx; i++) {
            ret.append((char) bytes[i]);
        }
        return ret.toString();
    }

    /**
     * 删除str中的空白符，空白符的规定参考Character.isWhitespace()
     *
     * @param str
     * @return
     */
    public static String deleteWhitespace(String str) {
        StringBuffer buffer = new StringBuffer();
        int sz = str.length();
        for (int i = 0; i < sz; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                buffer.append(str.charAt(i));
            }
        }
        return buffer.toString();
    }

	/* ===================== 从字符串中获取数字相关 ============================== */

    /**
     * 获取字符串中的所有数字 <br />
     * allNumberFromStr("aakk39tt88") => 3988
     *
     * @param s
     * @return 数字或""
     */
    public static String allNumberFromStr(String s) {
        if (Judge.trimEmpty(s)) {
            return "";
        }
        Pattern p = Pattern.compile("[^0-9]");
        Matcher m = p.matcher(s);
        return m.replaceAll("").trim();
    }

    /**
     * 获取字符串中的数字集合 <br />
     * numberListFromStr("tt234pp09kk4") => [234, 09, 4]
     *
     * @param s
     * @return 至少为空集合
     */
    public static List<String> numberListFromStr(String s) {
        Pattern p = Pattern.compile("(\\d+)");
        Matcher m = p.matcher(s);
        List<String> list = new ArrayList<String>();
        while (m.find()) {
            list.add(m.group(1));
        }
        return list;
    }

    /**
     * 获取字符串中的数字集合 <br />
     * longListFromStr("tt234pp09kk4") => [234, 9, 4]
     *
     * @param s
     * @return 至少为空集合
     */
    public static List<Long> longListFromStr(String s) {
        Pattern p = Pattern.compile("(\\d+)");
        Matcher m = p.matcher(s);
        List<Long> list = new ArrayList<Long>();
        while (m.find()) {
            list.add(convertLong(m.group(1), 0L));
        }
        return list;
    }

    /**
     * 获取字符串中的第一组连续的数字 <br />
     * firstNumberFromStr("ee90we34dd") => 90
     *
     * @param s
     * @return 数字或""
     */
    public static String firstNumberFromStr(String s) {
        if (Judge.trimEmpty(s)) {
            return "";
        }
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(s);
        if (m.find()) {
            return m.group();
        }
        return "";
    }

	/* ===================== 转换相关 ============================== */

    /**
     * 将字符串转换为Long型，调用了Long.valueOf(),但不抛出异常 <br />
     * convertLong("34", 0L) => 34 <br>
     * convertLong("", 0L) => 0<br>
     * convertLong("", null) => null
     *
     * @param s
     * @param defVal
     *            当Long.valueOf()跑出异常时返回该值
     * @return
     */
    public static Long convertLong(String s, Long defVal) {
        try {
            return Long.valueOf(s);
        } catch (NumberFormatException e) {
            return defVal;
        }
    }

    public static int	intSize	= 32;

    public static int parseInt(String s, int base) {
        if (s == null || s.length() <= 0) {
            throw new RuntimeException("Empty string bad");
        }

        boolean neg = false;
        if (s.charAt(0) == '+') {
            s = s.substring(1);
        } else if (s.charAt(0) == '-') {
            neg = true;
            s = s.substring(1);
        }

        if (s.length() < 1) {
            throw new RuntimeException("invalid syntax");
        }

        if (2 <= base && base <= 36) {
            // valid base; nothing to do
        } else if (base == 0) {
            // Look for octal, hex prefix.
            if (s.charAt(0) == '0' && s.length() > 1 && (s.charAt(1) == 'x' || s.charAt(1) == 'X')) {
                base = 16;
                s = s.substring(2);
                if (s.length() < 1) {
                    throw new RuntimeException("invalid syntax");
                }
            } else if (s.charAt(0) == '0') {
                base = 8;
            } else {
                base = 10;
            }
        } else {
            throw new RuntimeException("invalid base");
        }

        // TODO

        return 0;
    }

    public static void main(String[] args) {
        String s = "1";
        System.out.println("s=" + s.substring(1));
    }

}
