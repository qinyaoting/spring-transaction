package test14.factory;

import test13.simplefactory.Product;

/**
 * Created by chin on 8/31/16.
 */
public class IceboxFactory implements Factory {
    @Override
    public Product create() {
        return new Icebox();
    }
}
