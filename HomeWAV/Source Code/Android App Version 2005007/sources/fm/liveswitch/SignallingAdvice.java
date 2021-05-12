package fm.liveswitch;

import java.util.HashMap;

class SignallingAdvice extends SignallingBaseAdvice {
    private SignallingBaseAdvice _callbackPolling;
    private SignallingBaseAdvice _longPolling;
    private SignallingBaseAdvice _webSocket;

    public static SignallingAdvice fromJson(String str) {
        return (SignallingAdvice) JsonSerializer.deserializeObjectFast(str, new IFunction0<SignallingAdvice>() {
            public SignallingAdvice invoke() {
                return new SignallingAdvice();
            }
        }, new IAction3<SignallingAdvice, String, String>() {
            public void invoke(SignallingAdvice signallingAdvice, String str, String str2) {
                if (str == null) {
                    return;
                }
                if (Global.equals(str, "hosts")) {
                    signallingAdvice.setHosts(JsonSerializer.deserializeStringArray(str2));
                } else if (Global.equals(str, "interval")) {
                    signallingAdvice.setInterval(JsonSerializer.deserializeInteger(str2));
                } else if (Global.equals(str, "reconnect")) {
                    signallingAdvice.setReconnect(SignallingBaseAdvice.deserializeReconnect(str2));
                } else if (Global.equals(str, "websocket")) {
                    signallingAdvice.setWebSocket(SignallingBaseAdvice.fromJson(str2));
                } else if (Global.equals(str, "long-polling")) {
                    signallingAdvice.setLongPolling(SignallingBaseAdvice.fromJson(str2));
                } else if (Global.equals(str, "callback-polling")) {
                    signallingAdvice.setCallbackPolling(SignallingBaseAdvice.fromJson(str2));
                }
            }
        });
    }

    public SignallingBaseAdvice getCallbackPolling() {
        return this._callbackPolling;
    }

    public SignallingBaseAdvice getLongPolling() {
        return this._longPolling;
    }

    public SignallingBaseAdvice getWebSocket() {
        return this._webSocket;
    }

    public void setCallbackPolling(SignallingBaseAdvice signallingBaseAdvice) {
        this._callbackPolling = signallingBaseAdvice;
    }

    public void setLongPolling(SignallingBaseAdvice signallingBaseAdvice) {
        this._longPolling = signallingBaseAdvice;
    }

    public void setWebSocket(SignallingBaseAdvice signallingBaseAdvice) {
        this._webSocket = signallingBaseAdvice;
    }

    public static String toJson(SignallingAdvice signallingAdvice) {
        return JsonSerializer.serializeObjectFast(signallingAdvice, new IAction2<SignallingAdvice, HashMap<String, String>>() {
            public void invoke(SignallingAdvice signallingAdvice, HashMap<String, String> hashMap) {
                if (signallingAdvice.getHosts() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "hosts", JsonSerializer.serializeStringArray(signallingAdvice.getHosts()));
                }
                if (signallingAdvice.getInterval().getHasValue()) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "interval", JsonSerializer.serializeInteger(signallingAdvice.getInterval()));
                }
                if (!Global.equals(signallingAdvice.getReconnect(), SignallingReconnect.NotSet)) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "reconnect", SignallingBaseAdvice.serializeReconnect(signallingAdvice.getReconnect()));
                }
                if (signallingAdvice.getWebSocket() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "websocket", SignallingBaseAdvice.toJson(signallingAdvice.getWebSocket()));
                }
                if (signallingAdvice.getLongPolling() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "long-polling", SignallingBaseAdvice.toJson(signallingAdvice.getLongPolling()));
                }
                if (signallingAdvice.getCallbackPolling() != null) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "callback-polling", SignallingBaseAdvice.toJson(signallingAdvice.getCallbackPolling()));
                }
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }
}
