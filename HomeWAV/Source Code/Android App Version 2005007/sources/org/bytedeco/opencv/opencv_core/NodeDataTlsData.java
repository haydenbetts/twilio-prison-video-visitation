package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
@Name({"cv::TLSData<cv::instr::NodeDataTls>"})
public class NodeDataTlsData extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native void cleanup();

    public native NodeDataTls get();

    @ByRef
    public native NodeDataTls getRef();

    static {
        Loader.load();
    }

    public NodeDataTlsData(Pointer pointer) {
        super(pointer);
    }

    public NodeDataTlsData(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public NodeDataTlsData position(long j) {
        return (NodeDataTlsData) super.position(j);
    }

    public NodeDataTlsData getPointer(long j) {
        return new NodeDataTlsData((Pointer) this).position(this.position + j);
    }

    public NodeDataTlsData() {
        super((Pointer) null);
        allocate();
    }
}
