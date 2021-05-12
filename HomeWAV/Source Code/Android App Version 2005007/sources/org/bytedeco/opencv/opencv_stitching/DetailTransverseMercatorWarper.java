package org.bytedeco.opencv.opencv_stitching;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_stitching;

@Properties(inherit = {opencv_stitching.class})
@Name({"cv::detail::TransverseMercatorWarper"})
public class DetailTransverseMercatorWarper extends RotationWarper {
    private native void allocate(float f);

    static {
        Loader.load();
    }

    public DetailTransverseMercatorWarper(Pointer pointer) {
        super(pointer);
    }

    public DetailTransverseMercatorWarper(float f) {
        super((Pointer) null);
        allocate(f);
    }
}
