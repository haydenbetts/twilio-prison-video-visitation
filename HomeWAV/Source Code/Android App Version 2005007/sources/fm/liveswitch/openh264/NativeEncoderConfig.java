package fm.liveswitch.openh264;

class NativeEncoderConfig {
    private int __complexityMode;
    private int __enableAdaptiveQuant = 1;
    private int __enableBackgroundDetection = 0;
    private int __enableDenoise = 0;
    private int __enableFrameCroppingFlag = 1;
    private int __enableFrameSkip = 0;
    private int __enableLongTermReference = 0;
    private int __enableSceneChangeDetect = 1;
    private int __enableSsei = 0;
    private int __entropyCodingModeFlag = 0;
    private int __intraPeriod = 0;
    private int __isLosslessLink = 0;
    private int __loopFilterAlphaC0Offset = 0;
    private int __loopFilterBetaOffset = 0;
    private int __loopFilterDisableIdc = 0;
    private int __ltrMarkPeriod = 30;
    private int __ltrNumber = 0;
    private float __maxFrameRate = 30.0f;
    private int __maxNalSize = 1100;
    private int __maxQP = 63;
    private int __minQP = 20;
    private int __multipleThreadIdc = 0;
    private int __numRefFrame = -1;
    private int __paddingFlag = 0;
    private int __parameterStrategy;
    private int __prefixNalAddingControl = 0;
    private int __rcMode;
    private int __simulcastAvc = 0;
    private int __spatialLayers = 1;
    private int __temporalLayers = 1;
    private int __usageType;
    private int __useLoadBalancing = 1;

    public NativeEncoderConfig deepCopy() {
        NativeEncoderConfig nativeEncoderConfig = new NativeEncoderConfig();
        nativeEncoderConfig.__usageType = this.__usageType;
        nativeEncoderConfig.__rcMode = this.__rcMode;
        nativeEncoderConfig.__maxFrameRate = this.__maxFrameRate;
        nativeEncoderConfig.__complexityMode = this.__complexityMode;
        nativeEncoderConfig.__intraPeriod = this.__intraPeriod;
        nativeEncoderConfig.__numRefFrame = this.__numRefFrame;
        nativeEncoderConfig.__parameterStrategy = this.__parameterStrategy;
        nativeEncoderConfig.__prefixNalAddingControl = this.__prefixNalAddingControl;
        nativeEncoderConfig.__enableSsei = this.__enableSsei;
        nativeEncoderConfig.__simulcastAvc = this.__simulcastAvc;
        nativeEncoderConfig.__paddingFlag = this.__paddingFlag;
        nativeEncoderConfig.__entropyCodingModeFlag = this.__entropyCodingModeFlag;
        nativeEncoderConfig.__enableFrameSkip = this.__enableFrameSkip;
        nativeEncoderConfig.__minQP = this.__minQP;
        nativeEncoderConfig.__maxQP = this.__maxQP;
        nativeEncoderConfig.__maxNalSize = this.__maxNalSize;
        nativeEncoderConfig.__enableLongTermReference = this.__enableLongTermReference;
        nativeEncoderConfig.__ltrNumber = this.__ltrNumber;
        nativeEncoderConfig.__ltrMarkPeriod = this.__ltrMarkPeriod;
        nativeEncoderConfig.__multipleThreadIdc = this.__multipleThreadIdc;
        nativeEncoderConfig.__useLoadBalancing = this.__useLoadBalancing;
        nativeEncoderConfig.__loopFilterDisableIdc = this.__loopFilterDisableIdc;
        nativeEncoderConfig.__loopFilterAlphaC0Offset = this.__loopFilterAlphaC0Offset;
        nativeEncoderConfig.__loopFilterBetaOffset = this.__loopFilterBetaOffset;
        nativeEncoderConfig.__enableDenoise = this.__enableDenoise;
        nativeEncoderConfig.__enableBackgroundDetection = this.__enableBackgroundDetection;
        nativeEncoderConfig.__enableAdaptiveQuant = this.__enableAdaptiveQuant;
        nativeEncoderConfig.__enableFrameCroppingFlag = this.__enableFrameCroppingFlag;
        nativeEncoderConfig.__enableSceneChangeDetect = this.__enableSceneChangeDetect;
        nativeEncoderConfig.__isLosslessLink = this.__isLosslessLink;
        nativeEncoderConfig.__temporalLayers = this.__temporalLayers;
        nativeEncoderConfig.__spatialLayers = this.__spatialLayers;
        return nativeEncoderConfig;
    }

    public int getComplexityMode() {
        return this.__complexityMode;
    }

    public int getEnableAdaptiveQuant() {
        return this.__enableAdaptiveQuant;
    }

    public int getEnableBackgroundDetection() {
        return this.__enableBackgroundDetection;
    }

    public int getEnableDenoise() {
        return this.__enableDenoise;
    }

    public int getEnableFrameCroppingFlag() {
        return this.__enableFrameCroppingFlag;
    }

    public int getEnableFrameSkip() {
        return this.__enableFrameSkip;
    }

    public int getEnableLongTermReference() {
        return this.__enableLongTermReference;
    }

    public int getEnableSceneChangeDetect() {
        return this.__enableSceneChangeDetect;
    }

    public int getEnableSsei() {
        return this.__enableSsei;
    }

    public int getEntropyCodingModeFlag() {
        return this.__entropyCodingModeFlag;
    }

    public int getIntraPeriod() {
        return this.__intraPeriod;
    }

    public int getIsLosslessLink() {
        return this.__isLosslessLink;
    }

    public int getLoopFilterAlphaC0Offset() {
        return this.__loopFilterAlphaC0Offset;
    }

    public int getLoopFilterBetaOffset() {
        return this.__loopFilterBetaOffset;
    }

    public int getLoopFilterDisableIdc() {
        return this.__loopFilterDisableIdc;
    }

    public int getLtrMarkPeriod() {
        return this.__ltrMarkPeriod;
    }

    public int getLtrNumber() {
        return this.__ltrNumber;
    }

    public float getMaxFrameRate() {
        return this.__maxFrameRate;
    }

    public int getMaxNalSize() {
        return this.__maxNalSize;
    }

    public int getMaxQP() {
        return this.__maxQP;
    }

    public int getMinQP() {
        return this.__minQP;
    }

    public int getMultipleThreadIdc() {
        return this.__multipleThreadIdc;
    }

    public int getNumRefFrame() {
        return this.__numRefFrame;
    }

    public int getPaddingFlag() {
        return this.__paddingFlag;
    }

    public int getParameterStrategy() {
        return this.__parameterStrategy;
    }

    public int getPrefixNalAddingControl() {
        return this.__prefixNalAddingControl;
    }

    public int getRcMode() {
        return this.__rcMode;
    }

    public int getSimulcastAvc() {
        return this.__simulcastAvc;
    }

    public int getSpatialLayers() {
        return this.__spatialLayers;
    }

    public int getTemporalLayers() {
        return this.__temporalLayers;
    }

    public int getUsageType() {
        return this.__usageType;
    }

    public int getUseLoadBalancing() {
        return this.__useLoadBalancing;
    }

    public void setComplexityMode(int i) {
        this.__complexityMode = i;
    }

    public void setEnableAdaptiveQuant(int i) {
        this.__enableAdaptiveQuant = i;
    }

    public void setEnableBackgroundDetection(int i) {
        this.__enableBackgroundDetection = i;
    }

    public void setEnableDenoise(int i) {
        this.__enableDenoise = i;
    }

    public void setEnableFrameCroppingFlag(int i) {
        this.__enableFrameCroppingFlag = i;
    }

    public void setEnableFrameSkip(int i) {
        this.__enableFrameSkip = i;
    }

    public void setEnableLongTermReference(int i) {
        this.__enableLongTermReference = i;
    }

    public void setEnableSceneChangeDetect(int i) {
        this.__enableSceneChangeDetect = i;
    }

    public void setEnableSsei(int i) {
        this.__enableSsei = i;
    }

    public void setEntropyCodingModeFlag(int i) {
        this.__entropyCodingModeFlag = i;
    }

    public void setIntraPeriod(int i) {
        this.__intraPeriod = i;
    }

    public void setIsLosslessLink(int i) {
        this.__isLosslessLink = i;
    }

    public void setLoopFilterAlphaC0Offset(int i) {
        this.__loopFilterAlphaC0Offset = i;
    }

    public void setLoopFilterBetaOffset(int i) {
        this.__loopFilterBetaOffset = i;
    }

    public void setLoopFilterDisableIdc(int i) {
        this.__loopFilterDisableIdc = i;
    }

    public void setLtrMarkPeriod(int i) {
        this.__ltrMarkPeriod = i;
    }

    public void setLtrNumber(int i) {
        this.__ltrNumber = i;
    }

    public void setMaxFrameRate(float f) {
        this.__maxFrameRate = f;
    }

    public void setMaxNalSize(int i) {
        this.__maxNalSize = i;
    }

    public void setMaxQP(int i) {
        this.__maxQP = i;
    }

    public void setMinQP(int i) {
        this.__minQP = i;
    }

    public void setMultipleThreadIdc(int i) {
        this.__multipleThreadIdc = i;
    }

    public void setNumRefFrame(int i) {
        this.__numRefFrame = i;
    }

    public void setPaddingFlag(int i) {
        this.__paddingFlag = i;
    }

    public void setParameterStrategy(int i) {
        this.__parameterStrategy = i;
    }

    public void setPrefixNalAddingControl(int i) {
        this.__prefixNalAddingControl = i;
    }

    public void setRcMode(int i) {
        this.__rcMode = i;
    }

    public void setSimulcastAvc(int i) {
        this.__simulcastAvc = i;
    }

    public void setSpatialLayers(int i) {
        this.__spatialLayers = i;
    }

    public void setTemporalLayers(int i) {
        this.__temporalLayers = i;
    }

    public void setUsageType(int i) {
        this.__usageType = i;
    }

    public void setUseLoadBalancing(int i) {
        this.__useLoadBalancing = i;
    }
}
