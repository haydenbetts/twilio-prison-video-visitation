package fm.liveswitch.diagnostics;

import fm.liveswitch.ClassExtensions;
import fm.liveswitch.IActionDelegate0;
import fm.liveswitch.ILog;
import fm.liveswitch.Log;
import fm.liveswitch.LogLevel;
import fm.liveswitch.ManagedTimer;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class Timers {
    private static ILog __log = Log.getLogger(Timers.class);
    private static int __outputInterval;
    private static ManagedTimer __outputTimer;
    private static ArrayList<Timer> __timers = new ArrayList<>();
    private static LogLevel _outputLogLevel;

    public static DurationTimer getDurationTimer(String str) {
        DurationTimer durationTimer;
        synchronized (__timers) {
            durationTimer = new DurationTimer(str);
            __timers.add(durationTimer);
        }
        return durationTimer;
    }

    public static DurationTimer getDurationTimer(Class cls) {
        return getDurationTimer(ClassExtensions.getFullName(cls));
    }

    public static boolean getEnabled() {
        return getOutputInterval() > 0 && __log.isLogEnabled(getOutputLogLevel());
    }

    public static int getOutputInterval() {
        return __outputInterval;
    }

    public static LogLevel getOutputLogLevel() {
        return _outputLogLevel;
    }

    public static RateTimer getRateTimer(String str) {
        RateTimer rateTimer;
        synchronized (__timers) {
            rateTimer = new RateTimer(str);
            __timers.add(rateTimer);
        }
        return rateTimer;
    }

    public static RateTimer getRateTimer(Class cls) {
        return getRateTimer(ClassExtensions.getFullName(cls));
    }

    /* access modifiers changed from: private */
    public static void logTick() {
        if (getEnabled()) {
            Iterator<Timer> it = __timers.iterator();
            while (it.hasNext()) {
                Timer next = it.next();
                if (next.getHasData()) {
                    LogLevel outputLogLevel = getOutputLogLevel();
                    if (outputLogLevel == LogLevel.Verbose) {
                        __log.verbose(next.getStats());
                    } else if (outputLogLevel == LogLevel.Debug) {
                        __log.debug(next.getStats());
                    } else if (outputLogLevel == LogLevel.Info) {
                        __log.info(next.getStats());
                    } else if (outputLogLevel == LogLevel.Warn) {
                        __log.warn(next.getStats());
                    } else if (outputLogLevel == LogLevel.Error) {
                        __log.error(next.getStats());
                    } else if (outputLogLevel == LogLevel.Fatal) {
                        __log.fatal(next.getStats());
                    }
                }
            }
        }
    }

    static void removeTimer(Timer timer) {
        synchronized (__timers) {
            __timers.remove(timer);
        }
    }

    public static void setOutputInterval(int i) {
        if (__outputInterval != i) {
            if (i > 0) {
                ManagedTimer managedTimer = __outputTimer;
                if (managedTimer != null) {
                    managedTimer.stop();
                    __outputTimer = null;
                }
                ManagedTimer managedTimer2 = new ManagedTimer(i * 1000, new IActionDelegate0() {
                    public String getId() {
                        return "fm.liveswitch.diagnostics.Timers.logTick";
                    }

                    public void invoke() {
                        Timers.logTick();
                    }
                });
                __outputTimer = managedTimer2;
                managedTimer2.start();
            } else {
                ManagedTimer managedTimer3 = __outputTimer;
                if (managedTimer3 != null) {
                    managedTimer3.stop();
                    __outputTimer = null;
                }
            }
            __outputInterval = i;
        }
    }

    public static void setOutputLogLevel(LogLevel logLevel) {
        _outputLogLevel = logLevel;
    }

    static {
        setOutputLogLevel(LogLevel.Debug);
        setOutputInterval(0);
    }
}
