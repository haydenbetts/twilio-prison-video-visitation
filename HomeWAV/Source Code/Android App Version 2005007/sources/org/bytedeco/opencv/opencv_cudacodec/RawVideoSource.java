package org.bytedeco.opencv.opencv_cudacodec;

import java.nio.ByteBuffer;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.SizeTPointer;
import org.bytedeco.javacpp.annotation.ByPtrPtr;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_cudacodec;

@Namespace("cv::cudacodec")
@Properties(inherit = {opencv_cudacodec.class})
public class RawVideoSource extends Pointer {
    @ByVal
    public native FormatInfo format();

    @Cast({"bool"})
    public native boolean getNextPacket(@ByPtrPtr @Cast({"unsigned char**"}) ByteBuffer byteBuffer, @Cast({"size_t*"}) SizeTPointer sizeTPointer);

    @Cast({"bool"})
    public native boolean getNextPacket(@ByPtrPtr @Cast({"unsigned char**"}) BytePointer bytePointer, @Cast({"size_t*"}) SizeTPointer sizeTPointer);

    @Cast({"bool"})
    public native boolean getNextPacket(@Cast({"unsigned char**"}) PointerPointer pointerPointer, @Cast({"size_t*"}) SizeTPointer sizeTPointer);

    @Cast({"bool"})
    public native boolean getNextPacket(@ByPtrPtr @Cast({"unsigned char**"}) byte[] bArr, @Cast({"size_t*"}) SizeTPointer sizeTPointer);

    static {
        Loader.load();
    }

    public RawVideoSource(Pointer pointer) {
        super(pointer);
    }
}
