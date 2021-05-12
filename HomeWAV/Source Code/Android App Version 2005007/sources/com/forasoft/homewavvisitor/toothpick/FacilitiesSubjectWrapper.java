package com.forasoft.homewavvisitor.toothpick;

import com.forasoft.homewavvisitor.model.data.Facility;
import io.reactivex.subjects.BehaviorSubject;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001d\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/forasoft/homewavvisitor/toothpick/FacilitiesSubjectWrapper;", "", "()V", "subject", "Lio/reactivex/subjects/BehaviorSubject;", "", "Lcom/forasoft/homewavvisitor/model/data/Facility;", "getSubject", "()Lio/reactivex/subjects/BehaviorSubject;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: FacilitiesSubjectWrapper.kt */
public final class FacilitiesSubjectWrapper {
    private final BehaviorSubject<List<Facility>> subject;

    public FacilitiesSubjectWrapper() {
        BehaviorSubject<List<Facility>> create = BehaviorSubject.create();
        Intrinsics.checkExpressionValueIsNotNull(create, "BehaviorSubject.create()");
        this.subject = create;
    }

    public final BehaviorSubject<List<Facility>> getSubject() {
        return this.subject;
    }
}
