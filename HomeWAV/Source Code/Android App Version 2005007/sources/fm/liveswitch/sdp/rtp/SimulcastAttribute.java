package fm.liveswitch.sdp.rtp;

import fm.liveswitch.ArrayExtensions;
import fm.liveswitch.Global;
import fm.liveswitch.IntegerHolder;
import fm.liveswitch.MathAssistant;
import fm.liveswitch.StringBuilderExtensions;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.sdp.Attribute;
import fm.liveswitch.sdp.AttributeCategory;
import fm.liveswitch.sdp.AttributeType;

public class SimulcastAttribute extends Attribute {
    private SimulcastStreamDescription _description1;
    private SimulcastStreamDescription _description2;
    private int _draftVersion;

    public static SimulcastAttribute fromAttributeValue(String str) {
        SimulcastStream[] simulcastStreamArr;
        String str2;
        int i;
        SimulcastStream[] simulcastStreamArr2;
        int indexOf = StringExtensions.indexOf(str, " ");
        String substring = StringExtensions.substring(str, 0, indexOf);
        String substring2 = str.substring(indexOf + 1);
        int indexOf2 = StringExtensions.indexOf(substring2, " ");
        SimulcastStreamDescription simulcastStreamDescription = null;
        if (indexOf2 == -1) {
            IntegerHolder integerHolder = new IntegerHolder(-1);
            simulcastStreamArr = streamsFromString(substring2, integerHolder);
            i = integerHolder.getValue();
            simulcastStreamArr2 = null;
            str2 = null;
        } else {
            IntegerHolder integerHolder2 = new IntegerHolder(-1);
            SimulcastStream[] streamsFromString = streamsFromString(StringExtensions.substring(substring2, 0, indexOf2), integerHolder2);
            int value = integerHolder2.getValue();
            String substring3 = substring2.substring(indexOf2 + 1);
            int indexOf3 = StringExtensions.indexOf(substring3, " ");
            String substring4 = StringExtensions.substring(substring3, 0, indexOf3);
            String substring5 = substring3.substring(indexOf3 + 1);
            IntegerHolder integerHolder3 = new IntegerHolder(-1);
            SimulcastStream[] streamsFromString2 = streamsFromString(substring5, integerHolder3);
            str2 = substring4;
            i = MathAssistant.max(value, integerHolder3.getValue());
            simulcastStreamArr2 = streamsFromString2;
            simulcastStreamArr = streamsFromString;
        }
        SimulcastStreamDescription simulcastStreamDescription2 = new SimulcastStreamDescription(substring, simulcastStreamArr);
        if (str2 != null) {
            simulcastStreamDescription = new SimulcastStreamDescription(str2, simulcastStreamArr2);
        }
        SimulcastAttribute simulcastAttribute = new SimulcastAttribute(simulcastStreamDescription2, simulcastStreamDescription);
        simulcastAttribute.setDraftVersion(i);
        return simulcastAttribute;
    }

    /* access modifiers changed from: protected */
    public String getAttributeValue() {
        StringBuilder sb = new StringBuilder();
        StringBuilderExtensions.append(sb, getDescription1().getDirection());
        StringBuilderExtensions.append(sb, " ");
        StringBuilderExtensions.append(sb, streamsToString(getDescription1().getStreams(), getDraftVersion()));
        if (getDescription2() != null) {
            StringBuilderExtensions.append(sb, " ");
            StringBuilderExtensions.append(sb, getDescription2().getDirection());
            StringBuilderExtensions.append(sb, " ");
            StringBuilderExtensions.append(sb, streamsToString(getDescription2().getStreams(), getDraftVersion()));
        }
        return sb.toString();
    }

    private SimulcastStreamDescription getDescription(String str) {
        if (getDescription1() != null && Global.equals(getDescription1().getDirection(), str)) {
            return getDescription1();
        }
        if (getDescription2() == null || !Global.equals(getDescription2().getDirection(), str)) {
            return null;
        }
        return getDescription2();
    }

    public SimulcastStreamDescription getDescription1() {
        return this._description1;
    }

    public SimulcastStreamDescription getDescription2() {
        return this._description2;
    }

    public int getDraftVersion() {
        return this._draftVersion;
    }

    public SimulcastStreamDescription getReceiveDescription() {
        return getDescription(SimulcastDirection.getReceive());
    }

    public SimulcastStreamDescription getSendDescription() {
        return getDescription(SimulcastDirection.getSend());
    }

    private void setDescription1(SimulcastStreamDescription simulcastStreamDescription) {
        this._description1 = simulcastStreamDescription;
    }

    private void setDescription2(SimulcastStreamDescription simulcastStreamDescription) {
        this._description2 = simulcastStreamDescription;
    }

    public void setDraftVersion(int i) {
        this._draftVersion = i;
    }

    private SimulcastAttribute() {
        super.setAttributeType(AttributeType.SimulcastAttribute);
        super.setMultiplexingCategory(AttributeCategory.Normal);
    }

    public SimulcastAttribute(SimulcastStreamDescription simulcastStreamDescription) {
        this(simulcastStreamDescription, (SimulcastStreamDescription) null);
    }

    public SimulcastAttribute(SimulcastStreamDescription simulcastStreamDescription, SimulcastStreamDescription simulcastStreamDescription2) {
        this();
        if (simulcastStreamDescription == null) {
            throw new RuntimeException(new Exception("Simulcast attribute 'description1' cannot be null."));
        } else if (simulcastStreamDescription2 == null || !Global.equals(simulcastStreamDescription.getDirection(), simulcastStreamDescription2.getDirection())) {
            setDescription1(simulcastStreamDescription);
            setDescription2(simulcastStreamDescription2);
        } else {
            throw new RuntimeException(new Exception("Simulcast attribute 'descriptions' must have different directions."));
        }
    }

    private static SimulcastStream[] streamsFromString(String str, IntegerHolder integerHolder) {
        integerHolder.setValue(-1);
        if (str.startsWith("rid=")) {
            integerHolder.setValue(3);
            str = str.substring(StringExtensions.getLength("rid="));
        } else if (str.startsWith("pt=")) {
            throw new RuntimeException(new Exception("Draft version 03 'pt=' parsing is not supported."));
        }
        String[] split = StringExtensions.split(str, new char[]{';'});
        SimulcastStream[] simulcastStreamArr = new SimulcastStream[ArrayExtensions.getLength((Object[]) split)];
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) simulcastStreamArr); i++) {
            String[] split2 = StringExtensions.split(split[i], new char[]{','});
            SimulcastStreamId[] simulcastStreamIdArr = new SimulcastStreamId[ArrayExtensions.getLength((Object[]) split2)];
            for (int i2 = 0; i2 < ArrayExtensions.getLength((Object[]) simulcastStreamIdArr); i2++) {
                String str2 = split2[i2];
                if (str2.startsWith("~")) {
                    simulcastStreamIdArr[i2] = new SimulcastStreamId(str2.substring(1), true);
                } else {
                    simulcastStreamIdArr[i2] = new SimulcastStreamId(str2);
                }
            }
            simulcastStreamArr[i] = new SimulcastStream(simulcastStreamIdArr);
        }
        return simulcastStreamArr;
    }

    private static String streamsToString(SimulcastStream[] simulcastStreamArr, int i) {
        String[] strArr = new String[ArrayExtensions.getLength((Object[]) simulcastStreamArr)];
        for (int i2 = 0; i2 < ArrayExtensions.getLength((Object[]) strArr); i2++) {
            SimulcastStream simulcastStream = simulcastStreamArr[i2];
            String[] strArr2 = new String[ArrayExtensions.getLength((Object[]) simulcastStream.getIds())];
            for (int i3 = 0; i3 < ArrayExtensions.getLength((Object[]) strArr2); i3++) {
                SimulcastStreamId simulcastStreamId = simulcastStream.getIds()[i3];
                if (simulcastStreamId.getPaused()) {
                    strArr2[i3] = StringExtensions.format("~{0}", (Object) simulcastStreamId.getId());
                } else {
                    strArr2[i3] = simulcastStreamId.getId();
                }
            }
            strArr[i2] = StringExtensions.join(",", strArr2);
        }
        String join = StringExtensions.join(";", strArr);
        return (i == 2 || i == 3) ? StringExtensions.concat("rid=", join) : join;
    }
}
