package org.bytedeco.opencv.opencv_xfeatures2d;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_features2d.Feature2D;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_xfeatures2d;

@Namespace("cv::xfeatures2d")
@Properties(inherit = {opencv_xfeatures2d.class})
public class BriefDescriptorExtractor extends Feature2D {
    private native void allocate();

    private native void allocateArray(long j);

    @opencv_core.Ptr
    public static native BriefDescriptorExtractor create();

    @opencv_core.Ptr
    public static native BriefDescriptorExtractor create(int i, @Cast({"bool"}) boolean z);

    static {
        Loader.load();
    }

    public BriefDescriptorExtractor() {
        super((Pointer) null);
        allocate();
    }

    public BriefDescriptorExtractor(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public BriefDescriptorExtractor(Pointer pointer) {
        super(pointer);
    }

    public BriefDescriptorExtractor position(long j) {
        return (BriefDescriptorExtractor) super.position(j);
    }

    public BriefDescriptorExtractor getPointer(long j) {
        return new BriefDescriptorExtractor((Pointer) this).position(this.position + j);
    }
}
