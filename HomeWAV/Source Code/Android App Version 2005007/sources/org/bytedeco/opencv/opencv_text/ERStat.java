package org.bytedeco.opencv.opencv_text;

import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_text;

@Namespace("cv::text")
@Properties(inherit = {opencv_text.class})
@NoOffset
public class ERStat extends Pointer {
    private native void allocate();

    private native void allocate(int i, int i2, int i3, int i4);

    private native void allocateArray(long j);

    public native int area();

    public native ERStat area(int i);

    public native double central_moments(int i);

    @MemberGetter
    public native DoublePointer central_moments();

    public native ERStat central_moments(int i, double d);

    public native ERStat child();

    public native ERStat child(ERStat eRStat);

    public native float convex_hull_ratio();

    public native ERStat convex_hull_ratio(float f);

    public native ERStat crossings(IntDeque intDeque);

    @opencv_core.Ptr
    public native IntDeque crossings();

    public native int euler();

    public native ERStat euler(int i);

    public native float hole_area_ratio();

    public native ERStat hole_area_ratio(float f);

    public native int level();

    public native ERStat level(int i);

    public native ERStat local_maxima(boolean z);

    @Cast({"bool"})
    public native boolean local_maxima();

    public native ERStat max_probability_ancestor();

    public native ERStat max_probability_ancestor(ERStat eRStat);

    public native float med_crossings();

    public native ERStat med_crossings(float f);

    public native ERStat min_probability_ancestor();

    public native ERStat min_probability_ancestor(ERStat eRStat);

    public native ERStat next();

    public native ERStat next(ERStat eRStat);

    public native float num_inflexion_points();

    public native ERStat num_inflexion_points(float f);

    public native ERStat parent();

    public native ERStat parent(ERStat eRStat);

    public native int perimeter();

    public native ERStat perimeter(int i);

    public native int pixel();

    public native ERStat pixel(int i);

    public native ERStat pixels(IntVector intVector);

    public native IntVector pixels();

    public native ERStat prev();

    public native ERStat prev(ERStat eRStat);

    public native double probability();

    public native ERStat probability(double d);

    public native double raw_moments(int i);

    @MemberGetter
    public native DoublePointer raw_moments();

    public native ERStat raw_moments(int i, double d);

    @ByRef
    public native Rect rect();

    public native ERStat rect(Rect rect);

    static {
        Loader.load();
    }

    public ERStat(Pointer pointer) {
        super(pointer);
    }

    public ERStat(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public ERStat position(long j) {
        return (ERStat) super.position(j);
    }

    public ERStat getPointer(long j) {
        return new ERStat((Pointer) this).position(this.position + j);
    }

    public ERStat(int i, int i2, int i3, int i4) {
        super((Pointer) null);
        allocate(i, i2, i3, i4);
    }

    public ERStat() {
        super((Pointer) null);
        allocate();
    }
}
