package org.bytedeco.opencv.opencv_core;

import java.util.Arrays;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Index;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
@Name({"std::vector<cv::instr::NodeDataTls*>"})
public class NodeDataTlsVector extends Pointer {
    private native void allocate();

    private native void allocate(@Cast({"size_t"}) long j);

    @ByVal
    public native Iterator begin();

    @ByVal
    public native Iterator end();

    @ByVal
    public native Iterator erase(@ByVal Iterator iterator);

    @Index(function = "at")
    public native NodeDataTls get(@Cast({"size_t"}) long j);

    @ByVal
    public native Iterator insert(@ByVal Iterator iterator, NodeDataTls nodeDataTls);

    public native NodeDataTlsVector put(@Cast({"size_t"}) long j, NodeDataTls nodeDataTls);

    @ByRef
    @Name({"operator ="})
    public native NodeDataTlsVector put(@ByRef NodeDataTlsVector nodeDataTlsVector);

    public native void resize(@Cast({"size_t"}) long j);

    public native long size();

    static {
        Loader.load();
    }

    public NodeDataTlsVector(Pointer pointer) {
        super(pointer);
    }

    public NodeDataTlsVector(NodeDataTls nodeDataTls) {
        this(1);
        put(0, nodeDataTls);
    }

    public NodeDataTlsVector(NodeDataTls... nodeDataTlsArr) {
        this((long) nodeDataTlsArr.length);
        put(nodeDataTlsArr);
    }

    public NodeDataTlsVector() {
        allocate();
    }

    public NodeDataTlsVector(long j) {
        allocate(j);
    }

    public boolean empty() {
        return size() == 0;
    }

    public void clear() {
        resize(0);
    }

    @Name({"iterator"})
    @NoOffset
    public static class Iterator extends Pointer {
        @Name({"operator =="})
        public native boolean equals(@ByRef Iterator iterator);

        @Const
        @Name({"operator *"})
        public native NodeDataTls get();

        @ByRef
        @Name({"operator ++"})
        public native Iterator increment();

        public Iterator(Pointer pointer) {
            super(pointer);
        }

        public Iterator() {
        }
    }

    public NodeDataTls[] get() {
        int size = size() < 2147483647L ? (int) size() : Integer.MAX_VALUE;
        NodeDataTls[] nodeDataTlsArr = new NodeDataTls[size];
        for (int i = 0; i < size; i++) {
            nodeDataTlsArr[i] = get((long) i);
        }
        return nodeDataTlsArr;
    }

    public String toString() {
        return Arrays.toString(get());
    }

    public NodeDataTls pop_back() {
        long size = size() - 1;
        NodeDataTls nodeDataTls = get(size);
        resize(size);
        return nodeDataTls;
    }

    public NodeDataTlsVector push_back(NodeDataTls nodeDataTls) {
        long size = size();
        resize(1 + size);
        return put(size, nodeDataTls);
    }

    public NodeDataTlsVector put(NodeDataTls nodeDataTls) {
        if (size() != 1) {
            resize(1);
        }
        return put(0, nodeDataTls);
    }

    public NodeDataTlsVector put(NodeDataTls... nodeDataTlsArr) {
        if (size() != ((long) nodeDataTlsArr.length)) {
            resize((long) nodeDataTlsArr.length);
        }
        for (int i = 0; i < nodeDataTlsArr.length; i++) {
            put((long) i, nodeDataTlsArr[i]);
        }
        return this;
    }
}
