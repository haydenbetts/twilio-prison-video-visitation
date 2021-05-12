package com.forasoft.homewavvisitor.model.interactor.register;

import com.forasoft.homewavvisitor.model.data.ErrorCause;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.model.data.register.InmateShort;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a>\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u0003 \u0004*\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00020\u0002 \u0004*\u001e\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u0003 \u0004*\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00020\u0002\u0018\u00010\u00010\u00012\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0004\b\u0007\u0010\b"}, d2 = {"<anonymous>", "Lio/reactivex/Observable;", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "kotlin.jvm.PlatformType", "it", "", "apply", "(Ljava/lang/Integer;)Lio/reactivex/Observable;"}, k = 3, mv = {1, 1, 16})
/* compiled from: AddConnectionInteractor.kt */
final class AddConnectionInteractor$addConnections$addInmateObservable$1<T, R> implements Function<T, ObservableSource<? extends R>> {
    final /* synthetic */ List $inmates;
    final /* synthetic */ AddConnectionInteractor this$0;

    AddConnectionInteractor$addConnections$addInmateObservable$1(AddConnectionInteractor addConnectionInteractor, List list) {
        this.this$0 = addConnectionInteractor;
        this.$inmates = list;
    }

    public final Observable<ApiResponse<Inmate>> apply(Integer num) {
        Intrinsics.checkParameterIsNotNull(num, "it");
        return this.this$0.registerRepository.addInmate((InmateShort) this.$inmates.get(num.intValue())).flatMap(new Function<T, ObservableSource<? extends R>>(this) {
            final /* synthetic */ AddConnectionInteractor$addConnections$addInmateObservable$1 this$0;

            {
                this.this$0 = r1;
            }

            public final Observable<ApiResponse<Inmate>> apply(ApiResponse<Inmate> apiResponse) {
                String str;
                String message;
                String removePrefix;
                Intrinsics.checkParameterIsNotNull(apiResponse, "it");
                if (apiResponse.getOk()) {
                    this.this$0.this$0.analytics.onAddConnection();
                    return Observable.just(apiResponse);
                }
                ErrorCause errorCause = apiResponse.getErrorCause();
                if (errorCause == null || (message = errorCause.getMessage()) == null || (removePrefix = StringsKt.removePrefix(message, (CharSequence) "\"")) == null || (str = StringsKt.removeSuffix(removePrefix, (CharSequence) "\"")) == null) {
                    str = "";
                }
                return Observable.error(new Throwable(str));
            }
        });
    }
}
