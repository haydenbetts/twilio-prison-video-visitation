package org.bytedeco.opencv.opencv_features2d;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.StdVector;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_features2d;

@Namespace("cv")
@Properties(inherit = {opencv_features2d.class})
public class BRISK extends Feature2D {
    private native void allocate();

    private native void allocateArray(long j);

    @opencv_core.Ptr
    public static native BRISK create();

    @opencv_core.Ptr
    public static native BRISK create(int i, int i2, float f);

    @opencv_core.Ptr
    public static native BRISK create(int i, int i2, @StdVector FloatBuffer floatBuffer, @StdVector IntBuffer intBuffer);

    @opencv_core.Ptr
    public static native BRISK create(int i, int i2, @StdVector FloatBuffer floatBuffer, @StdVector IntBuffer intBuffer, float f, float f2, @StdVector IntBuffer intBuffer2);

    @opencv_core.Ptr
    public static native BRISK create(int i, int i2, @StdVector FloatPointer floatPointer, @StdVector IntPointer intPointer);

    @opencv_core.Ptr
    public static native BRISK create(int i, int i2, @StdVector FloatPointer floatPointer, @StdVector IntPointer intPointer, float f, float f2, @StdVector IntPointer intPointer2);

    @opencv_core.Ptr
    public static native BRISK create(int i, int i2, @StdVector float[] fArr, @StdVector int[] iArr);

    @opencv_core.Ptr
    public static native BRISK create(int i, int i2, @StdVector float[] fArr, @StdVector int[] iArr, float f, float f2, @StdVector int[] iArr2);

    @opencv_core.Ptr
    public static native BRISK create(@StdVector FloatBuffer floatBuffer, @StdVector IntBuffer intBuffer);

    @opencv_core.Ptr
    public static native BRISK create(@StdVector FloatBuffer floatBuffer, @StdVector IntBuffer intBuffer, float f, float f2, @StdVector IntBuffer intBuffer2);

    @opencv_core.Ptr
    public static native BRISK create(@StdVector FloatPointer floatPointer, @StdVector IntPointer intPointer);

    @opencv_core.Ptr
    public static native BRISK create(@StdVector FloatPointer floatPointer, @StdVector IntPointer intPointer, float f, float f2, @StdVector IntPointer intPointer2);

    @opencv_core.Ptr
    public static native BRISK create(@StdVector float[] fArr, @StdVector int[] iArr);

    @opencv_core.Ptr
    public static native BRISK create(@StdVector float[] fArr, @StdVector int[] iArr, float f, float f2, @StdVector int[] iArr2);

    @opencv_core.Str
    public native BytePointer getDefaultName();

    public native int getOctaves();

    public native int getThreshold();

    public native void setOctaves(int i);

    public native void setThreshold(int i);

    static {
        Loader.load();
    }

    public BRISK() {
        super((Pointer) null);
        allocate();
    }

    public BRISK(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public BRISK(Pointer pointer) {
        super(pointer);
    }

    public BRISK position(long j) {
        return (BRISK) super.position(j);
    }

    public BRISK getPointer(long j) {
        return new BRISK((Pointer) this).position(this.position + j);
    }
}
