package fm.liveswitch.openh264;

import fm.liveswitch.ArrayExtensions;

class NativeEncoderSpatialLayerConfig {
    private int __aspectRatio = 0;
    private int __aspectRatioExtHeight = 0;
    private int __aspectRatioExtWidth = 0;
    private int __aspectRatioPresent = 0;
    private int __colorDescriptionPresent = 0;
    private char __colorMatrix;
    private char __colorPrimaries;
    private int __dLayerQp = 0;
    private float __frameRate = 30.0f;
    private int __fullRange = 0;
    private int __levelIdc = 0;
    private int __profileIdc = 66;
    private int[] __sliceMBNum = new int[35];
    private int __sliceMode = 0;
    private int __sliceNum = 0;
    private int __sliceSizeConstraint = 1100;
    private char __transferCharacteristics;
    private char __videoFormat;
    private int __videoSignalTypePresent = 0;

    public NativeEncoderSpatialLayerConfig deepCopy() {
        NativeEncoderSpatialLayerConfig nativeEncoderSpatialLayerConfig = new NativeEncoderSpatialLayerConfig();
        nativeEncoderSpatialLayerConfig.__frameRate = this.__frameRate;
        nativeEncoderSpatialLayerConfig.__profileIdc = this.__profileIdc;
        nativeEncoderSpatialLayerConfig.__levelIdc = this.__levelIdc;
        nativeEncoderSpatialLayerConfig.__dLayerQp = this.__dLayerQp;
        nativeEncoderSpatialLayerConfig.__sliceMode = this.__sliceMode;
        nativeEncoderSpatialLayerConfig.__sliceNum = this.__sliceNum;
        nativeEncoderSpatialLayerConfig.__sliceSizeConstraint = this.__sliceSizeConstraint;
        nativeEncoderSpatialLayerConfig.__videoSignalTypePresent = this.__videoSignalTypePresent;
        nativeEncoderSpatialLayerConfig.__videoFormat = this.__videoFormat;
        nativeEncoderSpatialLayerConfig.__fullRange = this.__fullRange;
        nativeEncoderSpatialLayerConfig.__colorDescriptionPresent = this.__colorDescriptionPresent;
        nativeEncoderSpatialLayerConfig.__colorPrimaries = this.__colorPrimaries;
        nativeEncoderSpatialLayerConfig.__transferCharacteristics = this.__transferCharacteristics;
        nativeEncoderSpatialLayerConfig.__colorMatrix = this.__colorMatrix;
        nativeEncoderSpatialLayerConfig.__aspectRatioPresent = this.__aspectRatioPresent;
        nativeEncoderSpatialLayerConfig.__aspectRatio = this.__aspectRatio;
        nativeEncoderSpatialLayerConfig.__aspectRatioExtWidth = this.__aspectRatioExtWidth;
        nativeEncoderSpatialLayerConfig.__aspectRatioExtHeight = this.__aspectRatioExtHeight;
        for (int i = 0; i < ArrayExtensions.getLength(this.__sliceMBNum); i++) {
            nativeEncoderSpatialLayerConfig.__sliceMBNum[i] = this.__sliceMBNum[i];
        }
        return nativeEncoderSpatialLayerConfig;
    }

    public int getAspectRatio() {
        return this.__aspectRatio;
    }

    public int getAspectRatioExtHeight() {
        return this.__aspectRatioExtHeight;
    }

    public int getAspectRatioExtWidth() {
        return this.__aspectRatioExtWidth;
    }

    public int getAspectRatioPresent() {
        return this.__aspectRatioPresent;
    }

    public int getColorDescriptionPresent() {
        return this.__colorDescriptionPresent;
    }

    public char getColorMatrix() {
        return this.__colorMatrix;
    }

    public char getColorPrimaries() {
        return this.__colorPrimaries;
    }

    public int getDLayerQp() {
        return this.__dLayerQp;
    }

    public float getFrameRate() {
        return this.__frameRate;
    }

    public int getFullRange() {
        return this.__fullRange;
    }

    public int getLevelIdc() {
        return this.__levelIdc;
    }

    public int getProfileIdc() {
        return this.__profileIdc;
    }

    public int[] getSliceMBNum() {
        return this.__sliceMBNum;
    }

    public int getSliceMode() {
        return this.__sliceMode;
    }

    public int getSliceNum() {
        return this.__sliceNum;
    }

    public int getSliceSizeConstraint() {
        return this.__sliceSizeConstraint;
    }

    public char getTransferCharacteristics() {
        return this.__transferCharacteristics;
    }

    public char getVideoFormat() {
        return this.__videoFormat;
    }

    public int getVideoSignalTypePresent() {
        return this.__videoSignalTypePresent;
    }

    public void setAspectRatio(int i) {
        this.__aspectRatio = i;
    }

    public void setAspectRatioExtHeight(int i) {
        this.__aspectRatioExtHeight = i;
    }

    public void setAspectRatioExtWidth(int i) {
        this.__aspectRatioExtWidth = i;
    }

    public void setAspectRatioPresent(int i) {
        this.__aspectRatioPresent = i;
    }

    public void setColorDescriptionPresent(int i) {
        this.__colorDescriptionPresent = i;
    }

    public void setColorMatrix(char c) {
        this.__colorMatrix = c;
    }

    public void setColorPrimaries(char c) {
        this.__colorPrimaries = c;
    }

    public void setDLayerQp(int i) {
        this.__dLayerQp = i;
    }

    public void setFrameRate(float f) {
        this.__frameRate = f;
    }

    public void setFullRange(int i) {
        this.__fullRange = i;
    }

    public void setLevelIdc(int i) {
        this.__levelIdc = i;
    }

    public void setProfileIdc(int i) {
        this.__profileIdc = i;
    }

    public void setSliceMBNum(int[] iArr) {
        this.__sliceMBNum = iArr;
    }

    public void setSliceMode(int i) {
        this.__sliceMode = i;
    }

    public void setSliceNum(int i) {
        this.__sliceNum = i;
    }

    public void setSliceSizeConstraint(int i) {
        this.__sliceSizeConstraint = i;
    }

    public void setTransferCharacteristics(char c) {
        this.__transferCharacteristics = c;
    }

    public void setVideoFormat(char c) {
        this.__videoFormat = c;
    }

    public void setVideoSignalTypePresent(int i) {
        this.__videoSignalTypePresent = i;
    }
}
