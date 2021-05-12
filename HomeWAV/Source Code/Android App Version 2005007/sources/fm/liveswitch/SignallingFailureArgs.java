package fm.liveswitch;

abstract class SignallingFailureArgs extends SignallingOutputArgs {
    private Exception _exception;

    static int getErrorCode(Exception exc) {
        if (exc == null) {
            return -1;
        }
        try {
            if (StringExtensions.isNullOrEmpty(exc.getMessage())) {
                return -1;
            }
            if (StringExtensions.indexOf(exc.getMessage(), "::", StringComparison.InvariantCulture) <= -1) {
                return -1;
            }
            String str = Splitter.split(exc.getMessage(), "::")[0];
            IntegerHolder integerHolder = new IntegerHolder(-1);
            boolean tryParseIntegerValue = ParseAssistant.tryParseIntegerValue(str, integerHolder);
            int value = integerHolder.getValue();
            if (!tryParseIntegerValue) {
                return -1;
            }
            return value;
        } catch (Exception unused) {
            return -1;
        }
    }

    public int getErrorCode() {
        return getErrorCode(getException());
    }

    static String getErrorMessage(Exception exc) {
        if (exc == null) {
            return null;
        }
        try {
            if (StringExtensions.isNullOrEmpty(exc.getMessage())) {
                return null;
            }
            if (StringExtensions.indexOf(exc.getMessage(), "::", StringComparison.InvariantCulture) > -1) {
                return Splitter.split(exc.getMessage(), "::")[1];
            }
            return exc.getMessage();
        } catch (Exception unused) {
            return null;
        }
    }

    public String getErrorMessage() {
        return getErrorMessage(getException());
    }

    public Exception getException() {
        return this._exception;
    }

    public void setException(Exception exc) {
        this._exception = exc;
    }

    protected SignallingFailureArgs() {
    }
}
