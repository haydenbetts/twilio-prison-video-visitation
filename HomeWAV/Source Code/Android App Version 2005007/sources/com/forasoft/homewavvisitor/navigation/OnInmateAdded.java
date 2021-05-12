package com.forasoft.homewavvisitor.navigation;

import com.forasoft.homewavvisitor.model.data.Inmate;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/OnInmateAdded;", "Lcom/forasoft/homewavvisitor/navigation/BusEvent;", "inmate", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "(Lcom/forasoft/homewavvisitor/model/data/Inmate;)V", "getInmate", "()Lcom/forasoft/homewavvisitor/model/data/Inmate;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: BusNotifier.kt */
public final class OnInmateAdded extends BusEvent {
    private final Inmate inmate;

    public static /* synthetic */ OnInmateAdded copy$default(OnInmateAdded onInmateAdded, Inmate inmate2, int i, Object obj) {
        if ((i & 1) != 0) {
            inmate2 = onInmateAdded.inmate;
        }
        return onInmateAdded.copy(inmate2);
    }

    public final Inmate component1() {
        return this.inmate;
    }

    public final OnInmateAdded copy(Inmate inmate2) {
        Intrinsics.checkParameterIsNotNull(inmate2, "inmate");
        return new OnInmateAdded(inmate2);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            return (obj instanceof OnInmateAdded) && Intrinsics.areEqual((Object) this.inmate, (Object) ((OnInmateAdded) obj).inmate);
        }
        return true;
    }

    public int hashCode() {
        Inmate inmate2 = this.inmate;
        if (inmate2 != null) {
            return inmate2.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "OnInmateAdded(inmate=" + this.inmate + ")";
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public OnInmateAdded(Inmate inmate2) {
        super((DefaultConstructorMarker) null);
        Intrinsics.checkParameterIsNotNull(inmate2, "inmate");
        this.inmate = inmate2;
    }

    public final Inmate getInmate() {
        return this.inmate;
    }
}
