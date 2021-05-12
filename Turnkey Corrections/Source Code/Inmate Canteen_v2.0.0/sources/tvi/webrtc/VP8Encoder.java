package tvi.webrtc;

@JNINamespace("webrtc::jni")
class VP8Encoder extends WrappedNativeVideoEncoder {
    static native long nativeCreateEncoder();

    /* access modifiers changed from: package-private */
    public boolean isSoftwareEncoder() {
        return true;
    }

    VP8Encoder() {
    }

    /* access modifiers changed from: package-private */
    public long createNativeEncoder() {
        return nativeCreateEncoder();
    }
}
