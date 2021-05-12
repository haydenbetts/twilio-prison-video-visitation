package fm.liveswitch.sdp.rtp;

import fm.liveswitch.ArrayExtensions;
import fm.liveswitch.Global;
import fm.liveswitch.IntegerExtensions;
import fm.liveswitch.StringBuilderExtensions;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.sdp.Attribute;
import fm.liveswitch.sdp.AttributeCategory;
import fm.liveswitch.sdp.AttributeType;

public class RidAttribute extends Attribute {
    private String __direction;
    private String _id;
    private int[] _payloadTypes;
    private RidRestriction[] _restrictions;

    private boolean directionIsValid(String str) {
        String[] strArr = {RidDirection.getSend(), RidDirection.getReceive()};
        for (int i = 0; i < 2; i++) {
            if (Global.equals(str, strArr[i])) {
                return true;
            }
        }
        return false;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v0, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v1, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: fm.liveswitch.sdp.rtp.RidRestriction[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v2, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: fm.liveswitch.sdp.rtp.RidRestriction[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: int[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v18, resolved type: fm.liveswitch.sdp.rtp.RidRestriction[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v5, resolved type: int[]} */
    /* JADX WARNING: type inference failed for: r5v4, types: [fm.liveswitch.sdp.rtp.RidRestriction[], java.lang.Object[]] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static fm.liveswitch.sdp.rtp.RidAttribute fromAttributeValue(java.lang.String r11) {
        /*
            java.lang.String r0 = " "
            int r1 = fm.liveswitch.StringExtensions.indexOf((java.lang.String) r11, (java.lang.String) r0)
            r2 = 0
            java.lang.String r3 = fm.liveswitch.StringExtensions.substring(r11, r2, r1)
            r4 = 1
            int r1 = r1 + r4
            java.lang.String r11 = r11.substring(r1)
            int r0 = fm.liveswitch.StringExtensions.indexOf((java.lang.String) r11, (java.lang.String) r0)
            r1 = -1
            r5 = 0
            if (r0 != r1) goto L_0x001c
            r0 = r5
            goto L_0x00aa
        L_0x001c:
            java.lang.String r6 = fm.liveswitch.StringExtensions.substring(r11, r2, r0)
            int r0 = r0 + r4
            java.lang.String r11 = r11.substring(r0)
            char[] r0 = new char[r4]
            r7 = 59
            r0[r2] = r7
            java.lang.String[] r11 = fm.liveswitch.StringExtensions.split(r11, r0)
            int r0 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r11)
            if (r0 <= 0) goto L_0x0068
            r0 = r11[r2]
            java.lang.String r7 = "pt="
            boolean r0 = r0.startsWith(r7)
            if (r0 == 0) goto L_0x0068
            r0 = r11[r2]
            r7 = 3
            java.lang.String r0 = r0.substring(r7)
            char[] r7 = new char[r4]
            r8 = 44
            r7[r2] = r8
            java.lang.String[] r0 = fm.liveswitch.StringExtensions.split(r0, r7)
            int r7 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r0)
            int[] r7 = new int[r7]
            r8 = 0
        L_0x0057:
            int r9 = fm.liveswitch.ArrayExtensions.getLength((int[]) r7)
            if (r8 >= r9) goto L_0x006a
            r9 = r0[r8]
            int r9 = fm.liveswitch.ParseAssistant.parseIntegerValue(r9)
            r7[r8] = r9
            int r8 = r8 + 1
            goto L_0x0057
        L_0x0068:
            r7 = r5
            r4 = 0
        L_0x006a:
            int r0 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r11)
            if (r0 <= r4) goto L_0x00a7
            int r0 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r11)
            int r0 = r0 - r4
            fm.liveswitch.sdp.rtp.RidRestriction[] r5 = new fm.liveswitch.sdp.rtp.RidRestriction[r0]
            r0 = 0
        L_0x0078:
            int r8 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r5)
            if (r0 >= r8) goto L_0x00a7
            int r8 = r4 + 1
            r4 = r11[r4]
            java.lang.String r9 = "="
            int r9 = fm.liveswitch.StringExtensions.indexOf((java.lang.String) r4, (java.lang.String) r9)
            if (r9 != r1) goto L_0x0092
            fm.liveswitch.sdp.rtp.RidRestriction r9 = new fm.liveswitch.sdp.rtp.RidRestriction
            r9.<init>(r4)
            r5[r0] = r9
            goto L_0x00a3
        L_0x0092:
            java.lang.String r10 = fm.liveswitch.StringExtensions.substring(r4, r2, r9)
            int r9 = r9 + 1
            java.lang.String r4 = r4.substring(r9)
            fm.liveswitch.sdp.rtp.RidRestriction r9 = new fm.liveswitch.sdp.rtp.RidRestriction
            r9.<init>(r10, r4)
            r5[r0] = r9
        L_0x00a3:
            int r0 = r0 + 1
            r4 = r8
            goto L_0x0078
        L_0x00a7:
            r0 = r5
            r11 = r6
            r5 = r7
        L_0x00aa:
            fm.liveswitch.sdp.rtp.RidAttribute r1 = new fm.liveswitch.sdp.rtp.RidAttribute
            r1.<init>()
            r1.setId(r3)
            r1.setDirection(r11)
            r1.setPayloadTypes(r5)
            r1.setRestrictions(r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.sdp.rtp.RidAttribute.fromAttributeValue(java.lang.String):fm.liveswitch.sdp.rtp.RidAttribute");
    }

    /* access modifiers changed from: protected */
    public String getAttributeValue() {
        int i;
        StringBuilder sb = new StringBuilder();
        StringBuilderExtensions.append(sb, getId());
        StringBuilderExtensions.append(sb, " ");
        StringBuilderExtensions.append(sb, getDirection());
        int length = getPayloadTypes() == null ? 0 : ArrayExtensions.getLength(getPayloadTypes());
        int length2 = getRestrictions() == null ? 0 : ArrayExtensions.getLength((Object[]) getRestrictions());
        int i2 = 1;
        String[] strArr = new String[((length == 0 ? 0 : 1) + length2)];
        if (length > 0) {
            String[] strArr2 = new String[length];
            for (int i3 = 0; i3 < length; i3++) {
                strArr2[i3] = IntegerExtensions.toString(Integer.valueOf(getPayloadTypes()[i3]));
            }
            strArr[0] = StringExtensions.format("pt={0}", (Object) StringExtensions.join(",", strArr2));
        } else {
            i2 = 0;
        }
        if (length2 > 0) {
            for (RidRestriction ridRestriction : getRestrictions()) {
                if (ridRestriction.getValue() == null) {
                    i = i2 + 1;
                    strArr[i2] = ridRestriction.getKey();
                } else {
                    i = i2 + 1;
                    strArr[i2] = StringExtensions.format("{0}={1}", ridRestriction.getKey(), ridRestriction.getValue());
                }
                i2 = i;
            }
        }
        if (ArrayExtensions.getLength((Object[]) strArr) > 0) {
            StringBuilderExtensions.append(sb, " ");
            StringBuilderExtensions.append(sb, StringExtensions.join(";", strArr));
        }
        return sb.toString();
    }

    public String getDirection() {
        return this.__direction;
    }

    public String getId() {
        return this._id;
    }

    public int[] getPayloadTypes() {
        return this._payloadTypes;
    }

    public RidRestriction[] getRestrictions() {
        return this._restrictions;
    }

    public String getRestrictionValue(String str) {
        if (getRestrictions() == null) {
            return null;
        }
        for (RidRestriction ridRestriction : getRestrictions()) {
            if (Global.equals(ridRestriction.getKey(), str)) {
                return ridRestriction.getValue();
            }
        }
        return null;
    }

    private RidAttribute() {
        super.setAttributeType(AttributeType.RtpRidAttribute);
        super.setMultiplexingCategory(AttributeCategory.Special);
    }

    public RidAttribute(String str, String str2) {
        this(str, str2, (int[]) null, (RidRestriction[]) null);
    }

    public RidAttribute(String str, String str2, int[] iArr) {
        this(str, str2, iArr, (RidRestriction[]) null);
    }

    public RidAttribute(String str, String str2, int[] iArr, RidRestriction[] ridRestrictionArr) {
        this();
        if (StringExtensions.isNullOrEmpty(str)) {
            throw new RuntimeException(new Exception("RID 'id' cannot be null."));
        } else if (StringExtensions.isNullOrEmpty(str2)) {
            throw new RuntimeException(new Exception("RID 'direction' cannot be null."));
        } else if (validateId(str)) {
            setId(str);
            setDirection(str2);
            setPayloadTypes(iArr);
            setRestrictions(ridRestrictionArr);
        } else {
            throw new RuntimeException(new Exception("RID 'id' may only conain alphanumeric characters."));
        }
    }

    public RidAttribute(String str, String str2, RidRestriction[] ridRestrictionArr) {
        this(str, str2, (int[]) null, ridRestrictionArr);
    }

    private void setDirection(String str) {
        if (directionIsValid(str)) {
            this.__direction = str;
            return;
        }
        throw new RuntimeException(new Exception(StringExtensions.format("RID 'direction' cannot be '{0}'.", (Object) str)));
    }

    private void setId(String str) {
        this._id = str;
    }

    public void setPayloadTypes(int[] iArr) {
        this._payloadTypes = iArr;
    }

    public void setRestrictions(RidRestriction[] ridRestrictionArr) {
        this._restrictions = ridRestrictionArr;
    }

    public static boolean validateId(String str) {
        for (int i = 0; i < StringExtensions.getLength(str); i++) {
            char charAt = str.charAt(i);
            if (charAt < '-' || ((charAt > '-' && charAt < '0') || ((charAt > '9' && charAt < 'A') || ((charAt > 'Z' && charAt < '_') || ((charAt > '_' && charAt < 'a') || charAt > 'z'))))) {
                return false;
            }
        }
        return true;
    }
}
