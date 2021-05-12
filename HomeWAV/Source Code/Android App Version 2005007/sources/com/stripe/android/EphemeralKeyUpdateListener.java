package com.stripe.android;

public interface EphemeralKeyUpdateListener {
    void onKeyUpdate(String str);

    void onKeyUpdateFailure(int i, String str);
}
