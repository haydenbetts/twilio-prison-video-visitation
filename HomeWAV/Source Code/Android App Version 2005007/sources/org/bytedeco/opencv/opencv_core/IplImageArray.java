package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.ValueGetter;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
@Name({"IplImage*"})
public class IplImageArray extends CvArrArray {
    private native void allocateArray(long j);

    @ValueGetter
    public native IplImage get();

    public IplImageArray(IplImage... iplImageArr) {
        this((long) iplImageArr.length);
        put((CvArr[]) iplImageArr);
        position(0);
    }

    public IplImageArray(long j) {
        super(new CvArr[0]);
        allocateArray(j);
    }

    public IplImageArray(Pointer pointer) {
        super(pointer);
    }

    public IplImageArray position(long j) {
        return (IplImageArray) super.position(j);
    }

    public IplImageArray put(CvArr... cvArrArr) {
        return (IplImageArray) super.put(cvArrArr);
    }

    public IplImageArray put(CvArr cvArr) {
        if (cvArr instanceof IplImage) {
            return (IplImageArray) super.put(cvArr);
        }
        throw new ArrayStoreException(cvArr.getClass().getName());
    }
}
