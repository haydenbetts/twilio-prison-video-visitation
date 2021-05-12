package org.bytedeco.opencv.opencv_stitching;

import java.nio.FloatBuffer;
import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_stitching;

@Namespace("cv::detail")
@Properties(inherit = {opencv_stitching.class})
public class SphericalPortraitProjector extends ProjectorBase {
    private native void allocate();

    private native void allocateArray(long j);

    public native void mapBackward(float f, float f2, @ByRef FloatBuffer floatBuffer, @ByRef FloatBuffer floatBuffer2);

    public native void mapBackward(float f, float f2, @ByRef FloatPointer floatPointer, @ByRef FloatPointer floatPointer2);

    public native void mapBackward(float f, float f2, @ByRef float[] fArr, @ByRef float[] fArr2);

    public native void mapForward(float f, float f2, @ByRef FloatBuffer floatBuffer, @ByRef FloatBuffer floatBuffer2);

    public native void mapForward(float f, float f2, @ByRef FloatPointer floatPointer, @ByRef FloatPointer floatPointer2);

    public native void mapForward(float f, float f2, @ByRef float[] fArr, @ByRef float[] fArr2);

    static {
        Loader.load();
    }

    public SphericalPortraitProjector() {
        super((Pointer) null);
        allocate();
    }

    public SphericalPortraitProjector(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public SphericalPortraitProjector(Pointer pointer) {
        super(pointer);
    }

    public SphericalPortraitProjector position(long j) {
        return (SphericalPortraitProjector) super.position(j);
    }

    public SphericalPortraitProjector getPointer(long j) {
        return new SphericalPortraitProjector((Pointer) this).position(this.position + j);
    }
}
