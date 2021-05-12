package com.forasoft.homewavvisitor;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0006HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0006HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0016"}, d2 = {"Lcom/forasoft/homewavvisitor/AppInfo;", "", "deviceType", "", "versionName", "versionCode", "", "(Ljava/lang/String;Ljava/lang/String;I)V", "getDeviceType", "()Ljava/lang/String;", "getVersionCode", "()I", "getVersionName", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: AppInfo.kt */
public final class AppInfo {
    private final String deviceType;
    private final int versionCode;
    private final String versionName;

    public static /* synthetic */ AppInfo copy$default(AppInfo appInfo, String str, String str2, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = appInfo.deviceType;
        }
        if ((i2 & 2) != 0) {
            str2 = appInfo.versionName;
        }
        if ((i2 & 4) != 0) {
            i = appInfo.versionCode;
        }
        return appInfo.copy(str, str2, i);
    }

    public final String component1() {
        return this.deviceType;
    }

    public final String component2() {
        return this.versionName;
    }

    public final int component3() {
        return this.versionCode;
    }

    public final AppInfo copy(String str, String str2, int i) {
        Intrinsics.checkParameterIsNotNull(str, "deviceType");
        Intrinsics.checkParameterIsNotNull(str2, "versionName");
        return new AppInfo(str, str2, i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AppInfo)) {
            return false;
        }
        AppInfo appInfo = (AppInfo) obj;
        return Intrinsics.areEqual((Object) this.deviceType, (Object) appInfo.deviceType) && Intrinsics.areEqual((Object) this.versionName, (Object) appInfo.versionName) && this.versionCode == appInfo.versionCode;
    }

    public int hashCode() {
        String str = this.deviceType;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.versionName;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return ((hashCode + i) * 31) + this.versionCode;
    }

    public String toString() {
        return "AppInfo(deviceType=" + this.deviceType + ", versionName=" + this.versionName + ", versionCode=" + this.versionCode + ")";
    }

    public AppInfo(String str, String str2, int i) {
        Intrinsics.checkParameterIsNotNull(str, "deviceType");
        Intrinsics.checkParameterIsNotNull(str2, "versionName");
        this.deviceType = str;
        this.versionName = str2;
        this.versionCode = i;
    }

    public final String getDeviceType() {
        return this.deviceType;
    }

    public final String getVersionName() {
        return this.versionName;
    }

    public final int getVersionCode() {
        return this.versionCode;
    }
}
