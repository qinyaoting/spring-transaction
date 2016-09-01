package test13.simplefactory;

/**
 * Created by chin on 8/31/16.
 */
public class Test {

    public static void main(String[] args) {
        try {
            SimpleFactory.factory("Washer");
            SimpleFactory.factory("Icebox");
            SimpleFactory.factory("AirCondition");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 由上面的代码可以看出，简单工厂的核心就是一个SimpleFactory类，他拥有必要的逻辑判断能力和所有产品的创建权利，我们只需要向把定单给他，
     * 就能得到我们想要的产品。这使用起来似乎非常方便。但，实际上，这个SimpleFactory有很多的局限。首先，我们每次想要增加一种新产品的时候，
     * 都必须修改SimpleFactory的原代码。其次，当我们拥有很多很多产品的时候，而且产品之间又存在复杂的层次关系的时候，这个类必须拥有复杂的逻辑判断能力，
     * 其代码量也将不断地激增，这对以后的维护简直就是恐怖两个字...还有就是，整个系统都严重依赖SimpleFactory类，只要SimpleFactory类一出问题，
     * 系统就进入不能工作的状态，这也是最为致命的一点....以上的不足将在工厂模式的另外两种状态中得到解决。
     */

    /*

     */
}
