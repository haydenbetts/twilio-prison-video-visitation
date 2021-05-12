package org.bytedeco.opencv.opencv_features2d;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.presets.opencv_features2d;

@Namespace("cv")
@Properties(inherit = {opencv_features2d.class})
@NoOffset
public class BOWTrainer extends Pointer {
    public native void add(@ByRef @Const Mat mat);

    public native void clear();

    @ByVal
    public native Mat cluster();

    @ByVal
    public native Mat cluster(@ByRef @Const Mat mat);

    public native int descriptorsCount();

    @ByRef
    @Const
    public native MatVector getDescriptors();

    static {
        Loader.load();
    }

    public BOWTrainer(Pointer pointer) {
        super(pointer);
    }
}
