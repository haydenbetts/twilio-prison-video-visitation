package org.bytedeco.ffmpeg.avcodec;

import org.bytedeco.ffmpeg.avutil.AVClass;
import org.bytedeco.ffmpeg.avutil.AVRational;
import org.bytedeco.ffmpeg.presets.avcodec;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avcodec.class})
public class AVBSFContext extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native AVBSFContext av_class(AVClass aVClass);

    @Const
    public native AVClass av_class();

    public native AVBSFContext filter(AVBitStreamFilter aVBitStreamFilter);

    @Const
    public native AVBitStreamFilter filter();

    public native AVBSFContext internal(AVBSFInternal aVBSFInternal);

    public native AVBSFInternal internal();

    public native AVBSFContext par_in(AVCodecParameters aVCodecParameters);

    public native AVCodecParameters par_in();

    public native AVBSFContext par_out(AVCodecParameters aVCodecParameters);

    public native AVCodecParameters par_out();

    public native AVBSFContext priv_data(Pointer pointer);

    public native Pointer priv_data();

    public native AVBSFContext time_base_in(AVRational aVRational);

    @ByRef
    public native AVRational time_base_in();

    public native AVBSFContext time_base_out(AVRational aVRational);

    @ByRef
    public native AVRational time_base_out();

    static {
        Loader.load();
    }

    public AVBSFContext() {
        super((Pointer) null);
        allocate();
    }

    public AVBSFContext(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVBSFContext(Pointer pointer) {
        super(pointer);
    }

    public AVBSFContext position(long j) {
        return (AVBSFContext) super.position(j);
    }

    public AVBSFContext getPointer(long j) {
        return new AVBSFContext((Pointer) this).position(this.position + j);
    }
}
