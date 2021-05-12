package org.bytedeco.opencv.opencv_imgproc;

import java.util.Arrays;
import org.bytedeco.javacpp.FloatPointer;
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
import org.bytedeco.opencv.presets.opencv_imgproc;

@Properties(inherit = {opencv_imgproc.class})
@Name({"std::vector<cv::Vec6f>"})
public class Vec6fVector extends Pointer {
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
    @Cast({"cv::Vec6f*"})
    public native FloatPointer get(@Cast({"size_t"}) long j);

    @ByVal
    public native Iterator insert(@ByVal Iterator iterator, @ByRef @Cast({"cv::Vec6f*"}) FloatPointer floatPointer);

    public native Vec6fVector put(@Cast({"size_t"}) long j, FloatPointer floatPointer);

    @ByRef
    @Name({"operator ="})
    public native Vec6fVector put(@ByRef Vec6fVector vec6fVector);

    public native void resize(@Cast({"size_t"}) long j);

    public native long size();

    static {
        Loader.load();
    }

    public Vec6fVector(Pointer pointer) {
        super(pointer);
    }

    public Vec6fVector(FloatPointer floatPointer) {
        this(1);
        put(0, floatPointer);
    }

    public Vec6fVector(FloatPointer... floatPointerArr) {
        this((long) floatPointerArr.length);
        put(floatPointerArr);
    }

    public Vec6fVector() {
        allocate();
    }

    public Vec6fVector(long j) {
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
        @Cast({"cv::Vec6f*"})
        @Const
        @Name({"operator *"})
        public native FloatPointer get();

        @ByRef
        @Name({"operator ++"})
        public native Iterator increment();

        public Iterator(Pointer pointer) {
            super(pointer);
        }

        public Iterator() {
        }
    }

    public FloatPointer[] get() {
        int size = size() < 2147483647L ? (int) size() : Integer.MAX_VALUE;
        FloatPointer[] floatPointerArr = new FloatPointer[size];
        for (int i = 0; i < size; i++) {
            floatPointerArr[i] = get((long) i);
        }
        return floatPointerArr;
    }

    public String toString() {
        return Arrays.toString(get());
    }

    public FloatPointer pop_back() {
        long size = size() - 1;
        FloatPointer floatPointer = get(size);
        resize(size);
        return floatPointer;
    }

    public Vec6fVector push_back(FloatPointer floatPointer) {
        long size = size();
        resize(1 + size);
        return put(size, floatPointer);
    }

    public Vec6fVector put(FloatPointer floatPointer) {
        if (size() != 1) {
            resize(1);
        }
        return put(0, floatPointer);
    }

    public Vec6fVector put(FloatPointer... floatPointerArr) {
        if (size() != ((long) floatPointerArr.length)) {
            resize((long) floatPointerArr.length);
        }
        for (int i = 0; i < floatPointerArr.length; i++) {
            put((long) i, floatPointerArr[i]);
        }
        return this;
    }
}
