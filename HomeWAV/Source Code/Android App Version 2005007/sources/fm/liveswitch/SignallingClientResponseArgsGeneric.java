package fm.liveswitch;

abstract class SignallingClientResponseArgsGeneric<T> extends SignallingClientResponseArgs {
    private T _methodArgs;

    public T getMethodArgs() {
        return this._methodArgs;
    }

    public void setMethodArgs(T t) {
        this._methodArgs = t;
    }

    private SignallingClientResponseArgsGeneric() {
    }

    public SignallingClientResponseArgsGeneric(T t) {
        this();
        setMethodArgs(t);
    }
}
