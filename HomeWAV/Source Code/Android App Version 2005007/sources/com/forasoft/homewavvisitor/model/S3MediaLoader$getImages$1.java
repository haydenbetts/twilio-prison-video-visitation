package com.forasoft.homewavvisitor.model;

import com.forasoft.homewavvisitor.model.data.ErrorCause;
import com.forasoft.homewavvisitor.model.data.MediaWithCategory;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a>\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u0003 \u0004*\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00020\u0002 \u0004*\u001e\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u0003 \u0004*\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00020\u0002\u0018\u00010\u00010\u00012\u0012\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00020\u0006H\n¢\u0006\u0002\b\b"}, d2 = {"<anonymous>", "Lio/reactivex/Single;", "", "", "kotlin.jvm.PlatformType", "it", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "Lcom/forasoft/homewavvisitor/model/data/MediaWithCategory;", "apply"}, k = 3, mv = {1, 1, 16})
/* compiled from: S3MediaLoader.kt */
final class S3MediaLoader$getImages$1<T, R> implements Function<T, SingleSource<? extends R>> {
    public static final S3MediaLoader$getImages$1 INSTANCE = new S3MediaLoader$getImages$1();

    S3MediaLoader$getImages$1() {
    }

    public final Single<List<String>> apply(ApiResponse<? extends List<MediaWithCategory>> apiResponse) {
        Intrinsics.checkParameterIsNotNull(apiResponse, "it");
        if (apiResponse.getBody() != null) {
            Collection arrayList = new ArrayList();
            for (MediaWithCategory items : (Iterable) apiResponse.getBody()) {
                CollectionsKt.addAll(arrayList, items.getItems());
            }
            return Single.just((List) arrayList);
        }
        ErrorCause errorCause = apiResponse.getErrorCause();
        return Single.error(new Throwable(errorCause != null ? errorCause.getMessage() : null));
    }
}
