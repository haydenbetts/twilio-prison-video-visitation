package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv::instr")
@Properties(inherit = {opencv_core.class})
@NoOffset
public class NodeData extends Pointer {
    private native void allocate();

    private native void allocate(String str, String str2, int i, Pointer pointer, @Cast({"bool"}) boolean z, @Cast({"cv::instr::TYPE"}) int i2, @Cast({"cv::instr::IMPL"}) int i3);

    private native void allocate(@Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, int i, Pointer pointer, @Cast({"bool"}) boolean z, @Cast({"cv::instr::TYPE"}) int i2, @Cast({"cv::instr::IMPL"}) int i3);

    private native void allocate(@ByRef NodeData nodeData);

    private native void allocateArray(long j);

    public native double getMeanMs();

    public native double getTotalMs();

    public native NodeData m_alwaysExpand(boolean z);

    @Cast({"bool"})
    public native boolean m_alwaysExpand();

    public native int m_counter();

    public native NodeData m_counter(int i);

    @Cast({"const char*"})
    public native BytePointer m_fileName();

    public native NodeData m_fileName(BytePointer bytePointer);

    public native NodeData m_funError(boolean z);

    @Cast({"bool"})
    public native boolean m_funError();

    @opencv_core.Str
    public native BytePointer m_funName();

    public native NodeData m_funName(BytePointer bytePointer);

    @Cast({"cv::instr::IMPL"})
    public native int m_implType();

    public native NodeData m_implType(int i);

    @Cast({"cv::instr::TYPE"})
    public native int m_instrType();

    public native NodeData m_instrType(int i);

    public native int m_lineNum();

    public native NodeData m_lineNum(int i);

    public native Pointer m_retAddress();

    public native NodeData m_retAddress(Pointer pointer);

    public native int m_threads();

    public native NodeData m_threads(int i);

    @Cast({"uint64"})
    public native int m_ticksTotal();

    public native NodeData m_ticksTotal(int i);

    @ByRef
    @MemberGetter
    public native NodeDataTlsData m_tls();

    @ByRef
    @Name({"operator ="})
    public native NodeData put(@ByRef @Const NodeData nodeData);

    static {
        Loader.load();
    }

    public NodeData(Pointer pointer) {
        super(pointer);
    }

    public NodeData(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public NodeData position(long j) {
        return (NodeData) super.position(j);
    }

    public NodeData getPointer(long j) {
        return new NodeData(this).position(this.position + j);
    }

    public NodeData(@Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, int i, Pointer pointer, @Cast({"bool"}) boolean z, @Cast({"cv::instr::TYPE"}) int i2, @Cast({"cv::instr::IMPL"}) int i3) {
        super((Pointer) null);
        allocate(bytePointer, bytePointer2, i, pointer, z, i2, i3);
    }

    public NodeData() {
        super((Pointer) null);
        allocate();
    }

    public NodeData(String str, String str2, int i, Pointer pointer, @Cast({"bool"}) boolean z, @Cast({"cv::instr::TYPE"}) int i2, @Cast({"cv::instr::IMPL"}) int i3) {
        super((Pointer) null);
        allocate(str, str2, i, pointer, z, i2, i3);
    }

    public NodeData(@ByRef NodeData nodeData) {
        super((Pointer) null);
        allocate(nodeData);
    }
}
