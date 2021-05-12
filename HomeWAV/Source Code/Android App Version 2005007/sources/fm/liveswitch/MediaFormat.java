package fm.liveswitch;

import fm.liveswitch.MediaFormat;
import java.util.ArrayList;

public abstract class MediaFormat<TFormat extends MediaFormat<TFormat>> {
    private String __name;
    private String __packetizationMode;
    private int _clockRate;
    private boolean _isEncrypted;
    private boolean _isFixedBitrate;
    private boolean _isInjected;
    private boolean _isPacketized;
    private String _level;
    private boolean _levelIsStrict;
    private String _profile;
    private int _registeredPayloadType;
    private int _staticPayloadType;

    public static String getRedName() {
        return "red";
    }

    public static String getUlpFecName() {
        return "ulpfec";
    }

    /* access modifiers changed from: protected */
    public abstract TFormat createInstance();

    public abstract FormatInfo getInfo();

    public abstract boolean getIsCompressed();

    public int getMaxBitrate() {
        return -1;
    }

    /* access modifiers changed from: protected */
    public String getMaxLevel(String str, String str2) {
        return str;
    }

    public int getMinBitrate() {
        return -1;
    }

    /* access modifiers changed from: protected */
    public String getMinLevel(String str, String str2) {
        return str;
    }

    public abstract String getParameters();

    /* access modifiers changed from: protected */
    public boolean isLevelCompatible(String str) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean isProfileCompatible(String str) {
        return false;
    }

    public TFormat clone() {
        TFormat createInstance = createInstance();
        createInstance.setName(getName());
        createInstance.setClockRate(getClockRate());
        createInstance.setIsPacketized(getIsPacketized());
        createInstance.setPacketizationMode(getPacketizationMode());
        createInstance.setProfile(getProfile());
        createInstance.setLevel(getLevel());
        createInstance.setLevelIsStrict(getLevelIsStrict());
        createInstance.setIsEncrypted(getIsEncrypted());
        createInstance.setIsInjected(getIsInjected());
        createInstance.setRegisteredPayloadType(getRegisteredPayloadType());
        createInstance.setStaticPayloadType(getStaticPayloadType());
        createInstance.setIsFixedBitrate(getIsFixedBitrate());
        return createInstance;
    }

    public int getClockRate() {
        return this._clockRate;
    }

    public String getFullName() {
        if (getName() != null) {
            String parameters = getParameters();
            if (StringExtensions.isNullOrEmpty(parameters)) {
                return StringExtensions.format("{0}/{1}", getName(), IntegerExtensions.toString(Integer.valueOf(getClockRate())));
            }
            return StringExtensions.format("{0}/{1}/{2}", getName(), IntegerExtensions.toString(Integer.valueOf(getClockRate())), parameters);
        }
        throw new RuntimeException(new Exception("Name cannot be null."));
    }

    public boolean getIsEncrypted() {
        return this._isEncrypted;
    }

    public boolean getIsFixedBitrate() {
        return this._isFixedBitrate;
    }

    public boolean getIsInjected() {
        return this._isInjected;
    }

    public boolean getIsPacketized() {
        return this._isPacketized;
    }

    public String getLevel() {
        return this._level;
    }

    public boolean getLevelIsStrict() {
        return this._levelIsStrict;
    }

    public String getName() {
        return this.__name;
    }

    public String getPacketizationMode() {
        if (getIsPacketized()) {
            return this.__packetizationMode;
        }
        return null;
    }

    public String getProfile() {
        return this._profile;
    }

    /* access modifiers changed from: package-private */
    public int getRegisteredPayloadType() {
        return this._registeredPayloadType;
    }

    public int getStaticPayloadType() {
        return this._staticPayloadType;
    }

    private void initialize() {
        setRegisteredPayloadType(-1);
        setStaticPayloadType(-1);
    }

    private void initialize(String str, int i, String str2, String str3, String str4) {
        setName(str);
        setClockRate(i);
        setProfile(str2);
        setLevel(str3);
        setPacketizationMode(str4);
        if (str4 != null) {
            setIsPacketized(true);
        }
    }

    public boolean isCompatible(TFormat tformat) {
        if (tformat == null || !StringExtensions.isEqual(getName(), tformat.getName(), StringComparison.InvariantCultureIgnoreCase) || getClockRate() != tformat.getClockRate() || !Global.equals(Boolean.valueOf(getIsPacketized()), Boolean.valueOf(tformat.getIsPacketized())) || !Global.equals(getPacketizationMode(), tformat.getPacketizationMode())) {
            return false;
        }
        if (getProfile() != null && tformat.getProfile() != null && !StringExtensions.isEqual(getProfile(), tformat.getProfile(), StringComparison.InvariantCultureIgnoreCase) && !isProfileCompatible(tformat.getProfile())) {
            return false;
        }
        if (getLevel() == null || tformat.getLevel() == null || !getLevelIsStrict() || StringExtensions.isEqual(getLevel(), tformat.getLevel(), StringComparison.InvariantCultureIgnoreCase) || isLevelCompatible(tformat.getLevel())) {
            return true;
        }
        return false;
    }

    public boolean isEquivalent(TFormat tformat) {
        return isEquivalent(tformat, false);
    }

    public boolean isEquivalent(TFormat tformat, boolean z) {
        if (tformat == null || !StringExtensions.isEqual(getName(), tformat.getName(), StringComparison.InvariantCultureIgnoreCase) || getClockRate() != tformat.getClockRate()) {
            return false;
        }
        if (!z && (!Global.equals(Boolean.valueOf(getIsPacketized()), Boolean.valueOf(tformat.getIsPacketized())) || !Global.equals(getPacketizationMode(), tformat.getPacketizationMode()))) {
            return false;
        }
        if (getProfile() == null) {
            if (tformat.getProfile() != null) {
                return false;
            }
        } else if (!StringExtensions.isEqual(getProfile(), tformat.getProfile(), StringComparison.InvariantCultureIgnoreCase)) {
            return false;
        }
        if (getLevel() == null) {
            if (tformat.getLevel() != null) {
                return false;
            }
            return true;
        } else if (!StringExtensions.isEqual(getLevel(), tformat.getLevel(), StringComparison.InvariantCultureIgnoreCase)) {
            return false;
        } else {
            return true;
        }
    }

    protected MediaFormat() {
        initialize();
    }

    public MediaFormat(String str, int i) {
        initialize();
        initialize(str, i, (String) null, (String) null, (String) null);
    }

    public MediaFormat(String str, int i, String str2) {
        initialize();
        initialize(str, i, (String) null, (String) null, str2);
    }

    public MediaFormat(String str, int i, String str2, String str3) {
        initialize();
        initialize(str, i, str2, str3, (String) null);
    }

    public MediaFormat(String str, int i, String str2, String str3, String str4) {
        initialize();
        initialize(str, i, str2, str3, str4);
    }

    public void setClockRate(int i) {
        this._clockRate = i;
    }

    public void setIsEncrypted(boolean z) {
        this._isEncrypted = z;
    }

    /* access modifiers changed from: protected */
    public void setIsFixedBitrate(boolean z) {
        this._isFixedBitrate = z;
    }

    public void setIsInjected(boolean z) {
        this._isInjected = z;
    }

    public void setIsPacketized(boolean z) {
        this._isPacketized = z;
    }

    public void setLevel(String str) {
        this._level = str;
    }

    public void setLevelIsStrict(boolean z) {
        this._levelIsStrict = z;
    }

    public void setName(String str) {
        if (str != null) {
            this.__name = str;
            return;
        }
        throw new RuntimeException(new Exception("Name cannot be null."));
    }

    public void setPacketizationMode(String str) {
        this.__packetizationMode = str;
    }

    public void setProfile(String str) {
        this._profile = str;
    }

    /* access modifiers changed from: package-private */
    public void setRegisteredPayloadType(int i) {
        this._registeredPayloadType = i;
    }

    /* access modifiers changed from: protected */
    public void setStaticPayloadType(int i) {
        this._staticPayloadType = i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        StringBuilderExtensions.append(sb, getFullName());
        ArrayList arrayList = new ArrayList();
        if (getPacketizationMode() != null) {
            arrayList.add(StringExtensions.format("Packetization Mode {0}", (Object) getPacketizationMode()));
        }
        if (getProfile() != null) {
            arrayList.add(StringExtensions.format("Profile {0}", (Object) getProfile()));
        }
        if (getLevel() != null) {
            arrayList.add(StringExtensions.format("Level {0}", (Object) getLevel()));
        }
        if (ArrayListExtensions.getCount(arrayList) > 0) {
            StringBuilderExtensions.append(sb, " (");
            StringBuilderExtensions.append(sb, StringExtensions.join("; ", (String[]) arrayList.toArray(new String[0])));
            StringBuilderExtensions.append(sb, ")");
        }
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public void updateLevelIsStrictToCompatible(TFormat tformat) {
        if (tformat.getLevelIsStrict()) {
            setLevelIsStrict(true);
        }
    }

    /* access modifiers changed from: protected */
    public void updateLevelToCompatible(TFormat tformat) {
        if (tformat.getLevel() == null) {
            return;
        }
        if (getLevel() == null) {
            setLevel(tformat.getLevel());
        } else if (getLevelIsStrict()) {
        } else {
            if (tformat.getLevelIsStrict()) {
                setLevel(tformat.getLevel());
            } else {
                setLevel(getMinLevel(getLevel(), tformat.getLevel()));
            }
        }
    }

    /* access modifiers changed from: protected */
    public void updateProfileToCompatible(TFormat tformat) {
        if (tformat.getProfile() != null) {
            setProfile(tformat.getProfile());
        }
    }

    /* access modifiers changed from: package-private */
    public void updateToCompatible(TFormat tformat) {
        updateProfileToCompatible(tformat);
        updateLevelToCompatible(tformat);
        updateLevelIsStrictToCompatible(tformat);
    }
}
