package test15.abstractfactory;

/**
 * Created by chin on 8/31/16.
 */
//创建型号为A的产品工厂
public class FactoryA implements Factory{
    //创建洗衣机-A
    public Washer createWasher(){
        return new WasherA();
    }

    //创建冰箱-A
    public Icebox createIcebox(){
        return new IceboxA();
    }
} 
