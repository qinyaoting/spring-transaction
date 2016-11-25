package test39.filter4;

/**
 * Created by chin on 11/25/16.
 */
public class SensitiveFilter implements Filter {
    public void doFilter(Request req, Response resp, FilterChain chain) {
        // 过滤req.reqStr中的敏感词
        req.reqStr = req.reqStr.replace("敏感", "").replace("被就业", "就业");
        req.reqStr += "===SensitiveFilter";
        chain.doFilter(req, resp);
        resp.respStr += "===SensitiveFilter";
    }
}
