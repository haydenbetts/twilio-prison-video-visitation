package tvi.webrtc;

import tvi.webrtc.VideoEncoder;

@JNINamespace("webrtc::jni")
public class VideoEncoderFallback extends WrappedNativeVideoEncoder {
    private final VideoEncoder fallback;
    private final VideoEncoder primary;

    private static native long nativeCreateEncoder(VideoEncoder videoEncoder, VideoEncoder videoEncoder2);

    public /* bridge */ /* synthetic */ VideoCodecStatus encode(VideoFrame videoFrame, VideoEncoder.EncodeInfo encodeInfo) {
        return super.encode(videoFrame, encodeInfo);
    }

    public /* bridge */ /* synthetic */ String getImplementationName() {
        return super.getImplementationName();
    }

    public /* bridge */ /* synthetic */ VideoEncoder.ScalingSettings getScalingSettings() {
        return super.getScalingSettings();
    }

    public /* bridge */ /* synthetic */ VideoCodecStatus initEncode(VideoEncoder.Settings settings, VideoEncoder.Callback callback) {
        return super.initEncode(settings, callback);
    }

    public /* bridge */ /* synthetic */ VideoCodecStatus release() {
        return super.release();
    }

    public /* bridge */ /* synthetic */ VideoCodecStatus setChannelParameters(short s, long j) {
        return super.setChannelParameters(s, j);
    }

    public /* bridge */ /* synthetic */ VideoCodecStatus setRateAllocation(VideoEncoder.BitrateAllocation bitrateAllocation, int i) {
        return super.setRateAllocation(bitrateAllocation, i);
    }

    public VideoEncoderFallback(VideoEncoder videoEncoder, VideoEncoder videoEncoder2) {
        this.fallback = videoEncoder;
        this.primary = videoEncoder2;
    }

    /* access modifiers changed from: package-private */
    public long createNativeEncoder() {
        return nativeCreateEncoder(this.fallback, this.primary);
    }

    /* access modifiers changed from: package-private */
    public boolean isSoftwareEncoder() {
        return isWrappedSoftwareEncoder(this.primary);
    }
}
