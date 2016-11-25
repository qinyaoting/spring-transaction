package test39.filter2;

/**
 * Created by chin on 11/25/16.
 */
public class HtmlFilter implements Filter {
    @Override
    public String doFilter(String str) {
        String r = str;
        r = r.replace("<", "&lt;").replace(">", "&gt;");
        return r;
    }
}
