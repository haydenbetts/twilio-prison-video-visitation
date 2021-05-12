package com.forasoft.homewavvisitor.presentation.presenter.register;

import com.forasoft.homewavvisitor.model.data.Facility;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.BehaviorSubject;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012&\u0010\u0002\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004 \u0006*\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "response", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "", "Lcom/forasoft/homewavvisitor/model/data/Facility;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: AddConnectionSignUpPresenter.kt */
final class AddConnectionSignUpPresenter$loadFacilities$1<T> implements Consumer<ApiResponse<? extends List<? extends Facility>>> {
    final /* synthetic */ AddConnectionSignUpPresenter this$0;

    AddConnectionSignUpPresenter$loadFacilities$1(AddConnectionSignUpPresenter addConnectionSignUpPresenter) {
        this.this$0 = addConnectionSignUpPresenter;
    }

    public final void accept(ApiResponse<? extends List<Facility>> apiResponse) {
        BehaviorSubject<List<Facility>> subject = this.this$0.facilitiesSubjectWrapper.getSubject();
        Object body = apiResponse.getBody();
        if (body == null) {
            Intrinsics.throwNpe();
        }
        subject.onNext(body);
    }
}
