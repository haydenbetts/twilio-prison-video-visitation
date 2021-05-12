package fm.liveswitch;

import com.stripe.android.CustomerSession;
import com.thoughtbot.expandablerecyclerview.BuildConfig;
import com.urbanairship.MessageCenterDataManager;
import java.util.Date;
import java.util.HashMap;

public class LogEvent {
    private Exception _exception;
    private LogLevel _level;
    private String _message;
    private String _scope;
    private String _tag;
    private long _threadId;
    private Date _timestamp;

    public static LogEvent fromJson(String str) {
        return (LogEvent) JsonSerializer.deserializeObject(str, new IFunction0<LogEvent>() {
            public LogEvent invoke() {
                return new LogEvent();
            }
        }, new IAction3<LogEvent, String, String>() {
            public void invoke(LogEvent logEvent, String str, String str2) {
                if (str.equals(MessageCenterDataManager.MessageTable.COLUMN_NAME_TIMESTAMP)) {
                    logEvent.setTimestamp(Iso8601Timestamp.iso8601ToDateTime(JsonSerializer.deserializeString(str2)));
                } else if (str.equals("tag")) {
                    logEvent.setTag(JsonSerializer.deserializeString(str2));
                } else if (str.equals("scope")) {
                    logEvent.setScope(JsonSerializer.deserializeString(str2));
                } else if (str.equals("level")) {
                    logEvent.setLevel(LogEvent.stringToLogLevel(JsonSerializer.deserializeString(str2)));
                } else if (str.equals("message")) {
                    logEvent.setMessage(JsonSerializer.deserializeString(str2));
                } else if (str.equals(CustomerSession.EXTRA_EXCEPTION)) {
                    logEvent.setException(new Exception(JsonSerializer.deserializeString(str2)));
                } else if (str.equals("threadId")) {
                    logEvent.setThreadId(JsonSerializer.deserializeLong(str2).getValue());
                }
            }
        });
    }

    public Exception getException() {
        return this._exception;
    }

    public LogLevel getLevel() {
        return this._level;
    }

    public LogLevel getLogLevel() {
        return getLevel();
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

    public Date getTimestamp() {
        return this._timestamp;
    }

    private LogEvent() {
        this._timestamp = new Date();
    }

    public LogEvent(Date date, String str, String str2, LogLevel logLevel, String str3, Exception exc, long j) {
        this._timestamp = new Date();
        setTimestamp(date);
        setTag(str);
        setScope(str2);
        setLevel(logLevel);
        setMessage(str3);
        setException(exc);
        setThreadId(j);
    }

    /* access modifiers changed from: private */
    public static String logLevelToString(LogLevel logLevel) {
        if (logLevel == LogLevel.Verbose) {
            return "verbose";
        }
        if (logLevel == LogLevel.Debug) {
            return BuildConfig.BUILD_TYPE;
        }
        if (logLevel == LogLevel.Info) {
            return "info";
        }
        if (logLevel == LogLevel.Warn) {
            return "warn";
        }
        if (logLevel == LogLevel.Error) {
            return "error";
        }
        return logLevel == LogLevel.Fatal ? "fatal" : "none";
    }

    public void setException(Exception exc) {
        this._exception = exc;
    }

    public void setLevel(LogLevel logLevel) {
        this._level = logLevel;
    }

    private void setLogLevel(LogLevel logLevel) {
        setLevel(logLevel);
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

    public void setTimestamp(Date date) {
        this._timestamp = date;
    }

    /* access modifiers changed from: private */
    public static LogLevel stringToLogLevel(String str) {
        if (str.equals("verbose")) {
            return LogLevel.Verbose;
        }
        if (str.equals(BuildConfig.BUILD_TYPE)) {
            return LogLevel.Debug;
        }
        if (str.equals("info")) {
            return LogLevel.Info;
        }
        if (str.equals("warn")) {
            return LogLevel.Warn;
        }
        if (str.equals("error")) {
            return LogLevel.Error;
        }
        if (str.equals("fatal")) {
            return LogLevel.Fatal;
        }
        return LogLevel.None;
    }

    public static String toJson(LogEvent logEvent) {
        return JsonSerializer.serializeObject(logEvent, new IAction2<LogEvent, HashMap<String, String>>(logEvent) {
            final /* synthetic */ LogEvent val$logEvent;

            {
                this.val$logEvent = r1;
            }

            public void invoke(LogEvent logEvent, HashMap<String, String> hashMap) {
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), MessageCenterDataManager.MessageTable.COLUMN_NAME_TIMESTAMP, JsonSerializer.serializeString(Iso8601Timestamp.dateTimeToIso8601(this.val$logEvent.getTimestamp())));
                if (this.val$logEvent.getTag() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "tag", JsonSerializer.serializeString(this.val$logEvent.getTag()));
                }
                if (this.val$logEvent.getScope() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "scope", JsonSerializer.serializeString(this.val$logEvent.getScope()));
                }
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "level", JsonSerializer.serializeString(LogEvent.logLevelToString(this.val$logEvent.getLevel())));
                if (this.val$logEvent.getMessage() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "message", JsonSerializer.serializeString(this.val$logEvent.getMessage()));
                }
                if (this.val$logEvent.getException() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), CustomerSession.EXTRA_EXCEPTION, JsonSerializer.serializeString(this.val$logEvent.getException().getMessage()));
                }
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "threadId", JsonSerializer.serializeLong(new NullableLong(this.val$logEvent.getThreadId())));
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public String toString() {
        Object[] objArr = new Object[8];
        objArr[0] = LogProvider.getProduct();
        objArr[1] = "0";
        objArr[2] = LongExtensions.toString(Long.valueOf(getThreadId()));
        objArr[3] = LogProvider.getLogLevelString(getLevel());
        String str = "-";
        objArr[4] = getTag() == null ? str : getTag();
        if (getScope() != null) {
            str = getScope();
        }
        objArr[5] = str;
        objArr[6] = LogProvider.getPrefixTimestamp(getTimestamp());
        objArr[7] = getMessage();
        String format = StringExtensions.format("[{0}][{1}][{2}] {3} [{4}][{5}] {6} {7}", objArr);
        return getException() != null ? StringExtensions.concat(format, StringExtensions.format("\n{0}", (Object) getException().toString())) : format;
    }
}
