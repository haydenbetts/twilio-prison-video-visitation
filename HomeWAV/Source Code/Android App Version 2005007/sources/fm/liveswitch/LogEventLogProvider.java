package fm.liveswitch;

import java.util.ArrayList;

public class LogEventLogProvider extends LogProvider {
    private ArrayList<LogEvent> __events;
    private Object __eventsLock;

    public LogEvent[] clear() {
        LogEvent[] logEventArr;
        synchronized (this.__eventsLock) {
            logEventArr = (LogEvent[]) this.__events.toArray(new LogEvent[0]);
            this.__events = new ArrayList<>();
        }
        return logEventArr;
    }

    /* access modifiers changed from: protected */
    public void doLog(LogEvent logEvent) {
        synchronized (this.__eventsLock) {
            this.__events.add(logEvent);
        }
    }

    public LogEvent[] getEvents() {
        LogEvent[] logEventArr;
        synchronized (this.__eventsLock) {
            logEventArr = (LogEvent[]) this.__events.toArray(new LogEvent[0]);
        }
        return logEventArr;
    }

    public LogEventLogProvider() {
        this(LogLevel.Info);
    }

    public LogEventLogProvider(LogLevel logLevel) {
        this.__events = new ArrayList<>();
        this.__eventsLock = new Object();
        super.setLevel(logLevel);
    }
}
