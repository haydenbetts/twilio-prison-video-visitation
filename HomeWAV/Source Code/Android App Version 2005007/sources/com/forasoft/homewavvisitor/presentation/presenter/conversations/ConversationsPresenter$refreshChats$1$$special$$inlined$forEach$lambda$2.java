package com.forasoft.homewavvisitor.presentation.presenter.conversations;

import com.forasoft.homewavvisitor.presentation.view.conversations.ConversationsView;
import com.google.gson.stream.MalformedJsonException;
import io.reactivex.functions.Consumer;
import java.net.SocketTimeoutException;
import kotlin.Metadata;
import timber.log.Timber;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005¨\u0006\u0006"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "accept", "com/forasoft/homewavvisitor/presentation/presenter/conversations/ConversationsPresenter$refreshChats$1$1$2"}, k = 3, mv = {1, 1, 16})
/* compiled from: ConversationsPresenter.kt */
final class ConversationsPresenter$refreshChats$1$$special$$inlined$forEach$lambda$2<T> implements Consumer<Throwable> {
    final /* synthetic */ ConversationsPresenter$refreshChats$1 this$0;

    ConversationsPresenter$refreshChats$1$$special$$inlined$forEach$lambda$2(ConversationsPresenter$refreshChats$1 conversationsPresenter$refreshChats$1) {
        this.this$0 = conversationsPresenter$refreshChats$1;
    }

    public final void accept(Throwable th) {
        Timber.d(th);
        if ((th instanceof MalformedJsonException) || (th instanceof SocketTimeoutException)) {
            ((ConversationsView) this.this$0.this$0.getViewState()).showServerError();
        }
    }
}
