package com.forasoft.homewavvisitor.presentation.presenter.account;

import com.forasoft.homewavvisitor.model.data.Inmate;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00040\u0001H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "", "it", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "apply"}, k = 3, mv = {1, 1, 16})
/* compiled from: RefundsPresenter.kt */
final class RefundsPresenter$onFirstViewAttach$2<T, R> implements Function<T, R> {
    public static final RefundsPresenter$onFirstViewAttach$2 INSTANCE = new RefundsPresenter$onFirstViewAttach$2();

    RefundsPresenter$onFirstViewAttach$2() {
    }

    public final List<String> apply(List<Inmate> list) {
        Intrinsics.checkParameterIsNotNull(list, "it");
        Iterable<Inmate> iterable = list;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Inmate full_name : iterable) {
            String full_name2 = full_name.getFull_name();
            if (full_name2 == null) {
                Intrinsics.throwNpe();
            }
            arrayList.add(full_name2);
        }
        return (List) arrayList;
    }
}
