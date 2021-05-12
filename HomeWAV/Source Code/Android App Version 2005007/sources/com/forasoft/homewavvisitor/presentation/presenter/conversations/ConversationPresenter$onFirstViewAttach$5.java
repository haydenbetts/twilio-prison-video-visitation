package com.forasoft.homewavvisitor.presentation.presenter.conversations;

import com.forasoft.homewavvisitor.model.data.Inmate;
import io.reactivex.functions.Function;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "apply"}, k = 3, mv = {1, 1, 16})
/* compiled from: ConversationPresenter.kt */
final class ConversationPresenter$onFirstViewAttach$5<T, R> implements Function<T, R> {
    public static final ConversationPresenter$onFirstViewAttach$5 INSTANCE = new ConversationPresenter$onFirstViewAttach$5();

    ConversationPresenter$onFirstViewAttach$5() {
    }

    public /* bridge */ /* synthetic */ Object apply(Object obj) {
        return Integer.valueOf(apply((Inmate) obj));
    }

    public final int apply(Inmate inmate) {
        Intrinsics.checkParameterIsNotNull(inmate, "it");
        return Integer.parseInt(inmate.getPrison_max_text_message_length());
    }
}
