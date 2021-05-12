package com.paypal.android.sdk.onetouch.core.enums;

import androidx.exifinterface.media.ExifInterface;

public enum Protocol {
    v0("0.0"),
    v1("1.0"),
    v2("2.0"),
    v3("3.0");
    
    private final String mVersion;

    private Protocol(String str) {
        this.mVersion = str;
    }

    public String getVersion() {
        return this.mVersion;
    }

    public static Protocol getProtocol(String str) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case 48:
                if (str.equals("0")) {
                    c = 0;
                    break;
                }
                break;
            case 49:
                if (str.equals("1")) {
                    c = 1;
                    break;
                }
                break;
            case 50:
                if (str.equals("2")) {
                    c = 2;
                    break;
                }
                break;
            case 51:
                if (str.equals(ExifInterface.GPS_MEASUREMENT_3D)) {
                    c = 3;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return v0;
            case 1:
                return v1;
            case 2:
                return v2;
            case 3:
                return v3;
            default:
                throw new IllegalArgumentException("invalid protocol");
        }
    }
}
