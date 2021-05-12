package fm.liveswitch;

public class TextLogProvider extends LogProvider {
    private IAction1<String> __callback;
    private StringBuilder __text;
    private Object __textLock;

    public String clear() {
        String sb;
        synchronized (this.__textLock) {
            sb = this.__text.toString();
            this.__text = new StringBuilder();
        }
        return sb;
    }

    /* access modifiers changed from: protected */
    public void doLog(LogEvent logEvent) {
        writeLine(generateLogLine(logEvent));
    }

    public IAction1<String> getCallback() {
        return this.__callback;
    }

    public String getText() {
        String sb;
        synchronized (this.__textLock) {
            sb = this.__text.toString();
        }
        return sb;
    }

    public void setCallback(IAction1<String> iAction1) {
        synchronized (this.__textLock) {
            String sb = this.__text.toString();
            if (!StringExtensions.isNullOrEmpty(sb)) {
                if (sb.endsWith("\n")) {
                    sb = StringExtensions.substring(sb, 0, StringExtensions.getLength(sb) - 1);
                }
                iAction1.invoke(sb);
            }
            this.__callback = iAction1;
        }
    }

    public TextLogProvider() {
        this(LogLevel.Info);
    }

    public TextLogProvider(LogLevel logLevel) {
        this.__text = new StringBuilder();
        this.__textLock = new Object();
        super.setLevel(logLevel);
    }

    private void writeLine(String str) {
        synchronized (this.__textLock) {
            StringBuilderExtensions.append(this.__text, StringExtensions.concat(str, "\n"));
            IAction1<String> iAction1 = this.__callback;
            if (iAction1 != null) {
                iAction1.invoke(str);
            }
        }
    }
}
