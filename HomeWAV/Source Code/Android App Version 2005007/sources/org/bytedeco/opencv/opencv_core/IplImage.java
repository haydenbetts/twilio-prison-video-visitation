package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
public class IplImage extends AbstractIplImage {
    private native void allocate();

    private native void allocateArray(long j);

    public native int BorderConst(int i);

    @MemberGetter
    public native IntPointer BorderConst();

    public native IplImage BorderConst(int i, int i2);

    public native int BorderMode(int i);

    @MemberGetter
    public native IntPointer BorderMode();

    public native IplImage BorderMode(int i, int i2);

    public native int ID();

    public native IplImage ID(int i);

    public native int align();

    public native IplImage align(int i);

    public native int alphaChannel();

    public native IplImage alphaChannel(int i);

    @Cast({"char"})
    public native byte channelSeq(int i);

    @MemberGetter
    @Cast({"char*"})
    public native BytePointer channelSeq();

    public native IplImage channelSeq(int i, byte b);

    @Cast({"char"})
    public native byte colorModel(int i);

    @MemberGetter
    @Cast({"char*"})
    public native BytePointer colorModel();

    public native IplImage colorModel(int i, byte b);

    public native int dataOrder();

    public native IplImage dataOrder(int i);

    public native int depth();

    public native IplImage depth(int i);

    public native int height();

    public native IplImage height(int i);

    @Cast({"char*"})
    public native BytePointer imageData();

    public native IplImage imageData(BytePointer bytePointer);

    @Cast({"char*"})
    public native BytePointer imageDataOrigin();

    public native IplImage imageDataOrigin(BytePointer bytePointer);

    public native Pointer imageId();

    public native IplImage imageId(Pointer pointer);

    public native int imageSize();

    public native IplImage imageSize(int i);

    public native IplImage maskROI();

    public native IplImage maskROI(IplImage iplImage);

    public native int nChannels();

    public native IplImage nChannels(int i);

    public native int nSize();

    public native IplImage nSize(int i);

    public native int origin();

    public native IplImage origin(int i);

    public native IplImage roi(IplROI iplROI);

    public native IplROI roi();

    public native IplImage tileInfo(IplTileInfo iplTileInfo);

    public native IplTileInfo tileInfo();

    public native int width();

    public native IplImage width(int i);

    public native int widthStep();

    public native IplImage widthStep(int i);

    public /* bridge */ /* synthetic */ Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    static {
        Loader.load();
    }

    public IplImage() {
        super((Pointer) null);
        allocate();
    }

    public IplImage(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public IplImage(Pointer pointer) {
        super(pointer);
    }

    public IplImage position(long j) {
        return (IplImage) super.position(j);
    }

    public IplImage getPointer(long j) {
        return new IplImage((Pointer) this).position(this.position + j);
    }
}
