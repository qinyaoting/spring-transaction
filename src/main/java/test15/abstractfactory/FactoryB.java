package test15.abstractfactory;

/**
 * Created by chin on 8/31/16.
 */
//创建型号为B的产品工厂
public class FactoryB implements Factory{
    //创建洗衣机-B
    public Washer createWasher(){
        return new WasherB();
    }

    //创建冰箱-B
    public Icebox createIcebox(){
        return new IceboxB();
    }

    /**
     * 抽象工厂和工厂方法的区别
     *
     * 在于它们的意图不同而不是工厂方法是创建一个产品，而抽象工厂是创建很多产品。

     抽象工厂只是创建一系列相互依赖或关联地产品的接口，是针对类的设计模式。

     工厂方法是定一个一个接口，让子类去实例化，是针对对象的设计模式。
     */
}
