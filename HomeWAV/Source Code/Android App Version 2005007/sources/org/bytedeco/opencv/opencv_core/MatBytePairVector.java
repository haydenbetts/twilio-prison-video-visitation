package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Index;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
@Name({"std::vector<std::pair<cv::Mat,uchar> >"})
public class MatBytePairVector extends Pointer {
    private native void allocate();

    private native void allocate(@Cast({"size_t"}) long j);

    @ByRef
    @Index(function = "at")
    public native Mat first(@Cast({"size_t"}) long j);

    public native MatBytePairVector first(@Cast({"size_t"}) long j, Mat mat);

    @ByRef
    @Name({"operator ="})
    public native MatBytePairVector put(@ByRef MatBytePairVector matBytePairVector);

    public native void resize(@Cast({"size_t"}) long j);

    @Index(function = "at")
    @Cast({"uchar"})
    public native byte second(@Cast({"size_t"}) long j);

    public native MatBytePairVector second(@Cast({"size_t"}) long j, byte b);

    public native long size();

    static {
        Loader.load();
    }

    public MatBytePairVector(Pointer pointer) {
        super(pointer);
    }

    public MatBytePairVector(Mat[] matArr, byte[] bArr) {
        this((long) Math.min(matArr.length, bArr.length));
        put(matArr, bArr);
    }

    public MatBytePairVector() {
        allocate();
    }

    public MatBytePairVector(long j) {
        allocate(j);
    }

    public boolean empty() {
        return size() == 0;
    }

    public void clear() {
        resize(0);
    }

    public MatBytePairVector put(Mat[] matArr, byte[] bArr) {
        int i = 0;
        while (i < matArr.length && i < bArr.length) {
            long j = (long) i;
            first(j, matArr[i]);
            second(j, bArr[i]);
            i++;
        }
        return this;
    }
}
