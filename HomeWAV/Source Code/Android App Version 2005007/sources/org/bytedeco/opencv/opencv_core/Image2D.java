package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv::ocl")
@Properties(inherit = {opencv_core.class})
@NoOffset
public class Image2D extends Pointer {
    private native void allocate();

    private native void allocate(@ByRef @Const Image2D image2D);

    private native void allocate(@ByRef @Const UMat uMat);

    private native void allocate(@ByRef @Const UMat uMat, @Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2);

    private native void allocateArray(long j);

    @Cast({"bool"})
    public static native boolean canCreateAlias(@ByRef @Const UMat uMat);

    @Cast({"bool"})
    public static native boolean isFormatSupported(int i, int i2, @Cast({"bool"}) boolean z);

    public native Pointer ptr();

    @ByRef
    @Name({"operator ="})
    public native Image2D put(@ByRef @Const Image2D image2D);

    static {
        Loader.load();
    }

    public Image2D(Pointer pointer) {
        super(pointer);
    }

    public Image2D(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public Image2D position(long j) {
        return (Image2D) super.position(j);
    }

    public Image2D getPointer(long j) {
        return new Image2D(this).position(this.position + j);
    }

    public Image2D() {
        super((Pointer) null);
        allocate();
    }

    public Image2D(@ByRef @Const UMat uMat, @Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2) {
        super((Pointer) null);
        allocate(uMat, z, z2);
    }

    public Image2D(@ByRef @Const UMat uMat) {
        super((Pointer) null);
        allocate(uMat);
    }

    public Image2D(@ByRef @Const Image2D image2D) {
        super((Pointer) null);
        allocate(image2D);
    }
}
