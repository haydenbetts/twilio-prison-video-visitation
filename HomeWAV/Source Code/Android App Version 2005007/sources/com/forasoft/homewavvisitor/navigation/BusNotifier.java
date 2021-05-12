package com.forasoft.homewavvisitor.navigation;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0007¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005J\u000e\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/forasoft/homewavvisitor/navigation/BusNotifier;", "", "()V", "listeners", "", "Lcom/forasoft/homewavvisitor/navigation/BusListener;", "add", "", "listener", "notify", "event", "Lcom/forasoft/homewavvisitor/navigation/BusEvent;", "remove", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: BusNotifier.kt */
public final class BusNotifier {
    private Set<? extends BusListener> listeners = SetsKt.emptySet();

    public final void add(BusListener busListener) {
        Intrinsics.checkParameterIsNotNull(busListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.listeners = SetsKt.plus(this.listeners, busListener);
    }

    public final void remove(BusListener busListener) {
        Intrinsics.checkParameterIsNotNull(busListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.listeners = SetsKt.minus(this.listeners, busListener);
    }

    public final void notify(BusEvent busEvent) {
        Intrinsics.checkParameterIsNotNull(busEvent, "event");
        for (BusListener onEvent : this.listeners) {
            onEvent.onEvent(busEvent);
        }
    }
}
