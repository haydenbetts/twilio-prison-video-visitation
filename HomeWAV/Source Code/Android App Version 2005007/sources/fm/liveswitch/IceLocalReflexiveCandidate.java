package fm.liveswitch;

class IceLocalReflexiveCandidate extends IceCandidate {
    private IceCandidate __base;

    /* access modifiers changed from: package-private */
    public IceCandidate getLocalHostBaseCandidate() {
        return this.__base;
    }

    public IceLocalReflexiveCandidate(Object obj, String str, ProtocolType protocolType, String str2, int i, CandidateType candidateType, String str3, int i2) {
        super(obj, str, protocolType, str2, i, candidateType, str3, i2);
    }

    /* access modifiers changed from: package-private */
    public void setLocalHostBaseCandidate(IceCandidate iceCandidate) {
        this.__base = iceCandidate;
        setDispatchApplicationData(iceCandidate.getDispatchApplicationData());
        setDispatchStunMessage(iceCandidate.getDispatchStunMessage());
    }
}
