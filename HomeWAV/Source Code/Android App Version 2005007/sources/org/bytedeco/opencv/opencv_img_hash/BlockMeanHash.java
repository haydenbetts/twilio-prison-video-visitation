package org.bytedeco.opencv.opencv_img_hash;

import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.StdVector;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_img_hash;

@Namespace("cv::img_hash")
@Properties(inherit = {opencv_img_hash.class})
public class BlockMeanHash extends ImgHashBase {
    @opencv_core.Ptr
    public static native BlockMeanHash create();

    @opencv_core.Ptr
    public static native BlockMeanHash create(int i);

    @StdVector
    public native DoublePointer getMean();

    public native void setMode(int i);

    static {
        Loader.load();
    }

    public BlockMeanHash(Pointer pointer) {
        super(pointer);
    }
}
