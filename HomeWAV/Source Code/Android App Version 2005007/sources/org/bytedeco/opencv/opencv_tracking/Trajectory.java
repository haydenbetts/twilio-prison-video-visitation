package org.bytedeco.opencv.opencv_tracking;

import java.util.Arrays;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Index;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_tracking;

@Properties(inherit = {opencv_tracking.class})
@Name({"std::vector<cv::Ptr<cv::TrackerTargetState> >"})
public class Trajectory extends Pointer {
    private native void allocate();

    private native void allocate(@Cast({"size_t"}) long j);

    @ByVal
    public native Iterator begin();

    @ByVal
    public native Iterator end();

    @ByVal
    public native Iterator erase(@ByVal Iterator iterator);

    @Index(function = "at")
    @opencv_core.Ptr
    public native TrackerTargetState get(@Cast({"size_t"}) long j);

    @ByVal
    public native Iterator insert(@ByVal Iterator iterator, @opencv_core.Ptr TrackerTargetState trackerTargetState);

    public native Trajectory put(@Cast({"size_t"}) long j, TrackerTargetState trackerTargetState);

    @ByRef
    @Name({"operator ="})
    public native Trajectory put(@ByRef Trajectory trajectory);

    public native void resize(@Cast({"size_t"}) long j);

    public native long size();

    static {
        Loader.load();
    }

    public Trajectory(Pointer pointer) {
        super(pointer);
    }

    public Trajectory(TrackerTargetState trackerTargetState) {
        this(1);
        put(0, trackerTargetState);
    }

    public Trajectory(TrackerTargetState... trackerTargetStateArr) {
        this((long) trackerTargetStateArr.length);
        put(trackerTargetStateArr);
    }

    public Trajectory() {
        allocate();
    }

    public Trajectory(long j) {
        allocate(j);
    }

    public boolean empty() {
        return size() == 0;
    }

    public void clear() {
        resize(0);
    }

    @Name({"iterator"})
    @NoOffset
    public static class Iterator extends Pointer {
        @Name({"operator =="})
        public native boolean equals(@ByRef Iterator iterator);

        @Const
        @opencv_core.Ptr
        @Name({"operator *"})
        public native TrackerTargetState get();

        @ByRef
        @Name({"operator ++"})
        public native Iterator increment();

        public Iterator(Pointer pointer) {
            super(pointer);
        }

        public Iterator() {
        }
    }

    public TrackerTargetState[] get() {
        int size = size() < 2147483647L ? (int) size() : Integer.MAX_VALUE;
        TrackerTargetState[] trackerTargetStateArr = new TrackerTargetState[size];
        for (int i = 0; i < size; i++) {
            trackerTargetStateArr[i] = get((long) i);
        }
        return trackerTargetStateArr;
    }

    public String toString() {
        return Arrays.toString(get());
    }

    public TrackerTargetState pop_back() {
        long size = size() - 1;
        TrackerTargetState trackerTargetState = get(size);
        resize(size);
        return trackerTargetState;
    }

    public Trajectory push_back(TrackerTargetState trackerTargetState) {
        long size = size();
        resize(1 + size);
        return put(size, trackerTargetState);
    }

    public Trajectory put(TrackerTargetState trackerTargetState) {
        if (size() != 1) {
            resize(1);
        }
        return put(0, trackerTargetState);
    }

    public Trajectory put(TrackerTargetState... trackerTargetStateArr) {
        if (size() != ((long) trackerTargetStateArr.length)) {
            resize((long) trackerTargetStateArr.length);
        }
        for (int i = 0; i < trackerTargetStateArr.length; i++) {
            put((long) i, trackerTargetStateArr[i]);
        }
        return this;
    }
}
