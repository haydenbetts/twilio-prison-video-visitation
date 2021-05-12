package org.bytedeco.opencv.opencv_stitching;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.PointVector;
import org.bytedeco.opencv.opencv_core.UMatVector;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_stitching;

@Namespace("cv::detail")
@Properties(inherit = {opencv_stitching.class})
@NoOffset
public class GraphCutSeamFinder extends GraphCutSeamFinderBase {
    private native void allocate();

    private native void allocate(int i, float f, float f2);

    private native void allocate(@opencv_core.Str String str);

    private native void allocate(@opencv_core.Str String str, float f, float f2);

    private native void allocate(@opencv_core.Str BytePointer bytePointer);

    private native void allocate(@opencv_core.Str BytePointer bytePointer, float f, float f2);

    private native void allocateArray(long j);

    @Namespace
    @Name({"static_cast<cv::detail::SeamFinder*>"})
    public static native SeamFinder asSeamFinder(GraphCutSeamFinder graphCutSeamFinder);

    public native void find(@ByRef @Const UMatVector uMatVector, @ByRef @Const PointVector pointVector, @ByRef UMatVector uMatVector2);

    static {
        Loader.load();
    }

    public GraphCutSeamFinder(Pointer pointer) {
        super(pointer);
    }

    public GraphCutSeamFinder(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public GraphCutSeamFinder position(long j) {
        return (GraphCutSeamFinder) super.position(j);
    }

    public GraphCutSeamFinder getPointer(long j) {
        return new GraphCutSeamFinder((Pointer) this).position(this.position + j);
    }

    public SeamFinder asSeamFinder() {
        return asSeamFinder(this);
    }

    public GraphCutSeamFinder(int i, float f, float f2) {
        super((Pointer) null);
        allocate(i, f, f2);
    }

    public GraphCutSeamFinder() {
        super((Pointer) null);
        allocate();
    }

    public GraphCutSeamFinder(@opencv_core.Str BytePointer bytePointer, float f, float f2) {
        super((Pointer) null);
        allocate(bytePointer, f, f2);
    }

    public GraphCutSeamFinder(@opencv_core.Str BytePointer bytePointer) {
        super((Pointer) null);
        allocate(bytePointer);
    }

    public GraphCutSeamFinder(@opencv_core.Str String str, float f, float f2) {
        super((Pointer) null);
        allocate(str, f, f2);
    }

    public GraphCutSeamFinder(@opencv_core.Str String str) {
        super((Pointer) null);
        allocate(str);
    }
}
