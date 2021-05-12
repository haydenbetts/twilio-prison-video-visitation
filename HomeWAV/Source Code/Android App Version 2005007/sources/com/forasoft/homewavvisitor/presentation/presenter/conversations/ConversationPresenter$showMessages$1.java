package com.forasoft.homewavvisitor.presentation.presenter.conversations;

import com.forasoft.homewavvisitor.dao.MessageDao;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.data.CallEndedEvent;
import com.forasoft.homewavvisitor.model.data.CallEntity;
import com.forasoft.homewavvisitor.model.data.CallStartedEvent;
import com.forasoft.homewavvisitor.model.data.ChatItem;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.model.data.Message;
import com.forasoft.homewavvisitor.model.data.Notification;
import com.forasoft.homewavvisitor.model.data.auth.User;
import com.forasoft.homewavvisitor.model.notifications.MessageListenerService;
import com.forasoft.homewavvisitor.presentation.view.conversations.ConversationView;
import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.rxkotlin.DisposableKt;
import io.reactivex.rxkotlin.Flowables;
import io.reactivex.rxkotlin.SubscribersKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import org.threeten.bp.LocalDateTime;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: ConversationPresenter.kt */
final class ConversationPresenter$showMessages$1<T> implements Consumer<Inmate> {
    final /* synthetic */ ConversationPresenter this$0;

    ConversationPresenter$showMessages$1(ConversationPresenter conversationPresenter) {
        this.this$0 = conversationPresenter;
    }

    public final void accept(Inmate inmate) {
        CompositeDisposable access$getDisposables$p = this.this$0.getDisposables();
        Flowables flowables = Flowables.INSTANCE;
        MessageDao access$getMessageDao$p = this.this$0.messageDao;
        String access$getInmateId$p = this.this$0.inmateId;
        User user = this.this$0.authHolder.getUser();
        String visitor_id = user != null ? user.getVisitor_id() : null;
        if (visitor_id == null) {
            Intrinsics.throwNpe();
        }
        Flowable<R> map = access$getMessageDao$p.getDialog(access$getInmateId$p, visitor_id).map(new Function<T, R>(this) {
            final /* synthetic */ ConversationPresenter$showMessages$1 this$0;

            {
                this.this$0 = r1;
            }

            public final List<Message> apply(List<Message> list) {
                Intrinsics.checkParameterIsNotNull(list, "it");
                return this.this$0.this$0.updateStatus(list);
            }
        }).map(AnonymousClass2.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "messageDao.getDialog(inm….map { it as ChatItem } }");
        Flowable<R> map2 = this.this$0.notificationDao.getByType(MessageListenerService.TYPE_LOW_BALANCE).map(new Function<T, R>(this) {
            final /* synthetic */ ConversationPresenter$showMessages$1 this$0;

            {
                this.this$0 = r1;
            }

            public final List<Notification> apply(List<Notification> list) {
                Intrinsics.checkParameterIsNotNull(list, "it");
                return this.this$0.this$0.filterByInmate(list);
            }
        }).map(AnonymousClass4.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map2, "notificationDao.getByTyp….map { it as ChatItem } }");
        Flowable<R> map3 = this.this$0.callDao.getCallsWithInmate(this.this$0.inmateId).map(new Function<T, R>(this) {
            final /* synthetic */ ConversationPresenter$showMessages$1 this$0;

            {
                this.this$0 = r1;
            }

            public final List<ChatItem> apply(List<CallEntity> list) {
                List<CallEntity> list2 = list;
                Intrinsics.checkParameterIsNotNull(list2, "it");
                Iterable iterable = list2;
                Collection arrayList = new ArrayList();
                for (Object next : iterable) {
                    if (((CallEntity) next).getConnected() != null) {
                        arrayList.add(next);
                    }
                }
                Iterable<CallEntity> iterable2 = (List) arrayList;
                Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable2, 10));
                for (CallEntity copy$default : iterable2) {
                    arrayList2.add(new CallStartedEvent(CallEntity.copy$default(copy$default, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (LocalDateTime) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, ((Inmate) this.this$0.this$0.inmate.getValue()).getFull_name(), (String) null, (String) null, (String) null, (String) null, (String) null, (LocalDateTime) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, -67108865, 2047, (Object) null)));
                }
                List list3 = (List) arrayList2;
                Collection arrayList3 = new ArrayList();
                for (Object next2 : iterable) {
                    if (((CallEntity) next2).getDisconnected() != null) {
                        arrayList3.add(next2);
                    }
                }
                Iterable<CallEntity> iterable3 = (List) arrayList3;
                Collection arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable3, 10));
                for (CallEntity copy$default2 : iterable3) {
                    arrayList4.add(new CallEndedEvent(CallEntity.copy$default(copy$default2, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (LocalDateTime) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, ((Inmate) this.this$0.this$0.inmate.getValue()).getFull_name(), (String) null, (String) null, (String) null, (String) null, (String) null, (LocalDateTime) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, -67108865, 2047, (Object) null)));
                }
                return CollectionsKt.plus(list3, (List) arrayList4);
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(map3, "callDao.getCallsWithInma…                        }");
        Flowable combineLatest = Flowable.combineLatest(map, map2, map3, new ConversationPresenter$showMessages$1$$special$$inlined$combineLatest$1());
        if (combineLatest == null) {
            Intrinsics.throwNpe();
        }
        Flowable map4 = combineLatest.map(AnonymousClass7.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map4, "Flowables.combineLatest(…ding(ChatItem::created) }");
        Flowable doOnNext = CommonKt.applyAsync(map4).doOnNext(new Consumer<List<? extends ChatItem>>(this) {
            final /* synthetic */ ConversationPresenter$showMessages$1 this$0;

            {
                this.this$0 = r1;
            }

            public final void accept(List<? extends ChatItem> list) {
                Object obj;
                ConversationPresenter conversationPresenter = this.this$0.this$0;
                Intrinsics.checkExpressionValueIsNotNull(list, "it");
                Iterator it = list.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        obj = null;
                        break;
                    }
                    obj = it.next();
                    if (((ChatItem) obj) instanceof Message) {
                        break;
                    }
                }
                conversationPresenter.lastMessage = (ChatItem) obj;
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(doOnNext, "Flowables.combineLatest(…rNull { it is Message } }");
        DisposableKt.plusAssign(access$getDisposables$p, SubscribersKt.subscribeBy$default(doOnNext, (Function1) null, (Function0) null, (Function1) new Function1<List<? extends ChatItem>, Unit>((ConversationView) this.this$0.getViewState()) {
            public final String getName() {
                return "displayMessages";
            }

            public final KDeclarationContainer getOwner() {
                return Reflection.getOrCreateKotlinClass(ConversationView.class);
            }

            public final String getSignature() {
                return "displayMessages(Ljava/util/List;)V";
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((List<? extends ChatItem>) (List) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(List<? extends ChatItem> list) {
                Intrinsics.checkParameterIsNotNull(list, "p1");
                ((ConversationView) this.receiver).displayMessages(list);
            }
        }, 3, (Object) null));
    }
}
