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

@Namespace("cv")
@Properties(inherit = {opencv_core.class})
@NoOffset
public class DMatch extends Pointer {
    private native void allocate();

    private native void allocate(int i, int i2, float f);

    private native void allocate(int i, int i2, int i3, float f);

    private native void allocateArray(long j);

    public native float distance();

    public native DMatch distance(float f);

    public native int imgIdx();

    public native DMatch imgIdx(int i);

    @Cast({"bool"})
    @Name({"operator <"})
    public native boolean lessThan(@ByRef @Const DMatch dMatch);

    public native int queryIdx();

    public native DMatch queryIdx(int i);

    public native int trainIdx();

    public native DMatch trainIdx(int i);

    static {
        Loader.load();
    }

    public DMatch(Pointer pointer) {
        super(pointer);
    }

    public DMatch(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public DMatch position(long j) {
        return (DMatch) super.position(j);
    }

    public DMatch getPointer(long j) {
        return new DMatch((Pointer) this).position(this.position + j);
    }

    public DMatch() {
        super((Pointer) null);
        allocate();
    }

    public DMatch(int i, int i2, float f) {
        super((Pointer) null);
        allocate(i, i2, f);
    }

    public DMatch(int i, int i2, int i3, float f) {
        super((Pointer) null);
        allocate(i, i2, i3, f);
    }
}
