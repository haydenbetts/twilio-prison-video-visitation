package com.forasoft.homewavvisitor.presentation.presenter.home;

import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "T", "call"}, k = 3, mv = {1, 1, 16})
/* compiled from: HomePresenter.kt */
public final class HomePresenter$handleSuccess$3<V> implements Callable<T> {
    final /* synthetic */ List $visits;
    final /* synthetic */ HomePresenter this$0;

    public HomePresenter$handleSuccess$3(HomePresenter homePresenter, List list) {
        this.this$0 = homePresenter;
        this.$visits = list;
    }

    public final void call() {
        this.this$0.visitDao.saveVisits(this.$visits);
    }
}
