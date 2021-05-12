package com.forasoft.homewavvisitor.presentation.presenter;

import com.forasoft.homewavvisitor.model.data.State;
import com.forasoft.homewavvisitor.presentation.view.MainView;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/forasoft/homewavvisitor/model/data/State;", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: MainPresenter.kt */
final class MainPresenter$onFirstViewAttach$1<T> implements Consumer<State> {
    final /* synthetic */ MainPresenter this$0;

    MainPresenter$onFirstViewAttach$1(MainPresenter mainPresenter) {
        this.this$0 = mainPresenter;
    }

    public final void accept(State state) {
        if (state != null) {
            ((MainView) this.this$0.getViewState()).updateMessagesBadge(Integer.parseInt(state.getUnreadMessages()));
        }
    }
}
