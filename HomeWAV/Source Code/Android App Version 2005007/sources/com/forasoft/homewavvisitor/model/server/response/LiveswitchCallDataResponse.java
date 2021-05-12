package com.forasoft.homewavvisitor.model.server.response;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0018\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003JO\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u0003HÆ\u0001J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 HÖ\u0003J\t\u0010!\u001a\u00020\u001cHÖ\u0001J\t\u0010\"\u001a\u00020\u0003HÖ\u0001J\u0019\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u001cHÖ\u0001R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\f¨\u0006("}, d2 = {"Lcom/forasoft/homewavvisitor/model/server/response/LiveswitchCallDataResponse;", "Landroid/os/Parcelable;", "liveswitchServerUrl", "", "applicationId", "deviceId", "userId", "channelId", "messageChannelId", "accessToken", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAccessToken", "()Ljava/lang/String;", "getApplicationId", "getChannelId", "getDeviceId", "getLiveswitchServerUrl", "getMessageChannelId", "getUserId", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: LiveswitchCallDataResponse.kt */
public final class LiveswitchCallDataResponse implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Creator();
    private final String accessToken;
    private final String applicationId;
    private final String channelId;
    private final String deviceId;
    private final String liveswitchServerUrl;
    private final String messageChannelId;
    private final String userId;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public static class Creator implements Parcelable.Creator {
        public final Object createFromParcel(Parcel parcel) {
            Intrinsics.checkParameterIsNotNull(parcel, "in");
            return new LiveswitchCallDataResponse(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
        }

        public final Object[] newArray(int i) {
            return new LiveswitchCallDataResponse[i];
        }
    }

    public static /* synthetic */ LiveswitchCallDataResponse copy$default(LiveswitchCallDataResponse liveswitchCallDataResponse, String str, String str2, String str3, String str4, String str5, String str6, String str7, int i, Object obj) {
        if ((i & 1) != 0) {
            str = liveswitchCallDataResponse.liveswitchServerUrl;
        }
        if ((i & 2) != 0) {
            str2 = liveswitchCallDataResponse.applicationId;
        }
        String str8 = str2;
        if ((i & 4) != 0) {
            str3 = liveswitchCallDataResponse.deviceId;
        }
        String str9 = str3;
        if ((i & 8) != 0) {
            str4 = liveswitchCallDataResponse.userId;
        }
        String str10 = str4;
        if ((i & 16) != 0) {
            str5 = liveswitchCallDataResponse.channelId;
        }
        String str11 = str5;
        if ((i & 32) != 0) {
            str6 = liveswitchCallDataResponse.messageChannelId;
        }
        String str12 = str6;
        if ((i & 64) != 0) {
            str7 = liveswitchCallDataResponse.accessToken;
        }
        return liveswitchCallDataResponse.copy(str, str8, str9, str10, str11, str12, str7);
    }

    public final String component1() {
        return this.liveswitchServerUrl;
    }

    public final String component2() {
        return this.applicationId;
    }

    public final String component3() {
        return this.deviceId;
    }

    public final String component4() {
        return this.userId;
    }

    public final String component5() {
        return this.channelId;
    }

    public final String component6() {
        return this.messageChannelId;
    }

    public final String component7() {
        return this.accessToken;
    }

    public final LiveswitchCallDataResponse copy(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        Intrinsics.checkParameterIsNotNull(str, "liveswitchServerUrl");
        Intrinsics.checkParameterIsNotNull(str2, "applicationId");
        Intrinsics.checkParameterIsNotNull(str3, "deviceId");
        Intrinsics.checkParameterIsNotNull(str4, "userId");
        Intrinsics.checkParameterIsNotNull(str5, "channelId");
        Intrinsics.checkParameterIsNotNull(str6, "messageChannelId");
        String str8 = str7;
        Intrinsics.checkParameterIsNotNull(str8, "accessToken");
        return new LiveswitchCallDataResponse(str, str2, str3, str4, str5, str6, str8);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LiveswitchCallDataResponse)) {
            return false;
        }
        LiveswitchCallDataResponse liveswitchCallDataResponse = (LiveswitchCallDataResponse) obj;
        return Intrinsics.areEqual((Object) this.liveswitchServerUrl, (Object) liveswitchCallDataResponse.liveswitchServerUrl) && Intrinsics.areEqual((Object) this.applicationId, (Object) liveswitchCallDataResponse.applicationId) && Intrinsics.areEqual((Object) this.deviceId, (Object) liveswitchCallDataResponse.deviceId) && Intrinsics.areEqual((Object) this.userId, (Object) liveswitchCallDataResponse.userId) && Intrinsics.areEqual((Object) this.channelId, (Object) liveswitchCallDataResponse.channelId) && Intrinsics.areEqual((Object) this.messageChannelId, (Object) liveswitchCallDataResponse.messageChannelId) && Intrinsics.areEqual((Object) this.accessToken, (Object) liveswitchCallDataResponse.accessToken);
    }

    public int hashCode() {
        String str = this.liveswitchServerUrl;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.applicationId;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.deviceId;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.userId;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.channelId;
        int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.messageChannelId;
        int hashCode6 = (hashCode5 + (str6 != null ? str6.hashCode() : 0)) * 31;
        String str7 = this.accessToken;
        if (str7 != null) {
            i = str7.hashCode();
        }
        return hashCode6 + i;
    }

    public String toString() {
        return "LiveswitchCallDataResponse(liveswitchServerUrl=" + this.liveswitchServerUrl + ", applicationId=" + this.applicationId + ", deviceId=" + this.deviceId + ", userId=" + this.userId + ", channelId=" + this.channelId + ", messageChannelId=" + this.messageChannelId + ", accessToken=" + this.accessToken + ")";
    }

    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        parcel.writeString(this.liveswitchServerUrl);
        parcel.writeString(this.applicationId);
        parcel.writeString(this.deviceId);
        parcel.writeString(this.userId);
        parcel.writeString(this.channelId);
        parcel.writeString(this.messageChannelId);
        parcel.writeString(this.accessToken);
    }

    public LiveswitchCallDataResponse(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        Intrinsics.checkParameterIsNotNull(str, "liveswitchServerUrl");
        Intrinsics.checkParameterIsNotNull(str2, "applicationId");
        Intrinsics.checkParameterIsNotNull(str3, "deviceId");
        Intrinsics.checkParameterIsNotNull(str4, "userId");
        Intrinsics.checkParameterIsNotNull(str5, "channelId");
        Intrinsics.checkParameterIsNotNull(str6, "messageChannelId");
        Intrinsics.checkParameterIsNotNull(str7, "accessToken");
        this.liveswitchServerUrl = str;
        this.applicationId = str2;
        this.deviceId = str3;
        this.userId = str4;
        this.channelId = str5;
        this.messageChannelId = str6;
        this.accessToken = str7;
    }

    public final String getApplicationId() {
        return this.applicationId;
    }

    public final String getLiveswitchServerUrl() {
        return this.liveswitchServerUrl;
    }

    public final String getChannelId() {
        return this.channelId;
    }

    public final String getDeviceId() {
        return this.deviceId;
    }

    public final String getUserId() {
        return this.userId;
    }

    public final String getAccessToken() {
        return this.accessToken;
    }

    public final String getMessageChannelId() {
        return this.messageChannelId;
    }
}
