package org.bytedeco.opencv.opencv_cudacodec;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_cudacodec;

@Namespace("cv::cudacodec")
@Properties(inherit = {opencv_cudacodec.class})
public class EncoderCallBack extends Pointer {
    public static final int BFRAME = 3;
    public static final int IFRAME = 1;
    public static final int PFRAME = 2;

    @Cast({"uchar*"})
    public native ByteBuffer acquireBitStream(IntBuffer intBuffer);

    @Cast({"uchar*"})
    public native BytePointer acquireBitStream(IntPointer intPointer);

    @Cast({"uchar*"})
    public native byte[] acquireBitStream(int[] iArr);

    public native void onBeginFrame(int i, @Cast({"cv::cudacodec::EncoderCallBack::PicType"}) int i2);

    public native void onEndFrame(int i, @Cast({"cv::cudacodec::EncoderCallBack::PicType"}) int i2);

    public native void releaseBitStream(@Cast({"unsigned char*"}) ByteBuffer byteBuffer, int i);

    public native void releaseBitStream(@Cast({"unsigned char*"}) BytePointer bytePointer, int i);

    public native void releaseBitStream(@Cast({"unsigned char*"}) byte[] bArr, int i);

    static {
        Loader.load();
    }

    public EncoderCallBack(Pointer pointer) {
        super(pointer);
    }
}
