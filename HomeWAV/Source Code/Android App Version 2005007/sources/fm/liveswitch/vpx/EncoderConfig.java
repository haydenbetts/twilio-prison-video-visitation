package fm.liveswitch.vpx;

public class EncoderConfig {
    private BitDepth __bitDepth;
    private NativeEncoderConfig __config = new NativeEncoderConfig();
    private EndUsageMode __endUsageMode;
    private KeyframeMode __keyframeMode;
    private TemporalLayerMode __temporalLayerMode;

    public EncoderConfig deepCopy() {
        EncoderConfig encoderConfig = new EncoderConfig();
        encoderConfig.__bitDepth = this.__bitDepth;
        encoderConfig.__keyframeMode = this.__keyframeMode;
        encoderConfig.__endUsageMode = this.__endUsageMode;
        encoderConfig.__temporalLayerMode = this.__temporalLayerMode;
        encoderConfig.__config = this.__config.deepCopy();
        return encoderConfig;
    }

    public EncoderConfig() {
        setBitDepth(BitDepth.getBits8());
        setKeyframeMode(KeyframeMode.getAuto());
        setEndUsageMode(EndUsageMode.getCbr());
        setTemporalLayerMode(TemporalLayerMode.getNoLayering());
    }

    public BitDepth getBitDepth() {
        return this.__bitDepth;
    }

    public int getBufferInitialSize() {
        return this.__config.getBufferInitialSize();
    }

    public int getBufferOptimalSize() {
        return this.__config.getBufferOptimalSize();
    }

    public int getBufferSize() {
        return this.__config.getBufferSize();
    }

    public int getCpu() {
        return this.__config.getCpu();
    }

    public int getDropFrameThreshold() {
        return this.__config.getDropFrameThreshold();
    }

    public EndUsageMode getEndUsageMode() {
        return this.__endUsageMode;
    }

    public int getErrorResilient() {
        return this.__config.getErrorResilient();
    }

    public int getInputBitDepth() {
        return this.__config.getInputBitDepth();
    }

    public int getKeyframeMaxDistance() {
        return this.__config.getKeyframeMaxDistance();
    }

    public int getKeyframeMinDistance() {
        return this.__config.getKeyframeMinDistance();
    }

    public KeyframeMode getKeyframeMode() {
        return this.__keyframeMode;
    }

    public int getLagInFrames() {
        return this.__config.getLagInFrames();
    }

    public int[] getLayerTargetBitrate() {
        return this.__config.getLayerTargetBitrate();
    }

    public int getMaxQuantizer() {
        return this.__config.getMaxQuantizer();
    }

    public int getMinQuantizer() {
        return this.__config.getMinQuantizer();
    }

    /* access modifiers changed from: package-private */
    public NativeEncoderConfig getNativeConfig() {
        return this.__config;
    }

    public int getOvershootPercentage() {
        return this.__config.getOvershootPercentage();
    }

    public int getProfile() {
        return this.__config.getProfile();
    }

    public boolean getResizeAllowed() {
        return this.__config.getResizeAllowed() == 1;
    }

    public int getResizeDownThreshold() {
        return this.__config.getResizeDownThreshold();
    }

    public int getResizeUpThreshold() {
        return this.__config.getResizeUpThreshold();
    }

    public int getScaledHeight() {
        return this.__config.getScaledHeight();
    }

    public int getScaledWidth() {
        return this.__config.getScaledWidth();
    }

    public int[] getSpatialEnableAutoAltRef() {
        return this.__config.getSpatialEnableAutoAltRef();
    }

    public int getSpatialLayers() {
        return this.__config.getSpatialLayers();
    }

    public int[] getSpatialTargetBitrate() {
        return this.__config.getSpatialTargetBitrate();
    }

    public TemporalLayerMode getTemporalLayerMode() {
        return this.__temporalLayerMode;
    }

    public int getTemporalLayers() {
        return this.__config.getTemporalLayers();
    }

    public int[] getTemporalPattern() {
        return this.__config.getTemporalPattern();
    }

    public int getTemporalPeriodicity() {
        return this.__config.getTemporalPeriodicity();
    }

    public int[] getTemporalRateDecimator() {
        return this.__config.getTemporalRateDecimator();
    }

    public int[] getTemporalTargetBitrate() {
        return this.__config.getTemporalTargetBitrate();
    }

    public int getThreads() {
        return this.__config.getThreads();
    }

    public int getTimebaseDenominator() {
        return this.__config.getTimebaseDenominator();
    }

    public int getTimebaseNumerator() {
        return this.__config.getTimebaseNumerator();
    }

    public int getUndershootPercentage() {
        return this.__config.getUndershootPercentage();
    }

    public int getUsage() {
        return this.__config.getUsage();
    }

    public void setBitDepth(BitDepth bitDepth) {
        this.__bitDepth = bitDepth;
        this.__config.setBitDepth(bitDepth.getValue());
    }

    public void setBufferInitialSize(int i) {
        this.__config.setBufferInitialSize(i);
    }

    public void setBufferOptimalSize(int i) {
        this.__config.setBufferOptimalSize(i);
    }

    public void setBufferSize(int i) {
        this.__config.setBufferSize(i);
    }

    public void setCpu(int i) {
        this.__config.setCpu(i);
    }

    public void setDropFrameThreshold(int i) {
        this.__config.setDropFrameThreshold(i);
    }

    public void setEndUsageMode(EndUsageMode endUsageMode) {
        this.__endUsageMode = endUsageMode;
        this.__config.setEndUsage(endUsageMode.getValue());
    }

    public void setErrorResilient(int i) {
        this.__config.setErrorResilient(i);
    }

    public void setInputBitDepth(int i) {
        this.__config.setInputBitDepth(i);
    }

    public void setKeyframeMaxDistance(int i) {
        this.__config.setKeyframeMaxDistance(i);
    }

    public void setKeyframeMinDistance(int i) {
        this.__config.setKeyframeMinDistance(i);
    }

    public void setKeyframeMode(KeyframeMode keyframeMode) {
        this.__keyframeMode = keyframeMode;
        this.__config.setKeyframeMode(keyframeMode.getValue());
    }

    public void setLagInFrames(int i) {
        this.__config.setLagInFrames(i);
    }

    public void setLayerTargetBitrate(int[] iArr) {
        this.__config.setLayerTargetBitrate(iArr);
    }

    public void setMaxQuantizer(int i) {
        this.__config.setMaxQuantizer(i);
    }

    public void setMinQuantizer(int i) {
        this.__config.setMinQuantizer(i);
    }

    public void setOvershootPercentage(int i) {
        this.__config.setOvershootPercentage(i);
    }

    public void setProfile(int i) {
        this.__config.setProfile(i);
    }

    public void setResizeAllowed(boolean z) {
        this.__config.setResizeAllowed(z ? 1 : 0);
    }

    public void setResizeDownThreshold(int i) {
        this.__config.setResizeDownThreshold(i);
    }

    public void setResizeUpThreshold(int i) {
        this.__config.setResizeUpThreshold(i);
    }

    public void setScaledHeight(int i) {
        this.__config.setScaledHeight(i);
    }

    public void setScaledWidth(int i) {
        this.__config.setScaledWidth(i);
    }

    public void setSpatialEnableAutoAltRef(int[] iArr) {
        this.__config.setSpatialEnableAutoAltRef(iArr);
    }

    public void setSpatialLayers(int i) {
        this.__config.setSpatialLayers(i);
    }

    public void setSpatialTargetBitrate(int[] iArr) {
        this.__config.setSpatialTargetBitrate(iArr);
    }

    public void setTemporalLayerMode(TemporalLayerMode temporalLayerMode) {
        this.__temporalLayerMode = temporalLayerMode;
        this.__config.setTemporalLayerMode(temporalLayerMode.getValue());
    }

    public void setTemporalLayers(int i) {
        this.__config.setTemporalLayers(i);
    }

    public void setTemporalPattern(int[] iArr) {
        this.__config.setTemporalPattern(iArr);
    }

    public void setTemporalPeriodicity(int i) {
        this.__config.setTemporalPeriodicity(i);
    }

    public void setTemporalRateDecimator(int[] iArr) {
        this.__config.setTemporalRateDecimator(iArr);
    }

    public void setTemporalTargetBitrate(int[] iArr) {
        this.__config.setTemporalTargetBitrate(iArr);
    }

    public void setThreads(int i) {
        this.__config.setThreads(i);
    }

    public void setTimebaseDenominator(int i) {
        this.__config.setTimebaseDenominator(i);
    }

    public void setTimebaseNumerator(int i) {
        this.__config.setTimebaseNumerator(i);
    }

    public void setUndershootPercentage(int i) {
        this.__config.setUndershootPercentage(i);
    }

    public void setUsage(int i) {
        this.__config.setUsage(i);
    }
}
