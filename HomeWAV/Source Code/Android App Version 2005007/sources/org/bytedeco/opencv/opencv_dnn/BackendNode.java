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
public class BackendNode extends Pointer {
    private native void allocate(int i);

    public native int backendId();

    public native BackendNode backendId(int i);

    static {
        Loader.load();
    }

    public BackendNode(Pointer pointer) {
        super(pointer);
    }

    public BackendNode(int i) {
        super((Pointer) null);
        allocate(i);
    }
}
