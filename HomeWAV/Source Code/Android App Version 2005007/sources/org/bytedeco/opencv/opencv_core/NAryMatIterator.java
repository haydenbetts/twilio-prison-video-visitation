package org.bytedeco.opencv.opencv_core;

import java.nio.ByteBuffer;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.annotation.ByPtrPtr;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv")
@Properties(inherit = {opencv_core.class})
@NoOffset
public class NAryMatIterator extends Pointer {
    private native void allocate();

    private native void allocate(@Cast({"const cv::Mat**"}) PointerPointer pointerPointer, @Cast({"uchar**"}) PointerPointer pointerPointer2, int i);

    private native void allocate(@Cast({"const cv::Mat**"}) PointerPointer pointerPointer, Mat mat, int i);

    private native void allocate(@ByPtrPtr @Const Mat mat, @ByPtrPtr @Cast({"uchar**"}) ByteBuffer byteBuffer);

    private native void allocate(@ByPtrPtr @Const Mat mat, @ByPtrPtr @Cast({"uchar**"}) ByteBuffer byteBuffer, int i);

    private native void allocate(@ByPtrPtr @Const Mat mat, @ByPtrPtr @Cast({"uchar**"}) BytePointer bytePointer);

    private native void allocate(@ByPtrPtr @Const Mat mat, @ByPtrPtr @Cast({"uchar**"}) BytePointer bytePointer, int i);

    private native void allocate(@ByPtrPtr @Const Mat mat, Mat mat2);

    private native void allocate(@ByPtrPtr @Const Mat mat, Mat mat2, int i);

    private native void allocate(@ByPtrPtr @Const Mat mat, @ByPtrPtr @Cast({"uchar**"}) byte[] bArr);

    private native void allocate(@ByPtrPtr @Const Mat mat, @ByPtrPtr @Cast({"uchar**"}) byte[] bArr, int i);

    private native void allocateArray(long j);

    @MemberGetter
    @Cast({"const cv::Mat**"})
    public native PointerPointer arrays();

    @Const
    public native Mat arrays(int i);

    public native NAryMatIterator arrays(int i, Mat mat);

    @ByRef
    @Name({"operator ++"})
    public native NAryMatIterator increment();

    @ByVal
    @Name({"operator ++"})
    public native NAryMatIterator increment(int i);

    public native void init(@Cast({"const cv::Mat**"}) PointerPointer pointerPointer, Mat mat, @Cast({"uchar**"}) PointerPointer pointerPointer2, int i);

    public native void init(@ByPtrPtr @Const Mat mat, Mat mat2, @ByPtrPtr @Cast({"uchar**"}) ByteBuffer byteBuffer);

    public native void init(@ByPtrPtr @Const Mat mat, Mat mat2, @ByPtrPtr @Cast({"uchar**"}) ByteBuffer byteBuffer, int i);

    public native void init(@ByPtrPtr @Const Mat mat, Mat mat2, @ByPtrPtr @Cast({"uchar**"}) BytePointer bytePointer);

    public native void init(@ByPtrPtr @Const Mat mat, Mat mat2, @ByPtrPtr @Cast({"uchar**"}) BytePointer bytePointer, int i);

    public native void init(@ByPtrPtr @Const Mat mat, Mat mat2, @ByPtrPtr @Cast({"uchar**"}) byte[] bArr);

    public native void init(@ByPtrPtr @Const Mat mat, Mat mat2, @ByPtrPtr @Cast({"uchar**"}) byte[] bArr, int i);

    public native int narrays();

    public native NAryMatIterator narrays(int i);

    @Cast({"size_t"})
    public native long nplanes();

    public native NAryMatIterator nplanes(long j);

    public native Mat planes();

    public native NAryMatIterator planes(Mat mat);

    @Cast({"uchar*"})
    public native BytePointer ptrs(int i);

    @Cast({"uchar**"})
    public native PointerPointer ptrs();

    public native NAryMatIterator ptrs(int i, BytePointer bytePointer);

    public native NAryMatIterator ptrs(PointerPointer pointerPointer);

    @Cast({"size_t"})
    public native long size();

    public native NAryMatIterator size(long j);

    static {
        Loader.load();
    }

    public NAryMatIterator(Pointer pointer) {
        super(pointer);
    }

    public NAryMatIterator(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public NAryMatIterator position(long j) {
        return (NAryMatIterator) super.position(j);
    }

    public NAryMatIterator getPointer(long j) {
        return new NAryMatIterator((Pointer) this).position(this.position + j);
    }

    public NAryMatIterator() {
        super((Pointer) null);
        allocate();
    }

    public NAryMatIterator(@Cast({"const cv::Mat**"}) PointerPointer pointerPointer, @Cast({"uchar**"}) PointerPointer pointerPointer2, int i) {
        super((Pointer) null);
        allocate(pointerPointer, pointerPointer2, i);
    }

    public NAryMatIterator(@ByPtrPtr @Const Mat mat, @ByPtrPtr @Cast({"uchar**"}) BytePointer bytePointer) {
        super((Pointer) null);
        allocate(mat, bytePointer);
    }

    public NAryMatIterator(@ByPtrPtr @Const Mat mat, @ByPtrPtr @Cast({"uchar**"}) BytePointer bytePointer, int i) {
        super((Pointer) null);
        allocate(mat, bytePointer, i);
    }

    public NAryMatIterator(@ByPtrPtr @Const Mat mat, @ByPtrPtr @Cast({"uchar**"}) ByteBuffer byteBuffer, int i) {
        super((Pointer) null);
        allocate(mat, byteBuffer, i);
    }

    public NAryMatIterator(@ByPtrPtr @Const Mat mat, @ByPtrPtr @Cast({"uchar**"}) ByteBuffer byteBuffer) {
        super((Pointer) null);
        allocate(mat, byteBuffer);
    }

    public NAryMatIterator(@ByPtrPtr @Const Mat mat, @ByPtrPtr @Cast({"uchar**"}) byte[] bArr, int i) {
        super((Pointer) null);
        allocate(mat, bArr, i);
    }

    public NAryMatIterator(@ByPtrPtr @Const Mat mat, @ByPtrPtr @Cast({"uchar**"}) byte[] bArr) {
        super((Pointer) null);
        allocate(mat, bArr);
    }

    public NAryMatIterator(@Cast({"const cv::Mat**"}) PointerPointer pointerPointer, Mat mat, int i) {
        super((Pointer) null);
        allocate(pointerPointer, mat, i);
    }

    public NAryMatIterator(@ByPtrPtr @Const Mat mat, Mat mat2) {
        super((Pointer) null);
        allocate(mat, mat2);
    }

    public NAryMatIterator(@ByPtrPtr @Const Mat mat, Mat mat2, int i) {
        super((Pointer) null);
        allocate(mat, mat2, i);
    }
}
