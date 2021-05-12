package org.bytedeco.opencv.opencv_tracking;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_tracking;

@Namespace("cv")
@Properties(inherit = {opencv_tracking.class})
public class CvHOGFeatureParams extends CvFeatureParams {
    private native void allocate();

    private native void allocateArray(long j);

    static {
        Loader.load();
    }

    public CvHOGFeatureParams(Pointer pointer) {
        super(pointer);
    }

    public CvHOGFeatureParams(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CvHOGFeatureParams position(long j) {
        return (CvHOGFeatureParams) super.position(j);
    }

    public CvHOGFeatureParams getPointer(long j) {
        return new CvHOGFeatureParams((Pointer) this).position(this.position + j);
    }

    public CvHOGFeatureParams() {
        super((Pointer) null);
        allocate();
    }
}
