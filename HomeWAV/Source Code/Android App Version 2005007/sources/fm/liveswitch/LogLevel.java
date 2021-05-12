package fm.liveswitch;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum LogLevel {
    Verbose(1),
    Debug(2),
    Info(3),
    Warn(4),
    Error(5),
    Fatal(6),
    None(7);
    
    private static final Map<Integer, LogLevel> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(LogLevel.class).iterator();
        while (it.hasNext()) {
            LogLevel logLevel = (LogLevel) it.next();
            lookup.put(Integer.valueOf(logLevel.getAssignedValue()), logLevel);
        }
    }

    private LogLevel(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static LogLevel getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
