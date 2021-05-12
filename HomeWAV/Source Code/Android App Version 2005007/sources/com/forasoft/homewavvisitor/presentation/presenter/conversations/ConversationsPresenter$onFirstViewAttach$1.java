package com.forasoft.homewavvisitor.presentation.presenter.conversations;

import com.forasoft.homewavvisitor.model.data.State;
import io.reactivex.functions.Function;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "", "it", "Lcom/forasoft/homewavvisitor/model/data/State;", "apply"}, k = 3, mv = {1, 1, 16})
/* compiled from: ConversationsPresenter.kt */
final class ConversationsPresenter$onFirstViewAttach$1<T, R> implements Function<T, R> {
    public static final ConversationsPresenter$onFirstViewAttach$1 INSTANCE = new ConversationsPresenter$onFirstViewAttach$1();

    ConversationsPresenter$onFirstViewAttach$1() {
    }

    public final Map<String, String> apply(State state) {
        Intrinsics.checkParameterIsNotNull(state, "it");
        return state.getStatus();
    }
}
