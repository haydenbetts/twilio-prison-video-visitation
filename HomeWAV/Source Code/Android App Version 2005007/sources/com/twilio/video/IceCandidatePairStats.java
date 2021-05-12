package com.twilio.video;

public class IceCandidatePairStats {
    public final boolean activeCandidatePair;
    public final double availableIncomingBitrate;
    public final double availableOutgoingBitrate;
    public final long bytesReceived;
    public final long bytesSent;
    public final long consentRequestsReceived;
    public final long consentRequestsSent;
    public final long consentResponsesReceived;
    public final long consentResponsesSent;
    public final double currentRoundTripTime;
    public final String localCandidateId;
    public final String localCandidateIp;
    public final boolean nominated;
    public final long priority;
    public final boolean readable;
    public final String relayProtocol;
    public final String remoteCandidateId;
    public final String remoteCandidateIp;
    public final long requestsReceived;
    public final long requestsSent;
    public final long responsesReceived;
    public final long retransmissionsReceived;
    public final long retransmissionsSent;
    public final IceCandidatePairState state;
    public final double totalRoundTripTime;
    public final String transportId;
    public final boolean writeable;

    IceCandidatePairStats(String str, String str2, String str3, IceCandidatePairState iceCandidatePairState, String str4, String str5, long j, boolean z, boolean z2, boolean z3, long j2, long j3, double d, double d2, double d3, double d4, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, boolean z4, String str6) {
        this.transportId = str;
        this.localCandidateId = str2;
        this.remoteCandidateId = str3;
        this.state = iceCandidatePairState;
        this.localCandidateIp = str4;
        this.remoteCandidateIp = str5;
        this.priority = j;
        this.nominated = z;
        this.writeable = z2;
        this.readable = z3;
        this.bytesSent = j2;
        this.bytesReceived = j3;
        this.totalRoundTripTime = d;
        this.currentRoundTripTime = d2;
        this.availableOutgoingBitrate = d3;
        this.availableIncomingBitrate = d4;
        this.requestsReceived = j4;
        this.requestsSent = j5;
        this.responsesReceived = j6;
        this.retransmissionsReceived = j7;
        this.retransmissionsSent = j8;
        this.consentRequestsReceived = j9;
        this.consentRequestsSent = j10;
        this.consentResponsesReceived = j11;
        this.consentResponsesSent = j12;
        this.activeCandidatePair = z4;
        this.relayProtocol = str6;
    }
}
