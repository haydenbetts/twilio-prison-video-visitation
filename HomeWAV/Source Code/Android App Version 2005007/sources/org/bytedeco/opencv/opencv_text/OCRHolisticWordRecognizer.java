package org.bytedeco.opencv.opencv_text;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.StdString;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_core.StringVector;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_text;

@Namespace("cv::text")
@Properties(inherit = {opencv_text.class})
public class OCRHolisticWordRecognizer extends BaseOCR {
    @opencv_core.Ptr
    public static native OCRHolisticWordRecognizer create(@StdString String str, @StdString String str2, @StdString String str3);

    @opencv_core.Ptr
    public static native OCRHolisticWordRecognizer create(@StdString BytePointer bytePointer, @StdString BytePointer bytePointer2, @StdString BytePointer bytePointer3);

    public native void run(@ByRef Mat mat, @ByRef @StdString BytePointer bytePointer);

    public native void run(@ByRef Mat mat, @ByRef @StdString BytePointer bytePointer, RectVector rectVector, StringVector stringVector, FloatVector floatVector, int i);

    public native void run(@ByRef Mat mat, @ByRef Mat mat2, @ByRef @StdString BytePointer bytePointer);

    public native void run(@ByRef Mat mat, @ByRef Mat mat2, @ByRef @StdString BytePointer bytePointer, RectVector rectVector, StringVector stringVector, FloatVector floatVector, int i);

    static {
        Loader.load();
    }

    public OCRHolisticWordRecognizer(Pointer pointer) {
        super(pointer);
    }
}
