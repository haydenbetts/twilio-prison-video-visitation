package org.bytedeco.opencv.opencv_core;

import java.nio.IntBuffer;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv")
@Properties(inherit = {opencv_core.class})
@NoOffset
public class MatConstIterator extends Pointer {
    private native void allocate();

    private native void allocate(@Const Mat mat);

    private native void allocate(@Const Mat mat, int i);

    private native void allocate(@Const Mat mat, int i, int i2);

    private native void allocate(@Const Mat mat, @ByVal Point point);

    private native void allocate(@ByRef @Const MatConstIterator matConstIterator);

    private native void allocateArray(long j);

    @ByRef
    @Name({"operator +="})
    public native MatConstIterator addPut(@Cast({"ptrdiff_t"}) long j);

    @ByRef
    @Name({"operator --"})
    public native MatConstIterator decrement();

    @ByVal
    @Name({"operator --"})
    public native MatConstIterator decrement(int i);

    @Cast({"size_t"})
    public native long elemSize();

    public native MatConstIterator elemSize(long j);

    @Cast({"const uchar*"})
    @Name({"operator []"})
    public native BytePointer get(@Cast({"ptrdiff_t"}) long j);

    @ByRef
    @Name({"operator ++"})
    public native MatConstIterator increment();

    @ByVal
    @Name({"operator ++"})
    public native MatConstIterator increment(int i);

    @Cast({"ptrdiff_t"})
    public native long lpos();

    @Const
    public native Mat m();

    public native MatConstIterator m(Mat mat);

    @Cast({"const uchar*"})
    @Name({"operator *"})
    public native BytePointer multiply();

    @ByVal
    public native Point pos();

    public native void pos(IntBuffer intBuffer);

    public native void pos(IntPointer intPointer);

    public native void pos(int[] iArr);

    @Cast({"const uchar*"})
    public native BytePointer ptr();

    public native MatConstIterator ptr(BytePointer bytePointer);

    @ByRef
    @Name({"operator ="})
    public native MatConstIterator put(@ByRef @Const MatConstIterator matConstIterator);

    public native void seek(@Cast({"ptrdiff_t"}) long j);

    public native void seek(@Cast({"ptrdiff_t"}) long j, @Cast({"bool"}) boolean z);

    public native void seek(@Const IntBuffer intBuffer);

    public native void seek(@Const IntBuffer intBuffer, @Cast({"bool"}) boolean z);

    public native void seek(@Const IntPointer intPointer);

    public native void seek(@Const IntPointer intPointer, @Cast({"bool"}) boolean z);

    public native void seek(@Const int[] iArr);

    public native void seek(@Const int[] iArr, @Cast({"bool"}) boolean z);

    @Cast({"const uchar*"})
    public native BytePointer sliceEnd();

    public native MatConstIterator sliceEnd(BytePointer bytePointer);

    @Cast({"const uchar*"})
    public native BytePointer sliceStart();

    public native MatConstIterator sliceStart(BytePointer bytePointer);

    @ByRef
    @Name({"operator -="})
    public native MatConstIterator subtractPut(@Cast({"ptrdiff_t"}) long j);

    static {
        Loader.load();
    }

    public MatConstIterator(Pointer pointer) {
        super(pointer);
    }

    public MatConstIterator(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public MatConstIterator position(long j) {
        return (MatConstIterator) super.position(j);
    }

    public MatConstIterator getPointer(long j) {
        return new MatConstIterator(this).position(this.position + j);
    }

    public MatConstIterator() {
        super((Pointer) null);
        allocate();
    }

    public MatConstIterator(@Const Mat mat) {
        super((Pointer) null);
        allocate(mat);
    }

    public MatConstIterator(@Const Mat mat, int i, int i2) {
        super((Pointer) null);
        allocate(mat, i, i2);
    }

    public MatConstIterator(@Const Mat mat, int i) {
        super((Pointer) null);
        allocate(mat, i);
    }

    public MatConstIterator(@Const Mat mat, @ByVal Point point) {
        super((Pointer) null);
        allocate(mat, point);
    }

    public MatConstIterator(@ByRef @Const MatConstIterator matConstIterator) {
        super((Pointer) null);
        allocate(matConstIterator);
    }
}
