package org.bytedeco.opencv.opencv_dnn;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Range;
import org.bytedeco.opencv.presets.opencv_dnn;

@Namespace("cv::dnn")
@Properties(inherit = {opencv_dnn.class})
public class _Range extends Range {
    private native void allocate(int i);

    private native void allocate(int i, int i2);

    private native void allocate(@ByRef @Const Range range);

    static {
        Loader.load();
    }

    public _Range(Pointer pointer) {
        super(pointer);
    }

    public _Range(@ByRef @Const Range range) {
        super((Pointer) null);
        allocate(range);
    }

    public _Range(int i, int i2) {
        super((Pointer) null);
        allocate(i, i2);
    }

    public _Range(int i) {
        super((Pointer) null);
        allocate(i);
    }
}
