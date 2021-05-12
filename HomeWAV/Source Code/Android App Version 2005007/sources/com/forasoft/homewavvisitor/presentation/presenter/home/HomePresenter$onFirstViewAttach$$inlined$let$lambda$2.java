package com.forasoft.homewavvisitor.presentation.presenter.home;

import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.presentation.view.home.HomeView;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006¨\u0006\u0007"}, d2 = {"<anonymous>", "", "it", "", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "kotlin.jvm.PlatformType", "accept", "com/forasoft/homewavvisitor/presentation/presenter/home/HomePresenter$onFirstViewAttach$2$3"}, k = 3, mv = {1, 1, 16})
/* compiled from: HomePresenter.kt */
final class HomePresenter$onFirstViewAttach$$inlined$let$lambda$2<T> implements Consumer<List<? extends Inmate>> {
    final /* synthetic */ HomePresenter this$0;

    HomePresenter$onFirstViewAttach$$inlined$let$lambda$2(HomePresenter homePresenter) {
        this.this$0 = homePresenter;
    }

    public final void accept(List<Inmate> list) {
        Intrinsics.checkExpressionValueIsNotNull(list, "it");
        Collection arrayList = new ArrayList();
        for (Object next : list) {
            if (Intrinsics.areEqual((Object) ((Inmate) next).getInvisible(), (Object) "1")) {
                arrayList.add(next);
            }
        }
        List list2 = (List) arrayList;
        if (!list2.isEmpty()) {
            ((HomeView) this.this$0.getViewState()).showWarningDialog(list2);
        }
    }
}
