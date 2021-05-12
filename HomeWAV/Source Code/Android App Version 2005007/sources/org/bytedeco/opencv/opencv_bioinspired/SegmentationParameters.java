package org.bytedeco.opencv.opencv_bioinspired;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_bioinspired;

@Namespace("cv::bioinspired")
@Properties(inherit = {opencv_bioinspired.class})
@NoOffset
public class SegmentationParameters extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native float contextEnergy_spatialConstant();

    public native SegmentationParameters contextEnergy_spatialConstant(float f);

    public native float contextEnergy_temporalConstant();

    public native SegmentationParameters contextEnergy_temporalConstant(float f);

    public native float localEnergy_spatialConstant();

    public native SegmentationParameters localEnergy_spatialConstant(float f);

    public native float localEnergy_temporalConstant();

    public native SegmentationParameters localEnergy_temporalConstant(float f);

    public native float neighborhoodEnergy_spatialConstant();

    public native SegmentationParameters neighborhoodEnergy_spatialConstant(float f);

    public native float neighborhoodEnergy_temporalConstant();

    public native SegmentationParameters neighborhoodEnergy_temporalConstant(float f);

    public native float thresholdOFF();

    public native SegmentationParameters thresholdOFF(float f);

    public native float thresholdON();

    public native SegmentationParameters thresholdON(float f);

    static {
        Loader.load();
    }

    public SegmentationParameters(Pointer pointer) {
        super(pointer);
    }

    public SegmentationParameters(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public SegmentationParameters position(long j) {
        return (SegmentationParameters) super.position(j);
    }

    public SegmentationParameters getPointer(long j) {
        return new SegmentationParameters((Pointer) this).position(this.position + j);
    }

    public SegmentationParameters() {
        super((Pointer) null);
        allocate();
    }
}
