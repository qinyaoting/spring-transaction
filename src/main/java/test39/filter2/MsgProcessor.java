package test39.filter2;

/**
 * Created by chin on 11/25/16.
 */
public class MsgProcessor {
    private String msg;
    private Filter[] filters = {new HtmlFilter(),new SensitiveFilter()};

    public MsgProcessor(String msg){
        this.msg = msg;
    }

    public String process(){
        String r = msg;
        for(Filter f : filters){
            r = f.doFilter(r);
        }
        return r;
    }
}
