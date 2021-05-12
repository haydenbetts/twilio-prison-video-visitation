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
@Name({"std::vector<std::vector<cv::DMatch> >"})
public class DMatchVectorVector extends Pointer {
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
    public native DMatchVector get(@Cast({"size_t"}) long j);

    @ByVal
    public native Iterator insert(@ByVal Iterator iterator, @ByRef DMatchVector dMatchVector);

    public native DMatchVectorVector put(@Cast({"size_t"}) long j, DMatchVector dMatchVector);

    @ByRef
    @Name({"operator ="})
    public native DMatchVectorVector put(@ByRef DMatchVectorVector dMatchVectorVector);

    public native void resize(@Cast({"size_t"}) long j);

    public native long size();

    static {
        Loader.load();
    }

    public DMatchVectorVector(Pointer pointer) {
        super(pointer);
    }

    public DMatchVectorVector(DMatchVector dMatchVector) {
        this(1);
        put(0, dMatchVector);
    }

    public DMatchVectorVector(DMatchVector... dMatchVectorArr) {
        this((long) dMatchVectorArr.length);
        put(dMatchVectorArr);
    }

    public DMatchVectorVector() {
        allocate();
    }

    public DMatchVectorVector(long j) {
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
        public native DMatchVector get();

        @ByRef
        @Name({"operator ++"})
        public native Iterator increment();

        public Iterator(Pointer pointer) {
            super(pointer);
        }

        public Iterator() {
        }
    }

    public DMatchVector[] get() {
        int size = size() < 2147483647L ? (int) size() : Integer.MAX_VALUE;
        DMatchVector[] dMatchVectorArr = new DMatchVector[size];
        for (int i = 0; i < size; i++) {
            dMatchVectorArr[i] = get((long) i);
        }
        return dMatchVectorArr;
    }

    public String toString() {
        return Arrays.toString(get());
    }

    public DMatchVector pop_back() {
        long size = size() - 1;
        DMatchVector dMatchVector = get(size);
        resize(size);
        return dMatchVector;
    }

    public DMatchVectorVector push_back(DMatchVector dMatchVector) {
        long size = size();
        resize(1 + size);
        return put(size, dMatchVector);
    }

    public DMatchVectorVector put(DMatchVector dMatchVector) {
        if (size() != 1) {
            resize(1);
        }
        return put(0, dMatchVector);
    }

    public DMatchVectorVector put(DMatchVector... dMatchVectorArr) {
        if (size() != ((long) dMatchVectorArr.length)) {
            resize((long) dMatchVectorArr.length);
        }
        for (int i = 0; i < dMatchVectorArr.length; i++) {
            put((long) i, dMatchVectorArr[i]);
        }
        return this;
    }
}
