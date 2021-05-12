package fm.liveswitch;

import java.util.ArrayList;

class VirtualNatMapping {
    private ArrayList<TransportAddress> __externalAddresses = new ArrayList<>();
    private Object __externalAddressesLock = new Object();
    private TransportAddress _internalAddress;
    private boolean _isForwarding;
    private int _port;
    private int _timeout;
    private IAction1<VirtualNatMapping> _timeoutCallback;
    private TimeoutTimer _timer;

    /* access modifiers changed from: package-private */
    public VirtualNatMapping addExternalPermission(TransportAddress transportAddress, VirtualNatMode virtualNatMode) {
        if (!Global.equals(virtualNatMode, VirtualNatMode.FullCone)) {
            if (Global.equals(virtualNatMode, VirtualNatMode.AddressRestrictedCone)) {
                synchronized (this.__externalAddressesLock) {
                    this.__externalAddresses.add(new TransportAddress(transportAddress.getIPAddress(), 0));
                }
            } else {
                synchronized (this.__externalAddressesLock) {
                    this.__externalAddresses.add(new TransportAddress(transportAddress.getIPAddress(), transportAddress.getPort()));
                }
            }
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public boolean checkExternalPermission(TransportAddress transportAddress, VirtualNatMode virtualNatMode) {
        if (Global.equals(virtualNatMode, VirtualNatMode.FullCone)) {
            return true;
        }
        synchronized (this.__externalAddressesLock) {
            for (int i = 0; i < ArrayListExtensions.getCount(this.__externalAddresses); i++) {
                TransportAddress transportAddress2 = (TransportAddress) ArrayListExtensions.getItem(this.__externalAddresses).get(i);
                if (Global.equals(virtualNatMode, VirtualNatMode.AddressRestrictedCone)) {
                    if (Global.equals(transportAddress2.getIPAddress(), transportAddress.getIPAddress())) {
                        return true;
                    }
                } else if (Global.equals(transportAddress2.getIPAddress(), transportAddress.getIPAddress()) && transportAddress2.getPort() == transportAddress.getPort()) {
                    return true;
                }
            }
            return false;
        }
    }

    public TransportAddress[] getExternalAddresses() {
        TransportAddress[] transportAddressArr;
        synchronized (this.__externalAddressesLock) {
            transportAddressArr = (TransportAddress[]) this.__externalAddresses.toArray(new TransportAddress[0]);
        }
        return transportAddressArr;
    }

    public TransportAddress getInternalAddress() {
        return this._internalAddress;
    }

    public boolean getIsForwarding() {
        return this._isForwarding;
    }

    public int getPort() {
        return this._port;
    }

    /* access modifiers changed from: package-private */
    public boolean keepAlive() {
        if (this._timeout <= 0) {
            return true;
        }
        if (!this._timer.stop()) {
            return false;
        }
        TimeoutTimer timeoutTimer = new TimeoutTimer(new IActionDelegate1<Object>() {
            public String getId() {
                return "fm.liveswitch.VirtualNatMapping.timerCallback";
            }

            public void invoke(Object obj) {
                VirtualNatMapping.this.timerCallback(obj);
            }
        }, (Object) null);
        this._timer = timeoutTimer;
        try {
            timeoutTimer.start(this._timeout);
        } catch (Exception e) {
            Log.error("Could not start virtual NAT mapping keep-alive timer.", e);
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean removeExternalPermission(TransportAddress transportAddress, VirtualNatMode virtualNatMode) {
        if (!Global.equals(virtualNatMode, VirtualNatMode.FullCone)) {
            synchronized (this.__externalAddressesLock) {
                for (int i = 0; i < ArrayListExtensions.getCount(this.__externalAddresses); i++) {
                    TransportAddress transportAddress2 = (TransportAddress) ArrayListExtensions.getItem(this.__externalAddresses).get(i);
                    if (Global.equals(virtualNatMode, VirtualNatMode.AddressRestrictedCone)) {
                        if (Global.equals(transportAddress2.getIPAddress(), transportAddress.getIPAddress())) {
                            ArrayListExtensions.removeAt(this.__externalAddresses, i);
                            return true;
                        }
                    } else if (Global.equals(transportAddress2.getIPAddress(), transportAddress.getIPAddress()) && transportAddress2.getPort() == transportAddress.getPort()) {
                        ArrayListExtensions.removeAt(this.__externalAddresses, i);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void setInternalAddress(TransportAddress transportAddress) {
        this._internalAddress = transportAddress;
    }

    public void setIsForwarding(boolean z) {
        this._isForwarding = z;
    }

    public void setPort(int i) {
        this._port = i;
    }

    /* access modifiers changed from: private */
    public void timerCallback(Object obj) {
        IAction1<VirtualNatMapping> iAction1 = this._timeoutCallback;
        if (iAction1 != null) {
            iAction1.invoke(this);
        }
    }

    public String toString() {
        String format = StringExtensions.format("{0} -> {1}", getInternalAddress().toString(), IntegerExtensions.toString(Integer.valueOf(getPort())));
        ArrayList arrayList = new ArrayList();
        for (TransportAddress transportAddress : getExternalAddresses()) {
            arrayList.add(transportAddress.toString());
        }
        return ArrayListExtensions.getCount(arrayList) > 0 ? StringExtensions.format("{0} ({1})", format, StringExtensions.join(", ", (String[]) arrayList.toArray(new String[0]))) : format;
    }

    /* access modifiers changed from: package-private */
    public boolean tryReuse(TransportAddress transportAddress, TransportAddress transportAddress2, VirtualNatMode virtualNatMode) {
        if (!getInternalAddress().equals(transportAddress)) {
            return false;
        }
        if (Global.equals(virtualNatMode, VirtualNatMode.Symmetric) && !checkExternalPermission(transportAddress2, virtualNatMode)) {
            return false;
        }
        boolean keepAlive = keepAlive();
        if (keepAlive && !Global.equals(virtualNatMode, VirtualNatMode.Symmetric)) {
            addExternalPermission(transportAddress2, virtualNatMode);
        }
        return keepAlive;
    }

    public VirtualNatMapping(int i, IAction1<VirtualNatMapping> iAction1) {
        this._timeout = i;
        if (i > 0) {
            this._timeoutCallback = iAction1;
            TimeoutTimer timeoutTimer = new TimeoutTimer(new IActionDelegate1<Object>() {
                public String getId() {
                    return "fm.liveswitch.VirtualNatMapping.timerCallback";
                }

                public void invoke(Object obj) {
                    VirtualNatMapping.this.timerCallback(obj);
                }
            }, (Object) null);
            this._timer = timeoutTimer;
            try {
                timeoutTimer.start(this._timeout);
            } catch (Exception e) {
                Log.error("Could not create virtual NAT mapping timer.", e);
            }
        }
    }
}
