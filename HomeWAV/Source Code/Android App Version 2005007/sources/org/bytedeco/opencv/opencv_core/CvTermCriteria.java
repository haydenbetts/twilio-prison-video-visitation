package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
public class CvTermCriteria extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    @ByVal
    @Name({"operator cv::TermCriteria"})
    public native TermCriteria asTermCriteria();

    public native double epsilon();

    public native CvTermCriteria epsilon(double d);

    public native int max_iter();

    public native CvTermCriteria max_iter(int i);

    public native int type();

    public native CvTermCriteria type(int i);

    static {
        Loader.load();
    }

    public CvTermCriteria() {
        super((Pointer) null);
        allocate();
    }

    public CvTermCriteria(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CvTermCriteria(Pointer pointer) {
        super(pointer);
    }

    public CvTermCriteria position(long j) {
        return (CvTermCriteria) super.position(j);
    }

    public CvTermCriteria getPointer(long j) {
        return new CvTermCriteria((Pointer) this).position(this.position + j);
    }
}
