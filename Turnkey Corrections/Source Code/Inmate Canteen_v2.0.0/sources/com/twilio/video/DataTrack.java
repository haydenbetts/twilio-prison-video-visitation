package com.twilio.video;

import androidx.annotation.NonNull;

public abstract class DataTrack implements Track {
    private final boolean enabled;
    private final int maxPacketLifeTime;
    private final int maxRetransmits;
    private final String name;
    private final boolean ordered;
    private final boolean reliable;

    protected DataTrack(boolean z, boolean z2, boolean z3, int i, int i2, @NonNull String str) {
        this.enabled = z;
        this.ordered = z2;
        this.reliable = z3;
        this.maxPacketLifeTime = i;
        this.maxRetransmits = i2;
        this.name = str;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    @NonNull
    public String getName() {
        return this.name;
    }

    public boolean isOrdered() {
        return this.ordered;
    }

    public boolean isReliable() {
        return this.reliable;
    }

    public int getMaxPacketLifeTime() {
        return this.maxPacketLifeTime;
    }

    public int getMaxRetransmits() {
        return this.maxRetransmits;
    }
}
