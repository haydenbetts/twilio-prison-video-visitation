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
@Name({"std::vector<std::vector<cv::Point2f> >"})
public class Point2fVectorVector extends Pointer {
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
    public native Point2fVector get(@Cast({"size_t"}) long j);

    @ByVal
    public native Iterator insert(@ByVal Iterator iterator, @ByRef Point2fVector point2fVector);

    public native Point2fVectorVector put(@Cast({"size_t"}) long j, Point2fVector point2fVector);

    @ByRef
    @Name({"operator ="})
    public native Point2fVectorVector put(@ByRef Point2fVectorVector point2fVectorVector);

    public native void resize(@Cast({"size_t"}) long j);

    public native long size();

    static {
        Loader.load();
    }

    public Point2fVectorVector(Pointer pointer) {
        super(pointer);
    }

    public Point2fVectorVector(Point2fVector point2fVector) {
        this(1);
        put(0, point2fVector);
    }

    public Point2fVectorVector(Point2fVector... point2fVectorArr) {
        this((long) point2fVectorArr.length);
        put(point2fVectorArr);
    }

    public Point2fVectorVector() {
        allocate();
    }

    public Point2fVectorVector(long j) {
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
        public native Point2fVector get();

        @ByRef
        @Name({"operator ++"})
        public native Iterator increment();

        public Iterator(Pointer pointer) {
            super(pointer);
        }

        public Iterator() {
        }
    }

    public Point2fVector[] get() {
        int size = size() < 2147483647L ? (int) size() : Integer.MAX_VALUE;
        Point2fVector[] point2fVectorArr = new Point2fVector[size];
        for (int i = 0; i < size; i++) {
            point2fVectorArr[i] = get((long) i);
        }
        return point2fVectorArr;
    }

    public String toString() {
        return Arrays.toString(get());
    }

    public Point2fVector pop_back() {
        long size = size() - 1;
        Point2fVector point2fVector = get(size);
        resize(size);
        return point2fVector;
    }

    public Point2fVectorVector push_back(Point2fVector point2fVector) {
        long size = size();
        resize(1 + size);
        return put(size, point2fVector);
    }

    public Point2fVectorVector put(Point2fVector point2fVector) {
        if (size() != 1) {
            resize(1);
        }
        return put(0, point2fVector);
    }

    public Point2fVectorVector put(Point2fVector... point2fVectorArr) {
        if (size() != ((long) point2fVectorArr.length)) {
            resize((long) point2fVectorArr.length);
        }
        for (int i = 0; i < point2fVectorArr.length; i++) {
            put((long) i, point2fVectorArr[i]);
        }
        return this;
    }
}
