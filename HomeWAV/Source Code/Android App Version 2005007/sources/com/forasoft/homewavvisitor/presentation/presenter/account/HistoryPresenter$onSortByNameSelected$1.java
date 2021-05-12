package com.forasoft.homewavvisitor.presentation.presenter.account;

import com.forasoft.homewavvisitor.model.data.account.HistoryItem;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/forasoft/homewavvisitor/model/data/account/HistoryItem;", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: HistoryPresenter.kt */
final class HistoryPresenter$onSortByNameSelected$1 extends Lambda implements Function1<HistoryItem, Boolean> {
    final /* synthetic */ String $inmateName;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    HistoryPresenter$onSortByNameSelected$1(String str) {
        super(1);
        this.$inmateName = str;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return Boolean.valueOf(invoke((HistoryItem) obj));
    }

    public final boolean invoke(HistoryItem historyItem) {
        Intrinsics.checkParameterIsNotNull(historyItem, "it");
        return Intrinsics.areEqual((Object) historyItem.getFullName(), (Object) this.$inmateName);
    }
}
