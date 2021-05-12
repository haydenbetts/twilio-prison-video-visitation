package fm.liveswitch;

import java.util.HashMap;

class SignallingClientSendState {
    private boolean _isStream;
    private HashMap<String, SignallingClientRequest> _requestMapping;
    private SignallingClientRequest[] _requests;

    public boolean getIsStream() {
        return this._isStream;
    }

    public HashMap<String, SignallingClientRequest> getRequestMapping() {
        return this._requestMapping;
    }

    public SignallingClientRequest[] getRequests() {
        return this._requests;
    }

    public void setIsStream(boolean z) {
        this._isStream = z;
    }

    public void setRequestMapping(HashMap<String, SignallingClientRequest> hashMap) {
        this._requestMapping = hashMap;
    }

    public void setRequests(SignallingClientRequest[] signallingClientRequestArr) {
        this._requests = signallingClientRequestArr;
    }
}
