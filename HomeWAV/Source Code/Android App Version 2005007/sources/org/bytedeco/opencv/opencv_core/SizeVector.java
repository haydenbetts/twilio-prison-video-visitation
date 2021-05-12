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
@Name({"std::vector<cv::Size>"})
public class SizeVector extends Pointer {
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
    public native Size get(@Cast({"size_t"}) long j);

    @ByVal
    public native Iterator insert(@ByVal Iterator iterator, @ByRef Size size);

    public native SizeVector put(@Cast({"size_t"}) long j, Size size);

    @ByRef
    @Name({"operator ="})
    public native SizeVector put(@ByRef SizeVector sizeVector);

    public native void resize(@Cast({"size_t"}) long j);

    public native long size();

    static {
        Loader.load();
    }

    public SizeVector(Pointer pointer) {
        super(pointer);
    }

    public SizeVector(Size size) {
        this(1);
        put(0, size);
    }

    public SizeVector(Size... sizeArr) {
        this((long) sizeArr.length);
        put(sizeArr);
    }

    public SizeVector() {
        allocate();
    }

    public SizeVector(long j) {
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
        public native Size get();

        @ByRef
        @Name({"operator ++"})
        public native Iterator increment();

        public Iterator(Pointer pointer) {
            super(pointer);
        }

        public Iterator() {
        }
    }

    public Size[] get() {
        int size = size() < 2147483647L ? (int) size() : Integer.MAX_VALUE;
        Size[] sizeArr = new Size[size];
        for (int i = 0; i < size; i++) {
            sizeArr[i] = get((long) i);
        }
        return sizeArr;
    }

    public String toString() {
        return Arrays.toString(get());
    }

    public Size pop_back() {
        long size = size() - 1;
        Size size2 = get(size);
        resize(size);
        return size2;
    }

    public SizeVector push_back(Size size) {
        long size2 = size();
        resize(1 + size2);
        return put(size2, size);
    }

    public SizeVector put(Size size) {
        if (size() != 1) {
            resize(1);
        }
        return put(0, size);
    }

    public SizeVector put(Size... sizeArr) {
        if (size() != ((long) sizeArr.length)) {
            resize((long) sizeArr.length);
        }
        for (int i = 0; i < sizeArr.length; i++) {
            put((long) i, sizeArr[i]);
        }
        return this;
    }
}
