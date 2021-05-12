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
@Name({"std::vector<cv::Point>"})
public class PointVector extends Pointer {
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
    public native Point get(@Cast({"size_t"}) long j);

    @ByVal
    public native Iterator insert(@ByVal Iterator iterator, @ByRef Point point);

    public native PointVector put(@Cast({"size_t"}) long j, Point point);

    @ByRef
    @Name({"operator ="})
    public native PointVector put(@ByRef PointVector pointVector);

    public native void resize(@Cast({"size_t"}) long j);

    public native long size();

    static {
        Loader.load();
    }

    public PointVector(Pointer pointer) {
        super(pointer);
    }

    public PointVector(Point point) {
        this(1);
        put(0, point);
    }

    public PointVector(Point... pointArr) {
        this((long) pointArr.length);
        put(pointArr);
    }

    public PointVector() {
        allocate();
    }

    public PointVector(long j) {
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
        public native Point get();

        @ByRef
        @Name({"operator ++"})
        public native Iterator increment();

        public Iterator(Pointer pointer) {
            super(pointer);
        }

        public Iterator() {
        }
    }

    public Point[] get() {
        int size = size() < 2147483647L ? (int) size() : Integer.MAX_VALUE;
        Point[] pointArr = new Point[size];
        for (int i = 0; i < size; i++) {
            pointArr[i] = get((long) i);
        }
        return pointArr;
    }

    public String toString() {
        return Arrays.toString(get());
    }

    public Point pop_back() {
        long size = size() - 1;
        Point point = get(size);
        resize(size);
        return point;
    }

    public PointVector push_back(Point point) {
        long size = size();
        resize(1 + size);
        return put(size, point);
    }

    public PointVector put(Point point) {
        if (size() != 1) {
            resize(1);
        }
        return put(0, point);
    }

    public PointVector put(Point... pointArr) {
        if (size() != ((long) pointArr.length)) {
            resize((long) pointArr.length);
        }
        for (int i = 0; i < pointArr.length; i++) {
            put((long) i, pointArr[i]);
        }
        return this;
    }
}
