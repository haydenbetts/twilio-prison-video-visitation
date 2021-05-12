package org.bytedeco.opencv.opencv_core;

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

@Properties(inherit = {opencv_core.class})
@Name({"std::vector<std::vector<cv::KeyPoint> >"})
public class KeyPointVectorVector extends Pointer {
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
    public native KeyPointVector get(@Cast({"size_t"}) long j);

    @ByVal
    public native Iterator insert(@ByVal Iterator iterator, @ByRef KeyPointVector keyPointVector);

    public native KeyPointVectorVector put(@Cast({"size_t"}) long j, KeyPointVector keyPointVector);

    @ByRef
    @Name({"operator ="})
    public native KeyPointVectorVector put(@ByRef KeyPointVectorVector keyPointVectorVector);

    public native void resize(@Cast({"size_t"}) long j);

    public native long size();

    static {
        Loader.load();
    }

    public KeyPointVectorVector(Pointer pointer) {
        super(pointer);
    }

    public KeyPointVectorVector(KeyPointVector keyPointVector) {
        this(1);
        put(0, keyPointVector);
    }

    public KeyPointVectorVector(KeyPointVector... keyPointVectorArr) {
        this((long) keyPointVectorArr.length);
        put(keyPointVectorArr);
    }

    public KeyPointVectorVector() {
        allocate();
    }

    public KeyPointVectorVector(long j) {
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
        public native KeyPointVector get();

        @ByRef
        @Name({"operator ++"})
        public native Iterator increment();

        public Iterator(Pointer pointer) {
            super(pointer);
        }

        public Iterator() {
        }
    }

    public KeyPointVector[] get() {
        int size = size() < 2147483647L ? (int) size() : Integer.MAX_VALUE;
        KeyPointVector[] keyPointVectorArr = new KeyPointVector[size];
        for (int i = 0; i < size; i++) {
            keyPointVectorArr[i] = get((long) i);
        }
        return keyPointVectorArr;
    }

    public String toString() {
        return Arrays.toString(get());
    }

    public KeyPointVector pop_back() {
        long size = size() - 1;
        KeyPointVector keyPointVector = get(size);
        resize(size);
        return keyPointVector;
    }

    public KeyPointVectorVector push_back(KeyPointVector keyPointVector) {
        long size = size();
        resize(1 + size);
        return put(size, keyPointVector);
    }

    public KeyPointVectorVector put(KeyPointVector keyPointVector) {
        if (size() != 1) {
            resize(1);
        }
        return put(0, keyPointVector);
    }

    public KeyPointVectorVector put(KeyPointVector... keyPointVectorArr) {
        if (size() != ((long) keyPointVectorArr.length)) {
            resize((long) keyPointVectorArr.length);
        }
        for (int i = 0; i < keyPointVectorArr.length; i++) {
            put((long) i, keyPointVectorArr[i]);
        }
        return this;
    }
}
