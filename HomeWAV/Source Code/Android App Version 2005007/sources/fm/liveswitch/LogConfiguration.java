package fm.liveswitch;

import java.util.ArrayList;
import java.util.HashMap;

class LogConfiguration {
    private static Object __lock = new Object();
    private static LogProvider[] __logProviders = new LogProvider[0];
    private static int __providerCount;
    private static HashMap<String, LogLevel> __tagOverrides = new HashMap<>();
    private static Object __tagOverridesLock = new Object();
    private static LogLevel _defaultLogLevel;

    public static void addLogProvider(LogProvider logProvider) {
        synchronized (__lock) {
            ArrayList createArray = ArrayListExtensions.createArray((T[]) __logProviders);
            createArray.add(logProvider);
            __providerCount++;
            __logProviders = (LogProvider[]) createArray.toArray(new LogProvider[0]);
        }
    }

    public static void clearLogProviders() {
        synchronized (__lock) {
            __logProviders = new LogProvider[0];
            __providerCount = 0;
        }
    }

    public static LogLevel getDefaultLogLevel() {
        return _defaultLogLevel;
    }

    public static boolean getHasProviders() {
        return __providerCount > 0;
    }

    public static LogProvider[] getLogProviders() {
        return __logProviders;
    }

    public static LogLevel getTagLogLevel(String str) {
        if (str != null) {
            synchronized (__tagOverridesLock) {
                if (__tagOverrides.containsKey(str)) {
                    LogLevel logLevel = HashMapExtensions.getItem(__tagOverrides).get(str);
                    return logLevel;
                }
            }
        }
        return getDefaultLogLevel();
    }

    static {
        setDefaultLogLevel(LogLevel.Info);
    }

    public static boolean removeLogProvider(LogProvider logProvider) {
        synchronized (__lock) {
            ArrayList createArray = ArrayListExtensions.createArray((T[]) __logProviders);
            if (!createArray.remove(logProvider)) {
                return false;
            }
            __providerCount--;
            __logProviders = (LogProvider[]) createArray.toArray(new LogProvider[0]);
            return true;
        }
    }

    public static void setDefaultLogLevel(LogLevel logLevel) {
        _defaultLogLevel = logLevel;
    }

    public static void setTagLogLevel(String str, LogLevel logLevel) {
        if (str != null) {
            synchronized (__tagOverridesLock) {
                HashMapExtensions.set(HashMapExtensions.getItem(__tagOverrides), str, logLevel);
            }
        }
    }
}
