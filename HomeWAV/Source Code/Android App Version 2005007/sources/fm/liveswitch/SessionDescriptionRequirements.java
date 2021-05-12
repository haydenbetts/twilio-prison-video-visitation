package fm.liveswitch;

class SessionDescriptionRequirements {
    private BundlePolicy _bundlePolicy;
    private MultiplexPolicy _multiplexPolicy;
    private String _tieBreaker;
    private TrickleIcePolicy _trickleIcePolicy;

    public BundlePolicy getBundlePolicy() {
        return this._bundlePolicy;
    }

    public MultiplexPolicy getMultiplexPolicy() {
        return this._multiplexPolicy;
    }

    public String getTieBreaker() {
        return this._tieBreaker;
    }

    public TrickleIcePolicy getTrickleIcePolicy() {
        return this._trickleIcePolicy;
    }

    public SessionDescriptionRequirements(String str, TrickleIcePolicy trickleIcePolicy, MultiplexPolicy multiplexPolicy, BundlePolicy bundlePolicy) {
        setTieBreaker(str);
        setTrickleIcePolicy(trickleIcePolicy);
        setMultiplexPolicy(multiplexPolicy);
        setBundlePolicy(bundlePolicy);
    }

    public void setBundlePolicy(BundlePolicy bundlePolicy) {
        this._bundlePolicy = bundlePolicy;
    }

    public void setMultiplexPolicy(MultiplexPolicy multiplexPolicy) {
        this._multiplexPolicy = multiplexPolicy;
    }

    public void setTieBreaker(String str) {
        this._tieBreaker = str;
    }

    public void setTrickleIcePolicy(TrickleIcePolicy trickleIcePolicy) {
        this._trickleIcePolicy = trickleIcePolicy;
    }
}
