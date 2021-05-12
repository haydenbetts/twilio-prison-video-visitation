package org.bytedeco.ffmpeg.avcodec;

import org.bytedeco.ffmpeg.presets.avcodec;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avcodec.class})
public class AVBitStreamFilterContext extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native AVBitStreamFilterContext args(BytePointer bytePointer);

    @Cast({"char*"})
    public native BytePointer args();

    @Const
    public native AVBitStreamFilter filter();

    public native AVBitStreamFilterContext filter(AVBitStreamFilter aVBitStreamFilter);

    public native AVBitStreamFilterContext next();

    public native AVBitStreamFilterContext next(AVBitStreamFilterContext aVBitStreamFilterContext);

    public native AVBitStreamFilterContext parser(AVCodecParserContext aVCodecParserContext);

    public native AVCodecParserContext parser();

    public native AVBitStreamFilterContext priv_data(Pointer pointer);

    public native Pointer priv_data();

    static {
        Loader.load();
    }

    public AVBitStreamFilterContext() {
        super((Pointer) null);
        allocate();
    }

    public AVBitStreamFilterContext(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVBitStreamFilterContext(Pointer pointer) {
        super(pointer);
    }

    public AVBitStreamFilterContext position(long j) {
        return (AVBitStreamFilterContext) super.position(j);
    }

    public AVBitStreamFilterContext getPointer(long j) {
        return new AVBitStreamFilterContext((Pointer) this).position(this.position + j);
    }
}
