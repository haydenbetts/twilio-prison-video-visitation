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
@Name({"std::vector<cv::DMatch>"})
public class DMatchVector extends Pointer {
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
    public native DMatch get(@Cast({"size_t"}) long j);

    @ByVal
    public native Iterator insert(@ByVal Iterator iterator, @ByRef DMatch dMatch);

    public native DMatchVector put(@Cast({"size_t"}) long j, DMatch dMatch);

    @ByRef
    @Name({"operator ="})
    public native DMatchVector put(@ByRef DMatchVector dMatchVector);

    public native void resize(@Cast({"size_t"}) long j);

    public native long size();

    static {
        Loader.load();
    }

    public DMatchVector(Pointer pointer) {
        super(pointer);
    }

    public DMatchVector(DMatch dMatch) {
        this(1);
        put(0, dMatch);
    }

    public DMatchVector(DMatch... dMatchArr) {
        this((long) dMatchArr.length);
        put(dMatchArr);
    }

    public DMatchVector() {
        allocate();
    }

    public DMatchVector(long j) {
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
        public native DMatch get();

        @ByRef
        @Name({"operator ++"})
        public native Iterator increment();

        public Iterator(Pointer pointer) {
            super(pointer);
        }

        public Iterator() {
        }
    }

    public DMatch[] get() {
        int size = size() < 2147483647L ? (int) size() : Integer.MAX_VALUE;
        DMatch[] dMatchArr = new DMatch[size];
        for (int i = 0; i < size; i++) {
            dMatchArr[i] = get((long) i);
        }
        return dMatchArr;
    }

    public String toString() {
        return Arrays.toString(get());
    }

    public DMatch pop_back() {
        long size = size() - 1;
        DMatch dMatch = get(size);
        resize(size);
        return dMatch;
    }

    public DMatchVector push_back(DMatch dMatch) {
        long size = size();
        resize(1 + size);
        return put(size, dMatch);
    }

    public DMatchVector put(DMatch dMatch) {
        if (size() != 1) {
            resize(1);
        }
        return put(0, dMatch);
    }

    public DMatchVector put(DMatch... dMatchArr) {
        if (size() != ((long) dMatchArr.length)) {
            resize((long) dMatchArr.length);
        }
        for (int i = 0; i < dMatchArr.length; i++) {
            put((long) i, dMatchArr[i]);
        }
        return this;
    }
}
