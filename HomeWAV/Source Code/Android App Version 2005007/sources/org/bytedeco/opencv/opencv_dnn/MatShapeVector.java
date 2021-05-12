package org.bytedeco.opencv.opencv_dnn;

import java.util.Arrays;
import org.bytedeco.javacpp.IntPointer;
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
import org.bytedeco.javacpp.annotation.StdVector;
import org.bytedeco.opencv.presets.opencv_dnn;

@Properties(inherit = {opencv_dnn.class})
@Name({"std::vector<cv::dnn::MatShape>"})
public class MatShapeVector extends Pointer {
    private native void allocate();

    private native void allocate(@Cast({"size_t"}) long j);

    @ByVal
    public native Iterator begin();

    @ByVal
    public native Iterator end();

    @ByVal
    public native Iterator erase(@ByVal Iterator iterator);

    @Index(function = "at")
    @StdVector
    public native IntPointer get(@Cast({"size_t"}) long j);

    @ByVal
    public native Iterator insert(@ByVal Iterator iterator, @StdVector IntPointer intPointer);

    public native MatShapeVector put(@Cast({"size_t"}) long j, IntPointer intPointer);

    @ByRef
    @Name({"operator ="})
    public native MatShapeVector put(@ByRef MatShapeVector matShapeVector);

    public native void resize(@Cast({"size_t"}) long j);

    public native long size();

    static {
        Loader.load();
    }

    public MatShapeVector(Pointer pointer) {
        super(pointer);
    }

    public MatShapeVector(IntPointer intPointer) {
        this(1);
        put(0, intPointer);
    }

    public MatShapeVector(IntPointer... intPointerArr) {
        this((long) intPointerArr.length);
        put(intPointerArr);
    }

    public MatShapeVector() {
        allocate();
    }

    public MatShapeVector(long j) {
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

        @Const
        @StdVector
        @Name({"operator *"})
        public native IntPointer get();

        @ByRef
        @Name({"operator ++"})
        public native Iterator increment();

        public Iterator(Pointer pointer) {
            super(pointer);
        }

        public Iterator() {
        }
    }

    public IntPointer[] get() {
        int size = size() < 2147483647L ? (int) size() : Integer.MAX_VALUE;
        IntPointer[] intPointerArr = new IntPointer[size];
        for (int i = 0; i < size; i++) {
            intPointerArr[i] = get((long) i);
        }
        return intPointerArr;
    }

    public String toString() {
        return Arrays.toString(get());
    }

    public IntPointer pop_back() {
        long size = size() - 1;
        IntPointer intPointer = get(size);
        resize(size);
        return intPointer;
    }

    public MatShapeVector push_back(IntPointer intPointer) {
        long size = size();
        resize(1 + size);
        return put(size, intPointer);
    }

    public MatShapeVector put(IntPointer intPointer) {
        if (size() != 1) {
            resize(1);
        }
        return put(0, intPointer);
    }

    public MatShapeVector put(IntPointer... intPointerArr) {
        if (size() != ((long) intPointerArr.length)) {
            resize((long) intPointerArr.length);
        }
        for (int i = 0; i < intPointerArr.length; i++) {
            put((long) i, intPointerArr[i]);
        }
        return this;
    }
}
