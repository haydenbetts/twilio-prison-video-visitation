package fm.liveswitch;

class RtpVideoTransport extends RtpTransport<VideoFrame, VideoBuffer, VideoBufferCollection, VideoFormat, VideoFormatCollection> {
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: fm.liveswitch.PacketizedVideoBuffer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: fm.liveswitch.VideoBuffer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: fm.liveswitch.PacketizedVideoBuffer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: fm.liveswitch.PacketizedVideoBuffer} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public fm.liveswitch.VideoFrame createFrame(fm.liveswitch.RtpPacketHeader r9, fm.liveswitch.DataBuffer r10, fm.liveswitch.VideoFormat r11) {
        /*
            r8 = this;
            fm.liveswitch.VideoFrame r0 = new fm.liveswitch.VideoFrame
            boolean r1 = r11.getIsPacketized()
            if (r1 == 0) goto L_0x0014
            fm.liveswitch.PacketizedVideoBuffer r1 = new fm.liveswitch.PacketizedVideoBuffer
            r3 = -1
            r4 = -1
            r2 = r1
            r5 = r10
            r6 = r11
            r7 = r9
            r2.<init>((int) r3, (int) r4, (fm.liveswitch.DataBuffer) r5, (fm.liveswitch.VideoFormat) r6, (fm.liveswitch.RtpPacketHeader) r7)
            goto L_0x001a
        L_0x0014:
            fm.liveswitch.VideoBuffer r1 = new fm.liveswitch.VideoBuffer
            r9 = -1
            r1.<init>((int) r9, (int) r9, (fm.liveswitch.DataBuffer) r10, (fm.liveswitch.VideoFormat) r11)
        L_0x001a:
            r0.<init>((fm.liveswitch.VideoBuffer) r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.RtpVideoTransport.createFrame(fm.liveswitch.RtpPacketHeader, fm.liveswitch.DataBuffer, fm.liveswitch.VideoFormat):fm.liveswitch.VideoFrame");
    }

    public VideoFrame[] createFrameArray(int i) {
        return new VideoFrame[i];
    }

    public RtpVideoTransport(Object obj, SimulcastMode simulcastMode, NackConfig nackConfig, RedFecConfig redFecConfig, JitterConfig jitterConfig, boolean z, HexDump hexDump, Transport transport, Transport transport2) {
        super(obj, StreamType.Video, simulcastMode, nackConfig, redFecConfig, jitterConfig, z, hexDump, transport, transport2);
    }
}
