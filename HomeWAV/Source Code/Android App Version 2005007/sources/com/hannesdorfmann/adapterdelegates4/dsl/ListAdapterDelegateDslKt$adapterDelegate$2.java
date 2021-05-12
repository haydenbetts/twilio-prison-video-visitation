package com.hannesdorfmann.adapterdelegates4.dsl;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001\"\n\b\u0000\u0010\u0003\u0018\u0001*\u0002H\u0004\"\u0004\b\u0001\u0010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\n¢\u0006\u0002\b\t"}, d2 = {"<anonymous>", "Landroid/view/View;", "kotlin.jvm.PlatformType", "I", "T", "parent", "Landroid/view/ViewGroup;", "layout", "", "invoke"}, k = 3, mv = {1, 1, 15})
/* compiled from: ListAdapterDelegateDsl.kt */
public final class ListAdapterDelegateDslKt$adapterDelegate$2 extends Lambda implements Function2<ViewGroup, Integer, View> {
    public static final ListAdapterDelegateDslKt$adapterDelegate$2 INSTANCE = new ListAdapterDelegateDslKt$adapterDelegate$2();

    public ListAdapterDelegateDslKt$adapterDelegate$2() {
        super(2);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return invoke((ViewGroup) obj, ((Number) obj2).intValue());
    }

    public final View invoke(ViewGroup viewGroup, int i) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(i, viewGroup, false);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "LayoutInflater.from(pare…          false\n        )");
        return inflate;
    }
}
