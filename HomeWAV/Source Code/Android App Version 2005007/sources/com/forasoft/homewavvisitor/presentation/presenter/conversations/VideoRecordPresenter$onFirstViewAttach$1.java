package com.forasoft.homewavvisitor.presentation.presenter.conversations;

import com.forasoft.homewavvisitor.model.data.MessageInfo;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import io.reactivex.functions.Function;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lcom/forasoft/homewavvisitor/model/data/MessageInfo;", "it", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "apply"}, k = 3, mv = {1, 1, 16})
/* compiled from: VideoRecordPresenter.kt */
final class VideoRecordPresenter$onFirstViewAttach$1<T, R> implements Function<T, R> {
    public static final VideoRecordPresenter$onFirstViewAttach$1 INSTANCE = new VideoRecordPresenter$onFirstViewAttach$1();

    VideoRecordPresenter$onFirstViewAttach$1() {
    }

    public final MessageInfo apply(ApiResponse<MessageInfo> apiResponse) {
        Intrinsics.checkParameterIsNotNull(apiResponse, "it");
        return apiResponse.getBody();
    }
}
