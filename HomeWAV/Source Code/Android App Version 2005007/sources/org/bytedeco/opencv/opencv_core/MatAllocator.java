package org.bytedeco.opencv.opencv_core;

import java.nio.IntBuffer;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.SizeTPointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv")
@Properties(inherit = {opencv_core.class})
public class MatAllocator extends Pointer {
    @Name({"allocate"})
    public native UMatData _allocate(int i, @Const IntBuffer intBuffer, int i2, Pointer pointer, @Cast({"size_t*"}) SizeTPointer sizeTPointer, @Cast({"cv::AccessFlag"}) int i3, @Cast({"cv::UMatUsageFlags"}) int i4);

    @Name({"allocate"})
    public native UMatData _allocate(int i, @Const IntPointer intPointer, int i2, Pointer pointer, @Cast({"size_t*"}) SizeTPointer sizeTPointer, @Cast({"cv::AccessFlag"}) int i3, @Cast({"cv::UMatUsageFlags"}) int i4);

    @Name({"allocate"})
    public native UMatData _allocate(int i, @Const int[] iArr, int i2, Pointer pointer, @Cast({"size_t*"}) SizeTPointer sizeTPointer, @Cast({"cv::AccessFlag"}) int i3, @Cast({"cv::UMatUsageFlags"}) int i4);

    @Cast({"bool"})
    @Name({"allocate"})
    public native boolean _allocate(UMatData uMatData, @Cast({"cv::AccessFlag"}) int i, @Cast({"cv::UMatUsageFlags"}) int i2);

    @Name({"deallocate"})
    public native void _deallocate(UMatData uMatData);

    public native void copy(UMatData uMatData, UMatData uMatData2, int i, @Cast({"const size_t*"}) SizeTPointer sizeTPointer, @Cast({"const size_t*"}) SizeTPointer sizeTPointer2, @Cast({"const size_t*"}) SizeTPointer sizeTPointer3, @Cast({"const size_t*"}) SizeTPointer sizeTPointer4, @Cast({"const size_t*"}) SizeTPointer sizeTPointer5, @Cast({"bool"}) boolean z);

    public native void download(UMatData uMatData, Pointer pointer, int i, @Cast({"const size_t*"}) SizeTPointer sizeTPointer, @Cast({"const size_t*"}) SizeTPointer sizeTPointer2, @Cast({"const size_t*"}) SizeTPointer sizeTPointer3, @Cast({"const size_t*"}) SizeTPointer sizeTPointer4);

    public native BufferPoolController getBufferPoolController();

    public native BufferPoolController getBufferPoolController(String str);

    public native BufferPoolController getBufferPoolController(@Cast({"const char*"}) BytePointer bytePointer);

    public native void map(UMatData uMatData, @Cast({"cv::AccessFlag"}) int i);

    public native void unmap(UMatData uMatData);

    public native void upload(UMatData uMatData, @Const Pointer pointer, int i, @Cast({"const size_t*"}) SizeTPointer sizeTPointer, @Cast({"const size_t*"}) SizeTPointer sizeTPointer2, @Cast({"const size_t*"}) SizeTPointer sizeTPointer3, @Cast({"const size_t*"}) SizeTPointer sizeTPointer4);

    static {
        Loader.load();
    }

    public MatAllocator(Pointer pointer) {
        super(pointer);
    }
}
