package test55.singleton;

/**
 * Created by chin on 8/25/16.
 */
public class Singleton {

    private Singleton(){}

    public static Singleton instance = null;


    // 单线程使用
    /*public static Singleton getInstance () {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }*/

    // 多线程
    /*public static synchronized Singleton getInstance () {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }*/
    //  不过这种简单的写法不适合用于像服务器这种服务很多线程的程序上，同步机制会造成相当的效能低落，
    //  为了顾及Singleton、Lazy Initialization与效能问题，因而有了Double-check Locking的模式：




    public static Singleton getInstance() {
        if (instance == null){
            synchronized(Singleton.class){
                if(instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    // new Singleton()不是一个atomic的操作。
    //你的instance可能处于一个不是null，但是又不是完整对象的状态。
    //这样会导致程序崩溃。
    // 好像有几篇文章论述了java中DCL不可实现


    // 个更牛的写法，既满足Lazy load，又满足只有一个实例，即使多线程环境也是一个实例。
    /*public class Singleton {

        static class SingletonHolder {

            static Singleton instance = new Singleton();

        }

        public static Singleton getInstance() {

            return SingletonHolder.instance;

        }
    }*/

    /*public class Singleton {

        private static volatile Singleton instance = null;

        // private constructor suppresses
        private Singleton(){
        }

        public static Singleton getInstance() {
            // if already inited, no need to get lock everytime
            if (instance == null) {
                synchronized (Singleton.class) {
                    if (instance == null) {
                        instance = new Singleton();
                    }
                }
            }

            return instance;
        }
    }*/


}


