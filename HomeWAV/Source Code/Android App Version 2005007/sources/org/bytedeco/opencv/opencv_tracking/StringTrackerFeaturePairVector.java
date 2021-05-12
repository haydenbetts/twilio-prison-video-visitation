package org.bytedeco.opencv.opencv_tracking;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Index;
import org.bytedeco.javacpp.annotation.MemberSetter;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_tracking;

@Properties(inherit = {opencv_tracking.class})
@Name({"std::vector<std::pair<cv::String,cv::Ptr<cv::TrackerFeature> > >"})
public class StringTrackerFeaturePairVector extends Pointer {
    private native void allocate();

    private native void allocate(@Cast({"size_t"}) long j);

    @Index(function = "at")
    @opencv_core.Str
    public native BytePointer first(@Cast({"size_t"}) long j);

    @Index(function = "at")
    @MemberSetter
    public native StringTrackerFeaturePairVector first(@Cast({"size_t"}) long j, @opencv_core.Str String str);

    public native StringTrackerFeaturePairVector first(@Cast({"size_t"}) long j, BytePointer bytePointer);

    @ByRef
    @Name({"operator ="})
    public native StringTrackerFeaturePairVector put(@ByRef StringTrackerFeaturePairVector stringTrackerFeaturePairVector);

    public native void resize(@Cast({"size_t"}) long j);

    public native StringTrackerFeaturePairVector second(@Cast({"size_t"}) long j, TrackerFeature trackerFeature);

    @Index(function = "at")
    @opencv_core.Ptr
    public native TrackerFeature second(@Cast({"size_t"}) long j);

    public native long size();

    static {
        Loader.load();
    }

    public StringTrackerFeaturePairVector(Pointer pointer) {
        super(pointer);
    }

    public StringTrackerFeaturePairVector(BytePointer[] bytePointerArr, TrackerFeature[] trackerFeatureArr) {
        this((long) Math.min(bytePointerArr.length, trackerFeatureArr.length));
        put(bytePointerArr, trackerFeatureArr);
    }

    public StringTrackerFeaturePairVector(String[] strArr, TrackerFeature[] trackerFeatureArr) {
        this((long) Math.min(strArr.length, trackerFeatureArr.length));
        put(strArr, trackerFeatureArr);
    }

    public StringTrackerFeaturePairVector() {
        allocate();
    }

    public StringTrackerFeaturePairVector(long j) {
        allocate(j);
    }

    public boolean empty() {
        return size() == 0;
    }

    public void clear() {
        resize(0);
    }

    public StringTrackerFeaturePairVector put(BytePointer[] bytePointerArr, TrackerFeature[] trackerFeatureArr) {
        int i = 0;
        while (i < bytePointerArr.length && i < trackerFeatureArr.length) {
            long j = (long) i;
            first(j, bytePointerArr[i]);
            second(j, trackerFeatureArr[i]);
            i++;
        }
        return this;
    }

    public StringTrackerFeaturePairVector put(String[] strArr, TrackerFeature[] trackerFeatureArr) {
        int i = 0;
        while (i < strArr.length && i < trackerFeatureArr.length) {
            long j = (long) i;
            first(j, strArr[i]);
            second(j, trackerFeatureArr[i]);
            i++;
        }
        return this;
    }
}
