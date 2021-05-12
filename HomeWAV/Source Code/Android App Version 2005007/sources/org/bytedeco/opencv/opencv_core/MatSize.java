package org.bytedeco.opencv.opencv_core;

import java.nio.IntBuffer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv")
@Properties(inherit = {opencv_core.class})
@NoOffset
public class MatSize extends Pointer {
    private native void allocate(IntBuffer intBuffer);

    private native void allocate(IntPointer intPointer);

    private native void allocate(int[] iArr);

    @ByVal
    @Name({"operator ()"})
    public native Size apply();

    @Const
    @Name({"operator const int*"})
    public native IntPointer asIntPointer();

    public native int dims();

    @Cast({"bool"})
    @Name({"operator =="})
    public native boolean equals(@ByRef @Const MatSize matSize);

    @ByRef
    @Name({"operator []"})
    public native IntPointer get(int i);

    @Cast({"bool"})
    @Name({"operator !="})
    public native boolean notEquals(@ByRef @Const MatSize matSize);

    public native IntPointer p();

    public native MatSize p(IntPointer intPointer);

    static {
        Loader.load();
    }

    public MatSize(Pointer pointer) {
        super(pointer);
    }

    public MatSize(IntPointer intPointer) {
        super((Pointer) null);
        allocate(intPointer);
    }

    public MatSize(IntBuffer intBuffer) {
        super((Pointer) null);
        allocate(intBuffer);
    }

    public MatSize(int[] iArr) {
        super((Pointer) null);
        allocate(iArr);
    }
}
