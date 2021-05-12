package tvi.webrtc;

@JNINamespace("webrtc::jni")
public class VideoSource extends MediaSource {
    private static native void nativeAdaptOutputFormat(long j, int i, int i2, int i3);

    public VideoSource(long j) {
        super(j);
    }

    public void adaptOutputFormat(int i, int i2, int i3) {
        nativeAdaptOutputFormat(this.nativeSource, i, i2, i3);
    }
}
