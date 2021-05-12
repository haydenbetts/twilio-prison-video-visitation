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
@Name({"std::vector<cv::Rect2d>"})
public class Rect2dVector extends Pointer {
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
    public native Rect2d get(@Cast({"size_t"}) long j);

    @ByVal
    public native Iterator insert(@ByVal Iterator iterator, @ByRef Rect2d rect2d);

    public native Rect2dVector put(@Cast({"size_t"}) long j, Rect2d rect2d);

    @ByRef
    @Name({"operator ="})
    public native Rect2dVector put(@ByRef Rect2dVector rect2dVector);

    public native void resize(@Cast({"size_t"}) long j);

    public native long size();

    static {
        Loader.load();
    }

    public Rect2dVector(Pointer pointer) {
        super(pointer);
    }

    public Rect2dVector(Rect2d rect2d) {
        this(1);
        put(0, rect2d);
    }

    public Rect2dVector(Rect2d... rect2dArr) {
        this((long) rect2dArr.length);
        put(rect2dArr);
    }

    public Rect2dVector() {
        allocate();
    }

    public Rect2dVector(long j) {
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
        public native Rect2d get();

        @ByRef
        @Name({"operator ++"})
        public native Iterator increment();

        public Iterator(Pointer pointer) {
            super(pointer);
        }

        public Iterator() {
        }
    }

    public Rect2d[] get() {
        int size = size() < 2147483647L ? (int) size() : Integer.MAX_VALUE;
        Rect2d[] rect2dArr = new Rect2d[size];
        for (int i = 0; i < size; i++) {
            rect2dArr[i] = get((long) i);
        }
        return rect2dArr;
    }

    public String toString() {
        return Arrays.toString(get());
    }

    public Rect2d pop_back() {
        long size = size() - 1;
        Rect2d rect2d = get(size);
        resize(size);
        return rect2d;
    }

    public Rect2dVector push_back(Rect2d rect2d) {
        long size = size();
        resize(1 + size);
        return put(size, rect2d);
    }

    public Rect2dVector put(Rect2d rect2d) {
        if (size() != 1) {
            resize(1);
        }
        return put(0, rect2d);
    }

    public Rect2dVector put(Rect2d... rect2dArr) {
        if (size() != ((long) rect2dArr.length)) {
            resize((long) rect2dArr.length);
        }
        for (int i = 0; i < rect2dArr.length; i++) {
            put((long) i, rect2dArr[i]);
        }
        return this;
    }
}
