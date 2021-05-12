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
@Name({"std::vector<cv::Point2d>"})
public class Point2dVector extends Pointer {
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
    public native Point2d get(@Cast({"size_t"}) long j);

    @ByVal
    public native Iterator insert(@ByVal Iterator iterator, @ByRef Point2d point2d);

    public native Point2dVector put(@Cast({"size_t"}) long j, Point2d point2d);

    @ByRef
    @Name({"operator ="})
    public native Point2dVector put(@ByRef Point2dVector point2dVector);

    public native void resize(@Cast({"size_t"}) long j);

    public native long size();

    static {
        Loader.load();
    }

    public Point2dVector(Pointer pointer) {
        super(pointer);
    }

    public Point2dVector(Point2d point2d) {
        this(1);
        put(0, point2d);
    }

    public Point2dVector(Point2d... point2dArr) {
        this((long) point2dArr.length);
        put(point2dArr);
    }

    public Point2dVector() {
        allocate();
    }

    public Point2dVector(long j) {
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
        public native Point2d get();

        @ByRef
        @Name({"operator ++"})
        public native Iterator increment();

        public Iterator(Pointer pointer) {
            super(pointer);
        }

        public Iterator() {
        }
    }

    public Point2d[] get() {
        int size = size() < 2147483647L ? (int) size() : Integer.MAX_VALUE;
        Point2d[] point2dArr = new Point2d[size];
        for (int i = 0; i < size; i++) {
            point2dArr[i] = get((long) i);
        }
        return point2dArr;
    }

    public String toString() {
        return Arrays.toString(get());
    }

    public Point2d pop_back() {
        long size = size() - 1;
        Point2d point2d = get(size);
        resize(size);
        return point2d;
    }

    public Point2dVector push_back(Point2d point2d) {
        long size = size();
        resize(1 + size);
        return put(size, point2d);
    }

    public Point2dVector put(Point2d point2d) {
        if (size() != 1) {
            resize(1);
        }
        return put(0, point2d);
    }

    public Point2dVector put(Point2d... point2dArr) {
        if (size() != ((long) point2dArr.length)) {
            resize((long) point2dArr.length);
        }
        for (int i = 0; i < point2dArr.length; i++) {
            put((long) i, point2dArr[i]);
        }
        return this;
    }
}
