package com.paypal.android.sdk.onetouch.core.fpti;

import com.microsoft.appcenter.Constants;

public enum TrackingPoint {
    SwitchToBrowser("switchaway", "browser"),
    SwitchToWallet("switchaway", "wallet"),
    Cancel("switchback", "cancel"),
    Return("switchback", "return"),
    Error("switchback", "cancel", true);
    
    private final String mC;
    private final String mD;
    private final boolean mHasError;

    private TrackingPoint(String str, String str2, boolean z) {
        this.mC = str;
        this.mD = str2;
        this.mHasError = z;
    }

    private TrackingPoint(String str, String str2) {
        this(r7, r8, str, str2, false);
    }

    public String getCd() {
        return this.mC + Constants.COMMON_SCHEMA_PREFIX_SEPARATOR + this.mD;
    }

    public boolean hasError() {
        return this.mHasError;
    }
}
