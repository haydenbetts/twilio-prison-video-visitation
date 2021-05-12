package fm.liveswitch;

import fm.liveswitch.stun.BindingRequest;
import fm.liveswitch.stun.BindingResponse;
import fm.liveswitch.stun.Error;
import fm.liveswitch.stun.ErrorCodeAttribute;
import fm.liveswitch.stun.Message;
import fm.liveswitch.stun.MessageType;
import fm.liveswitch.stun.TransactionTransmitCounterAttribute;
import fm.liveswitch.stun.turn.AllocateResponse;
import fm.liveswitch.stun.turn.DataAttribute;
import fm.liveswitch.stun.turn.DataIndication;
import fm.liveswitch.stun.turn.XorPeerAddressAttribute;
import java.util.HashMap;

class IceDatagramSocketManager extends IceSocketManager {
    private static IDataBufferPool __dataBufferPool;
    private static ILog __log;
    private HashMap<String, String> __orderedIPAddresses = new HashMap<>();
    private IceCandidate _localHostCandidate;
    private DatagramSocket _socket;

    /* access modifiers changed from: protected */
    public void closeSocket() {
        DatagramSocket socket = getSocket();
        if (socket != null && !socket.getIsClosed()) {
            socket.close();
            __log.debug(StringExtensions.format("Closed socket for socket manager {0}.", (Object) super.getId()));
        } else if (socket == null && !super.getIsTerminatingOrTerminated()) {
            __log.debug(StringExtensions.format("Could not close socket for socket manager {0}: no socket instantiated - nothing to close.", (Object) super.getId()));
        } else if (socket.getIsClosed() && !super.getIsTerminatingOrTerminated()) {
            __log.debug(StringExtensions.format("Could not close socket for socket manager {0}: socket already closed.", (Object) super.getId()));
        }
    }

    private void doListen() {
        DatagramSocket socket = getSocket();
        if (socket == null || getSocketIsClosed()) {
            this._transactionManager.remove(this);
            synchronized (super.getLock()) {
                if (!Global.equals(super.getState(), IceGatheringState.Closed) && !Global.equals(super.getState(), IceGatheringState.Failed)) {
                    super.setState(IceGatheringState.Closed);
                }
            }
            return;
        }
        socket.receiveAsync(new IActionDelegate3<DataBuffer, String, Integer>() {
            public String getId() {
                return "fm.liveswitch.IceDatagramSocketManager.listenReceiveSuccess";
            }

            public void invoke(DataBuffer dataBuffer, String str, Integer num) {
                IceDatagramSocketManager.this.listenReceiveSuccess(dataBuffer, str, num.intValue());
            }
        }, new IActionDelegate1<Exception>() {
            public String getId() {
                return "fm.liveswitch.IceDatagramSocketManager.listenReceiveFailure";
            }

            public void invoke(Exception exc) {
                IceDatagramSocketManager.this.listenReceiveFailure(exc);
            }
        });
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x008a A[SYNTHETIC, Splitter:B:19:0x008a] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x01a0  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x01a8 A[SYNTHETIC, Splitter:B:49:0x01a8] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean doProcessServerResponse(fm.liveswitch.IceSendRequestSuccessArgs r39) {
        /*
            r38 = this;
            r12 = r38
            fm.liveswitch.ScheduledItem r13 = r39.getItem()
            java.lang.Object r0 = r13.getState()
            fm.liveswitch.IceSendMessageArgs r0 = (fm.liveswitch.IceSendMessageArgs) r0
            r1 = r0
            fm.liveswitch.IceSendMessageArgs r1 = (fm.liveswitch.IceSendMessageArgs) r1
            fm.liveswitch.stun.Message r0 = r39.getResponse()
            fm.liveswitch.TransportAddress r2 = r39.getRemoteAddress()
            fm.liveswitch.Error r0 = validateResponse(r1, r0, r2)
            fm.liveswitch.stun.Message r2 = r39.getResponse()
            boolean r2 = r2 instanceof fm.liveswitch.stun.turn.AllocateResponse
            if (r2 == 0) goto L_0x0026
            java.lang.String r3 = "TURN"
            goto L_0x0028
        L_0x0026:
            java.lang.String r3 = "STUN"
        L_0x0028:
            r4 = 0
            r5 = 1
            r14 = 0
            if (r0 != 0) goto L_0x021e
            fm.liveswitch.TransportAddress r0 = r39.getRemoteAddress()
            fm.liveswitch.TransportAddress r6 = r1.getAddress()
            boolean r0 = r0.equals(r6)
            if (r0 != 0) goto L_0x005a
            fm.liveswitch.TransportAddress r0 = r39.getRemoteAddress()
            java.lang.String r0 = r0.toString()
            fm.liveswitch.TransportAddress r1 = r1.getAddress()
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "{2} server response source {0} does not match targeted endpoint {1}."
            java.lang.String r0 = fm.liveswitch.StringExtensions.format(r2, r0, r1, r3)
            fm.liveswitch.ILog r1 = __log
            r1.debug(r0)
            super.removeTransaction(r13)
            return r14
        L_0x005a:
            fm.liveswitch.stun.Message r0 = r39.getResponse()
            fm.liveswitch.stun.XorMappedAddressAttribute r0 = r0.getXorMappedAddress()
            if (r0 == 0) goto L_0x0071
            java.lang.String r4 = r0.getIPAddress()
            int r3 = r0.getPort()
        L_0x006c:
            r20 = r3
            r19 = r4
            goto L_0x0088
        L_0x0071:
            fm.liveswitch.stun.Message r3 = r39.getResponse()
            fm.liveswitch.stun.MappedAddressAttribute r3 = r3.getMappedAddress()
            if (r3 == 0) goto L_0x0084
            java.lang.String r4 = r3.getIPAddress()
            int r3 = r3.getPort()
            goto L_0x006c
        L_0x0084:
            r19 = r4
            r20 = 0
        L_0x0088:
            if (r2 == 0) goto L_0x01a0
            fm.liveswitch.stun.Message r2 = r1.getMessage()     // Catch:{ Exception -> 0x0186 }
            fm.liveswitch.stun.turn.AllocateRequest r2 = (fm.liveswitch.stun.turn.AllocateRequest) r2     // Catch:{ Exception -> 0x0186 }
            fm.liveswitch.stun.turn.AllocateRequest r2 = (fm.liveswitch.stun.turn.AllocateRequest) r2     // Catch:{ Exception -> 0x0186 }
            java.lang.String r32 = r1.getRelayPassword()     // Catch:{ Exception -> 0x0186 }
            fm.liveswitch.stun.UsernameAttribute r3 = r2.getUsername()     // Catch:{ Exception -> 0x0186 }
            if (r3 != 0) goto L_0x009f
            java.lang.String r3 = fm.liveswitch.StringExtensions.empty     // Catch:{ Exception -> 0x0186 }
            goto L_0x00a7
        L_0x009f:
            fm.liveswitch.stun.UsernameAttribute r3 = r2.getUsername()     // Catch:{ Exception -> 0x0186 }
            java.lang.String r3 = r3.getValue()     // Catch:{ Exception -> 0x0186 }
        L_0x00a7:
            r31 = r3
            fm.liveswitch.stun.Message r3 = r39.getResponse()     // Catch:{ Exception -> 0x0186 }
            fm.liveswitch.stun.turn.XorRelayedAddressAttribute r3 = r3.getXorRelayedAddress()     // Catch:{ Exception -> 0x0186 }
            fm.liveswitch.stun.Message r4 = r39.getResponse()     // Catch:{ Exception -> 0x0186 }
            fm.liveswitch.stun.turn.LifetimeAttribute r4 = r4.getLifetime()     // Catch:{ Exception -> 0x0186 }
            fm.liveswitch.stun.RealmAttribute r6 = r2.getRealm()     // Catch:{ Exception -> 0x0186 }
            if (r6 != 0) goto L_0x00c2
            java.lang.String r6 = fm.liveswitch.StringExtensions.empty     // Catch:{ Exception -> 0x0186 }
            goto L_0x00ca
        L_0x00c2:
            fm.liveswitch.stun.RealmAttribute r6 = r2.getRealm()     // Catch:{ Exception -> 0x0186 }
            java.lang.String r6 = r6.getValue()     // Catch:{ Exception -> 0x0186 }
        L_0x00ca:
            r34 = r6
            fm.liveswitch.stun.NonceAttribute r6 = r2.getNonce()     // Catch:{ Exception -> 0x0186 }
            if (r6 != 0) goto L_0x00d5
            java.lang.String r2 = fm.liveswitch.StringExtensions.empty     // Catch:{ Exception -> 0x0186 }
            goto L_0x00dd
        L_0x00d5:
            fm.liveswitch.stun.NonceAttribute r2 = r2.getNonce()     // Catch:{ Exception -> 0x0186 }
            java.lang.String r2 = r2.getValue()     // Catch:{ Exception -> 0x0186 }
        L_0x00dd:
            r33 = r2
            java.lang.String r2 = r3.getIPAddress()     // Catch:{ Exception -> 0x0186 }
            boolean r2 = fm.liveswitch.TransportAddress.isAny(r2)     // Catch:{ Exception -> 0x0186 }
            if (r2 == 0) goto L_0x00f4
            fm.liveswitch.TransportAddress r2 = r39.getRemoteAddress()     // Catch:{ Exception -> 0x0186 }
            java.lang.String r2 = r2.getIPAddress()     // Catch:{ Exception -> 0x0186 }
            r3.setIPAddress(r2)     // Catch:{ Exception -> 0x0186 }
        L_0x00f4:
            fm.liveswitch.CandidateType r2 = fm.liveswitch.CandidateType.Relayed     // Catch:{ Exception -> 0x0186 }
            java.lang.String r6 = r3.getIPAddress()     // Catch:{ Exception -> 0x0186 }
            fm.liveswitch.TransportAddress r7 = r39.getRemoteAddress()     // Catch:{ Exception -> 0x0186 }
            fm.liveswitch.ProtocolType r8 = super.getProtocol()     // Catch:{ Exception -> 0x0186 }
            java.lang.String r23 = fm.liveswitch.IceCandidate.generateLocalCandidateFoundation(r2, r6, r7, r8)     // Catch:{ Exception -> 0x0186 }
            fm.liveswitch.IceLocalRelayedCandidate r2 = new fm.liveswitch.IceLocalRelayedCandidate     // Catch:{ Exception -> 0x0186 }
            java.lang.Object r22 = super.getLock()     // Catch:{ Exception -> 0x0186 }
            fm.liveswitch.ProtocolType r24 = super.getProtocol()     // Catch:{ Exception -> 0x0186 }
            java.lang.String r25 = r3.getIPAddress()     // Catch:{ Exception -> 0x0186 }
            int r26 = r3.getPort()     // Catch:{ Exception -> 0x0186 }
            java.lang.String r27 = r0.getIPAddress()     // Catch:{ Exception -> 0x0186 }
            int r28 = r0.getPort()     // Catch:{ Exception -> 0x0186 }
            long r29 = r4.getLifetime()     // Catch:{ Exception -> 0x0186 }
            fm.liveswitch.TransportAddress r35 = r1.getAddress()     // Catch:{ Exception -> 0x0186 }
            fm.liveswitch.IceTransactionManager r0 = r12._transactionManager     // Catch:{ Exception -> 0x0186 }
            fm.liveswitch.IceGatherOptions r3 = super.getGatherOptions()     // Catch:{ Exception -> 0x0186 }
            int r37 = r3.getStunRequestTimeout()     // Catch:{ Exception -> 0x0186 }
            r21 = r2
            r36 = r0
            r21.<init>(r22, r23, r24, r25, r26, r27, r28, r29, r31, r32, r33, r34, r35, r36, r37)     // Catch:{ Exception -> 0x0186 }
            fm.liveswitch.DatagramSocket r0 = r38.getSocket()     // Catch:{ Exception -> 0x0186 }
            long r3 = r0.getAdapterSpeed()     // Catch:{ Exception -> 0x0186 }
            r2.setAdapterSpeed(r3)     // Catch:{ Exception -> 0x0186 }
            fm.liveswitch.IceDatagramSocketManager$3 r0 = new fm.liveswitch.IceDatagramSocketManager$3     // Catch:{ Exception -> 0x0186 }
            r0.<init>()     // Catch:{ Exception -> 0x0186 }
            r2.setDispatchStunMessage(r0)     // Catch:{ Exception -> 0x0186 }
            fm.liveswitch.IceDatagramSocketManager$4 r0 = new fm.liveswitch.IceDatagramSocketManager$4     // Catch:{ Exception -> 0x0186 }
            r0.<init>()     // Catch:{ Exception -> 0x0186 }
            r2.setDispatchApplicationData(r0)     // Catch:{ Exception -> 0x0186 }
            fm.liveswitch.IceDatagramSocketManager$5 r0 = new fm.liveswitch.IceDatagramSocketManager$5     // Catch:{ Exception -> 0x0186 }
            r0.<init>()     // Catch:{ Exception -> 0x0186 }
            r2.setOnRelayStateChanged(r0)     // Catch:{ Exception -> 0x0186 }
            fm.liveswitch.ProtocolType r0 = super.getProtocol()     // Catch:{ Exception -> 0x0186 }
            r2.setRelayProtocol(r0)     // Catch:{ Exception -> 0x0186 }
            long r3 = fm.liveswitch.Scheduler.getCurrentTime()     // Catch:{ Exception -> 0x0186 }
            r6 = r39
            int r0 = fm.liveswitch.IceTransactionManager.calculateInstantaneousRtt(r14, r6, r5, r3)     // Catch:{ Exception -> 0x0184 }
            if (r0 < 0) goto L_0x017d
            r2.setLastRelayServerRoundTripTime(r0)     // Catch:{ Exception -> 0x0184 }
            int r3 = r2.getSmoothedRelayServerRoundTripTime()     // Catch:{ Exception -> 0x0184 }
            int r0 = fm.liveswitch.IceTransactionManager.calculateSmoothedRtt(r0, r3)     // Catch:{ Exception -> 0x0184 }
            r2.setSmoothedRelayServerRoundTripTime(r0)     // Catch:{ Exception -> 0x0184 }
        L_0x017d:
            super.raiseLocalCandidate(r2)     // Catch:{ Exception -> 0x0184 }
            r2.startScheduleRefreshTransactions()     // Catch:{ Exception -> 0x0184 }
            goto L_0x01a2
        L_0x0184:
            r0 = move-exception
            goto L_0x0189
        L_0x0186:
            r0 = move-exception
            r6 = r39
        L_0x0189:
            fm.liveswitch.Error r2 = new fm.liveswitch.Error
            fm.liveswitch.ErrorCode r3 = fm.liveswitch.ErrorCode.IceLocalRelayedDatagramCandidateError
            r2.<init>((fm.liveswitch.ErrorCode) r3, (java.lang.Exception) r0)
            fm.liveswitch.ILog r0 = __log
            java.lang.String r2 = r2.getDescription()
            java.lang.String r3 = "Datagram Socket Manager: Could not process TURN server response: {0}"
            java.lang.String r2 = fm.liveswitch.StringExtensions.format((java.lang.String) r3, (java.lang.Object) r2)
            r0.debug(r2)
            goto L_0x01a2
        L_0x01a0:
            r6 = r39
        L_0x01a2:
            boolean r0 = r1.getRaiseServerReflexiveCandidates()
            if (r0 == 0) goto L_0x021a
            fm.liveswitch.CandidateType r0 = fm.liveswitch.CandidateType.ServerReflexive     // Catch:{ Exception -> 0x0203 }
            fm.liveswitch.IceCandidate r1 = r38.getLocalHostCandidate()     // Catch:{ Exception -> 0x0203 }
            java.lang.String r1 = r1.getIPAddress()     // Catch:{ Exception -> 0x0203 }
            fm.liveswitch.TransportAddress r2 = r39.getRemoteAddress()     // Catch:{ Exception -> 0x0203 }
            fm.liveswitch.ProtocolType r3 = super.getProtocol()     // Catch:{ Exception -> 0x0203 }
            java.lang.String r17 = fm.liveswitch.IceCandidate.generateLocalCandidateFoundation(r0, r1, r2, r3)     // Catch:{ Exception -> 0x0203 }
            fm.liveswitch.IceLocalReflexiveCandidate r0 = new fm.liveswitch.IceLocalReflexiveCandidate     // Catch:{ Exception -> 0x0203 }
            java.lang.Object r16 = super.getLock()     // Catch:{ Exception -> 0x0203 }
            fm.liveswitch.ProtocolType r18 = super.getProtocol()     // Catch:{ Exception -> 0x0203 }
            fm.liveswitch.CandidateType r21 = fm.liveswitch.CandidateType.ServerReflexive     // Catch:{ Exception -> 0x0203 }
            fm.liveswitch.DatagramSocket r1 = r38.getSocket()     // Catch:{ Exception -> 0x0203 }
            java.lang.String r22 = r1.getLocalIPAddress()     // Catch:{ Exception -> 0x0203 }
            fm.liveswitch.DatagramSocket r1 = r38.getSocket()     // Catch:{ Exception -> 0x0203 }
            int r23 = r1.getLocalPort()     // Catch:{ Exception -> 0x0203 }
            r15 = r0
            r15.<init>(r16, r17, r18, r19, r20, r21, r22, r23)     // Catch:{ Exception -> 0x0203 }
            fm.liveswitch.DatagramSocket r1 = r38.getSocket()     // Catch:{ Exception -> 0x0203 }
            long r1 = r1.getAdapterSpeed()     // Catch:{ Exception -> 0x0203 }
            r0.setAdapterSpeed(r1)     // Catch:{ Exception -> 0x0203 }
            fm.liveswitch.IceDatagramSocketManager$6 r1 = new fm.liveswitch.IceDatagramSocketManager$6     // Catch:{ Exception -> 0x0203 }
            r1.<init>()     // Catch:{ Exception -> 0x0203 }
            r0.setDispatchStunMessage(r1)     // Catch:{ Exception -> 0x0203 }
            fm.liveswitch.ProtocolType r1 = super.getProtocol()     // Catch:{ Exception -> 0x0203 }
            r0.setRelayProtocol(r1)     // Catch:{ Exception -> 0x0203 }
            fm.liveswitch.IceCandidate r1 = r38.getLocalHostCandidate()     // Catch:{ Exception -> 0x0203 }
            r0.setLocalHostBaseCandidate(r1)     // Catch:{ Exception -> 0x0203 }
            super.raiseLocalCandidate(r0)     // Catch:{ Exception -> 0x0203 }
            goto L_0x021a
        L_0x0203:
            r0 = move-exception
            fm.liveswitch.Error r1 = new fm.liveswitch.Error
            fm.liveswitch.ErrorCode r2 = fm.liveswitch.ErrorCode.IceLocalServerReflexiveCandidateError
            r1.<init>((fm.liveswitch.ErrorCode) r2, (java.lang.Exception) r0)
            fm.liveswitch.ILog r0 = __log
            java.lang.String r1 = r1.getDescription()
            java.lang.String r2 = "Datagram Socket Manager: Could not process STUN server response: {0}"
            java.lang.String r1 = fm.liveswitch.StringExtensions.format((java.lang.String) r2, (java.lang.Object) r1)
            r0.debug(r1)
        L_0x021a:
            super.removeTransaction(r13)
            return r5
        L_0x021e:
            r6 = r39
            java.util.HashMap r7 = super.getNumberOfStunRequests()
            java.util.HashMap r7 = fm.liveswitch.HashMapExtensions.getItem(r7)
            fm.liveswitch.TransportAddress r8 = r1.getAddress()
            java.lang.String r8 = r8.getIPAddress()
            java.lang.Object r7 = r7.get(r8)
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r7 = r7.intValue()
            if (r2 == 0) goto L_0x0284
            fm.liveswitch.ErrorCode r8 = r0.getCode()
            fm.liveswitch.ErrorCode r9 = fm.liveswitch.ErrorCode.StunUnauthorized
            boolean r8 = fm.liveswitch.Global.equals(r8, r9)
            if (r8 == 0) goto L_0x0284
            fm.liveswitch.stun.Message r8 = r1.getMessage()
            fm.liveswitch.stun.NonceAttribute r8 = r8.getNonce()
            if (r8 != 0) goto L_0x0284
            fm.liveswitch.stun.Message r8 = r1.getMessage()
            fm.liveswitch.stun.RealmAttribute r8 = r8.getRealm()
            if (r8 != 0) goto L_0x0284
            r3 = r0
            fm.liveswitch.stun.UnauthorizedStunError r3 = (fm.liveswitch.stun.UnauthorizedStunError) r3
            fm.liveswitch.stun.UnauthorizedStunError r3 = (fm.liveswitch.stun.UnauthorizedStunError) r3
            fm.liveswitch.stun.NonceAttribute r6 = r3.getNonce()
            if (r6 == 0) goto L_0x0270
            fm.liveswitch.stun.NonceAttribute r6 = r3.getNonce()
            java.lang.String r6 = r6.getValue()
            goto L_0x0271
        L_0x0270:
            r6 = r4
        L_0x0271:
            fm.liveswitch.stun.RealmAttribute r8 = r3.getRealm()
            if (r8 == 0) goto L_0x0281
            fm.liveswitch.stun.RealmAttribute r3 = r3.getRealm()
            java.lang.String r3 = r3.getValue()
            goto L_0x033b
        L_0x0281:
            r3 = r4
            goto L_0x033b
        L_0x0284:
            java.lang.String r8 = ""
            if (r2 == 0) goto L_0x02ec
            fm.liveswitch.ErrorCode r9 = r0.getCode()
            fm.liveswitch.ErrorCode r10 = fm.liveswitch.ErrorCode.StunStaleNonce
            boolean r9 = fm.liveswitch.Global.equals(r9, r10)
            if (r9 == 0) goto L_0x02ec
            r9 = r0
            fm.liveswitch.stun.StaleNonceError r9 = (fm.liveswitch.stun.StaleNonceError) r9
            fm.liveswitch.stun.StaleNonceError r9 = (fm.liveswitch.stun.StaleNonceError) r9
            fm.liveswitch.stun.NonceAttribute r10 = r9.getNonce()
            if (r10 == 0) goto L_0x02a8
            fm.liveswitch.stun.NonceAttribute r10 = r9.getNonce()
            java.lang.String r10 = r10.getValue()
            goto L_0x02a9
        L_0x02a8:
            r10 = r4
        L_0x02a9:
            fm.liveswitch.stun.RealmAttribute r11 = r9.getRealm()
            if (r11 == 0) goto L_0x02b8
            fm.liveswitch.stun.RealmAttribute r9 = r9.getRealm()
            java.lang.String r9 = r9.getValue()
            goto L_0x02b9
        L_0x02b8:
            r9 = r4
        L_0x02b9:
            fm.liveswitch.IceGatherOptions r11 = super.getGatherOptions()
            int r11 = r11.getTurnAllocateRequestLimit()
            if (r7 >= r11) goto L_0x02c5
            java.lang.String r8 = " Further attempts to establish server allocation will be made with an updated nonce."
        L_0x02c5:
            r11 = 4
            java.lang.Object[] r11 = new java.lang.Object[r11]
            fm.liveswitch.TransportAddress r6 = r39.getRemoteAddress()
            java.lang.String r6 = r6.toString()
            r11[r14] = r6
            java.lang.String r6 = r0.getDescription()
            r11[r5] = r6
            r6 = 2
            r11[r6] = r8
            r6 = 3
            r11[r6] = r3
            java.lang.String r3 = "{3} server {0} reports error {1}.{2}"
            java.lang.String r3 = fm.liveswitch.StringExtensions.format((java.lang.String) r3, (java.lang.Object[]) r11)
            fm.liveswitch.ILog r6 = __log
            r6.debug(r3)
            r3 = r9
            r6 = r10
            goto L_0x033b
        L_0x02ec:
            fm.liveswitch.ErrorCode r9 = r0.getCode()
            fm.liveswitch.ErrorCode r10 = fm.liveswitch.ErrorCode.StunTryAlternate
            boolean r9 = fm.liveswitch.Global.equals(r9, r10)
            if (r9 == 0) goto L_0x0412
            r6 = r0
            fm.liveswitch.stun.TryAlternateStunError r6 = (fm.liveswitch.stun.TryAlternateStunError) r6
            fm.liveswitch.stun.TryAlternateStunError r6 = (fm.liveswitch.stun.TryAlternateStunError) r6
            fm.liveswitch.TransportAddress r9 = new fm.liveswitch.TransportAddress
            fm.liveswitch.stun.AlternateServerAttribute r10 = r6.getAlternateServer()
            java.lang.String r10 = r10.getIPAddress()
            fm.liveswitch.stun.AlternateServerAttribute r6 = r6.getAlternateServer()
            int r6 = r6.getPort()
            r9.<init>(r10, r6)
            r1.setAddress(r9)
            fm.liveswitch.IceGatherOptions r6 = super.getGatherOptions()
            int r6 = r6.getTurnAllocateRequestLimit()
            if (r7 >= r6) goto L_0x0326
            if (r2 == 0) goto L_0x0324
            java.lang.String r8 = " Attempts to establish server allocation with a new server will be made."
            goto L_0x0326
        L_0x0324:
            java.lang.String r8 = " Attempts to establish server binding with a new server will be made."
        L_0x0326:
            fm.liveswitch.TransportAddress r6 = r1.getAddress()
            java.lang.String r6 = r6.toString()
            java.lang.String r9 = "{2} server sent an instruction to try an alternate server {0}.{1}."
            java.lang.String r3 = fm.liveswitch.StringExtensions.format(r9, r6, r8, r3)
            fm.liveswitch.ILog r6 = __log
            r6.debug(r3)
            r3 = r4
            r6 = r3
        L_0x033b:
            fm.liveswitch.TransportAddress r8 = r1.getAddress()
            java.lang.String r8 = r8.getIPAddress()
            if (r2 != 0) goto L_0x0351
            fm.liveswitch.IceGatherOptions r9 = super.getGatherOptions()
            int r9 = r9.getStunBindingRequestLimit()
            if (r7 >= r9) goto L_0x0351
            r9 = 1
            goto L_0x0352
        L_0x0351:
            r9 = 0
        L_0x0352:
            if (r2 == 0) goto L_0x035f
            fm.liveswitch.IceGatherOptions r10 = super.getGatherOptions()
            int r10 = r10.getTurnAllocateRequestLimit()
            if (r7 >= r10) goto L_0x035f
            goto L_0x0360
        L_0x035f:
            r5 = 0
        L_0x0360:
            fm.liveswitch.Holder r7 = new fm.liveswitch.Holder
            r7.<init>(r8)
            java.util.HashMap<java.lang.String, java.lang.String> r10 = r12.__orderedIPAddresses
            boolean r8 = fm.liveswitch.HashMapExtensions.tryGetValue(r10, r8, r7)
            java.lang.Object r7 = r7.getValue()
            java.lang.String r7 = (java.lang.String) r7
            if (r9 != 0) goto L_0x037d
            if (r5 != 0) goto L_0x037d
            if (r8 == 0) goto L_0x037d
            r3 = r2 ^ 1
            r8 = r4
            r10 = r8
            r4 = r2
            goto L_0x0381
        L_0x037d:
            r10 = r3
            r8 = r6
            r3 = 0
            r4 = 0
        L_0x0381:
            if (r9 != 0) goto L_0x0391
            if (r3 != 0) goto L_0x0391
            if (r5 != 0) goto L_0x0391
            if (r4 == 0) goto L_0x038a
            goto L_0x0391
        L_0x038a:
            r12.finaliseGathering(r0)
            super.removeTransaction(r13)
            return r14
        L_0x0391:
            boolean r0 = fm.liveswitch.StringExtensions.isNullOrEmpty(r8)
            if (r0 != 0) goto L_0x03a3
            fm.liveswitch.stun.Message r0 = r1.getMessage()
            fm.liveswitch.stun.NonceAttribute r3 = new fm.liveswitch.stun.NonceAttribute
            r3.<init>(r8)
            r0.setNonce(r3)
        L_0x03a3:
            boolean r0 = fm.liveswitch.StringExtensions.isNullOrEmpty(r10)
            if (r0 != 0) goto L_0x03b5
            fm.liveswitch.stun.Message r0 = r1.getMessage()
            fm.liveswitch.stun.RealmAttribute r3 = new fm.liveswitch.stun.RealmAttribute
            r3.<init>(r10)
            r0.setRealm(r3)
        L_0x03b5:
            if (r2 == 0) goto L_0x03ec
            fm.liveswitch.stun.Message r0 = r1.getMessage()
            fm.liveswitch.stun.UsernameAttribute r0 = r0.getUsername()
            java.lang.String r2 = r0.getValue()
            java.lang.String r3 = r1.getRelayPassword()
            fm.liveswitch.TransportAddress r0 = r1.getAddress()
            int r5 = r0.getPort()
            boolean r6 = r1.getRaiseServerReflexiveCandidates()
            fm.liveswitch.IceDatagramSocketManager$7 r9 = new fm.liveswitch.IceDatagramSocketManager$7
            r9.<init>()
            fm.liveswitch.IceDatagramSocketManager$8 r0 = new fm.liveswitch.IceDatagramSocketManager$8
            r0.<init>()
            fm.liveswitch.IceDatagramSocketManager$9 r11 = new fm.liveswitch.IceDatagramSocketManager$9
            r11.<init>()
            r1 = r38
            r4 = r7
            r7 = r8
            r8 = r10
            r10 = r0
            super.serverAllocate(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
            goto L_0x040e
        L_0x03ec:
            fm.liveswitch.TransportAddress r2 = new fm.liveswitch.TransportAddress
            fm.liveswitch.TransportAddress r0 = r1.getAddress()
            int r0 = r0.getPort()
            r2.<init>(r7, r0)
            fm.liveswitch.IceDatagramSocketManager$10 r4 = new fm.liveswitch.IceDatagramSocketManager$10
            r4.<init>()
            fm.liveswitch.IceDatagramSocketManager$11 r5 = new fm.liveswitch.IceDatagramSocketManager$11
            r5.<init>()
            fm.liveswitch.IceDatagramSocketManager$12 r6 = new fm.liveswitch.IceDatagramSocketManager$12
            r6.<init>()
            r1 = r38
            r3 = r8
            super.serverBind(r2, r3, r4, r5, r6)
        L_0x040e:
            super.removeTransaction(r13)
            return r14
        L_0x0412:
            fm.liveswitch.ErrorCode r1 = r0.getCode()
            fm.liveswitch.ErrorCode r4 = fm.liveswitch.ErrorCode.StunAddressFamilyNotSupported
            boolean r1 = fm.liveswitch.Global.equals(r1, r4)
            if (r1 == 0) goto L_0x043b
            java.lang.Object r0 = r13.getState()
            fm.liveswitch.IceSendMessageArgs r0 = (fm.liveswitch.IceSendMessageArgs) r0
            fm.liveswitch.IceSendMessageArgs r0 = (fm.liveswitch.IceSendMessageArgs) r0
            fm.liveswitch.TransportAddress r0 = r0.getAddress()
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "TURN server {0} reports error 440. Address family not supported."
            java.lang.String r0 = fm.liveswitch.StringExtensions.format((java.lang.String) r1, (java.lang.Object) r0)
            fm.liveswitch.Log.debug(r0)
            r12.nextIpAllocateRequest(r13)
            return r14
        L_0x043b:
            if (r2 == 0) goto L_0x0494
            fm.liveswitch.ErrorCode r1 = r0.getCode()
            fm.liveswitch.ErrorCode r2 = fm.liveswitch.ErrorCode.StunTurnAllocationMismatch
            boolean r1 = fm.liveswitch.Global.equals(r1, r2)
            if (r1 == 0) goto L_0x0494
            fm.liveswitch.IceGatherOptions r1 = super.getGatherOptions()
            int r1 = r1.getTurnAllocateRequestLimit()
            if (r7 >= r1) goto L_0x0478
            fm.liveswitch.TransportAddress r1 = r39.getRemoteAddress()
            java.lang.String r1 = r1.toString()
            fm.liveswitch.DatagramSocket r2 = r38.getSocket()
            java.lang.String r2 = r2.getLocalIPAddress()
            java.lang.String r3 = "TURN allocate request to {0} from {1}: allocation mismatch detected. Further attempts to establish server allocation will be made."
            java.lang.String r1 = fm.liveswitch.StringExtensions.format(r3, r1, r2)
            fm.liveswitch.ILog r2 = __log
            r2.debug(r1)
            fm.liveswitch.IAction1 r1 = super.getOnAllocationMismatchException()
            if (r1 == 0) goto L_0x04ab
            r1.invoke(r12)
            goto L_0x04ab
        L_0x0478:
            fm.liveswitch.TransportAddress r1 = r39.getRemoteAddress()
            java.lang.String r1 = r1.toString()
            fm.liveswitch.DatagramSocket r2 = r38.getSocket()
            java.lang.String r2 = r2.getLocalIPAddress()
            java.lang.String r3 = "TURN allocate request to {0} from {1}: allocation mismatch detected. No further attempts to establish server allocation will be made."
            java.lang.String r1 = fm.liveswitch.StringExtensions.format(r3, r1, r2)
            fm.liveswitch.ILog r2 = __log
            r2.debug(r1)
            goto L_0x04ab
        L_0x0494:
            fm.liveswitch.TransportAddress r1 = r39.getRemoteAddress()
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = r0.getDescription()
            java.lang.String r4 = "{2} server {0} reports error {1}."
            java.lang.String r1 = fm.liveswitch.StringExtensions.format(r4, r1, r2, r3)
            fm.liveswitch.ILog r2 = __log
            r2.debug(r1)
        L_0x04ab:
            r12.finaliseGathering(r0)
            super.removeTransaction(r13)
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.IceDatagramSocketManager.doProcessServerResponse(fm.liveswitch.IceSendRequestSuccessArgs):boolean");
    }

    private void finaliseGathering(Error error) {
        if (super.getIsGathering()) {
            return;
        }
        if (this._localCandidates == null || HashMapExtensions.getCount(this._localCandidates) <= 0) {
            super.setError(error);
            super.setState(IceGatheringState.Failed);
            return;
        }
        synchronized (super.getLock()) {
            if (Global.equals(super.getState(), IceGatheringState.Gathering)) {
                super.setState(IceGatheringState.Complete);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0077, code lost:
        if (fm.liveswitch.Global.equals(r9, fm.liveswitch.IceGatherPolicy.All) != false) goto L_0x0081;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x007f, code lost:
        if (fm.liveswitch.Global.equals(r9, fm.liveswitch.IceGatherPolicy.NoHost) == false) goto L_0x0082;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0081, code lost:
        r2 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x008a, code lost:
        if (super.getServer().getIPAddress() == null) goto L_0x0097;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x008c, code lost:
        gatherLocalNonHostCandidatesForAddress(super.getServer().getIPAddress(), r8, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x009b, code lost:
        if (super.getIsGathering() != false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x009d, code lost:
        r8 = super.getLock();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00a1, code lost:
        monitor-enter(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00ac, code lost:
        if (fm.liveswitch.Global.equals(super.getState(), fm.liveswitch.IceGatheringState.Gathering) == false) goto L_0x00b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00ae, code lost:
        super.setState(fm.liveswitch.IceGatheringState.Complete);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00b3, code lost:
        monitor-exit(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void gatherLocalNonHostCandidates(fm.liveswitch.IceServer r8, fm.liveswitch.IceGatherPolicy r9) {
        /*
            r7 = this;
            java.lang.String[] r0 = r8.getIPAddresses()
            if (r0 == 0) goto L_0x00bb
            java.lang.String[] r0 = r8.getIPAddresses()
            int r0 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r0)
            if (r0 != 0) goto L_0x0012
            goto L_0x00bb
        L_0x0012:
            java.lang.Object r0 = super.getLock()
            monitor-enter(r0)
            super.setServer(r8)     // Catch:{ all -> 0x00b8 }
            java.lang.String[] r1 = r8.getIPAddresses()     // Catch:{ all -> 0x00b8 }
            r2 = 0
            r3 = 0
        L_0x0020:
            int r4 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r1)     // Catch:{ all -> 0x00b8 }
            if (r3 >= r4) goto L_0x003d
            int r4 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r1)     // Catch:{ all -> 0x00b8 }
            int r5 = r3 + 1
            if (r4 <= r5) goto L_0x003b
            java.util.HashMap<java.lang.String, java.lang.String> r4 = r7.__orderedIPAddresses     // Catch:{ all -> 0x00b8 }
            java.util.HashMap r4 = fm.liveswitch.HashMapExtensions.getItem(r4)     // Catch:{ all -> 0x00b8 }
            r3 = r1[r3]     // Catch:{ all -> 0x00b8 }
            r6 = r1[r5]     // Catch:{ all -> 0x00b8 }
            fm.liveswitch.HashMapExtensions.set(r4, r3, r6)     // Catch:{ all -> 0x00b8 }
        L_0x003b:
            r3 = r5
            goto L_0x0020
        L_0x003d:
            fm.liveswitch.IceGatheringState r1 = super.getState()     // Catch:{ all -> 0x00b8 }
            fm.liveswitch.IceGatheringState r3 = fm.liveswitch.IceGatheringState.New     // Catch:{ all -> 0x00b8 }
            boolean r1 = fm.liveswitch.Global.equals(r1, r3)     // Catch:{ all -> 0x00b8 }
            if (r1 != 0) goto L_0x006b
            fm.liveswitch.IceGatheringState r1 = super.getState()     // Catch:{ all -> 0x00b8 }
            fm.liveswitch.IceGatheringState r3 = fm.liveswitch.IceGatheringState.Complete     // Catch:{ all -> 0x00b8 }
            boolean r1 = fm.liveswitch.Global.equals(r1, r3)     // Catch:{ all -> 0x00b8 }
            if (r1 == 0) goto L_0x0056
            goto L_0x006b
        L_0x0056:
            fm.liveswitch.IceGatheringState r1 = super.getState()     // Catch:{ all -> 0x00b8 }
            fm.liveswitch.IceGatheringState r3 = fm.liveswitch.IceGatheringState.Closed     // Catch:{ all -> 0x00b8 }
            boolean r1 = fm.liveswitch.Global.equals(r1, r3)     // Catch:{ all -> 0x00b8 }
            if (r1 == 0) goto L_0x0070
            fm.liveswitch.ILog r8 = __log     // Catch:{ all -> 0x00b8 }
            java.lang.String r9 = "Datagram socket manager in closed state when attempting to gather local candidates"
            r8.error(r9)     // Catch:{ all -> 0x00b8 }
            monitor-exit(r0)     // Catch:{ all -> 0x00b8 }
            return
        L_0x006b:
            fm.liveswitch.IceGatheringState r1 = fm.liveswitch.IceGatheringState.Gathering     // Catch:{ all -> 0x00b8 }
            super.setState(r1)     // Catch:{ all -> 0x00b8 }
        L_0x0070:
            monitor-exit(r0)     // Catch:{ all -> 0x00b8 }
            fm.liveswitch.IceGatherPolicy r0 = fm.liveswitch.IceGatherPolicy.All
            boolean r0 = fm.liveswitch.Global.equals(r9, r0)
            if (r0 != 0) goto L_0x0081
            fm.liveswitch.IceGatherPolicy r0 = fm.liveswitch.IceGatherPolicy.NoHost
            boolean r9 = fm.liveswitch.Global.equals(r9, r0)
            if (r9 == 0) goto L_0x0082
        L_0x0081:
            r2 = 1
        L_0x0082:
            fm.liveswitch.IceServer r9 = super.getServer()
            java.lang.String r9 = r9.getIPAddress()
            if (r9 == 0) goto L_0x0097
            fm.liveswitch.IceServer r9 = super.getServer()
            java.lang.String r9 = r9.getIPAddress()
            r7.gatherLocalNonHostCandidatesForAddress(r9, r8, r2)
        L_0x0097:
            boolean r8 = super.getIsGathering()
            if (r8 != 0) goto L_0x00c2
            java.lang.Object r8 = super.getLock()
            monitor-enter(r8)
            fm.liveswitch.IceGatheringState r9 = super.getState()     // Catch:{ all -> 0x00b5 }
            fm.liveswitch.IceGatheringState r0 = fm.liveswitch.IceGatheringState.Gathering     // Catch:{ all -> 0x00b5 }
            boolean r9 = fm.liveswitch.Global.equals(r9, r0)     // Catch:{ all -> 0x00b5 }
            if (r9 == 0) goto L_0x00b3
            fm.liveswitch.IceGatheringState r9 = fm.liveswitch.IceGatheringState.Complete     // Catch:{ all -> 0x00b5 }
            super.setState(r9)     // Catch:{ all -> 0x00b5 }
        L_0x00b3:
            monitor-exit(r8)     // Catch:{ all -> 0x00b5 }
            goto L_0x00c2
        L_0x00b5:
            r9 = move-exception
            monitor-exit(r8)     // Catch:{ all -> 0x00b5 }
            throw r9
        L_0x00b8:
            r8 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00b8 }
            throw r8
        L_0x00bb:
            fm.liveswitch.ILog r8 = __log
            java.lang.String r9 = "Datagram socket manager cannot gather non-local candidates: server IP addresses not supplied."
            r8.debug(r9)
        L_0x00c2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.IceDatagramSocketManager.gatherLocalNonHostCandidates(fm.liveswitch.IceServer, fm.liveswitch.IceGatherPolicy):void");
    }

    private void gatherLocalNonHostCandidatesForAddress(String str, IceServer iceServer, boolean z) {
        String iPAddress = getLocalHostCandidate() == null ? null : getLocalHostCandidate().getIPAddress();
        if (iPAddress != null && str != null && Global.equals(LocalNetwork.getAddressType(iPAddress), LocalNetwork.getAddressType(str))) {
            if (iceServer.getIsTurn() && str != null) {
                super.serverAllocate(iceServer.getUsername(), iceServer.getPassword(), str, iceServer.getPort(), z, (String) null, (String) null, new IActionDelegate1<ScheduledItem>() {
                    public String getId() {
                        return "fm.liveswitch.IceDatagramSocketManager.sendServerAllocateStunMessage";
                    }

                    public void invoke(ScheduledItem scheduledItem) {
                        IceDatagramSocketManager.this.sendServerAllocateStunMessage(scheduledItem);
                    }
                }, new IActionDelegate1<IceSendRequestSuccessArgs>() {
                    public String getId() {
                        return "fm.liveswitch.IceDatagramSocketManager.processServerResponse";
                    }

                    public void invoke(IceSendRequestSuccessArgs iceSendRequestSuccessArgs) {
                        IceDatagramSocketManager.this.processServerResponse(iceSendRequestSuccessArgs);
                    }
                }, new IActionDelegate1<ScheduledItem>() {
                    public String getId() {
                        return "fm.liveswitch.IceDatagramSocketManager.sendAllocateRequestTimedout";
                    }

                    public void invoke(ScheduledItem scheduledItem) {
                        IceDatagramSocketManager.this.sendAllocateRequestTimedout(scheduledItem);
                    }
                });
            } else if (iceServer.getIsStun() && z && str != null) {
                super.serverBind(new TransportAddress(str, iceServer.getPort()), (String) null, new IActionDelegate1<ScheduledItem>() {
                    public String getId() {
                        return "fm.liveswitch.IceDatagramSocketManager.sendServerBindStunMessage";
                    }

                    public void invoke(ScheduledItem scheduledItem) {
                        IceDatagramSocketManager.this.sendServerBindStunMessage(scheduledItem);
                    }
                }, new IActionDelegate1<IceSendRequestSuccessArgs>() {
                    public String getId() {
                        return "fm.liveswitch.IceDatagramSocketManager.processServerResponse";
                    }

                    public void invoke(IceSendRequestSuccessArgs iceSendRequestSuccessArgs) {
                        IceDatagramSocketManager.this.processServerResponse(iceSendRequestSuccessArgs);
                    }
                }, new IActionDelegate1<ScheduledItem>() {
                    public String getId() {
                        return "fm.liveswitch.IceDatagramSocketManager.sendBindingRequestTimedout";
                    }

                    public void invoke(ScheduledItem scheduledItem) {
                        IceDatagramSocketManager.this.sendBindingRequestTimedout(scheduledItem);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: package-private */
    public long getAdapterSpeed() {
        if (getLocalHostCandidate() != null) {
            return getLocalHostCandidate().getAdapterSpeed();
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    public IceCandidate getLocalHostCandidate() {
        return this._localHostCandidate;
    }

    /* access modifiers changed from: package-private */
    public String getLocalIpAddress() {
        if (getSocket() != null) {
            return getSocket().getLocalIPAddress();
        }
        return StringExtensions.empty;
    }

    /* access modifiers changed from: package-private */
    public int getLocalPort() {
        if (getSocket() != null) {
            return getSocket().getLocalPort();
        }
        return 0;
    }

    public TransportAddress getLocalTransportAddress() {
        DatagramSocket socket = getSocket();
        if (socket == null) {
            return null;
        }
        String localIPAddress = socket.getLocalIPAddress();
        if (localIPAddress == null) {
            localIPAddress = StringExtensions.empty;
        }
        return new TransportAddress(localIPAddress, socket.getLocalPort());
    }

    public DatagramSocket getSocket() {
        return this._socket;
    }

    private boolean getSocketIsClosed() {
        DatagramSocket socket = getSocket();
        return socket == null || socket.getIsClosed();
    }

    static {
        Class<IceDatagramSocketManager> cls = IceDatagramSocketManager.class;
        __dataBufferPool = DataBufferPool.getTracer((Class) cls);
        __log = Log.getLogger((Class) cls);
    }

    public IceDatagramSocketManager(Object obj, DatagramSocket datagramSocket, IceTransactionManager iceTransactionManager) {
        super(obj, iceTransactionManager);
        super.setProtocol(ProtocolType.Udp);
        if (datagramSocket != null) {
            setSocket(datagramSocket);
            return;
        }
        throw new RuntimeException(new Exception("Socket cannot be null."));
    }

    /* access modifiers changed from: private */
    public void listenReceiveFailure(Exception exc) {
        synchronized (super.getLock()) {
            this._transactionManager.remove(this);
            closeSocket();
            if (!Global.equals(super.getState(), IceGatheringState.Closed) && !Global.equals(super.getState(), IceGatheringState.Closing)) {
                super.setError(new Error(ErrorCode.SocketReceiveError, exc));
                super.setState(IceGatheringState.Failed);
            }
        }
    }

    /* access modifiers changed from: private */
    public void listenReceiveSuccess(DataBuffer dataBuffer, String str, int i) {
        TransportAddress transportAddress = new TransportAddress(str, i);
        Holder holder = new Holder(dataBuffer);
        Holder holder2 = new Holder(transportAddress);
        BooleanHolder booleanHolder = new BooleanHolder(false);
        Message postProcess = postProcess(dataBuffer, transportAddress, holder, holder2, booleanHolder);
        DataBuffer dataBuffer2 = (DataBuffer) holder.getValue();
        TransportAddress transportAddress2 = (TransportAddress) holder2.getValue();
        boolean value = booleanHolder.getValue();
        if (postProcess == null) {
            IceCandidate localHostCandidate = getLocalHostCandidate();
            if (value) {
                TransportAddress transportAddress3 = new TransportAddress(str, i);
                Holder holder3 = new Holder(null);
                boolean tryGetValue = HashMapExtensions.tryGetValue(this._localRelayedCandidates, transportAddress3.toString(), holder3);
                IceLocalRelayedCandidate iceLocalRelayedCandidate = (IceLocalRelayedCandidate) holder3.getValue();
                if (!tryGetValue) {
                    __log.error(StringExtensions.format("Could not find local server relayed candidate matching incoming binding request for remote IP {0} and relay server {1}:{2}.", transportAddress2.getIPAddress(), str, IntegerExtensions.toString(Integer.valueOf(i))));
                    return;
                }
                localHostCandidate = iceLocalRelayedCandidate;
            }
            if (dataBuffer2 != null) {
                super.getOnIncomingData().invoke(dataBuffer2, localHostCandidate, transportAddress2);
            }
        } else {
            String encodeBuffer = Base64.encodeBuffer(postProcess.getTransactionId());
            IceSendRequestSuccessArgs iceSendRequestSuccessArgs = new IceSendRequestSuccessArgs();
            iceSendRequestSuccessArgs.setRemoteAddress(transportAddress2);
            iceSendRequestSuccessArgs.setResponse(postProcess);
            iceSendRequestSuccessArgs.setRelayed(value);
            if (!this._transactionManager.tryTriggerOnResponse(encodeBuffer, iceSendRequestSuccessArgs) && (postProcess instanceof BindingRequest)) {
                IceCandidate localHostCandidate2 = getLocalHostCandidate();
                if (value) {
                    TransportAddress transportAddress4 = new TransportAddress(str, i);
                    Holder holder4 = new Holder(null);
                    boolean tryGetValue2 = HashMapExtensions.tryGetValue(this._localRelayedCandidates, transportAddress4.toString(), holder4);
                    IceLocalRelayedCandidate iceLocalRelayedCandidate2 = (IceLocalRelayedCandidate) holder4.getValue();
                    if (!tryGetValue2) {
                        __log.error(StringExtensions.format("Could not find local server relayed candidate matching incoming binding request for remote IP {0} and relay server {1}:{2}.", transportAddress2.getIPAddress(), str, IntegerExtensions.toString(Integer.valueOf(i))));
                        return;
                    }
                    localHostCandidate2 = iceLocalRelayedCandidate2;
                }
                IAction3<Message, IceCandidate, TransportAddress> onStunRequest = super.getOnStunRequest();
                if (onStunRequest != null) {
                    onStunRequest.invoke(postProcess, localHostCandidate2, transportAddress2);
                }
            }
        }
        if (!Global.equals(super.getState(), IceGatheringState.Closed) && !Global.equals(super.getState(), IceGatheringState.Failed)) {
            doListen();
        }
    }

    private void nextIpAllocateRequest(ScheduledItem scheduledItem) {
        IceSendMessageArgs iceSendMessageArgs = (IceSendMessageArgs) scheduledItem.getState();
        String iPAddress = iceSendMessageArgs.getAddress().getIPAddress();
        if (super.getServer() != null) {
            Holder holder = new Holder(null);
            boolean tryGetValue = HashMapExtensions.tryGetValue(this.__orderedIPAddresses, iPAddress, holder);
            String str = (String) holder.getValue();
            if (tryGetValue) {
                super.serverAllocate(iceSendMessageArgs.getMessage().getUsername().getValue(), iceSendMessageArgs.getRelayPassword(), str, iceSendMessageArgs.getAddress().getPort(), iceSendMessageArgs.getRaiseServerReflexiveCandidates(), (String) null, (String) null, new IActionDelegate1<ScheduledItem>() {
                    public String getId() {
                        return "fm.liveswitch.IceDatagramSocketManager.sendServerAllocateStunMessage";
                    }

                    public void invoke(ScheduledItem scheduledItem) {
                        IceDatagramSocketManager.this.sendServerAllocateStunMessage(scheduledItem);
                    }
                }, new IActionDelegate1<IceSendRequestSuccessArgs>() {
                    public String getId() {
                        return "fm.liveswitch.IceDatagramSocketManager.processServerResponse";
                    }

                    public void invoke(IceSendRequestSuccessArgs iceSendRequestSuccessArgs) {
                        IceDatagramSocketManager.this.processServerResponse(iceSendRequestSuccessArgs);
                    }
                }, new IActionDelegate1<ScheduledItem>() {
                    public String getId() {
                        return "fm.liveswitch.IceDatagramSocketManager.sendAllocateRequestTimedout";
                    }

                    public void invoke(ScheduledItem scheduledItem) {
                        IceDatagramSocketManager.this.sendAllocateRequestTimedout(scheduledItem);
                    }
                });
            }
        }
        super.removeTransaction(scheduledItem);
    }

    private Message postProcess(DataBuffer dataBuffer, TransportAddress transportAddress, Holder<DataBuffer> holder, Holder<TransportAddress> holder2, BooleanHolder booleanHolder) {
        Message message;
        try {
            message = Message.readFrom(dataBuffer);
            if (message != null) {
                try {
                    if (message instanceof DataIndication) {
                        DataAttribute data = message.getData();
                        XorPeerAddressAttribute xorPeerAddress = message.getXorPeerAddress();
                        holder.setValue(data.getData());
                        holder2.setValue(new TransportAddress(xorPeerAddress.getIPAddress(), xorPeerAddress.getPort()));
                        booleanHolder.setValue(true);
                        return Message.readFrom(holder.getValue());
                    }
                } catch (Exception unused) {
                }
            }
        } catch (Exception unused2) {
            message = null;
        }
        holder.setValue(dataBuffer);
        holder2.setValue(transportAddress);
        booleanHolder.setValue(false);
        return message;
    }

    /* access modifiers changed from: private */
    public void processServerResponse(IceSendRequestSuccessArgs iceSendRequestSuccessArgs) {
        if ((iceSendRequestSuccessArgs.getResponse() instanceof BindingResponse) || (iceSendRequestSuccessArgs.getResponse() instanceof AllocateResponse)) {
            doProcessServerResponse(iceSendRequestSuccessArgs);
        }
    }

    /* access modifiers changed from: private */
    public void sendAllocateRequestTimedout(ScheduledItem scheduledItem) {
        IceSendMessageArgs iceSendMessageArgs = (IceSendMessageArgs) scheduledItem.getState();
        TransportAddress localTransportAddress = getLocalTransportAddress();
        __log.warn(StringExtensions.format("Allocate request from {1} to {0} timed out.", iceSendMessageArgs.getAddress().toString(), localTransportAddress == null ? StringExtensions.empty : localTransportAddress.toString()));
        nextIpAllocateRequest(scheduledItem);
    }

    /* access modifiers changed from: protected */
    public Error sendApplicationData(DataBuffer dataBuffer, IceCandidate iceCandidate, TransportAddress transportAddress) {
        if (Global.equals(super.getState(), IceGatheringState.Closed) || Global.equals(super.getState(), IceGatheringState.Closing) || Global.equals(super.getState(), IceGatheringState.Failed) || Global.equals(super.getState(), IceGatheringState.New)) {
            return new Error(ErrorCode.SocketManagerInvalidState, StringExtensions.format("Attempt to send message while Datagram Socket Manager in {0} state.", (Object) super.getState().toString()));
        }
        if (iceCandidate == null) {
            return new Error(ErrorCode.SocketManagerInvalidState, "Attempted to send payload data to null candidate.");
        }
        if (dataBuffer == null) {
            return new Error(ErrorCode.SocketManagerInvalidState, "Attempted to send null payload data.");
        }
        if (getSocketIsClosed()) {
            super.setError(new Error(ErrorCode.SocketClosed, new Exception("Could not send application data: socket is closed.")));
            super.setState(IceGatheringState.Failed);
            return super.getError();
        }
        try {
            TransportAddress transportAddress2 = iceCandidate.getTransportAddress();
            Holder holder = new Holder(null);
            Holder holder2 = new Holder(transportAddress2);
            super.turnPreProcess(dataBuffer, transportAddress2, transportAddress, holder, holder2);
            DataBuffer dataBuffer2 = (DataBuffer) holder.getValue();
            TransportAddress transportAddress3 = (TransportAddress) holder2.getValue();
            if (!getSocket().getIsClosed()) {
                return getSocket().send(dataBuffer2, transportAddress3.getIPAddress(), transportAddress3.getPort());
            }
            super.setError(new Error(ErrorCode.SocketClosed, new Exception("Could not send application data: socket is not open.")));
            super.setState(IceGatheringState.Failed);
            return super.getError();
        } catch (Exception e) {
            if (__log.getIsDebugEnabled()) {
                __log.debug("Could not send on socket.", e);
            }
            super.setError(new Error(ErrorCode.SocketSendError, e));
            super.setState(IceGatheringState.Failed);
            return super.getError();
        }
    }

    /* access modifiers changed from: private */
    public void sendBindingRequestTimedout(ScheduledItem scheduledItem) {
        IceSendMessageArgs iceSendMessageArgs = (IceSendMessageArgs) scheduledItem.getState();
        TransportAddress localTransportAddress = getLocalTransportAddress();
        __log.warn(StringExtensions.format("Binding request from {1} to {0} timed out.", iceSendMessageArgs.getAddress().toString(), localTransportAddress == null ? StringExtensions.empty : localTransportAddress.toString()));
        String iPAddress = iceSendMessageArgs.getAddress().getIPAddress();
        if (super.getServer() != null) {
            Holder holder = new Holder(null);
            boolean tryGetValue = HashMapExtensions.tryGetValue(this.__orderedIPAddresses, iPAddress, holder);
            String str = (String) holder.getValue();
            if (tryGetValue) {
                super.serverBind(new TransportAddress(str, iceSendMessageArgs.getAddress().getPort()), (String) null, new IActionDelegate1<ScheduledItem>() {
                    public String getId() {
                        return "fm.liveswitch.IceDatagramSocketManager.sendServerBindStunMessage";
                    }

                    public void invoke(ScheduledItem scheduledItem) {
                        IceDatagramSocketManager.this.sendServerBindStunMessage(scheduledItem);
                    }
                }, new IActionDelegate1<IceSendRequestSuccessArgs>() {
                    public String getId() {
                        return "fm.liveswitch.IceDatagramSocketManager.processServerResponse";
                    }

                    public void invoke(IceSendRequestSuccessArgs iceSendRequestSuccessArgs) {
                        IceDatagramSocketManager.this.processServerResponse(iceSendRequestSuccessArgs);
                    }
                }, new IActionDelegate1<ScheduledItem>() {
                    public String getId() {
                        return "fm.liveswitch.IceDatagramSocketManager.sendBindingRequestTimedout";
                    }

                    public void invoke(ScheduledItem scheduledItem) {
                        IceDatagramSocketManager.this.sendBindingRequestTimedout(scheduledItem);
                    }
                });
            }
        }
        super.removeTransaction(scheduledItem);
    }

    /* access modifiers changed from: private */
    public void sendServerAllocateStunMessage(ScheduledItem scheduledItem) {
        IceSendMessageArgs iceSendMessageArgs = (IceSendMessageArgs) scheduledItem.getState();
        iceSendMessageArgs.getMessage().setTransactionTransmitCounter(new TransactionTransmitCounterAttribute(scheduledItem.getInvocationCount()));
        sendStunMessage(iceSendMessageArgs);
    }

    /* access modifiers changed from: private */
    public void sendServerBindStunMessage(ScheduledItem scheduledItem) {
        sendStunMessage((IceSendMessageArgs) scheduledItem.getState());
    }

    /* access modifiers changed from: private */
    public void sendStunMessage(IceSendMessageArgs iceSendMessageArgs) {
        Holder holder = new Holder(null);
        boolean tryDispatchStunMessage = tryDispatchStunMessage(iceSendMessageArgs, holder);
        Error error = (Error) holder.getValue();
        if (!tryDispatchStunMessage && !Global.equals(super.getState(), IceGatheringState.Closed)) {
            IAction1<IceSendRequestFailureArgs> onFailure = iceSendMessageArgs.getOnFailure();
            IceSendRequestFailureArgs iceSendRequestFailureArgs = new IceSendRequestFailureArgs();
            iceSendRequestFailureArgs.setCandidatePairId(iceSendMessageArgs.getCandidatePairId());
            iceSendRequestFailureArgs.setError(error);
            iceSendRequestFailureArgs.setAddress(iceSendMessageArgs.getAddress());
            iceSendRequestFailureArgs.setTurnRelay(iceSendMessageArgs.getTurnRelay());
            if (onFailure != null) {
                onFailure.invoke(iceSendRequestFailureArgs);
            } else {
                __log.debug(StringExtensions.format("Unable to send request: {0}", (Object) error.getDescription()));
            }
            super.setError(error);
            super.setState(IceGatheringState.Failed);
        }
    }

    private void setLocalHostCandidate(IceCandidate iceCandidate) {
        this._localHostCandidate = iceCandidate;
    }

    private void setSocket(DatagramSocket datagramSocket) {
        this._socket = datagramSocket;
    }

    public boolean start(IceGatherOptions iceGatherOptions) {
        super.setGatherOptions(iceGatherOptions);
        synchronized (super.getLock()) {
            if (Global.equals(super.getState(), IceGatheringState.Gathering) || Global.equals(super.getState(), IceGatheringState.Complete)) {
                String format = StringExtensions.format("Could not start Datagram Socket Manager {1}: it is in {0} state.", super.getState().toString(), super.getId());
                __log.debug(format);
                throw new RuntimeException(new Exception(format));
            }
            try {
                TransportAddress transportAddress = null;
                IceCandidate iceCandidate = new IceCandidate(super.getLock(), IceCandidate.generateLocalCandidateFoundation(CandidateType.Host, getSocket().getLocalIPAddress(), (TransportAddress) null, super.getProtocol()), super.getProtocol(), getSocket().getLocalIPAddress(), getSocket().getLocalPort(), CandidateType.Host);
                iceCandidate.setAdapterSpeed(getSocket().getAdapterSpeed());
                iceCandidate.setDispatchStunMessage(new IActionDelegate1<IceSendMessageArgs>() {
                    public String getId() {
                        return "fm.liveswitch.IceDatagramSocketManager.sendStunMessage";
                    }

                    public void invoke(IceSendMessageArgs iceSendMessageArgs) {
                        IceDatagramSocketManager.this.sendStunMessage(iceSendMessageArgs);
                    }
                });
                iceCandidate.setDispatchApplicationData(new IFunctionDelegate3<DataBuffer, IceCandidate, TransportAddress, Error>() {
                    public String getId() {
                        return "fm.liveswitch.IceSocketManager.sendApplicationData";
                    }

                    public Error invoke(DataBuffer dataBuffer, IceCandidate iceCandidate, TransportAddress transportAddress) {
                        return IceDatagramSocketManager.this.sendApplicationData(dataBuffer, iceCandidate, transportAddress);
                    }
                });
                iceCandidate.setRelayProtocol(super.getProtocol());
                setLocalHostCandidate(iceCandidate);
                if (Global.equals(super.getGatherOptions().getPolicy(), IceGatherPolicy.All)) {
                    super.raiseLocalCandidate(iceCandidate);
                }
                if (iceGatherOptions.getPublicIPAddresses() != null && !Global.equals(super.getGatherOptions().getPolicy(), IceGatherPolicy.Relay)) {
                    String[] publicIPAddresses = iceGatherOptions.getPublicIPAddresses();
                    int length = publicIPAddresses.length;
                    int i = 0;
                    while (i < length) {
                        String str = publicIPAddresses[i];
                        String generateLocalCandidateFoundation = IceCandidate.generateLocalCandidateFoundation(CandidateType.ServerReflexive, str, transportAddress, super.getProtocol());
                        IceLocalReflexiveCandidate iceLocalReflexiveCandidate = r7;
                        IceLocalReflexiveCandidate iceLocalReflexiveCandidate2 = new IceLocalReflexiveCandidate(super.getLock(), generateLocalCandidateFoundation, super.getProtocol(), str, iceCandidate.getPort(), CandidateType.ServerReflexive, iceCandidate.getIPAddress(), iceCandidate.getPort());
                        iceLocalReflexiveCandidate.setRelayProtocol(super.getProtocol());
                        iceLocalReflexiveCandidate.setLocalHostBaseCandidate(iceCandidate);
                        super.raiseLocalCandidate(iceLocalReflexiveCandidate);
                        i++;
                        transportAddress = null;
                    }
                }
                super.setState(IceGatheringState.Complete);
            } catch (Exception e) {
                __log.debug(StringExtensions.format("Could not start Datagram Socket Manager {0}:{1}.", super.getId(), e.toString()));
                throw new RuntimeException(e);
            }
        }
        doListen();
        return true;
    }

    private boolean tryDispatchStunMessage(IceSendMessageArgs iceSendMessageArgs, Holder<Error> holder) {
        DataBuffer take;
        try {
            if (getSocketIsClosed()) {
                holder.setValue(new Error(ErrorCode.SocketClosed, new Exception("Datagram socket is closed.")));
                return false;
            }
            take = __dataBufferPool.take(iceSendMessageArgs.getMessage().getLength());
            iceSendMessageArgs.getMessage().writeTo(take);
            TransportAddress address = iceSendMessageArgs.getAddress();
            Holder holder2 = new Holder(null);
            Holder holder3 = new Holder(address);
            super.turnPreProcess(take, address, iceSendMessageArgs.getTurnRelay(), holder2, holder3);
            DataBuffer dataBuffer = (DataBuffer) holder2.getValue();
            TransportAddress transportAddress = (TransportAddress) holder3.getValue();
            if (!getSocket().getIsClosed()) {
                holder.setValue(getSocket().send(dataBuffer, transportAddress.getIPAddress(), transportAddress.getPort()));
                if (holder.getValue() != null) {
                    holder.setValue(new Error(ErrorCode.SocketSendError, new Exception("Unspecified socket send failure.")));
                    take.free();
                    return false;
                }
            }
            holder.setValue(null);
            take.free();
            return true;
        } catch (Exception e) {
            holder.setValue(new Error(ErrorCode.SocketSendError, e));
            __log.error(StringExtensions.format("Could not send message: {0}", (Object) e.getMessage()));
            return false;
        } catch (Throwable th) {
            take.free();
            throw th;
        }
    }

    static Error validateResponse(IceSendMessageArgs iceSendMessageArgs, Message message, TransportAddress transportAddress) {
        if ((iceSendMessageArgs.getMessage() instanceof BindingRequest) && !(message instanceof BindingResponse)) {
            return new Error(ErrorCode.StunInvalidResponseType, new Exception("Client generated a STUN Binding Request but received a message of the type other than Binding Response."));
        }
        if (!iceSendMessageArgs.getMessage().getTransactionId().sequenceEquals(message.getTransactionId())) {
            return new Error(ErrorCode.StunInvalidTransactionId, new Exception(StringExtensions.format("Response transaction ID {0} does not match request transaction ID {1}.", message.getTransactionId().toHexString(), iceSendMessageArgs.getMessage().getTransactionId().toHexString())));
        }
        if (!Global.equals(message.getMessageType(), MessageType.ErrorResponse)) {
            return null;
        }
        ErrorCodeAttribute errorCode = message.getErrorCode();
        if (errorCode == null) {
            return new Error(ErrorCode.StunInvalidErrorCode, new Exception("Error response received, but no error code was supplied."));
        }
        Error createStunError = Error.createStunError(errorCode.getCode(), message);
        if (createStunError != null) {
            return createStunError;
        }
        return new Error(ErrorCode.StunUnknownStunErrorCode, new Exception(StringExtensions.format("Server responded with an unknown error code ({0}).", (Object) IntegerExtensions.toString(Integer.valueOf(errorCode.getCode())))));
    }
}
