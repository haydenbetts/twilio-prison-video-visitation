package com.twilio.video;

import androidx.annotation.Nullable;
import java.nio.ByteBuffer;
import tvi.webrtc.VideoRenderer;

public class I420Frame {
    public final int height;
    public int rotationDegree;
    public final float[] samplingMatrix;
    public int textureId;
    final VideoRenderer.I420Frame webRtcI420Frame;
    public final int width;
    @Nullable
    public final ByteBuffer[] yuvPlanes;
    @Nullable
    public final int[] yuvStrides;

    I420Frame(VideoRenderer.I420Frame i420Frame) {
        this.width = i420Frame.width;
        this.height = i420Frame.height;
        this.yuvStrides = i420Frame.yuvStrides;
        this.yuvPlanes = i420Frame.yuvPlanes;
        this.rotationDegree = i420Frame.rotationDegree;
        this.webRtcI420Frame = i420Frame;
        this.textureId = i420Frame.textureId;
        this.samplingMatrix = i420Frame.samplingMatrix;
        if (this.rotationDegree % 90 != 0) {
            throw new IllegalArgumentException("Rotation degree not multiple of 90: " + this.rotationDegree);
        }
    }

    public int rotatedWidth() {
        return this.rotationDegree % 180 == 0 ? this.width : this.height;
    }

    public int rotatedHeight() {
        return this.rotationDegree % 180 == 0 ? this.height : this.width;
    }

    public synchronized void release() {
        VideoRenderer.renderFrameDone(this.webRtcI420Frame);
    }

    public String toString() {
        return this.width + "x" + this.height + ":" + this.yuvStrides[0] + ":" + this.yuvStrides[1] + ":" + this.yuvStrides[2];
    }
}
