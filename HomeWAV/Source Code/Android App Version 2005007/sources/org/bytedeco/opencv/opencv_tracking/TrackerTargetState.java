package org.bytedeco.opencv.opencv_tracking;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Point2f;
import org.bytedeco.opencv.presets.opencv_tracking;

@Namespace("cv")
@Properties(inherit = {opencv_tracking.class})
public class TrackerTargetState extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native int getTargetHeight();

    @ByVal
    public native Point2f getTargetPosition();

    public native int getTargetWidth();

    public native void setTargetHeight(int i);

    public native void setTargetPosition(@ByRef @Const Point2f point2f);

    public native void setTargetWidth(int i);

    static {
        Loader.load();
    }

    public TrackerTargetState() {
        super((Pointer) null);
        allocate();
    }

    public TrackerTargetState(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public TrackerTargetState(Pointer pointer) {
        super(pointer);
    }

    public TrackerTargetState position(long j) {
        return (TrackerTargetState) super.position(j);
    }

    public TrackerTargetState getPointer(long j) {
        return new TrackerTargetState((Pointer) this).position(this.position + j);
    }
}
