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
@Name({"std::vector<cv::Point3f>"})
public class Point3fVector extends Pointer {
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
    public native Point3f get(@Cast({"size_t"}) long j);

    @ByVal
    public native Iterator insert(@ByVal Iterator iterator, @ByRef Point3f point3f);

    public native Point3fVector put(@Cast({"size_t"}) long j, Point3f point3f);

    @ByRef
    @Name({"operator ="})
    public native Point3fVector put(@ByRef Point3fVector point3fVector);

    public native void resize(@Cast({"size_t"}) long j);

    public native long size();

    static {
        Loader.load();
    }

    public Point3fVector(Pointer pointer) {
        super(pointer);
    }

    public Point3fVector(Point3f point3f) {
        this(1);
        put(0, point3f);
    }

    public Point3fVector(Point3f... point3fArr) {
        this((long) point3fArr.length);
        put(point3fArr);
    }

    public Point3fVector() {
        allocate();
    }

    public Point3fVector(long j) {
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
        public native Point3f get();

        @ByRef
        @Name({"operator ++"})
        public native Iterator increment();

        public Iterator(Pointer pointer) {
            super(pointer);
        }

        public Iterator() {
        }
    }

    public Point3f[] get() {
        int size = size() < 2147483647L ? (int) size() : Integer.MAX_VALUE;
        Point3f[] point3fArr = new Point3f[size];
        for (int i = 0; i < size; i++) {
            point3fArr[i] = get((long) i);
        }
        return point3fArr;
    }

    public String toString() {
        return Arrays.toString(get());
    }

    public Point3f pop_back() {
        long size = size() - 1;
        Point3f point3f = get(size);
        resize(size);
        return point3f;
    }

    public Point3fVector push_back(Point3f point3f) {
        long size = size();
        resize(1 + size);
        return put(size, point3f);
    }

    public Point3fVector put(Point3f point3f) {
        if (size() != 1) {
            resize(1);
        }
        return put(0, point3f);
    }

    public Point3fVector put(Point3f... point3fArr) {
        if (size() != ((long) point3fArr.length)) {
            resize((long) point3fArr.length);
        }
        for (int i = 0; i < point3fArr.length; i++) {
            put((long) i, point3fArr[i]);
        }
        return this;
    }
}
