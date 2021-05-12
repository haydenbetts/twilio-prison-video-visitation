package org.bytedeco.opencv.opencv_tracking;

import java.nio.IntBuffer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.StdVector;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.presets.opencv_tracking;

@Namespace("cv")
@Properties(inherit = {opencv_tracking.class})
@NoOffset
public class StrongClassifierDirectSelection extends Pointer {
    private native void allocate(int i, int i2, @ByVal Size size, @ByRef @Const Rect rect);

    private native void allocate(int i, int i2, @ByVal Size size, @ByRef @Const Rect rect, @Cast({"bool"}) boolean z, int i3);

    public native float classifySmooth(@ByRef @Const MatVector matVector, @ByRef @Const Rect rect, @ByRef IntBuffer intBuffer);

    public native float classifySmooth(@ByRef @Const MatVector matVector, @ByRef @Const Rect rect, @ByRef IntPointer intPointer);

    public native float classifySmooth(@ByRef @Const MatVector matVector, @ByRef @Const Rect rect, @ByRef int[] iArr);

    public native float eval(@ByRef @Const Mat mat);

    public native int getNumBaseClassifier();

    @ByVal
    public native Size getPatchSize();

    @ByVal
    public native Rect getROI();

    public native int getReplacedClassifier();

    @StdVector
    public native IntPointer getSelectedWeakClassifier();

    public native int getSwappedClassifier();

    @Cast({"bool"})
    public native boolean getUseFeatureExchange();

    public native void initBaseClassifier();

    public native void replaceWeakClassifier(int i);

    @Cast({"bool"})
    public native boolean update(@ByRef @Const Mat mat, int i);

    @Cast({"bool"})
    public native boolean update(@ByRef @Const Mat mat, int i, float f);

    static {
        Loader.load();
    }

    public StrongClassifierDirectSelection(Pointer pointer) {
        super(pointer);
    }

    public StrongClassifierDirectSelection(int i, int i2, @ByVal Size size, @ByRef @Const Rect rect, @Cast({"bool"}) boolean z, int i3) {
        super((Pointer) null);
        allocate(i, i2, size, rect, z, i3);
    }

    public StrongClassifierDirectSelection(int i, int i2, @ByVal Size size, @ByRef @Const Rect rect) {
        super((Pointer) null);
        allocate(i, i2, size, rect);
    }
}
