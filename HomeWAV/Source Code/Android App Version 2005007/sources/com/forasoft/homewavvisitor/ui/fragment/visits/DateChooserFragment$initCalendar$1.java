package com.forasoft.homewavvisitor.ui.fragment.visits;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.threeten.bp.LocalDateTime;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lorg/threeten/bp/LocalDateTime;", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: DateChooserFragment.kt */
final class DateChooserFragment$initCalendar$1 extends Lambda implements Function1<LocalDateTime, Unit> {
    final /* synthetic */ DateChooserFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DateChooserFragment$initCalendar$1(DateChooserFragment dateChooserFragment) {
        super(1);
        this.this$0 = dateChooserFragment;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((LocalDateTime) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(LocalDateTime localDateTime) {
        this.this$0.getPresenter().onDateSelected(localDateTime);
    }
}
