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
@Name({"std::vector<std::vector<cv::Point> >"})
public class PointVectorVector extends Pointer {
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
    public native PointVector get(@Cast({"size_t"}) long j);

    @ByVal
    public native Iterator insert(@ByVal Iterator iterator, @ByRef PointVector pointVector);

    public native PointVectorVector put(@Cast({"size_t"}) long j, PointVector pointVector);

    @ByRef
    @Name({"operator ="})
    public native PointVectorVector put(@ByRef PointVectorVector pointVectorVector);

    public native void resize(@Cast({"size_t"}) long j);

    public native long size();

    static {
        Loader.load();
    }

    public PointVectorVector(Pointer pointer) {
        super(pointer);
    }

    public PointVectorVector(PointVector pointVector) {
        this(1);
        put(0, pointVector);
    }

    public PointVectorVector(PointVector... pointVectorArr) {
        this((long) pointVectorArr.length);
        put(pointVectorArr);
    }

    public PointVectorVector() {
        allocate();
    }

    public PointVectorVector(long j) {
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
        public native PointVector get();

        @ByRef
        @Name({"operator ++"})
        public native Iterator increment();

        public Iterator(Pointer pointer) {
            super(pointer);
        }

        public Iterator() {
        }
    }

    public PointVector[] get() {
        int size = size() < 2147483647L ? (int) size() : Integer.MAX_VALUE;
        PointVector[] pointVectorArr = new PointVector[size];
        for (int i = 0; i < size; i++) {
            pointVectorArr[i] = get((long) i);
        }
        return pointVectorArr;
    }

    public String toString() {
        return Arrays.toString(get());
    }

    public PointVector pop_back() {
        long size = size() - 1;
        PointVector pointVector = get(size);
        resize(size);
        return pointVector;
    }

    public PointVectorVector push_back(PointVector pointVector) {
        long size = size();
        resize(1 + size);
        return put(size, pointVector);
    }

    public PointVectorVector put(PointVector pointVector) {
        if (size() != 1) {
            resize(1);
        }
        return put(0, pointVector);
    }

    public PointVectorVector put(PointVector... pointVectorArr) {
        if (size() != ((long) pointVectorArr.length)) {
            resize((long) pointVectorArr.length);
        }
        for (int i = 0; i < pointVectorArr.length; i++) {
            put((long) i, pointVectorArr[i]);
        }
        return this;
    }
}
