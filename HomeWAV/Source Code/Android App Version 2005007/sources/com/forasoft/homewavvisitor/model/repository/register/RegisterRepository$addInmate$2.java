package com.forasoft.homewavvisitor.model.repository.register;

import com.forasoft.homewavvisitor.dao.InmateDao;
import com.forasoft.homewavvisitor.model.data.ErrorCause;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001aB\u0012\u001a\b\u0001\u0012\u0016\u0012\u0004\u0012\u00020\u0003 \u0004*\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00020\u0002 \u0004* \u0012\u001a\b\u0001\u0012\u0016\u0012\u0004\u0012\u00020\u0003 \u0004*\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00020\u0002\u0018\u00010\u00010\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "Lio/reactivex/Observable;", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "kotlin.jvm.PlatformType", "response", "apply"}, k = 3, mv = {1, 1, 16})
/* compiled from: RegisterRepository.kt */
final class RegisterRepository$addInmate$2<T, R> implements Function<T, ObservableSource<? extends R>> {
    final /* synthetic */ RegisterRepository this$0;

    RegisterRepository$addInmate$2(RegisterRepository registerRepository) {
        this.this$0 = registerRepository;
    }

    public final Observable<? extends ApiResponse<Inmate>> apply(final ApiResponse<Inmate> apiResponse) {
        Intrinsics.checkParameterIsNotNull(apiResponse, "response");
        if (apiResponse.getOk()) {
            return Observable.fromCallable(new Callable<T>(this) {
                final /* synthetic */ RegisterRepository$addInmate$2 this$0;

                {
                    this.this$0 = r1;
                }

                public final void call() {
                    InmateDao access$getInmateDao$p = this.this$0.this$0.inmateDao;
                    Object body = apiResponse.getBody();
                    if (body == null) {
                        Intrinsics.throwNpe();
                    }
                    access$getInmateDao$p.saveInmate((Inmate) body);
                }
            }).flatMap(new Function<T, ObservableSource<? extends R>>() {
                public final Observable<ApiResponse<Inmate>> apply(Unit unit) {
                    Intrinsics.checkParameterIsNotNull(unit, "it");
                    return Observable.just(new ApiResponse(true, (ErrorCause) null, apiResponse.getBody()));
                }
            });
        }
        return Observable.just(new ApiResponse(false, apiResponse.getErrorCause(), null));
    }
}
