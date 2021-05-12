package org.bytedeco.opencv.opencv_videostab;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.Virtual;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.presets.opencv_videostab;

@Namespace("cv::videostab")
@Properties(inherit = {opencv_videostab.class})
public class IFrameSource extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    @Virtual(true)
    @ByVal
    public native Mat nextFrame();

    @Virtual(true)
    public native void reset();

    static {
        Loader.load();
    }

    public IFrameSource() {
        super((Pointer) null);
        allocate();
    }

    public IFrameSource(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public IFrameSource(Pointer pointer) {
        super(pointer);
    }

    public IFrameSource position(long j) {
        return (IFrameSource) super.position(j);
    }

    public IFrameSource getPointer(long j) {
        return new IFrameSource((Pointer) this).position(this.position + j);
    }
}
