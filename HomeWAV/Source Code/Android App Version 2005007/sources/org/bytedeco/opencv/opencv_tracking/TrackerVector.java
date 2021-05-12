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
@Name({"std::vector<cv::Ptr<cv::Tracker> >"})
public class TrackerVector extends Pointer {
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
    public native Tracker get(@Cast({"size_t"}) long j);

    @ByVal
    public native Iterator insert(@ByVal Iterator iterator, @opencv_core.Ptr Tracker tracker);

    public native TrackerVector put(@Cast({"size_t"}) long j, Tracker tracker);

    @ByRef
    @Name({"operator ="})
    public native TrackerVector put(@ByRef TrackerVector trackerVector);

    public native void resize(@Cast({"size_t"}) long j);

    public native long size();

    static {
        Loader.load();
    }

    public TrackerVector(Pointer pointer) {
        super(pointer);
    }

    public TrackerVector(Tracker tracker) {
        this(1);
        put(0, tracker);
    }

    public TrackerVector(Tracker... trackerArr) {
        this((long) trackerArr.length);
        put(trackerArr);
    }

    public TrackerVector() {
        allocate();
    }

    public TrackerVector(long j) {
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
        public native Tracker get();

        @ByRef
        @Name({"operator ++"})
        public native Iterator increment();

        public Iterator(Pointer pointer) {
            super(pointer);
        }

        public Iterator() {
        }
    }

    public Tracker[] get() {
        int size = size() < 2147483647L ? (int) size() : Integer.MAX_VALUE;
        Tracker[] trackerArr = new Tracker[size];
        for (int i = 0; i < size; i++) {
            trackerArr[i] = get((long) i);
        }
        return trackerArr;
    }

    public String toString() {
        return Arrays.toString(get());
    }

    public Tracker pop_back() {
        long size = size() - 1;
        Tracker tracker = get(size);
        resize(size);
        return tracker;
    }

    public TrackerVector push_back(Tracker tracker) {
        long size = size();
        resize(1 + size);
        return put(size, tracker);
    }

    public TrackerVector put(Tracker tracker) {
        if (size() != 1) {
            resize(1);
        }
        return put(0, tracker);
    }

    public TrackerVector put(Tracker... trackerArr) {
        if (size() != ((long) trackerArr.length)) {
            resize((long) trackerArr.length);
        }
        for (int i = 0; i < trackerArr.length; i++) {
            put((long) i, trackerArr[i]);
        }
        return this;
    }
}
