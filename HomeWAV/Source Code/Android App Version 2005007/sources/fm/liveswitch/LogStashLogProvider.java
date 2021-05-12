package fm.liveswitch;

import com.stripe.android.CustomerSession;
import com.urbanairship.MessageCenterDataManager;
import java.util.HashMap;

public class LogStashLogProvider extends LogProvider {
    private static ILog __log = Log.getLogger(LogStashLogProvider.class);
    private ManagedCondition __connectCondition;
    private TcpSocket __socket;
    private String _address;
    private boolean _connectFailed;
    private boolean _isConnected;
    private int _port;

    /* access modifiers changed from: private */
    public void connectFailed(Exception exc, boolean z) {
        synchronized (this.__connectCondition) {
            this.__connectCondition.pulse();
            this._connectFailed = true;
            __log.error("Unable to connect to LogStash.", exc);
        }
    }

    /* access modifiers changed from: private */
    public void connectSuccess() {
        synchronized (this.__connectCondition) {
            this.__connectCondition.pulse();
            this._isConnected = true;
        }
    }

    /* access modifiers changed from: protected */
    public void doLog(LogEvent logEvent) {
        if (!this._connectFailed) {
            if (!this._isConnected) {
                synchronized (this.__connectCondition) {
                    this.__socket.connectAsync(getAddress(), getAddress(), getPort(), 1000, new IActionDelegate0() {
                        public String getId() {
                            return "fm.liveswitch.LogStashLogProvider.connectSuccess";
                        }

                        public void invoke() {
                            LogStashLogProvider.this.connectSuccess();
                        }
                    }, new IActionDelegate2<Exception, Boolean>() {
                        public String getId() {
                            return "fm.liveswitch.LogStashLogProvider.connectFailed";
                        }

                        public void invoke(Exception exc, Boolean bool) {
                            LogStashLogProvider.this.connectFailed(exc, bool.booleanValue());
                        }
                    });
                    this.__connectCondition.halt();
                }
            }
            if (this._isConnected) {
                HashMap hashMap = new HashMap();
                HashMapExtensions.add(hashMap, MessageCenterDataManager.MessageTable.COLUMN_NAME_TIMESTAMP, JsonSerializer.serializeString(LogProvider.getPrefixTimestamp(DateExtensions.toUniversalTime(logEvent.getTimestamp()))));
                HashMapExtensions.add(hashMap, "product", JsonSerializer.serializeString(LogProvider.getProduct()));
                HashMapExtensions.add(hashMap, "level", JsonSerializer.serializeString(LogProvider.getLogLevelString(logEvent.getLevel()).trim()));
                HashMapExtensions.add(hashMap, "message", JsonSerializer.serializeString(logEvent.getMessage()));
                HashMapExtensions.add(hashMap, "scope", JsonSerializer.serializeString(logEvent.getScope()));
                HashMapExtensions.add(hashMap, "tag", JsonSerializer.serializeString(logEvent.getTag()));
                HashMapExtensions.add(hashMap, "processId", JsonSerializer.serializeInteger(new NullableInteger(super.getProcessId())));
                HashMapExtensions.add(hashMap, "threadId", JsonSerializer.serializeLong(new NullableLong(logEvent.getThreadId())));
                HashMapExtensions.add(hashMap, "accountId", License.getCurrent() != null ? JsonSerializer.serializeString(License.getCurrent().getAccountId()) : "null");
                if (logEvent.getException() != null) {
                    HashMapExtensions.add(hashMap, CustomerSession.EXTRA_EXCEPTION, JsonSerializer.serializeString(logEvent.getException().getMessage()));
                }
                this.__socket.send(DataBuffer.wrap(Utf8.encode(JsonSerializer.serializeDictionary(hashMap, new IFunction1<String, String>() {
                    public String invoke(String str) {
                        return str;
                    }
                }))));
                this.__socket.send(DataBuffer.wrap(new byte[]{10}));
            }
        }
    }

    public String getAddress() {
        return this._address;
    }

    public int getPort() {
        return this._port;
    }

    public LogStashLogProvider(String str, int i, boolean z) {
        this(str, i, z, LogLevel.Info);
    }

    public LogStashLogProvider(String str, int i, boolean z, LogLevel logLevel) {
        setAddress(str);
        setPort(i);
        super.setLevel(logLevel);
        this.__socket = new TcpSocket(false, false, z);
        this.__connectCondition = new ManagedCondition();
    }

    private void setAddress(String str) {
        this._address = str;
    }

    private void setPort(int i) {
        this._port = i;
    }
}
