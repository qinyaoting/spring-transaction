package test39.filter4;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;


/**
 * Created by chin on 11/25/16.
 */
public class HtmlFilter implements Filter {

    @Override
    public void doFilter(Request req,Response resp,FilterChain chain) {
        //过滤req.reqStr中的HTML标记
        req.reqStr = req.reqStr.replace("<", "&lt;").replace(">", "&gt;");
        req.reqStr += "---HtmlFilter()---";
        chain.doFilter(req, resp);
        resp.respStr += "---HtmlFilter()---";
    }
}
