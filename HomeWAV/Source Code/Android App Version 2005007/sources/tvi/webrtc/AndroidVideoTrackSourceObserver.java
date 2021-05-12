package tvi.webrtc;

import tvi.webrtc.VideoCapturer;
import tvi.webrtc.VideoFrame;

@JNINamespace("webrtc::jni")
class AndroidVideoTrackSourceObserver implements VideoCapturer.CapturerObserver {
    private final long nativeSource;

    private static native void nativeCapturerStarted(long j, boolean z);

    private static native void nativeCapturerStopped(long j);

    private static native void nativeOnByteBufferFrameCaptured(long j, byte[] bArr, int i, int i2, int i3, int i4, long j2);

    private static native void nativeOnFrameCaptured(long j, int i, int i2, int i3, long j2, VideoFrame.Buffer buffer);

    private static native void nativeOnTextureFrameCaptured(long j, int i, int i2, int i3, float[] fArr, int i4, long j2);

    public AndroidVideoTrackSourceObserver(long j) {
        this.nativeSource = j;
    }

    public void onCapturerStarted(boolean z) {
        nativeCapturerStarted(this.nativeSource, z);
    }

    public void onCapturerStopped() {
        nativeCapturerStopped(this.nativeSource);
    }

    public void onByteBufferFrameCaptured(byte[] bArr, int i, int i2, int i3, long j) {
        nativeOnByteBufferFrameCaptured(this.nativeSource, bArr, bArr.length, i, i2, i3, j);
    }

    public void onTextureFrameCaptured(int i, int i2, int i3, float[] fArr, int i4, long j) {
        nativeOnTextureFrameCaptured(this.nativeSource, i, i2, i3, fArr, i4, j);
    }

    public void onFrameCaptured(VideoFrame videoFrame) {
        nativeOnFrameCaptured(this.nativeSource, videoFrame.getBuffer().getWidth(), videoFrame.getBuffer().getHeight(), videoFrame.getRotation(), videoFrame.getTimestampNs(), videoFrame.getBuffer());
    }
}
