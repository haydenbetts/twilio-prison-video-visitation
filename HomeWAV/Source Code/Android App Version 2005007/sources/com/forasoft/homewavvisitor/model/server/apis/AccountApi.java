package com.forasoft.homewavvisitor.model.server.apis;

import com.forasoft.homewavvisitor.model.data.Empty;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.model.data.account.History;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import io.reactivex.Single;
import java.util.List;
import kotlin.Metadata;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u0007H'J*\u0010\b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u00040\u00032\u000e\b\u0001\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\tH'J$\u0010\u000b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\u00040\u00032\b\b\u0001\u0010\f\u001a\u00020\u0007H'J\u0014\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00040\u0003H'¨\u0006\u000f"}, d2 = {"Lcom/forasoft/homewavvisitor/model/server/apis/AccountApi;", "", "changeVisibleToInmateState", "Lio/reactivex/Single;", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "allowCalls", "", "changeVisibleToInmatesState", "", "Lcom/forasoft/homewavvisitor/model/data/Empty;", "deleteInmate", "id", "getHistory", "Lcom/forasoft/homewavvisitor/model/data/account/History;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: AccountApi.kt */
public interface AccountApi {
    @FormUrlEncoded
    @POST("app/toggle-visibility")
    Single<ApiResponse<Inmate>> changeVisibleToInmateState(@Field("inmate_id") String str);

    @FormUrlEncoded
    @POST("app/toggle-visibility-all")
    Single<ApiResponse<List<Empty>>> changeVisibleToInmatesState(@Field("inmate_ids[]") List<String> list);

    @FormUrlEncoded
    @POST("app/delete-inmate")
    Single<ApiResponse<List<Inmate>>> deleteInmate(@Field("inmate_id") String str);

    @GET("app/account-history")
    Single<ApiResponse<History>> getHistory();
}
