package org.bytedeco.opencv.global;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.StdString;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.GpuMatVector;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.PointVectorVector;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_core.StringVector;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.opencv_core.UMatVector;
import org.bytedeco.opencv.opencv_text.ERFilter;
import org.bytedeco.opencv.opencv_text.ERStatVectorVector;
import org.bytedeco.opencv.opencv_text.OCRBeamSearchDecoder;
import org.bytedeco.opencv.opencv_text.OCRHMMDecoder;
import org.bytedeco.opencv.presets.opencv_core;

public class opencv_text extends org.bytedeco.opencv.presets.opencv_text {
    public static final int ERFILTER_NM_IHSGrad = 1;
    public static final int ERFILTER_NM_RGBLGrad = 0;
    public static final int ERGROUPING_ORIENTATION_ANY = 1;
    public static final int ERGROUPING_ORIENTATION_HORIZ = 0;
    public static final int OCR_CNN_CLASSIFIER = 1;
    public static final int OCR_DECODER_VITERBI = 0;
    public static final int OCR_KNN_CLASSIFIER = 0;
    public static final int OCR_LEVEL_TEXTLINE = 1;
    public static final int OCR_LEVEL_WORD = 0;
    public static final int OEM_CUBE_ONLY = 1;
    public static final int OEM_DEFAULT = 3;
    public static final int OEM_TESSERACT_CUBE_COMBINED = 2;
    public static final int OEM_TESSERACT_ONLY = 0;
    public static final int PSM_AUTO = 3;
    public static final int PSM_AUTO_ONLY = 2;
    public static final int PSM_AUTO_OSD = 1;
    public static final int PSM_CIRCLE_WORD = 9;
    public static final int PSM_OSD_ONLY = 0;
    public static final int PSM_SINGLE_BLOCK = 6;
    public static final int PSM_SINGLE_BLOCK_VERT_TEXT = 5;
    public static final int PSM_SINGLE_CHAR = 10;
    public static final int PSM_SINGLE_COLUMN = 4;
    public static final int PSM_SINGLE_LINE = 7;
    public static final int PSM_SINGLE_WORD = 8;

    @Namespace("cv::text")
    public static native void MSERsToERStats(@ByVal GpuMat gpuMat, @ByRef PointVectorVector pointVectorVector, @ByRef ERStatVectorVector eRStatVectorVector);

    @Namespace("cv::text")
    public static native void MSERsToERStats(@ByVal Mat mat, @ByRef PointVectorVector pointVectorVector, @ByRef ERStatVectorVector eRStatVectorVector);

    @Namespace("cv::text")
    public static native void MSERsToERStats(@ByVal UMat uMat, @ByRef PointVectorVector pointVectorVector, @ByRef ERStatVectorVector eRStatVectorVector);

    @Namespace("cv::text")
    public static native void computeNMChannels(@ByVal GpuMat gpuMat, @ByVal GpuMatVector gpuMatVector);

    @Namespace("cv::text")
    public static native void computeNMChannels(@ByVal GpuMat gpuMat, @ByVal GpuMatVector gpuMatVector, int i);

    @Namespace("cv::text")
    public static native void computeNMChannels(@ByVal GpuMat gpuMat, @ByVal MatVector matVector);

    @Namespace("cv::text")
    public static native void computeNMChannels(@ByVal GpuMat gpuMat, @ByVal MatVector matVector, int i);

    @Namespace("cv::text")
    public static native void computeNMChannels(@ByVal GpuMat gpuMat, @ByVal UMatVector uMatVector);

    @Namespace("cv::text")
    public static native void computeNMChannels(@ByVal GpuMat gpuMat, @ByVal UMatVector uMatVector, int i);

    @Namespace("cv::text")
    public static native void computeNMChannels(@ByVal Mat mat, @ByVal GpuMatVector gpuMatVector);

    @Namespace("cv::text")
    public static native void computeNMChannels(@ByVal Mat mat, @ByVal GpuMatVector gpuMatVector, int i);

    @Namespace("cv::text")
    public static native void computeNMChannels(@ByVal Mat mat, @ByVal MatVector matVector);

    @Namespace("cv::text")
    public static native void computeNMChannels(@ByVal Mat mat, @ByVal MatVector matVector, int i);

    @Namespace("cv::text")
    public static native void computeNMChannels(@ByVal Mat mat, @ByVal UMatVector uMatVector);

    @Namespace("cv::text")
    public static native void computeNMChannels(@ByVal Mat mat, @ByVal UMatVector uMatVector, int i);

    @Namespace("cv::text")
    public static native void computeNMChannels(@ByVal UMat uMat, @ByVal GpuMatVector gpuMatVector);

    @Namespace("cv::text")
    public static native void computeNMChannels(@ByVal UMat uMat, @ByVal GpuMatVector gpuMatVector, int i);

    @Namespace("cv::text")
    public static native void computeNMChannels(@ByVal UMat uMat, @ByVal MatVector matVector);

    @Namespace("cv::text")
    public static native void computeNMChannels(@ByVal UMat uMat, @ByVal MatVector matVector, int i);

    @Namespace("cv::text")
    public static native void computeNMChannels(@ByVal UMat uMat, @ByVal UMatVector uMatVector);

    @Namespace("cv::text")
    public static native void computeNMChannels(@ByVal UMat uMat, @ByVal UMatVector uMatVector, int i);

    @Namespace("cv::text")
    @opencv_core.Ptr
    public static native ERFilter createERFilterNM1(@opencv_core.Str String str);

    @Namespace("cv::text")
    @opencv_core.Ptr
    public static native ERFilter createERFilterNM1(@opencv_core.Str String str, int i, float f, float f2, float f3, @Cast({"bool"}) boolean z, float f4);

    @Namespace("cv::text")
    @opencv_core.Ptr
    public static native ERFilter createERFilterNM1(@opencv_core.Str BytePointer bytePointer);

    @Namespace("cv::text")
    @opencv_core.Ptr
    public static native ERFilter createERFilterNM1(@opencv_core.Str BytePointer bytePointer, int i, float f, float f2, float f3, @Cast({"bool"}) boolean z, float f4);

    @Namespace("cv::text")
    @opencv_core.Ptr
    public static native ERFilter createERFilterNM1(@opencv_core.Ptr ERFilter.Callback callback);

    @Namespace("cv::text")
    @opencv_core.Ptr
    public static native ERFilter createERFilterNM1(@opencv_core.Ptr ERFilter.Callback callback, int i, float f, float f2, float f3, @Cast({"bool"}) boolean z, float f4);

    @Namespace("cv::text")
    @opencv_core.Ptr
    public static native ERFilter createERFilterNM2(@opencv_core.Str String str);

    @Namespace("cv::text")
    @opencv_core.Ptr
    public static native ERFilter createERFilterNM2(@opencv_core.Str String str, float f);

    @Namespace("cv::text")
    @opencv_core.Ptr
    public static native ERFilter createERFilterNM2(@opencv_core.Str BytePointer bytePointer);

    @Namespace("cv::text")
    @opencv_core.Ptr
    public static native ERFilter createERFilterNM2(@opencv_core.Str BytePointer bytePointer, float f);

    @Namespace("cv::text")
    @opencv_core.Ptr
    public static native ERFilter createERFilterNM2(@opencv_core.Ptr ERFilter.Callback callback);

    @Namespace("cv::text")
    @opencv_core.Ptr
    public static native ERFilter createERFilterNM2(@opencv_core.Ptr ERFilter.Callback callback, float f);

    @Namespace("cv::text")
    @ByVal
    public static native Mat createOCRHMMTransitionsTable(@opencv_core.Str String str, @ByRef StringVector stringVector);

    @Namespace("cv::text")
    @ByVal
    public static native Mat createOCRHMMTransitionsTable(@opencv_core.Str BytePointer bytePointer, @ByRef StringVector stringVector);

    @Namespace("cv::text")
    public static native void createOCRHMMTransitionsTable(@ByRef @StdString BytePointer bytePointer, @ByRef StringVector stringVector, @ByVal GpuMat gpuMat);

    @Namespace("cv::text")
    public static native void createOCRHMMTransitionsTable(@ByRef @StdString BytePointer bytePointer, @ByRef StringVector stringVector, @ByVal Mat mat);

    @Namespace("cv::text")
    public static native void createOCRHMMTransitionsTable(@ByRef @StdString BytePointer bytePointer, @ByRef StringVector stringVector, @ByVal UMat uMat);

    @Namespace("cv::text")
    public static native void detectRegions(@ByVal GpuMat gpuMat, @opencv_core.Ptr ERFilter eRFilter, @opencv_core.Ptr ERFilter eRFilter2, @ByRef PointVectorVector pointVectorVector);

    @Namespace("cv::text")
    public static native void detectRegions(@ByVal GpuMat gpuMat, @opencv_core.Ptr ERFilter eRFilter, @opencv_core.Ptr ERFilter eRFilter2, @ByRef RectVector rectVector);

    @Namespace("cv::text")
    public static native void detectRegions(@ByVal GpuMat gpuMat, @opencv_core.Ptr ERFilter eRFilter, @opencv_core.Ptr ERFilter eRFilter2, @ByRef RectVector rectVector, int i, @opencv_core.Str String str, float f);

    @Namespace("cv::text")
    public static native void detectRegions(@ByVal GpuMat gpuMat, @opencv_core.Ptr ERFilter eRFilter, @opencv_core.Ptr ERFilter eRFilter2, @ByRef RectVector rectVector, int i, @opencv_core.Str BytePointer bytePointer, float f);

    @Namespace("cv::text")
    public static native void detectRegions(@ByVal Mat mat, @opencv_core.Ptr ERFilter eRFilter, @opencv_core.Ptr ERFilter eRFilter2, @ByRef PointVectorVector pointVectorVector);

    @Namespace("cv::text")
    public static native void detectRegions(@ByVal Mat mat, @opencv_core.Ptr ERFilter eRFilter, @opencv_core.Ptr ERFilter eRFilter2, @ByRef RectVector rectVector);

    @Namespace("cv::text")
    public static native void detectRegions(@ByVal Mat mat, @opencv_core.Ptr ERFilter eRFilter, @opencv_core.Ptr ERFilter eRFilter2, @ByRef RectVector rectVector, int i, @opencv_core.Str String str, float f);

    @Namespace("cv::text")
    public static native void detectRegions(@ByVal Mat mat, @opencv_core.Ptr ERFilter eRFilter, @opencv_core.Ptr ERFilter eRFilter2, @ByRef RectVector rectVector, int i, @opencv_core.Str BytePointer bytePointer, float f);

    @Namespace("cv::text")
    public static native void detectRegions(@ByVal UMat uMat, @opencv_core.Ptr ERFilter eRFilter, @opencv_core.Ptr ERFilter eRFilter2, @ByRef PointVectorVector pointVectorVector);

    @Namespace("cv::text")
    public static native void detectRegions(@ByVal UMat uMat, @opencv_core.Ptr ERFilter eRFilter, @opencv_core.Ptr ERFilter eRFilter2, @ByRef RectVector rectVector);

    @Namespace("cv::text")
    public static native void detectRegions(@ByVal UMat uMat, @opencv_core.Ptr ERFilter eRFilter, @opencv_core.Ptr ERFilter eRFilter2, @ByRef RectVector rectVector, int i, @opencv_core.Str String str, float f);

    @Namespace("cv::text")
    public static native void detectRegions(@ByVal UMat uMat, @opencv_core.Ptr ERFilter eRFilter, @opencv_core.Ptr ERFilter eRFilter2, @ByRef RectVector rectVector, int i, @opencv_core.Str BytePointer bytePointer, float f);

    @Namespace("cv::text")
    public static native void erGrouping(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal PointVectorVector pointVectorVector, @ByRef RectVector rectVector);

    @Namespace("cv::text")
    public static native void erGrouping(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal PointVectorVector pointVectorVector, @ByRef RectVector rectVector, int i, @opencv_core.Str String str, float f);

    @Namespace("cv::text")
    public static native void erGrouping(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal PointVectorVector pointVectorVector, @ByRef RectVector rectVector, int i, @opencv_core.Str BytePointer bytePointer, float f);

    @Namespace("cv::text")
    public static native void erGrouping(@ByVal GpuMat gpuMat, @ByVal GpuMatVector gpuMatVector, @ByRef ERStatVectorVector eRStatVectorVector, @ByRef @Cast({"std::vector<std::vector<cv::Vec2i> >*"}) PointVectorVector pointVectorVector, @ByRef RectVector rectVector);

    @Namespace("cv::text")
    public static native void erGrouping(@ByVal GpuMat gpuMat, @ByVal GpuMatVector gpuMatVector, @ByRef ERStatVectorVector eRStatVectorVector, @ByRef @Cast({"std::vector<std::vector<cv::Vec2i> >*"}) PointVectorVector pointVectorVector, @ByRef RectVector rectVector, int i, @StdString String str, float f);

    @Namespace("cv::text")
    public static native void erGrouping(@ByVal GpuMat gpuMat, @ByVal GpuMatVector gpuMatVector, @ByRef ERStatVectorVector eRStatVectorVector, @ByRef @Cast({"std::vector<std::vector<cv::Vec2i> >*"}) PointVectorVector pointVectorVector, @ByRef RectVector rectVector, int i, @StdString BytePointer bytePointer, float f);

    @Namespace("cv::text")
    public static native void erGrouping(@ByVal GpuMat gpuMat, @ByVal MatVector matVector, @ByRef ERStatVectorVector eRStatVectorVector, @ByRef @Cast({"std::vector<std::vector<cv::Vec2i> >*"}) PointVectorVector pointVectorVector, @ByRef RectVector rectVector);

    @Namespace("cv::text")
    public static native void erGrouping(@ByVal GpuMat gpuMat, @ByVal MatVector matVector, @ByRef ERStatVectorVector eRStatVectorVector, @ByRef @Cast({"std::vector<std::vector<cv::Vec2i> >*"}) PointVectorVector pointVectorVector, @ByRef RectVector rectVector, int i, @StdString String str, float f);

    @Namespace("cv::text")
    public static native void erGrouping(@ByVal GpuMat gpuMat, @ByVal MatVector matVector, @ByRef ERStatVectorVector eRStatVectorVector, @ByRef @Cast({"std::vector<std::vector<cv::Vec2i> >*"}) PointVectorVector pointVectorVector, @ByRef RectVector rectVector, int i, @StdString BytePointer bytePointer, float f);

    @Namespace("cv::text")
    public static native void erGrouping(@ByVal GpuMat gpuMat, @ByVal UMatVector uMatVector, @ByRef ERStatVectorVector eRStatVectorVector, @ByRef @Cast({"std::vector<std::vector<cv::Vec2i> >*"}) PointVectorVector pointVectorVector, @ByRef RectVector rectVector);

    @Namespace("cv::text")
    public static native void erGrouping(@ByVal GpuMat gpuMat, @ByVal UMatVector uMatVector, @ByRef ERStatVectorVector eRStatVectorVector, @ByRef @Cast({"std::vector<std::vector<cv::Vec2i> >*"}) PointVectorVector pointVectorVector, @ByRef RectVector rectVector, int i, @StdString String str, float f);

    @Namespace("cv::text")
    public static native void erGrouping(@ByVal GpuMat gpuMat, @ByVal UMatVector uMatVector, @ByRef ERStatVectorVector eRStatVectorVector, @ByRef @Cast({"std::vector<std::vector<cv::Vec2i> >*"}) PointVectorVector pointVectorVector, @ByRef RectVector rectVector, int i, @StdString BytePointer bytePointer, float f);

    @Namespace("cv::text")
    public static native void erGrouping(@ByVal Mat mat, @ByVal GpuMatVector gpuMatVector, @ByRef ERStatVectorVector eRStatVectorVector, @ByRef @Cast({"std::vector<std::vector<cv::Vec2i> >*"}) PointVectorVector pointVectorVector, @ByRef RectVector rectVector);

    @Namespace("cv::text")
    public static native void erGrouping(@ByVal Mat mat, @ByVal GpuMatVector gpuMatVector, @ByRef ERStatVectorVector eRStatVectorVector, @ByRef @Cast({"std::vector<std::vector<cv::Vec2i> >*"}) PointVectorVector pointVectorVector, @ByRef RectVector rectVector, int i, @StdString String str, float f);

    @Namespace("cv::text")
    public static native void erGrouping(@ByVal Mat mat, @ByVal GpuMatVector gpuMatVector, @ByRef ERStatVectorVector eRStatVectorVector, @ByRef @Cast({"std::vector<std::vector<cv::Vec2i> >*"}) PointVectorVector pointVectorVector, @ByRef RectVector rectVector, int i, @StdString BytePointer bytePointer, float f);

    @Namespace("cv::text")
    public static native void erGrouping(@ByVal Mat mat, @ByVal Mat mat2, @ByVal PointVectorVector pointVectorVector, @ByRef RectVector rectVector);

    @Namespace("cv::text")
    public static native void erGrouping(@ByVal Mat mat, @ByVal Mat mat2, @ByVal PointVectorVector pointVectorVector, @ByRef RectVector rectVector, int i, @opencv_core.Str String str, float f);

    @Namespace("cv::text")
    public static native void erGrouping(@ByVal Mat mat, @ByVal Mat mat2, @ByVal PointVectorVector pointVectorVector, @ByRef RectVector rectVector, int i, @opencv_core.Str BytePointer bytePointer, float f);

    @Namespace("cv::text")
    public static native void erGrouping(@ByVal Mat mat, @ByVal MatVector matVector, @ByRef ERStatVectorVector eRStatVectorVector, @ByRef @Cast({"std::vector<std::vector<cv::Vec2i> >*"}) PointVectorVector pointVectorVector, @ByRef RectVector rectVector);

    @Namespace("cv::text")
    public static native void erGrouping(@ByVal Mat mat, @ByVal MatVector matVector, @ByRef ERStatVectorVector eRStatVectorVector, @ByRef @Cast({"std::vector<std::vector<cv::Vec2i> >*"}) PointVectorVector pointVectorVector, @ByRef RectVector rectVector, int i, @StdString String str, float f);

    @Namespace("cv::text")
    public static native void erGrouping(@ByVal Mat mat, @ByVal MatVector matVector, @ByRef ERStatVectorVector eRStatVectorVector, @ByRef @Cast({"std::vector<std::vector<cv::Vec2i> >*"}) PointVectorVector pointVectorVector, @ByRef RectVector rectVector, int i, @StdString BytePointer bytePointer, float f);

    @Namespace("cv::text")
    public static native void erGrouping(@ByVal Mat mat, @ByVal UMatVector uMatVector, @ByRef ERStatVectorVector eRStatVectorVector, @ByRef @Cast({"std::vector<std::vector<cv::Vec2i> >*"}) PointVectorVector pointVectorVector, @ByRef RectVector rectVector);

    @Namespace("cv::text")
    public static native void erGrouping(@ByVal Mat mat, @ByVal UMatVector uMatVector, @ByRef ERStatVectorVector eRStatVectorVector, @ByRef @Cast({"std::vector<std::vector<cv::Vec2i> >*"}) PointVectorVector pointVectorVector, @ByRef RectVector rectVector, int i, @StdString String str, float f);

    @Namespace("cv::text")
    public static native void erGrouping(@ByVal Mat mat, @ByVal UMatVector uMatVector, @ByRef ERStatVectorVector eRStatVectorVector, @ByRef @Cast({"std::vector<std::vector<cv::Vec2i> >*"}) PointVectorVector pointVectorVector, @ByRef RectVector rectVector, int i, @StdString BytePointer bytePointer, float f);

    @Namespace("cv::text")
    public static native void erGrouping(@ByVal UMat uMat, @ByVal GpuMatVector gpuMatVector, @ByRef ERStatVectorVector eRStatVectorVector, @ByRef @Cast({"std::vector<std::vector<cv::Vec2i> >*"}) PointVectorVector pointVectorVector, @ByRef RectVector rectVector);

    @Namespace("cv::text")
    public static native void erGrouping(@ByVal UMat uMat, @ByVal GpuMatVector gpuMatVector, @ByRef ERStatVectorVector eRStatVectorVector, @ByRef @Cast({"std::vector<std::vector<cv::Vec2i> >*"}) PointVectorVector pointVectorVector, @ByRef RectVector rectVector, int i, @StdString String str, float f);

    @Namespace("cv::text")
    public static native void erGrouping(@ByVal UMat uMat, @ByVal GpuMatVector gpuMatVector, @ByRef ERStatVectorVector eRStatVectorVector, @ByRef @Cast({"std::vector<std::vector<cv::Vec2i> >*"}) PointVectorVector pointVectorVector, @ByRef RectVector rectVector, int i, @StdString BytePointer bytePointer, float f);

    @Namespace("cv::text")
    public static native void erGrouping(@ByVal UMat uMat, @ByVal MatVector matVector, @ByRef ERStatVectorVector eRStatVectorVector, @ByRef @Cast({"std::vector<std::vector<cv::Vec2i> >*"}) PointVectorVector pointVectorVector, @ByRef RectVector rectVector);

    @Namespace("cv::text")
    public static native void erGrouping(@ByVal UMat uMat, @ByVal MatVector matVector, @ByRef ERStatVectorVector eRStatVectorVector, @ByRef @Cast({"std::vector<std::vector<cv::Vec2i> >*"}) PointVectorVector pointVectorVector, @ByRef RectVector rectVector, int i, @StdString String str, float f);

    @Namespace("cv::text")
    public static native void erGrouping(@ByVal UMat uMat, @ByVal MatVector matVector, @ByRef ERStatVectorVector eRStatVectorVector, @ByRef @Cast({"std::vector<std::vector<cv::Vec2i> >*"}) PointVectorVector pointVectorVector, @ByRef RectVector rectVector, int i, @StdString BytePointer bytePointer, float f);

    @Namespace("cv::text")
    public static native void erGrouping(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal PointVectorVector pointVectorVector, @ByRef RectVector rectVector);

    @Namespace("cv::text")
    public static native void erGrouping(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal PointVectorVector pointVectorVector, @ByRef RectVector rectVector, int i, @opencv_core.Str String str, float f);

    @Namespace("cv::text")
    public static native void erGrouping(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal PointVectorVector pointVectorVector, @ByRef RectVector rectVector, int i, @opencv_core.Str BytePointer bytePointer, float f);

    @Namespace("cv::text")
    public static native void erGrouping(@ByVal UMat uMat, @ByVal UMatVector uMatVector, @ByRef ERStatVectorVector eRStatVectorVector, @ByRef @Cast({"std::vector<std::vector<cv::Vec2i> >*"}) PointVectorVector pointVectorVector, @ByRef RectVector rectVector);

    @Namespace("cv::text")
    public static native void erGrouping(@ByVal UMat uMat, @ByVal UMatVector uMatVector, @ByRef ERStatVectorVector eRStatVectorVector, @ByRef @Cast({"std::vector<std::vector<cv::Vec2i> >*"}) PointVectorVector pointVectorVector, @ByRef RectVector rectVector, int i, @StdString String str, float f);

    @Namespace("cv::text")
    public static native void erGrouping(@ByVal UMat uMat, @ByVal UMatVector uMatVector, @ByRef ERStatVectorVector eRStatVectorVector, @ByRef @Cast({"std::vector<std::vector<cv::Vec2i> >*"}) PointVectorVector pointVectorVector, @ByRef RectVector rectVector, int i, @StdString BytePointer bytePointer, float f);

    @Namespace("cv::text")
    @opencv_core.Ptr
    public static native ERFilter.Callback loadClassifierNM1(@opencv_core.Str String str);

    @Namespace("cv::text")
    @opencv_core.Ptr
    public static native ERFilter.Callback loadClassifierNM1(@opencv_core.Str BytePointer bytePointer);

    @Namespace("cv::text")
    @opencv_core.Ptr
    public static native ERFilter.Callback loadClassifierNM2(@opencv_core.Str String str);

    @Namespace("cv::text")
    @opencv_core.Ptr
    public static native ERFilter.Callback loadClassifierNM2(@opencv_core.Str BytePointer bytePointer);

    @Namespace("cv::text")
    @opencv_core.Ptr
    public static native OCRBeamSearchDecoder.ClassifierCallback loadOCRBeamSearchClassifierCNN(@opencv_core.Str String str);

    @Namespace("cv::text")
    @opencv_core.Ptr
    public static native OCRBeamSearchDecoder.ClassifierCallback loadOCRBeamSearchClassifierCNN(@opencv_core.Str BytePointer bytePointer);

    @Namespace("cv::text")
    @opencv_core.Ptr
    public static native OCRHMMDecoder.ClassifierCallback loadOCRHMMClassifier(@opencv_core.Str String str, int i);

    @Namespace("cv::text")
    @opencv_core.Ptr
    public static native OCRHMMDecoder.ClassifierCallback loadOCRHMMClassifier(@opencv_core.Str BytePointer bytePointer, int i);

    @Namespace("cv::text")
    @opencv_core.Ptr
    public static native OCRHMMDecoder.ClassifierCallback loadOCRHMMClassifierCNN(@opencv_core.Str String str);

    @Namespace("cv::text")
    @opencv_core.Ptr
    public static native OCRHMMDecoder.ClassifierCallback loadOCRHMMClassifierCNN(@opencv_core.Str BytePointer bytePointer);

    @Namespace("cv::text")
    @opencv_core.Ptr
    public static native OCRHMMDecoder.ClassifierCallback loadOCRHMMClassifierNM(@opencv_core.Str String str);

    @Namespace("cv::text")
    @opencv_core.Ptr
    public static native OCRHMMDecoder.ClassifierCallback loadOCRHMMClassifierNM(@opencv_core.Str BytePointer bytePointer);

    static {
        Loader.load();
    }
}
