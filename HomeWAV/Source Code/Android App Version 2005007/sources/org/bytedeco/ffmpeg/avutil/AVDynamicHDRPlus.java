package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class AVDynamicHDRPlus extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    @Cast({"uint8_t"})
    public native byte application_version();

    public native AVDynamicHDRPlus application_version(byte b);

    @Cast({"uint8_t"})
    public native byte itu_t_t35_country_code();

    public native AVDynamicHDRPlus itu_t_t35_country_code(byte b);

    public native AVDynamicHDRPlus mastering_display_actual_peak_luminance(int i, int i2, AVRational aVRational);

    @MemberGetter
    @Cast({"AVRational(* /*[25]*/ )[25]"})
    public native AVRational mastering_display_actual_peak_luminance();

    @ByRef
    public native AVRational mastering_display_actual_peak_luminance(int i, int i2);

    @Cast({"uint8_t"})
    public native byte mastering_display_actual_peak_luminance_flag();

    public native AVDynamicHDRPlus mastering_display_actual_peak_luminance_flag(byte b);

    @Cast({"uint8_t"})
    public native byte num_cols_mastering_display_actual_peak_luminance();

    public native AVDynamicHDRPlus num_cols_mastering_display_actual_peak_luminance(byte b);

    @Cast({"uint8_t"})
    public native byte num_cols_targeted_system_display_actual_peak_luminance();

    public native AVDynamicHDRPlus num_cols_targeted_system_display_actual_peak_luminance(byte b);

    @Cast({"uint8_t"})
    public native byte num_rows_mastering_display_actual_peak_luminance();

    public native AVDynamicHDRPlus num_rows_mastering_display_actual_peak_luminance(byte b);

    @Cast({"uint8_t"})
    public native byte num_rows_targeted_system_display_actual_peak_luminance();

    public native AVDynamicHDRPlus num_rows_targeted_system_display_actual_peak_luminance(byte b);

    @Cast({"uint8_t"})
    public native byte num_windows();

    public native AVDynamicHDRPlus num_windows(byte b);

    public native AVDynamicHDRPlus params(int i, AVHDRPlusColorTransformParams aVHDRPlusColorTransformParams);

    @MemberGetter
    public native AVHDRPlusColorTransformParams params();

    @ByRef
    public native AVHDRPlusColorTransformParams params(int i);

    public native AVDynamicHDRPlus targeted_system_display_actual_peak_luminance(int i, int i2, AVRational aVRational);

    @MemberGetter
    @Cast({"AVRational(* /*[25]*/ )[25]"})
    public native AVRational targeted_system_display_actual_peak_luminance();

    @ByRef
    public native AVRational targeted_system_display_actual_peak_luminance(int i, int i2);

    @Cast({"uint8_t"})
    public native byte targeted_system_display_actual_peak_luminance_flag();

    public native AVDynamicHDRPlus targeted_system_display_actual_peak_luminance_flag(byte b);

    public native AVDynamicHDRPlus targeted_system_display_maximum_luminance(AVRational aVRational);

    @ByRef
    public native AVRational targeted_system_display_maximum_luminance();

    static {
        Loader.load();
    }

    public AVDynamicHDRPlus() {
        super((Pointer) null);
        allocate();
    }

    public AVDynamicHDRPlus(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVDynamicHDRPlus(Pointer pointer) {
        super(pointer);
    }

    public AVDynamicHDRPlus position(long j) {
        return (AVDynamicHDRPlus) super.position(j);
    }

    public AVDynamicHDRPlus getPointer(long j) {
        return new AVDynamicHDRPlus((Pointer) this).position(this.position + j);
    }
}
