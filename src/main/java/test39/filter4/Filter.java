package test39.filter4;



/**
 * Created by chin on 11/25/16.
 */
public interface Filter {
    void doFilter(Request req,Response resp,FilterChain chain);
}
