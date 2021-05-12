package fm.liveswitch.vpx;

import fm.liveswitch.ArrayExtensions;

class NativeEncoderConfig {
    private int __bitDepth;
    private int __bufferInitialSize = 4000;
    private int __bufferOptimalSize = 5000;
    private int __bufferSize = 6000;
    private int __cpu = -99;
    private int __dropFrameThreshold = 0;
    private int __endUsage;
    private int __errorResilient = ErrorResilientType.getDefault();
    private int __inputBitDepth = 8;
    private int __keyframeMaxDistance = 1800;
    private int __keyframeMinDistance = 1800;
    private int __keyframeMode;
    private int __lagInFrames = 0;
    private int[] __layerTargetBitrate;
    private int __maxQuantizer = 47;
    private int __minQuantizer = 0;
    private int __overshootPercentage = 100;
    private int __profile = 0;
    private int __resizeAllowed = 0;
    private int __resizeDownThreshold = 30;
    private int __resizeUpThreshold = 60;
    private int __scaledHeight = 1;
    private int __scaledWidth = 1;
    private int[] __spatialEnableAutoAltRef;
    private int __spatialLayers = 1;
    private int[] __spatialTargetBitrate;
    private int __temporalLayerMode;
    private int __temporalLayers;
    private int[] __temporalPattern;
    private int __temporalPeriodicity;
    private int[] __temporalRateDecimator;
    private int[] __temporalTargetBitrate;
    private int __threads = 0;
    private int __timebaseDenominator = 30;
    private int __timebaseNumerator = 1;
    private int __undershootPercentage = 100;
    private int __usage = 0;

    public NativeEncoderConfig deepCopy() {
        NativeEncoderConfig nativeEncoderConfig = new NativeEncoderConfig();
        nativeEncoderConfig.__bitDepth = this.__bitDepth;
        nativeEncoderConfig.__inputBitDepth = this.__inputBitDepth;
        nativeEncoderConfig.__lagInFrames = this.__lagInFrames;
        nativeEncoderConfig.__threads = this.__threads;
        nativeEncoderConfig.__dropFrameThreshold = this.__dropFrameThreshold;
        nativeEncoderConfig.__resizeAllowed = this.__resizeAllowed;
        nativeEncoderConfig.__scaledWidth = this.__scaledWidth;
        nativeEncoderConfig.__scaledHeight = this.__scaledHeight;
        nativeEncoderConfig.__resizeUpThreshold = this.__resizeUpThreshold;
        nativeEncoderConfig.__resizeDownThreshold = this.__resizeDownThreshold;
        nativeEncoderConfig.__minQuantizer = this.__minQuantizer;
        nativeEncoderConfig.__maxQuantizer = this.__maxQuantizer;
        nativeEncoderConfig.__undershootPercentage = this.__undershootPercentage;
        nativeEncoderConfig.__overshootPercentage = this.__overshootPercentage;
        nativeEncoderConfig.__profile = this.__profile;
        nativeEncoderConfig.__errorResilient = this.__errorResilient;
        nativeEncoderConfig.__timebaseNumerator = this.__timebaseNumerator;
        nativeEncoderConfig.__timebaseDenominator = this.__timebaseDenominator;
        nativeEncoderConfig.__usage = this.__usage;
        nativeEncoderConfig.__endUsage = this.__endUsage;
        nativeEncoderConfig.__bufferSize = this.__bufferSize;
        nativeEncoderConfig.__bufferInitialSize = this.__bufferInitialSize;
        nativeEncoderConfig.__bufferOptimalSize = this.__bufferOptimalSize;
        nativeEncoderConfig.__cpu = this.__cpu;
        nativeEncoderConfig.__keyframeMode = this.__keyframeMode;
        nativeEncoderConfig.__keyframeMinDistance = this.__keyframeMinDistance;
        nativeEncoderConfig.__keyframeMaxDistance = this.__keyframeMaxDistance;
        nativeEncoderConfig.__spatialLayers = this.__spatialLayers;
        nativeEncoderConfig.__temporalLayers = this.__temporalLayers;
        nativeEncoderConfig.__temporalLayerMode = this.__temporalLayerMode;
        nativeEncoderConfig.__temporalPeriodicity = this.__temporalPeriodicity;
        for (int i = 0; i < ArrayExtensions.getLength(this.__spatialEnableAutoAltRef); i++) {
            nativeEncoderConfig.__spatialEnableAutoAltRef[i] = this.__spatialEnableAutoAltRef[i];
        }
        for (int i2 = 0; i2 < ArrayExtensions.getLength(this.__spatialTargetBitrate); i2++) {
            nativeEncoderConfig.__spatialTargetBitrate[i2] = this.__spatialTargetBitrate[i2];
        }
        for (int i3 = 0; i3 < ArrayExtensions.getLength(this.__temporalTargetBitrate); i3++) {
            nativeEncoderConfig.__temporalTargetBitrate[i3] = this.__temporalTargetBitrate[i3];
        }
        for (int i4 = 0; i4 < ArrayExtensions.getLength(this.__temporalRateDecimator); i4++) {
            nativeEncoderConfig.__temporalRateDecimator[i4] = this.__temporalRateDecimator[i4];
        }
        for (int i5 = 0; i5 < ArrayExtensions.getLength(this.__temporalPattern); i5++) {
            nativeEncoderConfig.__temporalPattern[i5] = this.__temporalPattern[i5];
        }
        for (int i6 = 0; i6 < ArrayExtensions.getLength(this.__layerTargetBitrate); i6++) {
            nativeEncoderConfig.__layerTargetBitrate[i6] = this.__layerTargetBitrate[i6];
        }
        return nativeEncoderConfig;
    }

    public int getBitDepth() {
        return this.__bitDepth;
    }

    public int getBufferInitialSize() {
        return this.__bufferInitialSize;
    }

    public int getBufferOptimalSize() {
        return this.__bufferOptimalSize;
    }

    public int getBufferSize() {
        return this.__bufferSize;
    }

    public int getCpu() {
        return this.__cpu;
    }

    public int getDropFrameThreshold() {
        return this.__dropFrameThreshold;
    }

    public int getEndUsage() {
        return this.__endUsage;
    }

    public int getErrorResilient() {
        return this.__errorResilient;
    }

    public int getInputBitDepth() {
        return this.__inputBitDepth;
    }

    public int getKeyframeMaxDistance() {
        return this.__keyframeMaxDistance;
    }

    public int getKeyframeMinDistance() {
        return this.__keyframeMinDistance;
    }

    public int getKeyframeMode() {
        return this.__keyframeMode;
    }

    public int getLagInFrames() {
        return this.__lagInFrames;
    }

    public int[] getLayerTargetBitrate() {
        return this.__layerTargetBitrate;
    }

    public int getMaxQuantizer() {
        return this.__maxQuantizer;
    }

    public int getMinQuantizer() {
        return this.__minQuantizer;
    }

    public int getOvershootPercentage() {
        return this.__overshootPercentage;
    }

    public int getProfile() {
        return this.__profile;
    }

    public int getResizeAllowed() {
        return this.__resizeAllowed;
    }

    public int getResizeDownThreshold() {
        return this.__resizeDownThreshold;
    }

    public int getResizeUpThreshold() {
        return this.__resizeUpThreshold;
    }

    public int getScaledHeight() {
        return this.__scaledHeight;
    }

    public int getScaledWidth() {
        return this.__scaledWidth;
    }

    public int[] getSpatialEnableAutoAltRef() {
        return this.__spatialEnableAutoAltRef;
    }

    public int getSpatialLayers() {
        return this.__spatialLayers;
    }

    public int[] getSpatialTargetBitrate() {
        return this.__spatialTargetBitrate;
    }

    public int getTemporalLayerMode() {
        return this.__temporalLayerMode;
    }

    public int getTemporalLayers() {
        return this.__temporalLayers;
    }

    public int[] getTemporalPattern() {
        return this.__temporalPattern;
    }

    public int getTemporalPeriodicity() {
        return this.__temporalPeriodicity;
    }

    public int[] getTemporalRateDecimator() {
        return this.__temporalRateDecimator;
    }

    public int[] getTemporalTargetBitrate() {
        return this.__temporalTargetBitrate;
    }

    public int getThreads() {
        return this.__threads;
    }

    public int getTimebaseDenominator() {
        return this.__timebaseDenominator;
    }

    public int getTimebaseNumerator() {
        return this.__timebaseNumerator;
    }

    public int getUndershootPercentage() {
        return this.__undershootPercentage;
    }

    public int getUsage() {
        return this.__usage;
    }

    public NativeEncoderConfig() {
        int[] iArr = new int[5];
        this.__spatialEnableAutoAltRef = iArr;
        this.__spatialTargetBitrate = new int[5];
        this.__temporalLayers = 1;
        this.__temporalLayerMode = 0;
        this.__temporalPeriodicity = 0;
        this.__temporalTargetBitrate = new int[5];
        this.__temporalRateDecimator = new int[5];
        this.__temporalPattern = new int[16];
        this.__layerTargetBitrate = new int[12];
        zeroArray(iArr);
        zeroArray(this.__spatialTargetBitrate);
        zeroArray(this.__temporalTargetBitrate);
        zeroArray(this.__temporalRateDecimator);
        zeroArray(this.__temporalPattern);
        zeroArray(this.__layerTargetBitrate);
    }

    public void setBitDepth(int i) {
        this.__bitDepth = i;
    }

    public void setBufferInitialSize(int i) {
        this.__bufferInitialSize = i;
    }

    public void setBufferOptimalSize(int i) {
        this.__bufferOptimalSize = i;
    }

    public void setBufferSize(int i) {
        this.__bufferSize = i;
    }

    public void setCpu(int i) {
        this.__cpu = i;
    }

    public void setDropFrameThreshold(int i) {
        this.__dropFrameThreshold = i;
    }

    public void setEndUsage(int i) {
        this.__endUsage = i;
    }

    public void setErrorResilient(int i) {
        this.__errorResilient = i;
    }

    public void setInputBitDepth(int i) {
        this.__inputBitDepth = i;
    }

    public void setKeyframeMaxDistance(int i) {
        this.__keyframeMaxDistance = i;
    }

    public void setKeyframeMinDistance(int i) {
        this.__keyframeMinDistance = i;
    }

    public void setKeyframeMode(int i) {
        this.__keyframeMode = i;
    }

    public void setLagInFrames(int i) {
        this.__lagInFrames = i;
    }

    public void setLayerTargetBitrate(int[] iArr) {
        this.__layerTargetBitrate = iArr;
    }

    public void setMaxQuantizer(int i) {
        this.__maxQuantizer = i;
    }

    public void setMinQuantizer(int i) {
        this.__minQuantizer = i;
    }

    public void setOvershootPercentage(int i) {
        this.__overshootPercentage = i;
    }

    public void setProfile(int i) {
        this.__profile = i;
    }

    public void setResizeAllowed(int i) {
        this.__resizeAllowed = i;
    }

    public void setResizeDownThreshold(int i) {
        this.__resizeDownThreshold = i;
    }

    public void setResizeUpThreshold(int i) {
        this.__resizeUpThreshold = i;
    }

    public void setScaledHeight(int i) {
        this.__scaledHeight = i;
    }

    public void setScaledWidth(int i) {
        this.__scaledWidth = i;
    }

    public void setSpatialEnableAutoAltRef(int[] iArr) {
        this.__spatialEnableAutoAltRef = iArr;
    }

    public void setSpatialLayers(int i) {
        this.__spatialLayers = i;
    }

    public void setSpatialTargetBitrate(int[] iArr) {
        this.__spatialTargetBitrate = iArr;
    }

    public void setTemporalLayerMode(int i) {
        this.__temporalLayerMode = i;
    }

    public void setTemporalLayers(int i) {
        this.__temporalLayers = i;
    }

    public void setTemporalPattern(int[] iArr) {
        this.__temporalPattern = iArr;
    }

    public void setTemporalPeriodicity(int i) {
        this.__temporalPeriodicity = i;
    }

    public void setTemporalRateDecimator(int[] iArr) {
        this.__temporalRateDecimator = iArr;
    }

    public void setTemporalTargetBitrate(int[] iArr) {
        this.__temporalTargetBitrate = iArr;
    }

    public void setThreads(int i) {
        this.__threads = i;
    }

    public void setTimebaseDenominator(int i) {
        this.__timebaseDenominator = i;
    }

    public void setTimebaseNumerator(int i) {
        this.__timebaseNumerator = i;
    }

    public void setUndershootPercentage(int i) {
        this.__undershootPercentage = i;
    }

    public void setUsage(int i) {
        this.__usage = i;
    }

    private void zeroArray(int[] iArr) {
        for (int i = 0; i < ArrayExtensions.getLength(iArr); i++) {
            iArr[i] = 0;
        }
    }
}
