package org.bytedeco.opencv.opencv_stitching;

import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.StdVector;
import org.bytedeco.opencv.presets.opencv_stitching;

@Namespace("cv::detail")
@Properties(inherit = {opencv_stitching.class})
@NoOffset
public class DisjointSets extends Pointer {
    private native void allocate();

    private native void allocate(int i);

    public native void createOneElemSets(int i);

    public native int findSetByElem(int i);

    public native int mergeSets(int i, int i2);

    @StdVector
    public native IntPointer parent();

    public native DisjointSets parent(IntPointer intPointer);

    @StdVector
    public native IntPointer size();

    public native DisjointSets size(IntPointer intPointer);

    static {
        Loader.load();
    }

    public DisjointSets(Pointer pointer) {
        super(pointer);
    }

    public DisjointSets(int i) {
        super((Pointer) null);
        allocate(i);
    }

    public DisjointSets() {
        super((Pointer) null);
        allocate();
    }
}
