package org.jetbrains.anko.sdk15.listeners;

import android.view.View;
import android.widget.AdapterView;
import kotlin.Metadata;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
/* compiled from: Listeners.kt */
public final class Sdk15ListenersListenersKt$sam$i$android_widget_AdapterView_OnItemClickListener$0 implements AdapterView.OnItemClickListener {
    private final /* synthetic */ Function4 function;

    public Sdk15ListenersListenersKt$sam$i$android_widget_AdapterView_OnItemClickListener$0(Function4 function4) {
        this.function = function4;
    }

    public final /* synthetic */ void onItemClick(AdapterView adapterView, View view, int i, long j) {
        Intrinsics.checkExpressionValueIsNotNull(this.function.invoke(adapterView, view, Integer.valueOf(i), Long.valueOf(j)), "invoke(...)");
    }
}
