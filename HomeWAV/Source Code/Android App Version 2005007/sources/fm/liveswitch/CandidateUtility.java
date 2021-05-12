package fm.liveswitch;

import com.stripe.android.model.SourceRedirect;

public class CandidateUtility {
    public static CandidatePairState candidatePairStateFromString(String str) {
        if (str.equals("new")) {
            return CandidatePairState.New;
        }
        if (str.equals("waiting")) {
            return CandidatePairState.Waiting;
        }
        if (str.equals("inProgress")) {
            return CandidatePairState.InProgress;
        }
        if (str.equals(SourceRedirect.SUCCEEDED)) {
            return CandidatePairState.Succeeded;
        }
        if (str.equals("failed")) {
            return CandidatePairState.Failed;
        }
        if (str.equals("closed")) {
            return CandidatePairState.Closed;
        }
        if (str.equals("connectivityLost")) {
            return CandidatePairState.ConnectivityLost;
        }
        return CandidatePairState.New;
    }

    public static String candidatePairStateToString(CandidatePairState candidatePairState) {
        if (candidatePairState == CandidatePairState.New) {
            return "new";
        }
        if (candidatePairState == CandidatePairState.Waiting) {
            return "waiting";
        }
        if (candidatePairState == CandidatePairState.InProgress) {
            return "inProgress";
        }
        if (candidatePairState == CandidatePairState.Succeeded) {
            return SourceRedirect.SUCCEEDED;
        }
        if (candidatePairState == CandidatePairState.Failed) {
            return "failed";
        }
        if (candidatePairState == CandidatePairState.Closed) {
            return "closed";
        }
        if (candidatePairState == CandidatePairState.ConnectivityLost) {
            return "connectivityLost";
        }
        return null;
    }

    public static ProtocolType protocolTypeFromString(String str) {
        if (str.equals("udp")) {
            return ProtocolType.Udp;
        }
        if (str.equals("tcp")) {
            return ProtocolType.Tcp;
        }
        if (str.equals("tls")) {
            return ProtocolType.Tls;
        }
        return ProtocolType.Unknown;
    }

    public static String protocolTypeToString(ProtocolType protocolType) {
        if (protocolType == ProtocolType.Udp) {
            return "udp";
        }
        if (protocolType == ProtocolType.Tcp) {
            return "tcp";
        }
        return protocolType == ProtocolType.Tls ? "tls" : "unknown";
    }

    public static CandidateType typeFromString(String str) {
        if (str.equals("host")) {
            return CandidateType.Host;
        }
        if (str.equals("srflx")) {
            return CandidateType.ServerReflexive;
        }
        if (str.equals("relay")) {
            return CandidateType.Relayed;
        }
        if (str.equals("prflx")) {
            return CandidateType.PeerReflexive;
        }
        return CandidateType.Unknown;
    }

    public static String typeToString(CandidateType candidateType) {
        if (candidateType == CandidateType.Host) {
            return "host";
        }
        if (candidateType == CandidateType.ServerReflexive) {
            return "srflx";
        }
        if (candidateType == CandidateType.Relayed) {
            return "relay";
        }
        return candidateType == CandidateType.PeerReflexive ? "prflx" : "unknown";
    }
}
