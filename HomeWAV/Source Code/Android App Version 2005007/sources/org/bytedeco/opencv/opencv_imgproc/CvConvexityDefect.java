package org.bytedeco.opencv.opencv_imgproc;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.CvPoint;
import org.bytedeco.opencv.presets.opencv_imgproc;

@Properties(inherit = {opencv_imgproc.class})
public class CvConvexityDefect extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native float depth();

    public native CvConvexityDefect depth(float f);

    public native CvPoint depth_point();

    public native CvConvexityDefect depth_point(CvPoint cvPoint);

    public native CvPoint end();

    public native CvConvexityDefect end(CvPoint cvPoint);

    public native CvPoint start();

    public native CvConvexityDefect start(CvPoint cvPoint);

    static {
        Loader.load();
    }

    public CvConvexityDefect() {
        super((Pointer) null);
        allocate();
    }

    public CvConvexityDefect(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CvConvexityDefect(Pointer pointer) {
        super(pointer);
    }

    public CvConvexityDefect position(long j) {
        return (CvConvexityDefect) super.position(j);
    }

    public CvConvexityDefect getPointer(long j) {
        return new CvConvexityDefect((Pointer) this).position(this.position + j);
    }
}
