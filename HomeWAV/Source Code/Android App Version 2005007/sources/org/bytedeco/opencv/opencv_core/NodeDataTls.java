package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv::instr")
@Properties(inherit = {opencv_core.class})
@NoOffset
public class NodeDataTls extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    @Cast({"uint64"})
    public native int m_ticksTotal();

    public native NodeDataTls m_ticksTotal(int i);

    static {
        Loader.load();
    }

    public NodeDataTls(Pointer pointer) {
        super(pointer);
    }

    public NodeDataTls(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public NodeDataTls position(long j) {
        return (NodeDataTls) super.position(j);
    }

    public NodeDataTls getPointer(long j) {
        return new NodeDataTls((Pointer) this).position(this.position + j);
    }

    public NodeDataTls() {
        super((Pointer) null);
        allocate();
    }
}
