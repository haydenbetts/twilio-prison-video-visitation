package fm.liveswitch;

import java.util.ArrayList;
import java.util.Date;

abstract class TurnAllocation {
    private ArrayList<TurnChannelBinding> __channelBindingsList = new ArrayList<>();
    private Object __channelBindingsLock = new Object();
    private TurnChannelBinding[] __channels = new TurnChannelBinding[0];
    private TimeoutTimer __expirationTimer;
    private long __lastLifetime;
    private IAction1<TransportAddress> __onExpires;
    private TurnPermission[] __permissions = new TurnPermission[0];
    private ArrayList<TurnPermission> __permissionsList = new ArrayList<>();
    private Object __permissionsLock = new Object();
    private TransportAddress _clientAddress;
    private Date _expires = new Date();
    private String _realm;
    private DataBuffer _reservation;
    private DataBuffer _transactionId;
    private String _username;

    public abstract String getLocalIPAddress();

    public abstract int getLocalPort();

    public abstract String getPublicIPAddress();

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0065, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x011a, code lost:
        if (fm.liveswitch.Log.getIsDebugEnabled() == false) goto L_0x0135;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x011c, code lost:
        fm.liveswitch.Log.debug(fm.liveswitch.StringExtensions.format("Adding {0}:{1} allocation channel binding for {2}.", r10.getIPAddress(), fm.liveswitch.IntegerExtensions.toString(java.lang.Integer.valueOf(r11)), r12.toString()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0135, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public fm.liveswitch.stun.Error addChannelBinding(fm.liveswitch.TransportAddress r10, int r11, fm.liveswitch.TransportAddress r12) {
        /*
            r9 = this;
            java.lang.Object r0 = r9.__channelBindingsLock
            monitor-enter(r0)
            r1 = 0
            r2 = 0
        L_0x0005:
            java.util.ArrayList<fm.liveswitch.TurnChannelBinding> r3 = r9.__channelBindingsList     // Catch:{ all -> 0x0136 }
            int r3 = fm.liveswitch.ArrayListExtensions.getCount(r3)     // Catch:{ all -> 0x0136 }
            r4 = 0
            if (r2 >= r3) goto L_0x00f8
            java.util.ArrayList<fm.liveswitch.TurnChannelBinding> r3 = r9.__channelBindingsList     // Catch:{ all -> 0x0136 }
            java.util.ArrayList r3 = fm.liveswitch.ArrayListExtensions.getItem(r3)     // Catch:{ all -> 0x0136 }
            java.lang.Object r3 = r3.get(r2)     // Catch:{ all -> 0x0136 }
            fm.liveswitch.TurnChannelBinding r3 = (fm.liveswitch.TurnChannelBinding) r3     // Catch:{ all -> 0x0136 }
            boolean r5 = r3.getIsExpired()     // Catch:{ all -> 0x0136 }
            r6 = 1
            if (r5 == 0) goto L_0x002a
            java.util.ArrayList<fm.liveswitch.TurnChannelBinding> r3 = r9.__channelBindingsList     // Catch:{ all -> 0x0136 }
            fm.liveswitch.ArrayListExtensions.removeAt(r3, r2)     // Catch:{ all -> 0x0136 }
            int r2 = r2 + -1
            goto L_0x00f5
        L_0x002a:
            int r5 = r3.getChannelNumber()     // Catch:{ all -> 0x0136 }
            if (r5 != r11) goto L_0x0066
            fm.liveswitch.TransportAddress r5 = r3.getAddress()     // Catch:{ all -> 0x0136 }
            boolean r5 = r5.equals(r10)     // Catch:{ all -> 0x0136 }
            if (r5 == 0) goto L_0x0066
            r3.extendTimeToExpiry()     // Catch:{ all -> 0x0136 }
            boolean r10 = fm.liveswitch.Log.getIsDebugEnabled()     // Catch:{ all -> 0x0136 }
            if (r10 == 0) goto L_0x0064
            java.lang.String r10 = "Extending {0}:{1} allocation channel binding for {2}."
            fm.liveswitch.TransportAddress r11 = r3.getAddress()     // Catch:{ all -> 0x0136 }
            java.lang.String r11 = r11.getIPAddress()     // Catch:{ all -> 0x0136 }
            int r1 = r3.getChannelNumber()     // Catch:{ all -> 0x0136 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x0136 }
            java.lang.String r1 = fm.liveswitch.IntegerExtensions.toString(r1)     // Catch:{ all -> 0x0136 }
            java.lang.String r12 = r12.toString()     // Catch:{ all -> 0x0136 }
            java.lang.String r10 = fm.liveswitch.StringExtensions.format(r10, r11, r1, r12)     // Catch:{ all -> 0x0136 }
            fm.liveswitch.Log.debug(r10)     // Catch:{ all -> 0x0136 }
        L_0x0064:
            monitor-exit(r0)     // Catch:{ all -> 0x0136 }
            return r4
        L_0x0066:
            fm.liveswitch.TransportAddress r4 = r3.getAddress()     // Catch:{ all -> 0x0136 }
            boolean r4 = r4.equals(r10)     // Catch:{ all -> 0x0136 }
            r5 = 3
            r7 = 2
            r8 = 4
            if (r4 == 0) goto L_0x00b3
            java.lang.String r10 = "Could not extend {0}:{1} allocation channel binding for {2}. Channel number mismatch ({3})."
            java.lang.Object[] r2 = new java.lang.Object[r8]     // Catch:{ all -> 0x0136 }
            fm.liveswitch.TransportAddress r4 = r3.getAddress()     // Catch:{ all -> 0x0136 }
            java.lang.String r4 = r4.getIPAddress()     // Catch:{ all -> 0x0136 }
            r2[r1] = r4     // Catch:{ all -> 0x0136 }
            int r1 = r3.getChannelNumber()     // Catch:{ all -> 0x0136 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x0136 }
            java.lang.String r1 = fm.liveswitch.IntegerExtensions.toString(r1)     // Catch:{ all -> 0x0136 }
            r2[r6] = r1     // Catch:{ all -> 0x0136 }
            java.lang.String r12 = r12.toString()     // Catch:{ all -> 0x0136 }
            r2[r7] = r12     // Catch:{ all -> 0x0136 }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)     // Catch:{ all -> 0x0136 }
            java.lang.String r11 = fm.liveswitch.IntegerExtensions.toString(r11)     // Catch:{ all -> 0x0136 }
            r2[r5] = r11     // Catch:{ all -> 0x0136 }
            java.lang.String r10 = fm.liveswitch.StringExtensions.format((java.lang.String) r10, (java.lang.Object[]) r2)     // Catch:{ all -> 0x0136 }
            boolean r11 = fm.liveswitch.Log.getIsErrorEnabled()     // Catch:{ all -> 0x0136 }
            if (r11 == 0) goto L_0x00ac
            fm.liveswitch.Log.error(r10)     // Catch:{ all -> 0x0136 }
        L_0x00ac:
            fm.liveswitch.stun.BadRequestError r11 = new fm.liveswitch.stun.BadRequestError     // Catch:{ all -> 0x0136 }
            r11.<init>(r10)     // Catch:{ all -> 0x0136 }
            monitor-exit(r0)     // Catch:{ all -> 0x0136 }
            return r11
        L_0x00b3:
            int r4 = r3.getChannelNumber()     // Catch:{ all -> 0x0136 }
            if (r4 != r11) goto L_0x00f5
            java.lang.String r11 = "Could not extend {0}:{1} allocation channel binding for {2}. Address mismatch ({3})."
            java.lang.Object[] r2 = new java.lang.Object[r8]     // Catch:{ all -> 0x0136 }
            fm.liveswitch.TransportAddress r4 = r3.getAddress()     // Catch:{ all -> 0x0136 }
            java.lang.String r4 = r4.getIPAddress()     // Catch:{ all -> 0x0136 }
            r2[r1] = r4     // Catch:{ all -> 0x0136 }
            int r1 = r3.getChannelNumber()     // Catch:{ all -> 0x0136 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x0136 }
            java.lang.String r1 = fm.liveswitch.IntegerExtensions.toString(r1)     // Catch:{ all -> 0x0136 }
            r2[r6] = r1     // Catch:{ all -> 0x0136 }
            java.lang.String r12 = r12.toString()     // Catch:{ all -> 0x0136 }
            r2[r7] = r12     // Catch:{ all -> 0x0136 }
            java.lang.String r10 = r10.getIPAddress()     // Catch:{ all -> 0x0136 }
            r2[r5] = r10     // Catch:{ all -> 0x0136 }
            java.lang.String r10 = fm.liveswitch.StringExtensions.format((java.lang.String) r11, (java.lang.Object[]) r2)     // Catch:{ all -> 0x0136 }
            boolean r11 = fm.liveswitch.Log.getIsErrorEnabled()     // Catch:{ all -> 0x0136 }
            if (r11 == 0) goto L_0x00ee
            fm.liveswitch.Log.error(r10)     // Catch:{ all -> 0x0136 }
        L_0x00ee:
            fm.liveswitch.stun.BadRequestError r11 = new fm.liveswitch.stun.BadRequestError     // Catch:{ all -> 0x0136 }
            r11.<init>(r10)     // Catch:{ all -> 0x0136 }
            monitor-exit(r0)     // Catch:{ all -> 0x0136 }
            return r11
        L_0x00f5:
            int r2 = r2 + r6
            goto L_0x0005
        L_0x00f8:
            java.util.ArrayList<fm.liveswitch.TurnChannelBinding> r2 = r9.__channelBindingsList     // Catch:{ all -> 0x0136 }
            fm.liveswitch.TurnChannelBinding r3 = new fm.liveswitch.TurnChannelBinding     // Catch:{ all -> 0x0136 }
            r3.<init>(r10, r11)     // Catch:{ all -> 0x0136 }
            r2.add(r3)     // Catch:{ all -> 0x0136 }
            java.util.ArrayList<fm.liveswitch.TurnChannelBinding> r2 = r9.__channelBindingsList     // Catch:{ all -> 0x0136 }
            fm.liveswitch.TurnChannelBinding[] r1 = new fm.liveswitch.TurnChannelBinding[r1]     // Catch:{ all -> 0x0136 }
            java.lang.Object[] r1 = r2.toArray(r1)     // Catch:{ all -> 0x0136 }
            fm.liveswitch.TurnChannelBinding[] r1 = (fm.liveswitch.TurnChannelBinding[]) r1     // Catch:{ all -> 0x0136 }
            r9.__channels = r1     // Catch:{ all -> 0x0136 }
            java.lang.String r1 = r10.getIPAddress()     // Catch:{ all -> 0x0136 }
            r9.addPermission(r1)     // Catch:{ all -> 0x0136 }
            monitor-exit(r0)     // Catch:{ all -> 0x0136 }
            boolean r0 = fm.liveswitch.Log.getIsDebugEnabled()
            if (r0 == 0) goto L_0x0135
            java.lang.String r0 = "Adding {0}:{1} allocation channel binding for {2}."
            java.lang.String r10 = r10.getIPAddress()
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)
            java.lang.String r11 = fm.liveswitch.IntegerExtensions.toString(r11)
            java.lang.String r12 = r12.toString()
            java.lang.String r10 = fm.liveswitch.StringExtensions.format(r0, r10, r11, r12)
            fm.liveswitch.Log.debug(r10)
        L_0x0135:
            return r4
        L_0x0136:
            r10 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0136 }
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.TurnAllocation.addChannelBinding(fm.liveswitch.TransportAddress, int, fm.liveswitch.TransportAddress):fm.liveswitch.stun.Error");
    }

    public boolean addPermission(String str) {
        synchronized (this.__permissionsLock) {
            int i = 0;
            while (i < ArrayListExtensions.getCount(this.__permissionsList)) {
                TurnPermission turnPermission = (TurnPermission) ArrayListExtensions.getItem(this.__permissionsList).get(i);
                if (turnPermission.getIsExpired()) {
                    ArrayListExtensions.removeAt(this.__permissionsList, i);
                    i--;
                } else if (Global.equals(turnPermission.getAddress(), str)) {
                    turnPermission.extendTimeToExpiry();
                    return false;
                }
                i++;
            }
            this.__permissionsList.add(new TurnPermission(str));
            this.__permissions = (TurnPermission[]) this.__permissionsList.toArray(new TurnPermission[0]);
            return true;
        }
    }

    public void close() {
        TimeoutTimer timeoutTimer = this.__expirationTimer;
        if (timeoutTimer != null) {
            timeoutTimer.stop();
            this.__expirationTimer = null;
        }
    }

    /* access modifiers changed from: private */
    public void expiredCallback(Object obj) {
        IAction1<TransportAddress> iAction1 = this.__onExpires;
        if (iAction1 != null) {
            iAction1.invoke(getClientAddress());
        }
    }

    public TurnChannelBinding[] getChannelBindings() {
        return this.__channels;
    }

    public TransportAddress getClientAddress() {
        return this._clientAddress;
    }

    public boolean getIsExpired() {
        return DateExtensions.getTicks(DateExtensions.getUtcNow()) > DateExtensions.getTicks(this._expires);
    }

    public long getLastLifetime() {
        return this.__lastLifetime;
    }

    public TurnPermission[] getPermissions() {
        return this.__permissions;
    }

    public String getRealm() {
        return this._realm;
    }

    public DataBuffer getReservation() {
        return this._reservation;
    }

    public DataBuffer getTransactionId() {
        return this._transactionId;
    }

    public String getUsername() {
        return this._username;
    }

    public int hasChannelBindingAddress(TransportAddress transportAddress) {
        for (TurnChannelBinding turnChannelBinding : this.__channels) {
            if (turnChannelBinding.getAddress().equals(transportAddress) && !turnChannelBinding.getIsExpired()) {
                return turnChannelBinding.getChannelNumber();
            }
        }
        return 0;
    }

    public TransportAddress hasChannelBindingNumber(int i) {
        for (TurnChannelBinding turnChannelBinding : this.__channels) {
            if (turnChannelBinding.getChannelNumber() == i && !turnChannelBinding.getIsExpired()) {
                return turnChannelBinding.getAddress();
            }
        }
        return null;
    }

    public boolean hasPermission(String str) {
        for (TurnPermission turnPermission : this.__permissions) {
            if (Global.equals(turnPermission.getAddress(), str) && !turnPermission.getIsExpired()) {
                return true;
            }
        }
        return false;
    }

    public boolean refresh(long j) {
        return updateTimeToExpiry(j);
    }

    private void setClientAddress(TransportAddress transportAddress) {
        this._clientAddress = transportAddress;
    }

    private void setRealm(String str) {
        this._realm = str;
    }

    private void setReservation(DataBuffer dataBuffer) {
        this._reservation = dataBuffer;
    }

    private void setTransactionId(DataBuffer dataBuffer) {
        this._transactionId = dataBuffer;
    }

    private void setUsername(String str) {
        this._username = str;
    }

    public TurnAllocation(DataBuffer dataBuffer, DataBuffer dataBuffer2, TransportAddress transportAddress, String str, String str2, long j, IAction1<TransportAddress> iAction1) {
        if (dataBuffer == null) {
            throw new RuntimeException(new Exception("transactionId cannot be null"));
        } else if (transportAddress == null) {
            throw new RuntimeException(new Exception("clientAddress cannot be null"));
        } else if (str == null) {
            throw new RuntimeException(new Exception("username cannot be null"));
        } else if (str2 != null) {
            setTransactionId(dataBuffer != null ? DataBuffer.wrap(dataBuffer.toArray(), dataBuffer.getLittleEndian()) : dataBuffer);
            setReservation(dataBuffer2 != null ? DataBuffer.wrap(dataBuffer2.toArray(), dataBuffer2.getLittleEndian()) : dataBuffer2);
            setClientAddress(transportAddress);
            setUsername(str);
            setRealm(str2);
            this.__onExpires = iAction1;
            updateTimeToExpiry(j);
        } else {
            throw new RuntimeException(new Exception("realm cannot be null"));
        }
    }

    private boolean updateTimeToExpiry(long j) {
        TimeoutTimer timeoutTimer = this.__expirationTimer;
        if (timeoutTimer != null && !timeoutTimer.stop()) {
            return false;
        }
        this.__lastLifetime = j;
        this._expires = DateExtensions.addSeconds(DateExtensions.getUtcNow(), (double) j);
        TimeoutTimer timeoutTimer2 = new TimeoutTimer(new IActionDelegate1<Object>() {
            public String getId() {
                return "fm.liveswitch.TurnAllocation.expiredCallback";
            }

            public void invoke(Object obj) {
                TurnAllocation.this.expiredCallback(obj);
            }
        }, (Object) null);
        this.__expirationTimer = timeoutTimer2;
        try {
            timeoutTimer2.start((int) (j * 1000));
            return true;
        } catch (Exception e) {
            Log.error("Could not start TURN allocation expiration timer.", e);
            return true;
        }
    }
}
