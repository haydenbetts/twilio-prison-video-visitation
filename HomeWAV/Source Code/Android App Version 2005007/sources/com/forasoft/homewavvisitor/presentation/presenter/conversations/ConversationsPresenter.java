package com.forasoft.homewavvisitor.presentation.presenter.conversations;

import com.forasoft.homewavvisitor.HomewavRouter;
import com.forasoft.homewavvisitor.dao.InmateDao;
import com.forasoft.homewavvisitor.dao.NotificationDao;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.data.Chat;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.model.data.Message;
import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.model.data.auth.User;
import com.forasoft.homewavvisitor.model.repository.HeartbeatRepository;
import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import com.forasoft.homewavvisitor.navigation.BusNotifier;
import com.forasoft.homewavvisitor.navigation.ResultListener;
import com.forasoft.homewavvisitor.navigation.Screens;
import com.forasoft.homewavvisitor.presentation.BasePresenter;
import com.forasoft.homewavvisitor.presentation.view.conversations.ConversationsView;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxkotlin.DisposableKt;
import io.reactivex.subjects.BehaviorSubject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import moxy.InjectViewState;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000}\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0010\u0000\n\u0002\b\u0005*\u0001\u0014\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B?\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0002\u0010\u0012J\u0016\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001f2\u0006\u0010 \u001a\u00020!H\u0002J\u0006\u0010\"\u001a\u00020#J\u0006\u0010$\u001a\u00020#J\u000e\u0010%\u001a\u00020#2\u0006\u0010&\u001a\u00020\u001cJ\u000e\u0010'\u001a\u00020#2\u0006\u0010(\u001a\u00020\u0018J\b\u0010)\u001a\u00020#H\u0016J\b\u0010*\u001a\u00020#H\u0014J\u0006\u0010+\u001a\u00020#J\u0012\u0010,\u001a\u00020#2\b\u0010-\u001a\u0004\u0018\u00010.H\u0016J\u0006\u0010/\u001a\u00020#J\u0006\u00100\u001a\u00020#J\u0006\u00101\u001a\u00020#J\b\u00102\u001a\u00020#H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u00020\u0014X\u0004¢\u0006\u0004\n\u0002\u0010\u0015R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R$\u0010\u0019\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u001c0\u001bj\b\u0012\u0004\u0012\u00020\u001c`\u001d0\u001aX\u0004¢\u0006\u0002\n\u0000¨\u00063"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/presenter/conversations/ConversationsPresenter;", "Lcom/forasoft/homewavvisitor/presentation/BasePresenter;", "Lcom/forasoft/homewavvisitor/presentation/view/conversations/ConversationsView;", "Lcom/forasoft/homewavvisitor/navigation/ResultListener;", "router", "Lcom/forasoft/homewavvisitor/HomewavRouter;", "api", "Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;", "authHolder", "Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;", "notificationDao", "Lcom/forasoft/homewavvisitor/dao/NotificationDao;", "inmatesDao", "Lcom/forasoft/homewavvisitor/dao/InmateDao;", "heartbeatRepository", "Lcom/forasoft/homewavvisitor/model/repository/HeartbeatRepository;", "notifier", "Lcom/forasoft/homewavvisitor/navigation/BusNotifier;", "(Lcom/forasoft/homewavvisitor/HomewavRouter;Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;Lcom/forasoft/homewavvisitor/dao/NotificationDao;Lcom/forasoft/homewavvisitor/dao/InmateDao;Lcom/forasoft/homewavvisitor/model/repository/HeartbeatRepository;Lcom/forasoft/homewavvisitor/navigation/BusNotifier;)V", "busListener", "com/forasoft/homewavvisitor/presentation/presenter/conversations/ConversationsPresenter$busListener$1", "Lcom/forasoft/homewavvisitor/presentation/presenter/conversations/ConversationsPresenter$busListener$1;", "deletedMessages", "", "", "subject", "Lio/reactivex/subjects/BehaviorSubject;", "Ljava/util/ArrayList;", "Lcom/forasoft/homewavvisitor/model/data/Chat;", "Lkotlin/collections/ArrayList;", "getChatForInmate", "Lio/reactivex/Single;", "inmate", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "getNotificationsCount", "", "onBackPressed", "onConversationClick", "chat", "onDeleteChatClicked", "inmateId", "onDestroy", "onFirstViewAttach", "onNotificationsClicked", "onResult", "resultData", "", "onSnackbarDismissed", "onTabSelected", "onUndoClicked", "refreshChats", "app_release"}, k = 1, mv = {1, 1, 16})
@InjectViewState
/* compiled from: ConversationsPresenter.kt */
public final class ConversationsPresenter extends BasePresenter<ConversationsView> implements ResultListener {
    private final HomewavApi api;
    private final AuthHolder authHolder;
    private final ConversationsPresenter$busListener$1 busListener = new ConversationsPresenter$busListener$1(this);
    /* access modifiers changed from: private */
    public final List<String> deletedMessages = new ArrayList();
    /* access modifiers changed from: private */
    public final HeartbeatRepository heartbeatRepository;
    private final InmateDao inmatesDao;
    private final NotificationDao notificationDao;
    private final BusNotifier notifier;
    private final HomewavRouter router;
    /* access modifiers changed from: private */
    public final BehaviorSubject<ArrayList<Chat>> subject;

    @Inject
    public ConversationsPresenter(HomewavRouter homewavRouter, HomewavApi homewavApi, AuthHolder authHolder2, NotificationDao notificationDao2, InmateDao inmateDao, HeartbeatRepository heartbeatRepository2, BusNotifier busNotifier) {
        Intrinsics.checkParameterIsNotNull(homewavRouter, "router");
        Intrinsics.checkParameterIsNotNull(homewavApi, "api");
        Intrinsics.checkParameterIsNotNull(authHolder2, "authHolder");
        Intrinsics.checkParameterIsNotNull(notificationDao2, "notificationDao");
        Intrinsics.checkParameterIsNotNull(inmateDao, "inmatesDao");
        Intrinsics.checkParameterIsNotNull(heartbeatRepository2, "heartbeatRepository");
        Intrinsics.checkParameterIsNotNull(busNotifier, "notifier");
        this.router = homewavRouter;
        this.api = homewavApi;
        this.authHolder = authHolder2;
        this.notificationDao = notificationDao2;
        this.inmatesDao = inmateDao;
        this.heartbeatRepository = heartbeatRepository2;
        this.notifier = busNotifier;
        BehaviorSubject<ArrayList<Chat>> createDefault = BehaviorSubject.createDefault(new ArrayList());
        Intrinsics.checkExpressionValueIsNotNull(createDefault, "BehaviorSubject.createDefault(ArrayList())");
        this.subject = createDefault;
    }

    /* access modifiers changed from: protected */
    public void onFirstViewAttach() {
        refreshChats();
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = this.heartbeatRepository.getHeartbeatState().map(ConversationsPresenter$onFirstViewAttach$1.INSTANCE).subscribe(new ConversationsPresenter$onFirstViewAttach$2(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "heartbeatRepository.hear…(chats)\n                }");
        DisposableKt.plusAssign(disposables, subscribe);
        this.notifier.add(this.busListener);
    }

    public void onResult(Object obj) {
        if (obj instanceof Message) {
            Object clone = this.subject.getValue().clone();
            if (clone != null) {
                ArrayList arrayList = (ArrayList) clone;
                List list = arrayList;
                Iterator it = list.iterator();
                int i = 0;
                while (true) {
                    if (!it.hasNext()) {
                        i = -1;
                        break;
                    } else if (Intrinsics.areEqual((Object) ((Chat) it.next()).getInmate().getId(), (Object) ((Message) obj).getInmateId())) {
                        break;
                    } else {
                        i++;
                    }
                }
                arrayList.set(i, Chat.copy$default((Chat) arrayList.get(i), (Inmate) null, (Message) obj, 1, (Object) null));
                this.subject.onNext(arrayList);
                ((ConversationsView) getViewState()).displayConversations(list);
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.ArrayList<com.forasoft.homewavvisitor.model.data.Chat> /* = java.util.ArrayList<com.forasoft.homewavvisitor.model.data.Chat> */");
            }
        }
        this.router.removeResultListener(0);
    }

    public final void onConversationClick(Chat chat) {
        Intrinsics.checkParameterIsNotNull(chat, "chat");
        this.router.setResultListener(0, this);
        this.router.navigateTo(new Screens.ConversationScreen(chat.getInmate().getId()));
    }

    public final void onBackPressed() {
        this.router.exit();
    }

    public final void onTabSelected() {
        refreshChats();
    }

    public final void getNotificationsCount() {
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = CommonKt.applyAsync(this.notificationDao.countAll()).subscribe(new ConversationsPresenter$getNotificationsCount$1(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "notificationDao.countAll…ateNotificationMenu(it) }");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    public final void onNotificationsClicked() {
        this.router.navigateTo(Screens.NotificationsScreen.INSTANCE);
    }

    private final void refreshChats() {
        CompositeDisposable disposables = getDisposables();
        InmateDao inmateDao = this.inmatesDao;
        User user = this.authHolder.getUser();
        String visitor_id = user != null ? user.getVisitor_id() : null;
        if (visitor_id == null) {
            Intrinsics.throwNpe();
        }
        Flowable<List<Inmate>> take = inmateDao.getApprovedInmates(visitor_id).take(1);
        Intrinsics.checkExpressionValueIsNotNull(take, "inmatesDao.getApprovedIn…\n                .take(1)");
        Disposable subscribe = CommonKt.applyAsync(take).subscribe(new ConversationsPresenter$refreshChats$1(this), ConversationsPresenter$refreshChats$2.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "inmatesDao.getApprovedIn… }\n                }, {})");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    /* access modifiers changed from: private */
    public final Single<Chat> getChatForInmate(Inmate inmate) {
        Single<Chat> map = HomewavApi.DefaultImpls.getLastMessages$default(this.api, inmate.getId(), 1, (Integer) null, 4, (Object) null).map(new ConversationsPresenter$getChatForInmate$1(inmate));
        Intrinsics.checkExpressionValueIsNotNull(map, "api.getLastMessages(inma…      }\n                }");
        return map;
    }

    public final void onDeleteChatClicked(String str) {
        Intrinsics.checkParameterIsNotNull(str, "inmateId");
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = CommonKt.applyAsync(this.api.deleteChat(str)).subscribe(new ConversationsPresenter$onDeleteChatClicked$1(this), ConversationsPresenter$onDeleteChatClicked$2.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "api.deleteChat(inmateId)…)\n                }, { })");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    public final void onUndoClicked() {
        if (!this.deletedMessages.isEmpty()) {
            CompositeDisposable disposables = getDisposables();
            Disposable subscribe = CommonKt.applyAsync(this.api.undoChatDeletion(this.deletedMessages)).subscribe(ConversationsPresenter$onUndoClicked$1.INSTANCE, ConversationsPresenter$onUndoClicked$2.INSTANCE);
            Intrinsics.checkExpressionValueIsNotNull(subscribe, "api.undoChatDeletion(del…       .subscribe({}, {})");
            DisposableKt.plusAssign(disposables, subscribe);
        }
    }

    public final void onSnackbarDismissed() {
        refreshChats();
    }

    public void onDestroy() {
        super.onDestroy();
        this.notifier.remove(this.busListener);
    }
}
