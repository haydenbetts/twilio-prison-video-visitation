package org.bytedeco.opencv.opencv_features2d;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.FileNode;
import org.bytedeco.opencv.opencv_core.FileStorage;
import org.bytedeco.opencv.opencv_core.GpuMatVector;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.UMatVector;
import org.bytedeco.opencv.opencv_flann.IndexParams;
import org.bytedeco.opencv.opencv_flann.SearchParams;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_features2d;

@Namespace("cv")
@Properties(inherit = {opencv_features2d.class})
@NoOffset
public class FlannBasedMatcher extends DescriptorMatcher {
    private native void allocate();

    private native void allocate(@opencv_core.Ptr IndexParams indexParams, @opencv_core.Ptr SearchParams searchParams);

    private native void allocateArray(long j);

    @opencv_core.Ptr
    public static native FlannBasedMatcher create();

    public native void add(@ByVal GpuMatVector gpuMatVector);

    public native void add(@ByVal MatVector matVector);

    public native void add(@ByVal UMatVector uMatVector);

    public native void clear();

    @opencv_core.Ptr
    public native DescriptorMatcher clone();

    @opencv_core.Ptr
    public native DescriptorMatcher clone(@Cast({"bool"}) boolean z);

    @Cast({"bool"})
    public native boolean isMaskSupported();

    public native void read(@ByRef @Const FileNode fileNode);

    public native void train();

    public native void write(@ByRef FileStorage fileStorage);

    static {
        Loader.load();
    }

    public FlannBasedMatcher(Pointer pointer) {
        super(pointer);
    }

    public FlannBasedMatcher(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public FlannBasedMatcher position(long j) {
        return (FlannBasedMatcher) super.position(j);
    }

    public FlannBasedMatcher getPointer(long j) {
        return new FlannBasedMatcher((Pointer) this).position(this.position + j);
    }

    public FlannBasedMatcher(@opencv_core.Ptr IndexParams indexParams, @opencv_core.Ptr SearchParams searchParams) {
        super((Pointer) null);
        allocate(indexParams, searchParams);
    }

    public FlannBasedMatcher() {
        super((Pointer) null);
        allocate();
    }
}
