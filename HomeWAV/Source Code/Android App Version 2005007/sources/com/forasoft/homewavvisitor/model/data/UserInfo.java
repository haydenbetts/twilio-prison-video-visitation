package com.forasoft.homewavvisitor.model.data;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0006\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u0007\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\b\u001a\u00020\u00032\b\u0010\t\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\n\u001a\u00020\u000bHÖ\u0001J\t\u0010\f\u001a\u00020\rHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0005¨\u0006\u000e"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/UserInfo;", "", "isDisapproved", "", "(Z)V", "()Z", "component1", "copy", "equals", "other", "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: UserInfo.kt */
public final class UserInfo {
    private final boolean isDisapproved;

    public static /* synthetic */ UserInfo copy$default(UserInfo userInfo, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = userInfo.isDisapproved;
        }
        return userInfo.copy(z);
    }

    public final boolean component1() {
        return this.isDisapproved;
    }

    public final UserInfo copy(boolean z) {
        return new UserInfo(z);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            return (obj instanceof UserInfo) && this.isDisapproved == ((UserInfo) obj).isDisapproved;
        }
        return true;
    }

    public int hashCode() {
        boolean z = this.isDisapproved;
        if (z) {
            return 1;
        }
        return z ? 1 : 0;
    }

    public String toString() {
        return "UserInfo(isDisapproved=" + this.isDisapproved + ")";
    }

    public UserInfo(boolean z) {
        this.isDisapproved = z;
    }

    public final boolean isDisapproved() {
        return this.isDisapproved;
    }
}
