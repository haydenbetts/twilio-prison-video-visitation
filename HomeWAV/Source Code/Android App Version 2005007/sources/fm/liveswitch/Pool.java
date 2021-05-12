package fm.liveswitch;

public class Pool<T> {
    private IFunction0<T> __createObject;
    private AtomicInteger __createObjectCounter;
    private Object __destroyLock;
    private volatile boolean __isDestroyed;
    private ManagedConcurrentStack<T> __stack;
    private int _maxSize;
    private int _minSize;

    public boolean destroy(IAction1<T> iAction1) {
        synchronized (this.__destroyLock) {
            this.__isDestroyed = true;
            Holder holder = new Holder(null);
            boolean tryPop = this.__stack.tryPop(holder);
            Object value = holder.getValue();
            while (tryPop) {
                iAction1.invoke(value);
            }
        }
        return true;
    }

    public T get() {
        Holder holder = new Holder(null);
        boolean tryPop = this.__stack.tryPop(holder);
        T value = holder.getValue();
        if (tryPop) {
            return value;
        }
        if (this.__createObjectCounter.increment() <= getMaxSize()) {
            return this.__createObject.invoke();
        }
        this.__createObjectCounter.decrement();
        throw new RuntimeException(new Exception("Could not create pooled object. Maximum size reached."));
    }

    public int getAvailable() {
        return this.__stack.getCount();
    }

    public int getMaxSize() {
        return this._maxSize;
    }

    public int getMinSize() {
        return this._minSize;
    }

    public int getSize() {
        return this.__createObjectCounter.getValue();
    }

    public Pool(IFunction0<T> iFunction0) {
        this(iFunction0, 0);
    }

    public Pool(IFunction0<T> iFunction0, int i) {
        this(iFunction0, i, Integer.MAX_VALUE);
    }

    public Pool(IFunction0<T> iFunction0, int i, int i2) {
        this.__destroyLock = new Object();
        this.__isDestroyed = false;
        if (iFunction0 == null) {
            throw new RuntimeException(new Exception("Cannot initialize pool without a function to create objects."));
        } else if (i < 0) {
            throw new RuntimeException(new Exception("Minimum pool size cannot be less than zero."));
        } else if (i2 < 0) {
            throw new RuntimeException(new Exception("Maximum pool size cannot be less than zero."));
        } else if (i <= i2) {
            this.__createObject = iFunction0;
            setMinSize(i);
            setMaxSize(i2);
            this.__stack = new ManagedConcurrentStack<>();
            this.__createObjectCounter = new AtomicInteger();
            for (int i3 = 0; i3 < getMinSize(); i3++) {
                this.__createObjectCounter.increment();
                this.__stack.push(this.__createObject.invoke());
            }
        } else {
            throw new RuntimeException(new Exception("Minimum pool size cannot be greater than maximum pool size."));
        }
    }

    public boolean put(T t) {
        if (t == null || this.__isDestroyed) {
            return false;
        }
        this.__stack.push(t);
        return true;
    }

    private void setMaxSize(int i) {
        this._maxSize = i;
    }

    private void setMinSize(int i) {
        this._minSize = i;
    }
}
