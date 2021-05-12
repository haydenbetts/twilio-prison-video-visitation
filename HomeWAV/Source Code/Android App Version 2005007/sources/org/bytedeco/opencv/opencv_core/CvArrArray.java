package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
@Name({"CvArr*"})
public class CvArrArray extends PointerPointer<CvArr> {
    private native void allocateArray(long j);

    public native CvArr get();

    public native CvArrArray put(CvArr cvArr);

    static {
        Loader.load();
    }

    public CvArrArray(CvArr... cvArrArr) {
        this((long) cvArrArr.length);
        put(cvArrArr);
        position(0);
    }

    public CvArrArray(long j) {
        super(j);
        allocateArray(j);
    }

    public CvArrArray(Pointer pointer) {
        super(pointer);
    }

    public CvArrArray position(long j) {
        return (CvArrArray) super.position(j);
    }

    public CvArrArray put(CvArr... cvArrArr) {
        for (int i = 0; i < cvArrArr.length; i++) {
            position((long) i).put(cvArrArr[i]);
        }
        return this;
    }
}
