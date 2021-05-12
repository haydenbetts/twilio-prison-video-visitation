package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class AVMasteringDisplayMetadata extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native AVMasteringDisplayMetadata display_primaries(int i, int i2, AVRational aVRational);

    @MemberGetter
    @Cast({"AVRational(* /*[3]*/ )[2]"})
    public native AVRational display_primaries();

    @ByRef
    public native AVRational display_primaries(int i, int i2);

    public native int has_luminance();

    public native AVMasteringDisplayMetadata has_luminance(int i);

    public native int has_primaries();

    public native AVMasteringDisplayMetadata has_primaries(int i);

    public native AVMasteringDisplayMetadata max_luminance(AVRational aVRational);

    @ByRef
    public native AVRational max_luminance();

    public native AVMasteringDisplayMetadata min_luminance(AVRational aVRational);

    @ByRef
    public native AVRational min_luminance();

    public native AVMasteringDisplayMetadata white_point(int i, AVRational aVRational);

    @MemberGetter
    public native AVRational white_point();

    @ByRef
    public native AVRational white_point(int i);

    static {
        Loader.load();
    }

    public AVMasteringDisplayMetadata() {
        super((Pointer) null);
        allocate();
    }

    public AVMasteringDisplayMetadata(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVMasteringDisplayMetadata(Pointer pointer) {
        super(pointer);
    }

    public AVMasteringDisplayMetadata position(long j) {
        return (AVMasteringDisplayMetadata) super.position(j);
    }

    public AVMasteringDisplayMetadata getPointer(long j) {
        return new AVMasteringDisplayMetadata((Pointer) this).position(this.position + j);
    }
}
