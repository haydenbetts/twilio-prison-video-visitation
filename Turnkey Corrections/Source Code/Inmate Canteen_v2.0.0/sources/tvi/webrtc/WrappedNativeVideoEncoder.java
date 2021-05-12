package tvi.webrtc;

import tvi.webrtc.VideoEncoder;

abstract class WrappedNativeVideoEncoder implements VideoEncoder {
    /* access modifiers changed from: package-private */
    @CalledByNative
    public abstract long createNativeEncoder();

    /* access modifiers changed from: package-private */
    public abstract boolean isSoftwareEncoder();

    WrappedNativeVideoEncoder() {
    }

    public VideoCodecStatus initEncode(VideoEncoder.Settings settings, VideoEncoder.Callback callback) {
        throw new UnsupportedOperationException("Not implemented.");
    }

    public VideoCodecStatus release() {
        throw new UnsupportedOperationException("Not implemented.");
    }

    public VideoCodecStatus encode(VideoFrame videoFrame, VideoEncoder.EncodeInfo encodeInfo) {
        throw new UnsupportedOperationException("Not implemented.");
    }

    public VideoCodecStatus setChannelParameters(short s, long j) {
        throw new UnsupportedOperationException("Not implemented.");
    }

    public VideoCodecStatus setRateAllocation(VideoEncoder.BitrateAllocation bitrateAllocation, int i) {
        throw new UnsupportedOperationException("Not implemented.");
    }

    public VideoEncoder.ScalingSettings getScalingSettings() {
        throw new UnsupportedOperationException("Not implemented.");
    }

    public String getImplementationName() {
        throw new UnsupportedOperationException("Not implemented.");
    }

    @CalledByNative
    static boolean isWrappedSoftwareEncoder(VideoEncoder videoEncoder) {
        return (videoEncoder instanceof WrappedNativeVideoEncoder) && ((WrappedNativeVideoEncoder) videoEncoder).isSoftwareEncoder();
    }

    @CalledByNative
    static boolean isInstanceOf(VideoEncoder videoEncoder) {
        return videoEncoder instanceof WrappedNativeVideoEncoder;
    }
}
