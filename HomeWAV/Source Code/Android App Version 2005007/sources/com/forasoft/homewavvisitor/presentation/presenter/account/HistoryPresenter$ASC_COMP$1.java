package com.forasoft.homewavvisitor.presentation.presenter.account;

import com.forasoft.homewavvisitor.model.data.account.HistoryItem;
import java.util.Comparator;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "o1", "Lcom/forasoft/homewavvisitor/model/data/account/HistoryItem;", "kotlin.jvm.PlatformType", "o2", "compare"}, k = 3, mv = {1, 1, 16})
/* compiled from: HistoryPresenter.kt */
final class HistoryPresenter$ASC_COMP$1<T> implements Comparator<HistoryItem> {
    public static final HistoryPresenter$ASC_COMP$1 INSTANCE = new HistoryPresenter$ASC_COMP$1();

    HistoryPresenter$ASC_COMP$1() {
    }

    public final int compare(HistoryItem historyItem, HistoryItem historyItem2) {
        return (int) (historyItem.getTimestamp() - historyItem2.getTimestamp());
    }
}
