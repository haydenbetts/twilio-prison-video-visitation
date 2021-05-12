package com.forasoft.homewavvisitor.presentation.presenter.conversations;

import com.forasoft.homewavvisitor.presentation.view.conversations.ConversationView;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "accept", "(Ljava/lang/Integer;)V"}, k = 3, mv = {1, 1, 16})
/* compiled from: ConversationPresenter.kt */
final class ConversationPresenter$getNotificationsCount$1<T> implements Consumer<Integer> {
    final /* synthetic */ ConversationPresenter this$0;

    ConversationPresenter$getNotificationsCount$1(ConversationPresenter conversationPresenter) {
        this.this$0 = conversationPresenter;
    }

    public final void accept(Integer num) {
        Intrinsics.checkExpressionValueIsNotNull(num, "it");
        ((ConversationView) this.this$0.getViewState()).updateNotificationMenu(num.intValue());
    }
}
