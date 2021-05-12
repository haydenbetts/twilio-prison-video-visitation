package fm.liveswitch;

import java.util.Date;

public abstract class LogProvider {
    private IFunction1<LogEvent, Boolean> _filter;
    private LogLevel _level;
    private int _processId;

    public static String getProduct() {
        return "LiveSwitch";
    }

    /* access modifiers changed from: protected */
    public abstract void doLog(LogEvent logEvent);

    /* access modifiers changed from: protected */
    public String generateLogLine(LogEvent logEvent) {
        Object[] objArr = new Object[8];
        objArr[0] = getProduct();
        objArr[1] = IntegerExtensions.toString(Integer.valueOf(getProcessId()));
        objArr[2] = LongExtensions.toString(Long.valueOf(logEvent.getThreadId()));
        objArr[3] = getLogLevelString(logEvent.getLevel());
        String str = "-";
        objArr[4] = logEvent.getTag() == null ? str : logEvent.getTag();
        if (logEvent.getScope() != null) {
            str = logEvent.getScope();
        }
        objArr[5] = str;
        objArr[6] = getPrefixTimestamp(logEvent.getTimestamp());
        objArr[7] = logEvent.getMessage();
        String format = StringExtensions.format("[{0}][{1}][{2}] {3} [{4}][{5}] {6} {7}", objArr);
        return logEvent.getException() != null ? StringExtensions.concat(format, StringExtensions.format("\n{0}", (Object) logEvent.getException().toString())) : format;
    }

    public IFunction1<LogEvent, Boolean> getFilter() {
        return this._filter;
    }

    public LogLevel getLevel() {
        return this._level;
    }

    public static String getLogLevelString(LogLevel logLevel) {
        if (logLevel == LogLevel.Verbose) {
            return "VERBOSE";
        }
        if (logLevel == LogLevel.Debug) {
            return "DEBUG  ";
        }
        if (logLevel == LogLevel.Info) {
            return "INFO   ";
        }
        if (logLevel == LogLevel.Warn) {
            return "WARN   ";
        }
        if (logLevel == LogLevel.Error) {
            return "ERROR  ";
        }
        return logLevel == LogLevel.Fatal ? "FATAL  " : "NONE   ";
    }

    /* access modifiers changed from: protected */
    public String getPrefix(LogLevel logLevel, boolean z) {
        String logLevelString = getLogLevelString(logLevel);
        return z ? StringExtensions.format("{0} {1}", logLevelString, getPrefixTimestamp(DateExtensions.getUtcNow())) : logLevelString;
    }

    public static String getPrefixTimestamp(Date date) {
        return Iso8601Timestamp.dateTimeToIso8601(date);
    }

    /* access modifiers changed from: protected */
    public int getProcessId() {
        return this._processId;
    }

    public void log(LogEvent logEvent) {
        if (logEvent.getLevel().getAssignedValue() >= getLevel().getAssignedValue()) {
            boolean z = true;
            try {
                if (getFilter() != null) {
                    z = getFilter().invoke(logEvent).booleanValue();
                }
                if (z) {
                    doLog(logEvent);
                }
            } catch (Exception unused) {
            }
        }
    }

    public LogProvider() {
        setLevel(LogLevel.Info);
        setProcessId(Platform.getInstance().getProcessId());
    }

    public void setFilter(IFunction1<LogEvent, Boolean> iFunction1) {
        this._filter = iFunction1;
    }

    public void setLevel(LogLevel logLevel) {
        this._level = logLevel;
    }

    private void setProcessId(int i) {
        this._processId = i;
    }
}
