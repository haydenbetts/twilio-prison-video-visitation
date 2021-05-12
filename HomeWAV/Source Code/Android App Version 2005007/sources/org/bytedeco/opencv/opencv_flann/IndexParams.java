package org.bytedeco.opencv.opencv_flann;

import java.nio.DoubleBuffer;
import java.nio.IntBuffer;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.StdVector;
import org.bytedeco.opencv.opencv_core.StringVector;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_flann;

@Namespace("cv::flann")
@Properties(inherit = {opencv_flann.class})
@NoOffset
public class IndexParams extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native void getAll(@ByRef StringVector stringVector, @Cast({"cv::flann::FlannIndexType*"}) @StdVector IntBuffer intBuffer, @ByRef StringVector stringVector2, @StdVector DoubleBuffer doubleBuffer);

    public native void getAll(@ByRef StringVector stringVector, @Cast({"cv::flann::FlannIndexType*"}) @StdVector IntPointer intPointer, @ByRef StringVector stringVector2, @StdVector DoublePointer doublePointer);

    public native void getAll(@ByRef StringVector stringVector, @Cast({"cv::flann::FlannIndexType*"}) @StdVector int[] iArr, @ByRef StringVector stringVector2, @StdVector double[] dArr);

    public native double getDouble(@opencv_core.Str String str);

    public native double getDouble(@opencv_core.Str String str, double d);

    public native double getDouble(@opencv_core.Str BytePointer bytePointer);

    public native double getDouble(@opencv_core.Str BytePointer bytePointer, double d);

    public native int getInt(@opencv_core.Str String str);

    public native int getInt(@opencv_core.Str String str, int i);

    public native int getInt(@opencv_core.Str BytePointer bytePointer);

    public native int getInt(@opencv_core.Str BytePointer bytePointer, int i);

    @opencv_core.Str
    public native String getString(@opencv_core.Str String str);

    @opencv_core.Str
    public native String getString(@opencv_core.Str String str, @opencv_core.Str String str2);

    @opencv_core.Str
    public native BytePointer getString(@opencv_core.Str BytePointer bytePointer);

    @opencv_core.Str
    public native BytePointer getString(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2);

    public native Pointer params();

    public native IndexParams params(Pointer pointer);

    public native void setAlgorithm(int i);

    public native void setBool(@opencv_core.Str String str, @Cast({"bool"}) boolean z);

    public native void setBool(@opencv_core.Str BytePointer bytePointer, @Cast({"bool"}) boolean z);

    public native void setDouble(@opencv_core.Str String str, double d);

    public native void setDouble(@opencv_core.Str BytePointer bytePointer, double d);

    public native void setFloat(@opencv_core.Str String str, float f);

    public native void setFloat(@opencv_core.Str BytePointer bytePointer, float f);

    public native void setInt(@opencv_core.Str String str, int i);

    public native void setInt(@opencv_core.Str BytePointer bytePointer, int i);

    public native void setString(@opencv_core.Str String str, @opencv_core.Str String str2);

    public native void setString(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2);

    static {
        Loader.load();
    }

    public IndexParams(Pointer pointer) {
        super(pointer);
    }

    public IndexParams(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public IndexParams position(long j) {
        return (IndexParams) super.position(j);
    }

    public IndexParams getPointer(long j) {
        return new IndexParams((Pointer) this).position(this.position + j);
    }

    public IndexParams() {
        super((Pointer) null);
        allocate();
    }
}
