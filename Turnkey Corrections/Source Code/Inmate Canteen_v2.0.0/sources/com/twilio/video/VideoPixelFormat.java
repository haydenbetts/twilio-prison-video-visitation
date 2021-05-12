package com.twilio.video;

public enum VideoPixelFormat {
    NV21,
    RGBA_8888;
    
    private int value;

    private native int nativeGetValue(String str);

    /* access modifiers changed from: package-private */
    public int getValue() {
        if (isUnset()) {
            this.value = nativeGetValue(name());
        }
        return this.value;
    }

    /* access modifiers changed from: package-private */
    public boolean isUnset() {
        return this.value == Integer.MIN_VALUE;
    }
}
