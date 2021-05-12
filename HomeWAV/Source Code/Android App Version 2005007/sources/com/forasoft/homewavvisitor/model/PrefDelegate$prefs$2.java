package com.forasoft.homewavvisitor.model;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001\"\u0004\b\u0000\u0010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "T", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: PrefDelegate.kt */
final class PrefDelegate$prefs$2 extends Lambda implements Function0<SharedPreferences> {
    final /* synthetic */ PrefDelegate this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PrefDelegate$prefs$2(PrefDelegate prefDelegate) {
        super(0);
        this.this$0 = prefDelegate;
    }

    public final SharedPreferences invoke() {
        return PreferenceManager.getDefaultSharedPreferences(this.this$0.context);
    }
}
