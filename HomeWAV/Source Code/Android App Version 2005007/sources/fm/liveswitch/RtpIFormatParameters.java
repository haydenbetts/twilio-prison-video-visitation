package fm.liveswitch;

import fm.liveswitch.MediaFormat;

interface RtpIFormatParameters<TFormat extends MediaFormat<TFormat>> {
    TFormat getNegotiatedFormat(int i);

    TFormat[] getNegotiatedFormats();

    int getNegotiatedPayloadType(TFormat tformat);

    TFormat getRedFormat();

    TFormat getUlpFecFormat();

    boolean hasNegotiatedPayloadType(int i);

    void setNegotiatedFormat(int i, TFormat tformat);
}
