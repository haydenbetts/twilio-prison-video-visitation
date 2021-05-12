package com.forasoft.homewavvisitor.model;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0002¢\u0006\u0002\u0010\u0006J$\u0010\u0007\u001a\u00020\u00022\b\u0010\b\u001a\u0004\u0018\u00010\t2\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u000bH\u0002¢\u0006\u0002\u0010\fJ'\u0010\r\u001a\u00020\u000e2\b\u0010\b\u001a\u0004\u0018\u00010\t2\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u000b2\u0006\u0010\u000f\u001a\u00020\u0002H\u0002¨\u0006\u0010"}, d2 = {"Lcom/forasoft/homewavvisitor/model/BooleanPrefDelegate;", "Lcom/forasoft/homewavvisitor/model/PrefDelegate;", "", "context", "Landroid/content/Context;", "default", "(Landroid/content/Context;Z)V", "getValue", "thisRef", "", "property", "Lkotlin/reflect/KProperty;", "(Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Boolean;", "setValue", "", "value", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: PrefDelegate.kt */
public final class BooleanPrefDelegate extends PrefDelegate<Boolean> {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BooleanPrefDelegate(Context context, boolean z) {
        super(context, Boolean.valueOf(z));
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ BooleanPrefDelegate(Context context, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? false : z);
    }

    public /* bridge */ /* synthetic */ void setValue(Object obj, KProperty kProperty, Object obj2) {
        setValue(obj, (KProperty<?>) kProperty, ((Boolean) obj2).booleanValue());
    }

    public Boolean getValue(Object obj, KProperty<?> kProperty) {
        Intrinsics.checkParameterIsNotNull(kProperty, "property");
        return Boolean.valueOf(getPrefs().getBoolean(kProperty.getName(), ((Boolean) getDefault()).booleanValue()));
    }

    public void setValue(Object obj, KProperty<?> kProperty, boolean z) {
        Intrinsics.checkParameterIsNotNull(kProperty, "property");
        getPrefs().edit().putBoolean(kProperty.getName(), z).apply();
    }
}
