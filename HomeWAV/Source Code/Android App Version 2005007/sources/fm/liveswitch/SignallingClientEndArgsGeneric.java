package fm.liveswitch;

abstract class SignallingClientEndArgsGeneric<T> extends SignallingClientEndArgs {
    private T _methodArgs;

    public T getMethodArgs() {
        return this._methodArgs;
    }

    public void setMethodArgs(T t) {
        this._methodArgs = t;
    }

    public SignallingClientEndArgsGeneric(T t) {
        setMethodArgs(t);
    }
}
