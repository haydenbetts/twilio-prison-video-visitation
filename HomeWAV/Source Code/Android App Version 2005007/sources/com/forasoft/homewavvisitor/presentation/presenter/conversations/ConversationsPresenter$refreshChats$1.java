package com.forasoft.homewavvisitor.presentation.presenter.conversations;

import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.model.data.State;
import com.forasoft.homewavvisitor.presentation.view.conversations.ConversationsView;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: ConversationsPresenter.kt */
final class ConversationsPresenter$refreshChats$1<T> implements Consumer<List<? extends Inmate>> {
    final /* synthetic */ ConversationsPresenter this$0;

    ConversationsPresenter$refreshChats$1(ConversationsPresenter conversationsPresenter) {
        this.this$0 = conversationsPresenter;
    }

    public final void accept(List<Inmate> list) {
        List<Inmate> list2 = list;
        State value = this.this$0.heartbeatRepository.getHeartbeatState().getValue();
        Map<String, String> status = value != null ? value.getStatus() : null;
        Intrinsics.checkExpressionValueIsNotNull(list2, "it");
        Iterable<Inmate> iterable = list2;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Inmate inmate : iterable) {
            String str = status != null ? status.get(inmate.getId()) : null;
            if (str != null) {
                inmate = Inmate.copy$default(inmate, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, str, (String) null, (String) null, false, false, false, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, false, false, (String) null, (String) null, -131073, -1, 63, (Object) null);
            }
            arrayList.add(inmate);
        }
        List<Inmate> list3 = (List) arrayList;
        if (list3.isEmpty()) {
            ((ConversationsView) this.this$0.getViewState()).displayConversations(CollectionsKt.emptyList());
            return;
        }
        for (Inmate access$getChatForInmate : list3) {
            this.this$0.getDisposables().add(CommonKt.applyAsync(this.this$0.getChatForInmate(access$getChatForInmate)).subscribe(new ConversationsPresenter$refreshChats$1$$special$$inlined$forEach$lambda$1(this), new ConversationsPresenter$refreshChats$1$$special$$inlined$forEach$lambda$2(this)));
        }
    }
}
