package org.bytedeco.opencv.opencv_videostab;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_videostab;

@Namespace("cv::videostab")
@Properties(inherit = {opencv_videostab.class})
public class NullLog extends ILog {
    private native void allocate();

    private native void allocateArray(long j);

    public native void print(String str);

    public native void print(@Cast({"const char*"}) BytePointer bytePointer);

    static {
        Loader.load();
    }

    public NullLog() {
        super((Pointer) null);
        allocate();
    }

    public NullLog(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public NullLog(Pointer pointer) {
        super(pointer);
    }

    public NullLog position(long j) {
        return (NullLog) super.position(j);
    }

    public NullLog getPointer(long j) {
        return new NullLog((Pointer) this).position(this.position + j);
    }
}
