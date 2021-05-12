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
@Name({"std::vector<cv::text::ERStat>"})
public class ERStatVector extends Pointer {
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
    public native ERStat get(@Cast({"size_t"}) long j);

    @ByVal
    public native Iterator insert(@ByVal Iterator iterator, @ByRef ERStat eRStat);

    public native ERStatVector put(@Cast({"size_t"}) long j, ERStat eRStat);

    @ByRef
    @Name({"operator ="})
    public native ERStatVector put(@ByRef ERStatVector eRStatVector);

    public native void resize(@Cast({"size_t"}) long j);

    public native long size();

    static {
        Loader.load();
    }

    public ERStatVector(Pointer pointer) {
        super(pointer);
    }

    public ERStatVector(ERStat eRStat) {
        this(1);
        put(0, eRStat);
    }

    public ERStatVector(ERStat... eRStatArr) {
        this((long) eRStatArr.length);
        put(eRStatArr);
    }

    public ERStatVector() {
        allocate();
    }

    public ERStatVector(long j) {
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
        public native ERStat get();

        @ByRef
        @Name({"operator ++"})
        public native Iterator increment();

        public Iterator(Pointer pointer) {
            super(pointer);
        }

        public Iterator() {
        }
    }

    public ERStat[] get() {
        int size = size() < 2147483647L ? (int) size() : Integer.MAX_VALUE;
        ERStat[] eRStatArr = new ERStat[size];
        for (int i = 0; i < size; i++) {
            eRStatArr[i] = get((long) i);
        }
        return eRStatArr;
    }

    public String toString() {
        return Arrays.toString(get());
    }

    public ERStat pop_back() {
        long size = size() - 1;
        ERStat eRStat = get(size);
        resize(size);
        return eRStat;
    }

    public ERStatVector push_back(ERStat eRStat) {
        long size = size();
        resize(1 + size);
        return put(size, eRStat);
    }

    public ERStatVector put(ERStat eRStat) {
        if (size() != 1) {
            resize(1);
        }
        return put(0, eRStat);
    }

    public ERStatVector put(ERStat... eRStatArr) {
        if (size() != ((long) eRStatArr.length)) {
            resize((long) eRStatArr.length);
        }
        for (int i = 0; i < eRStatArr.length; i++) {
            put((long) i, eRStatArr[i]);
        }
        return this;
    }
}
