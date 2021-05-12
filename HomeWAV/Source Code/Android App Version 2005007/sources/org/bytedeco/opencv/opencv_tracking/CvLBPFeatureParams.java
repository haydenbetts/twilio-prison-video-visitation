package org.bytedeco.opencv.opencv_tracking;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_tracking;

@Namespace("cv")
@Properties(inherit = {opencv_tracking.class})
public class CvLBPFeatureParams extends CvFeatureParams {
    private native void allocate();

    private native void allocateArray(long j);

    static {
        Loader.load();
    }

    public CvLBPFeatureParams(Pointer pointer) {
        super(pointer);
    }

    public CvLBPFeatureParams(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CvLBPFeatureParams position(long j) {
        return (CvLBPFeatureParams) super.position(j);
    }

    public CvLBPFeatureParams getPointer(long j) {
        return new CvLBPFeatureParams((Pointer) this).position(this.position + j);
    }

    public CvLBPFeatureParams() {
        super((Pointer) null);
        allocate();
    }
}
