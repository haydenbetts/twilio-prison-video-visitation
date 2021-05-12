package fm.liveswitch;

import java.util.HashMap;

public class Error {
    private ErrorCode _code;
    private Exception _exception;

    public Error() {
    }

    public Error(ErrorCode errorCode, Exception exc) {
        setCode(errorCode);
        setException(exc);
    }

    public Error(ErrorCode errorCode, String str) {
        setCode(errorCode);
        setMessage(str);
    }

    public static Error fromJson(String str) {
        return (Error) JsonSerializer.deserializeObject(str, new IFunction0<Error>() {
            public Error invoke() {
                return new Error();
            }
        }, new IAction3<Error, String, String>() {
            public void invoke(Error error, String str, String str2) {
                if (str == null) {
                    return;
                }
                if (Global.equals(str, "code")) {
                    error.setCodeValue(JsonSerializer.deserializeInteger(str2).getValue());
                } else if (Global.equals(str, "message")) {
                    error.setMessage(JsonSerializer.deserializeString(str2));
                }
            }
        });
    }

    public ErrorCode getCode() {
        return this._code;
    }

    public int getCodeValue() {
        return getCode().getAssignedValue();
    }

    public String getDescription() {
        String errorCode = getCode().toString();
        String message = getMessage();
        if (message != null) {
            errorCode = StringExtensions.concat(errorCode, " ", message);
        }
        String trimEnd = StringExtensions.trimEnd(errorCode, new char[0]);
        return !trimEnd.endsWith(".") ? StringExtensions.concat(trimEnd, ".") : trimEnd;
    }

    public ErrorCode getErrorCode() {
        return getCode();
    }

    public Exception getException() {
        return this._exception;
    }

    public String getMessage() {
        if (getException() == null) {
            return null;
        }
        return getException().getMessage();
    }

    public void setCode(ErrorCode errorCode) {
        this._code = errorCode;
    }

    public void setCodeValue(int i) {
        setCode(ErrorCode.getByAssignedValue(i));
    }

    public void setErrorCode(ErrorCode errorCode) {
        setCode(errorCode);
    }

    public void setException(Exception exc) {
        this._exception = exc;
    }

    public void setMessage(String str) {
        setException(str == null ? null : new Exception(str));
    }

    public static String toJson(Error error) {
        return JsonSerializer.serializeObject(error, new IAction2<Error, HashMap<String, String>>(error) {
            final /* synthetic */ Error val$error;

            {
                this.val$error = r1;
            }

            public void invoke(Error error, HashMap<String, String> hashMap) {
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "code", JsonSerializer.serializeInteger(new NullableInteger(error.getCodeValue())));
                if (this.val$error.getMessage() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "message", JsonSerializer.serializeString(error.getMessage()));
                }
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public String toString() {
        return getDescription();
    }
}
