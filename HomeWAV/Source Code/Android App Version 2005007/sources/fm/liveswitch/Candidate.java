package fm.liveswitch;

import fm.liveswitch.sdp.Attribute;
import fm.liveswitch.sdp.ice.CandidateAttribute;
import java.util.HashMap;

public class Candidate {
    private ProtocolType __protocol = ProtocolType.Unknown;
    private ProtocolType __relayProtocol = ProtocolType.Unknown;
    private CandidateAttribute __sdpCandidateAttribute;
    private boolean _dispatched;
    private int _sdpMediaIndex;

    public static Candidate fromJson(String str) {
        return (Candidate) JsonSerializer.deserializeObject(str, new IFunction0<Candidate>() {
            public Candidate invoke() {
                return new Candidate();
            }
        }, new IAction3<Candidate, String, String>() {
            public void invoke(Candidate candidate, String str, String str2) {
                if (str == null) {
                    return;
                }
                if (Global.equals(str, "sdpCandidateAttribute")) {
                    String deserializeString = JsonSerializer.deserializeString(str2);
                    if (deserializeString != null) {
                        if (!deserializeString.startsWith("a=")) {
                            deserializeString = StringExtensions.concat("a=", deserializeString);
                        }
                        candidate.setSdpCandidateAttribute((CandidateAttribute) Global.tryCast(Attribute.parse(deserializeString), CandidateAttribute.class));
                    }
                } else if (Global.equals(str, "sdpMediaIndex")) {
                    candidate.setSdpMediaIndex(JsonSerializer.deserializeInteger(str2).getValue());
                }
            }
        });
    }

    public boolean getDispatched() {
        return this._dispatched;
    }

    /* access modifiers changed from: package-private */
    public ProtocolType getProtocol() {
        return this.__protocol;
    }

    public ProtocolType getRelayProtocol() {
        return this.__relayProtocol;
    }

    public CandidateAttribute getSdpCandidateAttribute() {
        return this.__sdpCandidateAttribute;
    }

    public int getSdpMediaIndex() {
        return this._sdpMediaIndex;
    }

    public ProtocolType getTurnTransportProtocol() {
        return getRelayProtocol();
    }

    public void setDispatched(boolean z) {
        this._dispatched = z;
    }

    /* access modifiers changed from: package-private */
    public void setProtocol(ProtocolType protocolType) {
        this.__protocol = protocolType;
    }

    public void setRelayProtocol(ProtocolType protocolType) {
        this.__relayProtocol = protocolType;
    }

    public void setSdpCandidateAttribute(CandidateAttribute candidateAttribute) {
        setProtocol(CandidateUtility.protocolTypeFromString(candidateAttribute.getProtocol()));
        this.__sdpCandidateAttribute = candidateAttribute;
    }

    public void setSdpMediaIndex(int i) {
        this._sdpMediaIndex = i;
    }

    public void setTurnTransportProtocol(ProtocolType protocolType) {
        setRelayProtocol(protocolType);
    }

    public static String toJson(Candidate candidate) {
        return JsonSerializer.serializeObject(candidate, new IAction2<Candidate, HashMap<String, String>>(candidate) {
            final /* synthetic */ Candidate val$candidate;

            {
                this.val$candidate = r1;
            }

            public void invoke(Candidate candidate, HashMap<String, String> hashMap) {
                CandidateAttribute sdpCandidateAttribute = this.val$candidate.getSdpCandidateAttribute();
                if (sdpCandidateAttribute != null) {
                    String candidateAttribute = sdpCandidateAttribute.toString();
                    if (!candidateAttribute.startsWith("a=")) {
                        candidateAttribute = StringExtensions.concat("a=", candidateAttribute);
                    }
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "sdpCandidateAttribute", JsonSerializer.serializeString(candidateAttribute));
                }
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "sdpMediaIndex", JsonSerializer.serializeInteger(new NullableInteger(this.val$candidate.getSdpMediaIndex())));
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }
}
