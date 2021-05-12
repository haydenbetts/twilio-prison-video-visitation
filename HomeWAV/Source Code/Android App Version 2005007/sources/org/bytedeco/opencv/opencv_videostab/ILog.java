package org.bytedeco.opencv.opencv_videostab;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_videostab;

@Namespace("cv::videostab")
@Properties(inherit = {opencv_videostab.class})
public class ILog extends Pointer {
    public native void print(String str);

    public native void print(@Cast({"const char*"}) BytePointer bytePointer);

    static {
        Loader.load();
    }

    public ILog(Pointer pointer) {
        super(pointer);
    }
}
