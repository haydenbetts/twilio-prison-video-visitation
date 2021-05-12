package org.bytedeco.opencv.opencv_videostab;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_videostab;

@Namespace("cv::videostab")
@Properties(inherit = {opencv_videostab.class})
@NoOffset
public class MaskFrameSource extends IFrameSource {
    private native void allocate(@opencv_core.Ptr IFrameSource iFrameSource);

    @ByVal
    public native Mat nextFrame();

    public native void reset();

    public native void setMaskCallback(@ByVal opencv_videostab.MaskCallback maskCallback);

    static {
        Loader.load();
    }

    public MaskFrameSource(Pointer pointer) {
        super(pointer);
    }

    public MaskFrameSource(@opencv_core.Ptr IFrameSource iFrameSource) {
        super((Pointer) null);
        allocate(iFrameSource);
    }
}
