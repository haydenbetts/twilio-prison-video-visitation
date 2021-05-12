package org.bytedeco.opencv.opencv_dnn;

import java.util.Arrays;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Index;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Range;
import org.bytedeco.opencv.presets.opencv_dnn;

@Properties(inherit = {opencv_dnn.class})
@Name({"std::vector<std::vector<cv::Range> >"})
public class RangeVectorVector extends Pointer {
    private native void allocate();

    private native void allocate(@Cast({"size_t"}) long j);

    @ByRef
    @Index(function = "at")
    public native Range get(@Cast({"size_t"}) long j, @Cast({"size_t"}) long j2);

    public native RangeVectorVector put(@Cast({"size_t"}) long j, @Cast({"size_t"}) long j2, Range range);

    @ByRef
    @Name({"operator ="})
    public native RangeVectorVector put(@ByRef RangeVectorVector rangeVectorVector);

    public native void resize(@Cast({"size_t"}) long j);

    @Index(function = "at")
    public native void resize(@Cast({"size_t"}) long j, @Cast({"size_t"}) long j2);

    public native long size();

    @Index(function = "at")
    public native long size(@Cast({"size_t"}) long j);

    static {
        Loader.load();
    }

    public RangeVectorVector(Pointer pointer) {
        super(pointer);
    }

    public RangeVectorVector(Range[]... rangeArr) {
        this((long) rangeArr.length);
        put(rangeArr);
    }

    public RangeVectorVector() {
        allocate();
    }

    public RangeVectorVector(long j) {
        allocate(j);
    }

    public boolean empty() {
        return size() == 0;
    }

    public void clear() {
        resize(0);
    }

    public boolean empty(@Cast({"size_t"}) long j) {
        return size(j) == 0;
    }

    public void clear(@Cast({"size_t"}) long j) {
        resize(j, 0);
    }

    public Range[][] get() {
        int size = size() < 2147483647L ? (int) size() : Integer.MAX_VALUE;
        Range[][] rangeArr = new Range[size][];
        for (int i = 0; i < size; i++) {
            long j = (long) i;
            rangeArr[i] = new Range[(size(j) < 2147483647L ? (int) size(j) : Integer.MAX_VALUE)];
            for (int i2 = 0; i2 < rangeArr[i].length; i2++) {
                rangeArr[i][i2] = get(j, (long) i2);
            }
        }
        return rangeArr;
    }

    public String toString() {
        return Arrays.deepToString(get());
    }

    public RangeVectorVector put(Range[]... rangeArr) {
        if (size() != ((long) rangeArr.length)) {
            resize((long) rangeArr.length);
        }
        for (int i = 0; i < rangeArr.length; i++) {
            long j = (long) i;
            if (size(j) != ((long) rangeArr[i].length)) {
                resize(j, (long) rangeArr[i].length);
            }
            for (int i2 = 0; i2 < rangeArr[i].length; i2++) {
                long j2 = j;
                put(j2, (long) i2, rangeArr[i][i2]);
            }
        }
        return this;
    }
}
