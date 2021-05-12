package tvi.webrtc;

import tvi.webrtc.VideoDecoder;

abstract class WrappedNativeVideoDecoder implements VideoDecoder {
    /* access modifiers changed from: package-private */
    public abstract long createNativeDecoder();

    WrappedNativeVideoDecoder() {
    }

    public VideoCodecStatus initDecode(VideoDecoder.Settings settings, VideoDecoder.Callback callback) {
        throw new UnsupportedOperationException("Not implemented.");
    }

    public VideoCodecStatus release() {
        throw new UnsupportedOperationException("Not implemented.");
    }

    public VideoCodecStatus decode(EncodedImage encodedImage, VideoDecoder.DecodeInfo decodeInfo) {
        throw new UnsupportedOperationException("Not implemented.");
    }

    public boolean getPrefersLateDecoding() {
        throw new UnsupportedOperationException("Not implemented.");
    }

    public String getImplementationName() {
        throw new UnsupportedOperationException("Not implemented.");
    }

    static boolean isInstanceOf(VideoDecoder videoDecoder) {
        return videoDecoder instanceof WrappedNativeVideoDecoder;
    }
}
