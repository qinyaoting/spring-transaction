package test39.filter3;

import test39.filter2.Filter;

/**
 * Created by chin on 11/25/16.
 */
public class MsgProcessor {
    private String msg;
    private FilterChain chain;

    public MsgProcessor() {
    }

    public MsgProcessor(String msg,FilterChain chain){
        this.msg = msg;
        this.chain = chain;
    }

    public String process(){
        return chain.doFilter(msg);
    }
}
