package fm.liveswitch.openh264;

public class EncoderSpatialLayerConfig {
    private SampleAspectRatio __aspectRatio;
    private ColorMatrix __colorMatrix;
    private ColorPrimaries __colorPrimaries;
    private NativeEncoderSpatialLayerConfig __config = new NativeEncoderSpatialLayerConfig();
    private LevelIdc __levelIdc;
    private ProfileIdc __profileIdc;
    private SliceMode __sliceMode;
    private TransferCharacteristics __transferCharacteristics;
    private VideoFormatSPS __videoFormat;

    public EncoderSpatialLayerConfig deepCopy() {
        EncoderSpatialLayerConfig encoderSpatialLayerConfig = new EncoderSpatialLayerConfig();
        encoderSpatialLayerConfig.__profileIdc = this.__profileIdc;
        encoderSpatialLayerConfig.__levelIdc = this.__levelIdc;
        encoderSpatialLayerConfig.__sliceMode = this.__sliceMode;
        encoderSpatialLayerConfig.__aspectRatio = this.__aspectRatio;
        encoderSpatialLayerConfig.__videoFormat = this.__videoFormat;
        encoderSpatialLayerConfig.__colorPrimaries = this.__colorPrimaries;
        encoderSpatialLayerConfig.__transferCharacteristics = this.__transferCharacteristics;
        encoderSpatialLayerConfig.__colorMatrix = this.__colorMatrix;
        encoderSpatialLayerConfig.__config = this.__config.deepCopy();
        return encoderSpatialLayerConfig;
    }

    public EncoderSpatialLayerConfig() {
        setProfileIdc(ProfileIdc.getBaseline());
        setLevelIdc(LevelIdc.getLevel31());
        setSliceMode(SliceMode.getSizeLimited());
        setAspectRatio(SampleAspectRatio.getUnspecified());
        setVideoFormat(VideoFormatSPS.getUndefined());
        setColorPrimaries(ColorPrimaries.getUndefined());
        setTransferCharacteristics(TransferCharacteristics.getUndefined());
        setColorMatrix(ColorMatrix.getUndefined());
    }

    public SampleAspectRatio getAspectRatio() {
        return this.__aspectRatio;
    }

    public int getAspectRatioExtHeight() {
        return this.__config.getAspectRatioExtHeight();
    }

    public int getAspectRatioExtWidth() {
        return this.__config.getAspectRatioExtWidth();
    }

    public boolean getAspectRatioPresent() {
        return this.__config.getAspectRatioPresent() == 1;
    }

    public boolean getColorDescriptionPresent() {
        return this.__config.getColorDescriptionPresent() == 1;
    }

    public ColorMatrix getColorMatrix() {
        return this.__colorMatrix;
    }

    public ColorPrimaries getColorPrimaries() {
        return this.__colorPrimaries;
    }

    public int getDLayerQp() {
        return this.__config.getDLayerQp();
    }

    public float getFrameRate() {
        return this.__config.getFrameRate();
    }

    public boolean getFullRange() {
        return this.__config.getFullRange() == 1;
    }

    public LevelIdc getLevelIdc() {
        return this.__levelIdc;
    }

    /* access modifiers changed from: package-private */
    public NativeEncoderSpatialLayerConfig getNativeConfig() {
        return this.__config;
    }

    public ProfileIdc getProfileIdc() {
        return this.__profileIdc;
    }

    public int[] getSliceMBNum() {
        return this.__config.getSliceMBNum();
    }

    public SliceMode getSliceMode() {
        return this.__sliceMode;
    }

    public int getSliceNum() {
        return this.__config.getSliceNum();
    }

    public int getSliceSizeConstraint() {
        return this.__config.getSliceSizeConstraint();
    }

    public TransferCharacteristics getTransferCharacteristics() {
        return this.__transferCharacteristics;
    }

    public VideoFormatSPS getVideoFormat() {
        return this.__videoFormat;
    }

    public boolean getVideoSignalTypePresent() {
        return this.__config.getVideoSignalTypePresent() == 1;
    }

    public void setAspectRatio(SampleAspectRatio sampleAspectRatio) {
        this.__aspectRatio = sampleAspectRatio;
        this.__config.setAspectRatio(sampleAspectRatio.getValue());
    }

    public void setAspectRatioExtHeight(int i) {
        this.__config.setAspectRatioExtHeight(i);
    }

    public void setAspectRatioExtWidth(int i) {
        this.__config.setAspectRatioExtWidth(i);
    }

    public void setAspectRatioPresent(boolean z) {
        this.__config.setAspectRatioPresent(z ? 1 : 0);
    }

    public void setColorDescriptionPresent(boolean z) {
        this.__config.setColorDescriptionPresent(z ? 1 : 0);
    }

    public void setColorMatrix(ColorMatrix colorMatrix) {
        this.__colorMatrix = colorMatrix;
        this.__config.setColorMatrix(colorMatrix.getValue());
    }

    public void setColorPrimaries(ColorPrimaries colorPrimaries) {
        this.__colorPrimaries = colorPrimaries;
        this.__config.setColorPrimaries(colorPrimaries.getValue());
    }

    public void setDLayerQp(int i) {
        this.__config.setDLayerQp(i);
    }

    public void setFrameRate(float f) {
        this.__config.setFrameRate(f);
    }

    public void setFullRange(boolean z) {
        this.__config.setFullRange(z ? 1 : 0);
    }

    public void setLevelIdc(LevelIdc levelIdc) {
        this.__levelIdc = levelIdc;
        this.__config.setLevelIdc(levelIdc.getValue());
    }

    public void setProfileIdc(ProfileIdc profileIdc) {
        this.__profileIdc = profileIdc;
        this.__config.setProfileIdc(profileIdc.getValue());
    }

    public void setSliceMBNum(int[] iArr) {
        this.__config.setSliceMBNum(iArr);
    }

    public void setSliceMode(SliceMode sliceMode) {
        this.__sliceMode = sliceMode;
        this.__config.setSliceMode(sliceMode.getValue());
    }

    public void setSliceNum(int i) {
        this.__config.setSliceNum(i);
    }

    public void setSliceSizeConstraint(int i) {
        this.__config.setSliceSizeConstraint(i);
    }

    public void setTransferCharacteristics(TransferCharacteristics transferCharacteristics) {
        this.__transferCharacteristics = transferCharacteristics;
        this.__config.setTransferCharacteristics(transferCharacteristics.getValue());
    }

    public void setVideoFormat(VideoFormatSPS videoFormatSPS) {
        this.__videoFormat = videoFormatSPS;
        this.__config.setVideoFormat(videoFormatSPS.getValue());
    }

    public void setVideoSignalTypePresent(boolean z) {
        this.__config.setVideoSignalTypePresent(z ? 1 : 0);
    }
}
