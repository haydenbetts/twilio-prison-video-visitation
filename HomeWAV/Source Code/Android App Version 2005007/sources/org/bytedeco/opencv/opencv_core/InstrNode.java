package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.StdVector;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
@Name({"cv::Node<cv::instr::NodeData>"})
@NoOffset
public class InstrNode extends Pointer {
    private native void allocate();

    private native void allocate(@ByRef NodeData nodeData);

    private native void allocateArray(long j);

    public native void addChild(InstrNode instrNode);

    public native int findChild(InstrNode instrNode);

    public native InstrNode findChild(@ByRef NodeData nodeData);

    public native int getDepth();

    @Cast({"cv::Node<cv::instr::NodeData>**"})
    @StdVector
    public native PointerPointer m_childs();

    public native InstrNode m_childs(PointerPointer pointerPointer);

    public native InstrNode m_pParent();

    public native InstrNode m_pParent(InstrNode instrNode);

    public native InstrNode m_payload(NodeData nodeData);

    @ByRef
    public native NodeData m_payload();

    public native void removeChilds();

    static {
        Loader.load();
    }

    public InstrNode(Pointer pointer) {
        super(pointer);
    }

    public InstrNode(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public InstrNode position(long j) {
        return (InstrNode) super.position(j);
    }

    public InstrNode getPointer(long j) {
        return new InstrNode((Pointer) this).position(this.position + j);
    }

    public InstrNode() {
        super((Pointer) null);
        allocate();
    }

    public InstrNode(@ByRef NodeData nodeData) {
        super((Pointer) null);
        allocate(nodeData);
    }
}
