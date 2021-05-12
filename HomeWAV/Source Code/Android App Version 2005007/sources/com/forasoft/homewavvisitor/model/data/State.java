package com.forasoft.homewavvisitor.model.data;

import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BC\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\t\u001a\u00020\u0004¢\u0006\u0002\u0010\nJ\u0015\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0003J\u0015\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0004HÆ\u0003JO\u0010\u0016\u001a\u00020\u00002\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\t\u001a\u00020\u0004HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u0004HÖ\u0001R\"\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0016\u0010\t\u001a\u00020\u00048\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001d"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/State;", "", "status", "", "", "calls", "", "Lcom/forasoft/homewavvisitor/model/data/Call;", "balances", "unreadMessages", "(Ljava/util/Map;Ljava/util/List;Ljava/util/Map;Ljava/lang/String;)V", "getBalances", "()Ljava/util/Map;", "getCalls", "()Ljava/util/List;", "getStatus", "getUnreadMessages", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: State.kt */
public final class State {
    @SerializedName("credit_balances")
    private final Map<String, String> balances;
    private final List<Call> calls;
    private final Map<String, String> status;
    @SerializedName("unread_messages_count")
    private final String unreadMessages;

    public static /* synthetic */ State copy$default(State state, Map<String, String> map, List<Call> list, Map<String, String> map2, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            map = state.status;
        }
        if ((i & 2) != 0) {
            list = state.calls;
        }
        if ((i & 4) != 0) {
            map2 = state.balances;
        }
        if ((i & 8) != 0) {
            str = state.unreadMessages;
        }
        return state.copy(map, list, map2, str);
    }

    public final Map<String, String> component1() {
        return this.status;
    }

    public final List<Call> component2() {
        return this.calls;
    }

    public final Map<String, String> component3() {
        return this.balances;
    }

    public final String component4() {
        return this.unreadMessages;
    }

    public final State copy(Map<String, String> map, List<Call> list, Map<String, String> map2, String str) {
        Intrinsics.checkParameterIsNotNull(map, NotificationCompat.CATEGORY_STATUS);
        Intrinsics.checkParameterIsNotNull(list, "calls");
        Intrinsics.checkParameterIsNotNull(map2, "balances");
        Intrinsics.checkParameterIsNotNull(str, "unreadMessages");
        return new State(map, list, map2, str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof State)) {
            return false;
        }
        State state = (State) obj;
        return Intrinsics.areEqual((Object) this.status, (Object) state.status) && Intrinsics.areEqual((Object) this.calls, (Object) state.calls) && Intrinsics.areEqual((Object) this.balances, (Object) state.balances) && Intrinsics.areEqual((Object) this.unreadMessages, (Object) state.unreadMessages);
    }

    public int hashCode() {
        Map<String, String> map = this.status;
        int i = 0;
        int hashCode = (map != null ? map.hashCode() : 0) * 31;
        List<Call> list = this.calls;
        int hashCode2 = (hashCode + (list != null ? list.hashCode() : 0)) * 31;
        Map<String, String> map2 = this.balances;
        int hashCode3 = (hashCode2 + (map2 != null ? map2.hashCode() : 0)) * 31;
        String str = this.unreadMessages;
        if (str != null) {
            i = str.hashCode();
        }
        return hashCode3 + i;
    }

    public String toString() {
        return "State(status=" + this.status + ", calls=" + this.calls + ", balances=" + this.balances + ", unreadMessages=" + this.unreadMessages + ")";
    }

    public State(Map<String, String> map, List<Call> list, Map<String, String> map2, String str) {
        Intrinsics.checkParameterIsNotNull(map, NotificationCompat.CATEGORY_STATUS);
        Intrinsics.checkParameterIsNotNull(list, "calls");
        Intrinsics.checkParameterIsNotNull(map2, "balances");
        Intrinsics.checkParameterIsNotNull(str, "unreadMessages");
        this.status = map;
        this.calls = list;
        this.balances = map2;
        this.unreadMessages = str;
    }

    public final Map<String, String> getStatus() {
        return this.status;
    }

    public final List<Call> getCalls() {
        return this.calls;
    }

    public final Map<String, String> getBalances() {
        return this.balances;
    }

    public final String getUnreadMessages() {
        return this.unreadMessages;
    }
}
