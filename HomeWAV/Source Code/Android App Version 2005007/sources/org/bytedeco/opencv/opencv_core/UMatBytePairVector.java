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
@Name({"std::vector<std::pair<cv::UMat,uchar> >"})
public class UMatBytePairVector extends Pointer {
    private native void allocate();

    private native void allocate(@Cast({"size_t"}) long j);

    @ByRef
    @Index(function = "at")
    public native UMat first(@Cast({"size_t"}) long j);

    public native UMatBytePairVector first(@Cast({"size_t"}) long j, UMat uMat);

    @ByRef
    @Name({"operator ="})
    public native UMatBytePairVector put(@ByRef UMatBytePairVector uMatBytePairVector);

    public native void resize(@Cast({"size_t"}) long j);

    @Index(function = "at")
    @Cast({"uchar"})
    public native byte second(@Cast({"size_t"}) long j);

    public native UMatBytePairVector second(@Cast({"size_t"}) long j, byte b);

    public native long size();

    static {
        Loader.load();
    }

    public UMatBytePairVector(Pointer pointer) {
        super(pointer);
    }

    public UMatBytePairVector(UMat[] uMatArr, byte[] bArr) {
        this((long) Math.min(uMatArr.length, bArr.length));
        put(uMatArr, bArr);
    }

    public UMatBytePairVector() {
        allocate();
    }

    public UMatBytePairVector(long j) {
        allocate(j);
    }

    public boolean empty() {
        return size() == 0;
    }

    public void clear() {
        resize(0);
    }

    public UMatBytePairVector put(UMat[] uMatArr, byte[] bArr) {
        int i = 0;
        while (i < uMatArr.length && i < bArr.length) {
            long j = (long) i;
            first(j, uMatArr[i]);
            second(j, bArr[i]);
            i++;
        }
        return this;
    }
}
