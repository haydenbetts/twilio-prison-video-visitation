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
@Name({"std::vector<cv::KeyPoint>"})
public class KeyPointVector extends Pointer {
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
    public native KeyPoint get(@Cast({"size_t"}) long j);

    @ByVal
    public native Iterator insert(@ByVal Iterator iterator, @ByRef KeyPoint keyPoint);

    public native KeyPointVector put(@Cast({"size_t"}) long j, KeyPoint keyPoint);

    @ByRef
    @Name({"operator ="})
    public native KeyPointVector put(@ByRef KeyPointVector keyPointVector);

    public native void resize(@Cast({"size_t"}) long j);

    public native long size();

    static {
        Loader.load();
    }

    public KeyPointVector(Pointer pointer) {
        super(pointer);
    }

    public KeyPointVector(KeyPoint keyPoint) {
        this(1);
        put(0, keyPoint);
    }

    public KeyPointVector(KeyPoint... keyPointArr) {
        this((long) keyPointArr.length);
        put(keyPointArr);
    }

    public KeyPointVector() {
        allocate();
    }

    public KeyPointVector(long j) {
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
        public native KeyPoint get();

        @ByRef
        @Name({"operator ++"})
        public native Iterator increment();

        public Iterator(Pointer pointer) {
            super(pointer);
        }

        public Iterator() {
        }
    }

    public KeyPoint[] get() {
        int size = size() < 2147483647L ? (int) size() : Integer.MAX_VALUE;
        KeyPoint[] keyPointArr = new KeyPoint[size];
        for (int i = 0; i < size; i++) {
            keyPointArr[i] = get((long) i);
        }
        return keyPointArr;
    }

    public String toString() {
        return Arrays.toString(get());
    }

    public KeyPoint pop_back() {
        long size = size() - 1;
        KeyPoint keyPoint = get(size);
        resize(size);
        return keyPoint;
    }

    public KeyPointVector push_back(KeyPoint keyPoint) {
        long size = size();
        resize(1 + size);
        return put(size, keyPoint);
    }

    public KeyPointVector put(KeyPoint keyPoint) {
        if (size() != 1) {
            resize(1);
        }
        return put(0, keyPoint);
    }

    public KeyPointVector put(KeyPoint... keyPointArr) {
        if (size() != ((long) keyPointArr.length)) {
            resize((long) keyPointArr.length);
        }
        for (int i = 0; i < keyPointArr.length; i++) {
            put((long) i, keyPointArr[i]);
        }
        return this;
    }
}
