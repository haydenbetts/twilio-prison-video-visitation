package fm.liveswitch.sdp;

import fm.liveswitch.ArrayExtensions;
import fm.liveswitch.Global;
import fm.liveswitch.StringExtensions;

public class GroupAttribute extends Attribute {
    private String[] _ids;
    private GroupSemanticsType _semantics;

    public static GroupAttribute fromAttributeValue(String str) {
        GroupSemanticsType groupSemanticsType;
        String[] split = StringExtensions.split(str, new char[]{' '});
        String str2 = split[0];
        GroupSemanticsType groupSemanticsType2 = GroupSemanticsType.Unknown;
        String[] strArr = new String[(ArrayExtensions.getLength((Object[]) split) - 1)];
        if (Global.equals(str2, "BUNDLE")) {
            groupSemanticsType = GroupSemanticsType.Bundling;
        } else if (Global.equals(str2, "FID")) {
            groupSemanticsType = GroupSemanticsType.FlowIdentification;
        } else if (Global.equals(str2, "LS")) {
            groupSemanticsType = GroupSemanticsType.LipSynchronization;
        } else {
            groupSemanticsType = GroupSemanticsType.Unknown;
        }
        for (int i = 1; i < ArrayExtensions.getLength((Object[]) split); i++) {
            strArr[i - 1] = split[i];
        }
        return new GroupAttribute(groupSemanticsType, strArr);
    }

    /* access modifiers changed from: protected */
    public String getAttributeValue() {
        String str;
        if (Global.equals(getSemantics(), GroupSemanticsType.Bundling)) {
            str = "BUNDLE";
        } else if (Global.equals(getSemantics(), GroupSemanticsType.FlowIdentification)) {
            str = "FID";
        } else {
            str = Global.equals(getSemantics(), GroupSemanticsType.LipSynchronization) ? "LS" : "";
        }
        for (String concat : getIds()) {
            str = StringExtensions.concat(str, " ", concat);
        }
        return str;
    }

    public String[] getIds() {
        return this._ids;
    }

    public GroupSemanticsType getSemantics() {
        return this._semantics;
    }

    public GroupAttribute(GroupSemanticsType groupSemanticsType, String[] strArr) {
        setSemantics(groupSemanticsType);
        setIds(strArr);
        super.setAttributeType(AttributeType.GroupAttribute);
        super.setMultiplexingCategory(AttributeCategory.Normal);
    }

    private void setIds(String[] strArr) {
        this._ids = strArr;
    }

    private void setSemantics(GroupSemanticsType groupSemanticsType) {
        this._semantics = groupSemanticsType;
    }
}
