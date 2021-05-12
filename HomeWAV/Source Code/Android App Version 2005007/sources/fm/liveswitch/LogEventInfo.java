package fm.liveswitch;

import com.stripe.android.CustomerSession;
import java.util.HashMap;

public class LogEventInfo extends Info {
    private String _exception;
    private String _level;
    private String _message;
    private String _scope;
    private String _tag;
    private long _threadId;

    /* access modifiers changed from: protected */
    public void deserializeProperties(String str, String str2) {
        super.deserializeProperties(str, str2);
        if (str == null) {
            return;
        }
        if (Global.equals(str, "level")) {
            setLevel(JsonSerializer.deserializeString(str2));
        } else if (Global.equals(str, "tag")) {
            setTag(JsonSerializer.deserializeString(str2));
        } else if (Global.equals(str, "scope")) {
            setScope(JsonSerializer.deserializeString(str2));
        } else if (Global.equals(str, "message")) {
            setMessage(JsonSerializer.deserializeString(str2));
        } else if (Global.equals(str, CustomerSession.EXTRA_EXCEPTION)) {
            setException(JsonSerializer.deserializeString(str2));
        } else if (Global.equals(str, "threadId")) {
            setThreadId(JsonSerializer.deserializeLong(str2).getValue());
        }
    }

    public static LogEventInfo fromJson(String str) {
        return (LogEventInfo) JsonSerializer.deserializeObject(str, new IFunction0<LogEventInfo>() {
            public LogEventInfo invoke() {
                return new LogEventInfo();
            }
        }, new IAction3<LogEventInfo, String, String>() {
            public void invoke(LogEventInfo logEventInfo, String str, String str2) {
                logEventInfo.deserializeProperties(str, str2);
            }
        });
    }

    public String getException() {
        return this._exception;
    }

    public String getLevel() {
        return this._level;
    }

    public String getMessage() {
        return this._message;
    }

    public String getScope() {
        return this._scope;
    }

    public String getTag() {
        return this._tag;
    }

    public long getThreadId() {
        return this._threadId;
    }

    /* access modifiers changed from: protected */
    public void serializeProperties(HashMap<String, String> hashMap) {
        super.serializeProperties(hashMap);
        if (getLevel() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "level", JsonSerializer.serializeString(getLevel()));
        }
        if (getTag() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "tag", JsonSerializer.serializeString(getTag()));
        }
        if (getScope() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "scope", JsonSerializer.serializeString(getScope()));
        }
        if (getMessage() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "message", JsonSerializer.serializeString(getMessage()));
        }
        if (getException() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), CustomerSession.EXTRA_EXCEPTION, JsonSerializer.serializeString(getException()));
        }
        if (getThreadId() != 0) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "threadId", JsonSerializer.serializeLong(new NullableLong(getThreadId())));
        }
    }

    public void setException(String str) {
        this._exception = str;
    }

    private void setLevel(String str) {
        this._level = str;
    }

    public void setMessage(String str) {
        this._message = str;
    }

    public void setScope(String str) {
        this._scope = str;
    }

    public void setTag(String str) {
        this._tag = str;
    }

    public void setThreadId(long j) {
        this._threadId = j;
    }

    public static String toJson(LogEventInfo logEventInfo) {
        return JsonSerializer.serializeObject(logEventInfo, new IAction2<LogEventInfo, HashMap<String, String>>() {
            public void invoke(LogEventInfo logEventInfo, HashMap<String, String> hashMap) {
                logEventInfo.serializeProperties(hashMap);
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }
}
