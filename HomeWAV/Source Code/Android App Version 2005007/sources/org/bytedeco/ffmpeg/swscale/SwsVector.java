package org.bytedeco.ffmpeg.swscale;

import org.bytedeco.ffmpeg.presets.swscale;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {swscale.class})
public class SwsVector extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native SwsVector coeff(DoublePointer doublePointer);

    public native DoublePointer coeff();

    public native int length();

    public native SwsVector length(int i);

    static {
        Loader.load();
    }

    public SwsVector() {
        super((Pointer) null);
        allocate();
    }

    public SwsVector(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public SwsVector(Pointer pointer) {
        super(pointer);
    }

    public SwsVector position(long j) {
        return (SwsVector) super.position(j);
    }

    public SwsVector getPointer(long j) {
        return new SwsVector((Pointer) this).position(this.position + j);
    }
}
