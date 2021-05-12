package com.twilio.video;

import java.util.Set;

public class IceOptions {
    private final boolean abortOnIceServersTimeout;
    private final Set<IceServer> iceServers;
    private final long iceServersTimeout;
    private final IceTransportPolicy iceTransportPolicy;

    private IceOptions(Builder builder) {
        this.iceServers = builder.iceServers;
        this.iceTransportPolicy = builder.iceTransportPolicy;
        this.abortOnIceServersTimeout = builder.abortOnIceServersTimeout;
        this.iceServersTimeout = builder.iceServersTimeout;
    }

    public Set<IceServer> getIceServers() {
        return this.iceServers;
    }

    public IceTransportPolicy getIceTransportPolicy() {
        return this.iceTransportPolicy;
    }

    public boolean getAbortOnIceServersTimeout() {
        return this.abortOnIceServersTimeout;
    }

    /* access modifiers changed from: package-private */
    public IceServer[] getIceServersArray() {
        IceServer[] iceServerArr = new IceServer[0];
        Set<IceServer> set = this.iceServers;
        if (set == null || set.size() <= 0) {
            return iceServerArr;
        }
        Set<IceServer> set2 = this.iceServers;
        return (IceServer[]) set2.toArray(new IceServer[set2.size()]);
    }

    public long getIceServersTimeout() {
        return this.iceServersTimeout;
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public boolean abortOnIceServersTimeout = false;
        /* access modifiers changed from: private */
        public Set<IceServer> iceServers;
        /* access modifiers changed from: private */
        public long iceServersTimeout = TestUtils.THREE_SECONDS;
        /* access modifiers changed from: private */
        public IceTransportPolicy iceTransportPolicy = IceTransportPolicy.ALL;

        public Builder iceServers(Set<IceServer> set) {
            this.iceServers = set;
            return this;
        }

        public Builder iceTransportPolicy(IceTransportPolicy iceTransportPolicy2) {
            this.iceTransportPolicy = iceTransportPolicy2;
            return this;
        }

        public Builder abortOnIceServersTimeout(boolean z) {
            this.abortOnIceServersTimeout = z;
            return this;
        }

        public Builder iceServersTimeout(long j) {
            this.iceServersTimeout = j;
            return this;
        }

        public IceOptions build() {
            return new IceOptions(this);
        }
    }
}
