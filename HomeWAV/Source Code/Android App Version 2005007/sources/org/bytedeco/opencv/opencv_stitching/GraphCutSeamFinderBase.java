package org.bytedeco.opencv.opencv_stitching;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_stitching;

@Namespace("cv::detail")
@Properties(inherit = {opencv_stitching.class})
public class GraphCutSeamFinderBase extends Pointer {
    public static final int COST_COLOR = 0;
    public static final int COST_COLOR_GRAD = 1;

    private native void allocate();

    private native void allocateArray(long j);

    static {
        Loader.load();
    }

    public GraphCutSeamFinderBase() {
        super((Pointer) null);
        allocate();
    }

    public GraphCutSeamFinderBase(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public GraphCutSeamFinderBase(Pointer pointer) {
        super(pointer);
    }

    public GraphCutSeamFinderBase position(long j) {
        return (GraphCutSeamFinderBase) super.position(j);
    }

    public GraphCutSeamFinderBase getPointer(long j) {
        return new GraphCutSeamFinderBase((Pointer) this).position(this.position + j);
    }
}
