package org.bytedeco.opencv.opencv_tracking;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.FileNode;
import org.bytedeco.opencv.opencv_core.FileStorage;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_tracking;

@Namespace("cv")
@Properties(inherit = {opencv_tracking.class})
@NoOffset
public class CvFeatureParams extends CvParams {
    public static final int HAAR = 0;
    public static final int HOG = 2;
    public static final int LBP = 1;

    private native void allocate();

    private native void allocateArray(long j);

    @opencv_core.Ptr
    public static native CvFeatureParams create(@Cast({"cv::CvFeatureParams::FeatureType"}) int i);

    public native int featSize();

    public native CvFeatureParams featSize(int i);

    public native void init(@ByRef @Const CvFeatureParams cvFeatureParams);

    public native int maxCatCount();

    public native CvFeatureParams maxCatCount(int i);

    public native int numFeatures();

    public native CvFeatureParams numFeatures(int i);

    @Cast({"bool"})
    public native boolean read(@ByRef @Const FileNode fileNode);

    public native void write(@ByRef FileStorage fileStorage);

    static {
        Loader.load();
    }

    public CvFeatureParams(Pointer pointer) {
        super(pointer);
    }

    public CvFeatureParams(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CvFeatureParams position(long j) {
        return (CvFeatureParams) super.position(j);
    }

    public CvFeatureParams getPointer(long j) {
        return new CvFeatureParams((Pointer) this).position(this.position + j);
    }

    public CvFeatureParams() {
        super((Pointer) null);
        allocate();
    }
}
