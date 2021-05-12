package fm.liveswitch;

import java.util.ArrayList;
import java.util.Iterator;
import org.slf4j.Marker;

abstract class SignallingExtensible extends Dynamic {
    private SignallingExtensions __extensions;

    public static String getAcknowledgementExtensionName() {
        return "fm.ack";
    }

    public static String getAuthTokenExtensionName() {
        return "fm.authToken";
    }

    public static String getDeviceIdExtensionName() {
        return "fm.deviceId";
    }

    public static String getDisableBinaryExtensionName() {
        return "fm.dbin";
    }

    public static String getIsPrivateExtensionName() {
        return "fm.private";
    }

    public static String getMetaExtensionName() {
        return "fm.meta";
    }

    public static String getPresenceExtensionName() {
        return "fm.presence";
    }

    public static String getRemoteClientExtensionName() {
        return "fm.remoteClient";
    }

    public static String getReturnDataExtensionName() {
        return "fm.returnData";
    }

    public static String getServerActionsExtensionName() {
        return "fm.server";
    }

    public static String getServerTimeoutExtensionName() {
        return "fm.timeout";
    }

    public static String getStreamIdExtensionName() {
        return "fm.streamId";
    }

    public static String getSubscribedClientsExtensionName() {
        return "fm.subscribed";
    }

    public static String getTagExtensionName() {
        return "fm.tag";
    }

    public static String getUserIdExtensionName() {
        return "fm.userId";
    }

    protected static String[] sharedGetChannels(String[] strArr) {
        return strArr;
    }

    protected static String[] sharedGetKeys(String[] strArr) {
        return strArr;
    }

    protected static SignallingRecord[] sharedGetRecords(SignallingRecord[] signallingRecordArr) {
        return signallingRecordArr;
    }

    static SignallingRecord convertKeyToRecord(String str) {
        return new SignallingRecord(str);
    }

    static String convertRecordToKey(SignallingRecord signallingRecord) {
        return signallingRecord.getKey();
    }

    public void copyExtensions(SignallingExtensible signallingExtensible) {
        Iterator<String> it = signallingExtensible.getExtensionNames().iterator();
        while (it.hasNext()) {
            String next = it.next();
            setExtensionValueJson(next, signallingExtensible.getExtensionValueJson(next), false);
        }
    }

    public int getExtensionCount() {
        return getExtensions().getCount();
    }

    public ArrayList<String> getExtensionNames() {
        return getExtensions().getNames();
    }

    public SignallingExtensions getExtensions() {
        if (this.__extensions == null) {
            this.__extensions = new SignallingExtensions();
        }
        return this.__extensions;
    }

    public String getExtensionValueJson(String str) {
        return getExtensions().getValueJson(str);
    }

    public String getMetaJson() {
        return getExtensionValueJson(getMetaExtensionName());
    }

    public void setExtensions(SignallingExtensions signallingExtensions) {
        if (this.__extensions == null) {
            this.__extensions = new SignallingExtensions();
        }
        if (signallingExtensions != null) {
            Iterator<String> it = signallingExtensions.getNames().iterator();
            while (it.hasNext()) {
                String next = it.next();
                this.__extensions.setValueJson(next, signallingExtensions.getValueJson(next));
            }
        }
    }

    public void setExtensionValueJson(String str, String str2) {
        getExtensions().setValueJson(str, str2);
        super.setIsDirty(true);
    }

    public void setExtensionValueJson(String str, String str2, boolean z) {
        getExtensions().setValueJson(str, str2, z);
        super.setIsDirty(true);
    }

    public void setMetaJson(String str) {
        setExtensionValueJson(getMetaExtensionName(), str);
    }

    protected static String sharedGetChannel(String[] strArr) {
        if (strArr == null || ArrayExtensions.getLength((Object[]) strArr) == 0) {
            return null;
        }
        return strArr[0];
    }

    protected static String sharedGetKey(String[] strArr) {
        if (strArr == null || ArrayExtensions.getLength((Object[]) strArr) == 0) {
            return null;
        }
        return strArr[0];
    }

    protected static SignallingRecord sharedGetRecord(SignallingRecord[] signallingRecordArr) {
        if (signallingRecordArr == null || ArrayExtensions.getLength((Object[]) signallingRecordArr) == 0) {
            return null;
        }
        return signallingRecordArr[0];
    }

    protected static String[] sharedSetChannel(String str) {
        return sharedSetChannel(str, true);
    }

    protected static String[] sharedSetChannel(String str, boolean z) {
        if (str == null) {
            return null;
        }
        Holder holder = new Holder(null);
        boolean validateChannel = validateChannel(str, holder);
        String str2 = (String) holder.getValue();
        if (!z || validateChannel) {
            return new String[]{str};
        }
        throw new RuntimeException(new Exception(StringExtensions.format("Invalid channel. {0}", (Object) str2)));
    }

    protected static String[] sharedSetChannels(String[] strArr) {
        return sharedSetChannels(strArr, true);
    }

    protected static String[] sharedSetChannels(String[] strArr, boolean z) {
        if (strArr != null) {
            int length = strArr.length;
            int i = 0;
            while (i < length) {
                String str = strArr[i];
                Holder holder = new Holder(null);
                boolean validateChannel = validateChannel(str, holder);
                String str2 = (String) holder.getValue();
                if (!z || validateChannel) {
                    i++;
                } else {
                    throw new RuntimeException(new Exception(StringExtensions.format("Invalid channel. {0}", (Object) str2)));
                }
            }
        }
        return strArr;
    }

    protected static String[] sharedSetKey(String str) {
        return sharedSetKey(str, true);
    }

    protected static String[] sharedSetKey(String str, boolean z) {
        if (str == null) {
            return null;
        }
        Holder holder = new Holder(null);
        boolean validateKey = validateKey(str, holder);
        String str2 = (String) holder.getValue();
        if (!z || validateKey) {
            return new String[]{str};
        }
        throw new RuntimeException(new Exception(StringExtensions.format("Invalid key. {0}", (Object) str2)));
    }

    protected static String[] sharedSetKeys(String[] strArr) {
        return sharedSetKeys(strArr, true);
    }

    protected static String[] sharedSetKeys(String[] strArr, boolean z) {
        if (strArr != null) {
            int length = strArr.length;
            int i = 0;
            while (i < length) {
                String str = strArr[i];
                Holder holder = new Holder(null);
                boolean validateKey = validateKey(str, holder);
                String str2 = (String) holder.getValue();
                if (!z || validateKey) {
                    i++;
                } else {
                    throw new RuntimeException(new Exception(StringExtensions.format("Invalid key. {0}", (Object) str2)));
                }
            }
        }
        return strArr;
    }

    protected static SignallingRecord[] sharedSetRecord(SignallingRecord signallingRecord) {
        return sharedSetRecord(signallingRecord, true);
    }

    protected static SignallingRecord[] sharedSetRecord(SignallingRecord signallingRecord, boolean z) {
        Holder holder = new Holder(null);
        boolean validateRecord = validateRecord(signallingRecord, holder);
        String str = (String) holder.getValue();
        if (!z || validateRecord) {
            return new SignallingRecord[]{signallingRecord};
        }
        throw new RuntimeException(new Exception(StringExtensions.format("Invalid record. {0}", (Object) str)));
    }

    protected static SignallingRecord[] sharedSetRecords(SignallingRecord[] signallingRecordArr) {
        return sharedSetRecords(signallingRecordArr, true);
    }

    protected static SignallingRecord[] sharedSetRecords(SignallingRecord[] signallingRecordArr, boolean z) {
        if (signallingRecordArr != null) {
            int length = signallingRecordArr.length;
            int i = 0;
            while (i < length) {
                SignallingRecord signallingRecord = signallingRecordArr[i];
                Holder holder = new Holder(null);
                boolean validateRecord = validateRecord(signallingRecord, holder);
                String str = (String) holder.getValue();
                if (!z || validateRecord) {
                    i++;
                } else {
                    throw new RuntimeException(new Exception(StringExtensions.format("Invalid record. {0}", (Object) str)));
                }
            }
        }
        return signallingRecordArr;
    }

    protected SignallingExtensible() {
    }

    public static boolean validateChannel(String str, Holder<String> holder) {
        if (str == null) {
            holder.setValue("Channel is null.");
            return false;
        } else if (!StringExtensions.startsWith(str, "/", StringComparison.Ordinal)) {
            holder.setValue("Channel must start with a forward-slash (/).");
            return false;
        } else if (StringExtensions.indexOf(str, Marker.ANY_MARKER, StringComparison.Ordinal) >= 0) {
            holder.setValue("Channel may not contain asterisks (*).");
            return false;
        } else if (SignallingMetaChannels.isReservedChannel(str)) {
            holder.setValue("Channel is reserved.");
            return false;
        } else {
            holder.setValue(null);
            return true;
        }
    }

    public static boolean validateKey(String str, Holder<String> holder) {
        if (str == null) {
            holder.setValue("Key is null.");
            return false;
        }
        holder.setValue(null);
        return true;
    }

    public static boolean validateRecord(SignallingRecord signallingRecord, Holder<String> holder) {
        if (signallingRecord == null) {
            holder.setValue("Record is null.");
            return false;
        } else if (StringExtensions.isNullOrEmpty(signallingRecord.getKey())) {
            holder.setValue("Key is null or empty.");
            return false;
        } else {
            holder.setValue(null);
            return true;
        }
    }
}
