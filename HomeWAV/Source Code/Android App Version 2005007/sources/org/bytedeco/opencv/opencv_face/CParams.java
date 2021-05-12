package org.bytedeco.opencv.opencv_face;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_objdetect.CascadeClassifier;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_face;

@Namespace("cv::face")
@Properties(inherit = {opencv_face.class})
@NoOffset
public class CParams extends Pointer {
    private native void allocate(@opencv_core.Str String str);

    private native void allocate(@opencv_core.Str String str, double d, int i, @ByVal(nullValue = "cv::Size(30, 30)") Size size, @ByVal(nullValue = "cv::Size()") Size size2);

    private native void allocate(@opencv_core.Str BytePointer bytePointer);

    private native void allocate(@opencv_core.Str BytePointer bytePointer, double d, int i, @ByVal(nullValue = "cv::Size(30, 30)") Size size, @ByVal(nullValue = "cv::Size()") Size size2);

    @opencv_core.Str
    public native BytePointer cascade();

    public native CParams cascade(BytePointer bytePointer);

    public native CParams face_cascade(CascadeClassifier cascadeClassifier);

    @ByRef
    public native CascadeClassifier face_cascade();

    @ByRef
    public native Size maxSize();

    public native CParams maxSize(Size size);

    public native int minNeighbors();

    public native CParams minNeighbors(int i);

    @ByRef
    public native Size minSize();

    public native CParams minSize(Size size);

    public native double scaleFactor();

    public native CParams scaleFactor(double d);

    static {
        Loader.load();
    }

    public CParams(Pointer pointer) {
        super(pointer);
    }

    public CParams(@opencv_core.Str BytePointer bytePointer, double d, int i, @ByVal(nullValue = "cv::Size(30, 30)") Size size, @ByVal(nullValue = "cv::Size()") Size size2) {
        super((Pointer) null);
        allocate(bytePointer, d, i, size, size2);
    }

    public CParams(@opencv_core.Str BytePointer bytePointer) {
        super((Pointer) null);
        allocate(bytePointer);
    }

    public CParams(@opencv_core.Str String str, double d, int i, @ByVal(nullValue = "cv::Size(30, 30)") Size size, @ByVal(nullValue = "cv::Size()") Size size2) {
        super((Pointer) null);
        allocate(str, d, i, size, size2);
    }

    public CParams(@opencv_core.Str String str) {
        super((Pointer) null);
        allocate(str);
    }
}
