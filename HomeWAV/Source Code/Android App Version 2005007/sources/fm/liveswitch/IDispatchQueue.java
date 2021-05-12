package fm.liveswitch;

public interface IDispatchQueue<T> {
    void destroy();

    void enqueue(T t);

    long getQueueCount();
}
