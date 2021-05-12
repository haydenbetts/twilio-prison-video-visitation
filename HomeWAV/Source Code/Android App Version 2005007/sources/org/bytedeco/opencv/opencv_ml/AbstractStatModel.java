package org.bytedeco.opencv.opencv_ml;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Algorithm;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_ml;

@Properties(inherit = {opencv_ml.class})
@Name({"cv::ml::StatModel"})
public abstract class AbstractStatModel extends Algorithm {
    @opencv_core.Ptr
    @Name({"load<cv::ml::ANN_MLP>"})
    public static native ANN_MLP loadANN_MLP(@opencv_core.Str String str, @opencv_core.Str String str2);

    @opencv_core.Ptr
    @Name({"load<cv::ml::ANN_MLP>"})
    public static native ANN_MLP loadANN_MLP(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2);

    @opencv_core.Ptr
    @Name({"load<cv::ml::Boost>"})
    public static native Boost loadBoost(@opencv_core.Str String str, @opencv_core.Str String str2);

    @opencv_core.Ptr
    @Name({"load<cv::ml::Boost>"})
    public static native Boost loadBoost(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2);

    @opencv_core.Ptr
    @Name({"load<cv::ml::DTrees>"})
    public static native DTrees loadDTrees(@opencv_core.Str String str, @opencv_core.Str String str2);

    @opencv_core.Ptr
    @Name({"load<cv::ml::DTrees>"})
    public static native DTrees loadDTrees(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2);

    @opencv_core.Ptr
    @Name({"load<cv::ml::EM>"})
    public static native EM loadEM(@opencv_core.Str String str, @opencv_core.Str String str2);

    @opencv_core.Ptr
    @Name({"load<cv::ml::EM>"})
    public static native EM loadEM(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2);

    @opencv_core.Ptr
    @Name({"load<cv::ml::KNearest>"})
    public static native KNearest loadKNearest(@opencv_core.Str String str, @opencv_core.Str String str2);

    @opencv_core.Ptr
    @Name({"load<cv::ml::KNearest>"})
    public static native KNearest loadKNearest(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2);

    @opencv_core.Ptr
    @Name({"load<cv::ml::LogisticRegression>"})
    public static native LogisticRegression loadLogisticRegression(@opencv_core.Str String str, @opencv_core.Str String str2);

    @opencv_core.Ptr
    @Name({"load<cv::ml::LogisticRegression>"})
    public static native LogisticRegression loadLogisticRegression(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2);

    @opencv_core.Ptr
    @Name({"load<cv::ml::NormalBayesClassifier>"})
    public static native NormalBayesClassifier loadNormalBayesClassifier(@opencv_core.Str String str, @opencv_core.Str String str2);

    @opencv_core.Ptr
    @Name({"load<cv::ml::NormalBayesClassifier>"})
    public static native NormalBayesClassifier loadNormalBayesClassifier(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2);

    @opencv_core.Ptr
    @Name({"load<cv::ml::RTrees>"})
    public static native RTrees loadRTrees(@opencv_core.Str String str, @opencv_core.Str String str2);

    @opencv_core.Ptr
    @Name({"load<cv::ml::RTrees>"})
    public static native RTrees loadRTrees(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2);

    @opencv_core.Ptr
    @Name({"load<cv::ml::SVM>"})
    public static native SVM loadSVM(@opencv_core.Str String str, @opencv_core.Str String str2);

    @opencv_core.Ptr
    @Name({"load<cv::ml::SVM>"})
    public static native SVM loadSVM(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2);

    static {
        Loader.load();
    }

    public AbstractStatModel(Pointer pointer) {
        super(pointer);
    }
}
