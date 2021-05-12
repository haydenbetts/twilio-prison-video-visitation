package tvi.webrtc;

@JNINamespace("webrtc::jni")
class VP9Encoder extends WrappedNativeVideoEncoder {
    static native long nativeCreateEncoder();

    static native boolean nativeIsSupported();

    /* access modifiers changed from: package-private */
    public boolean isSoftwareEncoder() {
        return true;
    }

    VP9Encoder() {
    }

    /* access modifiers changed from: package-private */
    public long createNativeEncoder() {
        return nativeCreateEncoder();
    }
}
