package fm.liveswitch;

class ConnectionFactory {
    public McuConnection createMcuConnection(Object obj, String str, String str2, String str3, String str4, String str5, IFunction1<Message, Future<Message>> iFunction1, AudioStream audioStream, VideoStream videoStream, DataStream dataStream, String str6) {
        return new McuConnection(obj, str, str2, str3, str4, str5, iFunction1, audioStream, videoStream, dataStream, str6);
    }

    public PeerConnection createPeerConnection(Object obj, String str, String str2, String str3, String str4, String str5, IFunction1<Message, Future<Message>> iFunction1, PeerConnectionOffer peerConnectionOffer, AudioStream audioStream, VideoStream videoStream, DataStream dataStream) {
        return new PeerConnection(obj, str, str2, str3, str4, str5, iFunction1, peerConnectionOffer, audioStream, videoStream, dataStream);
    }

    public PeerConnection createPeerConnection(Object obj, String str, String str2, String str3, String str4, String str5, IFunction1<Message, Future<Message>> iFunction1, ClientInfo clientInfo, AudioStream audioStream, VideoStream videoStream, DataStream dataStream) {
        return new PeerConnection(obj, str, str2, str3, str4, str5, iFunction1, clientInfo, audioStream, videoStream, dataStream);
    }

    public SfuDownstreamConnection createSfuDownstreamConnection(Object obj, String str, String str2, String str3, String str4, String str5, IFunction1<Message, Future<Message>> iFunction1, ConnectionInfo connectionInfo, AudioStream audioStream, VideoStream videoStream, DataStream dataStream) {
        return new SfuDownstreamConnection(obj, str, str2, str3, str4, str5, iFunction1, connectionInfo, audioStream, videoStream, dataStream);
    }

    public SfuDownstreamConnection createSfuDownstreamConnection(Object obj, String str, String str2, String str3, String str4, String str5, IFunction1<Message, Future<Message>> iFunction1, String str6, AudioStream audioStream, VideoStream videoStream, DataStream dataStream) {
        return new SfuDownstreamConnection(obj, str, str2, str3, str4, str5, iFunction1, str6, audioStream, videoStream, dataStream);
    }

    public SfuUpstreamConnection createSfuUpstreamConnection(Object obj, String str, String str2, String str3, String str4, String str5, IFunction1<Message, Future<Message>> iFunction1, AudioStream audioStream, VideoStream videoStream, DataStream dataStream, String str6) {
        return new SfuUpstreamConnection(obj, str, str2, str3, str4, str5, iFunction1, audioStream, videoStream, dataStream, str6);
    }
}
