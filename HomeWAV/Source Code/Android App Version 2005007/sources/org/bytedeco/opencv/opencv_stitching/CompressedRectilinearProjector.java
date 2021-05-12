package org.bytedeco.opencv.opencv_stitching;

import java.nio.FloatBuffer;
import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_stitching;

@Namespace("cv::detail")
@Properties(inherit = {opencv_stitching.class})
@NoOffset
public class CompressedRectilinearProjector extends ProjectorBase {
    private native void allocate();

    private native void allocateArray(long j);

    public native float a();

    public native CompressedRectilinearProjector a(float f);

    public native float b();

    public native CompressedRectilinearProjector b(float f);

    public native void mapBackward(float f, float f2, @ByRef FloatBuffer floatBuffer, @ByRef FloatBuffer floatBuffer2);

    public native void mapBackward(float f, float f2, @ByRef FloatPointer floatPointer, @ByRef FloatPointer floatPointer2);

    public native void mapBackward(float f, float f2, @ByRef float[] fArr, @ByRef float[] fArr2);

    public native void mapForward(float f, float f2, @ByRef FloatBuffer floatBuffer, @ByRef FloatBuffer floatBuffer2);

    public native void mapForward(float f, float f2, @ByRef FloatPointer floatPointer, @ByRef FloatPointer floatPointer2);

    public native void mapForward(float f, float f2, @ByRef float[] fArr, @ByRef float[] fArr2);

    static {
        Loader.load();
    }

    public CompressedRectilinearProjector() {
        super((Pointer) null);
        allocate();
    }

    public CompressedRectilinearProjector(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CompressedRectilinearProjector(Pointer pointer) {
        super(pointer);
    }

    public CompressedRectilinearProjector position(long j) {
        return (CompressedRectilinearProjector) super.position(j);
    }

    public CompressedRectilinearProjector getPointer(long j) {
        return new CompressedRectilinearProjector((Pointer) this).position(this.position + j);
    }
}
