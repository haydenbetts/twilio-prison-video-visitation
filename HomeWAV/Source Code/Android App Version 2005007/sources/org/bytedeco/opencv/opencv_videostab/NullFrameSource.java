package org.bytedeco.opencv.opencv_videostab;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.presets.opencv_videostab;

@Namespace("cv::videostab")
@Properties(inherit = {opencv_videostab.class})
public class NullFrameSource extends IFrameSource {
    private native void allocate();

    private native void allocateArray(long j);

    @ByVal
    public native Mat nextFrame();

    public native void reset();

    static {
        Loader.load();
    }

    public NullFrameSource() {
        super((Pointer) null);
        allocate();
    }

    public NullFrameSource(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public NullFrameSource(Pointer pointer) {
        super(pointer);
    }

    public NullFrameSource position(long j) {
        return (NullFrameSource) super.position(j);
    }

    public NullFrameSource getPointer(long j) {
        return new NullFrameSource((Pointer) this).position(this.position + j);
    }
}
