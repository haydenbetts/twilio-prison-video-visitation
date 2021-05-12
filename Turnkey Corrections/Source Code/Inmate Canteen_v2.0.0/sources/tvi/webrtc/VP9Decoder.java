package tvi.webrtc;

@JNINamespace("webrtc::jni")
class VP9Decoder extends WrappedNativeVideoDecoder {
    static native long nativeCreateDecoder();

    static native boolean nativeIsSupported();

    VP9Decoder() {
    }

    /* access modifiers changed from: package-private */
    public long createNativeDecoder() {
        return nativeCreateDecoder();
    }
}
