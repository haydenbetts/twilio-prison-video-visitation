package com.forasoft.homewavvisitor.presentation.presenter.home;

import com.forasoft.homewavvisitor.model.data.Inmate;
import io.reactivex.functions.Predicate;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "test"}, k = 3, mv = {1, 1, 16})
/* compiled from: HomePresenter.kt */
final class HomePresenter$onFirstViewAttach$2$2<T> implements Predicate<List<? extends Inmate>> {
    public static final HomePresenter$onFirstViewAttach$2$2 INSTANCE = new HomePresenter$onFirstViewAttach$2$2();

    HomePresenter$onFirstViewAttach$2$2() {
    }

    public final boolean test(List<Inmate> list) {
        Intrinsics.checkParameterIsNotNull(list, "it");
        return !list.isEmpty();
    }
}
