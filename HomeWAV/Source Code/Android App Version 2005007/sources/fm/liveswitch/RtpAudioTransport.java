package fm.liveswitch;

class RtpAudioTransport extends RtpTransport<AudioFrame, AudioBuffer, AudioBufferCollection, AudioFormat, AudioFormatCollection> {
    public AudioFrame createFrame(RtpPacketHeader rtpPacketHeader, DataBuffer dataBuffer, AudioFormat audioFormat) {
        dataBuffer.setLittleEndian(audioFormat.getLittleEndian());
        return new AudioFrame(-1, audioFormat.getIsPacketized() ? new PacketizedAudioBuffer(dataBuffer, audioFormat, rtpPacketHeader) : new AudioBuffer(dataBuffer, audioFormat));
    }

    public AudioFrame[] createFrameArray(int i) {
        return new AudioFrame[i];
    }

    public RtpAudioTransport(Object obj, SimulcastMode simulcastMode, NackConfig nackConfig, RedFecConfig redFecConfig, JitterConfig jitterConfig, boolean z, HexDump hexDump, Transport transport, Transport transport2) {
        super(obj, StreamType.Audio, simulcastMode, nackConfig, redFecConfig, jitterConfig, z, hexDump, transport, transport2);
    }
}
