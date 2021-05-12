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
@Name({"std::vector<cv::Point3i>"})
public class Point3iVector extends Pointer {
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
    public native Point3i get(@Cast({"size_t"}) long j);

    @ByVal
    public native Iterator insert(@ByVal Iterator iterator, @ByRef Point3i point3i);

    public native Point3iVector put(@Cast({"size_t"}) long j, Point3i point3i);

    @ByRef
    @Name({"operator ="})
    public native Point3iVector put(@ByRef Point3iVector point3iVector);

    public native void resize(@Cast({"size_t"}) long j);

    public native long size();

    static {
        Loader.load();
    }

    public Point3iVector(Pointer pointer) {
        super(pointer);
    }

    public Point3iVector(Point3i point3i) {
        this(1);
        put(0, point3i);
    }

    public Point3iVector(Point3i... point3iArr) {
        this((long) point3iArr.length);
        put(point3iArr);
    }

    public Point3iVector() {
        allocate();
    }

    public Point3iVector(long j) {
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
        public native Point3i get();

        @ByRef
        @Name({"operator ++"})
        public native Iterator increment();

        public Iterator(Pointer pointer) {
            super(pointer);
        }

        public Iterator() {
        }
    }

    public Point3i[] get() {
        int size = size() < 2147483647L ? (int) size() : Integer.MAX_VALUE;
        Point3i[] point3iArr = new Point3i[size];
        for (int i = 0; i < size; i++) {
            point3iArr[i] = get((long) i);
        }
        return point3iArr;
    }

    public String toString() {
        return Arrays.toString(get());
    }

    public Point3i pop_back() {
        long size = size() - 1;
        Point3i point3i = get(size);
        resize(size);
        return point3i;
    }

    public Point3iVector push_back(Point3i point3i) {
        long size = size();
        resize(1 + size);
        return put(size, point3i);
    }

    public Point3iVector put(Point3i point3i) {
        if (size() != 1) {
            resize(1);
        }
        return put(0, point3i);
    }

    public Point3iVector put(Point3i... point3iArr) {
        if (size() != ((long) point3iArr.length)) {
            resize((long) point3iArr.length);
        }
        for (int i = 0; i < point3iArr.length; i++) {
            put((long) i, point3iArr[i]);
        }
        return this;
    }
}
