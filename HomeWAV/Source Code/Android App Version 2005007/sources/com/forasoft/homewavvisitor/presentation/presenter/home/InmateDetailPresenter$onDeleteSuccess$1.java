package com.forasoft.homewavvisitor.presentation.presenter.home;

import java.util.concurrent.Callable;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "call"}, k = 3, mv = {1, 1, 16})
/* compiled from: InmateDetailPresenter.kt */
final class InmateDetailPresenter$onDeleteSuccess$1<V> implements Callable<T> {
    final /* synthetic */ InmateDetailPresenter this$0;

    InmateDetailPresenter$onDeleteSuccess$1(InmateDetailPresenter inmateDetailPresenter) {
        this.this$0 = inmateDetailPresenter;
    }

    public final void call() {
        this.this$0.inmateDao.deleteInmate(this.this$0.inmate.getId());
    }
}
