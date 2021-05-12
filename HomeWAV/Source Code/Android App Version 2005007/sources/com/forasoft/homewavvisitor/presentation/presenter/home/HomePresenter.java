package com.forasoft.homewavvisitor.presentation.presenter.home;

import androidx.exifinterface.media.ExifInterface;
import com.forasoft.homewavvisitor.dao.InmateDao;
import com.forasoft.homewavvisitor.dao.NotificationDao;
import com.forasoft.homewavvisitor.dao.VisitDao;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.AirshipAnalytics;
import com.forasoft.homewavvisitor.model.Analytics;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.model.data.State;
import com.forasoft.homewavvisitor.model.data.account.ScheduledVisit;
import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.model.data.auth.User;
import com.forasoft.homewavvisitor.model.repository.HeartbeatRepository;
import com.forasoft.homewavvisitor.model.server.apis.AccountApi;
import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import com.forasoft.homewavvisitor.model.server.response.Response;
import com.forasoft.homewavvisitor.navigation.BusNotifier;
import com.forasoft.homewavvisitor.navigation.Screens;
import com.forasoft.homewavvisitor.presentation.BasePresenter;
import com.forasoft.homewavvisitor.presentation.view.home.HomeView;
import com.google.gson.stream.MalformedJsonException;
import com.urbanairship.Cancelable;
import com.urbanairship.UAirship;
import com.urbanairship.channel.NamedUser;
import com.urbanairship.messagecenter.Inbox;
import com.urbanairship.messagecenter.Message;
import com.urbanairship.messagecenter.MessageCenter;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxkotlin.DisposableKt;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import moxy.InjectViewState;
import ru.terrakok.cicerone.Router;
import timber.log.Timber;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u001d\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001Bk\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0012\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00130\u0012\u0012\u0006\u0010\u0015\u001a\u00020\u0016\u0012\u0006\u0010\u0017\u001a\u00020\u0018\u0012\u0006\u0010\u0019\u001a\u00020\u001a¢\u0006\u0002\u0010\u001bJ\b\u0010%\u001a\u00020&H\u0016J\b\u0010'\u001a\u00020&H\u0002J\b\u0010(\u001a\u00020&H\u0002J\u0006\u0010)\u001a\u00020&J\b\u0010*\u001a\u00020&H\u0002J\u0010\u0010+\u001a\u00020&2\u0006\u0010,\u001a\u00020-H\u0002J%\u0010.\u001a\u00020&\"\u0006\b\u0000\u0010/\u0018\u00012\u0012\u00100\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H/0\u001301H\bJ\u0014\u00102\u001a\u00020&2\f\u00103\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013J\u0006\u00104\u001a\u00020 J\b\u00105\u001a\u00020&H\u0014J\u000e\u00106\u001a\u00020&2\u0006\u00107\u001a\u00020\u0014J\u000e\u00108\u001a\u00020&2\u0006\u00107\u001a\u00020\u0014J\u0006\u00109\u001a\u00020&J\u0006\u0010:\u001a\u00020&J\u0006\u0010;\u001a\u00020&J\u0006\u0010<\u001a\u00020&J\u0006\u0010=\u001a\u00020&J\u0006\u0010>\u001a\u00020&J\u0006\u0010?\u001a\u00020&J\u0006\u0010@\u001a\u00020&J\u0016\u0010A\u001a\u00020&2\f\u00103\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013H\u0002J\u0012\u0010B\u001a\u00020&2\b\b\u0002\u0010C\u001a\u00020 H\u0002J\u0012\u0010D\u001a\u00020&2\b\u0010E\u001a\u0004\u0018\u00010FH\u0002J\u0018\u0010G\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013*\b\u0012\u0004\u0012\u00020\u00140\u0013H\u0002R\u000e\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u00020\u001dX\u0004¢\u0006\u0004\n\u0002\u0010\u001eR\u000e\u0010\u0015\u001a\u00020\u0016X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00130\u0012X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000¨\u0006H"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/presenter/home/HomePresenter;", "Lcom/forasoft/homewavvisitor/presentation/BasePresenter;", "Lcom/forasoft/homewavvisitor/presentation/view/home/HomeView;", "router", "Lru/terrakok/cicerone/Router;", "authHolder", "Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;", "api", "Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;", "notificationDao", "Lcom/forasoft/homewavvisitor/dao/NotificationDao;", "inmateDao", "Lcom/forasoft/homewavvisitor/dao/InmateDao;", "visitDao", "Lcom/forasoft/homewavvisitor/dao/VisitDao;", "accountApi", "Lcom/forasoft/homewavvisitor/model/server/apis/AccountApi;", "inmatesSubject", "Lio/reactivex/subjects/BehaviorSubject;", "", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "heartbeatRepository", "Lcom/forasoft/homewavvisitor/model/repository/HeartbeatRepository;", "analytics", "Lcom/forasoft/homewavvisitor/model/Analytics;", "notifier", "Lcom/forasoft/homewavvisitor/navigation/BusNotifier;", "(Lru/terrakok/cicerone/Router;Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;Lcom/forasoft/homewavvisitor/dao/NotificationDao;Lcom/forasoft/homewavvisitor/dao/InmateDao;Lcom/forasoft/homewavvisitor/dao/VisitDao;Lcom/forasoft/homewavvisitor/model/server/apis/AccountApi;Lio/reactivex/subjects/BehaviorSubject;Lcom/forasoft/homewavvisitor/model/repository/HeartbeatRepository;Lcom/forasoft/homewavvisitor/model/Analytics;Lcom/forasoft/homewavvisitor/navigation/BusNotifier;)V", "busListener", "com/forasoft/homewavvisitor/presentation/presenter/home/HomePresenter$busListener$1", "Lcom/forasoft/homewavvisitor/presentation/presenter/home/HomePresenter$busListener$1;", "isMessageViewVisible", "", "lastRenderedMessageId", "", "updateCancelToken", "Lcom/urbanairship/Cancelable;", "beforeDestroy", "", "doMessageViewUpdating", "getInmates", "getNotificationsCount", "getVisits", "handleError", "error", "", "handleSuccess", "T", "response", "Lcom/forasoft/homewavvisitor/model/server/response/Response;", "onAppearClicked", "inmates", "onBackPressed", "onFirstViewAttach", "onInmateClicked", "inmate", "onMessageInmateClicked", "onNextVisitClicked", "onNotificationsClicked", "onTabSelected", "onViewAllPendingsClicked", "onViewFaqClicked", "onViewTutorialsClicked", "refresh", "sendAnalyticsMessageCenterOpened", "setAudioVideoTags", "updateMessageView", "forceRerender", "updateTagsAndShowInmatesList", "heartbeatState", "Lcom/forasoft/homewavvisitor/model/data/State;", "approved", "app_release"}, k = 1, mv = {1, 1, 16})
@InjectViewState
/* compiled from: HomePresenter.kt */
public final class HomePresenter extends BasePresenter<HomeView> {
    private final AccountApi accountApi;
    private final Analytics analytics;
    private final HomewavApi api;
    private final AuthHolder authHolder;
    private final HomePresenter$busListener$1 busListener = new HomePresenter$busListener$1(this);
    /* access modifiers changed from: private */
    public final HeartbeatRepository heartbeatRepository;
    /* access modifiers changed from: private */
    public final InmateDao inmateDao;
    /* access modifiers changed from: private */
    public final BehaviorSubject<List<Inmate>> inmatesSubject;
    private boolean isMessageViewVisible;
    private String lastRenderedMessageId;
    private final NotificationDao notificationDao;
    private final BusNotifier notifier;
    /* access modifiers changed from: private */
    public final Router router;
    private Cancelable updateCancelToken;
    /* access modifiers changed from: private */
    public final VisitDao visitDao;

    @Inject
    public HomePresenter(Router router2, AuthHolder authHolder2, HomewavApi homewavApi, NotificationDao notificationDao2, InmateDao inmateDao2, VisitDao visitDao2, AccountApi accountApi2, BehaviorSubject<List<Inmate>> behaviorSubject, HeartbeatRepository heartbeatRepository2, Analytics analytics2, BusNotifier busNotifier) {
        Intrinsics.checkParameterIsNotNull(router2, "router");
        Intrinsics.checkParameterIsNotNull(authHolder2, "authHolder");
        Intrinsics.checkParameterIsNotNull(homewavApi, "api");
        Intrinsics.checkParameterIsNotNull(notificationDao2, "notificationDao");
        Intrinsics.checkParameterIsNotNull(inmateDao2, "inmateDao");
        Intrinsics.checkParameterIsNotNull(visitDao2, "visitDao");
        Intrinsics.checkParameterIsNotNull(accountApi2, "accountApi");
        Intrinsics.checkParameterIsNotNull(behaviorSubject, "inmatesSubject");
        Intrinsics.checkParameterIsNotNull(heartbeatRepository2, "heartbeatRepository");
        Intrinsics.checkParameterIsNotNull(analytics2, Modules.ANALYTICS_MODULE);
        Intrinsics.checkParameterIsNotNull(busNotifier, "notifier");
        this.router = router2;
        this.authHolder = authHolder2;
        this.api = homewavApi;
        this.notificationDao = notificationDao2;
        this.inmateDao = inmateDao2;
        this.visitDao = visitDao2;
        this.accountApi = accountApi2;
        this.inmatesSubject = behaviorSubject;
        this.heartbeatRepository = heartbeatRepository2;
        this.analytics = analytics2;
        this.notifier = busNotifier;
    }

    /* access modifiers changed from: protected */
    public void onFirstViewAttach() {
        String visitor_id;
        getVisits();
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = this.heartbeatRepository.getHeartbeatState().subscribe(new HomePresenter$onFirstViewAttach$1(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "heartbeatRepository.hear…tState)\n                }");
        DisposableKt.plusAssign(disposables, subscribe);
        User user = this.authHolder.getUser();
        if (!(user == null || (visitor_id = user.getVisitor_id()) == null)) {
            CompositeDisposable disposables2 = getDisposables();
            Disposable subscribe2 = CommonKt.applyAsync(this.inmateDao.getPendingInmate(visitor_id)).subscribe(new HomePresenter$onFirstViewAttach$$inlined$let$lambda$1(this));
            Intrinsics.checkExpressionValueIsNotNull(subscribe2, "inmateDao.getPendingInma…  }\n                    }");
            DisposableKt.plusAssign(disposables2, subscribe2);
            CompositeDisposable disposables3 = getDisposables();
            Flowable<List<Inmate>> take = this.inmateDao.getInmates(visitor_id).filter(HomePresenter$onFirstViewAttach$2$2.INSTANCE).take(1);
            Intrinsics.checkExpressionValueIsNotNull(take, "inmateDao.getInmates(it)…                 .take(1)");
            Disposable subscribe3 = CommonKt.applyAsync(take).subscribe(new HomePresenter$onFirstViewAttach$$inlined$let$lambda$2(this), HomePresenter$onFirstViewAttach$2$4.INSTANCE);
            Intrinsics.checkExpressionValueIsNotNull(subscribe3, "inmateDao.getInmates(it)…                  }, { })");
            DisposableKt.plusAssign(disposables3, subscribe3);
            this.notifier.add(this.busListener);
        }
        long time = new Date().getTime() / ((long) 1000);
        CompositeDisposable disposables4 = getDisposables();
        Disposable subscribe4 = CommonKt.applyAsync(this.visitDao.getScheduledVisits(time)).subscribe(new HomePresenter$onFirstViewAttach$3(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe4, "visitDao.getScheduledVis…      }\n                }");
        DisposableKt.plusAssign(disposables4, subscribe4);
        CompositeDisposable disposables5 = getDisposables();
        Disposable subscribe5 = Observable.interval(0, 60, TimeUnit.SECONDS, Schedulers.computation()).subscribe(new HomePresenter$onFirstViewAttach$4(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe5, "Observable.interval(0L, …ating()\n                }");
        DisposableKt.plusAssign(disposables5, subscribe5);
    }

    /* access modifiers changed from: private */
    public final void doMessageViewUpdating() {
        MessageCenter shared = MessageCenter.shared();
        Intrinsics.checkExpressionValueIsNotNull(shared, "MessageCenter.shared()");
        this.updateCancelToken = shared.getInbox().fetchMessages(new HomePresenter$doMessageViewUpdating$1(this));
    }

    static /* synthetic */ void updateMessageView$default(HomePresenter homePresenter, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        homePresenter.updateMessageView(z);
    }

    private final void updateMessageView(boolean z) {
        if (this.isMessageViewVisible) {
            MessageCenter shared = MessageCenter.shared();
            Intrinsics.checkExpressionValueIsNotNull(shared, "MessageCenter.shared()");
            Inbox inbox = shared.getInbox();
            Intrinsics.checkExpressionValueIsNotNull(inbox, "MessageCenter.shared().inbox");
            if (inbox.getMessages().size() == 0) {
                ((HomeView) getViewState()).showEmptyMessageCenter();
                return;
            }
            String str = this.lastRenderedMessageId;
            Message message = inbox.getMessages().get(0);
            Intrinsics.checkExpressionValueIsNotNull(message, "inbox.messages[0]");
            boolean areEqual = Intrinsics.areEqual((Object) str, (Object) message.getMessageId());
            boolean z2 = true;
            if (!(!areEqual) && !z) {
                z2 = false;
            }
            Message message2 = inbox.getMessages().get(0);
            Intrinsics.checkExpressionValueIsNotNull(message2, "message");
            this.lastRenderedMessageId = message2.getMessageId();
            ((HomeView) getViewState()).updateMessageCenterView(message2, inbox.getUnreadCount(), z2);
        }
    }

    /* access modifiers changed from: private */
    public final void updateTagsAndShowInmatesList(State state) {
        List list;
        boolean z;
        boolean z2;
        Collection<String> values;
        if (this.inmatesSubject.hasValue()) {
            Map<String, String> status = state != null ? state.getStatus() : null;
            Map<String, String> balances = state != null ? state.getBalances() : null;
            List<Inmate> value = this.inmatesSubject.getValue();
            Intrinsics.checkExpressionValueIsNotNull(value, "inmatesSubject.value");
            Iterable<Inmate> iterable = value;
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (Inmate inmate : iterable) {
                arrayList.add(Inmate.copy$default(inmate, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, balances != null ? balances.get(inmate.getId()) : null, status != null ? status.get(inmate.getId()) : null, (String) null, (String) null, false, false, false, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, false, false, (String) null, (String) null, -196609, -1, 63, (Object) null));
            }
            List list2 = (List) arrayList;
            this.inmatesSubject.onNext(list2);
            if (balances == null || (values = balances.values()) == null) {
                list = CollectionsKt.emptyList();
            } else {
                Iterable<String> iterable2 = values;
                Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable2, 10));
                for (String parseDouble : iterable2) {
                    arrayList2.add(Double.valueOf(Double.parseDouble(parseDouble)));
                }
                list = (List) arrayList2;
            }
            Set linkedHashSet = new LinkedHashSet();
            Collection collection = linkedHashSet;
            collection.add("visitor");
            Set mutableSetOf = SetsKt.mutableSetOf("visitor_relationship_mother", "visitor_relationship_father", "visitor_relationship_sister", "visitor_relationship_brother", "visitor_relationship_grandparents", "visitor_relationship_husband", "visitor_relationship_wife", "visitor_relationship_friend");
            User user = this.authHolder.getUser();
            if (user == null || !user.isAdmin()) {
                mutableSetOf.add("admin");
            } else {
                collection.add("admin");
            }
            Iterable iterable3 = list;
            if (CollectionsKt.sumOfDouble(iterable3) > 0.0d) {
                collection.add("visitor_with_money");
                mutableSetOf.add("visitor_no_money");
                this.analytics.onMoney();
            } else {
                collection.add("visitor_no_money");
                this.analytics.onNoMoney();
                mutableSetOf.add("visitor_with_money");
            }
            if (!(iterable3 instanceof Collection) || !((Collection) iterable3).isEmpty()) {
                Iterator it = iterable3.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    if (((Number) it.next()).doubleValue() < 2.0d) {
                        z2 = true;
                        continue;
                    } else {
                        z2 = false;
                        continue;
                    }
                    if (z2) {
                        z = true;
                        break;
                    }
                }
            }
            z = false;
            if (z) {
                collection.add("visitor_least_inmate_less_two");
                this.analytics.onNormalBalance();
            } else {
                this.analytics.onLowBalance();
                mutableSetOf.add("visitor_least_inmate_less_two");
            }
            if (list2.isEmpty()) {
                collection.add("visitor_no_inmate");
            } else {
                mutableSetOf.add("visitor_no_inmate");
                Iterable iterable4 = list2;
                Collection arrayList3 = new ArrayList();
                for (Object next : iterable4) {
                    if (((Inmate) next).getPrison_name() != null) {
                        arrayList3.add(next);
                    }
                }
                Iterable<Inmate> iterable5 = (List) arrayList3;
                Collection arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable5, 10));
                for (Inmate prison_name : iterable5) {
                    arrayList4.add(String.valueOf(prison_name.getPrison_name()));
                }
                CollectionsKt.addAll(collection, (List) arrayList4);
                Collection arrayList5 = new ArrayList();
                for (Object next2 : iterable4) {
                    if (((Inmate) next2).getPrison_state() != null) {
                        arrayList5.add(next2);
                    }
                }
                Iterable<Inmate> iterable6 = (List) arrayList5;
                Collection arrayList6 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable6, 10));
                for (Inmate prison_state : iterable6) {
                    arrayList6.add(String.valueOf(prison_state.getPrison_state()));
                }
                CollectionsKt.addAll(collection, (List) arrayList6);
            }
            String[] strArr = {"mother", "father", "sister", "brother", "grandparents", "husband", "wife", "friend"};
            Collection arrayList7 = new ArrayList();
            for (Object next3 : list2) {
                String relationship = ((Inmate) next3).getRelationship();
                if (relationship != null) {
                    String lowerCase = relationship.toLowerCase();
                    Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase()");
                    if (ArraysKt.indexOf((T[]) strArr, lowerCase) >= 0) {
                        arrayList7.add(next3);
                    }
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
            }
            HashSet hashSet = new HashSet();
            ArrayList arrayList8 = new ArrayList();
            for (Object next4 : (List) arrayList7) {
                if (hashSet.add(((Inmate) next4).getRelationship())) {
                    arrayList8.add(next4);
                }
            }
            Iterable<Inmate> iterable7 = arrayList8;
            Collection arrayList9 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable7, 10));
            for (Inmate relationship2 : iterable7) {
                StringBuilder sb = new StringBuilder();
                sb.append("visitor_relationship_");
                String relationship3 = relationship2.getRelationship();
                if (relationship3 != null) {
                    String lowerCase2 = relationship3.toLowerCase();
                    Intrinsics.checkExpressionValueIsNotNull(lowerCase2, "(this as java.lang.String).toLowerCase()");
                    sb.append(lowerCase2);
                    arrayList9.add(sb.toString());
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
            }
            CollectionsKt.addAll(collection, (List) arrayList9);
            CollectionsKt.removeAll(mutableSetOf, linkedHashSet);
            UAirship shared = UAirship.shared();
            Intrinsics.checkExpressionValueIsNotNull(shared, "UAirship.shared()");
            NamedUser namedUser = shared.getNamedUser();
            Intrinsics.checkExpressionValueIsNotNull(namedUser, "UAirship.shared().namedUser");
            namedUser.editTagGroups().removeTags(AirshipAnalytics.TAG_GROUP, mutableSetOf).addTags(AirshipAnalytics.TAG_GROUP, linkedHashSet).apply();
            ((HomeView) getViewState()).showApprovedInmates(approved(list2));
            this.isMessageViewVisible = true;
            doMessageViewUpdating();
        }
    }

    private final List<Inmate> approved(List<Inmate> list) {
        Collection arrayList = new ArrayList();
        for (Object next : list) {
            Inmate inmate = (Inmate) next;
            if (Intrinsics.areEqual((Object) inmate.getApproved(), (Object) "yes") && !inmate.isFraud()) {
                arrayList.add(next);
            }
        }
        return (List) arrayList;
    }

    public final void refresh() {
        updateMessageView(true);
        getInmates();
    }

    public final void getNotificationsCount() {
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = CommonKt.applyAsync(this.notificationDao.countAll()).subscribe(new HomePresenter$getNotificationsCount$1(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "notificationDao.countAll…ateNotificationMenu(it) }");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    public final void onViewAllPendingsClicked() {
        this.router.navigateTo(Screens.PendingScreen.INSTANCE);
    }

    public final void onViewTutorialsClicked() {
        this.router.navigateTo(Screens.TutorialsScreen.INSTANCE);
    }

    public final void onViewFaqClicked() {
        this.router.navigateTo(Screens.HelpAndContactScreen.INSTANCE);
    }

    public final void onMessageInmateClicked(Inmate inmate) {
        Intrinsics.checkParameterIsNotNull(inmate, "inmate");
        this.router.navigateTo(new Screens.ConversationScreen(inmate.getId()));
    }

    public final void onNotificationsClicked() {
        this.router.navigateTo(Screens.NotificationsScreen.INSTANCE);
    }

    public final void onInmateClicked(Inmate inmate) {
        Intrinsics.checkParameterIsNotNull(inmate, "inmate");
        this.router.navigateTo(new Screens.InmateDetailScreen(inmate));
    }

    public final void onNextVisitClicked() {
        long time = new Date().getTime() / ((long) 1000);
        CompositeDisposable disposables = getDisposables();
        Flowable<List<ScheduledVisit>> take = this.visitDao.getScheduledVisits(time).take(1);
        Intrinsics.checkExpressionValueIsNotNull(take, "visitDao.getScheduledVis…\n                .take(1)");
        Disposable subscribe = CommonKt.applyAsync(take).subscribe(new HomePresenter$onNextVisitClicked$1(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "visitDao.getScheduledVis…      }\n                }");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    public final boolean onBackPressed() {
        this.router.exit();
        return true;
    }

    public final void onTabSelected() {
        getInmates();
    }

    public final void onAppearClicked(List<Inmate> list) {
        Intrinsics.checkParameterIsNotNull(list, "inmates");
        CompositeDisposable disposables = getDisposables();
        AccountApi accountApi2 = this.accountApi;
        Iterable<Inmate> iterable = list;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Inmate id : iterable) {
            arrayList.add(id.getId());
        }
        Disposable subscribe = CommonKt.applyAsync(accountApi2.changeVisibleToInmatesState((List) arrayList)).subscribe(new HomePresenter$onAppearClicked$2(this), new HomePresenter$onAppearClicked$3(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "accountApi.changeVisible…error)\n                })");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    /* access modifiers changed from: private */
    public final void getInmates() {
        String visitor_id;
        User user = this.authHolder.getUser();
        if (user != null && (visitor_id = user.getVisitor_id()) != null) {
            HomePresenter homePresenter = this;
            CommonKt.applyAsync(this.api.getInmates(visitor_id)).subscribe(new HomePresenter$sam$i$io_reactivex_functions_Consumer$0(new HomePresenter$getInmates$1$1(homePresenter)), new HomePresenter$sam$i$io_reactivex_functions_Consumer$0(new HomePresenter$getInmates$1$2(homePresenter)));
        }
    }

    private final void getVisits() {
        CompositeDisposable disposables = getDisposables();
        HomePresenter homePresenter = this;
        Disposable subscribe = CommonKt.applyAsync(this.api.getVisits()).map(HomePresenter$getVisits$1.INSTANCE).subscribe(new HomePresenter$sam$io_reactivex_functions_Consumer$0(new HomePresenter$getVisits$2(homePresenter)), new HomePresenter$sam$io_reactivex_functions_Consumer$0(new HomePresenter$getVisits$3(homePresenter)));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "api.getVisits()\n        …ccess, this::handleError)");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    /* access modifiers changed from: private */
    public final /* synthetic */ <T> void handleSuccess(Response<? extends List<? extends T>> response) {
        if (response.getOk()) {
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            Class<Object> cls = Object.class;
            if (Intrinsics.areEqual((Object) cls, (Object) Inmate.class)) {
                Object body = response.getBody();
                if (body != null) {
                    List list = (List) body;
                    Observable fromCallable = Observable.fromCallable(new HomePresenter$handleSuccess$1(this, list));
                    Intrinsics.checkExpressionValueIsNotNull(fromCallable, "Observable.fromCallable …es)\n                    }");
                    CommonKt.applyAsync(fromCallable).subscribe(HomePresenter$handleSuccess$2.INSTANCE);
                    setAudioVideoTags(list);
                    this.inmatesSubject.onNext(list);
                    updateTagsAndShowInmatesList(this.heartbeatRepository.getHeartbeatState().getValue());
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.List<com.forasoft.homewavvisitor.model.data.Inmate>");
            } else if (Intrinsics.areEqual((Object) cls, (Object) ScheduledVisit.class)) {
                Object body2 = response.getBody();
                if (body2 != null) {
                    Observable fromCallable2 = Observable.fromCallable(new HomePresenter$handleSuccess$3(this, (List) body2));
                    Intrinsics.checkExpressionValueIsNotNull(fromCallable2, "Observable.fromCallable …ts)\n                    }");
                    CommonKt.applyAsync(fromCallable2).subscribe(HomePresenter$handleSuccess$4.INSTANCE);
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.List<com.forasoft.homewavvisitor.model.data.account.ScheduledVisit>");
            }
        } else {
            handleError(new Throwable(response.getError()));
        }
    }

    /* access modifiers changed from: private */
    public final void handleError(Throwable th) {
        if ((th instanceof MalformedJsonException) || (th instanceof SocketTimeoutException)) {
            ((HomeView) getViewState()).showServerError();
        }
        Timber.d("onError " + th.getMessage(), new Object[0]);
    }

    /* access modifiers changed from: private */
    public final void setAudioVideoTags(List<Inmate> list) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        UAirship shared = UAirship.shared();
        Intrinsics.checkExpressionValueIsNotNull(shared, "UAirship.shared()");
        NamedUser namedUser = shared.getNamedUser();
        Intrinsics.checkExpressionValueIsNotNull(namedUser, "UAirship.shared().namedUser");
        Iterable iterable = list;
        boolean z6 = iterable instanceof Collection;
        boolean z7 = false;
        if (!z6 || !((Collection) iterable).isEmpty()) {
            Iterator it = iterable.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Inmate inmate = (Inmate) it.next();
                if (inmate.isVideoCallsDisabled() || inmate.isVoiceCallsDisabled()) {
                    z5 = false;
                    continue;
                } else {
                    z5 = true;
                    continue;
                }
                if (z5) {
                    z = true;
                    break;
                }
            }
        }
        z = false;
        if (!z6 || !((Collection) iterable).isEmpty()) {
            Iterator it2 = iterable.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                Inmate inmate2 = (Inmate) it2.next();
                if (inmate2.isVoiceCallsDisabled() || !inmate2.isVideoCallsDisabled()) {
                    z4 = false;
                    continue;
                } else {
                    z4 = true;
                    continue;
                }
                if (z4) {
                    z2 = true;
                    break;
                }
            }
        }
        z2 = false;
        if (!z6 || !((Collection) iterable).isEmpty()) {
            Iterator it3 = iterable.iterator();
            while (true) {
                if (!it3.hasNext()) {
                    break;
                }
                Inmate inmate3 = (Inmate) it3.next();
                if (inmate3.isVideoCallsDisabled() || !inmate3.isVoiceCallsDisabled()) {
                    z3 = false;
                    continue;
                } else {
                    z3 = true;
                    continue;
                }
                if (z3) {
                    z7 = true;
                    break;
                }
            }
        }
        Set mutableSetOf = SetsKt.mutableSetOf("visitor_with_voice_inmates", "visitor_with_video_inmates", "visitor_with_voice_and_video_inmates");
        Set linkedHashSet = new LinkedHashSet();
        if (z) {
            linkedHashSet.add("visitor_with_voice_and_video_inmates");
        }
        if (z2) {
            linkedHashSet.add("visitor_with_voice_inmates");
        }
        if (z7) {
            linkedHashSet.add("visitor_with_video_inmates");
        }
        namedUser.editTagGroups().removeTags(AirshipAnalytics.TAG_GROUP, mutableSetOf).apply();
        if (!list.isEmpty()) {
            namedUser.editTagGroups().addTags(AirshipAnalytics.TAG_GROUP, linkedHashSet).apply();
        }
    }

    public final void sendAnalyticsMessageCenterOpened() {
        this.analytics.onViewingMessageCenter();
    }

    public void beforeDestroy() {
        super.beforeDestroy();
        this.inmatesSubject.onNext(CollectionsKt.emptyList());
        Cancelable cancelable = this.updateCancelToken;
        if (cancelable != null) {
            cancelable.cancel();
        }
        this.notifier.remove(this.busListener);
    }
}
