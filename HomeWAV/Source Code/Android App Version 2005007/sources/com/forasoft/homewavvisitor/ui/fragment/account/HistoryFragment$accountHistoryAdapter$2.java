package com.forasoft.homewavvisitor.ui.fragment.account;

import com.forasoft.homewavvisitor.presentation.adapter.account.AccountHistoryAdapter;
import com.forasoft.homewavvisitor.toothpick.DI;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import toothpick.Toothpick;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Lcom/forasoft/homewavvisitor/presentation/adapter/account/AccountHistoryAdapter;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: HistoryFragment.kt */
final class HistoryFragment$accountHistoryAdapter$2 extends Lambda implements Function0<AccountHistoryAdapter> {
    public static final HistoryFragment$accountHistoryAdapter$2 INSTANCE = new HistoryFragment$accountHistoryAdapter$2();

    HistoryFragment$accountHistoryAdapter$2() {
        super(0);
    }

    public final AccountHistoryAdapter invoke() {
        return (AccountHistoryAdapter) Toothpick.openScope(DI.SERVER_SCOPE).getInstance(AccountHistoryAdapter.class);
    }
}
