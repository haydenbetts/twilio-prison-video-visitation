package lib.android.paypal.com.magnessdk;

public class InvalidInputException extends Exception {
    public InvalidInputException(String str) {
        super(str);
    }

    public InvalidInputException(Throwable th) {
        super(th);
    }
}
