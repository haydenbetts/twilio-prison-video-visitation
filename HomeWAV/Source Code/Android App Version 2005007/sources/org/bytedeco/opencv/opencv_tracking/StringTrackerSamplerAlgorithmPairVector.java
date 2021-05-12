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
@Name({"std::vector<std::pair<cv::String,cv::Ptr<cv::TrackerSamplerAlgorithm> > >"})
public class StringTrackerSamplerAlgorithmPairVector extends Pointer {
    private native void allocate();

    private native void allocate(@Cast({"size_t"}) long j);

    @Index(function = "at")
    @opencv_core.Str
    public native BytePointer first(@Cast({"size_t"}) long j);

    @Index(function = "at")
    @MemberSetter
    public native StringTrackerSamplerAlgorithmPairVector first(@Cast({"size_t"}) long j, @opencv_core.Str String str);

    public native StringTrackerSamplerAlgorithmPairVector first(@Cast({"size_t"}) long j, BytePointer bytePointer);

    @ByRef
    @Name({"operator ="})
    public native StringTrackerSamplerAlgorithmPairVector put(@ByRef StringTrackerSamplerAlgorithmPairVector stringTrackerSamplerAlgorithmPairVector);

    public native void resize(@Cast({"size_t"}) long j);

    public native StringTrackerSamplerAlgorithmPairVector second(@Cast({"size_t"}) long j, TrackerSamplerAlgorithm trackerSamplerAlgorithm);

    @Index(function = "at")
    @opencv_core.Ptr
    public native TrackerSamplerAlgorithm second(@Cast({"size_t"}) long j);

    public native long size();

    static {
        Loader.load();
    }

    public StringTrackerSamplerAlgorithmPairVector(Pointer pointer) {
        super(pointer);
    }

    public StringTrackerSamplerAlgorithmPairVector(BytePointer[] bytePointerArr, TrackerSamplerAlgorithm[] trackerSamplerAlgorithmArr) {
        this((long) Math.min(bytePointerArr.length, trackerSamplerAlgorithmArr.length));
        put(bytePointerArr, trackerSamplerAlgorithmArr);
    }

    public StringTrackerSamplerAlgorithmPairVector(String[] strArr, TrackerSamplerAlgorithm[] trackerSamplerAlgorithmArr) {
        this((long) Math.min(strArr.length, trackerSamplerAlgorithmArr.length));
        put(strArr, trackerSamplerAlgorithmArr);
    }

    public StringTrackerSamplerAlgorithmPairVector() {
        allocate();
    }

    public StringTrackerSamplerAlgorithmPairVector(long j) {
        allocate(j);
    }

    public boolean empty() {
        return size() == 0;
    }

    public void clear() {
        resize(0);
    }

    public StringTrackerSamplerAlgorithmPairVector put(BytePointer[] bytePointerArr, TrackerSamplerAlgorithm[] trackerSamplerAlgorithmArr) {
        int i = 0;
        while (i < bytePointerArr.length && i < trackerSamplerAlgorithmArr.length) {
            long j = (long) i;
            first(j, bytePointerArr[i]);
            second(j, trackerSamplerAlgorithmArr[i]);
            i++;
        }
        return this;
    }

    public StringTrackerSamplerAlgorithmPairVector put(String[] strArr, TrackerSamplerAlgorithm[] trackerSamplerAlgorithmArr) {
        int i = 0;
        while (i < strArr.length && i < trackerSamplerAlgorithmArr.length) {
            long j = (long) i;
            first(j, strArr[i]);
            second(j, trackerSamplerAlgorithmArr[i]);
            i++;
        }
        return this;
    }
}
