package fm.liveswitch;

public abstract class FutureBase<T> {
    private Exception _exception;
    private T _result;
    private FutureState _state;

    protected FutureBase() {
    }

    public Exception getException() {
        return this._exception;
    }

    public T getResult() {
        return this._result;
    }

    public FutureState getState() {
        return this._state;
    }

    /* access modifiers changed from: protected */
    public void setException(Exception exc) {
        this._exception = exc;
    }

    /* access modifiers changed from: protected */
    public void setResult(T t) {
        this._result = t;
    }

    /* access modifiers changed from: protected */
    public void setState(FutureState futureState) {
        this._state = futureState;
    }
}
