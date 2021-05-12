package com.forasoft.homewavvisitor.model.data.account;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/account/History;", "", "items", "", "Lcom/forasoft/homewavvisitor/model/data/account/HistoryItem;", "(Ljava/util/List;)V", "getItems", "()Ljava/util/List;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: History.kt */
public final class History {
    private final List<HistoryItem> items;

    public History(List<HistoryItem> list) {
        Intrinsics.checkParameterIsNotNull(list, "items");
        this.items = list;
    }

    public final List<HistoryItem> getItems() {
        return this.items;
    }
}
