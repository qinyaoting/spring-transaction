package test39.filter3;

import test39.filter2.Filter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chin on 11/25/16.
 */
public class FilterChain implements Filter {


    // 链 里有个list
    public List<Filter> filters= new ArrayList<Filter>();

    public FilterChain addFilter(Filter f){
        filters.add(f);
        return this;        // 返回自身
    }

    // 链 的doFilter方法,是遍历list,
    @Override
    public String doFilter(String str) {
        String r = str;
        for(Filter f : filters){
            r = f.doFilter(r);
        }
        return r;
    }
}
