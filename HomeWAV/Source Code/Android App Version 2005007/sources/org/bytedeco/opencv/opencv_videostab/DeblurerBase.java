package org.bytedeco.opencv.opencv_videostab;

import java.nio.FloatBuffer;
import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.StdVector;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.Range;
import org.bytedeco.opencv.presets.opencv_videostab;

@Namespace("cv::videostab")
@Properties(inherit = {opencv_videostab.class})
@NoOffset
public class DeblurerBase extends Pointer {
    @StdVector
    public native FloatPointer blurrinessRates();

    public native void deblur(int i, @ByRef Mat mat, @ByRef @Const Range range);

    @ByRef
    @Const
    public native MatVector frames();

    @ByRef
    @Const
    public native MatVector motions();

    public native int radius();

    public native void setBlurrinessRates(@StdVector FloatBuffer floatBuffer);

    public native void setBlurrinessRates(@StdVector FloatPointer floatPointer);

    public native void setBlurrinessRates(@StdVector float[] fArr);

    public native void setFrames(@ByRef @Const MatVector matVector);

    public native void setMotions(@ByRef @Const MatVector matVector);

    public native void setRadius(int i);

    static {
        Loader.load();
    }

    public DeblurerBase(Pointer pointer) {
        super(pointer);
    }
}
