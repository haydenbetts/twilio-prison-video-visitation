package com.forasoft.homewavvisitor.presentation.presenter.home;

import com.forasoft.homewavvisitor.model.data.State;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "heartbeatState", "Lcom/forasoft/homewavvisitor/model/data/State;", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: HomePresenter.kt */
final class HomePresenter$onFirstViewAttach$1<T> implements Consumer<State> {
    final /* synthetic */ HomePresenter this$0;

    HomePresenter$onFirstViewAttach$1(HomePresenter homePresenter) {
        this.this$0 = homePresenter;
    }

    public final void accept(State state) {
        this.this$0.updateTagsAndShowInmatesList(state);
    }
}
