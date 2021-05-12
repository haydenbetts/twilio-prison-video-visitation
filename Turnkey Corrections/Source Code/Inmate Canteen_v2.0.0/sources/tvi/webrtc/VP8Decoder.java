package tvi.webrtc;

@JNINamespace("webrtc::jni")
class VP8Decoder extends WrappedNativeVideoDecoder {
    static native long nativeCreateDecoder();

    VP8Decoder() {
    }

    /* access modifiers changed from: package-private */
    public long createNativeDecoder() {
        return nativeCreateDecoder();
    }
}
