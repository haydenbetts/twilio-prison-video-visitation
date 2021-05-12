package com.forasoft.homewavvisitor.presentation.presenter.visits;

import com.forasoft.homewavvisitor.model.data.account.ScheduledVisit;
import io.reactivex.functions.BiFunction;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SpreadBuilder;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "Lcom/forasoft/homewavvisitor/model/data/account/ScheduledVisit;", "t1", "t2", "apply"}, k = 3, mv = {1, 1, 16})
/* compiled from: DateChooserPresenter.kt */
final class DateChooserPresenter$loadSlotsFromDb$1<T1, T2, R> implements BiFunction<List<? extends ScheduledVisit>, List<? extends ScheduledVisit>, List<? extends ScheduledVisit>> {
    public static final DateChooserPresenter$loadSlotsFromDb$1 INSTANCE = new DateChooserPresenter$loadSlotsFromDb$1();

    DateChooserPresenter$loadSlotsFromDb$1() {
    }

    public final List<ScheduledVisit> apply(List<ScheduledVisit> list, List<ScheduledVisit> list2) {
        Intrinsics.checkParameterIsNotNull(list, "t1");
        Intrinsics.checkParameterIsNotNull(list2, "t2");
        SpreadBuilder spreadBuilder = new SpreadBuilder(2);
        Object[] array = list.toArray(new ScheduledVisit[0]);
        if (array != null) {
            spreadBuilder.addSpread(array);
            Object[] array2 = list2.toArray(new ScheduledVisit[0]);
            if (array2 != null) {
                spreadBuilder.addSpread(array2);
                return CollectionsKt.listOf((ScheduledVisit[]) spreadBuilder.toArray(new ScheduledVisit[spreadBuilder.size()]));
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }
}
