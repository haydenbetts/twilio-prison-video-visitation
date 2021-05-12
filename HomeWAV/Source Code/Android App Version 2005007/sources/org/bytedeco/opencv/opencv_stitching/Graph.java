package org.bytedeco.opencv.opencv_stitching;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_stitching;

@Namespace("cv::detail")
@Properties(inherit = {opencv_stitching.class})
@NoOffset
public class Graph extends Pointer {
    private native void allocate();

    private native void allocate(int i);

    public native void addEdge(int i, int i2, float f);

    public native void create(int i);

    public native int numVertices();

    static {
        Loader.load();
    }

    public Graph(Pointer pointer) {
        super(pointer);
    }

    public Graph(int i) {
        super((Pointer) null);
        allocate(i);
    }

    public Graph() {
        super((Pointer) null);
        allocate();
    }
}
