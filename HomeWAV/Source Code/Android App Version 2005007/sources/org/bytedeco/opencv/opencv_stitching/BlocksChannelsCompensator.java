package org.bytedeco.opencv.opencv_stitching;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.PointVector;
import org.bytedeco.opencv.opencv_core.UMatBytePairVector;
import org.bytedeco.opencv.opencv_core.UMatVector;
import org.bytedeco.opencv.presets.opencv_stitching;

@Namespace("cv::detail")
@Properties(inherit = {opencv_stitching.class})
public class BlocksChannelsCompensator extends BlocksCompensator {
    private native void allocate();

    private native void allocate(int i, int i2, int i3);

    private native void allocateArray(long j);

    public native void feed(@ByRef @Const PointVector pointVector, @ByRef @Const UMatVector uMatVector, @ByRef @Const UMatBytePairVector uMatBytePairVector);

    static {
        Loader.load();
    }

    public BlocksChannelsCompensator(Pointer pointer) {
        super(pointer);
    }

    public BlocksChannelsCompensator(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public BlocksChannelsCompensator position(long j) {
        return (BlocksChannelsCompensator) super.position(j);
    }

    public BlocksChannelsCompensator getPointer(long j) {
        return new BlocksChannelsCompensator((Pointer) this).position(this.position + j);
    }

    public BlocksChannelsCompensator(int i, int i2, int i3) {
        super((Pointer) null);
        allocate(i, i2, i3);
    }

    public BlocksChannelsCompensator() {
        super((Pointer) null);
        allocate();
    }
}
