package org.bytedeco.opencv.opencv_features2d;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_features2d;

@Namespace("cv")
@Properties(inherit = {opencv_features2d.class})
@NoOffset
public class BFMatcher extends DescriptorMatcher {
    private native void allocate();

    private native void allocate(int i, @Cast({"bool"}) boolean z);

    private native void allocateArray(long j);

    @opencv_core.Ptr
    public static native BFMatcher create();

    @opencv_core.Ptr
    public static native BFMatcher create(int i, @Cast({"bool"}) boolean z);

    @opencv_core.Ptr
    public native DescriptorMatcher clone();

    @opencv_core.Ptr
    public native DescriptorMatcher clone(@Cast({"bool"}) boolean z);

    @Cast({"bool"})
    public native boolean isMaskSupported();

    static {
        Loader.load();
    }

    public BFMatcher(Pointer pointer) {
        super(pointer);
    }

    public BFMatcher(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public BFMatcher position(long j) {
        return (BFMatcher) super.position(j);
    }

    public BFMatcher getPointer(long j) {
        return new BFMatcher((Pointer) this).position(this.position + j);
    }

    public BFMatcher(int i, @Cast({"bool"}) boolean z) {
        super((Pointer) null);
        allocate(i, z);
    }

    public BFMatcher() {
        super((Pointer) null);
        allocate();
    }
}
