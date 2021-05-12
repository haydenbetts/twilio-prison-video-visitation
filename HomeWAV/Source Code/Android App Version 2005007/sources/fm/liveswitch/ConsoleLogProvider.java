package fm.liveswitch;

import java.io.PrintStream;

public class ConsoleLogProvider extends LogProvider {
    public ConsoleLogProvider() {
        this(LogLevel.Warn);
    }

    public ConsoleLogProvider(LogLevel logLevel) {
        setLevel(logLevel);
    }

    /* access modifiers changed from: protected */
    public void doLog(LogEvent logEvent) {
        PrintStream printStream = System.out;
        printStream.print(generateLogLine(logEvent) + "\n");
    }
}
