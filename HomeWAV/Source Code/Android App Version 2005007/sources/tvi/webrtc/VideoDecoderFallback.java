package tvi.webrtc;

import tvi.webrtc.VideoDecoder;

@JNINamespace("webrtc::jni")
public class VideoDecoderFallback extends WrappedNativeVideoDecoder {
    private final VideoDecoder fallback;
    private final VideoDecoder primary;

    private static native long nativeCreateDecoder(VideoDecoder videoDecoder, VideoDecoder videoDecoder2);

    public /* bridge */ /* synthetic */ VideoCodecStatus decode(EncodedImage encodedImage, VideoDecoder.DecodeInfo decodeInfo) {
        return super.decode(encodedImage, decodeInfo);
    }

    public /* bridge */ /* synthetic */ String getImplementationName() {
        return super.getImplementationName();
    }

    public /* bridge */ /* synthetic */ boolean getPrefersLateDecoding() {
        return super.getPrefersLateDecoding();
    }

    public /* bridge */ /* synthetic */ VideoCodecStatus initDecode(VideoDecoder.Settings settings, VideoDecoder.Callback callback) {
        return super.initDecode(settings, callback);
    }

    public /* bridge */ /* synthetic */ VideoCodecStatus release() {
        return super.release();
    }

    public VideoDecoderFallback(VideoDecoder videoDecoder, VideoDecoder videoDecoder2) {
        this.fallback = videoDecoder;
        this.primary = videoDecoder2;
    }

    /* access modifiers changed from: package-private */
    public long createNativeDecoder() {
        return nativeCreateDecoder(this.fallback, this.primary);
    }
}
