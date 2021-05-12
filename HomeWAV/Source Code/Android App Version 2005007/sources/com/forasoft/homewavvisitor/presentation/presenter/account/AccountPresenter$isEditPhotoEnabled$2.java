package com.forasoft.homewavvisitor.presentation.presenter.account;

import com.forasoft.homewavvisitor.model.data.Facility;
import com.forasoft.homewavvisitor.model.data.Inmate;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012,\u0010\u0002\u001a(\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u0005 \u0006*\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00040\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00040\u0003H\n¢\u0006\u0002\b\b"}, d2 = {"<anonymous>", "", "<name for destructuring parameter 0>", "Lkotlin/Pair;", "", "Lcom/forasoft/homewavvisitor/model/data/Facility;", "kotlin.jvm.PlatformType", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "apply"}, k = 3, mv = {1, 1, 16})
/* compiled from: AccountPresenter.kt */
final class AccountPresenter$isEditPhotoEnabled$2<T, R> implements Function<T, R> {
    public static final AccountPresenter$isEditPhotoEnabled$2 INSTANCE = new AccountPresenter$isEditPhotoEnabled$2();

    AccountPresenter$isEditPhotoEnabled$2() {
    }

    public /* bridge */ /* synthetic */ Object apply(Object obj) {
        return Boolean.valueOf(apply((Pair<? extends List<Facility>, ? extends List<Inmate>>) (Pair) obj));
    }

    public final boolean apply(Pair<? extends List<Facility>, ? extends List<Inmate>> pair) {
        Object obj;
        boolean z;
        Intrinsics.checkParameterIsNotNull(pair, "<name for destructuring parameter 0>");
        List list = (List) pair.component1();
        Iterable iterable = (List) pair.component2();
        Map linkedHashMap = new LinkedHashMap();
        for (Object next : iterable) {
            String prison_id = ((Inmate) next).getPrison_id();
            Object obj2 = linkedHashMap.get(prison_id);
            if (obj2 == null) {
                obj2 = new ArrayList();
                linkedHashMap.put(prison_id, obj2);
            }
            ((List) obj2).add(next);
        }
        Set keySet = linkedHashMap.keySet();
        Intrinsics.checkExpressionValueIsNotNull(list, "allFacilities");
        Collection arrayList = new ArrayList();
        for (Object next2 : list) {
            if (keySet.contains(((Facility) next2).getId())) {
                arrayList.add(next2);
            }
        }
        Iterable iterable2 = (List) arrayList;
        Iterator it = iterable2.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (Intrinsics.areEqual((Object) ((Facility) obj).getRequire_photo_id(), (Object) "1")) {
                break;
            }
        }
        if (((Facility) obj) == null) {
            return false;
        }
        if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
            Iterator it2 = iterable.iterator();
            while (true) {
                if (it2.hasNext()) {
                    if (!(!((Inmate) it2.next()).isApproved())) {
                        z = false;
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        z = true;
        if (z) {
            return true;
        }
        Collection arrayList2 = new ArrayList();
        for (Object next3 : iterable) {
            if (((Inmate) next3).isApproved()) {
                arrayList2.add(next3);
            }
        }
        Map linkedHashMap2 = new LinkedHashMap();
        for (Object next4 : (List) arrayList2) {
            String prison_id2 = ((Inmate) next4).getPrison_id();
            Object obj3 = linkedHashMap2.get(prison_id2);
            if (obj3 == null) {
                obj3 = new ArrayList();
                linkedHashMap2.put(prison_id2, obj3);
            }
            ((List) obj3).add(next4);
        }
        Set keySet2 = linkedHashMap2.keySet();
        Collection arrayList3 = new ArrayList();
        for (Object next5 : iterable2) {
            if (keySet2.contains(((Facility) next5).getId())) {
                arrayList3.add(next5);
            }
        }
        List list2 = (List) arrayList3;
        if (list2.isEmpty()) {
            return false;
        }
        Iterable<Facility> iterable3 = list2;
        if (!(iterable3 instanceof Collection) || !((Collection) iterable3).isEmpty()) {
            for (Facility photo_lock_after_approval : iterable3) {
                if (!Intrinsics.areEqual((Object) photo_lock_after_approval.getPhoto_lock_after_approval(), (Object) "1")) {
                    return false;
                }
            }
        }
        return true;
    }
}
