package fm.liveswitch;

import com.microsoft.appcenter.Constants;
import fm.liveswitch.sdp.ice.CandidateAttribute;
import fm.liveswitch.sdp.ice.CandidateType;
import fm.liveswitch.sdp.ice.TransportProtocol;
import fm.liveswitch.stun.TransactionTransmitCounterAttribute;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.UByte;

class IceCandidate {
    private static byte _hostTypePreference = 126;
    private static byte _peerReflexivePreference = 110;
    private static byte _serverReflexivePreference = 100;
    private static byte _tcpRelayPreference = 0;
    private static byte _udpRelayPreference = 50;
    private String __ipAddress;
    private Object __lock;
    /* access modifiers changed from: private */
    public List<IAction1<Long>> __onPriorityChange;
    /* access modifiers changed from: private */
    public List<IAction2<IceLocalCandidateState, Error>> __onStateChange;
    private long __priority;
    private IceLocalCandidateState __state;
    private long _adapterSpeed;
    private IFunction3<DataBuffer, IceCandidate, TransportAddress, Error> _dispatchApplicationData;
    private IAction1<IceSendMessageArgs> _dispatchStunMessage;
    private Error _error;
    private String _foundation;
    private String _id;
    private IAction1<Long> _onPriorityChange;
    private IAction2<IceLocalCandidateState, Error> _onStateChange;
    private String _password;
    private int _port;
    private ProtocolType _protocol;
    private String _relatedIPAddress;
    private int _relatedPort;
    private ProtocolType _relayProtocol;
    private CandidateType _type;
    private String _username;

    public static int getUnset() {
        return -1;
    }

    /* access modifiers changed from: package-private */
    public void addOnPriorityChange(IAction1<Long> iAction1) {
        if (iAction1 != null) {
            if (this._onPriorityChange == null) {
                this._onPriorityChange = new IAction1<Long>() {
                    public void invoke(Long l) {
                        Iterator it = new ArrayList(IceCandidate.this.__onPriorityChange).iterator();
                        while (it.hasNext()) {
                            ((IAction1) it.next()).invoke(l);
                        }
                    }
                };
            }
            this.__onPriorityChange.add(iAction1);
        }
    }

    /* access modifiers changed from: package-private */
    public void addOnStateChange(IAction2<IceLocalCandidateState, Error> iAction2) {
        if (iAction2 != null) {
            if (this._onStateChange == null) {
                this._onStateChange = new IAction2<IceLocalCandidateState, Error>() {
                    public void invoke(IceLocalCandidateState iceLocalCandidateState, Error error) {
                        Iterator it = new ArrayList(IceCandidate.this.__onStateChange).iterator();
                        while (it.hasNext()) {
                            ((IAction2) it.next()).invoke(iceLocalCandidateState, error);
                        }
                    }
                };
            }
            this.__onStateChange.add(iAction2);
        }
    }

    public static int calculateLocalPreference(CandidateType candidateType, long j, IceComponent iceComponent, ProtocolType protocolType) {
        byte b;
        int i;
        if (candidateType == CandidateType.Host) {
            b = _hostTypePreference;
        } else if (candidateType == CandidateType.ServerReflexive) {
            b = _serverReflexivePreference;
        } else if (candidateType == CandidateType.Relayed) {
            b = (Global.equals(protocolType, ProtocolType.Udp) || Global.equals(protocolType, ProtocolType.Unknown)) ? _udpRelayPreference : _tcpRelayPreference;
        } else {
            b = candidateType == CandidateType.PeerReflexive ? _peerReflexivePreference : 0;
        }
        if (iceComponent == IceComponent.Rtp) {
            i = 1;
        } else {
            i = iceComponent == IceComponent.Rtcp ? 2 : 0;
        }
        if (i < 1 || i > 256) {
            throw new RuntimeException(new Exception("ComponentId must be an integer from 1 to 256 inclusive."));
        }
        int i2 = (int) ((j - ((long) ((b * UByte.MIN_VALUE) - (256 - i)))) / 256);
        if (i2 >= 0) {
            return i2;
        }
        return 0;
    }

    public static long calculatePriority(CandidateType candidateType, int i, IceComponent iceComponent, ProtocolType protocolType) {
        byte b;
        int i2 = 0;
        if (candidateType == CandidateType.Host) {
            b = _hostTypePreference;
        } else if (candidateType == CandidateType.ServerReflexive) {
            b = _serverReflexivePreference;
        } else if (candidateType == CandidateType.Relayed) {
            b = (Global.equals(protocolType, ProtocolType.Udp) || Global.equals(protocolType, ProtocolType.Unknown)) ? _udpRelayPreference : _tcpRelayPreference;
        } else {
            b = candidateType == CandidateType.PeerReflexive ? _peerReflexivePreference : 0;
        }
        if (iceComponent == IceComponent.Rtp) {
            i2 = 1;
        } else if (iceComponent == IceComponent.Rtcp) {
            i2 = 2;
        }
        if (i2 < 1 || i2 > 256) {
            throw new RuntimeException(new Exception("ComponentId must be an integer from 1 to 256 inclusive."));
        }
        long j = (long) ((b * UByte.MIN_VALUE) + (i * 256) + (256 - i2));
        if (j >= 0) {
            return j;
        }
        return (long) getUnset();
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            IceCandidate iceCandidate = (IceCandidate) Global.tryCast(obj, IceCandidate.class);
            if (!Global.equals(getIPAddress(), iceCandidate.getIPAddress()) || getPort() != iceCandidate.getPort()) {
                return false;
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static IceCandidate fromSdpCandidateAttribute(Object obj, CandidateAttribute candidateAttribute) {
        CandidateType candidateType;
        CandidateType candidateType2 = CandidateType.Host;
        if (Global.equals(candidateAttribute.getCandidateType(), CandidateType.getPeerReflexive())) {
            candidateType = CandidateType.PeerReflexive;
        } else if (Global.equals(candidateAttribute.getCandidateType(), CandidateType.getServerReflexive())) {
            candidateType = CandidateType.ServerReflexive;
        } else if (Global.equals(candidateAttribute.getCandidateType(), CandidateType.getRelayed())) {
            candidateType = CandidateType.Relayed;
        } else if (Global.equals(candidateAttribute.getCandidateType(), CandidateType.getHost())) {
            candidateType = CandidateType.Host;
        } else {
            throw new RuntimeException(new Exception("Only host, server reflexive, peer reflexive, and relayed candidates are supported by ICE."));
        }
        CandidateType candidateType3 = candidateType;
        if (candidateAttribute.getPriority() > 0 || candidateAttribute.getPriority() == -1) {
            long priority = candidateAttribute.getPriority();
            IceCandidate iceCandidate = new IceCandidate(obj, candidateAttribute.getFoundation(), StringExtensions.toLower(candidateAttribute.getProtocol()).equals(StringExtensions.toLower(TransportProtocol.getUdp())) ? ProtocolType.Udp : ProtocolType.Tcp, candidateAttribute.getConnectionAddress(), candidateAttribute.getPort(), candidateType3, candidateAttribute.getRelatedAddress(), candidateAttribute.getRelatedPort());
            iceCandidate.setPriority(priority);
            return iceCandidate;
        }
        throw new RuntimeException(new Exception("Invalid priority signaled for remote candidate."));
    }

    public CandidateAttribute generateAttribute(int i) {
        String host = CandidateType.getHost();
        if (Global.equals(getType(), CandidateType.PeerReflexive)) {
            host = CandidateType.getPeerReflexive();
        } else if (Global.equals(getType(), CandidateType.Relayed)) {
            host = CandidateType.getRelayed();
        } else if (Global.equals(getType(), CandidateType.ServerReflexive)) {
            host = CandidateType.getServerReflexive();
        }
        return new CandidateAttribute(getFoundation(), getPriority(), getIPAddress(), getPort(), host, getRelatedIPAddress(), getRelatedPort(), Global.equals(getProtocol(), ProtocolType.Tcp) ? TransportProtocol.getTcp() : TransportProtocol.getUdp(), i);
    }

    static String generateIpPortString(String str, int i) {
        if (str == null) {
            return "??:??";
        }
        if (Global.equals(LocalNetwork.getAddressType(str), AddressType.IPv6)) {
            str = StringExtensions.concat("[", sanitiseIpv6Address(str), "]");
        }
        return StringExtensions.concat(str, Constants.COMMON_SCHEMA_PREFIX_SEPARATOR, IntegerExtensions.toString(Integer.valueOf(i)));
    }

    static String generateLocalCandidateFoundation(CandidateType candidateType, String str, TransportAddress transportAddress, ProtocolType protocolType) {
        String iPAddress = (transportAddress == null || transportAddress.getIPAddress() == null) ? "-" : transportAddress.getIPAddress();
        HashType hashType = HashType.Sha1;
        Object[] objArr = new Object[4];
        objArr[0] = candidateType.toString();
        objArr[1] = str;
        objArr[2] = iPAddress;
        objArr[3] = Global.equals(protocolType, ProtocolType.Udp) ? "Udp" : "Tcp";
        return StringExtensions.substring(HashContextBase.compute(hashType, StringExtensions.format("{0}|{1}|{2}|{3}", objArr)).toHexString(), 0, 32);
    }

    /* access modifiers changed from: package-private */
    public long getAdapterSpeed() {
        return this._adapterSpeed;
    }

    /* access modifiers changed from: package-private */
    public IFunction3<DataBuffer, IceCandidate, TransportAddress, Error> getDispatchApplicationData() {
        return this._dispatchApplicationData;
    }

    /* access modifiers changed from: package-private */
    public IAction1<IceSendMessageArgs> getDispatchStunMessage() {
        return this._dispatchStunMessage;
    }

    public Error getError() {
        return this._error;
    }

    public String getFoundation() {
        return this._foundation;
    }

    public String getId() {
        return this._id;
    }

    public CandidateInfo getInfo() {
        CandidateInfo candidateInfo = new CandidateInfo();
        candidateInfo.setId(getId());
        candidateInfo.setIPAddress(getIPAddress());
        candidateInfo.setPort(new NullableInteger(getPort()));
        candidateInfo.setRelatedIPAddress(getRelatedIPAddress());
        candidateInfo.setRelatedPort(new NullableInteger(getRelatedPort()));
        candidateInfo.setPriority(new NullableLong(getPriority()));
        candidateInfo.setType(CandidateUtility.typeToString(getType()));
        candidateInfo.setProtocol(CandidateUtility.protocolTypeToString(getProtocol()));
        candidateInfo.setRelayProtocol(CandidateUtility.protocolTypeToString(getRelayProtocol()));
        return candidateInfo;
    }

    public String getIPAddress() {
        return this.__ipAddress;
    }

    /* access modifiers changed from: protected */
    public Object getLock() {
        return this.__lock;
    }

    /* access modifiers changed from: package-private */
    public String getPassword() {
        return this._password;
    }

    public int getPort() {
        return this._port;
    }

    public long getPriority() {
        return this.__priority;
    }

    public ProtocolType getProtocol() {
        return this._protocol;
    }

    public String getRelatedIPAddress() {
        return this._relatedIPAddress;
    }

    public int getRelatedPort() {
        return this._relatedPort;
    }

    public ProtocolType getRelayProtocol() {
        return this._relayProtocol;
    }

    public IceLocalCandidateState getState() {
        return this.__state;
    }

    public CandidateStats getStats() {
        CandidateStats candidateStats = new CandidateStats();
        candidateStats.setId(getId());
        candidateStats.setTimestamp(DateExtensions.getUtcNow());
        candidateStats.setIPAddress(getIPAddress());
        candidateStats.setPort(getPort());
        candidateStats.setRelatedIPAddress(getRelatedIPAddress());
        candidateStats.setRelatedPort(getRelatedPort());
        candidateStats.setPriority(getPriority());
        candidateStats.setProtocol(getProtocol());
        candidateStats.setRelayProtocol(getRelayProtocol());
        candidateStats.setType(getType());
        return candidateStats;
    }

    public TransportAddress getTransportAddress() {
        return new TransportAddress(getIPAddress(), getPort());
    }

    public CandidateType getType() {
        return this._type;
    }

    /* access modifiers changed from: package-private */
    public String getUsername() {
        return this._username;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public IceCandidate(Object obj, String str, ProtocolType protocolType, String str2, int i, CandidateType candidateType) {
        this(obj, str, protocolType, str2, i, candidateType, (String) null, getUnset());
    }

    public IceCandidate(Object obj, String str, ProtocolType protocolType, String str2, int i, CandidateType candidateType, String str3, int i2) {
        this.__onPriorityChange = new ArrayList();
        this.__onStateChange = new ArrayList();
        this._onPriorityChange = null;
        this._onStateChange = null;
        setId(Utility.generateId());
        setRelayProtocol(ProtocolType.Unknown);
        if (obj == null) {
            this.__lock = new Object();
        } else {
            this.__lock = obj;
        }
        setFoundation(str);
        setProtocol(protocolType);
        setIPAddress(str2);
        setPort(i);
        setType(candidateType);
        setRelatedIPAddress(str3);
        setRelatedPort(i2);
        setState(IceLocalCandidateState.Ready);
    }

    /* access modifiers changed from: package-private */
    public void notifyOfSocketError(Error error) {
        synchronized (this.__lock) {
            setError(error);
            setState(IceLocalCandidateState.Failed);
            if (Global.equals(getType(), CandidateType.Relayed)) {
                ((IceLocalRelayedCandidate) this).processDSMFailure();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void removeOnPriorityChange(IAction1<Long> iAction1) {
        IAction1<T> findIActionDelegate1WithId;
        if ((iAction1 instanceof IActionDelegate1) && (findIActionDelegate1WithId = Global.findIActionDelegate1WithId(this.__onPriorityChange, ((IActionDelegate1) iAction1).getId())) != null) {
            iAction1 = findIActionDelegate1WithId;
        }
        this.__onPriorityChange.remove(iAction1);
        if (this.__onPriorityChange.size() == 0) {
            this._onPriorityChange = null;
        }
    }

    /* access modifiers changed from: package-private */
    public void removeOnStateChange(IAction2<IceLocalCandidateState, Error> iAction2) {
        IAction2<T1, T2> findIActionDelegate2WithId;
        if ((iAction2 instanceof IActionDelegate2) && (findIActionDelegate2WithId = Global.findIActionDelegate2WithId(this.__onStateChange, ((IActionDelegate2) iAction2).getId())) != null) {
            iAction2 = findIActionDelegate2WithId;
        }
        this.__onStateChange.remove(iAction2);
        if (this.__onStateChange.size() == 0) {
            this._onStateChange = null;
        }
    }

    private static String sanitiseIpv6Address(String str) {
        if (!Global.equals(LocalNetwork.getAddressType(str), AddressType.IPv6)) {
            return str;
        }
        return ArrayExtensions.getLength((Object[]) StringExtensions.split(str, new char[]{':'})) == 8 ? str.replace("::", ":0:") : str;
    }

    public Error send(DataBuffer dataBuffer, IceCandidate iceCandidate) {
        if (Global.equals(getState(), IceLocalCandidateState.Ready)) {
            TransportAddress turnServer = Global.equals(getType(), CandidateType.Relayed) ? ((IceLocalRelayedCandidate) this).getTurnServer() : null;
            IFunction3<DataBuffer, IceCandidate, TransportAddress, Error> dispatchApplicationData = getDispatchApplicationData();
            if (dispatchApplicationData != null) {
                return dispatchApplicationData.invoke(dataBuffer, iceCandidate, turnServer);
            }
            Log.error(StringExtensions.format("Candidate {0} does not have send method assigned. Cannot send message.", (Object) toString()));
        }
        return null;
    }

    public void sendStunAndInsertAttemptMessage(ScheduledItem scheduledItem) {
        ((IceSendMessageArgs) scheduledItem.getState()).getMessage().setTransactionTransmitCounter(new TransactionTransmitCounterAttribute(scheduledItem.getInvocationCount()));
        sendStunMessage(scheduledItem);
    }

    /* access modifiers changed from: package-private */
    public void sendStunMessage(IceSendMessageArgs iceSendMessageArgs) {
        iceSendMessageArgs.setDispatchTimestamp(Scheduler.getCurrentTime());
        IAction1<IceSendMessageArgs> dispatchStunMessage = getDispatchStunMessage();
        if (dispatchStunMessage != null) {
            try {
                dispatchStunMessage.invoke(iceSendMessageArgs);
            } catch (Exception e) {
                Log.error(StringExtensions.format("IceLink candidate: Could not send stun message. {0}", (Object) e.getMessage()));
            }
        }
    }

    public void sendStunMessage(ScheduledItem scheduledItem) {
        IceSendMessageArgs iceSendMessageArgs = (IceSendMessageArgs) scheduledItem.getState();
        synchronized (this.__lock) {
            if (Global.equals(getState(), IceLocalCandidateState.Ready) || (Global.equals(getType(), CandidateType.Relayed) && Global.equals(((IceLocalRelayedCandidate) this).getRelayState(), IceLocalRelayedCandidateState.Closing))) {
                sendStunMessage(iceSendMessageArgs);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setAdapterSpeed(long j) {
        this._adapterSpeed = j;
    }

    /* access modifiers changed from: package-private */
    public void setDispatchApplicationData(IFunction3<DataBuffer, IceCandidate, TransportAddress, Error> iFunction3) {
        this._dispatchApplicationData = iFunction3;
    }

    /* access modifiers changed from: package-private */
    public void setDispatchStunMessage(IAction1<IceSendMessageArgs> iAction1) {
        this._dispatchStunMessage = iAction1;
    }

    /* access modifiers changed from: protected */
    public void setError(Error error) {
        this._error = error;
    }

    private void setFoundation(String str) {
        this._foundation = str;
    }

    public void setId(String str) {
        this._id = str;
    }

    private void setIPAddress(String str) {
        this.__ipAddress = sanitiseIpv6Address(str);
    }

    /* access modifiers changed from: package-private */
    public void setPassword(String str) {
        this._password = str;
    }

    private void setPort(int i) {
        this._port = i;
    }

    /* access modifiers changed from: package-private */
    public void setPriority(long j) {
        if (this.__priority != j) {
            this.__priority = j;
            IAction1<Long> iAction1 = this._onPriorityChange;
            if (iAction1 != null) {
                iAction1.invoke(Long.valueOf(j));
            }
        }
    }

    private void setProtocol(ProtocolType protocolType) {
        this._protocol = protocolType;
    }

    private void setRelatedIPAddress(String str) {
        this._relatedIPAddress = str;
    }

    private void setRelatedPort(int i) {
        this._relatedPort = i;
    }

    /* access modifiers changed from: package-private */
    public void setRelayProtocol(ProtocolType protocolType) {
        this._relayProtocol = protocolType;
    }

    /* access modifiers changed from: protected */
    public void setState(IceLocalCandidateState iceLocalCandidateState) {
        synchronized (this.__lock) {
            if (!Global.equals(iceLocalCandidateState, this.__state)) {
                this.__state = iceLocalCandidateState;
                IAction2<IceLocalCandidateState, Error> iAction2 = this._onStateChange;
                if (iAction2 != null) {
                    iAction2.invoke(iceLocalCandidateState, getError());
                }
            }
        }
    }

    private void setType(CandidateType candidateType) {
        this._type = candidateType;
    }

    /* access modifiers changed from: package-private */
    public void setUsername(String str) {
        this._username = str;
    }

    /* access modifiers changed from: package-private */
    public void stop() {
        synchronized (this.__lock) {
            setState(IceLocalCandidateState.Closed);
        }
    }

    public Candidate toCandidate(int i, int i2) {
        Candidate candidate = new Candidate();
        candidate.setSdpCandidateAttribute(generateAttribute(i));
        candidate.setSdpMediaIndex(i2);
        candidate.setRelayProtocol(getRelayProtocol());
        candidate.setProtocol(getProtocol());
        return candidate;
    }

    public String toString() {
        return generateIpPortString(getIPAddress(), getPort());
    }

    /* access modifiers changed from: package-private */
    public void updateProperties(IceCandidate iceCandidate) {
        setFoundation(iceCandidate.getFoundation());
        setProtocol(iceCandidate.getProtocol());
        setType(iceCandidate.getType());
        setPriority(iceCandidate.getPriority());
    }
}
