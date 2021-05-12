package fm.liveswitch;

import com.microsoft.appcenter.Constants;
import fm.liveswitch.stun.Message;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.jetbrains.anko.DimensionsKt;

class IceGatherer {
    private static ILog __log = Log.getLogger(IceGatherer.class);
    private HashMap<String, IcePreferenceSpeedBlock> __adapterSpeedTable;
    private IceComponent __component;
    private IceCandidate __defaultLocalCandidate;
    private int __defaultPortMax;
    private int __defaultPortMin;
    private LocalAddress[] __localAddresses;
    private Object __localPreferencesLock;
    private Object __lock;
    /* access modifiers changed from: private */
    public List<IAction3<DataBuffer, IceCandidate, TransportAddress>> __onIncomingApplicationData;
    /* access modifiers changed from: private */
    public List<IAction2<IceGatherer, IceCandidate>> __onLocalCandidate;
    /* access modifiers changed from: private */
    public List<IAction1<IceGatherer>> __onStateChange;
    private IceGatherOptions __options;
    private IceGatherer __relatedRtcpGatherer;
    private int __resolvesRemaining;
    private DatagramSocket[] __rtcpDatagramSockets;
    private ArrayList<IceSocketsServerPair> __rtcpStreamSocketsAndServers;
    private Scheduler __scheduler;
    private HashMap<String, IceSocketManager> __socketManagers;
    private ArrayList<ManagedSocket> __sockets;
    private IceGatheringState __state;
    private IceTransactionManager __transactionManager;
    private HashMap<String, Long> __usedLocalPreferences;
    private boolean _closingShouldNotTriggerGlobalNonGracefulShutdown;
    private IFunction1<DatagramSocketCreateArgs, DatagramSocket> _createDatagramSocket;
    private IFunction1<StreamSocketCreateArgs, StreamSocket> _createStreamSocket;
    private Error _error;
    private String _id;
    private IceParameters _localParameters;
    private IAction3<DataBuffer, IceCandidate, TransportAddress> _onIncomingApplicationData;
    private IAction2<IceGatherer, IceCandidate> _onLocalCandidate;
    private IAction1<IceGatherer> _onStateChange;
    private IAction3<Message, IceCandidate, TransportAddress> _onStunRequest;
    private int _receiveBufferSize;
    private int _sendBufferSize;
    private int _streamIndex;
    private boolean _verboseLogging;

    private void addNewSocketManager(IceSocketManager iceSocketManager) {
        String prepareSocketManagerKey = prepareSocketManagerKey(iceSocketManager.getProtocol(), iceSocketManager.getLocalIpAddress(), iceSocketManager.getLocalPort());
        synchronized (this.__lock) {
            HashMapExtensions.add(this.__socketManagers, prepareSocketManagerKey, iceSocketManager);
        }
    }

    public void addOnIncomingApplicationData(IAction3<DataBuffer, IceCandidate, TransportAddress> iAction3) {
        if (iAction3 != null) {
            if (this._onIncomingApplicationData == null) {
                this._onIncomingApplicationData = new IAction3<DataBuffer, IceCandidate, TransportAddress>() {
                    public void invoke(DataBuffer dataBuffer, IceCandidate iceCandidate, TransportAddress transportAddress) {
                        Iterator it = new ArrayList(IceGatherer.this.__onIncomingApplicationData).iterator();
                        while (it.hasNext()) {
                            ((IAction3) it.next()).invoke(dataBuffer, iceCandidate, transportAddress);
                        }
                    }
                };
            }
            this.__onIncomingApplicationData.add(iAction3);
        }
    }

    public void addOnLocalCandidate(IAction2<IceGatherer, IceCandidate> iAction2) {
        if (iAction2 != null) {
            if (this._onLocalCandidate == null) {
                this._onLocalCandidate = new IAction2<IceGatherer, IceCandidate>() {
                    public void invoke(IceGatherer iceGatherer, IceCandidate iceCandidate) {
                        Iterator it = new ArrayList(IceGatherer.this.__onLocalCandidate).iterator();
                        while (it.hasNext()) {
                            ((IAction2) it.next()).invoke(iceGatherer, iceCandidate);
                        }
                    }
                };
            }
            this.__onLocalCandidate.add(iAction2);
        }
    }

    public void addOnStateChange(IAction1<IceGatherer> iAction1) {
        if (iAction1 != null) {
            if (this._onStateChange == null) {
                this._onStateChange = new IAction1<IceGatherer>() {
                    public void invoke(IceGatherer iceGatherer) {
                        Iterator it = new ArrayList(IceGatherer.this.__onStateChange).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(iceGatherer);
                        }
                    }
                };
            }
            this.__onStateChange.add(iAction1);
        }
    }

    /* access modifiers changed from: package-private */
    public void addStreamSockets(IceSocketsServerPair iceSocketsServerPair) {
        synchronized (this.__lock) {
            int i = 0;
            ManagedSocket[] managedSocketArr = null;
            if (!Global.equals(getComponent(), IceComponent.Rtcp) || iceSocketsServerPair == null) {
                if (iceSocketsServerPair != null) {
                    managedSocketArr = iceSocketsServerPair.getSockets();
                }
                if (managedSocketArr != null) {
                    int length = managedSocketArr.length;
                    while (i < length) {
                        managedSocketArr[i].close();
                        i++;
                    }
                }
                setError(new Error(ErrorCode.IceUnsuitableSocketAssignment, new Exception(StringExtensions.format("Excpected component: RTP. Found component: {0}.", (Object) getComponent().toString()))));
                setState(IceGatheringState.Failed);
            } else {
                if (!Global.equals(getState(), IceGatheringState.Gathering)) {
                    if (!Global.equals(getState(), IceGatheringState.Complete)) {
                        if (Global.equals(getState(), IceGatheringState.New)) {
                            ArrayListExtensions.addRange(this.__sockets, (T[]) iceSocketsServerPair.getSockets());
                            this.__rtcpStreamSocketsAndServers.add(iceSocketsServerPair);
                        } else {
                            ManagedSocket[] sockets = iceSocketsServerPair.getSockets();
                            int length2 = sockets.length;
                            while (i < length2) {
                                sockets[i].close();
                                i++;
                            }
                        }
                    }
                }
                setState(IceGatheringState.Gathering);
                IceServer server = iceSocketsServerPair.getServer();
                ManagedSocket[] sockets2 = iceSocketsServerPair.getSockets();
                ArrayListExtensions.addRange(this.__sockets, (T[]) sockets2);
                prepareStreamSocketManagers(sockets2, server, server.getIPAddress(), (Object) null);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void assignDatagramSockets(ManagedSocket[] managedSocketArr) {
        if (managedSocketArr != null) {
            synchronized (this.__lock) {
                int i = 0;
                if (!Global.equals(getComponent(), IceComponent.Rtcp) || !Global.equals(getState(), IceGatheringState.New)) {
                    int length = managedSocketArr.length;
                    while (i < length) {
                        managedSocketArr[i].close();
                        i++;
                    }
                    setError(new Error(ErrorCode.IceUnsuitableSocketAssignment, new Exception(StringExtensions.format("Excpected component: RTP, expected state: New. Found component: {0}, found state: {1}.", getComponent().toString(), getState().toString()))));
                    setState(IceGatheringState.Failed);
                } else {
                    ArrayListExtensions.addRange(this.__sockets, (T[]) managedSocketArr);
                    DatagramSocket[] datagramSocketArr = new DatagramSocket[ArrayExtensions.getLength((Object[]) managedSocketArr)];
                    while (i < ArrayExtensions.getLength((Object[]) managedSocketArr)) {
                        datagramSocketArr[i] = managedSocketArr[i];
                        i++;
                    }
                    this.__rtcpDatagramSockets = datagramSocketArr;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x01d5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int assignLocalPreference(long r13) {
        /*
            r12 = this;
            fm.liveswitch.IceCandidate.getUnset()
            java.lang.Long r0 = java.lang.Long.valueOf(r13)
            java.lang.String r0 = fm.liveswitch.LongExtensions.toString(r0)
            java.util.HashMap<java.lang.String, fm.liveswitch.IcePreferenceSpeedBlock> r1 = r12.__adapterSpeedTable
            boolean r1 = r1.containsKey(r0)
            r2 = 65535(0xffff, float:9.1834E-41)
            if (r1 == 0) goto L_0x00fc
            java.util.HashMap<java.lang.String, fm.liveswitch.IcePreferenceSpeedBlock> r1 = r12.__adapterSpeedTable
            java.util.HashMap r1 = fm.liveswitch.HashMapExtensions.getItem(r1)
            java.lang.Object r1 = r1.get(r0)
            fm.liveswitch.IcePreferenceSpeedBlock r1 = (fm.liveswitch.IcePreferenceSpeedBlock) r1
            int r1 = r1.getStart()
            int r1 = r1 + -1
            if (r1 < 0) goto L_0x0071
            java.util.HashMap<java.lang.String, java.lang.Long> r3 = r12.__usedLocalPreferences
            java.lang.Integer r4 = java.lang.Integer.valueOf(r1)
            java.lang.String r4 = fm.liveswitch.IntegerExtensions.toString(r4)
            boolean r3 = r3.containsKey(r4)
            if (r3 != 0) goto L_0x0071
            java.util.HashMap<java.lang.String, fm.liveswitch.IcePreferenceSpeedBlock> r2 = r12.__adapterSpeedTable
            java.util.HashMap r2 = fm.liveswitch.HashMapExtensions.getItem(r2)
            java.lang.Object r2 = r2.get(r0)
            fm.liveswitch.IcePreferenceSpeedBlock r2 = (fm.liveswitch.IcePreferenceSpeedBlock) r2
            int r2 = r2.getStart()
            int r2 = r2 + -1
            java.util.HashMap<java.lang.String, fm.liveswitch.IcePreferenceSpeedBlock> r3 = r12.__adapterSpeedTable
            java.util.HashMap r3 = fm.liveswitch.HashMapExtensions.getItem(r3)
            java.lang.Object r0 = r3.get(r0)
            fm.liveswitch.IcePreferenceSpeedBlock r0 = (fm.liveswitch.IcePreferenceSpeedBlock) r0
            r0.setStart(r2)
            java.util.HashMap<java.lang.String, java.lang.Long> r0 = r12.__usedLocalPreferences
            java.util.HashMap r0 = fm.liveswitch.HashMapExtensions.getItem(r0)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r1)
            java.lang.String r2 = fm.liveswitch.IntegerExtensions.toString(r2)
            java.lang.Long r13 = java.lang.Long.valueOf(r13)
            fm.liveswitch.HashMapExtensions.set(r0, r2, r13)
            return r1
        L_0x0071:
            java.util.HashMap<java.lang.String, fm.liveswitch.IcePreferenceSpeedBlock> r3 = r12.__adapterSpeedTable
            java.util.HashMap r3 = fm.liveswitch.HashMapExtensions.getItem(r3)
            java.lang.Object r3 = r3.get(r0)
            fm.liveswitch.IcePreferenceSpeedBlock r3 = (fm.liveswitch.IcePreferenceSpeedBlock) r3
            int r3 = r3.getStop()
            if (r3 >= r2) goto L_0x00dc
            java.util.HashMap<java.lang.String, java.lang.Long> r2 = r12.__usedLocalPreferences
            java.util.HashMap<java.lang.String, fm.liveswitch.IcePreferenceSpeedBlock> r3 = r12.__adapterSpeedTable
            java.util.HashMap r3 = fm.liveswitch.HashMapExtensions.getItem(r3)
            java.lang.Object r3 = r3.get(r0)
            fm.liveswitch.IcePreferenceSpeedBlock r3 = (fm.liveswitch.IcePreferenceSpeedBlock) r3
            int r3 = r3.getStop()
            int r3 = r3 + 1
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            java.lang.String r3 = fm.liveswitch.IntegerExtensions.toString(r3)
            boolean r2 = r2.containsKey(r3)
            if (r2 != 0) goto L_0x00dc
            java.util.HashMap<java.lang.String, fm.liveswitch.IcePreferenceSpeedBlock> r1 = r12.__adapterSpeedTable
            java.util.HashMap r1 = fm.liveswitch.HashMapExtensions.getItem(r1)
            java.lang.Object r1 = r1.get(r0)
            fm.liveswitch.IcePreferenceSpeedBlock r1 = (fm.liveswitch.IcePreferenceSpeedBlock) r1
            int r1 = r1.getStop()
            int r1 = r1 + 1
            java.util.HashMap<java.lang.String, fm.liveswitch.IcePreferenceSpeedBlock> r2 = r12.__adapterSpeedTable
            java.util.HashMap r2 = fm.liveswitch.HashMapExtensions.getItem(r2)
            java.lang.Object r0 = r2.get(r0)
            fm.liveswitch.IcePreferenceSpeedBlock r0 = (fm.liveswitch.IcePreferenceSpeedBlock) r0
            r0.setStop(r1)
            java.util.HashMap<java.lang.String, java.lang.Long> r0 = r12.__usedLocalPreferences
            java.util.HashMap r0 = fm.liveswitch.HashMapExtensions.getItem(r0)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r1)
            java.lang.String r2 = fm.liveswitch.IntegerExtensions.toString(r2)
            java.lang.Long r13 = java.lang.Long.valueOf(r13)
            fm.liveswitch.HashMapExtensions.set(r0, r2, r13)
            return r1
        L_0x00dc:
            int r0 = r12.assignLocalPreferenceSuboptimally(r1)
            int r1 = fm.liveswitch.IceCandidate.getUnset()
            if (r0 == r1) goto L_0x00fb
            java.util.HashMap<java.lang.String, java.lang.Long> r1 = r12.__usedLocalPreferences
            java.util.HashMap r1 = fm.liveswitch.HashMapExtensions.getItem(r1)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r0)
            java.lang.String r2 = fm.liveswitch.IntegerExtensions.toString(r2)
            java.lang.Long r13 = java.lang.Long.valueOf(r13)
            fm.liveswitch.HashMapExtensions.set(r1, r2, r13)
        L_0x00fb:
            return r0
        L_0x00fc:
            java.util.HashMap<java.lang.String, fm.liveswitch.IcePreferenceSpeedBlock> r1 = r12.__adapterSpeedTable
            int r1 = fm.liveswitch.HashMapExtensions.getCount(r1)
            if (r1 != 0) goto L_0x012a
            r1 = 32676(0x7fa4, float:4.5789E-41)
            java.util.HashMap<java.lang.String, fm.liveswitch.IcePreferenceSpeedBlock> r2 = r12.__adapterSpeedTable
            java.util.HashMap r2 = fm.liveswitch.HashMapExtensions.getItem(r2)
            fm.liveswitch.IcePreferenceSpeedBlock r3 = new fm.liveswitch.IcePreferenceSpeedBlock
            r3.<init>(r1, r1)
            fm.liveswitch.HashMapExtensions.set(r2, r0, r3)
            java.util.HashMap<java.lang.String, java.lang.Long> r0 = r12.__usedLocalPreferences
            java.util.HashMap r0 = fm.liveswitch.HashMapExtensions.getItem(r0)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r1)
            java.lang.String r2 = fm.liveswitch.IntegerExtensions.toString(r2)
            java.lang.Long r13 = java.lang.Long.valueOf(r13)
            fm.liveswitch.HashMapExtensions.set(r0, r2, r13)
            return r1
        L_0x012a:
            java.util.HashMap<java.lang.String, fm.liveswitch.IcePreferenceSpeedBlock> r1 = r12.__adapterSpeedTable
            java.util.Set r1 = fm.liveswitch.HashMapExtensions.getKeys(r1)
            java.util.Iterator r1 = r1.iterator()
            r3 = -1
            r5 = r3
        L_0x0137:
            boolean r7 = r1.hasNext()
            r8 = 0
            if (r7 == 0) goto L_0x0161
            java.lang.Object r7 = r1.next()
            java.lang.String r7 = (java.lang.String) r7
            long r10 = fm.liveswitch.ParseAssistant.parseLongValue(r7)
            int r7 = (r10 > r3 ? 1 : (r10 == r3 ? 0 : -1))
            if (r7 <= 0) goto L_0x0153
            int r7 = (r10 > r13 ? 1 : (r10 == r13 ? 0 : -1))
            if (r7 >= 0) goto L_0x0153
            r3 = r10
            goto L_0x0137
        L_0x0153:
            int r7 = (r10 > r5 ? 1 : (r10 == r5 ? 0 : -1))
            if (r7 < 0) goto L_0x015b
            int r7 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
            if (r7 >= 0) goto L_0x0137
        L_0x015b:
            int r7 = (r10 > r13 ? 1 : (r10 == r13 ? 0 : -1))
            if (r7 <= 0) goto L_0x0137
            r5 = r10
            goto L_0x0137
        L_0x0161:
            int r1 = (r3 > r8 ? 1 : (r3 == r8 ? 0 : -1))
            if (r1 >= 0) goto L_0x0167
            r1 = 0
            goto L_0x0181
        L_0x0167:
            java.util.HashMap<java.lang.String, fm.liveswitch.IcePreferenceSpeedBlock> r1 = r12.__adapterSpeedTable
            java.util.HashMap r1 = fm.liveswitch.HashMapExtensions.getItem(r1)
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            java.lang.String r3 = fm.liveswitch.LongExtensions.toString(r3)
            java.lang.Object r1 = r1.get(r3)
            fm.liveswitch.IcePreferenceSpeedBlock r1 = (fm.liveswitch.IcePreferenceSpeedBlock) r1
            int r1 = r1.getStop()
            int r1 = r1 + 1
        L_0x0181:
            int r3 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
            if (r3 >= 0) goto L_0x0186
            goto L_0x01a0
        L_0x0186:
            java.util.HashMap<java.lang.String, fm.liveswitch.IcePreferenceSpeedBlock> r2 = r12.__adapterSpeedTable
            java.util.HashMap r2 = fm.liveswitch.HashMapExtensions.getItem(r2)
            java.lang.Long r3 = java.lang.Long.valueOf(r5)
            java.lang.String r3 = fm.liveswitch.LongExtensions.toString(r3)
            java.lang.Object r2 = r2.get(r3)
            fm.liveswitch.IcePreferenceSpeedBlock r2 = (fm.liveswitch.IcePreferenceSpeedBlock) r2
            int r2 = r2.getStart()
            int r2 = r2 + -1
        L_0x01a0:
            if (r2 <= r1) goto L_0x0237
            r3 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            int r5 = r2 + r1
            double r5 = (double) r5
            double r5 = r5 * r3
            double r3 = fm.liveswitch.MathAssistant.floor(r5)
            int r3 = (int) r3
            java.util.HashMap<java.lang.String, java.lang.Long> r4 = r12.__usedLocalPreferences
            java.lang.Integer r7 = java.lang.Integer.valueOf(r3)
            java.lang.String r7 = fm.liveswitch.IntegerExtensions.toString(r7)
            boolean r4 = r4.containsKey(r7)
            if (r4 == 0) goto L_0x01ef
        L_0x01be:
            int r3 = r3 + -1
            if (r3 < r1) goto L_0x01d3
            java.util.HashMap<java.lang.String, java.lang.Long> r4 = r12.__usedLocalPreferences
            java.lang.Integer r7 = java.lang.Integer.valueOf(r3)
            java.lang.String r7 = fm.liveswitch.IntegerExtensions.toString(r7)
            boolean r4 = r4.containsKey(r7)
            if (r4 == 0) goto L_0x01d3
            goto L_0x01be
        L_0x01d3:
            if (r3 >= r1) goto L_0x01ef
            double r3 = fm.liveswitch.MathAssistant.floor(r5)
            int r3 = (int) r3
        L_0x01da:
            int r3 = r3 + 1
            if (r3 > r2) goto L_0x01ef
            java.util.HashMap<java.lang.String, java.lang.Long> r4 = r12.__usedLocalPreferences
            java.lang.Integer r5 = java.lang.Integer.valueOf(r3)
            java.lang.String r5 = fm.liveswitch.IntegerExtensions.toString(r5)
            boolean r4 = r4.containsKey(r5)
            if (r4 == 0) goto L_0x01ef
            goto L_0x01da
        L_0x01ef:
            if (r3 < r1) goto L_0x0217
            if (r3 > r2) goto L_0x0217
            java.util.HashMap<java.lang.String, java.lang.Long> r1 = r12.__usedLocalPreferences
            java.util.HashMap r1 = fm.liveswitch.HashMapExtensions.getItem(r1)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r3)
            java.lang.String r2 = fm.liveswitch.IntegerExtensions.toString(r2)
            java.lang.Long r13 = java.lang.Long.valueOf(r13)
            fm.liveswitch.HashMapExtensions.set(r1, r2, r13)
            java.util.HashMap<java.lang.String, fm.liveswitch.IcePreferenceSpeedBlock> r13 = r12.__adapterSpeedTable
            java.util.HashMap r13 = fm.liveswitch.HashMapExtensions.getItem(r13)
            fm.liveswitch.IcePreferenceSpeedBlock r14 = new fm.liveswitch.IcePreferenceSpeedBlock
            r14.<init>(r3, r3)
            fm.liveswitch.HashMapExtensions.set(r13, r0, r14)
            return r3
        L_0x0217:
            int r0 = r12.assignLocalPreferenceSuboptimally(r3)
            int r1 = fm.liveswitch.IceCandidate.getUnset()
            if (r0 == r1) goto L_0x0236
            java.util.HashMap<java.lang.String, java.lang.Long> r1 = r12.__usedLocalPreferences
            java.util.HashMap r1 = fm.liveswitch.HashMapExtensions.getItem(r1)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r0)
            java.lang.String r2 = fm.liveswitch.IntegerExtensions.toString(r2)
            java.lang.Long r13 = java.lang.Long.valueOf(r13)
            fm.liveswitch.HashMapExtensions.set(r1, r2, r13)
        L_0x0236:
            return r0
        L_0x0237:
            int r0 = r12.assignLocalPreferenceSuboptimally(r1)
            int r1 = fm.liveswitch.IceCandidate.getUnset()
            if (r0 == r1) goto L_0x0256
            java.util.HashMap<java.lang.String, java.lang.Long> r1 = r12.__usedLocalPreferences
            java.util.HashMap r1 = fm.liveswitch.HashMapExtensions.getItem(r1)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r0)
            java.lang.String r2 = fm.liveswitch.IntegerExtensions.toString(r2)
            java.lang.Long r13 = java.lang.Long.valueOf(r13)
            fm.liveswitch.HashMapExtensions.set(r1, r2, r13)
        L_0x0256:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.IceGatherer.assignLocalPreference(long):int");
    }

    private int assignLocalPreferenceSuboptimally(int i) {
        int i2 = i;
        while (this.__usedLocalPreferences.containsKey(IntegerExtensions.toString(Integer.valueOf(i2))) && i2 >= 0) {
            i2--;
        }
        if (i2 < 0) {
            while (this.__usedLocalPreferences.containsKey(IntegerExtensions.toString(Integer.valueOf(i))) && i <= 65535) {
                i++;
            }
            i2 = i;
        }
        if (i2 != 65535 || !this.__usedLocalPreferences.containsKey(IntegerExtensions.toString(Integer.valueOf(i2)))) {
            return i2;
        }
        int unset = IceCandidate.getUnset();
        __log.error("Failed to assign local preference to a candidate");
        return unset;
    }

    public IceGatherer createRtcpGatherer() {
        IceGatherer iceGatherer;
        synchronized (this.__lock) {
            if (this.__relatedRtcpGatherer == null) {
                if (Global.equals(getState(), IceGatheringState.Gathering) || Global.equals(getState(), IceGatheringState.Closing) || Global.equals(getState(), IceGatheringState.Closed)) {
                    throw new RuntimeException(new Exception(StringExtensions.format("Gatherer cannot create related RTCP gatherer in state {0}.", (Object) getState().toString())));
                } else if (!Global.equals(this.__component, IceComponent.Rtcp)) {
                    this.__relatedRtcpGatherer = new IceGatherer(this.__lock, this.__scheduler, this.__options, getLocalParameters(), IceComponent.Rtcp);
                } else {
                    throw new RuntimeException(new Exception("RTCP Gatherer cannot create related RTCP gatherer."));
                }
            }
            iceGatherer = this.__relatedRtcpGatherer;
        }
        return iceGatherer;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:10|11|12|13|22) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x0048 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void createTcpSocket(boolean r5, boolean r6, java.lang.String r7, int r8, fm.liveswitch.Holder<fm.liveswitch.StreamSocket> r9, fm.liveswitch.BooleanHolder r10) {
        /*
            r4 = this;
            r0 = 0
            r9.setValue(r0)
            r1 = 0
            r10.setValue(r1)
            fm.liveswitch.IFunction1 r2 = r4.getCreateStreamSocket()     // Catch:{ Exception -> 0x004c }
            if (r2 == 0) goto L_0x0025
            fm.liveswitch.StreamSocketCreateArgs r2 = new fm.liveswitch.StreamSocketCreateArgs     // Catch:{ Exception -> 0x004c }
            r2.<init>(r1, r5, r6)     // Catch:{ Exception -> 0x004c }
            int r3 = r4.getStreamIndex()     // Catch:{ Exception -> 0x004c }
            r2.setStreamIndex(r3)     // Catch:{ Exception -> 0x004c }
            fm.liveswitch.IFunction1 r3 = r4.getCreateStreamSocket()     // Catch:{ Exception -> 0x004c }
            java.lang.Object r2 = r3.invoke(r2)     // Catch:{ Exception -> 0x004c }
            r9.setValue(r2)     // Catch:{ Exception -> 0x004c }
        L_0x0025:
            java.lang.Object r2 = r9.getValue()     // Catch:{ Exception -> 0x004c }
            if (r2 != 0) goto L_0x0033
            fm.liveswitch.TcpSocket r2 = new fm.liveswitch.TcpSocket     // Catch:{ Exception -> 0x004c }
            r2.<init>((boolean) r1, (boolean) r5, (boolean) r6)     // Catch:{ Exception -> 0x004c }
            r9.setValue(r2)     // Catch:{ Exception -> 0x004c }
        L_0x0033:
            java.lang.Object r5 = r9.getValue()     // Catch:{ Exception -> 0x004c }
            fm.liveswitch.StreamSocket r5 = (fm.liveswitch.StreamSocket) r5     // Catch:{ Exception -> 0x004c }
            boolean r5 = r5.bind(r7, r8, r10)     // Catch:{ Exception -> 0x004c }
            if (r5 != 0) goto L_0x005f
            java.lang.Object r5 = r9.getValue()     // Catch:{ Exception -> 0x0048 }
            fm.liveswitch.StreamSocket r5 = (fm.liveswitch.StreamSocket) r5     // Catch:{ Exception -> 0x0048 }
            r5.close()     // Catch:{ Exception -> 0x0048 }
        L_0x0048:
            r9.setValue(r0)     // Catch:{ Exception -> 0x004c }
            goto L_0x005f
        L_0x004c:
            r5 = move-exception
            fm.liveswitch.ILog r7 = __log
            if (r6 == 0) goto L_0x0054
            java.lang.String r6 = "TLS"
            goto L_0x0056
        L_0x0054:
            java.lang.String r6 = "TCP"
        L_0x0056:
            java.lang.String r8 = "Could not create {0} socket."
            java.lang.String r6 = fm.liveswitch.StringExtensions.format((java.lang.String) r8, (java.lang.Object) r6)
            r7.error((java.lang.String) r6, (java.lang.Exception) r5)
        L_0x005f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.IceGatherer.createTcpSocket(boolean, boolean, java.lang.String, int, fm.liveswitch.Holder, fm.liveswitch.BooleanHolder):void");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:20|21|22|23|28) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:22:0x007d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void createUdpSocket(boolean r4, java.lang.String r5, int r6, fm.liveswitch.Holder<fm.liveswitch.DatagramSocket> r7, fm.liveswitch.BooleanHolder r8) {
        /*
            r3 = this;
            r0 = 0
            r7.setValue(r0)
            r1 = 0
            r8.setValue(r1)
            fm.liveswitch.IFunction1 r1 = r3.getCreateDatagramSocket()     // Catch:{ Exception -> 0x0081 }
            if (r1 == 0) goto L_0x0025
            fm.liveswitch.DatagramSocketCreateArgs r1 = new fm.liveswitch.DatagramSocketCreateArgs     // Catch:{ Exception -> 0x0081 }
            r1.<init>(r4)     // Catch:{ Exception -> 0x0081 }
            int r2 = r3.getStreamIndex()     // Catch:{ Exception -> 0x0081 }
            r1.setStreamIndex(r2)     // Catch:{ Exception -> 0x0081 }
            fm.liveswitch.IFunction1 r2 = r3.getCreateDatagramSocket()     // Catch:{ Exception -> 0x0081 }
            java.lang.Object r1 = r2.invoke(r1)     // Catch:{ Exception -> 0x0081 }
            r7.setValue(r1)     // Catch:{ Exception -> 0x0081 }
        L_0x0025:
            java.lang.Object r1 = r7.getValue()     // Catch:{ Exception -> 0x0081 }
            if (r1 != 0) goto L_0x0033
            fm.liveswitch.UdpSocket r1 = new fm.liveswitch.UdpSocket     // Catch:{ Exception -> 0x0081 }
            r1.<init>(r4)     // Catch:{ Exception -> 0x0081 }
            r7.setValue(r1)     // Catch:{ Exception -> 0x0081 }
        L_0x0033:
            int r4 = r3.getSendBufferSize()     // Catch:{ Exception -> 0x0081 }
            r1 = 2048(0x800, float:2.87E-42)
            r2 = -1
            if (r4 != r2) goto L_0x004f
            java.lang.Object r4 = r7.getValue()     // Catch:{ Exception -> 0x0081 }
            fm.liveswitch.DatagramSocket r4 = (fm.liveswitch.DatagramSocket) r4     // Catch:{ Exception -> 0x0081 }
            int r4 = r4.getSendBufferSize()     // Catch:{ Exception -> 0x0081 }
            if (r4 == r2) goto L_0x004f
            int r4 = fm.liveswitch.MathAssistant.max((int) r1, (int) r4)     // Catch:{ Exception -> 0x0081 }
            r3.setSendBufferSize(r4)     // Catch:{ Exception -> 0x0081 }
        L_0x004f:
            int r4 = r3.getReceiveBufferSize()     // Catch:{ Exception -> 0x0081 }
            if (r4 != r2) goto L_0x0068
            java.lang.Object r4 = r7.getValue()     // Catch:{ Exception -> 0x0081 }
            fm.liveswitch.DatagramSocket r4 = (fm.liveswitch.DatagramSocket) r4     // Catch:{ Exception -> 0x0081 }
            int r4 = r4.getReceiveBufferSize()     // Catch:{ Exception -> 0x0081 }
            if (r4 == r2) goto L_0x0068
            int r4 = fm.liveswitch.MathAssistant.max((int) r1, (int) r4)     // Catch:{ Exception -> 0x0081 }
            r3.setReceiveBufferSize(r4)     // Catch:{ Exception -> 0x0081 }
        L_0x0068:
            java.lang.Object r4 = r7.getValue()     // Catch:{ Exception -> 0x0081 }
            fm.liveswitch.DatagramSocket r4 = (fm.liveswitch.DatagramSocket) r4     // Catch:{ Exception -> 0x0081 }
            boolean r4 = r4.bind(r5, r6, r8)     // Catch:{ Exception -> 0x0081 }
            if (r4 != 0) goto L_0x0089
            java.lang.Object r4 = r7.getValue()     // Catch:{ Exception -> 0x007d }
            fm.liveswitch.DatagramSocket r4 = (fm.liveswitch.DatagramSocket) r4     // Catch:{ Exception -> 0x007d }
            r4.close()     // Catch:{ Exception -> 0x007d }
        L_0x007d:
            r7.setValue(r0)     // Catch:{ Exception -> 0x0081 }
            goto L_0x0089
        L_0x0081:
            r4 = move-exception
            fm.liveswitch.ILog r5 = __log
            java.lang.String r6 = "Could not create UDP socket."
            r5.error((java.lang.String) r6, (java.lang.Exception) r4)
        L_0x0089:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.IceGatherer.createUdpSocket(boolean, java.lang.String, int, fm.liveswitch.Holder, fm.liveswitch.BooleanHolder):void");
    }

    private void decrementResolvesRemaining() {
        synchronized (this.__lock) {
            this.__resolvesRemaining--;
            if (getAllSocketManagersCompleteFailedOrClosedAndAtLeastOneCompleted() && this.__resolvesRemaining == 0) {
                setState(IceGatheringState.Complete);
            }
        }
    }

    /* access modifiers changed from: private */
    public void dnsResolveCallback(String[] strArr, Object obj) {
        doDnsResolveCallback(strArr, obj);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:74:0x01c4, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x01c6, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean doDnsResolveCallback(java.lang.String[] r14, java.lang.Object r15) {
        /*
            r13 = this;
            java.lang.Object r0 = r13.__lock
            monitor-enter(r0)
            fm.liveswitch.IceGatheringState r1 = r13.getState()     // Catch:{ all -> 0x01c7 }
            fm.liveswitch.IceGatheringState r2 = fm.liveswitch.IceGatheringState.Failed     // Catch:{ all -> 0x01c7 }
            boolean r1 = fm.liveswitch.Global.equals(r1, r2)     // Catch:{ all -> 0x01c7 }
            r2 = 0
            if (r1 != 0) goto L_0x01c5
            fm.liveswitch.IceGatheringState r1 = r13.getState()     // Catch:{ all -> 0x01c7 }
            fm.liveswitch.IceGatheringState r3 = fm.liveswitch.IceGatheringState.Closing     // Catch:{ all -> 0x01c7 }
            boolean r1 = fm.liveswitch.Global.equals(r1, r3)     // Catch:{ all -> 0x01c7 }
            if (r1 != 0) goto L_0x01c5
            fm.liveswitch.IceGatheringState r1 = r13.getState()     // Catch:{ all -> 0x01c7 }
            fm.liveswitch.IceGatheringState r3 = fm.liveswitch.IceGatheringState.Closed     // Catch:{ all -> 0x01c7 }
            boolean r1 = fm.liveswitch.Global.equals(r1, r3)     // Catch:{ all -> 0x01c7 }
            if (r1 == 0) goto L_0x002a
            goto L_0x01c5
        L_0x002a:
            fm.liveswitch.IceServer r15 = (fm.liveswitch.IceServer) r15     // Catch:{ all -> 0x01c7 }
            fm.liveswitch.IceServer r15 = (fm.liveswitch.IceServer) r15     // Catch:{ all -> 0x01c7 }
            r1 = 1
            if (r14 == 0) goto L_0x0037
            int r3 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r14)     // Catch:{ all -> 0x01c7 }
            if (r3 != 0) goto L_0x0049
        L_0x0037:
            java.lang.String r3 = r15.getHost()     // Catch:{ all -> 0x01c7 }
            boolean r3 = fm.liveswitch.TransportAddress.isIPAddress(r3)     // Catch:{ all -> 0x01c7 }
            if (r3 == 0) goto L_0x0049
            java.lang.String[] r14 = new java.lang.String[r1]     // Catch:{ all -> 0x01c7 }
            java.lang.String r3 = r15.getHost()     // Catch:{ all -> 0x01c7 }
            r14[r2] = r3     // Catch:{ all -> 0x01c7 }
        L_0x0049:
            java.util.ArrayList r9 = new java.util.ArrayList     // Catch:{ all -> 0x01c7 }
            r9.<init>()     // Catch:{ all -> 0x01c7 }
            if (r14 == 0) goto L_0x00d5
            int r3 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r14)     // Catch:{ all -> 0x01c7 }
            if (r3 <= 0) goto L_0x00d5
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ all -> 0x01c7 }
            r3.<init>()     // Catch:{ all -> 0x01c7 }
            int r4 = r14.length     // Catch:{ all -> 0x01c7 }
            r5 = 0
            r6 = 0
        L_0x005e:
            if (r5 >= r4) goto L_0x00a0
            r7 = r14[r5]     // Catch:{ all -> 0x01c7 }
            boolean r8 = fm.liveswitch.TransportAddress.isIPAddress(r7)     // Catch:{ all -> 0x01c7 }
            if (r8 == 0) goto L_0x009d
            r9.add(r7)     // Catch:{ all -> 0x01c7 }
            fm.liveswitch.AddressType r6 = fm.liveswitch.LocalNetwork.getAddressType(r7)     // Catch:{ all -> 0x01c7 }
            fm.liveswitch.AddressType r8 = fm.liveswitch.AddressType.IPv4     // Catch:{ all -> 0x01c7 }
            boolean r6 = fm.liveswitch.Global.equals(r6, r8)     // Catch:{ all -> 0x01c7 }
            if (r6 == 0) goto L_0x008a
            java.lang.String r6 = "{0}:{1}"
            int r8 = r15.getPort()     // Catch:{ all -> 0x01c7 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x01c7 }
            java.lang.String r8 = fm.liveswitch.IntegerExtensions.toString(r8)     // Catch:{ all -> 0x01c7 }
        L_0x0085:
            java.lang.String r6 = fm.liveswitch.StringExtensions.format(r6, r7, r8)     // Catch:{ all -> 0x01c7 }
            goto L_0x0099
        L_0x008a:
            java.lang.String r6 = "[{0}]:{1}"
            int r8 = r15.getPort()     // Catch:{ all -> 0x01c7 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x01c7 }
            java.lang.String r8 = fm.liveswitch.IntegerExtensions.toString(r8)     // Catch:{ all -> 0x01c7 }
            goto L_0x0085
        L_0x0099:
            r3.add(r6)     // Catch:{ all -> 0x01c7 }
            r6 = 1
        L_0x009d:
            int r5 = r5 + 1
            goto L_0x005e
        L_0x00a0:
            java.lang.String r4 = r15.getHost()     // Catch:{ all -> 0x01c7 }
            r14 = r14[r2]     // Catch:{ all -> 0x01c7 }
            boolean r14 = fm.liveswitch.Global.equals(r4, r14)     // Catch:{ all -> 0x01c7 }
            if (r14 != 0) goto L_0x00c9
            fm.liveswitch.ILog r14 = __log     // Catch:{ all -> 0x01c7 }
            java.lang.String r4 = "Server address '{0}' resolved to {1}."
            java.lang.String r5 = r15.getHost()     // Catch:{ all -> 0x01c7 }
            java.lang.String r7 = ", "
            java.lang.String[] r8 = new java.lang.String[r2]     // Catch:{ all -> 0x01c7 }
            java.lang.Object[] r3 = r3.toArray(r8)     // Catch:{ all -> 0x01c7 }
            java.lang.String[] r3 = (java.lang.String[]) r3     // Catch:{ all -> 0x01c7 }
            java.lang.String r3 = fm.liveswitch.StringExtensions.join(r7, r3)     // Catch:{ all -> 0x01c7 }
            java.lang.String r3 = fm.liveswitch.StringExtensions.format(r4, r5, r3)     // Catch:{ all -> 0x01c7 }
            r14.debug(r3)     // Catch:{ all -> 0x01c7 }
        L_0x00c9:
            java.lang.String[] r14 = new java.lang.String[r2]     // Catch:{ all -> 0x01c7 }
            java.lang.Object[] r14 = r9.toArray(r14)     // Catch:{ all -> 0x01c7 }
            java.lang.String[] r14 = (java.lang.String[]) r14     // Catch:{ all -> 0x01c7 }
            r15.setIPAddresses(r14)     // Catch:{ all -> 0x01c7 }
            goto L_0x00d6
        L_0x00d5:
            r6 = 0
        L_0x00d6:
            if (r6 != 0) goto L_0x00ec
            fm.liveswitch.ILog r14 = __log     // Catch:{ all -> 0x01c7 }
            java.lang.String r2 = "Server address '{0}' could not be resolved."
            java.lang.String r15 = r15.getHost()     // Catch:{ all -> 0x01c7 }
            java.lang.String r15 = fm.liveswitch.StringExtensions.format((java.lang.String) r2, (java.lang.Object) r15)     // Catch:{ all -> 0x01c7 }
            r14.error(r15)     // Catch:{ all -> 0x01c7 }
            r13.decrementResolvesRemaining()     // Catch:{ all -> 0x01c7 }
            goto L_0x01c3
        L_0x00ec:
            boolean r14 = r15.getIsTcp()     // Catch:{ all -> 0x01c7 }
            if (r14 == 0) goto L_0x01b7
            fm.liveswitch.IceComponent r14 = r13.getComponent()     // Catch:{ all -> 0x01c7 }
            fm.liveswitch.IceComponent r3 = fm.liveswitch.IceComponent.Rtp     // Catch:{ all -> 0x01c7 }
            boolean r14 = fm.liveswitch.Global.equals(r14, r3)     // Catch:{ all -> 0x01c7 }
            if (r14 == 0) goto L_0x01b7
            java.util.HashMap r14 = new java.util.HashMap     // Catch:{ all -> 0x01c7 }
            r14.<init>()     // Catch:{ all -> 0x01c7 }
            r3 = 0
        L_0x0104:
            java.lang.String[] r4 = r15.getIPAddresses()     // Catch:{ all -> 0x01c7 }
            int r4 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r4)     // Catch:{ all -> 0x01c7 }
            if (r3 >= r4) goto L_0x012f
            java.lang.String[] r4 = r15.getIPAddresses()     // Catch:{ all -> 0x01c7 }
            int r4 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r4)     // Catch:{ all -> 0x01c7 }
            int r5 = r3 + 1
            if (r4 <= r5) goto L_0x012d
            java.util.HashMap r4 = fm.liveswitch.HashMapExtensions.getItem(r14)     // Catch:{ all -> 0x01c7 }
            java.lang.String[] r6 = r15.getIPAddresses()     // Catch:{ all -> 0x01c7 }
            r3 = r6[r3]     // Catch:{ all -> 0x01c7 }
            java.lang.String[] r6 = r15.getIPAddresses()     // Catch:{ all -> 0x01c7 }
            r6 = r6[r5]     // Catch:{ all -> 0x01c7 }
            fm.liveswitch.HashMapExtensions.set(r4, r3, r6)     // Catch:{ all -> 0x01c7 }
        L_0x012d:
            r3 = r5
            goto L_0x0104
        L_0x012f:
            r3 = 0
            r4 = r3
            r5 = 0
        L_0x0132:
            int r6 = fm.liveswitch.ArrayListExtensions.getCount(r9)     // Catch:{ all -> 0x01c7 }
            if (r6 <= 0) goto L_0x0188
            if (r5 != 0) goto L_0x0188
            java.util.ArrayList r5 = fm.liveswitch.ArrayListExtensions.getItem(r9)     // Catch:{ all -> 0x01c7 }
            java.lang.Object r5 = r5.get(r2)     // Catch:{ all -> 0x01c7 }
            r10 = r5
            java.lang.String r10 = (java.lang.String) r10     // Catch:{ all -> 0x01c7 }
            fm.liveswitch.Holder r11 = new fm.liveswitch.Holder     // Catch:{ all -> 0x01c7 }
            r11.<init>(r3)     // Catch:{ all -> 0x01c7 }
            fm.liveswitch.Holder r12 = new fm.liveswitch.Holder     // Catch:{ all -> 0x01c7 }
            r12.<init>(r4)     // Catch:{ all -> 0x01c7 }
            java.util.ArrayList r3 = fm.liveswitch.ArrayListExtensions.getItem(r9)     // Catch:{ all -> 0x01c7 }
            java.lang.Object r3 = r3.get(r2)     // Catch:{ all -> 0x01c7 }
            r5 = r3
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ all -> 0x01c7 }
            r3 = r13
            r4 = r15
            r6 = r14
            r7 = r11
            r8 = r12
            boolean r5 = r3.openSocketsAndPrepareSocketManagers(r4, r5, r6, r7, r8)     // Catch:{ all -> 0x01c7 }
            java.lang.Object r3 = r11.getValue()     // Catch:{ all -> 0x01c7 }
            fm.liveswitch.ManagedSocket[] r3 = (fm.liveswitch.ManagedSocket[]) r3     // Catch:{ all -> 0x01c7 }
            java.lang.Object r4 = r12.getValue()     // Catch:{ all -> 0x01c7 }
            fm.liveswitch.ManagedSocket[] r4 = (fm.liveswitch.ManagedSocket[]) r4     // Catch:{ all -> 0x01c7 }
            if (r5 == 0) goto L_0x0132
            r15.setIPAddress(r10)     // Catch:{ all -> 0x01c7 }
            java.util.ArrayList<fm.liveswitch.ManagedSocket> r6 = r13.__sockets     // Catch:{ all -> 0x01c7 }
            fm.liveswitch.ArrayListExtensions.addRange(r6, (T[]) r3)     // Catch:{ all -> 0x01c7 }
            fm.liveswitch.IceGatherer r6 = r13.__relatedRtcpGatherer     // Catch:{ all -> 0x01c7 }
            if (r6 == 0) goto L_0x0132
            if (r4 == 0) goto L_0x0132
            fm.liveswitch.IceSocketsServerPair r7 = new fm.liveswitch.IceSocketsServerPair     // Catch:{ all -> 0x01c7 }
            r7.<init>(r4, r15)     // Catch:{ all -> 0x01c7 }
            r6.addStreamSockets(r7)     // Catch:{ all -> 0x01c7 }
            goto L_0x0132
        L_0x0188:
            if (r5 != 0) goto L_0x01b7
            int r14 = r13.__resolvesRemaining     // Catch:{ all -> 0x01c7 }
            if (r14 != 0) goto L_0x01b7
            boolean r14 = r13.verifyAllSocketsManagersClosedOrFailed()     // Catch:{ all -> 0x01c7 }
            if (r14 == 0) goto L_0x01b7
            fm.liveswitch.Error r14 = r13.getError()     // Catch:{ all -> 0x01c7 }
            if (r14 != 0) goto L_0x01a9
            fm.liveswitch.Error r14 = new fm.liveswitch.Error     // Catch:{ all -> 0x01c7 }
            fm.liveswitch.ErrorCode r15 = fm.liveswitch.ErrorCode.SocketClosed     // Catch:{ all -> 0x01c7 }
            java.lang.Exception r1 = new java.lang.Exception     // Catch:{ all -> 0x01c7 }
            java.lang.String r3 = "Failed to open stream sockets."
            r1.<init>(r3)     // Catch:{ all -> 0x01c7 }
            r14.<init>((fm.liveswitch.ErrorCode) r15, (java.lang.Exception) r1)     // Catch:{ all -> 0x01c7 }
            goto L_0x01ad
        L_0x01a9:
            fm.liveswitch.Error r14 = r13.getError()     // Catch:{ all -> 0x01c7 }
        L_0x01ad:
            r13.setError(r14)     // Catch:{ all -> 0x01c7 }
            fm.liveswitch.IceGatheringState r14 = fm.liveswitch.IceGatheringState.Failed     // Catch:{ all -> 0x01c7 }
            r13.setState(r14)     // Catch:{ all -> 0x01c7 }
            monitor-exit(r0)     // Catch:{ all -> 0x01c7 }
            return r2
        L_0x01b7:
            fm.liveswitch.IceGatherOptions r14 = r13.__options     // Catch:{ all -> 0x01c7 }
            fm.liveswitch.IceGatherPolicy r14 = r14.getPolicy()     // Catch:{ all -> 0x01c7 }
            r13.gatherLocalNonHostCandidates(r15, r14)     // Catch:{ all -> 0x01c7 }
            r13.decrementResolvesRemaining()     // Catch:{ all -> 0x01c7 }
        L_0x01c3:
            monitor-exit(r0)     // Catch:{ all -> 0x01c7 }
            return r1
        L_0x01c5:
            monitor-exit(r0)     // Catch:{ all -> 0x01c7 }
            return r2
        L_0x01c7:
            r14 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x01c7 }
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.IceGatherer.doDnsResolveCallback(java.lang.String[], java.lang.Object):boolean");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v3, resolved type: fm.liveswitch.StreamSocket} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v33, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v5, resolved type: fm.liveswitch.DatagramSocket} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v6, resolved type: fm.liveswitch.DatagramSocket} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v36, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v9, resolved type: fm.liveswitch.StreamSocket} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v10, resolved type: fm.liveswitch.StreamSocket} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v11, resolved type: fm.liveswitch.StreamSocket} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v40, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v14, resolved type: fm.liveswitch.StreamSocket} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v15, resolved type: fm.liveswitch.StreamSocket} */
    /* JADX WARNING: type inference failed for: r1v19, types: [fm.liveswitch.IceDatagramSocketManager] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x012a A[Catch:{ Exception -> 0x01da }] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0146 A[Catch:{ Exception -> 0x01da }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean doProcessAllocationMismatchException(fm.liveswitch.IceSocketManager r23) {
        /*
            r22 = this;
            r8 = r22
            java.util.HashMap r0 = r23.getNumberOfStunRequests()
            java.lang.Object r9 = r8.__lock
            monitor-enter(r9)
            fm.liveswitch.IceGatheringState r1 = r22.getState()     // Catch:{ all -> 0x01f6 }
            fm.liveswitch.IceGatheringState r2 = fm.liveswitch.IceGatheringState.Gathering     // Catch:{ all -> 0x01f6 }
            boolean r1 = fm.liveswitch.Global.equals(r1, r2)     // Catch:{ all -> 0x01f6 }
            r10 = 0
            if (r1 != 0) goto L_0x0024
            fm.liveswitch.IceGatheringState r1 = r22.getState()     // Catch:{ all -> 0x01f6 }
            fm.liveswitch.IceGatheringState r2 = fm.liveswitch.IceGatheringState.Complete     // Catch:{ all -> 0x01f6 }
            boolean r1 = fm.liveswitch.Global.equals(r1, r2)     // Catch:{ all -> 0x01f6 }
            if (r1 != 0) goto L_0x0024
            monitor-exit(r9)     // Catch:{ all -> 0x01f6 }
            return r10
        L_0x0024:
            fm.liveswitch.IceGatherer r1 = r8.__relatedRtcpGatherer     // Catch:{ all -> 0x01f6 }
            if (r1 != 0) goto L_0x01ec
            fm.liveswitch.IceComponent r1 = r22.getComponent()     // Catch:{ all -> 0x01f6 }
            fm.liveswitch.IceComponent r2 = fm.liveswitch.IceComponent.Rtcp     // Catch:{ all -> 0x01f6 }
            boolean r1 = fm.liveswitch.Global.equals(r1, r2)     // Catch:{ all -> 0x01f6 }
            if (r1 == 0) goto L_0x0036
            goto L_0x01ec
        L_0x0036:
            int r1 = r8.__defaultPortMin     // Catch:{ all -> 0x01f6 }
            int r2 = r8.__defaultPortMax     // Catch:{ all -> 0x01f6 }
            fm.liveswitch.IceGatherOptions r3 = r22.getOptions()     // Catch:{ all -> 0x01f6 }
            if (r3 == 0) goto L_0x0062
            fm.liveswitch.IceGatherOptions r3 = r22.getOptions()     // Catch:{ all -> 0x01f6 }
            fm.liveswitch.IcePortRange r3 = r3.getPortRange()     // Catch:{ all -> 0x01f6 }
            if (r3 == 0) goto L_0x0062
            fm.liveswitch.IceGatherOptions r1 = r22.getOptions()     // Catch:{ all -> 0x01f6 }
            fm.liveswitch.IcePortRange r1 = r1.getPortRange()     // Catch:{ all -> 0x01f6 }
            int r1 = r1.getMinimum()     // Catch:{ all -> 0x01f6 }
            fm.liveswitch.IceGatherOptions r2 = r22.getOptions()     // Catch:{ all -> 0x01f6 }
            fm.liveswitch.IcePortRange r2 = r2.getPortRange()     // Catch:{ all -> 0x01f6 }
            int r2 = r2.getMaximum()     // Catch:{ all -> 0x01f6 }
        L_0x0062:
            r11 = r1
            r12 = r2
            java.lang.String r13 = r23.getLocalIpAddress()     // Catch:{ all -> 0x01f6 }
            java.util.ArrayList r14 = new java.util.ArrayList     // Catch:{ all -> 0x01f6 }
            r14.<init>()     // Catch:{ all -> 0x01f6 }
            fm.liveswitch.AddressType r1 = fm.liveswitch.LocalNetwork.getAddressType(r13)     // Catch:{ all -> 0x01f6 }
            fm.liveswitch.AddressType r2 = fm.liveswitch.AddressType.IPv6     // Catch:{ all -> 0x01f6 }
            boolean r15 = fm.liveswitch.Global.equals(r1, r2)     // Catch:{ all -> 0x01f6 }
            r1 = 0
            r6 = r1
            r7 = r6
            r2 = 0
        L_0x007b:
            r3 = 1
            if (r1 != 0) goto L_0x016d
            int r4 = r11 / 2
            int r5 = r12 / 2
            int r5 = r5 + r3
            int r3 = fm.liveswitch.LockedRandomizer.next(r4, r5)     // Catch:{ all -> 0x01f6 }
            int r16 = r3 * 2
            java.lang.Integer r3 = java.lang.Integer.valueOf(r16)     // Catch:{ all -> 0x01f6 }
            java.lang.String r3 = fm.liveswitch.IntegerExtensions.toString(r3)     // Catch:{ all -> 0x01f6 }
            boolean r3 = r14.contains(r3)     // Catch:{ all -> 0x01f6 }
            if (r3 != 0) goto L_0x0151
            fm.liveswitch.ProtocolType r1 = r23.getProtocol()     // Catch:{ all -> 0x01f6 }
            fm.liveswitch.ProtocolType r3 = fm.liveswitch.ProtocolType.Tcp     // Catch:{ all -> 0x01f6 }
            boolean r1 = fm.liveswitch.Global.equals(r1, r3)     // Catch:{ all -> 0x01f6 }
            if (r1 == 0) goto L_0x00d0
            fm.liveswitch.Holder r5 = new fm.liveswitch.Holder     // Catch:{ all -> 0x01f6 }
            r5.<init>(r6)     // Catch:{ all -> 0x01f6 }
            fm.liveswitch.BooleanHolder r6 = new fm.liveswitch.BooleanHolder     // Catch:{ all -> 0x01f6 }
            r6.<init>(r2)     // Catch:{ all -> 0x01f6 }
            r3 = 0
            r1 = r22
            r2 = r15
            r4 = r13
            r17 = r5
            r5 = r16
            r18 = r6
            r6 = r17
            r10 = r7
            r7 = r18
            r1.createTcpSocket(r2, r3, r4, r5, r6, r7)     // Catch:{ all -> 0x01f6 }
            java.lang.Object r1 = r17.getValue()     // Catch:{ all -> 0x01f6 }
            r6 = r1
            fm.liveswitch.StreamSocket r6 = (fm.liveswitch.StreamSocket) r6     // Catch:{ all -> 0x01f6 }
            boolean r1 = r18.getValue()     // Catch:{ all -> 0x01f6 }
        L_0x00cb:
            r2 = r1
            r18 = r6
            r7 = r10
            goto L_0x0128
        L_0x00d0:
            r10 = r7
            fm.liveswitch.ProtocolType r1 = r23.getProtocol()     // Catch:{ all -> 0x01f6 }
            fm.liveswitch.ProtocolType r3 = fm.liveswitch.ProtocolType.Tls     // Catch:{ all -> 0x01f6 }
            boolean r1 = fm.liveswitch.Global.equals(r1, r3)     // Catch:{ all -> 0x01f6 }
            if (r1 == 0) goto L_0x0104
            fm.liveswitch.Holder r7 = new fm.liveswitch.Holder     // Catch:{ all -> 0x01f6 }
            r7.<init>(r6)     // Catch:{ all -> 0x01f6 }
            fm.liveswitch.BooleanHolder r6 = new fm.liveswitch.BooleanHolder     // Catch:{ all -> 0x01f6 }
            r6.<init>(r2)     // Catch:{ all -> 0x01f6 }
            r3 = 1
            r1 = r22
            r2 = r15
            r4 = r13
            r5 = r16
            r17 = r6
            r6 = r7
            r18 = r7
            r7 = r17
            r1.createTcpSocket(r2, r3, r4, r5, r6, r7)     // Catch:{ all -> 0x01f6 }
            java.lang.Object r1 = r18.getValue()     // Catch:{ all -> 0x01f6 }
            r6 = r1
            fm.liveswitch.StreamSocket r6 = (fm.liveswitch.StreamSocket) r6     // Catch:{ all -> 0x01f6 }
            boolean r1 = r17.getValue()     // Catch:{ all -> 0x01f6 }
            goto L_0x00cb
        L_0x0104:
            fm.liveswitch.Holder r7 = new fm.liveswitch.Holder     // Catch:{ all -> 0x01f6 }
            r7.<init>(r10)     // Catch:{ all -> 0x01f6 }
            fm.liveswitch.BooleanHolder r10 = new fm.liveswitch.BooleanHolder     // Catch:{ all -> 0x01f6 }
            r10.<init>(r2)     // Catch:{ all -> 0x01f6 }
            r1 = r22
            r2 = r15
            r3 = r13
            r4 = r16
            r5 = r7
            r18 = r6
            r6 = r10
            r1.createUdpSocket(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x01f6 }
            java.lang.Object r1 = r7.getValue()     // Catch:{ all -> 0x01f6 }
            r7 = r1
            fm.liveswitch.DatagramSocket r7 = (fm.liveswitch.DatagramSocket) r7     // Catch:{ all -> 0x01f6 }
            boolean r1 = r10.getValue()     // Catch:{ all -> 0x01f6 }
            r2 = r1
            r6 = r7
        L_0x0128:
            if (r6 != 0) goto L_0x0146
            if (r2 != 0) goto L_0x013a
            fm.liveswitch.ILog r0 = __log     // Catch:{ all -> 0x01f6 }
            java.lang.String r1 = "Could not gather new host candidates from {0}. Socket error. TURN will not be available on this interface."
            java.lang.String r1 = fm.liveswitch.StringExtensions.format((java.lang.String) r1, (java.lang.Object) r13)     // Catch:{ all -> 0x01f6 }
            r0.warn(r1)     // Catch:{ all -> 0x01f6 }
            monitor-exit(r9)     // Catch:{ all -> 0x01f6 }
            r0 = 0
            return r0
        L_0x013a:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r16)     // Catch:{ all -> 0x01f6 }
            java.lang.String r1 = fm.liveswitch.IntegerExtensions.toString(r1)     // Catch:{ all -> 0x01f6 }
            r14.add(r1)     // Catch:{ all -> 0x01f6 }
            goto L_0x014d
        L_0x0146:
            long r3 = r23.getAdapterSpeed()     // Catch:{ all -> 0x01f6 }
            r6.setAdapterSpeed(r3)     // Catch:{ all -> 0x01f6 }
        L_0x014d:
            r1 = r6
            r6 = r18
            goto L_0x0154
        L_0x0151:
            r18 = r6
            r10 = r7
        L_0x0154:
            int r3 = fm.liveswitch.ArrayListExtensions.getCount(r14)     // Catch:{ all -> 0x01f6 }
            int r4 = r12 - r11
            if (r3 != r4) goto L_0x016a
            fm.liveswitch.ILog r0 = __log     // Catch:{ all -> 0x01f6 }
            java.lang.String r1 = "Could not gather new host candidates from {0}. All ports are in use. TURN will not be available on this interface."
            java.lang.String r1 = fm.liveswitch.StringExtensions.format((java.lang.String) r1, (java.lang.Object) r13)     // Catch:{ all -> 0x01f6 }
            r0.warn(r1)     // Catch:{ all -> 0x01f6 }
            monitor-exit(r9)     // Catch:{ all -> 0x01f6 }
            r0 = 0
            return r0
        L_0x016a:
            r10 = 0
            goto L_0x007b
        L_0x016d:
            r18 = r6
            r10 = r7
            fm.liveswitch.ProtocolType r1 = r23.getProtocol()     // Catch:{ Exception -> 0x01da }
            fm.liveswitch.ProtocolType r2 = fm.liveswitch.ProtocolType.Udp     // Catch:{ Exception -> 0x01da }
            boolean r1 = fm.liveswitch.Global.equals(r1, r2)     // Catch:{ Exception -> 0x01da }
            if (r1 == 0) goto L_0x0186
            fm.liveswitch.IceDatagramSocketManager r1 = new fm.liveswitch.IceDatagramSocketManager     // Catch:{ Exception -> 0x01da }
            java.lang.Object r2 = r8.__lock     // Catch:{ Exception -> 0x01da }
            fm.liveswitch.IceTransactionManager r4 = r8.__transactionManager     // Catch:{ Exception -> 0x01da }
            r1.<init>(r2, r10, r4)     // Catch:{ Exception -> 0x01da }
            goto L_0x01a3
        L_0x0186:
            r1 = r23
            fm.liveswitch.IceStreamSocketManager r1 = (fm.liveswitch.IceStreamSocketManager) r1     // Catch:{ Exception -> 0x01da }
            fm.liveswitch.IceStreamSocketManager r1 = (fm.liveswitch.IceStreamSocketManager) r1     // Catch:{ Exception -> 0x01da }
            fm.liveswitch.IceServer r20 = r1.getServer()     // Catch:{ Exception -> 0x01da }
            fm.liveswitch.IceStreamSocketManager r1 = new fm.liveswitch.IceStreamSocketManager     // Catch:{ Exception -> 0x01da }
            java.lang.Object r2 = r8.__lock     // Catch:{ Exception -> 0x01da }
            fm.liveswitch.IceTransactionManager r4 = r8.__transactionManager     // Catch:{ Exception -> 0x01da }
            fm.liveswitch.Scheduler r5 = r8.__scheduler     // Catch:{ Exception -> 0x01da }
            r16 = r1
            r17 = r2
            r19 = r4
            r21 = r5
            r16.<init>(r17, r18, r19, r20, r21)     // Catch:{ Exception -> 0x01da }
        L_0x01a3:
            fm.liveswitch.IceGatherer$4 r2 = new fm.liveswitch.IceGatherer$4     // Catch:{ Exception -> 0x01da }
            r2.<init>()     // Catch:{ Exception -> 0x01da }
            r1.setOnLocalCandidate(r2)     // Catch:{ Exception -> 0x01da }
            fm.liveswitch.IceGatherer$5 r2 = new fm.liveswitch.IceGatherer$5     // Catch:{ Exception -> 0x01da }
            r2.<init>()     // Catch:{ Exception -> 0x01da }
            r1.setOnStateChange(r2)     // Catch:{ Exception -> 0x01da }
            fm.liveswitch.IceGatherer$6 r2 = new fm.liveswitch.IceGatherer$6     // Catch:{ Exception -> 0x01da }
            r2.<init>()     // Catch:{ Exception -> 0x01da }
            r1.setOnStunRequest(r2)     // Catch:{ Exception -> 0x01da }
            fm.liveswitch.IceGatherer$7 r2 = new fm.liveswitch.IceGatherer$7     // Catch:{ Exception -> 0x01da }
            r2.<init>()     // Catch:{ Exception -> 0x01da }
            r1.setOnIncomingData(r2)     // Catch:{ Exception -> 0x01da }
            fm.liveswitch.IceGatherer$8 r2 = new fm.liveswitch.IceGatherer$8     // Catch:{ Exception -> 0x01da }
            r2.<init>()     // Catch:{ Exception -> 0x01da }
            r1.setOnAllocationMismatchException(r2)     // Catch:{ Exception -> 0x01da }
            r1.setNumberOfStunRequests(r0)     // Catch:{ Exception -> 0x01da }
            r8.addNewSocketManager(r1)     // Catch:{ Exception -> 0x01da }
            r8.tryStartSocketManager(r1)     // Catch:{ Exception -> 0x01da }
            fm.liveswitch.IceGatheringState r0 = fm.liveswitch.IceGatheringState.Gathering     // Catch:{ Exception -> 0x01da }
            r8.setState(r0)     // Catch:{ Exception -> 0x01da }
            goto L_0x01ea
        L_0x01da:
            r0 = move-exception
            fm.liveswitch.ILog r1 = __log     // Catch:{ all -> 0x01f6 }
            java.lang.String r2 = "Cannot start socket manager for a new socket. TURN relay will not be available. {0}"
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x01f6 }
            java.lang.String r0 = fm.liveswitch.StringExtensions.format((java.lang.String) r2, (java.lang.Object) r0)     // Catch:{ all -> 0x01f6 }
            r1.error(r0)     // Catch:{ all -> 0x01f6 }
        L_0x01ea:
            monitor-exit(r9)     // Catch:{ all -> 0x01f6 }
            return r3
        L_0x01ec:
            fm.liveswitch.ILog r0 = __log     // Catch:{ all -> 0x01f6 }
            java.lang.String r1 = "Handling allocation mismatch exceptions is not supported for non-multiplexed cases. TURN relay will not be available."
            r0.error(r1)     // Catch:{ all -> 0x01f6 }
            monitor-exit(r9)     // Catch:{ all -> 0x01f6 }
            r0 = 0
            return r0
        L_0x01f6:
            r0 = move-exception
            monitor-exit(r9)     // Catch:{ all -> 0x01f6 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.IceGatherer.doProcessAllocationMismatchException(fm.liveswitch.IceSocketManager):boolean");
    }

    private void finaliseClosing() {
        synchronized (this.__lock) {
            this.__socketManagers.clear();
        }
        setState(IceGatheringState.Closed);
    }

    public IceCandidate findMatchingLocalCandidate(String str, int i) {
        for (IceCandidate iceCandidate : getLocalCandidates()) {
            if (Global.equals(iceCandidate.getIPAddress(), str) && iceCandidate.getPort() == i) {
                return iceCandidate;
            }
        }
        for (IceCandidate iceCandidate2 : getLocalCandidates()) {
            if (Global.equals(iceCandidate2.getRelatedIPAddress(), str) && iceCandidate2.getRelatedPort() == i) {
                return iceCandidate2;
            }
        }
        return null;
    }

    private void gatherLocalNonHostCandidates(IceServer iceServer, IceGatherPolicy iceGatherPolicy) {
        synchronized (this.__lock) {
            ArrayList arrayList = new ArrayList();
            for (IceSocketManager add : HashMapExtensions.getValues(this.__socketManagers)) {
                arrayList.add(add);
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                IceSocketManager iceSocketManager = (IceSocketManager) it.next();
                if (Global.equals(iceSocketManager.getProtocol(), ProtocolType.Udp) && iceServer.getIsUdp() && !iceServer.getIsSecure()) {
                    ((IceDatagramSocketManager) iceSocketManager).gatherLocalNonHostCandidates(iceServer, iceGatherPolicy);
                }
            }
        }
    }

    private boolean getAllSocketManagersCompleteFailedOrClosedAndAtLeastOneCompleted() {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        synchronized (this.__lock) {
            Iterator<IceSocketManager> it = HashMapExtensions.getValues(this.__socketManagers).iterator();
            i = 0;
            i2 = 0;
            i3 = 0;
            i4 = 0;
            i5 = 0;
            i6 = 0;
            i7 = 0;
            while (it.hasNext()) {
                IceSocketManager next = it.next();
                i2++;
                i += Global.equals(next.getState(), IceGatheringState.Complete) ? 1 : 0;
                i4 += Global.equals(next.getState(), IceGatheringState.Closed) ? 1 : 0;
                i5 += Global.equals(next.getState(), IceGatheringState.New) ? 1 : 0;
                i7 += Global.equals(next.getState(), IceGatheringState.Closing) ? 1 : 0;
                String str = StringExtensions.empty;
                Iterator<IceSocketManager> it2 = it;
                if (Global.equals(next.getProtocol(), ProtocolType.Tcp)) {
                    str = StringExtensions.concat("/ ", ((IceStreamSocketManager) next).getServer().getUrl());
                }
                if (Global.equals(next.getState(), IceGatheringState.Failed)) {
                    i3++;
                    arrayList.add(StringExtensions.concat(next.getProtocol().toString(), str));
                } else if (Global.equals(next.getState(), IceGatheringState.Gathering)) {
                    i6++;
                    arrayList2.add(StringExtensions.concat(next.getProtocol().toString(), str));
                    arrayList3.add(next.getLocalIpAddress() == null ? "NoIp" : next.getLocalIpAddress());
                }
                it = it2;
            }
        }
        boolean z = i > 0;
        boolean z2 = (i3 + i4) + i == i2;
        if (this._verboseLogging) {
            __log.debug(StringExtensions.format("For gatherer {0}, there are {1} socket managers in total: {2} new, {3} gathering, {4} complete, {5} closing, {6} closed, {7} failed", new Object[]{getId(), IntegerExtensions.toString(Integer.valueOf(i2)), IntegerExtensions.toString(Integer.valueOf(i5)), IntegerExtensions.toString(Integer.valueOf(i6)), IntegerExtensions.toString(Integer.valueOf(i)), IntegerExtensions.toString(Integer.valueOf(i7)), IntegerExtensions.toString(Integer.valueOf(i4)), IntegerExtensions.toString(Integer.valueOf(i3))}));
            __log.debug(StringExtensions.concat("Still gathering on: ", ArrayListExtensions.getCount(arrayList2) == 0 ? StringExtensions.concat(StringExtensions.join(", ", (String[]) arrayList2.toArray(new String[0])), "; with local ips: ", StringExtensions.join(", ", (String[]) arrayList3.toArray(new String[0]))) : " none."));
            __log.debug(StringExtensions.concat("Failed: ", ArrayListExtensions.getCount(arrayList) == 0 ? StringExtensions.join(", ", (String[]) arrayList.toArray(new String[0])) : " none."));
        }
        return z && z2;
    }

    public boolean getClosingShouldNotTriggerGlobalNonGracefulShutdown() {
        return this._closingShouldNotTriggerGlobalNonGracefulShutdown;
    }

    public IceComponent getComponent() {
        return this.__component;
    }

    public IFunction1<DatagramSocketCreateArgs, DatagramSocket> getCreateDatagramSocket() {
        return this._createDatagramSocket;
    }

    public IFunction1<StreamSocketCreateArgs, StreamSocket> getCreateStreamSocket() {
        return this._createStreamSocket;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0031, code lost:
        if (fm.liveswitch.Global.equals(r6, fm.liveswitch.AddressType.IPv4) != false) goto L_0x007c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public fm.liveswitch.IceCandidate getDefaultLocalCandidate() {
        /*
            r9 = this;
            fm.liveswitch.IceCandidate r0 = r9.__defaultLocalCandidate
            if (r0 != 0) goto L_0x008a
            fm.liveswitch.IceCandidate[] r0 = r9.getLocalCandidates()
            int r1 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r0)
            r2 = 0
            if (r1 <= 0) goto L_0x0084
            int r1 = r0.length
            r3 = 0
            r3 = r2
            r4 = 0
        L_0x0013:
            if (r4 >= r1) goto L_0x0085
            r5 = r0[r4]
            java.lang.String r6 = r5.getIPAddress()
            fm.liveswitch.AddressType r6 = fm.liveswitch.LocalNetwork.getAddressType(r6)
            fm.liveswitch.CandidateType r7 = r5.getType()
            fm.liveswitch.CandidateType r8 = fm.liveswitch.CandidateType.ServerReflexive
            boolean r7 = fm.liveswitch.Global.equals(r7, r8)
            if (r7 == 0) goto L_0x0034
            fm.liveswitch.AddressType r7 = fm.liveswitch.AddressType.IPv4
            boolean r6 = fm.liveswitch.Global.equals(r6, r7)
            if (r6 == 0) goto L_0x0080
            goto L_0x007c
        L_0x0034:
            fm.liveswitch.CandidateType r7 = r5.getType()
            fm.liveswitch.CandidateType r8 = fm.liveswitch.CandidateType.Relayed
            boolean r7 = fm.liveswitch.Global.equals(r7, r8)
            if (r7 == 0) goto L_0x0066
            fm.liveswitch.AddressType r7 = fm.liveswitch.AddressType.IPv4
            boolean r6 = fm.liveswitch.Global.equals(r6, r7)
            if (r6 == 0) goto L_0x0057
            if (r2 == 0) goto L_0x007c
            fm.liveswitch.CandidateType r6 = r2.getType()
            fm.liveswitch.CandidateType r7 = fm.liveswitch.CandidateType.Host
            boolean r6 = fm.liveswitch.Global.equals(r6, r7)
            if (r6 == 0) goto L_0x0081
            goto L_0x007c
        L_0x0057:
            if (r3 == 0) goto L_0x0080
            fm.liveswitch.CandidateType r6 = r3.getType()
            fm.liveswitch.CandidateType r7 = fm.liveswitch.CandidateType.Host
            boolean r6 = fm.liveswitch.Global.equals(r6, r7)
            if (r6 == 0) goto L_0x0081
            goto L_0x0080
        L_0x0066:
            fm.liveswitch.CandidateType r7 = r5.getType()
            fm.liveswitch.CandidateType r8 = fm.liveswitch.CandidateType.Host
            boolean r7 = fm.liveswitch.Global.equals(r7, r8)
            if (r7 == 0) goto L_0x0081
            fm.liveswitch.AddressType r7 = fm.liveswitch.AddressType.IPv4
            boolean r6 = fm.liveswitch.Global.equals(r6, r7)
            if (r6 == 0) goto L_0x007e
            if (r2 != 0) goto L_0x007e
        L_0x007c:
            r2 = r5
            goto L_0x0081
        L_0x007e:
            if (r3 != 0) goto L_0x0081
        L_0x0080:
            r3 = r5
        L_0x0081:
            int r4 = r4 + 1
            goto L_0x0013
        L_0x0084:
            r3 = r2
        L_0x0085:
            if (r2 != 0) goto L_0x0088
            r2 = r3
        L_0x0088:
            r9.__defaultLocalCandidate = r2
        L_0x008a:
            fm.liveswitch.IceCandidate r0 = r9.__defaultLocalCandidate
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.IceGatherer.getDefaultLocalCandidate():fm.liveswitch.IceCandidate");
    }

    public Error getError() {
        return this._error;
    }

    public String getId() {
        return this._id;
    }

    public IceCandidate[] getLocalCandidates() {
        ArrayList arrayList = new ArrayList();
        synchronized (this.__lock) {
            for (IceSocketManager iceSocketManager : HashMapExtensions.getValues(this.__socketManagers)) {
                ArrayListExtensions.addRange(arrayList, HashMapExtensions.getValues(iceSocketManager._localCandidates));
            }
        }
        return (IceCandidate[]) arrayList.toArray(new IceCandidate[0]);
    }

    public IceParameters getLocalParameters() {
        return this._localParameters;
    }

    private IceSocketManager getManager(IceLocalReflexiveCandidate iceLocalReflexiveCandidate) {
        IceSocketManager iceSocketManager;
        IceCandidate localHostBaseCandidate = iceLocalReflexiveCandidate.getLocalHostBaseCandidate();
        String prepareSocketManagerKey = prepareSocketManagerKey(localHostBaseCandidate.getRelayProtocol(), localHostBaseCandidate.getIPAddress(), localHostBaseCandidate.getPort());
        synchronized (this.__lock) {
            Holder holder = new Holder(null);
            HashMapExtensions.tryGetValue(this.__socketManagers, prepareSocketManagerKey, holder);
            iceSocketManager = (IceSocketManager) holder.getValue();
        }
        return iceSocketManager;
    }

    /* access modifiers changed from: package-private */
    public IAction3<Message, IceCandidate, TransportAddress> getOnStunRequest() {
        return this._onStunRequest;
    }

    public IceGatherOptions getOptions() {
        return this.__options;
    }

    public int getReceiveBufferSize() {
        return this._receiveBufferSize;
    }

    /* access modifiers changed from: package-private */
    public IceGatherer getRelatedRtcpGatherer() {
        return this.__relatedRtcpGatherer;
    }

    public int getSendBufferSize() {
        return this._sendBufferSize;
    }

    public IceGatheringState getState() {
        return this.__state;
    }

    public int getStreamIndex() {
        return this._streamIndex;
    }

    /* access modifiers changed from: package-private */
    public IceTransactionManager getTransactionManager() {
        return this.__transactionManager;
    }

    public IceGatherer(Object obj, Scheduler scheduler, IceGatherOptions iceGatherOptions, IceParameters iceParameters) {
        this(obj, scheduler, iceGatherOptions, iceParameters, IceComponent.Rtp);
    }

    public IceGatherer(Object obj, Scheduler scheduler, IceGatherOptions iceGatherOptions, IceParameters iceParameters, IceComponent iceComponent) {
        this.__onIncomingApplicationData = new ArrayList();
        this.__onLocalCandidate = new ArrayList();
        this.__onStateChange = new ArrayList();
        this._onIncomingApplicationData = null;
        this._onLocalCandidate = null;
        this._onStateChange = null;
        this._verboseLogging = false;
        this.__defaultPortMin = SctpParameterType.ForwardTsnSupportedParameter;
        this.__defaultPortMax = DimensionsKt.MAXDPI;
        this.__sockets = new ArrayList<>();
        this.__socketManagers = new HashMap<>();
        this.__defaultLocalCandidate = null;
        this.__resolvesRemaining = 0;
        this.__state = IceGatheringState.New;
        this.__adapterSpeedTable = new HashMap<>();
        this.__usedLocalPreferences = new HashMap<>();
        this.__localPreferencesLock = new Object();
        this.__lock = obj;
        this.__scheduler = scheduler;
        setId(Utility.generateId());
        if (iceGatherOptions != null) {
            this.__component = iceComponent;
            this.__options = iceGatherOptions;
            setCreateDatagramSocket(iceGatherOptions.getCreateDatagramSocket());
            setCreateStreamSocket(iceGatherOptions.getCreateStreamSocket());
            setStreamIndex(iceGatherOptions.getStreamIndex());
            if (iceParameters != null) {
                setLocalParameters(iceParameters);
                setSendBufferSize(-1);
                setReceiveBufferSize(-1);
                if (this.__options.getServers() != null) {
                    ArrayList arrayList = new ArrayList();
                    for (IceServer iceServer : this.__options.getServers()) {
                        if (!iceServer.getIsStun() || !iceServer.getIsSecure()) {
                            arrayList.add(iceServer);
                        } else {
                            Log.error(StringExtensions.format("Server {0} will not be used to gather candidates because secure STUN (servers with stuns schema) is not supported.", (Object) iceServer.getUrl()));
                        }
                    }
                    this.__options.setServers((IceServer[]) arrayList.toArray(new IceServer[0]));
                    this.__resolvesRemaining = ArrayExtensions.getLength((Object[]) this.__options.getServers());
                }
                if (Global.equals(iceComponent, IceComponent.Rtcp)) {
                    this.__rtcpStreamSocketsAndServers = new ArrayList<>();
                    return;
                }
                return;
            }
            throw new RuntimeException(new Exception("Local ice pramaters should not be null."));
        }
        throw new RuntimeException(new Exception("Gatherer: Null options argument."));
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x019f  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x02aa  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x02d2  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0312  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void openSockets(fm.liveswitch.ProtocolType r34, fm.liveswitch.Holder<fm.liveswitch.ManagedSocket[]> r35, fm.liveswitch.Holder<fm.liveswitch.ManagedSocket[]> r36) {
        /*
            r33 = this;
            r7 = r33
            r8 = r34
            int r0 = r7.__defaultPortMin
            int r1 = r7.__defaultPortMax
            fm.liveswitch.IceGatherOptions r2 = r33.getOptions()
            if (r2 == 0) goto L_0x0030
            fm.liveswitch.IceGatherOptions r2 = r33.getOptions()
            fm.liveswitch.IcePortRange r2 = r2.getPortRange()
            if (r2 == 0) goto L_0x0030
            fm.liveswitch.IceGatherOptions r0 = r33.getOptions()
            fm.liveswitch.IcePortRange r0 = r0.getPortRange()
            int r0 = r0.getMinimum()
            fm.liveswitch.IceGatherOptions r1 = r33.getOptions()
            fm.liveswitch.IcePortRange r1 = r1.getPortRange()
            int r1 = r1.getMaximum()
        L_0x0030:
            r9 = r0
            r10 = r1
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
            java.util.ArrayList r12 = new java.util.ArrayList
            r12.<init>()
            fm.liveswitch.LocalAddress[] r13 = r7.__localAddresses
            int r14 = r13.length
            r6 = 0
        L_0x0040:
            if (r6 >= r14) goto L_0x0357
            r16 = r13[r6]
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            java.lang.String r0 = r16.getIPAddress()
            fm.liveswitch.AddressType r0 = fm.liveswitch.LocalNetwork.getAddressType(r0)
            fm.liveswitch.AddressType r1 = fm.liveswitch.AddressType.IPv6
            boolean r17 = fm.liveswitch.Global.equals(r0, r1)
            r4 = 0
            r0 = r4
            r1 = 0
            r18 = 0
            r19 = 0
        L_0x005e:
            if (r0 != 0) goto L_0x034b
            if (r18 != 0) goto L_0x034b
            if (r19 != 0) goto L_0x034b
            int r2 = r9 / 2
            int r3 = r10 / 2
            r20 = 1
            int r3 = r3 + 1
            int r2 = fm.liveswitch.LockedRandomizer.next(r2, r3)
            r21 = 2
            int r3 = r2 * 2
            java.lang.Integer r2 = java.lang.Integer.valueOf(r3)
            java.lang.String r2 = fm.liveswitch.IntegerExtensions.toString(r2)
            boolean r2 = r5.contains(r2)
            if (r2 != 0) goto L_0x031c
            fm.liveswitch.ProtocolType r0 = fm.liveswitch.ProtocolType.Tcp
            boolean r0 = fm.liveswitch.Global.equals(r8, r0)
            if (r0 == 0) goto L_0x00c2
            fm.liveswitch.Holder r2 = new fm.liveswitch.Holder
            r2.<init>(r4)
            fm.liveswitch.BooleanHolder r0 = new fm.liveswitch.BooleanHolder
            r0.<init>(r1)
            r22 = 0
            java.lang.String r23 = r16.getIPAddress()
            r24 = r0
            r0 = r33
            r1 = r17
            r25 = r2
            r2 = r22
            r22 = r3
            r3 = r23
            r15 = r4
            r4 = r22
            r26 = r5
            r5 = r25
            r27 = r6
            r6 = r24
            r0.createTcpSocket(r1, r2, r3, r4, r5, r6)
            java.lang.Object r0 = r25.getValue()
            fm.liveswitch.StreamSocket r0 = (fm.liveswitch.StreamSocket) r0
            boolean r1 = r24.getValue()
        L_0x00c0:
            r6 = r0
            goto L_0x0120
        L_0x00c2:
            r22 = r3
            r15 = r4
            r26 = r5
            r27 = r6
            fm.liveswitch.ProtocolType r0 = fm.liveswitch.ProtocolType.Tls
            boolean r0 = fm.liveswitch.Global.equals(r8, r0)
            if (r0 == 0) goto L_0x00fb
            fm.liveswitch.Holder r6 = new fm.liveswitch.Holder
            r6.<init>(r15)
            fm.liveswitch.BooleanHolder r5 = new fm.liveswitch.BooleanHolder
            r5.<init>(r1)
            r2 = 1
            java.lang.String r3 = r16.getIPAddress()
            r0 = r33
            r1 = r17
            r4 = r22
            r24 = r5
            r5 = r6
            r25 = r6
            r6 = r24
            r0.createTcpSocket(r1, r2, r3, r4, r5, r6)
            java.lang.Object r0 = r25.getValue()
            fm.liveswitch.StreamSocket r0 = (fm.liveswitch.StreamSocket) r0
            boolean r1 = r24.getValue()
            goto L_0x00c0
        L_0x00fb:
            fm.liveswitch.Holder r6 = new fm.liveswitch.Holder
            r6.<init>(r15)
            fm.liveswitch.BooleanHolder r5 = new fm.liveswitch.BooleanHolder
            r5.<init>(r1)
            java.lang.String r2 = r16.getIPAddress()
            r0 = r33
            r1 = r17
            r3 = r22
            r4 = r6
            r24 = r5
            r0.createUdpSocket(r1, r2, r3, r4, r5)
            java.lang.Object r0 = r6.getValue()
            fm.liveswitch.DatagramSocket r0 = (fm.liveswitch.DatagramSocket) r0
            boolean r1 = r24.getValue()
            goto L_0x00c0
        L_0x0120:
            fm.liveswitch.ProtocolType r0 = fm.liveswitch.ProtocolType.Udp
            boolean r0 = fm.liveswitch.Global.equals(r8, r0)
            if (r0 == 0) goto L_0x012b
            java.lang.String r0 = "UDP"
            goto L_0x012d
        L_0x012b:
            java.lang.String r0 = "TCP"
        L_0x012d:
            r5 = r0
            if (r6 != 0) goto L_0x0154
            if (r1 == 0) goto L_0x0140
            java.lang.Integer r0 = java.lang.Integer.valueOf(r22)
            java.lang.String r0 = fm.liveswitch.IntegerExtensions.toString(r0)
            r4 = r26
            r4.add(r0)
            goto L_0x0156
        L_0x0140:
            r4 = r26
            fm.liveswitch.ILog r0 = __log
            java.lang.String r2 = r16.toString()
            java.lang.String r3 = "Could not gather host candidates from {0}. {1} socket error."
            java.lang.String r2 = fm.liveswitch.StringExtensions.format(r3, r2, r5)
            r0.warn(r2)
            r18 = 1
            goto L_0x0156
        L_0x0154:
            r4 = r26
        L_0x0156:
            if (r6 == 0) goto L_0x019b
            int r0 = r6.getLocalPort()
            r3 = r22
            if (r3 == r0) goto L_0x019d
            fm.liveswitch.ILog r0 = __log
            r2 = 4
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.String r18 = r16.toString()
            r22 = 0
            r2[r22] = r18
            r2[r20] = r5
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            java.lang.String r3 = fm.liveswitch.IntegerExtensions.toString(r3)
            r2[r21] = r3
            r3 = 3
            int r5 = r6.getLocalPort()
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            java.lang.String r5 = fm.liveswitch.IntegerExtensions.toString(r5)
            r2[r3] = r5
            java.lang.String r3 = "Could not gather host candidates from {0}. {1} socket error. Requested port {2} but got a binding on port {3}."
            java.lang.String r2 = fm.liveswitch.StringExtensions.format((java.lang.String) r3, (java.lang.Object[]) r2)
            r0.warn(r2)
            r2 = r4
            r0 = r6
            r25 = r13
            r24 = r15
        L_0x0197:
            r18 = 1
            goto L_0x0323
        L_0x019b:
            r3 = r22
        L_0x019d:
            if (r6 == 0) goto L_0x0312
            java.lang.String r2 = "]:"
            java.lang.String r0 = "["
            java.lang.String r15 = ":"
            r26 = r4
            if (r17 == 0) goto L_0x01c0
            java.lang.String r4 = r6.getLocalIPAddress()
            int r24 = r6.getLocalPort()
            java.lang.Integer r24 = java.lang.Integer.valueOf(r24)
            r25 = r13
            java.lang.String r13 = fm.liveswitch.IntegerExtensions.toString(r24)
            java.lang.String r4 = fm.liveswitch.StringExtensions.concat(r0, r4, r2, r13)
            goto L_0x01d6
        L_0x01c0:
            r25 = r13
            java.lang.String r4 = r6.getLocalIPAddress()
            int r13 = r6.getLocalPort()
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)
            java.lang.String r13 = fm.liveswitch.IntegerExtensions.toString(r13)
            java.lang.String r4 = fm.liveswitch.StringExtensions.concat(r4, r15, r13)
        L_0x01d6:
            fm.liveswitch.ILog r13 = __log
            r24 = r0
            java.lang.String r0 = r33.getId()
            r28 = r2
            java.lang.String r2 = "Opened {0} socket: {1} in gatherer {2}."
            java.lang.String r0 = fm.liveswitch.StringExtensions.format(r2, r5, r4, r0)
            r13.debug(r0)
            r13 = r5
            long r4 = r16.getAdapterSpeed()
            r6.setAdapterSpeed(r4)
            r11.add(r6)
            fm.liveswitch.IceGatherer r0 = r7.__relatedRtcpGatherer
            if (r0 == 0) goto L_0x030b
            fm.liveswitch.ProtocolType r0 = fm.liveswitch.ProtocolType.Tcp
            boolean r0 = fm.liveswitch.Global.equals(r8, r0)
            if (r0 == 0) goto L_0x0240
            fm.liveswitch.Holder r5 = new fm.liveswitch.Holder
            r0 = 0
            r5.<init>(r0)
            fm.liveswitch.BooleanHolder r4 = new fm.liveswitch.BooleanHolder
            r4.<init>(r1)
            r2 = 0
            java.lang.String r29 = r16.getIPAddress()
            int r30 = r3 + 1
            r1 = r24
            r0 = r33
            r7 = r1
            r1 = r17
            r24 = r13
            r13 = r28
            r28 = r3
            r3 = r29
            r31 = r26
            r26 = r4
            r4 = r30
            r32 = r24
            r24 = r5
            r29 = r6
            r6 = r26
            r0.createTcpSocket(r1, r2, r3, r4, r5, r6)
            java.lang.Object r0 = r24.getValue()
            fm.liveswitch.StreamSocket r0 = (fm.liveswitch.StreamSocket) r0
            boolean r1 = r26.getValue()
        L_0x023c:
            r24 = 0
            goto L_0x02a8
        L_0x0240:
            r29 = r6
            r32 = r13
            r7 = r24
            r31 = r26
            r13 = r28
            r28 = r3
            fm.liveswitch.ProtocolType r0 = fm.liveswitch.ProtocolType.Tls
            boolean r0 = fm.liveswitch.Global.equals(r8, r0)
            if (r0 == 0) goto L_0x027f
            fm.liveswitch.Holder r6 = new fm.liveswitch.Holder
            r0 = 0
            r6.<init>(r0)
            fm.liveswitch.BooleanHolder r5 = new fm.liveswitch.BooleanHolder
            r5.<init>(r1)
            r2 = 1
            java.lang.String r3 = r16.getIPAddress()
            int r4 = r28 + 1
            r0 = r33
            r1 = r17
            r24 = r5
            r5 = r6
            r26 = r6
            r6 = r24
            r0.createTcpSocket(r1, r2, r3, r4, r5, r6)
            java.lang.Object r0 = r26.getValue()
            fm.liveswitch.StreamSocket r0 = (fm.liveswitch.StreamSocket) r0
            boolean r1 = r24.getValue()
            goto L_0x023c
        L_0x027f:
            fm.liveswitch.Holder r6 = new fm.liveswitch.Holder
            r5 = 0
            r6.<init>(r5)
            fm.liveswitch.BooleanHolder r4 = new fm.liveswitch.BooleanHolder
            r4.<init>(r1)
            java.lang.String r2 = r16.getIPAddress()
            int r3 = r28 + 1
            r0 = r33
            r1 = r17
            r22 = r4
            r4 = r6
            r24 = r5
            r5 = r22
            r0.createUdpSocket(r1, r2, r3, r4, r5)
            java.lang.Object r0 = r6.getValue()
            fm.liveswitch.DatagramSocket r0 = (fm.liveswitch.DatagramSocket) r0
            boolean r1 = r22.getValue()
        L_0x02a8:
            if (r0 != 0) goto L_0x02d2
            if (r1 == 0) goto L_0x02bd
            java.lang.Integer r0 = java.lang.Integer.valueOf(r28)
            java.lang.String r0 = fm.liveswitch.IntegerExtensions.toString(r0)
            r2 = r31
            r2.add(r0)
            r29.close()
            goto L_0x0319
        L_0x02bd:
            r2 = r31
            fm.liveswitch.ILog r0 = __log
            java.lang.String r3 = r16.toString()
            java.lang.String r4 = "Could not gather host candidates from {0}. Socket error."
            java.lang.String r3 = fm.liveswitch.StringExtensions.format((java.lang.String) r4, (java.lang.Object) r3)
            r0.warn(r3)
            r0 = r29
            goto L_0x0197
        L_0x02d2:
            r2 = r31
            java.lang.String r3 = r29.getLocalIPAddress()
            int r4 = r29.getLocalPort()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            java.lang.String r4 = fm.liveswitch.IntegerExtensions.toString(r4)
            if (r17 == 0) goto L_0x02eb
            java.lang.String r3 = fm.liveswitch.StringExtensions.concat(r7, r3, r13, r4)
            goto L_0x02ef
        L_0x02eb:
            java.lang.String r3 = fm.liveswitch.StringExtensions.concat(r3, r15, r4)
        L_0x02ef:
            fm.liveswitch.ILog r4 = __log
            java.lang.String r5 = r33.getId()
            java.lang.String r6 = "Opened {0} socket for the secondary (RTCP) component: {1} in primary gatherer {2}."
            r7 = r32
            java.lang.String r3 = fm.liveswitch.StringExtensions.format(r6, r7, r3, r5)
            r4.debug(r3)
            long r3 = r16.getAdapterSpeed()
            r0.setAdapterSpeed(r3)
            r12.add(r0)
            goto L_0x0319
        L_0x030b:
            r29 = r6
            r2 = r26
            r24 = 0
            goto L_0x0319
        L_0x0312:
            r2 = r4
            r29 = r6
            r25 = r13
            r24 = r15
        L_0x0319:
            r0 = r29
            goto L_0x0323
        L_0x031c:
            r24 = r4
            r2 = r5
            r27 = r6
            r25 = r13
        L_0x0323:
            if (r18 != 0) goto L_0x0340
            int r3 = fm.liveswitch.ArrayListExtensions.getCount(r2)
            int r4 = r10 - r9
            int r4 = r4 / 2
            if (r3 < r4) goto L_0x0340
            fm.liveswitch.ILog r3 = __log
            java.lang.String r4 = r16.toString()
            java.lang.String r5 = "Could not gather host candidates from {0}. All ports are in use."
            java.lang.String r4 = fm.liveswitch.StringExtensions.format((java.lang.String) r5, (java.lang.Object) r4)
            r3.warn(r4)
            r19 = 1
        L_0x0340:
            r7 = r33
            r5 = r2
            r4 = r24
            r13 = r25
            r6 = r27
            goto L_0x005e
        L_0x034b:
            r27 = r6
            r25 = r13
            int r6 = r27 + 1
            r7 = r33
            r13 = r25
            goto L_0x0040
        L_0x0357:
            r0 = 0
            fm.liveswitch.ManagedSocket[] r1 = new fm.liveswitch.ManagedSocket[r0]
            java.lang.Object[] r1 = r11.toArray(r1)
            r2 = r35
            r2.setValue(r1)
            fm.liveswitch.ManagedSocket[] r0 = new fm.liveswitch.ManagedSocket[r0]
            java.lang.Object[] r0 = r12.toArray(r0)
            r1 = r36
            r1.setValue(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.IceGatherer.openSockets(fm.liveswitch.ProtocolType, fm.liveswitch.Holder, fm.liveswitch.Holder):void");
    }

    private boolean openSocketsAndPrepareSocketManagers(IceServer iceServer, String str, Object obj, Holder<ManagedSocket[]> holder, Holder<ManagedSocket[]> holder2) {
        openSockets(iceServer.getIsSecure() ? ProtocolType.Tls : ProtocolType.Tcp, holder, holder2);
        boolean prepareStreamSocketManagers = (holder.getValue() == null || ArrayExtensions.getLength((Object[]) holder.getValue()) <= 0) ? false : prepareStreamSocketManagers(holder.getValue(), iceServer, str, obj);
        if (!prepareStreamSocketManagers) {
            if (holder2.getValue() != null) {
                for (ManagedSocket close : holder2.getValue()) {
                    close.close();
                }
            }
            holder2.setValue(null);
            if (holder.getValue() != null) {
                for (ManagedSocket close2 : holder.getValue()) {
                    close2.close();
                }
            }
            holder.setValue(null);
        }
        return prepareStreamSocketManagers;
    }

    private String prepareSocketManagerKey(ProtocolType protocolType, String str, int i) {
        String str2;
        if (protocolType == ProtocolType.Udp) {
            str2 = "udp";
        } else if (protocolType == ProtocolType.Tcp) {
            str2 = "tcp";
        } else if (protocolType == ProtocolType.Tls) {
            str2 = "tls";
        } else {
            str2 = protocolType == ProtocolType.Unknown ? "unknown" : "";
        }
        return StringExtensions.concat((Object[]) new String[]{str2, Constants.COMMON_SCHEMA_PREFIX_SEPARATOR, str, Constants.COMMON_SCHEMA_PREFIX_SEPARATOR, IntegerExtensions.toString(Integer.valueOf(i))});
    }

    private boolean prepareStreamSocketManagers(ManagedSocket[] managedSocketArr, IceServer iceServer, String str, Object obj) {
        ArrayList arrayList = new ArrayList();
        boolean z = true;
        for (ManagedSocket managedSocket : managedSocketArr) {
            try {
                IceStreamSocketManager iceStreamSocketManager = new IceStreamSocketManager(this.__lock, (StreamSocket) managedSocket, this.__transactionManager, iceServer, this.__scheduler, str);
                iceStreamSocketManager.setOnLocalCandidate(new IActionDelegate1<IceCandidate>() {
                    public String getId() {
                        return "fm.liveswitch.IceGatherer.processLocalCandidate";
                    }

                    public void invoke(IceCandidate iceCandidate) {
                        IceGatherer.this.processLocalCandidate(iceCandidate);
                    }
                });
                iceStreamSocketManager.setOnStateChange(new IActionDelegate1<IceSocketManager>() {
                    public String getId() {
                        return "fm.liveswitch.IceGatherer.processSocketManagerStateChanged";
                    }

                    public void invoke(IceSocketManager iceSocketManager) {
                        IceGatherer.this.processSocketManagerStateChanged(iceSocketManager);
                    }
                });
                iceStreamSocketManager.setOnStunRequest(new IActionDelegate3<Message, IceCandidate, TransportAddress>() {
                    public String getId() {
                        return "fm.liveswitch.IceGatherer.processStunRequest";
                    }

                    public void invoke(Message message, IceCandidate iceCandidate, TransportAddress transportAddress) {
                        IceGatherer.this.processStunRequest(message, iceCandidate, transportAddress);
                    }
                });
                iceStreamSocketManager.setOnIncomingData(new IActionDelegate3<DataBuffer, IceCandidate, TransportAddress>() {
                    public String getId() {
                        return "fm.liveswitch.IceGatherer.processReceivedData";
                    }

                    public void invoke(DataBuffer dataBuffer, IceCandidate iceCandidate, TransportAddress transportAddress) {
                        IceGatherer.this.processReceivedData(dataBuffer, iceCandidate, transportAddress);
                    }
                });
                iceStreamSocketManager.setOnAllocationMismatchException(new IActionDelegate1<IceSocketManager>() {
                    public String getId() {
                        return "fm.liveswitch.IceGatherer.processAllocationMismatchException";
                    }

                    public void invoke(IceSocketManager iceSocketManager) {
                        IceGatherer.this.processAllocationMismatchException(iceSocketManager);
                    }
                });
                try {
                    iceStreamSocketManager.setContext(obj);
                    addNewSocketManager(iceStreamSocketManager);
                    arrayList.add(iceStreamSocketManager);
                    z = false;
                } catch (Exception e) {
                    e = e;
                    __log.error(StringExtensions.format("Failed to create stream socket manager: {0} for gatherer {1}", e.toString(), getId()));
                    managedSocket.close();
                }
            } catch (Exception e2) {
                e = e2;
                Object obj2 = obj;
                __log.error(StringExtensions.format("Failed to create stream socket manager: {0} for gatherer {1}", e.toString(), getId()));
                managedSocket.close();
            }
        }
        if (z) {
            return false;
        }
        Iterator it = arrayList.iterator();
        boolean z2 = true;
        while (it.hasNext()) {
            IceSocketManager iceSocketManager = (IceSocketManager) it.next();
            try {
                if (tryStartSocketManager(iceSocketManager)) {
                    z2 = false;
                }
            } catch (Exception e3) {
                __log.error(StringExtensions.format("Failed to start stream socket manager {0}: {1} for gatherer {2}", iceSocketManager.getId(), e3.toString(), getId()));
                iceSocketManager.stop();
            }
        }
        return !z2;
    }

    /* access modifiers changed from: private */
    public void processAllocationMismatchException(IceSocketManager iceSocketManager) {
        doProcessAllocationMismatchException(iceSocketManager);
    }

    /* access modifiers changed from: private */
    public void processLocalCandidate(IceCandidate iceCandidate) {
        int assignLocalPreference;
        if (!Global.equals(iceCandidate.getType(), CandidateType.PeerReflexive)) {
            IceCandidate.getUnset();
            synchronized (this.__localPreferencesLock) {
                assignLocalPreference = assignLocalPreference(iceCandidate.getAdapterSpeed());
            }
            iceCandidate.setPriority(IceCandidate.calculatePriority(iceCandidate.getType(), assignLocalPreference, getComponent(), iceCandidate.getRelayProtocol()));
        }
        if (getLocalParameters() != null) {
            iceCandidate.setUsername(getLocalParameters().getUsernameFragment() == null ? "" : getLocalParameters().getUsernameFragment());
            iceCandidate.setPassword(getLocalParameters().getPassword() == null ? "" : getLocalParameters().getPassword());
        }
        IAction2<IceGatherer, IceCandidate> iAction2 = this._onLocalCandidate;
        if (iAction2 != null) {
            iAction2.invoke(this, iceCandidate);
        }
    }

    /* access modifiers changed from: private */
    public void processReceivedData(DataBuffer dataBuffer, IceCandidate iceCandidate, TransportAddress transportAddress) {
        IAction3<DataBuffer, IceCandidate, TransportAddress> iAction3 = this._onIncomingApplicationData;
        if (iAction3 != null) {
            iAction3.invoke(dataBuffer, iceCandidate, transportAddress);
        }
    }

    /* access modifiers changed from: private */
    public void processSocketManagerStateChanged(IceSocketManager iceSocketManager) {
        Error error;
        String str;
        IceSocketManager iceSocketManager2 = iceSocketManager;
        IceGatheringState state = iceSocketManager2 == null ? IceGatheringState.Closed : iceSocketManager.getState();
        if (iceSocketManager2 == null) {
            error = null;
        } else {
            error = iceSocketManager.getError();
        }
        if (this._verboseLogging) {
            if (iceSocketManager2 == null) {
                str = "Removed/Closed";
            } else {
                str = iceSocketManager.getState().toString();
            }
            __log.debug(StringExtensions.format("Socket manager state change to: {0}", (Object) str));
        }
        synchronized (this.__lock) {
            if (!Global.equals(getState(), IceGatheringState.Closed)) {
                if (!Global.equals(getState(), IceGatheringState.Failed)) {
                    if (state == IceGatheringState.Complete) {
                        if (Global.equals(getState(), IceGatheringState.Gathering) && getAllSocketManagersCompleteFailedOrClosedAndAtLeastOneCompleted() && this.__resolvesRemaining == 0) {
                            setState(IceGatheringState.Complete);
                        }
                    } else if (state == IceGatheringState.Closed) {
                        if (Global.equals(getState(), IceGatheringState.Closing)) {
                            if (verifyAllSocketsManagersClosedOrFailed()) {
                                finaliseClosing();
                            }
                        } else if (Global.equals(getState(), IceGatheringState.Gathering) && getAllSocketManagersCompleteFailedOrClosedAndAtLeastOneCompleted() && this.__resolvesRemaining == 0) {
                            setState(IceGatheringState.Complete);
                        }
                    } else if (state == IceGatheringState.Failed) {
                        if (Global.equals(getState(), IceGatheringState.Gathering) && getAllSocketManagersCompleteFailedOrClosedAndAtLeastOneCompleted() && this.__resolvesRemaining == 0) {
                            setState(IceGatheringState.Complete);
                        } else if (!Global.equals(getState(), IceGatheringState.Closing)) {
                            if (error != null && ((Global.equals(iceSocketManager.getError().getCode(), ErrorCode.SocketReceiveError) || Global.equals(iceSocketManager.getError().getCode(), ErrorCode.IceLocalRelayedStreamCandidateError)) && iceSocketManager.getServer() != null && iceSocketManager.getServer().getIPAddresses() != null && ArrayExtensions.getLength((Object[]) iceSocketManager.getServer().getIPAddresses()) > 1 && (iceSocketManager2 instanceof IceStreamSocketManager))) {
                                IceStreamSocketManager iceStreamSocketManager = (IceStreamSocketManager) iceSocketManager2;
                                String serverIPAddress = iceStreamSocketManager.getServerIPAddress();
                                HashMap hashMap = (HashMap) iceStreamSocketManager.getContext();
                                if (hashMap != null) {
                                    __log.debug(StringExtensions.format("StreamSocketManager connection failed for {0}...", (Object) serverIPAddress));
                                    Holder holder = new Holder(null);
                                    String str2 = serverIPAddress;
                                    String str3 = !HashMapExtensions.tryGetValue(hashMap, serverIPAddress, holder) ? null : (String) holder.getValue();
                                    while (str3 != null) {
                                        __log.debug(StringExtensions.format("...Trying next resolved address {0}", (Object) str3));
                                        Holder holder2 = new Holder(null);
                                        Holder holder3 = new Holder(null);
                                        boolean openSocketsAndPrepareSocketManagers = openSocketsAndPrepareSocketManagers(iceStreamSocketManager.getServer(), str3, iceStreamSocketManager.getContext(), holder2, holder3);
                                        ManagedSocket[] managedSocketArr = (ManagedSocket[]) holder2.getValue();
                                        ManagedSocket[] managedSocketArr2 = (ManagedSocket[]) holder3.getValue();
                                        if (openSocketsAndPrepareSocketManagers) {
                                            iceStreamSocketManager.getServer().setIPAddress(str3);
                                            ArrayListExtensions.addRange(this.__sockets, (T[]) managedSocketArr);
                                            IceGatherer iceGatherer = this.__relatedRtcpGatherer;
                                            if (!(iceGatherer == null || managedSocketArr2 == null)) {
                                                iceGatherer.addStreamSockets(new IceSocketsServerPair(managedSocketArr2, iceStreamSocketManager.getServer()));
                                            }
                                        }
                                        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), str2, null);
                                        Holder holder4 = new Holder(str3);
                                        boolean tryGetValue = HashMapExtensions.tryGetValue(hashMap, str3, holder4);
                                        String str4 = (String) holder4.getValue();
                                        if (!tryGetValue) {
                                            str4 = null;
                                        }
                                        str2 = str3;
                                        str3 = str4;
                                    }
                                }
                            }
                            if (verifyAllSocketsManagersClosedOrFailed()) {
                                setError(error);
                                setState(IceGatheringState.Failed);
                            }
                        } else if (verifyAllSocketsManagersClosedOrFailed()) {
                            finaliseClosing();
                        }
                    }
                }
            }
            if (this._verboseLogging) {
                __log.debug("Gatherer already in the CLOSED or FAILED state. Ignoring.");
            }
        }
    }

    /* access modifiers changed from: private */
    public void processStunRequest(Message message, IceCandidate iceCandidate, TransportAddress transportAddress) {
        IAction3<Message, IceCandidate, TransportAddress> onStunRequest = getOnStunRequest();
        if (onStunRequest != null) {
            onStunRequest.invoke(message, iceCandidate, transportAddress);
        }
    }

    /* access modifiers changed from: package-private */
    public IceCandidate registerLocalPeerReflexiveCandidate(IceLocalReflexiveCandidate iceLocalReflexiveCandidate) {
        if (Global.equals(iceLocalReflexiveCandidate.getType(), CandidateType.PeerReflexive)) {
            IceSocketManager manager = getManager(iceLocalReflexiveCandidate);
            if (manager != null) {
                return manager.registerLocalPeerReflexiveCandidate(iceLocalReflexiveCandidate);
            }
            return null;
        }
        throw new RuntimeException(new Exception("Gatherer can only register peer reflexive candidates."));
    }

    public void removeOnIncomingApplicationData(IAction3<DataBuffer, IceCandidate, TransportAddress> iAction3) {
        IAction3<T1, T2, T3> findIActionDelegate3WithId;
        if ((iAction3 instanceof IActionDelegate3) && (findIActionDelegate3WithId = Global.findIActionDelegate3WithId(this.__onIncomingApplicationData, ((IActionDelegate3) iAction3).getId())) != null) {
            iAction3 = findIActionDelegate3WithId;
        }
        this.__onIncomingApplicationData.remove(iAction3);
        if (this.__onIncomingApplicationData.size() == 0) {
            this._onIncomingApplicationData = null;
        }
    }

    public void removeOnLocalCandidate(IAction2<IceGatherer, IceCandidate> iAction2) {
        IAction2<T1, T2> findIActionDelegate2WithId;
        if ((iAction2 instanceof IActionDelegate2) && (findIActionDelegate2WithId = Global.findIActionDelegate2WithId(this.__onLocalCandidate, ((IActionDelegate2) iAction2).getId())) != null) {
            iAction2 = findIActionDelegate2WithId;
        }
        this.__onLocalCandidate.remove(iAction2);
        if (this.__onLocalCandidate.size() == 0) {
            this._onLocalCandidate = null;
        }
    }

    public void removeOnStateChange(IAction1<IceGatherer> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onStateChange, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onStateChange.remove(iAction1);
        if (this.__onStateChange.size() == 0) {
            this._onStateChange = null;
        }
    }

    public void removeRtcpGatherer() {
        synchronized (this.__lock) {
            this.__relatedRtcpGatherer = null;
            if (this.__rtcpDatagramSockets != null) {
                this.__rtcpDatagramSockets = null;
            }
            ArrayList<IceSocketsServerPair> arrayList = this.__rtcpStreamSocketsAndServers;
            if (arrayList != null) {
                arrayList.clear();
                this.__rtcpStreamSocketsAndServers = null;
            }
        }
    }

    private void resolveServerAddresses() {
        if (ArrayExtensions.getLength((Object[]) this.__options.getServers()) == 0) {
            setState(IceGatheringState.Complete);
            return;
        }
        for (IceServer iceServer : this.__options.getServers()) {
            Dns.resolve(iceServer.getHost(), new IActionDelegate2<String[], Object>() {
                public String getId() {
                    return "fm.liveswitch.IceGatherer.dnsResolveCallback";
                }

                public void invoke(String[] strArr, Object obj) {
                    IceGatherer.this.dnsResolveCallback(strArr, obj);
                }
            }, iceServer);
        }
    }

    public void setClosingShouldNotTriggerGlobalNonGracefulShutdown(boolean z) {
        this._closingShouldNotTriggerGlobalNonGracefulShutdown = z;
    }

    public void setCreateDatagramSocket(IFunction1<DatagramSocketCreateArgs, DatagramSocket> iFunction1) {
        this._createDatagramSocket = iFunction1;
    }

    public void setCreateStreamSocket(IFunction1<StreamSocketCreateArgs, StreamSocket> iFunction1) {
        this._createStreamSocket = iFunction1;
    }

    public void setError(Error error) {
        this._error = error;
    }

    private void setId(String str) {
        this._id = str;
    }

    public void setLocalParameters(IceParameters iceParameters) {
        this._localParameters = iceParameters;
    }

    /* access modifiers changed from: package-private */
    public void setOnStunRequest(IAction3<Message, IceCandidate, TransportAddress> iAction3) {
        this._onStunRequest = iAction3;
    }

    private void setReceiveBufferSize(int i) {
        this._receiveBufferSize = i;
    }

    private void setResolvesRemaining(int i) {
        synchronized (this.__lock) {
            this.__resolvesRemaining = i;
        }
    }

    private void setSendBufferSize(int i) {
        this._sendBufferSize = i;
    }

    private void setState(IceGatheringState iceGatheringState) {
        if (!Global.equals(this.__state, iceGatheringState)) {
            this.__state = iceGatheringState;
            if (Global.equals(iceGatheringState, IceGatheringState.Failed) || Global.equals(iceGatheringState, IceGatheringState.Closed)) {
                removeRtcpGatherer();
                ArrayList<ManagedSocket> arrayList = this.__sockets;
                if (arrayList != null) {
                    int i = 0;
                    Iterator<ManagedSocket> it = arrayList.iterator();
                    while (it.hasNext()) {
                        ManagedSocket next = it.next();
                        if (!next.getIsClosed()) {
                            i++;
                            next.close();
                        }
                    }
                    if (i > 0) {
                        __log.debug(StringExtensions.format("Gatherer {0} cleaned up {1} orphaned sockets upon shutting down.", getId(), IntegerExtensions.toString(Integer.valueOf(i))));
                    }
                }
                this.__sockets = null;
            }
            IAction1<IceGatherer> iAction1 = this._onStateChange;
            if (iAction1 != null) {
                iAction1.invoke(this);
            }
        }
    }

    public void setStreamIndex(int i) {
        this._streamIndex = i;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x008f A[Catch:{ Exception -> 0x0198 }] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0093 A[Catch:{ Exception -> 0x0198 }] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0102 A[Catch:{ Exception -> 0x0198 }, LOOP:2: B:44:0x0100->B:45:0x0102, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x014c A[Catch:{ Exception -> 0x0198 }, LOOP:3: B:47:0x0146->B:49:0x014c, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0167 A[Catch:{ Exception -> 0x0198 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean start() {
        /*
            r12 = this;
            java.lang.Object r0 = r12.__lock
            monitor-enter(r0)
            fm.liveswitch.IceGatheringState r1 = r12.getState()     // Catch:{ all -> 0x01e5 }
            fm.liveswitch.IceGatheringState r2 = fm.liveswitch.IceGatheringState.New     // Catch:{ all -> 0x01e5 }
            boolean r1 = fm.liveswitch.Global.equals(r1, r2)     // Catch:{ all -> 0x01e5 }
            r2 = 0
            if (r1 != 0) goto L_0x0012
            monitor-exit(r0)     // Catch:{ all -> 0x01e5 }
            return r2
        L_0x0012:
            fm.liveswitch.IceTransactionManager r1 = new fm.liveswitch.IceTransactionManager     // Catch:{ all -> 0x01e5 }
            java.lang.Object r3 = r12.__lock     // Catch:{ all -> 0x01e5 }
            fm.liveswitch.Scheduler r4 = r12.__scheduler     // Catch:{ all -> 0x01e5 }
            r1.<init>(r3, r4)     // Catch:{ all -> 0x01e5 }
            r12.__transactionManager = r1     // Catch:{ all -> 0x01e5 }
            r1.start()     // Catch:{ all -> 0x01e5 }
            fm.liveswitch.IceGatherOptions r1 = r12.getOptions()     // Catch:{ all -> 0x01e5 }
            java.lang.String[] r1 = r1.getPrivateIPAddresses()     // Catch:{ all -> 0x01e5 }
            if (r1 == 0) goto L_0x0074
            fm.liveswitch.IceGatherOptions r1 = r12.getOptions()     // Catch:{ all -> 0x01e5 }
            java.lang.String[] r1 = r1.getPrivateIPAddresses()     // Catch:{ all -> 0x01e5 }
            int r1 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r1)     // Catch:{ all -> 0x01e5 }
            if (r1 != 0) goto L_0x0039
            goto L_0x0074
        L_0x0039:
            fm.liveswitch.IceGatherOptions r1 = r12.getOptions()     // Catch:{ all -> 0x01e5 }
            java.lang.String[] r1 = r1.getPrivateIPAddresses()     // Catch:{ all -> 0x01e5 }
            int r1 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r1)     // Catch:{ all -> 0x01e5 }
            fm.liveswitch.LocalAddress[] r1 = new fm.liveswitch.LocalAddress[r1]     // Catch:{ all -> 0x01e5 }
            r12.__localAddresses = r1     // Catch:{ all -> 0x01e5 }
            r1 = 0
        L_0x004a:
            fm.liveswitch.IceGatherOptions r3 = r12.getOptions()     // Catch:{ all -> 0x01e5 }
            java.lang.String[] r3 = r3.getPrivateIPAddresses()     // Catch:{ all -> 0x01e5 }
            int r3 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r3)     // Catch:{ all -> 0x01e5 }
            if (r1 >= r3) goto L_0x0082
            fm.liveswitch.LocalAddress[] r3 = r12.__localAddresses     // Catch:{ all -> 0x01e5 }
            fm.liveswitch.LocalAddress r10 = new fm.liveswitch.LocalAddress     // Catch:{ all -> 0x01e5 }
            fm.liveswitch.IceGatherOptions r4 = r12.getOptions()     // Catch:{ all -> 0x01e5 }
            java.lang.String[] r4 = r4.getPrivateIPAddresses()     // Catch:{ all -> 0x01e5 }
            r5 = r4[r1]     // Catch:{ all -> 0x01e5 }
            r6 = 0
            r7 = 0
            fm.liveswitch.NetworkType r9 = fm.liveswitch.NetworkType.Unknown     // Catch:{ all -> 0x01e5 }
            r4 = r10
            r4.<init>(r5, r6, r7, r9)     // Catch:{ all -> 0x01e5 }
            r3[r1] = r10     // Catch:{ all -> 0x01e5 }
            int r1 = r1 + 1
            goto L_0x004a
        L_0x0074:
            fm.liveswitch.IceGatherOptions r1 = r12.getOptions()     // Catch:{ all -> 0x01e5 }
            fm.liveswitch.AddressType[] r1 = r1.getAddressTypes()     // Catch:{ all -> 0x01e5 }
            fm.liveswitch.LocalAddress[] r1 = fm.liveswitch.LocalNetwork.getLocalAddresses(r1)     // Catch:{ all -> 0x01e5 }
            r12.__localAddresses = r1     // Catch:{ all -> 0x01e5 }
        L_0x0082:
            fm.liveswitch.IceComponent r1 = r12.getComponent()     // Catch:{ all -> 0x01e5 }
            fm.liveswitch.IceComponent r3 = fm.liveswitch.IceComponent.Rtcp     // Catch:{ all -> 0x01e5 }
            boolean r1 = fm.liveswitch.Global.equals(r1, r3)     // Catch:{ all -> 0x01e5 }
            r3 = 0
            if (r1 == 0) goto L_0x0093
            fm.liveswitch.DatagramSocket[] r1 = r12.__rtcpDatagramSockets     // Catch:{ all -> 0x01e5 }
            r4 = r3
            goto L_0x00f9
        L_0x0093:
            fm.liveswitch.LocalAddress[] r1 = r12.__localAddresses     // Catch:{ all -> 0x01e5 }
            if (r1 == 0) goto L_0x01c6
            int r1 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r1)     // Catch:{ all -> 0x01e5 }
            if (r1 <= 0) goto L_0x01c6
            fm.liveswitch.Holder r1 = new fm.liveswitch.Holder     // Catch:{ all -> 0x01e5 }
            r1.<init>(r3)     // Catch:{ all -> 0x01e5 }
            fm.liveswitch.Holder r4 = new fm.liveswitch.Holder     // Catch:{ all -> 0x01e5 }
            r4.<init>(r3)     // Catch:{ all -> 0x01e5 }
            fm.liveswitch.ProtocolType r5 = fm.liveswitch.ProtocolType.Udp     // Catch:{ all -> 0x01e5 }
            r12.openSockets(r5, r1, r4)     // Catch:{ all -> 0x01e5 }
            java.lang.Object r1 = r1.getValue()     // Catch:{ all -> 0x01e5 }
            fm.liveswitch.ManagedSocket[] r1 = (fm.liveswitch.ManagedSocket[]) r1     // Catch:{ all -> 0x01e5 }
            java.lang.Object r4 = r4.getValue()     // Catch:{ all -> 0x01e5 }
            fm.liveswitch.ManagedSocket[] r4 = (fm.liveswitch.ManagedSocket[]) r4     // Catch:{ all -> 0x01e5 }
            java.util.ArrayList<fm.liveswitch.ManagedSocket> r5 = r12.__sockets     // Catch:{ all -> 0x01e5 }
            fm.liveswitch.ArrayListExtensions.addRange(r5, (T[]) r1)     // Catch:{ all -> 0x01e5 }
            if (r1 != 0) goto L_0x00f0
            if (r4 == 0) goto L_0x00cd
            int r1 = r4.length     // Catch:{ all -> 0x01e5 }
            r3 = 0
        L_0x00c3:
            if (r3 >= r1) goto L_0x00cd
            r5 = r4[r3]     // Catch:{ all -> 0x01e5 }
            r5.close()     // Catch:{ all -> 0x01e5 }
            int r3 = r3 + 1
            goto L_0x00c3
        L_0x00cd:
            fm.liveswitch.Error r1 = r12.getError()     // Catch:{ all -> 0x01e5 }
            if (r1 != 0) goto L_0x00e2
            fm.liveswitch.Error r1 = new fm.liveswitch.Error     // Catch:{ all -> 0x01e5 }
            fm.liveswitch.ErrorCode r3 = fm.liveswitch.ErrorCode.SocketClosed     // Catch:{ all -> 0x01e5 }
            java.lang.Exception r4 = new java.lang.Exception     // Catch:{ all -> 0x01e5 }
            java.lang.String r5 = "Failed to open datagram sockets."
            r4.<init>(r5)     // Catch:{ all -> 0x01e5 }
            r1.<init>((fm.liveswitch.ErrorCode) r3, (java.lang.Exception) r4)     // Catch:{ all -> 0x01e5 }
            goto L_0x00e6
        L_0x00e2:
            fm.liveswitch.Error r1 = r12.getError()     // Catch:{ all -> 0x01e5 }
        L_0x00e6:
            r12.setError(r1)     // Catch:{ all -> 0x01e5 }
            fm.liveswitch.IceGatheringState r1 = fm.liveswitch.IceGatheringState.Failed     // Catch:{ all -> 0x01e5 }
            r12.setState(r1)     // Catch:{ all -> 0x01e5 }
            monitor-exit(r0)     // Catch:{ all -> 0x01e5 }
            return r2
        L_0x00f0:
            fm.liveswitch.IceGatherer r5 = r12.__relatedRtcpGatherer     // Catch:{ all -> 0x01e5 }
            if (r5 == 0) goto L_0x00f9
            if (r4 == 0) goto L_0x00f9
            r5.assignDatagramSockets(r4)     // Catch:{ all -> 0x01e5 }
        L_0x00f9:
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ all -> 0x01e5 }
            r5.<init>()     // Catch:{ all -> 0x01e5 }
            int r6 = r1.length     // Catch:{ Exception -> 0x0198 }
            r7 = 0
        L_0x0100:
            if (r7 >= r6) goto L_0x0142
            r8 = r1[r7]     // Catch:{ Exception -> 0x0198 }
            fm.liveswitch.IceDatagramSocketManager r9 = new fm.liveswitch.IceDatagramSocketManager     // Catch:{ Exception -> 0x0198 }
            java.lang.Object r10 = r12.__lock     // Catch:{ Exception -> 0x0198 }
            fm.liveswitch.DatagramSocket r8 = (fm.liveswitch.DatagramSocket) r8     // Catch:{ Exception -> 0x0198 }
            fm.liveswitch.DatagramSocket r8 = (fm.liveswitch.DatagramSocket) r8     // Catch:{ Exception -> 0x0198 }
            fm.liveswitch.IceTransactionManager r11 = r12.__transactionManager     // Catch:{ Exception -> 0x0198 }
            r9.<init>(r10, r8, r11)     // Catch:{ Exception -> 0x0198 }
            fm.liveswitch.IceGatherer$15 r8 = new fm.liveswitch.IceGatherer$15     // Catch:{ Exception -> 0x0198 }
            r8.<init>()     // Catch:{ Exception -> 0x0198 }
            r9.setOnLocalCandidate(r8)     // Catch:{ Exception -> 0x0198 }
            fm.liveswitch.IceGatherer$16 r8 = new fm.liveswitch.IceGatherer$16     // Catch:{ Exception -> 0x0198 }
            r8.<init>()     // Catch:{ Exception -> 0x0198 }
            r9.setOnStateChange(r8)     // Catch:{ Exception -> 0x0198 }
            fm.liveswitch.IceGatherer$17 r8 = new fm.liveswitch.IceGatherer$17     // Catch:{ Exception -> 0x0198 }
            r8.<init>()     // Catch:{ Exception -> 0x0198 }
            r9.setOnStunRequest(r8)     // Catch:{ Exception -> 0x0198 }
            fm.liveswitch.IceGatherer$18 r8 = new fm.liveswitch.IceGatherer$18     // Catch:{ Exception -> 0x0198 }
            r8.<init>()     // Catch:{ Exception -> 0x0198 }
            r9.setOnIncomingData(r8)     // Catch:{ Exception -> 0x0198 }
            fm.liveswitch.IceGatherer$19 r8 = new fm.liveswitch.IceGatherer$19     // Catch:{ Exception -> 0x0198 }
            r8.<init>()     // Catch:{ Exception -> 0x0198 }
            r9.setOnAllocationMismatchException(r8)     // Catch:{ Exception -> 0x0198 }
            r12.addNewSocketManager(r9)     // Catch:{ Exception -> 0x0198 }
            r5.add(r9)     // Catch:{ Exception -> 0x0198 }
            int r7 = r7 + 1
            goto L_0x0100
        L_0x0142:
            java.util.Iterator r5 = r5.iterator()     // Catch:{ Exception -> 0x0198 }
        L_0x0146:
            boolean r6 = r5.hasNext()     // Catch:{ Exception -> 0x0198 }
            if (r6 == 0) goto L_0x0156
            java.lang.Object r6 = r5.next()     // Catch:{ Exception -> 0x0198 }
            fm.liveswitch.IceSocketManager r6 = (fm.liveswitch.IceSocketManager) r6     // Catch:{ Exception -> 0x0198 }
            r12.tryStartSocketManager(r6)     // Catch:{ Exception -> 0x0198 }
            goto L_0x0146
        L_0x0156:
            fm.liveswitch.IceGatheringState r1 = fm.liveswitch.IceGatheringState.Gathering     // Catch:{ all -> 0x01e5 }
            r12.setState(r1)     // Catch:{ all -> 0x01e5 }
            fm.liveswitch.IceComponent r1 = r12.getComponent()     // Catch:{ all -> 0x01e5 }
            fm.liveswitch.IceComponent r4 = fm.liveswitch.IceComponent.Rtcp     // Catch:{ all -> 0x01e5 }
            boolean r1 = fm.liveswitch.Global.equals(r1, r4)     // Catch:{ all -> 0x01e5 }
            if (r1 == 0) goto L_0x0192
            java.util.ArrayList<fm.liveswitch.IceSocketsServerPair> r1 = r12.__rtcpStreamSocketsAndServers     // Catch:{ all -> 0x01e5 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x01e5 }
        L_0x016d:
            boolean r4 = r1.hasNext()     // Catch:{ all -> 0x01e5 }
            if (r4 == 0) goto L_0x018d
            java.lang.Object r4 = r1.next()     // Catch:{ all -> 0x01e5 }
            fm.liveswitch.IceSocketsServerPair r4 = (fm.liveswitch.IceSocketsServerPair) r4     // Catch:{ all -> 0x01e5 }
            fm.liveswitch.ManagedSocket[] r5 = r4.getSockets()     // Catch:{ all -> 0x01e5 }
            fm.liveswitch.IceServer r4 = r4.getServer()     // Catch:{ all -> 0x01e5 }
            java.lang.String r6 = r4.getIPAddress()     // Catch:{ all -> 0x01e5 }
            boolean r4 = r12.prepareStreamSocketManagers(r5, r4, r6, r3)     // Catch:{ all -> 0x01e5 }
            if (r4 != 0) goto L_0x016d
            monitor-exit(r0)     // Catch:{ all -> 0x01e5 }
            return r2
        L_0x018d:
            java.util.ArrayList<fm.liveswitch.IceSocketsServerPair> r1 = r12.__rtcpStreamSocketsAndServers     // Catch:{ all -> 0x01e5 }
            r1.clear()     // Catch:{ all -> 0x01e5 }
        L_0x0192:
            r12.resolveServerAddresses()     // Catch:{ all -> 0x01e5 }
            monitor-exit(r0)     // Catch:{ all -> 0x01e5 }
            r0 = 1
            return r0
        L_0x0198:
            r3 = move-exception
            if (r1 == 0) goto L_0x01a7
            int r5 = r1.length     // Catch:{ all -> 0x01e5 }
            r6 = 0
        L_0x019d:
            if (r6 >= r5) goto L_0x01a7
            r7 = r1[r6]     // Catch:{ all -> 0x01e5 }
            r7.close()     // Catch:{ all -> 0x01e5 }
            int r6 = r6 + 1
            goto L_0x019d
        L_0x01a7:
            if (r4 == 0) goto L_0x01b5
            int r1 = r4.length     // Catch:{ all -> 0x01e5 }
            r5 = 0
        L_0x01ab:
            if (r5 >= r1) goto L_0x01b5
            r6 = r4[r5]     // Catch:{ all -> 0x01e5 }
            r6.close()     // Catch:{ all -> 0x01e5 }
            int r5 = r5 + 1
            goto L_0x01ab
        L_0x01b5:
            fm.liveswitch.Error r1 = new fm.liveswitch.Error     // Catch:{ all -> 0x01e5 }
            fm.liveswitch.ErrorCode r4 = fm.liveswitch.ErrorCode.IceStartError     // Catch:{ all -> 0x01e5 }
            r1.<init>((fm.liveswitch.ErrorCode) r4, (java.lang.Exception) r3)     // Catch:{ all -> 0x01e5 }
            r12.setError(r1)     // Catch:{ all -> 0x01e5 }
            fm.liveswitch.IceGatheringState r1 = fm.liveswitch.IceGatheringState.Failed     // Catch:{ all -> 0x01e5 }
            r12.setState(r1)     // Catch:{ all -> 0x01e5 }
            monitor-exit(r0)     // Catch:{ all -> 0x01e5 }
            return r2
        L_0x01c6:
            java.lang.Object r1 = r12.__lock     // Catch:{ all -> 0x01e5 }
            monitor-enter(r1)     // Catch:{ all -> 0x01e5 }
            fm.liveswitch.Error r3 = new fm.liveswitch.Error     // Catch:{ all -> 0x01e2 }
            fm.liveswitch.ErrorCode r4 = fm.liveswitch.ErrorCode.IceLocalAddressUnavailable     // Catch:{ all -> 0x01e2 }
            java.lang.Exception r5 = new java.lang.Exception     // Catch:{ all -> 0x01e2 }
            java.lang.String r6 = "Could not obtain local IP addresses."
            r5.<init>(r6)     // Catch:{ all -> 0x01e2 }
            r3.<init>((fm.liveswitch.ErrorCode) r4, (java.lang.Exception) r5)     // Catch:{ all -> 0x01e2 }
            r12.setError(r3)     // Catch:{ all -> 0x01e2 }
            fm.liveswitch.IceGatheringState r3 = fm.liveswitch.IceGatheringState.Failed     // Catch:{ all -> 0x01e2 }
            r12.setState(r3)     // Catch:{ all -> 0x01e2 }
            monitor-exit(r1)     // Catch:{ all -> 0x01e2 }
            monitor-exit(r0)     // Catch:{ all -> 0x01e5 }
            return r2
        L_0x01e2:
            r2 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x01e2 }
            throw r2     // Catch:{ all -> 0x01e5 }
        L_0x01e5:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x01e5 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.IceGatherer.start():boolean");
    }

    public void stop() {
        synchronized (this.__lock) {
            if (Global.equals(getState(), IceGatheringState.New) || Global.equals(getState(), IceGatheringState.Gathering) || Global.equals(getState(), IceGatheringState.Complete) || Global.equals(getState(), IceGatheringState.Failed)) {
                removeRtcpGatherer();
                setState(IceGatheringState.Closing);
                HashMap<String, IceSocketManager> hashMap = this.__socketManagers;
                if (hashMap != null) {
                    if (HashMapExtensions.getCount(hashMap) != 0) {
                        int count = HashMapExtensions.getCount(this.__socketManagers);
                        IceSocketManager[] iceSocketManagerArr = new IceSocketManager[count];
                        int i = 0;
                        for (String str : HashMapExtensions.getKeys(this.__socketManagers)) {
                            iceSocketManagerArr[i] = HashMapExtensions.getItem(this.__socketManagers).get(str);
                            i++;
                        }
                        boolean z = true;
                        for (int i2 = 0; i2 < count; i2++) {
                            IceSocketManager iceSocketManager = iceSocketManagerArr[i2];
                            if (iceSocketManager != null) {
                                __log.debug(StringExtensions.format("Shutting down socket manager {0} while closing gatherer {1}.", iceSocketManager.getId(), getId()));
                                z &= !iceSocketManager.stop();
                            }
                        }
                        if (z) {
                            setState(IceGatheringState.Closed);
                        }
                    }
                }
                __log.debug(StringExtensions.format("No socket managers present while shutting down gatherer {0}. Transitioning to gatherer to Closed state.", (Object) getId()));
                setState(IceGatheringState.Closed);
            }
        }
    }

    private boolean tryStartSocketManager(IceSocketManager iceSocketManager) {
        __log.debug(StringExtensions.format("Starting socket manager {0} with a socket {1} for gatherer {2}.", iceSocketManager.getId(), Global.equals(LocalNetwork.getAddressType(iceSocketManager.getLocalIpAddress()), AddressType.IPv6) ? StringExtensions.concat("[", iceSocketManager.getLocalIpAddress(), "]:", IntegerExtensions.toString(Integer.valueOf(iceSocketManager.getLocalPort()))) : StringExtensions.concat(iceSocketManager.getLocalIpAddress(), Constants.COMMON_SCHEMA_PREFIX_SEPARATOR, IntegerExtensions.toString(Integer.valueOf(iceSocketManager.getLocalPort()))), getId()));
        boolean start = iceSocketManager.start(this.__options);
        if (!start) {
            synchronized (this.__lock) {
                HashMapExtensions.remove(this.__socketManagers, prepareSocketManagerKey(iceSocketManager.getProtocol(), iceSocketManager.getLocalIpAddress(), iceSocketManager.getLocalPort()));
                __log.debug(StringExtensions.format("Could not start socket manager {0} for gatherer {1}. Will shut down socket manager {0}.", iceSocketManager.getId(), getId()));
                iceSocketManager.stop();
            }
        }
        return start;
    }

    private boolean verifyAllSocketsManagersClosedOrFailed() {
        boolean z;
        synchronized (this.__lock) {
            z = true;
            for (IceSocketManager next : HashMapExtensions.getValues(this.__socketManagers)) {
                if (!Global.equals(next.getState(), IceGatheringState.Closed) && !Global.equals(next.getState(), IceGatheringState.Failed)) {
                    z = false;
                }
            }
        }
        return z;
    }
}
