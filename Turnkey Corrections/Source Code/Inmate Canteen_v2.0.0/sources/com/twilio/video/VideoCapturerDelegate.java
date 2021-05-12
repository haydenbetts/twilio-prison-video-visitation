package com.twilio.video;

import android.content.Context;
import com.twilio.video.VideoCapturer;
import java.util.List;
import tvi.webrtc.SurfaceTextureHelper;
import tvi.webrtc.VideoCapturer;
import tvi.webrtc.VideoFrame;

final class VideoCapturerDelegate implements VideoCapturer {
    private VideoCapturer.Listener listenerAdapter;
    private final VideoCapturer videoCapturer;
    private VideoPixelFormat videoPixelFormat;

    public void changeCaptureFormat(int i, int i2, int i3) {
    }

    public void dispose() {
    }

    VideoCapturerDelegate(VideoCapturer videoCapturer2) {
        this.videoCapturer = videoCapturer2;
    }

    public List<VideoFormat> getSupportedFormats() {
        return this.videoCapturer.getSupportedFormats();
    }

    public void initialize(SurfaceTextureHelper surfaceTextureHelper, Context context, VideoCapturer.CapturerObserver capturerObserver) {
        this.listenerAdapter = new VideoCapturerListenerAdapter(capturerObserver);
        VideoCapturer videoCapturer2 = this.videoCapturer;
        if (videoCapturer2 instanceof CameraCapturer) {
            ((CameraCapturer) videoCapturer2).setSurfaceTextureHelper(surfaceTextureHelper);
        } else if (videoCapturer2 instanceof ScreenCapturer) {
            ((ScreenCapturer) videoCapturer2).setSurfaceTextureHelper(surfaceTextureHelper);
        } else if (videoCapturer2 instanceof Camera2Capturer) {
            ((Camera2Capturer) videoCapturer2).setSurfaceTextureHelper(surfaceTextureHelper);
        }
    }

    public void startCapture(int i, int i2, int i3) {
        this.videoCapturer.startCapture(new VideoFormat(new VideoDimensions(i, i2), i3, this.videoPixelFormat), this.listenerAdapter);
    }

    public void stopCapture() throws InterruptedException {
        this.videoCapturer.stopCapture();
    }

    public boolean isScreencast() {
        return this.videoCapturer.isScreencast();
    }

    private void setVideoPixelFormat(VideoPixelFormat videoPixelFormat2) {
        this.videoPixelFormat = videoPixelFormat2;
    }

    static class NativeObserver implements VideoCapturer.CapturerObserver {
        private final long nativeCapturer;

        private native void nativeCapturerStarted(long j, boolean z);

        private native void nativeOnByteBufferFrameCaptured(long j, byte[] bArr, int i, int i2, int i3, int i4, long j2);

        private native void nativeOnFrameCaptured(long j, int i, int i2, long j2, int i3, VideoFrame.Buffer buffer);

        private native void nativeOnTextureFrameCaptured(long j, int i, int i2, int i3, float[] fArr, int i4, long j2);

        public void onCapturerStopped() {
        }

        public NativeObserver(long j) {
            this.nativeCapturer = j;
        }

        public void onCapturerStarted(boolean z) {
            nativeCapturerStarted(this.nativeCapturer, z);
        }

        public void onByteBufferFrameCaptured(byte[] bArr, int i, int i2, int i3, long j) {
            byte[] bArr2 = bArr;
            nativeOnByteBufferFrameCaptured(this.nativeCapturer, bArr2, bArr2.length, i, i2, i3, j);
        }

        public void onTextureFrameCaptured(int i, int i2, int i3, float[] fArr, int i4, long j) {
            nativeOnTextureFrameCaptured(this.nativeCapturer, i, i2, i3, fArr, i4, j);
        }

        public void onFrameCaptured(VideoFrame videoFrame) {
            VideoFrame.Buffer buffer = videoFrame.getBuffer();
            nativeOnFrameCaptured(this.nativeCapturer, buffer.getWidth(), buffer.getHeight(), videoFrame.getTimestampNs(), videoFrame.getRotation(), buffer);
        }
    }
}
