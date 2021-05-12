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
import org.bytedeco.opencv.opencv_core.TermCriteria;
import org.bytedeco.opencv.presets.opencv_features2d;

@Namespace("cv")
@Properties(inherit = {opencv_features2d.class})
@NoOffset
public class BOWKMeansTrainer extends BOWTrainer {
    private native void allocate(int i);

    private native void allocate(int i, @ByRef(nullValue = "cv::TermCriteria()") @Const TermCriteria termCriteria, int i2, int i3);

    @ByVal
    public native Mat cluster();

    @ByVal
    public native Mat cluster(@ByRef @Const Mat mat);

    static {
        Loader.load();
    }

    public BOWKMeansTrainer(Pointer pointer) {
        super(pointer);
    }

    public BOWKMeansTrainer(int i, @ByRef(nullValue = "cv::TermCriteria()") @Const TermCriteria termCriteria, int i2, int i3) {
        super((Pointer) null);
        allocate(i, termCriteria, i2, i3);
    }

    public BOWKMeansTrainer(int i) {
        super((Pointer) null);
        allocate(i);
    }
}
