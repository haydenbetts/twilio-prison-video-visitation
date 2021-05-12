package com.forasoft.homewavvisitor.presentation.presenter.visits;

import android.content.Intent;
import android.provider.CalendarContract;
import com.forasoft.homewavvisitor.model.data.account.ScheduledVisit;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lcom/forasoft/homewavvisitor/model/data/account/ScheduledVisit;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: VisitDetailsPresenter.kt */
final class VisitDetailsPresenter$onAddClicked$1<T> implements Consumer<ScheduledVisit> {
    final /* synthetic */ VisitDetailsPresenter this$0;

    VisitDetailsPresenter$onAddClicked$1(VisitDetailsPresenter visitDetailsPresenter) {
        this.this$0 = visitDetailsPresenter;
    }

    public final void accept(ScheduledVisit scheduledVisit) {
        Intent putExtra = new Intent("android.intent.action.INSERT").setData(CalendarContract.Events.CONTENT_URI).putExtra("beginTime", scheduledVisit.getTimestamp() * ((long) 1000));
        Intent addFlags = putExtra.putExtra("title", "Visit " + scheduledVisit.getInmate()).putExtra("eventLocation", scheduledVisit.getFacility()).addFlags(268435456);
        Intrinsics.checkExpressionValueIsNotNull(addFlags, "Intent(Intent.ACTION_INS…s(FLAG_ACTIVITY_NEW_TASK)");
        this.this$0.context.startActivity(addFlags);
    }
}
