package org.bytedeco.opencv.opencv_stitching;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_stitching;

@Namespace("cv::detail")
@Properties(inherit = {opencv_stitching.class})
@NoOffset
public class GraphEdge extends Pointer {
    private native void allocate(int i, int i2, float f);

    public native int from();

    public native GraphEdge from(int i);

    @Cast({"bool"})
    @Name({"operator >"})
    public native boolean greaterThan(@ByRef @Const GraphEdge graphEdge);

    @Cast({"bool"})
    @Name({"operator <"})
    public native boolean lessThan(@ByRef @Const GraphEdge graphEdge);

    public native int to();

    public native GraphEdge to(int i);

    public native float weight();

    public native GraphEdge weight(float f);

    static {
        Loader.load();
    }

    public GraphEdge(Pointer pointer) {
        super(pointer);
    }

    public GraphEdge(int i, int i2, float f) {
        super((Pointer) null);
        allocate(i, i2, f);
    }
}
