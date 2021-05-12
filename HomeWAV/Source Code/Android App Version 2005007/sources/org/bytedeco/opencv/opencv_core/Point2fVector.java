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
@Name({"std::vector<cv::Point2f>"})
public class Point2fVector extends Pointer {
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
    public native Point2f get(@Cast({"size_t"}) long j);

    @ByVal
    public native Iterator insert(@ByVal Iterator iterator, @ByRef Point2f point2f);

    public native Point2fVector put(@Cast({"size_t"}) long j, Point2f point2f);

    @ByRef
    @Name({"operator ="})
    public native Point2fVector put(@ByRef Point2fVector point2fVector);

    public native void resize(@Cast({"size_t"}) long j);

    public native long size();

    static {
        Loader.load();
    }

    public Point2fVector(Pointer pointer) {
        super(pointer);
    }

    public Point2fVector(Point2f point2f) {
        this(1);
        put(0, point2f);
    }

    public Point2fVector(Point2f... point2fArr) {
        this((long) point2fArr.length);
        put(point2fArr);
    }

    public Point2fVector() {
        allocate();
    }

    public Point2fVector(long j) {
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
        public native Point2f get();

        @ByRef
        @Name({"operator ++"})
        public native Iterator increment();

        public Iterator(Pointer pointer) {
            super(pointer);
        }

        public Iterator() {
        }
    }

    public Point2f[] get() {
        int size = size() < 2147483647L ? (int) size() : Integer.MAX_VALUE;
        Point2f[] point2fArr = new Point2f[size];
        for (int i = 0; i < size; i++) {
            point2fArr[i] = get((long) i);
        }
        return point2fArr;
    }

    public String toString() {
        return Arrays.toString(get());
    }

    public Point2f pop_back() {
        long size = size() - 1;
        Point2f point2f = get(size);
        resize(size);
        return point2f;
    }

    public Point2fVector push_back(Point2f point2f) {
        long size = size();
        resize(1 + size);
        return put(size, point2f);
    }

    public Point2fVector put(Point2f point2f) {
        if (size() != 1) {
            resize(1);
        }
        return put(0, point2f);
    }

    public Point2fVector put(Point2f... point2fArr) {
        if (size() != ((long) point2fArr.length)) {
            resize((long) point2fArr.length);
        }
        for (int i = 0; i < point2fArr.length; i++) {
            put((long) i, point2fArr[i]);
        }
        return this;
    }
}
