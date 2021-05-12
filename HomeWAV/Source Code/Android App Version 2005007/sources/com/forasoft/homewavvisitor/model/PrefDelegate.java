package com.forasoft.homewavvisitor.model;

import android.content.Context;
import android.content.SharedPreferences;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00028\u0000¢\u0006\u0002\u0010\u0006J$\u0010\u0010\u001a\u00028\u00002\b\u0010\u0011\u001a\u0004\u0018\u00010\u00022\n\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u0013H¦\u0002¢\u0006\u0002\u0010\u0014J,\u0010\u0015\u001a\u00020\u00162\b\u0010\u0011\u001a\u0004\u0018\u00010\u00022\n\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u00132\u0006\u0010\u0017\u001a\u00028\u0000H¦\u0002¢\u0006\u0002\u0010\u0018R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\u00028\u0000X\u0004¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\n\u001a\u00020\u000b8DX\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\r¨\u0006\u0019"}, d2 = {"Lcom/forasoft/homewavvisitor/model/PrefDelegate;", "T", "", "context", "Landroid/content/Context;", "default", "(Landroid/content/Context;Ljava/lang/Object;)V", "getDefault", "()Ljava/lang/Object;", "Ljava/lang/Object;", "prefs", "Landroid/content/SharedPreferences;", "getPrefs", "()Landroid/content/SharedPreferences;", "prefs$delegate", "Lkotlin/Lazy;", "getValue", "thisRef", "property", "Lkotlin/reflect/KProperty;", "(Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "setValue", "", "value", "(Ljava/lang/Object;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: PrefDelegate.kt */
public abstract class PrefDelegate<T> {
    /* access modifiers changed from: private */
    public final Context context;

    /* renamed from: default  reason: not valid java name */
    private final T f0default;
    private final Lazy prefs$delegate = LazyKt.lazy(new PrefDelegate$prefs$2(this));

    /* access modifiers changed from: protected */
    public final SharedPreferences getPrefs() {
        return (SharedPreferences) this.prefs$delegate.getValue();
    }

    public abstract T getValue(Object obj, KProperty<?> kProperty);

    public abstract void setValue(Object obj, KProperty<?> kProperty, T t);

    public PrefDelegate(Context context2, T t) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        this.context = context2;
        this.f0default = t;
    }

    /* access modifiers changed from: protected */
    public final T getDefault() {
        return this.f0default;
    }
}
