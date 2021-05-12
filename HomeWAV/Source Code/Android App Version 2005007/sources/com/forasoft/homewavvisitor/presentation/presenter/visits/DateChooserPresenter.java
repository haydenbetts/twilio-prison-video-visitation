package com.forasoft.homewavvisitor.presentation.presenter.visits;

import com.forasoft.homewavvisitor.dao.VisitDao;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.data.DaySlot;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import com.forasoft.homewavvisitor.navigation.Screens;
import com.forasoft.homewavvisitor.presentation.BasePresenter;
import com.forasoft.homewavvisitor.presentation.view.visits.DateChooserView;
import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxkotlin.DisposableKt;
import java.util.Map;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import moxy.InjectViewState;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.LocalTime;
import org.threeten.bp.ZoneId;
import org.threeten.bp.temporal.TemporalAdjuster;
import ru.terrakok.cicerone.Router;
import wseemann.media.FFmpegMediaMetadataRetriever;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B;\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u000fJ\b\u0010\u0012\u001a\u00020\u0013H\u0002J\b\u0010\u0014\u001a\u00020\u0013H\u0002J\u0006\u0010\u0015\u001a\u00020\u0013J\u000e\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0018J\u0010\u0010\u0019\u001a\u00020\u00132\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018J\b\u0010\u001a\u001a\u00020\u0013H\u0014R\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/presenter/visits/DateChooserPresenter;", "Lcom/forasoft/homewavvisitor/presentation/BasePresenter;", "Lcom/forasoft/homewavvisitor/presentation/view/visits/DateChooserView;", "router", "Lru/terrakok/cicerone/Router;", "schedule", "", "Lorg/threeten/bp/LocalDate;", "Lcom/forasoft/homewavvisitor/model/data/DaySlot;", "inmate", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "visitDao", "Lcom/forasoft/homewavvisitor/dao/VisitDao;", "api", "Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;", "(Lru/terrakok/cicerone/Router;Ljava/util/Map;Lcom/forasoft/homewavvisitor/model/data/Inmate;Lcom/forasoft/homewavvisitor/dao/VisitDao;Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;)V", "selectedMonth", "", "loadSlotsFromDb", "", "loadSlotsFromNetwork", "onBackPressed", "onCalendarScrolled", "date", "Lorg/threeten/bp/LocalDateTime;", "onDateSelected", "onFirstViewAttach", "app_release"}, k = 1, mv = {1, 1, 16})
@InjectViewState
/* compiled from: DateChooserPresenter.kt */
public final class DateChooserPresenter extends BasePresenter<DateChooserView> {
    private final HomewavApi api;
    private final Inmate inmate;
    private final Router router;
    private final Map<LocalDate, DaySlot> schedule;
    private int selectedMonth;
    private final VisitDao visitDao;

    @Inject
    public DateChooserPresenter(Router router2, Map<LocalDate, DaySlot> map, Inmate inmate2, VisitDao visitDao2, HomewavApi homewavApi) {
        Intrinsics.checkParameterIsNotNull(router2, "router");
        Intrinsics.checkParameterIsNotNull(map, "schedule");
        Intrinsics.checkParameterIsNotNull(inmate2, "inmate");
        Intrinsics.checkParameterIsNotNull(visitDao2, "visitDao");
        Intrinsics.checkParameterIsNotNull(homewavApi, "api");
        this.router = router2;
        this.schedule = map;
        this.inmate = inmate2;
        this.visitDao = visitDao2;
        this.api = homewavApi;
        LocalDateTime now = LocalDateTime.now();
        Intrinsics.checkExpressionValueIsNotNull(now, "LocalDateTime.now()");
        this.selectedMonth = now.getMonthValue();
    }

    /* access modifiers changed from: protected */
    public void onFirstViewAttach() {
        DateChooserView dateChooserView = (DateChooserView) getViewState();
        String full_name = this.inmate.getFull_name();
        if (full_name == null) {
            Intrinsics.throwNpe();
        }
        dateChooserView.updateToolbar(full_name);
        ((DateChooserView) getViewState()).initCalendar(this.schedule);
        ((DateChooserView) getViewState()).showProgress();
        loadSlotsFromDb();
        loadSlotsFromNetwork();
    }

    public final void onDateSelected(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            ((DateChooserView) getViewState()).showMessage("Can't schedule visit for this date");
            return;
        }
        this.router.navigateTo(new Screens.TimeChooseScreen(this.inmate, this.schedule.get(localDateTime.with((TemporalAdjuster) LocalTime.MIDNIGHT).toLocalDate())));
    }

    public final void onCalendarScrolled(LocalDateTime localDateTime) {
        Intrinsics.checkParameterIsNotNull(localDateTime, FFmpegMediaMetadataRetriever.METADATA_KEY_DATE);
        this.selectedMonth = localDateTime.getMonthValue();
    }

    private final void loadSlotsFromDb() {
        long epochSecond = LocalDateTime.now().with((TemporalAdjuster) LocalTime.MIDNIGHT).atZone(ZoneId.systemDefault()).toEpochSecond();
        CompositeDisposable disposables = getDisposables();
        VisitDao visitDao2 = this.visitDao;
        String prison_name = this.inmate.getPrison_name();
        if (prison_name == null) {
            Intrinsics.throwNpe();
        }
        Flowable take = Flowable.combineLatest(visitDao2.getScheduledVisits(epochSecond, prison_name), this.visitDao.getPendingVisits(epochSecond, this.inmate.getPrison_name()), DateChooserPresenter$loadSlotsFromDb$1.INSTANCE).take(1);
        Intrinsics.checkExpressionValueIsNotNull(take, "Flowable.combineLatest(\n…\n                .take(1)");
        Disposable subscribe = CommonKt.applyAsync(take).subscribe(new DateChooserPresenter$loadSlotsFromDb$2(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "Flowable.combineLatest(\n…lue)) }\n                }");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    private final void loadSlotsFromNetwork() {
        long epochSecond = LocalDateTime.now().with((TemporalAdjuster) LocalTime.MIDNIGHT).atZone(ZoneId.systemDefault()).toEpochSecond();
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = CommonKt.applyAsync(this.api.getSchedule(this.inmate.getId(), epochSecond, "month")).subscribe(new DateChooserPresenter$loadSlotsFromNetwork$1(this), new DateChooserPresenter$loadSlotsFromNetwork$2(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "api.getSchedule(inmate.i…     }\n                })");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    public final void onBackPressed() {
        this.router.exit();
    }
}
