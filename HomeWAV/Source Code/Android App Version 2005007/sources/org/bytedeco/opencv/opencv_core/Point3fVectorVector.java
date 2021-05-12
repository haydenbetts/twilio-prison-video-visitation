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
@Name({"std::vector<std::vector<cv::Point3f> >"})
public class Point3fVectorVector extends Pointer {
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
    @Cast({"std::vector<cv::Point3f>*"})
    public native Point3fVector get(@Cast({"size_t"}) long j);

    @ByVal
    public native Iterator insert(@ByVal Iterator iterator, @ByRef @Cast({"std::vector<cv::Point3f>*"}) Point3fVector point3fVector);

    public native Point3fVectorVector put(@Cast({"size_t"}) long j, Point3fVector point3fVector);

    @ByRef
    @Name({"operator ="})
    public native Point3fVectorVector put(@ByRef Point3fVectorVector point3fVectorVector);

    public native void resize(@Cast({"size_t"}) long j);

    public native long size();

    static {
        Loader.load();
    }

    public Point3fVectorVector(Pointer pointer) {
        super(pointer);
    }

    public Point3fVectorVector(Point3fVector point3fVector) {
        this(1);
        put(0, point3fVector);
    }

    public Point3fVectorVector(Point3fVector... point3fVectorArr) {
        this((long) point3fVectorArr.length);
        put(point3fVectorArr);
    }

    public Point3fVectorVector() {
        allocate();
    }

    public Point3fVectorVector(long j) {
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
        @Cast({"std::vector<cv::Point3f>*"})
        @Const
        @Name({"operator *"})
        public native Point3fVector get();

        @ByRef
        @Name({"operator ++"})
        public native Iterator increment();

        public Iterator(Pointer pointer) {
            super(pointer);
        }

        public Iterator() {
        }
    }

    public Point3fVector[] get() {
        int size = size() < 2147483647L ? (int) size() : Integer.MAX_VALUE;
        Point3fVector[] point3fVectorArr = new Point3fVector[size];
        for (int i = 0; i < size; i++) {
            point3fVectorArr[i] = get((long) i);
        }
        return point3fVectorArr;
    }

    public String toString() {
        return Arrays.toString(get());
    }

    public Point3fVector pop_back() {
        long size = size() - 1;
        Point3fVector point3fVector = get(size);
        resize(size);
        return point3fVector;
    }

    public Point3fVectorVector push_back(Point3fVector point3fVector) {
        long size = size();
        resize(1 + size);
        return put(size, point3fVector);
    }

    public Point3fVectorVector put(Point3fVector point3fVector) {
        if (size() != 1) {
            resize(1);
        }
        return put(0, point3fVector);
    }

    public Point3fVectorVector put(Point3fVector... point3fVectorArr) {
        if (size() != ((long) point3fVectorArr.length)) {
            resize((long) point3fVectorArr.length);
        }
        for (int i = 0; i < point3fVectorArr.length; i++) {
            put((long) i, point3fVectorArr[i]);
        }
        return this;
    }
}
