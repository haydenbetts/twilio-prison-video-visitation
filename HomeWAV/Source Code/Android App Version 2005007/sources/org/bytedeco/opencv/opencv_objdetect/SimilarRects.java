package org.bytedeco.opencv.opencv_objdetect;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.presets.opencv_objdetect;

@Namespace("cv")
@Properties(inherit = {opencv_objdetect.class})
@NoOffset
public class SimilarRects extends Pointer {
    private native void allocate(double d);

    @Cast({"bool"})
    @Name({"operator ()"})
    public native boolean apply(@ByRef @Const Rect rect, @ByRef @Const Rect rect2);

    public native double eps();

    public native SimilarRects eps(double d);

    static {
        Loader.load();
    }

    public SimilarRects(Pointer pointer) {
        super(pointer);
    }

    public SimilarRects(double d) {
        super((Pointer) null);
        allocate(d);
    }
}
