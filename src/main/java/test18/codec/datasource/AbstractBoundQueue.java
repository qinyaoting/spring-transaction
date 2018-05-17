package test18.codec.datasource;

public interface AbstractBoundQueue<T> {
    public void put(T item) throws InterruptedException;
    public T take() throws InterruptedException;
}
