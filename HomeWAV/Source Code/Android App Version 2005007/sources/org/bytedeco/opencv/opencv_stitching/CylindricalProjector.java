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
public class CylindricalProjector extends ProjectorBase {
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

    public CylindricalProjector() {
        super((Pointer) null);
        allocate();
    }

    public CylindricalProjector(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CylindricalProjector(Pointer pointer) {
        super(pointer);
    }

    public CylindricalProjector position(long j) {
        return (CylindricalProjector) super.position(j);
    }

    public CylindricalProjector getPointer(long j) {
        return new CylindricalProjector((Pointer) this).position(this.position + j);
    }
}
