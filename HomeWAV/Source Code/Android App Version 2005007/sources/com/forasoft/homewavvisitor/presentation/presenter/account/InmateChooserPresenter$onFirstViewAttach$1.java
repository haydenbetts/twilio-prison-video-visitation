package com.forasoft.homewavvisitor.presentation.presenter.account;

import com.forasoft.homewavvisitor.model.data.Inmate;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "it", "apply"}, k = 3, mv = {1, 1, 16})
/* compiled from: InmateChooserPresenter.kt */
final class InmateChooserPresenter$onFirstViewAttach$1<T, R> implements Function<T, R> {
    final /* synthetic */ InmateChooserPresenter this$0;

    InmateChooserPresenter$onFirstViewAttach$1(InmateChooserPresenter inmateChooserPresenter) {
        this.this$0 = inmateChooserPresenter;
    }

    public final List<Inmate> apply(List<Inmate> list) {
        Intrinsics.checkParameterIsNotNull(list, "it");
        Collection arrayList = new ArrayList();
        for (Object next : list) {
            String id = ((Inmate) next).getId();
            Inmate inmate = (Inmate) this.this$0.inmateWrapper.getValue();
            if (!Intrinsics.areEqual((Object) id, (Object) inmate != null ? inmate.getId() : null)) {
                arrayList.add(next);
            }
        }
        return (List) arrayList;
    }
}
