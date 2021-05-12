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

        @CalledByNative("Encoding")
        Encoding(boolean z, Integer num, Long l) {
            this.active = z;
            this.maxBitrateBps = num;
            this.ssrc = l;
        }

        /* access modifiers changed from: package-private */
        @CalledByNative("Encoding")
        public boolean getActive() {
            return this.active;
        }

        /* access modifiers changed from: package-private */
        @Nullable
        @CalledByNative("Encoding")
        public Integer getMaxBitrateBps() {
            return this.maxBitrateBps;
        }

        /* access modifiers changed from: package-private */
        @CalledByNative("Encoding")
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

        @CalledByNative("Codec")
        Codec(int i, String str, MediaStreamTrack.MediaType mediaType, Integer num, Integer num2) {
            this.payloadType = i;
            this.name = str;
            this.kind = mediaType;
            this.clockRate = num;
            this.numChannels = num2;
        }

        /* access modifiers changed from: package-private */
        @CalledByNative("Codec")
        public int getPayloadType() {
            return this.payloadType;
        }

        /* access modifiers changed from: package-private */
        @CalledByNative("Codec")
        public String getName() {
            return this.name;
        }

        /* access modifiers changed from: package-private */
        @CalledByNative("Codec")
        public MediaStreamTrack.MediaType getKind() {
            return this.kind;
        }

        /* access modifiers changed from: package-private */
        @CalledByNative("Codec")
        public Integer getClockRate() {
            return this.clockRate;
        }

        /* access modifiers changed from: package-private */
        @CalledByNative("Codec")
        public Integer getNumChannels() {
            return this.numChannels;
        }
    }

    public RtpParameters() {
        this.encodings = new ArrayList();
        this.codecs = new ArrayList();
    }

    @CalledByNative
    RtpParameters(List<Encoding> list, List<Codec> list2) {
        this.encodings = list;
        this.codecs = list2;
    }

    /* access modifiers changed from: package-private */
    @CalledByNative
    public List<Encoding> getEncodings() {
        return this.encodings;
    }

    /* access modifiers changed from: package-private */
    @CalledByNative
    public List<Codec> getCodecs() {
        return this.codecs;
    }
}
