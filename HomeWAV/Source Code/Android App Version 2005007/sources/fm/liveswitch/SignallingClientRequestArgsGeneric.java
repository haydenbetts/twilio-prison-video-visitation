package fm.liveswitch;

abstract class SignallingClientRequestArgsGeneric<T> extends SignallingClientRequestArgs {
    private T _methodArgs;

    public T getMethodArgs() {
        return this._methodArgs;
    }

    public void setMethodArgs(T t) {
        this._methodArgs = t;
    }

    public SignallingClientRequestArgsGeneric(T t) {
        setMethodArgs(t);
    }
}
