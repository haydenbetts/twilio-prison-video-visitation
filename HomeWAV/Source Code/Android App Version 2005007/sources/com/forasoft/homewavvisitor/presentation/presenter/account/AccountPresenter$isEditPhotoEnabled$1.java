package com.forasoft.homewavvisitor.presentation.presenter.account;

import com.forasoft.homewavvisitor.model.data.Facility;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import io.reactivex.functions.Function;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0012\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00010\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "Lcom/forasoft/homewavvisitor/model/data/Facility;", "it", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "apply"}, k = 3, mv = {1, 1, 16})
/* compiled from: AccountPresenter.kt */
final class AccountPresenter$isEditPhotoEnabled$1<T, R> implements Function<T, R> {
    public static final AccountPresenter$isEditPhotoEnabled$1 INSTANCE = new AccountPresenter$isEditPhotoEnabled$1();

    AccountPresenter$isEditPhotoEnabled$1() {
    }

    public final List<Facility> apply(ApiResponse<? extends List<Facility>> apiResponse) {
        Intrinsics.checkParameterIsNotNull(apiResponse, "it");
        Object body = apiResponse.getBody();
        if (body == null) {
            Intrinsics.throwNpe();
        }
        return (List) body;
    }
}
