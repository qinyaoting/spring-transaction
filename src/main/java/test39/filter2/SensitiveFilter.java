package test39.filter2;

/**
 * Created by chin on 11/25/16.
 */
public class SensitiveFilter implements Filter {
    @Override
    public String doFilter(String str) {
        String r = str;
        //过滤敏感词
        r = r.replace("敏感", "").replace("被就业", "就业");
        return r;
    }
}
