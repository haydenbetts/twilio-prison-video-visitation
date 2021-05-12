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
public class LogToStdout extends ILog {
    private native void allocate();

    private native void allocateArray(long j);

    public native void print(String str);

    public native void print(@Cast({"const char*"}) BytePointer bytePointer);

    static {
        Loader.load();
    }

    public LogToStdout() {
        super((Pointer) null);
        allocate();
    }

    public LogToStdout(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public LogToStdout(Pointer pointer) {
        super(pointer);
    }

    public LogToStdout position(long j) {
        return (LogToStdout) super.position(j);
    }

    public LogToStdout getPointer(long j) {
        return new LogToStdout((Pointer) this).position(this.position + j);
    }
}
