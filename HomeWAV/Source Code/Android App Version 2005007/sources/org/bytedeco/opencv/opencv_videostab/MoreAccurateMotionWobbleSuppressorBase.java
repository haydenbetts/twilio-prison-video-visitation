package org.bytedeco.opencv.opencv_videostab;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_videostab;

@Namespace("cv::videostab")
@Properties(inherit = {opencv_videostab.class})
@NoOffset
public class MoreAccurateMotionWobbleSuppressorBase extends WobbleSuppressorBase {
    public native int period();

    public native void setPeriod(int i);

    static {
        Loader.load();
    }

    public MoreAccurateMotionWobbleSuppressorBase(Pointer pointer) {
        super(pointer);
    }
}
