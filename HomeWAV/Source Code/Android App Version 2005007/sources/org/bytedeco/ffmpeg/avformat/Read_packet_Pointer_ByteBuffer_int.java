package org.bytedeco.ffmpeg.avformat;

import java.nio.ByteBuffer;
import org.bytedeco.ffmpeg.presets.avformat;
import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avformat.class})
public class Read_packet_Pointer_ByteBuffer_int extends FunctionPointer {
    private native void allocate();

    public native int call(Pointer pointer, @Cast({"uint8_t*"}) ByteBuffer byteBuffer, int i);

    static {
        Loader.load();
    }

    public Read_packet_Pointer_ByteBuffer_int(Pointer pointer) {
        super(pointer);
    }

    protected Read_packet_Pointer_ByteBuffer_int() {
        allocate();
    }
}
