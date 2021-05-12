package com.twilio.video;

public class DataTrackOptions {
    public static final DataTrackOptions DEFAULT_DATA_TRACK_OPTIONS = new Builder().ordered(true).maxPacketLifeTime(-1).maxRetransmits(-1).build();
    public static final int DEFAULT_MAX_PACKET_LIFE_TIME = -1;
    public static final int DEFAULT_MAX_RETRANSMITS = -1;
    final int maxPacketLifeTime;
    final int maxRetransmits;
    final String name;
    final boolean ordered;

    private DataTrackOptions(Builder builder) {
        this.ordered = builder.ordered;
        this.maxPacketLifeTime = builder.maxPacketLifeTime;
        this.maxRetransmits = builder.maxRetransmits;
        this.name = builder.name;
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public int maxPacketLifeTime = -1;
        /* access modifiers changed from: private */
        public int maxRetransmits = -1;
        /* access modifiers changed from: private */
        public String name = null;
        /* access modifiers changed from: private */
        public boolean ordered = true;

        public Builder ordered(boolean z) {
            this.ordered = z;
            return this;
        }

        public Builder maxPacketLifeTime(int i) {
            this.maxPacketLifeTime = i;
            return this;
        }

        public Builder maxRetransmits(int i) {
            this.maxRetransmits = i;
            return this;
        }

        public Builder name(String str) {
            this.name = str;
            return this;
        }

        public DataTrackOptions build() {
            boolean z = true;
            Preconditions.checkArgument(this.maxPacketLifeTime >= -1);
            Preconditions.checkArgument(this.maxPacketLifeTime <= 65535);
            Preconditions.checkArgument(this.maxRetransmits >= -1);
            Preconditions.checkArgument(this.maxRetransmits <= 65535);
            if (!(this.maxRetransmits == -1 || this.maxPacketLifeTime == -1)) {
                z = false;
            }
            Preconditions.checkState(z);
            return new DataTrackOptions(this);
        }
    }
}
