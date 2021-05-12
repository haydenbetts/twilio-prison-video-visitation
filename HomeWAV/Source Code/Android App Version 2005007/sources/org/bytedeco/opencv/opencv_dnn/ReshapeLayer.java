package org.bytedeco.opencv.opencv_dnn;

import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.StdVector;
import org.bytedeco.opencv.opencv_core.Range;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_dnn;

@Namespace("cv::dnn")
@Properties(inherit = {opencv_dnn.class})
@NoOffset
public class ReshapeLayer extends Layer {
    @opencv_core.Ptr
    public static native ReshapeLayer create(@ByRef @Const LayerParams layerParams);

    @ByRef
    @StdVector
    public native IntPointer newShapeDesc();

    public native ReshapeLayer newShapeDesc(IntPointer intPointer);

    @ByRef
    public native Range newShapeRange();

    public native ReshapeLayer newShapeRange(Range range);

    static {
        Loader.load();
    }

    public ReshapeLayer(Pointer pointer) {
        super(pointer);
    }
}
