package fm.liveswitch.openh264;

import fm.liveswitch.ArrayExtensions;

public class EncoderConfig {
    private ComplexityMode __complexityMode;
    private NativeEncoderConfig __config = new NativeEncoderConfig();
    private ParameterSetStrategy __parameterSetStrategy;
    private RateControlMode __rcMode;
    private UsageType __usageType;
    private EncoderSpatialLayerConfig[] _spatialLayers;

    public EncoderConfig deepCopy() {
        EncoderConfig encoderConfig = new EncoderConfig();
        encoderConfig.__usageType = this.__usageType;
        encoderConfig.__rcMode = this.__rcMode;
        encoderConfig.__complexityMode = this.__complexityMode;
        encoderConfig.__parameterSetStrategy = this.__parameterSetStrategy;
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) getSpatialLayers()); i++) {
            encoderConfig.getSpatialLayers()[i] = getSpatialLayers()[i].deepCopy();
        }
        encoderConfig.__config = this.__config.deepCopy();
        return encoderConfig;
    }

    public EncoderConfig() {
        setSpatialLayers(new EncoderSpatialLayerConfig[4]);
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) getSpatialLayers()); i++) {
            getSpatialLayers()[i] = new EncoderSpatialLayerConfig();
        }
        setMaxNalSize(1145);
        setUsageType(UsageType.getCameraVideoRealTime());
        setRcMode(RateControlMode.getBitrateMode());
        setComplexityMode(ComplexityMode.getMedium());
        setSpsPpsIdStrategy(ParameterSetStrategy.getConstantId());
    }

    public ComplexityMode getComplexityMode() {
        return this.__complexityMode;
    }

    public boolean getEnableAdaptiveQuant() {
        return this.__config.getEnableAdaptiveQuant() == 1;
    }

    public boolean getEnableBackgroundDetection() {
        return this.__config.getEnableBackgroundDetection() == 1;
    }

    public boolean getEnableDenoise() {
        return this.__config.getEnableDenoise() == 1;
    }

    public boolean getEnableFrameCroppingFlag() {
        return this.__config.getEnableFrameCroppingFlag() == 1;
    }

    public boolean getEnableFrameSkip() {
        return this.__config.getEnableFrameSkip() == 1;
    }

    public boolean getEnableLongTermReference() {
        return this.__config.getEnableLongTermReference() == 1;
    }

    public boolean getEnableSceneChangeDetect() {
        return this.__config.getEnableSceneChangeDetect() == 1;
    }

    public boolean getEnableSsei() {
        return this.__config.getEnableSsei() == 1;
    }

    public int getEntropyCodingModeFlag() {
        return this.__config.getEntropyCodingModeFlag();
    }

    public int getIntraPeriod() {
        return this.__config.getIntraPeriod();
    }

    public boolean getIsLosslessLink() {
        return this.__config.getIsLosslessLink() == 1;
    }

    public int getLoopFilterAlphaC0Offset() {
        return this.__config.getLoopFilterAlphaC0Offset();
    }

    public int getLoopFilterBetaOffset() {
        return this.__config.getLoopFilterBetaOffset();
    }

    public int getLoopFilterDisableIdc() {
        return this.__config.getLoopFilterDisableIdc();
    }

    public int getLtrMarkPeriod() {
        return this.__config.getLtrMarkPeriod();
    }

    public int getLtrRefNum() {
        return this.__config.getLtrNumber();
    }

    public float getMaxFrameRate() {
        return this.__config.getMaxFrameRate();
    }

    public int getMaxNalSize() {
        return this.__config.getMaxNalSize();
    }

    public int getMaxQP() {
        return this.__config.getMaxQP();
    }

    public int getMinQP() {
        return this.__config.getMinQP();
    }

    public int getMultipleThreadIdc() {
        return this.__config.getMultipleThreadIdc();
    }

    /* access modifiers changed from: package-private */
    public NativeEncoderConfig getNativeConfig() {
        return this.__config;
    }

    public int getNumRefFrame() {
        return this.__config.getNumRefFrame();
    }

    public boolean getPaddingFlag() {
        return this.__config.getPaddingFlag() == 1;
    }

    public boolean getPrefixNalAddingControl() {
        return this.__config.getPrefixNalAddingControl() == 1;
    }

    public RateControlMode getRcMode() {
        return this.__rcMode;
    }

    public boolean getSimulcastAvc() {
        return this.__config.getSimulcastAvc() == 1;
    }

    public int getSpatialLayerNum() {
        return this.__config.getSpatialLayers();
    }

    public EncoderSpatialLayerConfig[] getSpatialLayers() {
        return this._spatialLayers;
    }

    public ParameterSetStrategy getSpsPpsIdStrategy() {
        return this.__parameterSetStrategy;
    }

    public int getTemporalLayerNum() {
        return this.__config.getTemporalLayers();
    }

    public UsageType getUsageType() {
        return this.__usageType;
    }

    public boolean getUseLoadBalancing() {
        return this.__config.getUseLoadBalancing() == 1;
    }

    public void setComplexityMode(ComplexityMode complexityMode) {
        this.__complexityMode = complexityMode;
        this.__config.setComplexityMode(complexityMode.getValue());
    }

    public void setEnableAdaptiveQuant(boolean z) {
        this.__config.setEnableAdaptiveQuant(z ? 1 : 0);
    }

    public void setEnableBackgroundDetection(boolean z) {
        this.__config.setEnableBackgroundDetection(z ? 1 : 0);
    }

    public void setEnableDenoise(boolean z) {
        this.__config.setEnableDenoise(z ? 1 : 0);
    }

    public void setEnableFrameCroppingFlag(boolean z) {
        this.__config.setEnableFrameCroppingFlag(z ? 1 : 0);
    }

    public void setEnableFrameSkip(boolean z) {
        this.__config.setEnableFrameSkip(z ? 1 : 0);
    }

    public void setEnableLongTermReference(boolean z) {
        this.__config.setEnableLongTermReference(z ? 1 : 0);
    }

    public void setEnableSceneChangeDetect(boolean z) {
        this.__config.setEnableSceneChangeDetect(z ? 1 : 0);
    }

    public void setEnableSsei(boolean z) {
        this.__config.setEnableSsei(z ? 1 : 0);
    }

    public void setEntropyCodingModeFlag(int i) {
        this.__config.setEntropyCodingModeFlag(i);
    }

    public void setIntraPeriod(int i) {
        this.__config.setIntraPeriod(i);
    }

    public void setIsLosslessLink(boolean z) {
        this.__config.setIsLosslessLink(z ? 1 : 0);
    }

    public void setLoopFilterAlphaC0Offset(int i) {
        this.__config.setLoopFilterAlphaC0Offset(i);
    }

    public void setLoopFilterBetaOffset(int i) {
        this.__config.setLoopFilterBetaOffset(i);
    }

    public void setLoopFilterDisableIdc(int i) {
        this.__config.setLoopFilterDisableIdc(i);
    }

    public void setLtrMarkPeriod(int i) {
        this.__config.setLtrMarkPeriod(i);
    }

    public void setLtrRefNum(int i) {
        this.__config.setLtrNumber(i);
    }

    public void setMaxFrameRate(float f) {
        this.__config.setMaxFrameRate(f);
    }

    public void setMaxNalSize(int i) {
        this.__config.setMaxNalSize(i);
    }

    public void setMaxQP(int i) {
        this.__config.setMaxQP(i);
    }

    public void setMinQP(int i) {
        this.__config.setMinQP(i);
    }

    public void setMultipleThreadIdc(int i) {
        this.__config.setMultipleThreadIdc(i);
    }

    public void setNumRefFrame(int i) {
        this.__config.setNumRefFrame(i);
    }

    public void setPaddingFlag(boolean z) {
        this.__config.setPaddingFlag(z ? 1 : 0);
    }

    public void setPrefixNalAddingControl(boolean z) {
        this.__config.setPrefixNalAddingControl(z ? 1 : 0);
    }

    public void setRcMode(RateControlMode rateControlMode) {
        this.__rcMode = rateControlMode;
        this.__config.setRcMode(rateControlMode.getValue());
    }

    public void setSimulcastAvc(boolean z) {
        this.__config.setSimulcastAvc(z ? 1 : 0);
    }

    public void setSpatialLayerNum(int i) {
        this.__config.setSpatialLayers(i);
    }

    private void setSpatialLayers(EncoderSpatialLayerConfig[] encoderSpatialLayerConfigArr) {
        this._spatialLayers = encoderSpatialLayerConfigArr;
    }

    public void setSpsPpsIdStrategy(ParameterSetStrategy parameterSetStrategy) {
        this.__parameterSetStrategy = parameterSetStrategy;
        this.__config.setParameterStrategy(parameterSetStrategy.getValue());
    }

    public void setTemporalLayerNum(int i) {
        this.__config.setTemporalLayers(i);
    }

    public void setUsageType(UsageType usageType) {
        this.__usageType = usageType;
        this.__config.setUsageType(usageType.getValue());
    }

    public void setUseLoadBalancing(boolean z) {
        this.__config.setUseLoadBalancing(z ? 1 : 0);
    }
}
