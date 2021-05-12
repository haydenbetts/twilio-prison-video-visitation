package fm.liveswitch;

public interface ILog {
    void debug(String str);

    void debug(String str, Exception exc);

    void debug(String str, String str2);

    void debug(String str, String str2, Exception exc);

    void error(String str);

    void error(String str, Exception exc);

    void error(String str, String str2);

    void error(String str, String str2, Exception exc);

    void fatal(String str);

    void fatal(String str, Exception exc);

    void fatal(String str, String str2);

    void fatal(String str, String str2, Exception exc);

    void flush();

    boolean getIsDebugEnabled();

    boolean getIsErrorEnabled();

    boolean getIsFatalEnabled();

    boolean getIsInfoEnabled();

    boolean getIsVerboseEnabled();

    boolean getIsWarnEnabled();

    String getTag();

    void info(String str);

    void info(String str, Exception exc);

    void info(String str, String str2);

    void info(String str, String str2, Exception exc);

    boolean isLogEnabled(LogLevel logLevel);

    void log(LogEvent logEvent);

    void log(String str);

    void log(String str, String str2);

    void verbose(String str);

    void verbose(String str, Exception exc);

    void verbose(String str, String str2);

    void verbose(String str, String str2, Exception exc);

    void warn(String str);

    void warn(String str, Exception exc);

    void warn(String str, String str2);

    void warn(String str, String str2, Exception exc);
}
