package com.forasoft.homewavvisitor.presentation.presenter.account;

import air.HomeWAV.R;
import android.content.Context;
import com.forasoft.homewavvisitor.dao.NotificationDao;
import com.forasoft.homewavvisitor.dao.UserDao;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.Constants;
import com.forasoft.homewavvisitor.model.data.account.History;
import com.forasoft.homewavvisitor.model.data.account.HistoryItem;
import com.forasoft.homewavvisitor.model.interactor.account.AccountInteractor;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import com.forasoft.homewavvisitor.navigation.Screens;
import com.forasoft.homewavvisitor.presentation.BasePresenter;
import com.forasoft.homewavvisitor.presentation.view.account.HistoryView;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxkotlin.DisposableKt;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SpreadBuilder;
import moxy.InjectViewState;
import ru.terrakok.cicerone.Router;
import timber.log.Timber;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B7\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u000fJ\b\u0010\u001f\u001a\u00020 H\u0002J\b\u0010!\u001a\u00020 H\u0002J\b\u0010\"\u001a\u00020 H\u0002J\b\u0010#\u001a\u00020 H\u0002J\u0010\u0010$\u001a\u00020 2\u0006\u0010%\u001a\u00020&H\u0002J\b\u0010'\u001a\u00020 H\u0002J\b\u0010(\u001a\u00020 H\u0002J\b\u0010)\u001a\u00020 H\u0002J\u0006\u0010*\u001a\u00020 J\u0006\u0010+\u001a\u00020 J\u0006\u0010,\u001a\u00020\u0019J\u0006\u0010-\u001a\u00020 J\b\u0010.\u001a\u00020 H\u0014J\u0016\u0010/\u001a\u00020 2\f\u00100\u001a\b\u0012\u0004\u0012\u00020201H\u0002J\u0010\u00103\u001a\u00020 2\u0006\u00104\u001a\u000205H\u0002J\u0006\u00106\u001a\u00020 J\u0006\u00107\u001a\u00020 J\u0006\u00108\u001a\u00020\u0019J\u000e\u00109\u001a\u00020 2\u0006\u0010:\u001a\u00020&J\u000e\u0010;\u001a\u00020 2\u0006\u0010\u001e\u001a\u00020&J\u000e\u0010<\u001a\u00020 2\u0006\u0010\u001e\u001a\u00020&J\u0010\u0010=\u001a\u00020 2\u0006\u0010>\u001a\u00020?H\u0002J\u0010\u0010@\u001a\u00020 2\u0006\u0010A\u001a\u00020?H\u0002J\u0010\u0010B\u001a\u00020?2\u0006\u0010C\u001a\u00020?H\u0002R\u001e\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\u00120\u0011j\b\u0012\u0004\u0012\u00020\u0012`\u0013X\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\u00120\u0011j\b\u0012\u0004\u0012\u00020\u0012`\u0013X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0018X\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u001a\u001a\u0010\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0018X\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u001b\u001a\u0010\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0018X\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u001dX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u001e\u001a\u0012\u0012\u0004\u0012\u00020\u00120\u0011j\b\u0012\u0004\u0012\u00020\u0012`\u0013X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000¨\u0006D"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/presenter/account/HistoryPresenter;", "Lcom/forasoft/homewavvisitor/presentation/BasePresenter;", "Lcom/forasoft/homewavvisitor/presentation/view/account/HistoryView;", "context", "Landroid/content/Context;", "router", "Lru/terrakok/cicerone/Router;", "constants", "Lcom/forasoft/homewavvisitor/model/Constants;", "accountInteractor", "Lcom/forasoft/homewavvisitor/model/interactor/account/AccountInteractor;", "userDao", "Lcom/forasoft/homewavvisitor/dao/UserDao;", "dao", "Lcom/forasoft/homewavvisitor/dao/NotificationDao;", "(Landroid/content/Context;Lru/terrakok/cicerone/Router;Lcom/forasoft/homewavvisitor/model/Constants;Lcom/forasoft/homewavvisitor/model/interactor/account/AccountInteractor;Lcom/forasoft/homewavvisitor/dao/UserDao;Lcom/forasoft/homewavvisitor/dao/NotificationDao;)V", "ASC_COMP", "Ljava/util/Comparator;", "Lcom/forasoft/homewavvisitor/model/data/account/HistoryItem;", "Lkotlin/Comparator;", "DESC_COMP", "dateFormat", "Ljava/text/SimpleDateFormat;", "filterName", "Lkotlin/Function1;", "", "filterPeriod", "filterType", "history", "", "sortType", "countItems", "", "filterCurrentWeek", "filterHistory", "filterLastWeek", "filterMonth", "amount", "", "filterSinceCreation", "filterThisMonth", "filterThisYear", "getNotificationsCount", "onActivitiesClick", "onBackPressed", "onCallsClick", "onFirstViewAttach", "onHistoryLoaded", "response", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "Lcom/forasoft/homewavvisitor/model/data/account/History;", "onLoadFail", "it", "", "onMessagesClick", "onMoneyClick", "onNotificationsClicked", "onPeriodSelected", "index", "onSortByDateSelected", "onSortByNameSelected", "setPeriodFilter", "weekAgo", "Ljava/util/Calendar;", "showToPresentPeriod", "startDate", "subtractWeek", "from", "app_release"}, k = 1, mv = {1, 1, 16})
@InjectViewState
/* compiled from: HistoryPresenter.kt */
public final class HistoryPresenter extends BasePresenter<HistoryView> {
    private final Comparator<HistoryItem> ASC_COMP = HistoryPresenter$ASC_COMP$1.INSTANCE;
    private final Comparator<HistoryItem> DESC_COMP;
    private final AccountInteractor accountInteractor;
    private final Constants constants;
    private final Context context;
    private final NotificationDao dao;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy", Locale.US);
    private Function1<? super HistoryItem, Boolean> filterName;
    private Function1<? super HistoryItem, Boolean> filterPeriod;
    private Function1<? super HistoryItem, Boolean> filterType;
    private List<HistoryItem> history;
    private final Router router;
    private Comparator<HistoryItem> sortType;
    private final UserDao userDao;

    @Inject
    public HistoryPresenter(Context context2, Router router2, Constants constants2, AccountInteractor accountInteractor2, UserDao userDao2, NotificationDao notificationDao) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(router2, "router");
        Intrinsics.checkParameterIsNotNull(constants2, "constants");
        Intrinsics.checkParameterIsNotNull(accountInteractor2, "accountInteractor");
        Intrinsics.checkParameterIsNotNull(userDao2, "userDao");
        Intrinsics.checkParameterIsNotNull(notificationDao, "dao");
        this.context = context2;
        this.router = router2;
        this.constants = constants2;
        this.accountInteractor = accountInteractor2;
        this.userDao = userDao2;
        this.dao = notificationDao;
        Comparator<HistoryItem> comparator = HistoryPresenter$DESC_COMP$1.INSTANCE;
        this.DESC_COMP = comparator;
        this.sortType = comparator;
    }

    /* access modifiers changed from: protected */
    public void onFirstViewAttach() {
        filterCurrentWeek();
        CompositeDisposable disposables = getDisposables();
        HistoryPresenter historyPresenter = this;
        Disposable subscribe = this.accountInteractor.getHistory().subscribe(new HistoryPresenter$sam$io_reactivex_functions_Consumer$0(new HistoryPresenter$onFirstViewAttach$1(historyPresenter)), new HistoryPresenter$sam$io_reactivex_functions_Consumer$0(new HistoryPresenter$onFirstViewAttach$2(historyPresenter)));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "accountInteractor.getHis…Loaded, this::onLoadFail)");
        DisposableKt.plusAssign(disposables, subscribe);
        ((HistoryView) getViewState()).initPeriodSpinner(this.constants.getHistoryPeriodList());
        ((HistoryView) getViewState()).initOrderSpinner(this.constants.getHistoryOrderList());
        ((HistoryView) getViewState()).initInmatesSpinner(CollectionsKt.arrayListOf("All inmates"));
        ((HistoryView) getViewState()).selectActivities();
    }

    /* access modifiers changed from: private */
    public final void onLoadFail(Throwable th) {
        String localizedMessage = th.getLocalizedMessage();
        if (localizedMessage != null) {
            ((HistoryView) getViewState()).showMessage(localizedMessage);
        }
        Timber.e(th);
    }

    /* access modifiers changed from: private */
    public final void onHistoryLoaded(ApiResponse<History> apiResponse) {
        if (apiResponse.getBody() == null) {
            ((HistoryView) getViewState()).showMessage("No History");
            return;
        }
        this.history = apiResponse.getBody().getItems();
        StringBuilder sb = new StringBuilder();
        sb.append("onHistoryLoaded: history size = ");
        List<HistoryItem> list = this.history;
        sb.append(list != null ? Integer.valueOf(list.size()) : null);
        Timber.d(sb.toString(), new Object[0]);
        Timber.d("onHistoryLoaded: history: " + this.history, new Object[0]);
        HistoryView historyView = (HistoryView) getViewState();
        List<HistoryItem> list2 = this.history;
        if (list2 == null) {
            Intrinsics.throwNpe();
        }
        historyView.showData(CollectionsKt.sorted(list2));
        HistoryView historyView2 = (HistoryView) getViewState();
        SpreadBuilder spreadBuilder = new SpreadBuilder(2);
        spreadBuilder.add("All inmates");
        List<HistoryItem> list3 = this.history;
        if (list3 == null) {
            Intrinsics.throwNpe();
        }
        Iterable iterable = list3;
        Function1<? super HistoryItem, Boolean> function1 = this.filterPeriod;
        if (function1 == null) {
            function1 = HistoryPresenter$onHistoryLoaded$1.INSTANCE;
        }
        Collection arrayList = new ArrayList();
        for (Object next : iterable) {
            if (function1.invoke(next).booleanValue()) {
                arrayList.add(next);
            }
        }
        Iterable<HistoryItem> iterable2 = (List) arrayList;
        Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable2, 10));
        for (HistoryItem fullName : iterable2) {
            arrayList2.add(fullName.getFullName());
        }
        Object[] array = CollectionsKt.toSet((List) arrayList2).toArray(new String[0]);
        if (array != null) {
            spreadBuilder.addSpread(array);
            historyView2.initInmatesSpinner(CollectionsKt.arrayListOf((String[]) spreadBuilder.toArray(new String[spreadBuilder.size()])));
            countItems();
            filterHistory();
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    private final void countItems() {
        int i;
        int i2;
        List<HistoryItem> list = this.history;
        if (list != null) {
            if (list == null) {
                Intrinsics.throwNpe();
            }
            Iterable iterable = list;
            Function1<? super HistoryItem, Boolean> function1 = this.filterName;
            if (function1 == null) {
                function1 = HistoryPresenter$countItems$1.INSTANCE;
            }
            Collection arrayList = new ArrayList();
            for (Object next : iterable) {
                if (function1.invoke(next).booleanValue()) {
                    arrayList.add(next);
                }
            }
            Iterable iterable2 = (List) arrayList;
            Function1<? super HistoryItem, Boolean> function12 = this.filterPeriod;
            if (function12 == null) {
                function12 = HistoryPresenter$countItems$2.INSTANCE;
            }
            Collection arrayList2 = new ArrayList();
            for (Object next2 : iterable2) {
                if (function12.invoke(next2).booleanValue()) {
                    arrayList2.add(next2);
                }
            }
            List list2 = (List) arrayList2;
            ((HistoryView) getViewState()).setActivitiesCount(list2.size());
            HistoryView historyView = (HistoryView) getViewState();
            Iterable<HistoryItem> iterable3 = list2;
            boolean z = iterable3 instanceof Collection;
            if (!z || !((Collection) iterable3).isEmpty()) {
                i = 0;
                for (HistoryItem itemType : iterable3) {
                    if ((itemType.getItemType() == HistoryItem.Type.MESSAGE) && (i = i + 1) < 0) {
                        CollectionsKt.throwCountOverflow();
                    }
                }
            } else {
                i = 0;
            }
            historyView.setMessagesCount(i);
            HistoryView historyView2 = (HistoryView) getViewState();
            if (!z || !((Collection) iterable3).isEmpty()) {
                i2 = 0;
                for (HistoryItem itemType2 : iterable3) {
                    if ((itemType2.getItemType() == HistoryItem.Type.VIDEO_CALL) && (i2 = i2 + 1) < 0) {
                        CollectionsKt.throwCountOverflow();
                    }
                }
            } else {
                i2 = 0;
            }
            historyView2.setCallsCount(i2);
            Collection arrayList3 = new ArrayList();
            for (Object next3 : iterable3) {
                if (((HistoryItem) next3).getItemType() == HistoryItem.Type.MONEY) {
                    arrayList3.add(next3);
                }
            }
            Iterable<HistoryItem> iterable4 = (List) arrayList3;
            Collection arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable4, 10));
            for (HistoryItem value : iterable4) {
                String value2 = value.getValue();
                if (value2 != null) {
                    String substring = value2.substring(1);
                    Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
                    arrayList4.add(Float.valueOf(CommonKt.toFloatExceptionless(substring)));
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
            }
            ((HistoryView) getViewState()).setMoneyAmount(CollectionsKt.sumOfFloat((List) arrayList4));
        }
    }

    public final boolean onBackPressed() {
        this.router.exit();
        return true;
    }

    public final void getNotificationsCount() {
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = CommonKt.applyAsync(this.dao.countAll()).subscribe(new HistoryPresenter$getNotificationsCount$1(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "dao.countAll().applyAsyn…teNotificationMenu(it) })");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    public final void onPeriodSelected(int i) {
        switch (i) {
            case 0:
                filterCurrentWeek();
                break;
            case 1:
                filterLastWeek();
                break;
            case 2:
                filterThisMonth();
                break;
            case 3:
                filterMonth(3);
                break;
            case 4:
                filterMonth(6);
                break;
            case 5:
                filterThisYear();
                break;
            case 6:
                filterSinceCreation();
                break;
        }
        filterHistory();
        if (this.history != null) {
            HistoryView historyView = (HistoryView) getViewState();
            SpreadBuilder spreadBuilder = new SpreadBuilder(2);
            spreadBuilder.add("All inmates");
            List<HistoryItem> list = this.history;
            if (list == null) {
                Intrinsics.throwNpe();
            }
            Iterable iterable = list;
            Function1<? super HistoryItem, Boolean> function1 = this.filterPeriod;
            if (function1 == null) {
                function1 = HistoryPresenter$onPeriodSelected$1.INSTANCE;
            }
            Collection arrayList = new ArrayList();
            for (Object next : iterable) {
                if (function1.invoke(next).booleanValue()) {
                    arrayList.add(next);
                }
            }
            Iterable<HistoryItem> iterable2 = (List) arrayList;
            Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable2, 10));
            for (HistoryItem fullName : iterable2) {
                arrayList2.add(fullName.getFullName());
            }
            Object[] array = CollectionsKt.toSet((List) arrayList2).toArray(new String[0]);
            if (array != null) {
                spreadBuilder.addSpread(array);
                historyView.initInmatesSpinner(CollectionsKt.arrayListOf((String[]) spreadBuilder.toArray(new String[spreadBuilder.size()])));
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }
    }

    private final void filterThisMonth() {
        Calendar instance = Calendar.getInstance();
        instance.set(5, 1);
        instance.set(10, 0);
        instance.set(12, 0);
        Intrinsics.checkExpressionValueIsNotNull(instance, "calendar");
        showToPresentPeriod(instance);
        setPeriodFilter(instance);
    }

    private final void filterThisYear() {
        Calendar instance = Calendar.getInstance();
        instance.set(6, 1);
        instance.set(10, 0);
        instance.set(12, 0);
        Intrinsics.checkExpressionValueIsNotNull(instance, "calendar");
        showToPresentPeriod(instance);
        setPeriodFilter(instance);
    }

    private final void filterSinceCreation() {
        this.filterPeriod = null;
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = CommonKt.applyAsync(this.userDao.getSingleUser()).subscribe(new HistoryPresenter$filterSinceCreation$1(this), HistoryPresenter$filterSinceCreation$2.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "userDao.getSingleUser()\n…r)\n                }, {})");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    private final void filterHistory() {
        if (this.history != null) {
            HistoryView historyView = (HistoryView) getViewState();
            List<HistoryItem> list = this.history;
            if (list == null) {
                Intrinsics.throwNpe();
            }
            Iterable iterable = list;
            Function1<? super HistoryItem, Boolean> function1 = this.filterPeriod;
            if (function1 == null) {
                function1 = HistoryPresenter$filterHistory$1.INSTANCE;
            }
            Collection arrayList = new ArrayList();
            for (Object next : iterable) {
                if (function1.invoke(next).booleanValue()) {
                    arrayList.add(next);
                }
            }
            Iterable iterable2 = (List) arrayList;
            Function1<? super HistoryItem, Boolean> function12 = this.filterType;
            if (function12 == null) {
                function12 = HistoryPresenter$filterHistory$2.INSTANCE;
            }
            Collection arrayList2 = new ArrayList();
            for (Object next2 : iterable2) {
                if (function12.invoke(next2).booleanValue()) {
                    arrayList2.add(next2);
                }
            }
            Iterable iterable3 = (List) arrayList2;
            Function1<? super HistoryItem, Boolean> function13 = this.filterName;
            if (function13 == null) {
                function13 = HistoryPresenter$filterHistory$3.INSTANCE;
            }
            Collection arrayList3 = new ArrayList();
            for (Object next3 : iterable3) {
                if (function13.invoke(next3).booleanValue()) {
                    arrayList3.add(next3);
                }
            }
            historyView.showData(CollectionsKt.sortedWith((List) arrayList3, this.sortType));
            countItems();
        }
    }

    private final void filterMonth(int i) {
        Calendar instance = Calendar.getInstance();
        instance.add(2, i * -1);
        Intrinsics.checkExpressionValueIsNotNull(instance, "calendar");
        showToPresentPeriod(instance);
        setPeriodFilter(instance);
    }

    private final void filterLastWeek() {
        Calendar instance = Calendar.getInstance();
        instance.set(7, 2);
        Intrinsics.checkExpressionValueIsNotNull(instance, "weekAgo");
        Calendar subtractWeek = subtractWeek(instance);
        subtractWeek.set(7, 2);
        this.filterPeriod = new HistoryPresenter$filterLastWeek$1(subtractWeek, instance);
        ((HistoryView) getViewState()).showPeriod(this.dateFormat.format(subtractWeek.getTime()) + " - " + this.dateFormat.format(instance.getTime()));
    }

    private final void filterCurrentWeek() {
        Calendar instance = Calendar.getInstance();
        instance.set(7, 2);
        instance.set(10, 0);
        instance.set(12, 0);
        Intrinsics.checkExpressionValueIsNotNull(instance, "weekAgo");
        showToPresentPeriod(instance);
        setPeriodFilter(instance);
    }

    private final void setPeriodFilter(Calendar calendar) {
        this.filterPeriod = new HistoryPresenter$setPeriodFilter$1(calendar);
    }

    private final Calendar subtractWeek(Calendar calendar) {
        Object clone = calendar.clone();
        if (clone != null) {
            Calendar calendar2 = (Calendar) clone;
            calendar2.add(3, -1);
            return calendar2;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.util.Calendar");
    }

    /* access modifiers changed from: private */
    public final void showToPresentPeriod(Calendar calendar) {
        String string = this.context.getResources().getString(R.string.placeholder_past_to_present, new Object[]{this.dateFormat.format(calendar.getTime())});
        Intrinsics.checkExpressionValueIsNotNull(string, "context.resources.getStr…t.format(startDate.time))");
        ((HistoryView) getViewState()).showPeriod(string);
    }

    public final void onActivitiesClick() {
        ((HistoryView) getViewState()).selectActivities();
        this.filterType = null;
        filterHistory();
    }

    public final void onMessagesClick() {
        ((HistoryView) getViewState()).selectMessages();
        this.filterType = HistoryPresenter$onMessagesClick$1.INSTANCE;
        filterHistory();
    }

    public final void onCallsClick() {
        ((HistoryView) getViewState()).selectCalls();
        this.filterType = HistoryPresenter$onCallsClick$1.INSTANCE;
        filterHistory();
    }

    public final void onMoneyClick() {
        ((HistoryView) getViewState()).selectMoney();
        this.filterType = HistoryPresenter$onMoneyClick$1.INSTANCE;
        filterHistory();
    }

    public final boolean onNotificationsClicked() {
        this.router.navigateTo(Screens.NotificationsScreen.INSTANCE);
        return true;
    }

    public final void onSortByDateSelected(int i) {
        Comparator<HistoryItem> comparator;
        if (i == 0) {
            comparator = this.DESC_COMP;
        } else {
            comparator = this.ASC_COMP;
        }
        this.sortType = comparator;
        filterHistory();
    }

    public final void onSortByNameSelected(int i) {
        Function1<? super HistoryItem, Boolean> function1;
        if (i == 0) {
            function1 = null;
        } else {
            List<HistoryItem> list = this.history;
            if (list != null) {
                if (list == null) {
                    Intrinsics.throwNpe();
                }
                Iterable<HistoryItem> iterable = list;
                Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
                for (HistoryItem fullName : iterable) {
                    arrayList.add(fullName.getFullName());
                }
                function1 = new HistoryPresenter$onSortByNameSelected$1((String) CollectionsKt.toList(CollectionsKt.toSet((List) arrayList)).get(i - 1));
            } else {
                return;
            }
        }
        this.filterName = function1;
        filterHistory();
        countItems();
    }
}
