package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.ValueGetter;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
@Name({"CvMatND*"})
public class CvMatNDArray extends CvArrArray {
    private native void allocateArray(long j);

    @ValueGetter
    public native CvMatND get();

    public CvMatNDArray(CvMatND... cvMatNDArr) {
        this((long) cvMatNDArr.length);
        put((CvArr[]) cvMatNDArr);
        position(0);
    }

    public CvMatNDArray(long j) {
        super(new CvArr[0]);
        allocateArray(j);
    }

    public CvMatNDArray(Pointer pointer) {
        super(pointer);
    }

    public CvMatNDArray position(long j) {
        return (CvMatNDArray) super.position(j);
    }

    public CvMatNDArray put(CvArr... cvArrArr) {
        return (CvMatNDArray) super.put(cvArrArr);
    }

    public CvMatNDArray put(CvArr cvArr) {
        if (cvArr instanceof CvMatND) {
            return (CvMatNDArray) super.put(cvArr);
        }
        throw new ArrayStoreException(cvArr.getClass().getName());
    }
}
