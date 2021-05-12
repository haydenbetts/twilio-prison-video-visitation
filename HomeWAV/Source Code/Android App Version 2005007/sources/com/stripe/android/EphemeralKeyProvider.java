package com.stripe.android;

public interface EphemeralKeyProvider {
    void createEphemeralKey(String str, EphemeralKeyUpdateListener ephemeralKeyUpdateListener);
}
