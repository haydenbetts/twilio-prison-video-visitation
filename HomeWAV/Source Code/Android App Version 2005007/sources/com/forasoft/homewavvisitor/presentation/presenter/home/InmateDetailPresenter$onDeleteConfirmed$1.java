package com.forasoft.homewavvisitor.presentation.presenter.home;

import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012#\u0010\u0002\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0018\u00010\u0003¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b¢\u0006\u0002\b\t"}, d2 = {"<anonymous>", "", "p1", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "Lkotlin/ParameterName;", "name", "it", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: InmateDetailPresenter.kt */
final /* synthetic */ class InmateDetailPresenter$onDeleteConfirmed$1 extends FunctionReference implements Function1<ApiResponse<? extends List<? extends Inmate>>, Unit> {
    InmateDetailPresenter$onDeleteConfirmed$1(InmateDetailPresenter inmateDetailPresenter) {
        super(1, inmateDetailPresenter);
    }

    public final String getName() {
        return "onDeleteSuccess";
    }

    public final KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(InmateDetailPresenter.class);
    }

    public final String getSignature() {
        return "onDeleteSuccess(Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;)V";
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((ApiResponse<? extends List<Inmate>>) (ApiResponse) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(ApiResponse<? extends List<Inmate>> apiResponse) {
        ((InmateDetailPresenter) this.receiver).onDeleteSuccess(apiResponse);
    }
}
