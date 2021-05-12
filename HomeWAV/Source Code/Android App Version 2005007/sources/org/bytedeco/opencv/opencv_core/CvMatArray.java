package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.ValueGetter;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
@Name({"CvMat*"})
public class CvMatArray extends CvArrArray {
    private native void allocateArray(long j);

    @ValueGetter
    public native CvMat get();

    public CvMatArray(CvMat... cvMatArr) {
        this((long) cvMatArr.length);
        put((CvArr[]) cvMatArr);
        position(0);
    }

    public CvMatArray(long j) {
        super(new CvArr[0]);
        allocateArray(j);
    }

    public CvMatArray(Pointer pointer) {
        super(pointer);
    }

    public CvMatArray position(long j) {
        return (CvMatArray) super.position(j);
    }

    public CvMatArray put(CvArr... cvArrArr) {
        return (CvMatArray) super.put(cvArrArr);
    }

    public CvMatArray put(CvArr cvArr) {
        if (cvArr instanceof CvMat) {
            return (CvMatArray) super.put(cvArr);
        }
        throw new ArrayStoreException(cvArr.getClass().getName());
    }
}
