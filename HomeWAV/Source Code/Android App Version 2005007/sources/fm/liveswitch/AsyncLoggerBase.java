package fm.liveswitch;

abstract class AsyncLoggerBase implements ILog {
    private String _tag;

    /* access modifiers changed from: protected */
    public abstract void doQueueLogEvent(LogEvent logEvent);

    public abstract void flush();

    public AsyncLoggerBase(String str) {
        if (str != null) {
            setTag(str);
            return;
        }
        throw new RuntimeException(new Exception("The tag parameter cannot be null."));
    }

    public void debug(String str) {
        doLog(LogLevel.Debug, (String) null, str, (Exception) null);
    }

    public void debug(String str, Exception exc) {
        doLog(LogLevel.Debug, (String) null, str, exc);
    }

    public void debug(String str, String str2) {
        doLog(LogLevel.Debug, str, str2, (Exception) null);
    }

    public void debug(String str, String str2, Exception exc) {
        doLog(LogLevel.Debug, str, str2, exc);
    }

    /* access modifiers changed from: protected */
    public void doLog(LogLevel logLevel, String str, String str2, Exception exc) {
        doQueueLogEvent(new LogEvent(DateExtensions.getUtcNow(), getTag(), str, logLevel, str2, exc, ManagedThread.getCurrentThreadId()));
    }

    public void error(String str) {
        doLog(LogLevel.Error, (String) null, str, (Exception) null);
    }

    public void error(String str, Exception exc) {
        doLog(LogLevel.Error, (String) null, str, exc);
    }

    public void error(String str, String str2) {
        doLog(LogLevel.Error, str, str2, (Exception) null);
    }

    public void error(String str, String str2, Exception exc) {
        doLog(LogLevel.Error, str, str2, exc);
    }

    public void fatal(String str) {
        doLog(LogLevel.Fatal, (String) null, str, (Exception) null);
    }

    public void fatal(String str, Exception exc) {
        doLog(LogLevel.Fatal, (String) null, str, exc);
    }

    public void fatal(String str, String str2) {
        doLog(LogLevel.Fatal, str, str2, (Exception) null);
    }

    public void fatal(String str, String str2, Exception exc) {
        doLog(LogLevel.Fatal, str, str2, exc);
    }

    public boolean getIsDebugEnabled() {
        return isLogEnabled(LogLevel.Debug);
    }

    public boolean getIsErrorEnabled() {
        return isLogEnabled(LogLevel.Error);
    }

    public boolean getIsFatalEnabled() {
        return isLogEnabled(LogLevel.Fatal);
    }

    public boolean getIsInfoEnabled() {
        return isLogEnabled(LogLevel.Info);
    }

    public boolean getIsVerboseEnabled() {
        return isLogEnabled(LogLevel.Verbose);
    }

    public boolean getIsWarnEnabled() {
        return isLogEnabled(LogLevel.Warn);
    }

    public String getTag() {
        return this._tag;
    }

    public void info(String str) {
        doLog(LogLevel.Info, (String) null, str, (Exception) null);
    }

    public void info(String str, Exception exc) {
        doLog(LogLevel.Info, (String) null, str, exc);
    }

    public void info(String str, String str2) {
        doLog(LogLevel.Info, str, str2, (Exception) null);
    }

    public void info(String str, String str2, Exception exc) {
        doLog(LogLevel.Info, str, str2, exc);
    }

    public boolean isLogEnabled(LogLevel logLevel) {
        return LogConfiguration.getTagLogLevel(getTag()).getAssignedValue() <= logLevel.getAssignedValue();
    }

    public void log(LogEvent logEvent) {
        doQueueLogEvent(logEvent);
    }

    public void log(String str) {
        doLog(LogLevel.Info, (String) null, str, (Exception) null);
    }

    public void log(String str, String str2) {
        doLog(LogLevel.Info, str, str2, (Exception) null);
    }

    protected static void processLogEvent(LogEvent logEvent) {
        if (LogConfiguration.getHasProviders() && LogConfiguration.getTagLogLevel(logEvent.getTag()).getAssignedValue() <= logEvent.getLevel().getAssignedValue()) {
            for (LogProvider log : LogConfiguration.getLogProviders()) {
                log.log(logEvent);
            }
        }
    }

    private void setTag(String str) {
        this._tag = str;
    }

    public void verbose(String str) {
        doLog(LogLevel.Verbose, (String) null, str, (Exception) null);
    }

    public void verbose(String str, Exception exc) {
        doLog(LogLevel.Verbose, (String) null, str, exc);
    }

    public void verbose(String str, String str2) {
        doLog(LogLevel.Verbose, str, str2, (Exception) null);
    }

    public void verbose(String str, String str2, Exception exc) {
        doLog(LogLevel.Verbose, str, str2, exc);
    }

    public void warn(String str) {
        doLog(LogLevel.Warn, (String) null, str, (Exception) null);
    }

    public void warn(String str, Exception exc) {
        doLog(LogLevel.Warn, (String) null, str, exc);
    }

    public void warn(String str, String str2) {
        doLog(LogLevel.Warn, str, str2, (Exception) null);
    }

    public void warn(String str, String str2, Exception exc) {
        doLog(LogLevel.Warn, str, str2, exc);
    }
}
