package com.forasoft.homewavvisitor.model.repository.account;

import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.model.data.account.History;
import com.forasoft.homewavvisitor.model.server.apis.AccountApi;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import io.reactivex.Single;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00062\u0006\u0010\t\u001a\u00020\nJ \u0010\u000b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\f0\u00070\u00062\u0006\u0010\r\u001a\u00020\nJ\u0012\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00070\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/forasoft/homewavvisitor/model/repository/account/AccountRepository;", "", "api", "Lcom/forasoft/homewavvisitor/model/server/apis/AccountApi;", "(Lcom/forasoft/homewavvisitor/model/server/apis/AccountApi;)V", "changeVisibleToInmateState", "Lio/reactivex/Single;", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "inmateId", "", "deleteInmate", "", "id", "getHistory", "Lcom/forasoft/homewavvisitor/model/data/account/History;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: AccountRepository.kt */
public final class AccountRepository {
    private final AccountApi api;

    @Inject
    public AccountRepository(AccountApi accountApi) {
        Intrinsics.checkParameterIsNotNull(accountApi, "api");
        this.api = accountApi;
    }

    public final Single<ApiResponse<History>> getHistory() {
        return this.api.getHistory();
    }

    public final Single<ApiResponse<List<Inmate>>> deleteInmate(String str) {
        Intrinsics.checkParameterIsNotNull(str, "id");
        return this.api.deleteInmate(str);
    }

    public final Single<ApiResponse<Inmate>> changeVisibleToInmateState(String str) {
        Intrinsics.checkParameterIsNotNull(str, "inmateId");
        return this.api.changeVisibleToInmateState(str);
    }
}
