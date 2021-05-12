package org.bytedeco.opencv.opencv_features2d;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_features2d;

@Properties(inherit = {opencv_features2d.class})
@Name({"cv::Accumulator<unsigned char>"})
public class Accumulator extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    static {
        Loader.load();
    }

    public Accumulator() {
        super((Pointer) null);
        allocate();
    }

    public Accumulator(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public Accumulator(Pointer pointer) {
        super(pointer);
    }

    public Accumulator position(long j) {
        return (Accumulator) super.position(j);
    }

    public Accumulator getPointer(long j) {
        return new Accumulator((Pointer) this).position(this.position + j);
    }
}
