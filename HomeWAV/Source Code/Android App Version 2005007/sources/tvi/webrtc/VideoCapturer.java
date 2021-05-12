package tvi.webrtc;

import android.content.Context;

public interface VideoCapturer {
    void changeCaptureFormat(int i, int i2, int i3);

    void dispose();

    void initialize(SurfaceTextureHelper surfaceTextureHelper, Context context, CapturerObserver capturerObserver);

    boolean isScreencast();

    void startCapture(int i, int i2, int i3);

    void stopCapture() throws InterruptedException;

    public interface CapturerObserver {
        @Deprecated
        void onByteBufferFrameCaptured(byte[] bArr, int i, int i2, int i3, long j);

        void onCapturerStarted(boolean z);

        void onCapturerStopped();

        void onFrameCaptured(VideoFrame videoFrame);

        @Deprecated
        void onTextureFrameCaptured(int i, int i2, int i3, float[] fArr, int i4, long j);

        /* renamed from: tvi.webrtc.VideoCapturer$CapturerObserver$-CC  reason: invalid class name */
        public final /* synthetic */ class CC {
            @Deprecated
            public static void $default$onByteBufferFrameCaptured(CapturerObserver _this, byte[] bArr, int i, int i2, int i3, long j) {
                throw new UnsupportedOperationException("Deprecated and not implemented.");
            }

            @Deprecated
            public static void $default$onTextureFrameCaptured(CapturerObserver _this, int i, int i2, int i3, float[] fArr, int i4, long j) {
                throw new UnsupportedOperationException("Deprecated and not implemented.");
            }
        }
    }
}
