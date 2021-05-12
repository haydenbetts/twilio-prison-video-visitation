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
import org.bytedeco.opencv.presets.opencv_tracking;

@Properties(inherit = {opencv_tracking.class})
@Name({"std::vector<cv::ConfidenceMap>"})
public class ConfidenceMapVector extends Pointer {
    private native void allocate();

    private native void allocate(@Cast({"size_t"}) long j);

    @ByVal
    public native Iterator begin();

    @ByVal
    public native Iterator end();

    @ByVal
    public native Iterator erase(@ByVal Iterator iterator);

    @ByRef
    @Index(function = "at")
    public native ConfidenceMap get(@Cast({"size_t"}) long j);

    @ByVal
    public native Iterator insert(@ByVal Iterator iterator, @ByRef ConfidenceMap confidenceMap);

    public native ConfidenceMapVector put(@Cast({"size_t"}) long j, ConfidenceMap confidenceMap);

    @ByRef
    @Name({"operator ="})
    public native ConfidenceMapVector put(@ByRef ConfidenceMapVector confidenceMapVector);

    public native void resize(@Cast({"size_t"}) long j);

    public native long size();

    static {
        Loader.load();
    }

    public ConfidenceMapVector(Pointer pointer) {
        super(pointer);
    }

    public ConfidenceMapVector(ConfidenceMap confidenceMap) {
        this(1);
        put(0, confidenceMap);
    }

    public ConfidenceMapVector(ConfidenceMap... confidenceMapArr) {
        this((long) confidenceMapArr.length);
        put(confidenceMapArr);
    }

    public ConfidenceMapVector() {
        allocate();
    }

    public ConfidenceMapVector(long j) {
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

        @ByRef
        @Const
        @Name({"operator *"})
        public native ConfidenceMap get();

        @ByRef
        @Name({"operator ++"})
        public native Iterator increment();

        public Iterator(Pointer pointer) {
            super(pointer);
        }

        public Iterator() {
        }
    }

    public ConfidenceMap[] get() {
        int size = size() < 2147483647L ? (int) size() : Integer.MAX_VALUE;
        ConfidenceMap[] confidenceMapArr = new ConfidenceMap[size];
        for (int i = 0; i < size; i++) {
            confidenceMapArr[i] = get((long) i);
        }
        return confidenceMapArr;
    }

    public String toString() {
        return Arrays.toString(get());
    }

    public ConfidenceMap pop_back() {
        long size = size() - 1;
        ConfidenceMap confidenceMap = get(size);
        resize(size);
        return confidenceMap;
    }

    public ConfidenceMapVector push_back(ConfidenceMap confidenceMap) {
        long size = size();
        resize(1 + size);
        return put(size, confidenceMap);
    }

    public ConfidenceMapVector put(ConfidenceMap confidenceMap) {
        if (size() != 1) {
            resize(1);
        }
        return put(0, confidenceMap);
    }

    public ConfidenceMapVector put(ConfidenceMap... confidenceMapArr) {
        if (size() != ((long) confidenceMapArr.length)) {
            resize((long) confidenceMapArr.length);
        }
        for (int i = 0; i < confidenceMapArr.length; i++) {
            put((long) i, confidenceMapArr[i]);
        }
        return this;
    }
}
