package fm.liveswitch.android;

import android.util.Log;
import fm.liveswitch.LogEvent;
import fm.liveswitch.LogLevel;
import fm.liveswitch.StringExtensions;

public class LogProvider extends fm.liveswitch.LogProvider {
    public LogProvider() {
        this(LogLevel.Warn);
    }

    public LogProvider(LogLevel logLevel) {
        setLevel(logLevel);
    }

    /* access modifiers changed from: protected */
    public void doLog(LogEvent logEvent) {
        for (String format : logEvent.getMessage().length() > 2000 ? logEvent.getMessage().split("\\r\\n") : new String[]{logEvent.getMessage()}) {
            String format2 = StringExtensions.format("{0} {1}", getPrefixTimestamp(logEvent.getTimestamp()), format);
            if (logEvent.getLevel() == LogLevel.Debug) {
                Log.d(logEvent.getTag(), format2, logEvent.getException());
            } else if (logEvent.getLevel() == LogLevel.Info) {
                Log.i(logEvent.getTag(), format2, logEvent.getException());
            } else if (logEvent.getLevel() == LogLevel.Warn) {
                Log.w(logEvent.getTag(), format2, logEvent.getException());
            } else if (logEvent.getLevel() == LogLevel.Error || logEvent.getLevel() == LogLevel.Fatal) {
                Log.e(logEvent.getTag(), format2, logEvent.getException());
            } else {
                Log.v(logEvent.getTag(), format2, logEvent.getException());
            }
        }
    }
}
