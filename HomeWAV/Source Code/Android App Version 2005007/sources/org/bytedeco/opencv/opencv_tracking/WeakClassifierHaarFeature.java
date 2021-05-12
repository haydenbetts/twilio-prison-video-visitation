package org.bytedeco.opencv.opencv_tracking;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_tracking;

@Namespace("cv")
@Properties(inherit = {opencv_tracking.class})
@NoOffset
public class WeakClassifierHaarFeature extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native int eval(float f);

    @Cast({"bool"})
    public native boolean update(float f, int i);

    static {
        Loader.load();
    }

    public WeakClassifierHaarFeature(Pointer pointer) {
        super(pointer);
    }

    public WeakClassifierHaarFeature(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public WeakClassifierHaarFeature position(long j) {
        return (WeakClassifierHaarFeature) super.position(j);
    }

    public WeakClassifierHaarFeature getPointer(long j) {
        return new WeakClassifierHaarFeature((Pointer) this).position(this.position + j);
    }

    public WeakClassifierHaarFeature() {
        super((Pointer) null);
        allocate();
    }
}
