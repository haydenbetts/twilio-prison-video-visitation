package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.SizeTPointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv")
@Properties(inherit = {opencv_core.class})
@NoOffset
public class MatStep extends Pointer {
    private native void allocate();

    private native void allocate(@Cast({"size_t"}) long j);

    @Cast({"size_t"})
    @Name({"operator size_t"})
    public native long asLong();

    @Cast({"size_t"})
    public native long buf(int i);

    @MemberGetter
    @Cast({"size_t*"})
    public native SizeTPointer buf();

    public native MatStep buf(int i, long j);

    @ByRef
    @Cast({"size_t*"})
    @Name({"operator []"})
    public native SizeTPointer get(int i);

    @Cast({"size_t*"})
    public native SizeTPointer p();

    public native MatStep p(SizeTPointer sizeTPointer);

    @ByRef
    @Name({"operator ="})
    public native MatStep put(@Cast({"size_t"}) long j);

    static {
        Loader.load();
    }

    public MatStep(Pointer pointer) {
        super(pointer);
    }

    public MatStep() {
        super((Pointer) null);
        allocate();
    }

    public MatStep(@Cast({"size_t"}) long j) {
        super((Pointer) null);
        allocate(j);
    }
}
