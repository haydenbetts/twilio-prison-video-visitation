package com.forasoft.homewavvisitor.presentation.presenter.home;

import com.forasoft.homewavvisitor.model.data.Inmate;
import io.reactivex.functions.Function;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "it", "", "apply"}, k = 3, mv = {1, 1, 16})
/* compiled from: InmateDetailPresenter.kt */
final class InmateDetailPresenter$onFirstViewAttach$2<T, R> implements Function<T, R> {
    final /* synthetic */ InmateDetailPresenter this$0;

    InmateDetailPresenter$onFirstViewAttach$2(InmateDetailPresenter inmateDetailPresenter) {
        this.this$0 = inmateDetailPresenter;
    }

    public final Inmate apply(List<Inmate> list) {
        Intrinsics.checkParameterIsNotNull(list, "it");
        for (Inmate inmate : list) {
            if (Intrinsics.areEqual((Object) inmate.getId(), (Object) this.this$0.inmate.getId())) {
                return inmate;
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }
}
