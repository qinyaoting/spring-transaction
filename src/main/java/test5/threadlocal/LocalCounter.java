package test5.threadlocal;

/**
 * Created by chin on 6/14/16.
 */
public class LocalCounter {

    public Integer number =10;

    private static ThreadLocal<LocalCounter> counter = new ThreadLocal<LocalCounter>(){
        protected synchronized LocalCounter initialValue(){
            return new LocalCounter();
        }
    };//初始需要覆盖初始化方法，不覆盖第一次调用get方法值为null，使用前需要先调set方法初始化

    public static LocalCounter getCounter() {
        return (LocalCounter) counter.get();
    }

    public static void setCounter(LocalCounter counterFrom){
        counter.set(counterFrom);
    }
}
