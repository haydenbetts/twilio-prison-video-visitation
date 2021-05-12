package com.forasoft.homewavvisitor.model.server.response;

import androidx.core.app.NotificationCompat;
import com.forasoft.homewavvisitor.model.data.Call;
import com.forasoft.homewavvisitor.model.data.CallStatus;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/forasoft/homewavvisitor/model/server/response/CallStatusResponse;", "", "status", "Lcom/forasoft/homewavvisitor/model/data/CallStatus;", "call", "Lcom/forasoft/homewavvisitor/model/data/Call;", "(Lcom/forasoft/homewavvisitor/model/data/CallStatus;Lcom/forasoft/homewavvisitor/model/data/Call;)V", "getCall", "()Lcom/forasoft/homewavvisitor/model/data/Call;", "getStatus", "()Lcom/forasoft/homewavvisitor/model/data/CallStatus;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: CallStatusResponse.kt */
public final class CallStatusResponse {
    private final Call call;
    private final CallStatus status;

    public static /* synthetic */ CallStatusResponse copy$default(CallStatusResponse callStatusResponse, CallStatus callStatus, Call call2, int i, Object obj) {
        if ((i & 1) != 0) {
            callStatus = callStatusResponse.status;
        }
        if ((i & 2) != 0) {
            call2 = callStatusResponse.call;
        }
        return callStatusResponse.copy(callStatus, call2);
    }

    public final CallStatus component1() {
        return this.status;
    }

    public final Call component2() {
        return this.call;
    }

    public final CallStatusResponse copy(CallStatus callStatus, Call call2) {
        Intrinsics.checkParameterIsNotNull(callStatus, NotificationCompat.CATEGORY_STATUS);
        Intrinsics.checkParameterIsNotNull(call2, NotificationCompat.CATEGORY_CALL);
        return new CallStatusResponse(callStatus, call2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CallStatusResponse)) {
            return false;
        }
        CallStatusResponse callStatusResponse = (CallStatusResponse) obj;
        return Intrinsics.areEqual((Object) this.status, (Object) callStatusResponse.status) && Intrinsics.areEqual((Object) this.call, (Object) callStatusResponse.call);
    }

    public int hashCode() {
        CallStatus callStatus = this.status;
        int i = 0;
        int hashCode = (callStatus != null ? callStatus.hashCode() : 0) * 31;
        Call call2 = this.call;
        if (call2 != null) {
            i = call2.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "CallStatusResponse(status=" + this.status + ", call=" + this.call + ")";
    }

    public CallStatusResponse(CallStatus callStatus, Call call2) {
        Intrinsics.checkParameterIsNotNull(callStatus, NotificationCompat.CATEGORY_STATUS);
        Intrinsics.checkParameterIsNotNull(call2, NotificationCompat.CATEGORY_CALL);
        this.status = callStatus;
        this.call = call2;
    }

    public final Call getCall() {
        return this.call;
    }

    public final CallStatus getStatus() {
        return this.status;
    }
}
