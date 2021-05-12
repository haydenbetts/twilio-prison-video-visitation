package fm.liveswitch;

class CoreTransport {
    private String __connectionId;
    private Object __lock;
    private Scheduler __scheduler;
    private BundleTransport _bundleTransport;
    private DtlsTransport _dtlsTransport;
    private IceGatherer _gatherer;
    private IceTransport _iceTransport;
    private String _id;

    public CoreTransport(Object obj, String str, Scheduler scheduler, IceGatherer iceGatherer, IceTransport iceTransport, DtlsTransport dtlsTransport, BundleTransport bundleTransport) {
        setId(Utility.generateId());
        this.__lock = obj;
        this.__connectionId = str;
        this.__scheduler = scheduler;
        setGatherer(iceGatherer);
        setIceTransport(iceTransport);
        setDtlsTransport(dtlsTransport);
        setBundleTransport(bundleTransport);
    }

    public CoreTransport createComplementaryCoreTransport() {
        if (Global.equals(getGatherer().getState(), IceGatheringState.New)) {
            try {
                return new CoreTransport(this.__lock, this.__connectionId, this.__scheduler, getGatherer().createRtcpGatherer(), getIceTransport().createRtcpTransport(), (DtlsTransport) null, getBundleTransport());
            } catch (Exception e) {
                throw new RuntimeException(new Exception(StringExtensions.format("Could not create Complementary (RTCP) Core Transport: {0}", (Object) e.getMessage())));
            }
        } else {
            throw new RuntimeException(new Exception("IceLink: complementary core transport for non-multiplexing cases can only be created when reference core transport is in the new state."));
        }
    }

    public CoreTransport createComplementaryCoreTransport(DtlsCertificate[] dtlsCertificateArr, DtlsCipherSuite[] dtlsCipherSuiteArr, DtlsProtocolVersion dtlsProtocolVersion, DtlsProtocolVersion dtlsProtocolVersion2, DtlsProtocolVersion dtlsProtocolVersion3, DtlsRole dtlsRole) {
        try {
            CoreTransport createComplementaryCoreTransport = createComplementaryCoreTransport();
            createComplementaryCoreTransport.setDtlsTransport(new DtlsTransport(this.__lock, this.__connectionId, createComplementaryCoreTransport.getIceTransport(), dtlsCertificateArr, dtlsCipherSuiteArr, dtlsProtocolVersion, dtlsProtocolVersion2, dtlsProtocolVersion3, dtlsRole));
            return createComplementaryCoreTransport;
        } catch (Exception e) {
            throw new RuntimeException(new Exception(StringExtensions.format("Could not create Complementary (RTCP) Core Transport: {0}", (Object) e.getMessage())));
        }
    }

    public BundleTransport getBundleTransport() {
        return this._bundleTransport;
    }

    public DtlsTransport getDtlsTransport() {
        return this._dtlsTransport;
    }

    public IceGatherer getGatherer() {
        return this._gatherer;
    }

    public IceTransport getIceTransport() {
        return this._iceTransport;
    }

    public String getId() {
        return this._id;
    }

    public TransportInfo getInfo() {
        TransportInfo transportInfo = new TransportInfo();
        transportInfo.setId(getId());
        if (getDtlsTransport() != null) {
            getDtlsTransport().updateInfo(transportInfo);
        }
        if (getIceTransport() != null) {
            getIceTransport().updateInfo(transportInfo);
        }
        return transportInfo;
    }

    public int getReceiveBufferSize() {
        return getGatherer().getReceiveBufferSize();
    }

    public int getSendBufferSize() {
        return getGatherer().getSendBufferSize();
    }

    public TransportStats getStats() {
        TransportStats transportStats = new TransportStats();
        transportStats.setId(getId());
        transportStats.setTimestamp(DateExtensions.getUtcNow());
        if (getDtlsTransport() != null) {
            getDtlsTransport().updateStats(transportStats);
        }
        if (getIceTransport() != null) {
            getIceTransport().updateStats(transportStats);
        }
        return transportStats;
    }

    public void removeDtlsTransport() {
        setDtlsTransport((DtlsTransport) null);
    }

    private void setBundleTransport(BundleTransport bundleTransport) {
        this._bundleTransport = bundleTransport;
    }

    private void setDtlsTransport(DtlsTransport dtlsTransport) {
        this._dtlsTransport = dtlsTransport;
    }

    private void setGatherer(IceGatherer iceGatherer) {
        this._gatherer = iceGatherer;
    }

    private void setIceTransport(IceTransport iceTransport) {
        this._iceTransport = iceTransport;
    }

    public void setId(String str) {
        this._id = str;
    }
}
