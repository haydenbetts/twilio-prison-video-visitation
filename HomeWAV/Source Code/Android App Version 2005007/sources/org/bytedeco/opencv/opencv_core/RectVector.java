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
@Name({"std::vector<cv::Rect>"})
public class RectVector extends Pointer {
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
    public native Rect get(@Cast({"size_t"}) long j);

    @ByVal
    public native Iterator insert(@ByVal Iterator iterator, @ByRef Rect rect);

    public native RectVector put(@Cast({"size_t"}) long j, Rect rect);

    @ByRef
    @Name({"operator ="})
    public native RectVector put(@ByRef RectVector rectVector);

    public native void resize(@Cast({"size_t"}) long j);

    public native long size();

    static {
        Loader.load();
    }

    public RectVector(Pointer pointer) {
        super(pointer);
    }

    public RectVector(Rect rect) {
        this(1);
        put(0, rect);
    }

    public RectVector(Rect... rectArr) {
        this((long) rectArr.length);
        put(rectArr);
    }

    public RectVector() {
        allocate();
    }

    public RectVector(long j) {
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
        public native Rect get();

        @ByRef
        @Name({"operator ++"})
        public native Iterator increment();

        public Iterator(Pointer pointer) {
            super(pointer);
        }

        public Iterator() {
        }
    }

    public Rect[] get() {
        int size = size() < 2147483647L ? (int) size() : Integer.MAX_VALUE;
        Rect[] rectArr = new Rect[size];
        for (int i = 0; i < size; i++) {
            rectArr[i] = get((long) i);
        }
        return rectArr;
    }

    public String toString() {
        return Arrays.toString(get());
    }

    public Rect pop_back() {
        long size = size() - 1;
        Rect rect = get(size);
        resize(size);
        return rect;
    }

    public RectVector push_back(Rect rect) {
        long size = size();
        resize(1 + size);
        return put(size, rect);
    }

    public RectVector put(Rect rect) {
        if (size() != 1) {
            resize(1);
        }
        return put(0, rect);
    }

    public RectVector put(Rect... rectArr) {
        if (size() != ((long) rectArr.length)) {
            resize((long) rectArr.length);
        }
        for (int i = 0; i < rectArr.length; i++) {
            put((long) i, rectArr[i]);
        }
        return this;
    }
}
