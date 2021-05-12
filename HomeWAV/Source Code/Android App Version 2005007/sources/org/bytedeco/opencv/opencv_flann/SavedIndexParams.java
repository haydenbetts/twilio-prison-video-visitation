package org.bytedeco.opencv.opencv_flann;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_flann;

@Namespace("cv::flann")
@Properties(inherit = {opencv_flann.class})
public class SavedIndexParams extends IndexParams {
    private native void allocate(@opencv_core.Str String str);

    private native void allocate(@opencv_core.Str BytePointer bytePointer);

    static {
        Loader.load();
    }

    public SavedIndexParams(Pointer pointer) {
        super(pointer);
    }

    public SavedIndexParams(@opencv_core.Str BytePointer bytePointer) {
        super((Pointer) null);
        allocate(bytePointer);
    }

    public SavedIndexParams(@opencv_core.Str String str) {
        super((Pointer) null);
        allocate(str);
    }
}
