package com.twilio.video;

import androidx.annotation.NonNull;

public class NetworkQualityConfiguration {
    public final NetworkQualityVerbosity local;
    public final NetworkQualityVerbosity remote;

    public NetworkQualityConfiguration() {
        this(NetworkQualityVerbosity.NETWORK_QUALITY_VERBOSITY_MINIMAL, NetworkQualityVerbosity.NETWORK_QUALITY_VERBOSITY_NONE);
    }

    public NetworkQualityConfiguration(@NonNull NetworkQualityVerbosity networkQualityVerbosity, @NonNull NetworkQualityVerbosity networkQualityVerbosity2) {
        Preconditions.checkNotNull(networkQualityVerbosity, "Local verbosity cannot be null");
        Preconditions.checkArgument(networkQualityVerbosity != NetworkQualityVerbosity.NETWORK_QUALITY_VERBOSITY_NONE, "Local verbosity cannot be 'NetworkQualityVerbosity.NETWORK_QUALITY_VERBOSITY_NONE'");
        Preconditions.checkNotNull(networkQualityVerbosity2, "Remote verbosity cannot be null");
        this.local = networkQualityVerbosity;
        this.remote = networkQualityVerbosity2;
    }

    @NonNull
    public String toString() {
        return "NetworkQualityConfiguration{local=" + this.local + ", remote=" + this.remote + '}';
    }
}
