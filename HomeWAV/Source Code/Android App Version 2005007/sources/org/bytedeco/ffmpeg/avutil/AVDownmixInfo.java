package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class AVDownmixInfo extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native double center_mix_level();

    public native AVDownmixInfo center_mix_level(double d);

    public native double center_mix_level_ltrt();

    public native AVDownmixInfo center_mix_level_ltrt(double d);

    public native double lfe_mix_level();

    public native AVDownmixInfo lfe_mix_level(double d);

    @Cast({"AVDownmixType"})
    public native int preferred_downmix_type();

    public native AVDownmixInfo preferred_downmix_type(int i);

    public native double surround_mix_level();

    public native AVDownmixInfo surround_mix_level(double d);

    public native double surround_mix_level_ltrt();

    public native AVDownmixInfo surround_mix_level_ltrt(double d);

    static {
        Loader.load();
    }

    public AVDownmixInfo() {
        super((Pointer) null);
        allocate();
    }

    public AVDownmixInfo(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVDownmixInfo(Pointer pointer) {
        super(pointer);
    }

    public AVDownmixInfo position(long j) {
        return (AVDownmixInfo) super.position(j);
    }

    public AVDownmixInfo getPointer(long j) {
        return new AVDownmixInfo((Pointer) this).position(this.position + j);
    }
}
