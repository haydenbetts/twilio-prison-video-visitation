package fm.liveswitch.opus;

public class EncoderConfig {
    private ApplicationType __application = ApplicationType.getVoip();
    private Bandwidth __bandwidth = Bandwidth.getFullBand();
    private NativeEncoderConfig __config = new NativeEncoderConfig();
    private ExpertFrameDuration __expertFrameDuration = ExpertFrameDuration.getArgument();
    private Bandwidth __maxBandwidth = Bandwidth.getFullBand();
    private Signal __signal = Signal.getVoice();

    public EncoderConfig deepCopy() {
        EncoderConfig encoderConfig = new EncoderConfig();
        encoderConfig.__application = this.__application;
        encoderConfig.__signal = this.__signal;
        encoderConfig.__expertFrameDuration = this.__expertFrameDuration;
        encoderConfig.__maxBandwidth = this.__maxBandwidth;
        encoderConfig.__bandwidth = this.__bandwidth;
        encoderConfig.__config = this.__config.deepCopy();
        return encoderConfig;
    }

    public ApplicationType getApplication() {
        return this.__application;
    }

    public static int getAuto() {
        return OpusAuto.getValue();
    }

    public Bandwidth getBandwidth() {
        return this.__bandwidth;
    }

    public int getComplexity() {
        return this.__config.getComplexity();
    }

    public int getDtx() {
        return this.__config.getDtx();
    }

    public ExpertFrameDuration getExpertFrameDuration() {
        return this.__expertFrameDuration;
    }

    public int getForceChannels() {
        return this.__config.getForceChannels();
    }

    public boolean getForwardErrorCorrection() {
        return this.__config.getForwardErrorCorrection() == 1;
    }

    public boolean getIsPredictionDisabled() {
        return this.__config.getIsPredictionDisabled() == 1;
    }

    public boolean getIsVbr() {
        return this.__config.getIsVbr() == 1;
    }

    public Bandwidth getMaxBandwidth() {
        return this.__maxBandwidth;
    }

    /* access modifiers changed from: package-private */
    public NativeEncoderConfig getNativeConfig() {
        return this.__config;
    }

    public int getPacketLossPercent() {
        return this.__config.getPacketLossPercent();
    }

    public Signal getSignal() {
        return this.__signal;
    }

    public boolean getUseConstrainedVBR() {
        return this.__config.getUseConstrainedVBR() == 1;
    }

    public void setApplication(ApplicationType applicationType) {
        this.__application = applicationType;
        this.__config.setApplication(applicationType.getValue());
    }

    public void setBandwidth(Bandwidth bandwidth) {
        this.__bandwidth = bandwidth;
        this.__config.setBandwidth(bandwidth.getValue());
    }

    public void setComplexity(int i) {
        this.__config.setComplexity(i);
    }

    public void setDtx(int i) {
        this.__config.setDtx(i);
    }

    public void setExpertFrameDuration(ExpertFrameDuration expertFrameDuration) {
        this.__expertFrameDuration = expertFrameDuration;
        this.__config.setExpertFrameDuration(expertFrameDuration.getValue());
    }

    public void setForceChannels(int i) {
        this.__config.setForceChannels(i);
    }

    public void setForwardErrorCorrection(boolean z) {
        this.__config.setForwardErrorCorrection(z ? 1 : 0);
    }

    public void setIsPredictionDisabled(boolean z) {
        this.__config.setIsPredictionDisabled(z ? 1 : 0);
    }

    public void setIsVbr(boolean z) {
        this.__config.setIsVbr(z ? 1 : 0);
    }

    public void setMaxBandwidth(Bandwidth bandwidth) {
        this.__maxBandwidth = bandwidth;
        this.__config.setMaxBandwidth(bandwidth.getValue());
    }

    public void setPacketLossPercent(int i) {
        this.__config.setPacketLossPercent(i);
    }

    public void setSignal(Signal signal) {
        this.__signal = signal;
        this.__config.setSignal(signal.getValue());
    }

    public void setUseConstrainedVBR(boolean z) {
        this.__config.setUseConstrainedVBR(z ? 1 : 0);
    }
}
