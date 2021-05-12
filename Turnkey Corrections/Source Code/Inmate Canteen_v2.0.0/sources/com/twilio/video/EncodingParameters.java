package com.twilio.video;

import androidx.annotation.NonNull;

public class EncodingParameters {
    public final int maxAudioBitrate;
    public final int maxVideoBitrate;

    public EncodingParameters(int i, int i2) {
        this.maxAudioBitrate = i;
        this.maxVideoBitrate = i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        EncodingParameters encodingParameters = (EncodingParameters) obj;
        if (this.maxAudioBitrate == encodingParameters.maxAudioBitrate && this.maxVideoBitrate == encodingParameters.maxVideoBitrate) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (this.maxAudioBitrate * 31) + this.maxVideoBitrate;
    }

    @NonNull
    public String toString() {
        return "EncodingParameters{maxAudioBitrate=" + this.maxAudioBitrate + ", maxVideoBitrate=" + this.maxVideoBitrate + '}';
    }
}
