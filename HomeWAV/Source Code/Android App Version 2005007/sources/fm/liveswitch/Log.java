package fm.liveswitch;

public class Log {
    private static ILog __staticLogger = new AsyncLogger("FM");

    public static void addProvider(LogProvider logProvider) {
        registerProvider(logProvider);
    }

    public static void addProvider(LogProvider logProvider, LogLevel logLevel) {
        registerProvider(logProvider, logLevel);
    }

    public static void debug(String str) {
        __staticLogger.debug(str);
    }

    public static void debug(String str, Exception exc) {
        __staticLogger.debug(str, exc);
    }

    public static void error(String str) {
        __staticLogger.error(str);
    }

    public static void error(String str, Exception exc) {
        __staticLogger.error(str, exc);
    }

    public static void fatal(String str) {
        __staticLogger.fatal(str);
    }

    public static void fatal(String str, Exception exc) {
        __staticLogger.fatal(str, exc);
    }

    public static void flush() {
        __staticLogger.flush();
    }

    public static boolean getIsDebugEnabled() {
        return LogConfiguration.getDefaultLogLevel().getAssignedValue() <= LogLevel.Debug.getAssignedValue();
    }

    public static boolean getIsErrorEnabled() {
        return LogConfiguration.getDefaultLogLevel().getAssignedValue() <= LogLevel.Error.getAssignedValue();
    }

    public static boolean getIsFatalEnabled() {
        return LogConfiguration.getDefaultLogLevel().getAssignedValue() <= LogLevel.Fatal.getAssignedValue();
    }

    public static boolean getIsInfoEnabled() {
        return LogConfiguration.getDefaultLogLevel().getAssignedValue() <= LogLevel.Info.getAssignedValue();
    }

    public static boolean getIsVerboseEnabled() {
        return LogConfiguration.getDefaultLogLevel().getAssignedValue() <= LogLevel.Verbose.getAssignedValue();
    }

    public static boolean getIsWarnEnabled() {
        return LogConfiguration.getDefaultLogLevel().getAssignedValue() <= LogLevel.Warn.getAssignedValue();
    }

    public static ILog getLogger(String str) {
        return new AsyncLogger(str);
    }

    public static ILog getLogger(String str, LogLevel logLevel) {
        LogConfiguration.setTagLogLevel(str, logLevel);
        return getLogger(str);
    }

    public static ILog getLogger(Class cls) {
        return getLogger(ClassExtensions.getFullName(cls));
    }

    public static ILog getLogger(Class cls, LogLevel logLevel) {
        return getLogger(ClassExtensions.getFullName(cls), logLevel);
    }

    public static LogLevel getLogLevel() {
        return LogConfiguration.getDefaultLogLevel();
    }

    public static LogProvider getProvider() {
        LogProvider[] logProviders = LogConfiguration.getLogProviders();
        if (ArrayExtensions.getLength((Object[]) logProviders) > 0) {
            return logProviders[0];
        }
        return null;
    }

    public static LogProvider[] getProviders() {
        return LogConfiguration.getLogProviders();
    }

    public static void info(String str) {
        __staticLogger.info(str);
    }

    public static void info(String str, Exception exc) {
        __staticLogger.info(str, exc);
    }

    public static void registerProvider(LogProvider logProvider) {
        LogConfiguration.addLogProvider(logProvider);
        if (logProvider.getLevel().getAssignedValue() < LogConfiguration.getDefaultLogLevel().getAssignedValue()) {
            LogConfiguration.setDefaultLogLevel(logProvider.getLevel());
        }
    }

    public static void registerProvider(LogProvider logProvider, LogLevel logLevel) {
        logProvider.setLevel(logLevel);
        registerProvider(logProvider);
    }

    public static boolean removeProvider(LogProvider logProvider) {
        return unregisterProvider(logProvider);
    }

    public static void removeProviders() {
        unregisterProviders();
    }

    public static void setLogLevel(LogLevel logLevel) {
        LogConfiguration.setDefaultLogLevel(logLevel);
    }

    public static void setProvider(LogProvider logProvider) {
        LogConfiguration.clearLogProviders();
        registerProvider(logProvider);
    }

    public static void setTagOverride(String str, LogLevel logLevel) {
        LogConfiguration.setTagLogLevel(str, logLevel);
    }

    public static boolean unregisterProvider(LogProvider logProvider) {
        return LogConfiguration.removeLogProvider(logProvider);
    }

    public static void unregisterProviders() {
        LogConfiguration.clearLogProviders();
    }

    public static void verbose(String str) {
        __staticLogger.verbose(str);
    }

    public static void verbose(String str, Exception exc) {
        __staticLogger.verbose(str, exc);
    }

    public static void warn(String str) {
        __staticLogger.warn(str);
    }

    public static void warn(String str, Exception exc) {
        __staticLogger.warn(str, exc);
    }

    public static void writeLine(String str) {
        __staticLogger.log(str);
    }
}
