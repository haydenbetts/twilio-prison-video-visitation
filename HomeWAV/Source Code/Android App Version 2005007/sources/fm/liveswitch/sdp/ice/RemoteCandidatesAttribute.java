package fm.liveswitch.sdp.ice;

import fm.liveswitch.ArrayExtensions;
import fm.liveswitch.StringAssistant;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.sdp.Attribute;
import fm.liveswitch.sdp.AttributeCategory;
import fm.liveswitch.sdp.AttributeType;
import java.util.ArrayList;

public class RemoteCandidatesAttribute extends Attribute {
    private RemoteCandidate[] _candidates;

    public static RemoteCandidatesAttribute fromAttributeValue(String str) {
        String[] split = StringExtensions.split(str, new char[]{' '});
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) split); i += 3) {
            arrayList.add(RemoteCandidate.parse(StringExtensions.join(" ", StringAssistant.subArray(split, i, 3))));
        }
        RemoteCandidatesAttribute remoteCandidatesAttribute = new RemoteCandidatesAttribute();
        remoteCandidatesAttribute.setCandidates((RemoteCandidate[]) arrayList.toArray(new RemoteCandidate[0]));
        return remoteCandidatesAttribute;
    }

    /* access modifiers changed from: protected */
    public String getAttributeValue() {
        String[] strArr = new String[ArrayExtensions.getLength((Object[]) getCandidates())];
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) getCandidates()); i++) {
            strArr[i] = getCandidates()[i].toString();
        }
        return StringExtensions.join(" ", strArr);
    }

    public RemoteCandidate[] getCandidates() {
        return this._candidates;
    }

    private RemoteCandidatesAttribute() {
        super.setAttributeType(AttributeType.IceRemoteCandidatesAttribute);
        super.setMultiplexingCategory(AttributeCategory.Transport);
    }

    public RemoteCandidatesAttribute(RemoteCandidate[] remoteCandidateArr) {
        this();
        if (remoteCandidateArr != null) {
            setCandidates(remoteCandidateArr);
            return;
        }
        throw new RuntimeException(new Exception("candidates cannot be null."));
    }

    private void setCandidates(RemoteCandidate[] remoteCandidateArr) {
        this._candidates = remoteCandidateArr;
    }
}
