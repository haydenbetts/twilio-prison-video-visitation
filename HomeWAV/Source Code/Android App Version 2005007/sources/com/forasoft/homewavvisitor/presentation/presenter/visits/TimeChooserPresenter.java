package com.forasoft.homewavvisitor.presentation.presenter.visits;

import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.data.DaySlot;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.model.data.TimeSlot;
import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import com.forasoft.homewavvisitor.presentation.BasePresenter;
import com.forasoft.homewavvisitor.presentation.extensions.DateExtensionsKt;
import com.forasoft.homewavvisitor.presentation.view.visits.TimeChooserView;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxkotlin.DisposableKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import moxy.InjectViewState;
import ru.terrakok.cicerone.Router;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B'\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0006\u0010\u000f\u001a\u00020\u0010J\b\u0010\u0011\u001a\u00020\u0010H\u0014J\u0006\u0010\u0012\u001a\u00020\u0010J\u0006\u0010\u0013\u001a\u00020\u0010J\u000e\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\rR\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\f\u001a\u0004\u0018\u00010\rX\u000e¢\u0006\u0004\n\u0002\u0010\u000e¨\u0006\u0016"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/presenter/visits/TimeChooserPresenter;", "Lcom/forasoft/homewavvisitor/presentation/BasePresenter;", "Lcom/forasoft/homewavvisitor/presentation/view/visits/TimeChooserView;", "inmate", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "router", "Lru/terrakok/cicerone/Router;", "daySlot", "Lcom/forasoft/homewavvisitor/model/data/DaySlot;", "api", "Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;", "(Lcom/forasoft/homewavvisitor/model/data/Inmate;Lru/terrakok/cicerone/Router;Lcom/forasoft/homewavvisitor/model/data/DaySlot;Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;)V", "selectedTime", "", "Ljava/lang/Long;", "onBackPressed", "", "onFirstViewAttach", "onInviteClicked", "onInviteConfirmed", "onTimeSelected", "start", "app_release"}, k = 1, mv = {1, 1, 16})
@InjectViewState
/* compiled from: TimeChooserPresenter.kt */
public final class TimeChooserPresenter extends BasePresenter<TimeChooserView> {
    private final HomewavApi api;
    private final DaySlot daySlot;
    private final Inmate inmate;
    /* access modifiers changed from: private */
    public final Router router;
    private Long selectedTime;

    @Inject
    public TimeChooserPresenter(Inmate inmate2, Router router2, DaySlot daySlot2, HomewavApi homewavApi) {
        Intrinsics.checkParameterIsNotNull(inmate2, "inmate");
        Intrinsics.checkParameterIsNotNull(router2, "router");
        Intrinsics.checkParameterIsNotNull(daySlot2, "daySlot");
        Intrinsics.checkParameterIsNotNull(homewavApi, "api");
        this.inmate = inmate2;
        this.router = router2;
        this.daySlot = daySlot2;
        this.api = homewavApi;
    }

    /* access modifiers changed from: protected */
    public void onFirstViewAttach() {
        Date date = new Date(((TimeSlot) CollectionsKt.first(this.daySlot.getSlots())).getTimestamp() * ((long) 1000));
        TimeChooserView timeChooserView = (TimeChooserView) getViewState();
        String full_name = this.inmate.getFull_name();
        if (full_name == null) {
            Intrinsics.throwNpe();
        }
        timeChooserView.updateToolbar(full_name);
        ((TimeChooserView) getViewState()).displaySelectedDay(DateExtensionsKt.getAsDetailedDate(date));
        Collection arrayList = new ArrayList();
        Iterator it = this.daySlot.getSlots().iterator();
        while (true) {
            boolean z = false;
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            TimeSlot timeSlot = (TimeSlot) next;
            if (timeSlot.getStatus() == TimeSlot.Status.REQUESTED || timeSlot.getStatus() == TimeSlot.Status.RESERVED || timeSlot.getStatus() == TimeSlot.Status.CONFIRMED) {
                z = true;
            }
            if (z) {
                arrayList.add(next);
            }
        }
        Iterable<TimeSlot> iterable = (List) arrayList;
        Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (TimeSlot timestamp : iterable) {
            arrayList2.add(Long.valueOf(timestamp.getTimestamp()));
        }
        Set set = CollectionsKt.toSet((List) arrayList2);
        Collection arrayList3 = new ArrayList();
        for (Object next2 : this.daySlot.getSlots()) {
            if (((TimeSlot) next2).getStatus() == TimeSlot.Status.FREE) {
                arrayList3.add(next2);
            }
        }
        Collection arrayList4 = new ArrayList();
        for (Object next3 : (List) arrayList3) {
            if (!set.contains(Long.valueOf(((TimeSlot) next3).getTimestamp()))) {
                arrayList4.add(next3);
            }
        }
        ((TimeChooserView) getViewState()).displaySlots((List) arrayList4);
    }

    public final void onTimeSelected(long j) {
        this.selectedTime = Long.valueOf(j);
    }

    public final void onInviteClicked() {
        if (this.selectedTime != null) {
            TimeChooserView timeChooserView = (TimeChooserView) getViewState();
            String full_name = this.inmate.getFull_name();
            if (full_name == null) {
                Intrinsics.throwNpe();
            }
            Long l = this.selectedTime;
            if (l == null) {
                Intrinsics.throwNpe();
            }
            timeChooserView.showConfirmDialog(full_name, DateExtensionsKt.getAsConfirmationDate(new Date(l.longValue() * ((long) 1000))));
            return;
        }
        ((TimeChooserView) getViewState()).showMessage("Select time slot");
    }

    public final void onInviteConfirmed() {
        CompositeDisposable disposables = getDisposables();
        HomewavApi homewavApi = this.api;
        Long l = this.selectedTime;
        if (l == null) {
            Intrinsics.throwNpe();
        }
        Disposable subscribe = CommonKt.applyAsync(homewavApi.sendVisitRequest(l.longValue(), this.inmate.getId())).subscribe(new TimeChooserPresenter$onInviteConfirmed$1(this), TimeChooserPresenter$onInviteConfirmed$2.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "api.sendVisitRequest(sel… }\n                }, {})");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    public final void onBackPressed() {
        this.router.exit();
    }
}
