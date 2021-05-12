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
@Name({"std::vector<std::vector<cv::Rect> >"})
public class RectVectorVector extends Pointer {
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
    public native RectVector get(@Cast({"size_t"}) long j);

    @ByVal
    public native Iterator insert(@ByVal Iterator iterator, @ByRef RectVector rectVector);

    public native RectVectorVector put(@Cast({"size_t"}) long j, RectVector rectVector);

    @ByRef
    @Name({"operator ="})
    public native RectVectorVector put(@ByRef RectVectorVector rectVectorVector);

    public native void resize(@Cast({"size_t"}) long j);

    public native long size();

    static {
        Loader.load();
    }

    public RectVectorVector(Pointer pointer) {
        super(pointer);
    }

    public RectVectorVector(RectVector rectVector) {
        this(1);
        put(0, rectVector);
    }

    public RectVectorVector(RectVector... rectVectorArr) {
        this((long) rectVectorArr.length);
        put(rectVectorArr);
    }

    public RectVectorVector() {
        allocate();
    }

    public RectVectorVector(long j) {
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
        public native RectVector get();

        @ByRef
        @Name({"operator ++"})
        public native Iterator increment();

        public Iterator(Pointer pointer) {
            super(pointer);
        }

        public Iterator() {
        }
    }

    public RectVector[] get() {
        int size = size() < 2147483647L ? (int) size() : Integer.MAX_VALUE;
        RectVector[] rectVectorArr = new RectVector[size];
        for (int i = 0; i < size; i++) {
            rectVectorArr[i] = get((long) i);
        }
        return rectVectorArr;
    }

    public String toString() {
        return Arrays.toString(get());
    }

    public RectVector pop_back() {
        long size = size() - 1;
        RectVector rectVector = get(size);
        resize(size);
        return rectVector;
    }

    public RectVectorVector push_back(RectVector rectVector) {
        long size = size();
        resize(1 + size);
        return put(size, rectVector);
    }

    public RectVectorVector put(RectVector rectVector) {
        if (size() != 1) {
            resize(1);
        }
        return put(0, rectVector);
    }

    public RectVectorVector put(RectVector... rectVectorArr) {
        if (size() != ((long) rectVectorArr.length)) {
            resize((long) rectVectorArr.length);
        }
        for (int i = 0; i < rectVectorArr.length; i++) {
            put((long) i, rectVectorArr[i]);
        }
        return this;
    }
}
