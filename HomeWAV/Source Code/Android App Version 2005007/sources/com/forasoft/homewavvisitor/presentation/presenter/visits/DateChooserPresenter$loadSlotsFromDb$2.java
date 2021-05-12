package com.forasoft.homewavvisitor.presentation.presenter.visits;

import com.forasoft.homewavvisitor.model.data.DaySlot;
import com.forasoft.homewavvisitor.model.data.TimeSlot;
import com.forasoft.homewavvisitor.model.data.account.ScheduledVisit;
import com.forasoft.homewavvisitor.presentation.view.visits.DateChooserView;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.ZoneId;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "", "Lcom/forasoft/homewavvisitor/model/data/account/ScheduledVisit;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: DateChooserPresenter.kt */
final class DateChooserPresenter$loadSlotsFromDb$2<T> implements Consumer<List<? extends ScheduledVisit>> {
    final /* synthetic */ DateChooserPresenter this$0;

    DateChooserPresenter$loadSlotsFromDb$2(DateChooserPresenter dateChooserPresenter) {
        this.this$0 = dateChooserPresenter;
    }

    public final void accept(List<ScheduledVisit> list) {
        Intrinsics.checkExpressionValueIsNotNull(list, "it");
        Iterable<ScheduledVisit> iterable = list;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (ScheduledVisit scheduledVisit : iterable) {
            arrayList.add(new TimeSlot("", "", Intrinsics.areEqual((Object) scheduledVisit.getStatus(), (Object) "confirmed") ? TimeSlot.Status.CONFIRMED : TimeSlot.Status.Companion.getStatusByName(scheduledVisit.getStatus()), scheduledVisit.getTimestamp(), (Integer) null, (Integer) null, 48, (DefaultConstructorMarker) null));
        }
        Map linkedHashMap = new LinkedHashMap();
        for (Object next : (List) arrayList) {
            LocalDate localDate = Instant.ofEpochMilli(((TimeSlot) next).getTimestamp() * ((long) 1000)).atZone(ZoneId.systemDefault()).toLocalDate();
            Object obj = linkedHashMap.get(localDate);
            if (obj == null) {
                obj = new ArrayList();
                linkedHashMap.put(localDate, obj);
            }
            ((List) obj).add(next);
        }
        Map linkedHashMap2 = new LinkedHashMap(MapsKt.mapCapacity(linkedHashMap.size()));
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            linkedHashMap2.put(entry.getKey(), new DaySlot((List) entry.getValue(), true));
        }
        for (Map.Entry value : linkedHashMap2.entrySet()) {
            ((DateChooserView) this.this$0.getViewState()).updateCalendar(CollectionsKt.listOf(value.getValue()));
        }
    }
}
