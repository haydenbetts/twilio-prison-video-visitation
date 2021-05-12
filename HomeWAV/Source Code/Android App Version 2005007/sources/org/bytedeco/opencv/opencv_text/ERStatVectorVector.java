package org.bytedeco.opencv.opencv_text;

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
import org.bytedeco.opencv.presets.opencv_text;

@Properties(inherit = {opencv_text.class})
@Name({"std::vector<std::vector<cv::text::ERStat> >"})
public class ERStatVectorVector extends Pointer {
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
    public native ERStatVector get(@Cast({"size_t"}) long j);

    @ByVal
    public native Iterator insert(@ByVal Iterator iterator, @ByRef ERStatVector eRStatVector);

    public native ERStatVectorVector put(@Cast({"size_t"}) long j, ERStatVector eRStatVector);

    @ByRef
    @Name({"operator ="})
    public native ERStatVectorVector put(@ByRef ERStatVectorVector eRStatVectorVector);

    public native void resize(@Cast({"size_t"}) long j);

    public native long size();

    static {
        Loader.load();
    }

    public ERStatVectorVector(Pointer pointer) {
        super(pointer);
    }

    public ERStatVectorVector(ERStatVector eRStatVector) {
        this(1);
        put(0, eRStatVector);
    }

    public ERStatVectorVector(ERStatVector... eRStatVectorArr) {
        this((long) eRStatVectorArr.length);
        put(eRStatVectorArr);
    }

    public ERStatVectorVector() {
        allocate();
    }

    public ERStatVectorVector(long j) {
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
        public native ERStatVector get();

        @ByRef
        @Name({"operator ++"})
        public native Iterator increment();

        public Iterator(Pointer pointer) {
            super(pointer);
        }

        public Iterator() {
        }
    }

    public ERStatVector[] get() {
        int size = size() < 2147483647L ? (int) size() : Integer.MAX_VALUE;
        ERStatVector[] eRStatVectorArr = new ERStatVector[size];
        for (int i = 0; i < size; i++) {
            eRStatVectorArr[i] = get((long) i);
        }
        return eRStatVectorArr;
    }

    public String toString() {
        return Arrays.toString(get());
    }

    public ERStatVector pop_back() {
        long size = size() - 1;
        ERStatVector eRStatVector = get(size);
        resize(size);
        return eRStatVector;
    }

    public ERStatVectorVector push_back(ERStatVector eRStatVector) {
        long size = size();
        resize(1 + size);
        return put(size, eRStatVector);
    }

    public ERStatVectorVector put(ERStatVector eRStatVector) {
        if (size() != 1) {
            resize(1);
        }
        return put(0, eRStatVector);
    }

    public ERStatVectorVector put(ERStatVector... eRStatVectorArr) {
        if (size() != ((long) eRStatVectorArr.length)) {
            resize((long) eRStatVectorArr.length);
        }
        for (int i = 0; i < eRStatVectorArr.length; i++) {
            put((long) i, eRStatVectorArr[i]);
        }
        return this;
    }
}
