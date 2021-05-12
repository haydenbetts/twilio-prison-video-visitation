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
@Name({"std::vector<std::vector<cv::Point2d> >"})
public class Point2dVectorVector extends Pointer {
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
    public native Point2dVector get(@Cast({"size_t"}) long j);

    @ByVal
    public native Iterator insert(@ByVal Iterator iterator, @ByRef Point2dVector point2dVector);

    public native Point2dVectorVector put(@Cast({"size_t"}) long j, Point2dVector point2dVector);

    @ByRef
    @Name({"operator ="})
    public native Point2dVectorVector put(@ByRef Point2dVectorVector point2dVectorVector);

    public native void resize(@Cast({"size_t"}) long j);

    public native long size();

    static {
        Loader.load();
    }

    public Point2dVectorVector(Pointer pointer) {
        super(pointer);
    }

    public Point2dVectorVector(Point2dVector point2dVector) {
        this(1);
        put(0, point2dVector);
    }

    public Point2dVectorVector(Point2dVector... point2dVectorArr) {
        this((long) point2dVectorArr.length);
        put(point2dVectorArr);
    }

    public Point2dVectorVector() {
        allocate();
    }

    public Point2dVectorVector(long j) {
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
        public native Point2dVector get();

        @ByRef
        @Name({"operator ++"})
        public native Iterator increment();

        public Iterator(Pointer pointer) {
            super(pointer);
        }

        public Iterator() {
        }
    }

    public Point2dVector[] get() {
        int size = size() < 2147483647L ? (int) size() : Integer.MAX_VALUE;
        Point2dVector[] point2dVectorArr = new Point2dVector[size];
        for (int i = 0; i < size; i++) {
            point2dVectorArr[i] = get((long) i);
        }
        return point2dVectorArr;
    }

    public String toString() {
        return Arrays.toString(get());
    }

    public Point2dVector pop_back() {
        long size = size() - 1;
        Point2dVector point2dVector = get(size);
        resize(size);
        return point2dVector;
    }

    public Point2dVectorVector push_back(Point2dVector point2dVector) {
        long size = size();
        resize(1 + size);
        return put(size, point2dVector);
    }

    public Point2dVectorVector put(Point2dVector point2dVector) {
        if (size() != 1) {
            resize(1);
        }
        return put(0, point2dVector);
    }

    public Point2dVectorVector put(Point2dVector... point2dVectorArr) {
        if (size() != ((long) point2dVectorArr.length)) {
            resize((long) point2dVectorArr.length);
        }
        for (int i = 0; i < point2dVectorArr.length; i++) {
            put((long) i, point2dVectorArr[i]);
        }
        return this;
    }
}
