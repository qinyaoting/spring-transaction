package test14.factory;

import test13.simplefactory.*;
import test13.simplefactory.Product;

/**
 * Created by chin on 8/31/16.
 */
public class AirCondition implements Product {
    public AirCondition(){
        System.out.println("空调被制造了");
    }
}
