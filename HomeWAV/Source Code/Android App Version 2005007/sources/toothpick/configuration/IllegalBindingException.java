package toothpick.configuration;

public class IllegalBindingException extends IllegalStateException {
    public IllegalBindingException() {
    }

    public IllegalBindingException(String str) {
        super(str);
    }

    public IllegalBindingException(String str, Throwable th) {
        super(str, th);
    }

    public IllegalBindingException(Throwable th) {
        super(th);
    }
}
