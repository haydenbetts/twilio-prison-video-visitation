package fm.liveswitch;

import fm.liveswitch.sdp.ice.CandidateAttribute;
import fm.liveswitch.sdp.ice.CandidateType;
import java.util.ArrayList;

public class IceServerTestResult {
    private Candidate[] _candidates;

    public Candidate[] getCandidates() {
        return this._candidates;
    }

    private Candidate[] getCandidates(String str) {
        ArrayList arrayList = new ArrayList();
        for (Candidate candidate : getCandidates()) {
            if (Global.equals(candidate.getSdpCandidateAttribute().getCandidateType(), str)) {
                arrayList.add(candidate);
            }
        }
        return (Candidate[]) arrayList.toArray(new Candidate[0]);
    }

    public Candidate[] getHostCandidates() {
        return getCandidates(CandidateType.getHost());
    }

    public Candidate[] getPeerReflexiveCandidates() {
        return getCandidates(CandidateType.getPeerReflexive());
    }

    public Candidate[] getRelayedCandidates() {
        return getCandidates(CandidateType.getRelayed());
    }

    public ServerAddress[] getServerAddresses(int i) {
        return getServerAddresses(i, (String) null);
    }

    public ServerAddress[] getServerAddresses(int i, String str) {
        ArrayList arrayList = new ArrayList();
        Candidate[] serverReflexiveCandidates = getServerReflexiveCandidates();
        Candidate[] hostCandidates = getHostCandidates();
        if (ArrayExtensions.getLength((Object[]) serverReflexiveCandidates) > 0) {
            for (Candidate sdpCandidateAttribute : serverReflexiveCandidates) {
                CandidateAttribute sdpCandidateAttribute2 = sdpCandidateAttribute.getSdpCandidateAttribute();
                arrayList.add(new ServerAddress(sdpCandidateAttribute2.getRelatedAddress(), i, sdpCandidateAttribute2.getConnectionAddress()));
            }
        } else if (!StringExtensions.isNullOrEmpty(str)) {
            for (Candidate sdpCandidateAttribute3 : hostCandidates) {
                arrayList.add(new ServerAddress(sdpCandidateAttribute3.getSdpCandidateAttribute().getConnectionAddress(), i, str));
            }
        } else {
            for (Candidate sdpCandidateAttribute4 : hostCandidates) {
                String connectionAddress = sdpCandidateAttribute4.getSdpCandidateAttribute().getConnectionAddress();
                if (!TransportAddress.isPrivate(connectionAddress) && !TransportAddress.isReserved(connectionAddress) && !TransportAddress.isLinkLocal(connectionAddress) && !TransportAddress.isLoopback(connectionAddress)) {
                    arrayList.add(new ServerAddress(connectionAddress, i, connectionAddress));
                }
            }
        }
        return (ServerAddress[]) arrayList.toArray(new ServerAddress[0]);
    }

    public Candidate[] getServerReflexiveCandidates() {
        return getCandidates(CandidateType.getServerReflexive());
    }

    public IceServerTestResult(Candidate[] candidateArr) {
        setCandidates(candidateArr);
    }

    private void setCandidates(Candidate[] candidateArr) {
        this._candidates = candidateArr;
    }
}
