package tvi.webrtc;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import tvi.webrtc.MediaStreamTrack;

public class RtpParameters {
    public final List<Codec> codecs;
    public final List<Encoding> encodings;

    public static class Encoding {
        public boolean active = true;
        @Nullable
        public Integer maxBitrateBps;
        public Long ssrc;

        Encoding(boolean z, Integer num, Long l) {
            this.active = z;
            this.maxBitrateBps = num;
            this.ssrc = l;
        }

        /* access modifiers changed from: package-private */
        public boolean getActive() {
            return this.active;
        }

        /* access modifiers changed from: package-private */
        @Nullable
        public Integer getMaxBitrateBps() {
            return this.maxBitrateBps;
        }

        /* access modifiers changed from: package-private */
        public Long getSsrc() {
            return this.ssrc;
        }
    }

    public static class Codec {
        public Integer clockRate;
        MediaStreamTrack.MediaType kind;
        public String name;
        public Integer numChannels;
        public int payloadType;

        Codec(int i, String str, MediaStreamTrack.MediaType mediaType, Integer num, Integer num2) {
            this.payloadType = i;
            this.name = str;
            this.kind = mediaType;
            this.clockRate = num;
            this.numChannels = num2;
        }

        /* access modifiers changed from: package-private */
        public int getPayloadType() {
            return this.payloadType;
        }

        /* access modifiers changed from: package-private */
        public String getName() {
            return this.name;
        }

        /* access modifiers changed from: package-private */
        public MediaStreamTrack.MediaType getKind() {
            return this.kind;
        }

        /* access modifiers changed from: package-private */
        public Integer getClockRate() {
            return this.clockRate;
        }

        /* access modifiers changed from: package-private */
        public Integer getNumChannels() {
            return this.numChannels;
        }
    }

    public RtpParameters() {
        this.encodings = new ArrayList();
        this.codecs = new ArrayList();
    }

    RtpParameters(List<Encoding> list, List<Codec> list2) {
        this.encodings = list;
        this.codecs = list2;
    }

    /* access modifiers changed from: package-private */
    public List<Encoding> getEncodings() {
        return this.encodings;
    }

    /* access modifiers changed from: package-private */
    public List<Codec> getCodecs() {
        return this.codecs;
    }
}
