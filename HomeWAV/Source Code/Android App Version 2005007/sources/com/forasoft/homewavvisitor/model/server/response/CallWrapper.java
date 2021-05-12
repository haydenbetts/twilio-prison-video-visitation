package com.forasoft.homewavvisitor.model.server.response;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.app.NotificationCompat;
import com.forasoft.homewavvisitor.model.data.CallEntity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\u0019\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u001c"}, d2 = {"Lcom/forasoft/homewavvisitor/model/server/response/CallWrapper;", "Landroid/os/Parcelable;", "call", "Lcom/forasoft/homewavvisitor/model/data/CallEntity;", "max_call_duration", "", "(Lcom/forasoft/homewavvisitor/model/data/CallEntity;J)V", "getCall", "()Lcom/forasoft/homewavvisitor/model/data/CallEntity;", "getMax_call_duration", "()J", "component1", "component2", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: CallWrapper.kt */
public final class CallWrapper implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Creator();
    private final CallEntity call;
    private final long max_call_duration;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public static class Creator implements Parcelable.Creator {
        public final Object createFromParcel(Parcel parcel) {
            Intrinsics.checkParameterIsNotNull(parcel, "in");
            return new CallWrapper((CallEntity) CallEntity.CREATOR.createFromParcel(parcel), parcel.readLong());
        }

        public final Object[] newArray(int i) {
            return new CallWrapper[i];
        }
    }

    public static /* synthetic */ CallWrapper copy$default(CallWrapper callWrapper, CallEntity callEntity, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            callEntity = callWrapper.call;
        }
        if ((i & 2) != 0) {
            j = callWrapper.max_call_duration;
        }
        return callWrapper.copy(callEntity, j);
    }

    public final CallEntity component1() {
        return this.call;
    }

    public final long component2() {
        return this.max_call_duration;
    }

    public final CallWrapper copy(CallEntity callEntity, long j) {
        Intrinsics.checkParameterIsNotNull(callEntity, NotificationCompat.CATEGORY_CALL);
        return new CallWrapper(callEntity, j);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CallWrapper)) {
            return false;
        }
        CallWrapper callWrapper = (CallWrapper) obj;
        return Intrinsics.areEqual((Object) this.call, (Object) callWrapper.call) && this.max_call_duration == callWrapper.max_call_duration;
    }

    public int hashCode() {
        CallEntity callEntity = this.call;
        int hashCode = callEntity != null ? callEntity.hashCode() : 0;
        long j = this.max_call_duration;
        return (hashCode * 31) + ((int) (j ^ (j >>> 32)));
    }

    public String toString() {
        return "CallWrapper(call=" + this.call + ", max_call_duration=" + this.max_call_duration + ")";
    }

    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        this.call.writeToParcel(parcel, 0);
        parcel.writeLong(this.max_call_duration);
    }

    public CallWrapper(CallEntity callEntity, long j) {
        Intrinsics.checkParameterIsNotNull(callEntity, NotificationCompat.CATEGORY_CALL);
        this.call = callEntity;
        this.max_call_duration = j;
    }

    public final CallEntity getCall() {
        return this.call;
    }

    public final long getMax_call_duration() {
        return this.max_call_duration;
    }
}
