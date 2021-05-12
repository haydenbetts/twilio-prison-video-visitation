package fm.liveswitch;

import java.net.InetAddress;
import java.util.ArrayList;

public class DnsRequest {
    /* access modifiers changed from: private */
    public IAction2<String[], Object> _callback;
    /* access modifiers changed from: private */
    public String _name;
    /* access modifiers changed from: private */
    public Object _state;

    public DnsRequest(String str, IAction2<String[], Object> iAction2, Object obj) {
        this._name = str;
        this._callback = iAction2;
        this._state = obj;
    }

    public String getName() {
        return this._name;
    }

    public IAction2<String[], Object> getCallback() {
        return this._callback;
    }

    public Object getState() {
        return this._state;
    }

    public void resolve(boolean z) {
        new ManagedThread(new IAction1<ManagedThread>() {
            public void invoke(ManagedThread managedThread) {
                ArrayList arrayList = new ArrayList();
                try {
                    for (InetAddress hostAddress : InetAddress.getAllByName(DnsRequest.this._name)) {
                        arrayList.add(hostAddress.getHostAddress());
                    }
                } catch (Exception e) {
                    Log.error(StringExtensions.format("Could not resolve DNS name '{0}'.", (Object) DnsRequest.this._name), e);
                }
                DnsRequest.this._callback.invoke(arrayList.toArray(new String[arrayList.size()]), DnsRequest.this._state);
            }
        }).start();
    }
}
