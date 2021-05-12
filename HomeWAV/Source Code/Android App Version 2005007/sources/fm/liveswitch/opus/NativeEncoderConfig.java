package fm.liveswitch.opus;

class NativeEncoderConfig {
    private int __application;
    private int __bandwidth;
    private int __complexity = 5;
    private int __dtx = 0;
    private int __expertFrameDuration;
    private int __forceChannels = -1000;
    private int __forwardErrorCorrection = 0;
    private int __isPredictionDisabled = 0;
    private int __isVbr = 1;
    private int __maxBandwidth;
    private int __packetLossPercent = 5;
    private int __signal;
    private int __useConstrainedVBR = 1;

    public NativeEncoderConfig deepCopy() {
        NativeEncoderConfig nativeEncoderConfig = new NativeEncoderConfig();
        nativeEncoderConfig.__application = this.__application;
        nativeEncoderConfig.__forceChannels = this.__forceChannels;
        nativeEncoderConfig.__maxBandwidth = this.__maxBandwidth;
        nativeEncoderConfig.__bandwidth = this.__bandwidth;
        nativeEncoderConfig.__dtx = this.__dtx;
        nativeEncoderConfig.__complexity = this.__complexity;
        nativeEncoderConfig.__forwardErrorCorrection = this.__forwardErrorCorrection;
        nativeEncoderConfig.__packetLossPercent = this.__packetLossPercent;
        nativeEncoderConfig.__isVbr = this.__isVbr;
        nativeEncoderConfig.__useConstrainedVBR = this.__useConstrainedVBR;
        nativeEncoderConfig.__signal = this.__signal;
        nativeEncoderConfig.__expertFrameDuration = this.__expertFrameDuration;
        nativeEncoderConfig.__isPredictionDisabled = this.__isPredictionDisabled;
        return nativeEncoderConfig;
    }

    public int getApplication() {
        return this.__application;
    }

    public int getBandwidth() {
        return this.__bandwidth;
    }

    public int getComplexity() {
        return this.__complexity;
    }

    public int getDtx() {
        return this.__dtx;
    }

    public int getExpertFrameDuration() {
        return this.__expertFrameDuration;
    }

    public int getForceChannels() {
        return this.__forceChannels;
    }

    public int getForwardErrorCorrection() {
        return this.__forwardErrorCorrection;
    }

    public int getIsPredictionDisabled() {
        return this.__isPredictionDisabled;
    }

    public int getIsVbr() {
        return this.__isVbr;
    }

    public int getMaxBandwidth() {
        return this.__maxBandwidth;
    }

    public int getPacketLossPercent() {
        return this.__packetLossPercent;
    }

    public int getSignal() {
        return this.__signal;
    }

    public int getUseConstrainedVBR() {
        return this.__useConstrainedVBR;
    }

    public void setApplication(int i) {
        this.__application = i;
    }

    public void setBandwidth(int i) {
        this.__bandwidth = i;
    }

    public void setComplexity(int i) {
        this.__complexity = i;
    }

    public void setDtx(int i) {
        this.__dtx = i;
    }

    public void setExpertFrameDuration(int i) {
        this.__expertFrameDuration = i;
    }

    public void setForceChannels(int i) {
        this.__forceChannels = i;
    }

    public void setForwardErrorCorrection(int i) {
        this.__forwardErrorCorrection = i;
    }

    public void setIsPredictionDisabled(int i) {
        this.__isPredictionDisabled = i;
    }

    public void setIsVbr(int i) {
        this.__isVbr = i;
    }

    public void setMaxBandwidth(int i) {
        this.__maxBandwidth = i;
    }

    public void setPacketLossPercent(int i) {
        this.__packetLossPercent = i;
    }

    public void setSignal(int i) {
        this.__signal = i;
    }

    public void setUseConstrainedVBR(int i) {
        this.__useConstrainedVBR = i;
    }
}
