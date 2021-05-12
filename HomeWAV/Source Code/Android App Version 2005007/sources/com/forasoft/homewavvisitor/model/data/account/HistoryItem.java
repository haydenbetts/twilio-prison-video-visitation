package com.forasoft.homewavvisitor.model.data.account;

import com.google.gson.annotations.SerializedName;
import com.microsoft.appcenter.ingestion.models.CommonProperties;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import wseemann.media.FFmpegMediaMetadataRetriever;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0004\b\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001&B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003¢\u0006\u0002\u0010\nJ\u0011\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0000H\u0002J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003JE\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u0003HÆ\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010\u0019\u001a\u0004\u0018\u00010#HÖ\u0003J\t\u0010$\u001a\u00020\u0018HÖ\u0001J\t\u0010%\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\t\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u000e\u001a\u00020\u000f8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\f¨\u0006'"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/account/HistoryItem;", "", "date", "", "notes", "value", "timestamp", "", "type", "fullName", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;)V", "getDate", "()Ljava/lang/String;", "getFullName", "itemType", "Lcom/forasoft/homewavvisitor/model/data/account/HistoryItem$Type;", "getItemType", "()Lcom/forasoft/homewavvisitor/model/data/account/HistoryItem$Type;", "getNotes", "getTimestamp", "()J", "getType", "getValue", "compareTo", "", "other", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "", "hashCode", "toString", "Type", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: HistoryItem.kt */
public final class HistoryItem implements Comparable<HistoryItem> {
    private final String date;
    @SerializedName("inmate_name")
    private final String fullName;
    private final String notes;
    private final long timestamp;
    private final String type;
    private final String value;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/account/HistoryItem$Type;", "", "(Ljava/lang/String;I)V", "MESSAGE", "VIDEO_CALL", "MONEY", "UNDEFINED", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: HistoryItem.kt */
    public enum Type {
        MESSAGE,
        VIDEO_CALL,
        MONEY,
        UNDEFINED
    }

    public static /* synthetic */ HistoryItem copy$default(HistoryItem historyItem, String str, String str2, String str3, long j, String str4, String str5, int i, Object obj) {
        if ((i & 1) != 0) {
            str = historyItem.date;
        }
        if ((i & 2) != 0) {
            str2 = historyItem.notes;
        }
        String str6 = str2;
        if ((i & 4) != 0) {
            str3 = historyItem.value;
        }
        String str7 = str3;
        if ((i & 8) != 0) {
            j = historyItem.timestamp;
        }
        long j2 = j;
        if ((i & 16) != 0) {
            str4 = historyItem.type;
        }
        String str8 = str4;
        if ((i & 32) != 0) {
            str5 = historyItem.fullName;
        }
        return historyItem.copy(str, str6, str7, j2, str8, str5);
    }

    public final String component1() {
        return this.date;
    }

    public final String component2() {
        return this.notes;
    }

    public final String component3() {
        return this.value;
    }

    public final long component4() {
        return this.timestamp;
    }

    public final String component5() {
        return this.type;
    }

    public final String component6() {
        return this.fullName;
    }

    public final HistoryItem copy(String str, String str2, String str3, long j, String str4, String str5) {
        Intrinsics.checkParameterIsNotNull(str, FFmpegMediaMetadataRetriever.METADATA_KEY_DATE);
        Intrinsics.checkParameterIsNotNull(str2, "notes");
        Intrinsics.checkParameterIsNotNull(str3, CommonProperties.VALUE);
        Intrinsics.checkParameterIsNotNull(str4, "type");
        String str6 = str5;
        Intrinsics.checkParameterIsNotNull(str6, "fullName");
        return new HistoryItem(str, str2, str3, j, str4, str6);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HistoryItem)) {
            return false;
        }
        HistoryItem historyItem = (HistoryItem) obj;
        return Intrinsics.areEqual((Object) this.date, (Object) historyItem.date) && Intrinsics.areEqual((Object) this.notes, (Object) historyItem.notes) && Intrinsics.areEqual((Object) this.value, (Object) historyItem.value) && this.timestamp == historyItem.timestamp && Intrinsics.areEqual((Object) this.type, (Object) historyItem.type) && Intrinsics.areEqual((Object) this.fullName, (Object) historyItem.fullName);
    }

    public int hashCode() {
        String str = this.date;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.notes;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.value;
        int hashCode3 = str3 != null ? str3.hashCode() : 0;
        long j = this.timestamp;
        int i2 = (((hashCode2 + hashCode3) * 31) + ((int) (j ^ (j >>> 32)))) * 31;
        String str4 = this.type;
        int hashCode4 = (i2 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.fullName;
        if (str5 != null) {
            i = str5.hashCode();
        }
        return hashCode4 + i;
    }

    public String toString() {
        return "HistoryItem(date=" + this.date + ", notes=" + this.notes + ", value=" + this.value + ", timestamp=" + this.timestamp + ", type=" + this.type + ", fullName=" + this.fullName + ")";
    }

    public HistoryItem(String str, String str2, String str3, long j, String str4, String str5) {
        Intrinsics.checkParameterIsNotNull(str, FFmpegMediaMetadataRetriever.METADATA_KEY_DATE);
        Intrinsics.checkParameterIsNotNull(str2, "notes");
        Intrinsics.checkParameterIsNotNull(str3, CommonProperties.VALUE);
        Intrinsics.checkParameterIsNotNull(str4, "type");
        Intrinsics.checkParameterIsNotNull(str5, "fullName");
        this.date = str;
        this.notes = str2;
        this.value = str3;
        this.timestamp = j;
        this.type = str4;
        this.fullName = str5;
    }

    public final String getDate() {
        return this.date;
    }

    public final String getNotes() {
        return this.notes;
    }

    public final String getValue() {
        return this.value;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public final String getType() {
        return this.type;
    }

    public final String getFullName() {
        return this.fullName;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002f, code lost:
        if (r0.equals("kiosk_purchase") != false) goto L_0x0031;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        return com.forasoft.homewavvisitor.model.data.account.HistoryItem.Type.UNDEFINED;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        return com.forasoft.homewavvisitor.model.data.account.HistoryItem.Type.MONEY;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0010, code lost:
        if (r0.equals("purchase") != false) goto L_0x0031;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.forasoft.homewavvisitor.model.data.account.HistoryItem.Type getItemType() {
        /*
            r2 = this;
            java.lang.String r0 = r2.type
            int r1 = r0.hashCode()
            switch(r1) {
                case -63672425: goto L_0x0029;
                case 3045982: goto L_0x001e;
                case 954925063: goto L_0x0013;
                case 1743324417: goto L_0x000a;
                default: goto L_0x0009;
            }
        L_0x0009:
            goto L_0x0034
        L_0x000a:
            java.lang.String r1 = "purchase"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0034
            goto L_0x0031
        L_0x0013:
            java.lang.String r1 = "message"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0034
            com.forasoft.homewavvisitor.model.data.account.HistoryItem$Type r0 = com.forasoft.homewavvisitor.model.data.account.HistoryItem.Type.MESSAGE
            goto L_0x0036
        L_0x001e:
            java.lang.String r1 = "call"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0034
            com.forasoft.homewavvisitor.model.data.account.HistoryItem$Type r0 = com.forasoft.homewavvisitor.model.data.account.HistoryItem.Type.VIDEO_CALL
            goto L_0x0036
        L_0x0029:
            java.lang.String r1 = "kiosk_purchase"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0034
        L_0x0031:
            com.forasoft.homewavvisitor.model.data.account.HistoryItem$Type r0 = com.forasoft.homewavvisitor.model.data.account.HistoryItem.Type.MONEY
            goto L_0x0036
        L_0x0034:
            com.forasoft.homewavvisitor.model.data.account.HistoryItem$Type r0 = com.forasoft.homewavvisitor.model.data.account.HistoryItem.Type.UNDEFINED
        L_0x0036:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.model.data.account.HistoryItem.getItemType():com.forasoft.homewavvisitor.model.data.account.HistoryItem$Type");
    }

    public int compareTo(HistoryItem historyItem) {
        Intrinsics.checkParameterIsNotNull(historyItem, "other");
        return (int) (this.timestamp - historyItem.timestamp);
    }
}
