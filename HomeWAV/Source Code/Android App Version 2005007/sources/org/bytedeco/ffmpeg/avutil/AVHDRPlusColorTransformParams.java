package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class AVHDRPlusColorTransformParams extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native AVHDRPlusColorTransformParams average_maxrgb(AVRational aVRational);

    @ByRef
    public native AVRational average_maxrgb();

    public native AVHDRPlusColorTransformParams bezier_curve_anchors(int i, AVRational aVRational);

    @MemberGetter
    public native AVRational bezier_curve_anchors();

    @ByRef
    public native AVRational bezier_curve_anchors(int i);

    public native AVHDRPlusColorTransformParams center_of_ellipse_x(short s);

    @Cast({"uint16_t"})
    public native short center_of_ellipse_x();

    public native AVHDRPlusColorTransformParams center_of_ellipse_y(short s);

    @Cast({"uint16_t"})
    public native short center_of_ellipse_y();

    @Cast({"uint8_t"})
    public native byte color_saturation_mapping_flag();

    public native AVHDRPlusColorTransformParams color_saturation_mapping_flag(byte b);

    public native AVHDRPlusColorTransformParams color_saturation_weight(AVRational aVRational);

    @ByRef
    public native AVRational color_saturation_weight();

    public native AVHDRPlusColorTransformParams distribution_maxrgb(int i, AVHDRPlusPercentile aVHDRPlusPercentile);

    @MemberGetter
    public native AVHDRPlusPercentile distribution_maxrgb();

    @ByRef
    public native AVHDRPlusPercentile distribution_maxrgb(int i);

    public native AVHDRPlusColorTransformParams fraction_bright_pixels(AVRational aVRational);

    @ByRef
    public native AVRational fraction_bright_pixels();

    public native AVHDRPlusColorTransformParams knee_point_x(AVRational aVRational);

    @ByRef
    public native AVRational knee_point_x();

    public native AVHDRPlusColorTransformParams knee_point_y(AVRational aVRational);

    @ByRef
    public native AVRational knee_point_y();

    public native AVHDRPlusColorTransformParams maxscl(int i, AVRational aVRational);

    @MemberGetter
    public native AVRational maxscl();

    @ByRef
    public native AVRational maxscl(int i);

    @Cast({"uint8_t"})
    public native byte num_bezier_curve_anchors();

    public native AVHDRPlusColorTransformParams num_bezier_curve_anchors(byte b);

    @Cast({"uint8_t"})
    public native byte num_distribution_maxrgb_percentiles();

    public native AVHDRPlusColorTransformParams num_distribution_maxrgb_percentiles(byte b);

    @Cast({"AVHDRPlusOverlapProcessOption"})
    public native int overlap_process_option();

    public native AVHDRPlusColorTransformParams overlap_process_option(int i);

    @Cast({"uint8_t"})
    public native byte rotation_angle();

    public native AVHDRPlusColorTransformParams rotation_angle(byte b);

    public native AVHDRPlusColorTransformParams semimajor_axis_external_ellipse(short s);

    @Cast({"uint16_t"})
    public native short semimajor_axis_external_ellipse();

    public native AVHDRPlusColorTransformParams semimajor_axis_internal_ellipse(short s);

    @Cast({"uint16_t"})
    public native short semimajor_axis_internal_ellipse();

    public native AVHDRPlusColorTransformParams semiminor_axis_external_ellipse(short s);

    @Cast({"uint16_t"})
    public native short semiminor_axis_external_ellipse();

    @Cast({"uint8_t"})
    public native byte tone_mapping_flag();

    public native AVHDRPlusColorTransformParams tone_mapping_flag(byte b);

    public native AVHDRPlusColorTransformParams window_lower_right_corner_x(AVRational aVRational);

    @ByRef
    public native AVRational window_lower_right_corner_x();

    public native AVHDRPlusColorTransformParams window_lower_right_corner_y(AVRational aVRational);

    @ByRef
    public native AVRational window_lower_right_corner_y();

    public native AVHDRPlusColorTransformParams window_upper_left_corner_x(AVRational aVRational);

    @ByRef
    public native AVRational window_upper_left_corner_x();

    public native AVHDRPlusColorTransformParams window_upper_left_corner_y(AVRational aVRational);

    @ByRef
    public native AVRational window_upper_left_corner_y();

    static {
        Loader.load();
    }

    public AVHDRPlusColorTransformParams() {
        super((Pointer) null);
        allocate();
    }

    public AVHDRPlusColorTransformParams(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVHDRPlusColorTransformParams(Pointer pointer) {
        super(pointer);
    }

    public AVHDRPlusColorTransformParams position(long j) {
        return (AVHDRPlusColorTransformParams) super.position(j);
    }

    public AVHDRPlusColorTransformParams getPointer(long j) {
        return new AVHDRPlusColorTransformParams((Pointer) this).position(this.position + j);
    }
}
