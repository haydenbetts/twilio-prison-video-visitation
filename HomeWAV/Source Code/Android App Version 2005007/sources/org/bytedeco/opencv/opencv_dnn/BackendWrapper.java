package org.bytedeco.opencv.opencv_dnn;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_dnn;

@Namespace("cv::dnn")
@Properties(inherit = {opencv_dnn.class})
@NoOffset
public class BackendWrapper extends Pointer {
    public native int backendId();

    public native BackendWrapper backendId(int i);

    public native void copyToHost();

    public native void setHostDirty();

    public native int targetId();

    public native BackendWrapper targetId(int i);

    static {
        Loader.load();
    }

    public BackendWrapper(Pointer pointer) {
        super(pointer);
    }
}
