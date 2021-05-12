package com.forasoft.homewavvisitor.presentation.presenter.account;

import com.forasoft.homewavvisitor.model.data.auth.User;
import io.reactivex.functions.Consumer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lcom/forasoft/homewavvisitor/model/data/auth/User;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: HistoryPresenter.kt */
final class HistoryPresenter$filterSinceCreation$1<T> implements Consumer<User> {
    final /* synthetic */ HistoryPresenter this$0;

    HistoryPresenter$filterSinceCreation$1(HistoryPresenter historyPresenter) {
        this.this$0 = historyPresenter;
    }

    public final void accept(User user) {
        Date parse = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(user.getCreated());
        Calendar instance = Calendar.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(instance, "calendar");
        instance.setTime(parse);
        this.this$0.showToPresentPeriod(instance);
    }
}
