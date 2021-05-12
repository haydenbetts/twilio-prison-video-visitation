package fm.liveswitch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

class ReliableTransport {
    private HashMap<String, ReliableChannel> __channelsByInnerStreamId;
    private HashMap<String, ReliableChannel> __channelsByLabel;
    private boolean __isDtlsServer;
    private boolean __isOfferer;
    private boolean __legacyMatching;
    private Object __lock;
    /* access modifiers changed from: private */
    public List<IAction1<ReliableChannel>> __onDataChannel = new ArrayList();
    /* access modifiers changed from: private */
    public List<IAction1<ReliableTransport>> __onStateChange = new ArrayList();
    private ReliableTransportState __state;
    private Error _error;
    private String _id;
    private SctpTransport _innerTransport;
    private IAction1<ReliableChannel> _onDataChannel = null;
    private IAction1<ReliableTransport> _onStateChange = null;
    private ReliableSctpProtocol _protocol;

    public void addOnDataChannel(IAction1<ReliableChannel> iAction1) {
        if (iAction1 != null) {
            if (this._onDataChannel == null) {
                this._onDataChannel = new IAction1<ReliableChannel>() {
                    public void invoke(ReliableChannel reliableChannel) {
                        Iterator it = new ArrayList(ReliableTransport.this.__onDataChannel).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(reliableChannel);
                        }
                    }
                };
            }
            this.__onDataChannel.add(iAction1);
        }
    }

    public void addOnStateChange(IAction1<ReliableTransport> iAction1) {
        if (iAction1 != null) {
            if (this._onStateChange == null) {
                this._onStateChange = new IAction1<ReliableTransport>() {
                    public void invoke(ReliableTransport reliableTransport) {
                        Iterator it = new ArrayList(ReliableTransport.this.__onStateChange).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(reliableTransport);
                        }
                    }
                };
            }
            this.__onStateChange.add(iAction1);
        }
    }

    private boolean get_LegacyMatching() {
        return this.__legacyMatching;
    }

    public Error getError() {
        return this._error;
    }

    public String getId() {
        return this._id;
    }

    public SctpTransport getInnerTransport() {
        return this._innerTransport;
    }

    public ReliableSctpProtocol getProtocol() {
        return this._protocol;
    }

    public ReliableTransportState getState() {
        return this.__state;
    }

    private void openChannel(ReliableChannel reliableChannel) {
        reliableChannel.open();
    }

    /* access modifiers changed from: private */
    public void processChannelStateChange(ReliableChannel reliableChannel) {
        ReliableChannelState state = reliableChannel.getState();
        synchronized (this.__lock) {
            if (state == ReliableChannelState.Open) {
                boolean z = false;
                boolean z2 = true;
                for (ReliableChannel next : HashMapExtensions.getValues(this.__channelsByInnerStreamId)) {
                    z2 &= Global.equals(next.getState(), ReliableChannelState.Open);
                    if (Global.equals(next.getState(), ReliableChannelState.Failed)) {
                        setError(next.getError());
                        z = true;
                    }
                }
                if (z) {
                    setState(ReliableTransportState.Failed);
                } else if (z2) {
                    setState(ReliableTransportState.Connected);
                }
            } else if (state == ReliableChannelState.Failed) {
                setError(reliableChannel.getError());
                setState(ReliableTransportState.Failed);
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:54:?, code lost:
        r5.receiveSctpMessage(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:?, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0082 A[Catch:{ all -> 0x00f2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0091 A[Catch:{ all -> 0x00f2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0096 A[Catch:{ all -> 0x00f2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x009f A[Catch:{ all -> 0x00f2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00e0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void processIncomingSctpMessage(fm.liveswitch.SctpMessage r11) {
        /*
            r10 = this;
            int r0 = r11.getStreamId()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            java.lang.String r0 = fm.liveswitch.IntegerExtensions.toString(r0)
            r1 = 0
            r2 = 0
            java.lang.Object r3 = r10.__lock     // Catch:{ Exception -> 0x00f5 }
            monitor-enter(r3)     // Catch:{ Exception -> 0x00f5 }
            r4 = 1
            fm.liveswitch.Holder r5 = new fm.liveswitch.Holder     // Catch:{ all -> 0x00f2 }
            r5.<init>(r1)     // Catch:{ all -> 0x00f2 }
            java.util.HashMap<java.lang.String, fm.liveswitch.ReliableChannel> r6 = r10.__channelsByInnerStreamId     // Catch:{ all -> 0x00f2 }
            boolean r6 = fm.liveswitch.HashMapExtensions.tryGetValue(r6, r0, r5)     // Catch:{ all -> 0x00f2 }
            java.lang.Object r5 = r5.getValue()     // Catch:{ all -> 0x00f2 }
            fm.liveswitch.ReliableChannel r5 = (fm.liveswitch.ReliableChannel) r5     // Catch:{ all -> 0x00f2 }
            if (r6 != 0) goto L_0x00ed
            fm.liveswitch.ReliableRtcDcepDataChannelOpen r6 = fm.liveswitch.ReliableRtcDcepMessage.tryParseAsDataChannelOpenMessage(r11)     // Catch:{ all -> 0x00f2 }
            if (r6 == 0) goto L_0x00e2
            fm.liveswitch.ReliableChannelType r7 = r6.getChannelType()     // Catch:{ all -> 0x00f2 }
            fm.liveswitch.ReliableChannelType r8 = fm.liveswitch.ReliableChannelType.Reliable     // Catch:{ all -> 0x00f2 }
            boolean r7 = fm.liveswitch.Global.equals(r7, r8)     // Catch:{ all -> 0x00f2 }
            if (r7 != 0) goto L_0x0052
            fm.liveswitch.ReliableChannelType r7 = r6.getChannelType()     // Catch:{ all -> 0x00f2 }
            fm.liveswitch.ReliableChannelType r8 = fm.liveswitch.ReliableChannelType.PartialReliableREXMIT     // Catch:{ all -> 0x00f2 }
            boolean r7 = fm.liveswitch.Global.equals(r7, r8)     // Catch:{ all -> 0x00f2 }
            if (r7 != 0) goto L_0x0052
            fm.liveswitch.ReliableChannelType r7 = r6.getChannelType()     // Catch:{ all -> 0x00f2 }
            fm.liveswitch.ReliableChannelType r8 = fm.liveswitch.ReliableChannelType.PartialReliableTimed     // Catch:{ all -> 0x00f2 }
            boolean r7 = fm.liveswitch.Global.equals(r7, r8)     // Catch:{ all -> 0x00f2 }
            if (r7 == 0) goto L_0x0050
            goto L_0x0052
        L_0x0050:
            r7 = 0
            goto L_0x0053
        L_0x0052:
            r7 = 1
        L_0x0053:
            java.lang.String r1 = r6.getLabel()     // Catch:{ all -> 0x00f2 }
            fm.liveswitch.Holder r8 = new fm.liveswitch.Holder     // Catch:{ all -> 0x00f2 }
            r8.<init>(r5)     // Catch:{ all -> 0x00f2 }
            java.util.HashMap<java.lang.String, fm.liveswitch.ReliableChannel> r5 = r10.__channelsByLabel     // Catch:{ all -> 0x00f2 }
            boolean r5 = fm.liveswitch.HashMapExtensions.tryGetValue(r5, r1, r8)     // Catch:{ all -> 0x00f2 }
            java.lang.Object r8 = r8.getValue()     // Catch:{ all -> 0x00f2 }
            fm.liveswitch.ReliableChannel r8 = (fm.liveswitch.ReliableChannel) r8     // Catch:{ all -> 0x00f2 }
            boolean r9 = r10.get_LegacyMatching()     // Catch:{ all -> 0x00f2 }
            if (r9 != 0) goto L_0x009d
            if (r5 == 0) goto L_0x009d
            int r5 = r11.getStreamId()     // Catch:{ all -> 0x00f2 }
            r8.setInnerTransportStreamId(r5)     // Catch:{ all -> 0x00f2 }
            java.util.HashMap<java.lang.String, fm.liveswitch.ReliableChannel> r5 = r10.__channelsByInnerStreamId     // Catch:{ all -> 0x00f2 }
            fm.liveswitch.HashMapExtensions.add(r5, r0, r8)     // Catch:{ all -> 0x00f2 }
            boolean r5 = r8.getOrdered()     // Catch:{ all -> 0x00f2 }
            if (r5 != 0) goto L_0x0087
            if (r7 == 0) goto L_0x0085
            goto L_0x0087
        L_0x0085:
            r5 = 0
            goto L_0x0088
        L_0x0087:
            r5 = 1
        L_0x0088:
            r8.setOrdered(r5)     // Catch:{ all -> 0x00f2 }
            java.lang.String r5 = r8.getSubProtocol()     // Catch:{ all -> 0x00f2 }
            if (r5 == 0) goto L_0x0096
            java.lang.String r5 = r8.getSubProtocol()     // Catch:{ all -> 0x00f2 }
            goto L_0x009a
        L_0x0096:
            java.lang.String r5 = r6.getSubProtocol()     // Catch:{ all -> 0x00f2 }
        L_0x009a:
            r8.setSubProtocol(r5)     // Catch:{ all -> 0x00f2 }
        L_0x009d:
            if (r8 != 0) goto L_0x00e0
            java.lang.String r5 = "Reliable Data: remote peer requested communication on channel {0} with label {1}, which has not yet been created. Creating a new channel."
            java.lang.String r5 = fm.liveswitch.StringExtensions.format(r5, r0, r1)     // Catch:{ all -> 0x00f2 }
            fm.liveswitch.Log.debug(r5)     // Catch:{ all -> 0x00f2 }
            fm.liveswitch.ReliableChannel r5 = new fm.liveswitch.ReliableChannel     // Catch:{ all -> 0x00f2 }
            java.lang.String r8 = r6.getLabel()     // Catch:{ all -> 0x00f2 }
            java.lang.String r6 = r6.getSubProtocol()     // Catch:{ all -> 0x00f2 }
            java.lang.Object r9 = r10.__lock     // Catch:{ all -> 0x00f2 }
            r5.<init>(r8, r7, r6, r9)     // Catch:{ all -> 0x00f2 }
            boolean r2 = r10.get_LegacyMatching()     // Catch:{ all -> 0x00dd }
            if (r2 == 0) goto L_0x00c5
            int r2 = r11.getStreamId()     // Catch:{ all -> 0x00dd }
            r10.registerChannelLegacy(r2, r0, r5)     // Catch:{ all -> 0x00dd }
            goto L_0x00d4
        L_0x00c5:
            java.util.HashMap<java.lang.String, fm.liveswitch.ReliableChannel> r2 = r10.__channelsByInnerStreamId     // Catch:{ all -> 0x00dd }
            fm.liveswitch.HashMapExtensions.add(r2, r0, r5)     // Catch:{ all -> 0x00dd }
            int r2 = r11.getStreamId()     // Catch:{ all -> 0x00dd }
            r5.setInnerTransportStreamId(r2)     // Catch:{ all -> 0x00dd }
            r10.registerChannel(r5)     // Catch:{ all -> 0x00dd }
        L_0x00d4:
            fm.liveswitch.IAction1<fm.liveswitch.ReliableChannel> r2 = r10._onDataChannel     // Catch:{ all -> 0x00dd }
            if (r2 == 0) goto L_0x00db
            r2.invoke(r5)     // Catch:{ all -> 0x00dd }
        L_0x00db:
            r2 = 1
            goto L_0x00ed
        L_0x00dd:
            r11 = move-exception
            r2 = 1
            goto L_0x00f3
        L_0x00e0:
            r5 = r8
            goto L_0x00ed
        L_0x00e2:
            java.lang.String r11 = "Reliable Data: attempt to communicate on an unassigned channel {0}."
            java.lang.String r11 = fm.liveswitch.StringExtensions.format((java.lang.String) r11, (java.lang.Object) r0)     // Catch:{ all -> 0x00f2 }
            fm.liveswitch.Log.debug(r11)     // Catch:{ all -> 0x00f2 }
            monitor-exit(r3)     // Catch:{ all -> 0x00f2 }
            return
        L_0x00ed:
            monitor-exit(r3)     // Catch:{ all -> 0x00f2 }
            r5.receiveSctpMessage(r11)     // Catch:{ Exception -> 0x00f5 }
            goto L_0x0111
        L_0x00f2:
            r11 = move-exception
        L_0x00f3:
            monitor-exit(r3)     // Catch:{ all -> 0x00f2 }
            throw r11     // Catch:{ Exception -> 0x00f5 }
        L_0x00f5:
            r11 = move-exception
            java.lang.String r3 = "Reliable Data: Could not process incoming Sctp Message."
            fm.liveswitch.Log.error(r3, r11)
            if (r2 == 0) goto L_0x0111
            java.lang.Object r11 = r10.__lock
            monitor-enter(r11)
            java.util.HashMap<java.lang.String, fm.liveswitch.ReliableChannel> r2 = r10.__channelsByInnerStreamId     // Catch:{ all -> 0x010e }
            fm.liveswitch.HashMapExtensions.remove(r2, r0)     // Catch:{ all -> 0x010e }
            if (r1 == 0) goto L_0x010c
            java.util.HashMap<java.lang.String, fm.liveswitch.ReliableChannel> r0 = r10.__channelsByLabel     // Catch:{ all -> 0x010e }
            fm.liveswitch.HashMapExtensions.remove(r0, r1)     // Catch:{ all -> 0x010e }
        L_0x010c:
            monitor-exit(r11)     // Catch:{ all -> 0x010e }
            goto L_0x0111
        L_0x010e:
            r0 = move-exception
            monitor-exit(r11)     // Catch:{ all -> 0x010e }
            throw r0
        L_0x0111:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.ReliableTransport.processIncomingSctpMessage(fm.liveswitch.SctpMessage):void");
    }

    private void raiseStateChange() {
        IAction1<ReliableTransport> iAction1 = this._onStateChange;
        if (iAction1 != null) {
            iAction1.invoke(this);
        }
    }

    private void registerChannel(ReliableChannel reliableChannel) {
        HashMapExtensions.add(this.__channelsByLabel, reliableChannel.getLabel(), reliableChannel);
        reliableChannel.setInnerTransport(getInnerTransport());
        reliableChannel.removeOnStateChange(new IActionDelegate1<ReliableChannel>() {
            public String getId() {
                return "fm.liveswitch.ReliableTransport.processChannelStateChange";
            }

            public void invoke(ReliableChannel reliableChannel) {
                ReliableTransport.this.processChannelStateChange(reliableChannel);
            }
        });
        reliableChannel.addOnStateChange(new IActionDelegate1<ReliableChannel>() {
            public String getId() {
                return "fm.liveswitch.ReliableTransport.processChannelStateChange";
            }

            public void invoke(ReliableChannel reliableChannel) {
                ReliableTransport.this.processChannelStateChange(reliableChannel);
            }
        });
    }

    private void registerChannelLegacy(int i, String str, ReliableChannel reliableChannel) {
        reliableChannel.setInnerTransport(getInnerTransport());
        reliableChannel.setInnerTransportStreamId(i);
        HashMapExtensions.add(this.__channelsByInnerStreamId, str, reliableChannel);
        reliableChannel.removeOnStateChange(new IActionDelegate1<ReliableChannel>() {
            public String getId() {
                return "fm.liveswitch.ReliableTransport.processChannelStateChange";
            }

            public void invoke(ReliableChannel reliableChannel) {
                ReliableTransport.this.processChannelStateChange(reliableChannel);
            }
        });
        reliableChannel.addOnStateChange(new IActionDelegate1<ReliableChannel>() {
            public String getId() {
                return "fm.liveswitch.ReliableTransport.processChannelStateChange";
            }

            public void invoke(ReliableChannel reliableChannel) {
                ReliableTransport.this.processChannelStateChange(reliableChannel);
            }
        });
    }

    public ReliableTransport(Object obj, SctpTransport sctpTransport, ReliableChannel[] reliableChannelArr, boolean z) {
        this.__lock = obj;
        setId(Utility.generateId());
        setState(ReliableTransportState.New);
        this.__channelsByInnerStreamId = new HashMap<>();
        this.__channelsByLabel = new HashMap<>();
        setProtocol(ReliableSctpProtocol.RtcDataChannelSctpProtocol);
        setInnerTransport(sctpTransport);
        int i = 0;
        if (z) {
            while (i < ArrayExtensions.getLength((Object[]) reliableChannelArr)) {
                int i2 = (i * 2) + 1;
                registerChannelLegacy(i2, IntegerExtensions.toString(Integer.valueOf(i2)), reliableChannelArr[i]);
                i++;
            }
        } else {
            try {
                int length = reliableChannelArr.length;
                while (i < length) {
                    registerChannel(reliableChannelArr[i]);
                    i++;
                }
            } catch (Exception unused) {
                throw new RuntimeException(new Exception("Could not prepare ReliableTransport for data stream. Data channels within data stream must have unique labels. If index-based matching of data channels is required, set DataStream.LegacyDataChannelMatching to true."));
            }
        }
        set_LegacyMatching(z);
        getInnerTransport().setOnMessage(new IActionDelegate1<SctpMessage>() {
            public String getId() {
                return "fm.liveswitch.ReliableTransport.processIncomingSctpMessage";
            }

            public void invoke(SctpMessage sctpMessage) {
                ReliableTransport.this.processIncomingSctpMessage(sctpMessage);
            }
        });
    }

    public void removeOnDataChannel(IAction1<ReliableChannel> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onDataChannel, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onDataChannel.remove(iAction1);
        if (this.__onDataChannel.size() == 0) {
            this._onDataChannel = null;
        }
    }

    public void removeOnStateChange(IAction1<ReliableTransport> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onStateChange, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onStateChange.remove(iAction1);
        if (this.__onStateChange.size() == 0) {
            this._onStateChange = null;
        }
    }

    private void set_LegacyMatching(boolean z) {
        this.__legacyMatching = z;
    }

    private void setError(Error error) {
        this._error = error;
    }

    private void setId(String str) {
        this._id = str;
    }

    public void setInnerTransport(SctpTransport sctpTransport) {
        this._innerTransport = sctpTransport;
    }

    private void setProtocol(ReliableSctpProtocol reliableSctpProtocol) {
        this._protocol = reliableSctpProtocol;
    }

    public void setState(ReliableTransportState reliableTransportState) {
        synchronized (this.__lock) {
            if (!Global.equals(this.__state, reliableTransportState)) {
                this.__state = reliableTransportState;
                raiseStateChange();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x007c, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean start(boolean r7, boolean r8) {
        /*
            r6 = this;
            java.lang.Object r0 = r6.__lock
            monitor-enter(r0)
            r6.__isDtlsServer = r7     // Catch:{ all -> 0x007d }
            r6.__isOfferer = r8     // Catch:{ all -> 0x007d }
            fm.liveswitch.ReliableTransportState r7 = r6.getState()     // Catch:{ all -> 0x007d }
            fm.liveswitch.ReliableTransportState r8 = fm.liveswitch.ReliableTransportState.New     // Catch:{ all -> 0x007d }
            boolean r7 = fm.liveswitch.Global.equals(r7, r8)     // Catch:{ all -> 0x007d }
            r8 = 0
            if (r7 != 0) goto L_0x0016
            monitor-exit(r0)     // Catch:{ all -> 0x007d }
            return r8
        L_0x0016:
            fm.liveswitch.ReliableTransportState r7 = fm.liveswitch.ReliableTransportState.Opening     // Catch:{ all -> 0x007d }
            r6.setState(r7)     // Catch:{ all -> 0x007d }
            boolean r7 = r6.__isOfferer     // Catch:{ all -> 0x007d }
            r1 = 1
            if (r7 == 0) goto L_0x007b
            boolean r7 = r6.get_LegacyMatching()     // Catch:{ all -> 0x007d }
            if (r7 == 0) goto L_0x0040
            java.util.HashMap<java.lang.String, fm.liveswitch.ReliableChannel> r7 = r6.__channelsByInnerStreamId     // Catch:{ all -> 0x007d }
            java.util.Collection r7 = fm.liveswitch.HashMapExtensions.getValues(r7)     // Catch:{ all -> 0x007d }
            java.util.Iterator r7 = r7.iterator()     // Catch:{ all -> 0x007d }
        L_0x0030:
            boolean r8 = r7.hasNext()     // Catch:{ all -> 0x007d }
            if (r8 == 0) goto L_0x007b
            java.lang.Object r8 = r7.next()     // Catch:{ all -> 0x007d }
            fm.liveswitch.ReliableChannel r8 = (fm.liveswitch.ReliableChannel) r8     // Catch:{ all -> 0x007d }
            r6.openChannel(r8)     // Catch:{ all -> 0x007d }
            goto L_0x0030
        L_0x0040:
            java.util.HashMap<java.lang.String, fm.liveswitch.ReliableChannel> r7 = r6.__channelsByLabel     // Catch:{ all -> 0x007d }
            java.util.Collection r7 = fm.liveswitch.HashMapExtensions.getValues(r7)     // Catch:{ all -> 0x007d }
            java.util.Iterator r7 = r7.iterator()     // Catch:{ all -> 0x007d }
            r2 = 0
        L_0x004b:
            boolean r3 = r7.hasNext()     // Catch:{ all -> 0x007d }
            if (r3 == 0) goto L_0x007b
            java.lang.Object r3 = r7.next()     // Catch:{ all -> 0x007d }
            fm.liveswitch.ReliableChannel r3 = (fm.liveswitch.ReliableChannel) r3     // Catch:{ all -> 0x007d }
            boolean r4 = r6.__isDtlsServer     // Catch:{ all -> 0x007d }
            if (r4 == 0) goto L_0x005d
            r4 = 1
            goto L_0x005e
        L_0x005d:
            r4 = 0
        L_0x005e:
            int r5 = r2 * 2
            int r4 = r4 + r5
            r3.setInnerTransportStreamId(r4)     // Catch:{ all -> 0x007d }
            java.util.HashMap<java.lang.String, fm.liveswitch.ReliableChannel> r4 = r6.__channelsByInnerStreamId     // Catch:{ all -> 0x007d }
            int r5 = r3.getInnerTransportStreamId()     // Catch:{ all -> 0x007d }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x007d }
            java.lang.String r5 = fm.liveswitch.IntegerExtensions.toString(r5)     // Catch:{ all -> 0x007d }
            fm.liveswitch.HashMapExtensions.add(r4, r5, r3)     // Catch:{ all -> 0x007d }
            r6.openChannel(r3)     // Catch:{ all -> 0x007d }
            int r2 = r2 + 1
            goto L_0x004b
        L_0x007b:
            monitor-exit(r0)     // Catch:{ all -> 0x007d }
            return r1
        L_0x007d:
            r7 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x007d }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.ReliableTransport.start(boolean, boolean):boolean");
    }

    public void stop() {
        synchronized (this.__lock) {
            setState(ReliableTransportState.Closing);
            getInnerTransport().setOnMessage((IAction1<SctpMessage>) null);
            for (ReliableChannel next : HashMapExtensions.getValues(this.__channelsByInnerStreamId)) {
                next.removeOnStateChange(new IActionDelegate1<ReliableChannel>() {
                    public String getId() {
                        return "fm.liveswitch.ReliableTransport.processChannelStateChange";
                    }

                    public void invoke(ReliableChannel reliableChannel) {
                        ReliableTransport.this.processChannelStateChange(reliableChannel);
                    }
                });
                next.close();
            }
            setState(ReliableTransportState.Closed);
        }
    }
}
