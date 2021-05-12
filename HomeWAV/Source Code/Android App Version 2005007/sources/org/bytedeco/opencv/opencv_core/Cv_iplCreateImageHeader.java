package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Convention;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
@Convention("CV_STDCALL")
public class Cv_iplCreateImageHeader extends FunctionPointer {
    private native void allocate();

    public native IplImage call(int i, int i2, int i3, @Cast({"char*"}) BytePointer bytePointer, @Cast({"char*"}) BytePointer bytePointer2, int i4, int i5, int i6, int i7, int i8, IplROI iplROI, IplImage iplImage, Pointer pointer, IplTileInfo iplTileInfo);

    static {
        Loader.load();
    }

    public Cv_iplCreateImageHeader(Pointer pointer) {
        super(pointer);
    }

    protected Cv_iplCreateImageHeader() {
        allocate();
    }
}
