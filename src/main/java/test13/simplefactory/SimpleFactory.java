package test13.simplefactory;

/**
 * Created by chin on 8/31/16.
 */
public class SimpleFactory {

    public static Product factory(String productName) throws Exception{
        if(productName.equals("Washer")){
            return new Washer();
        }else if(productName.equals("Icebox")){
            return new Icebox();
        }else if(productName.equals("AirCondition")){
            return new AirCondition();
        }else{
            throw new Exception("没有该产品");
        }
    }
}
