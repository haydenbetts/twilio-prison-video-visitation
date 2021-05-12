package com.forasoft.homewavvisitor.presentation.presenter.conversations;

import io.reactivex.functions.Consumer;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import retrofit2.HttpException;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: VideoRecordPresenter.kt */
final class VideoRecordPresenter$onFirstViewAttach$4<T> implements Consumer<Throwable> {
    final /* synthetic */ VideoRecordPresenter this$0;

    VideoRecordPresenter$onFirstViewAttach$4(VideoRecordPresenter videoRecordPresenter) {
        this.this$0 = videoRecordPresenter;
    }

    public final void accept(Throwable th) {
        if (th instanceof HttpException) {
            VideoRecordPresenter videoRecordPresenter = this.this$0;
            String message = ((HttpException) th).message();
            Intrinsics.checkExpressionValueIsNotNull(message, "it.message()");
            videoRecordPresenter.exitWithMessage(message);
        } else if (th instanceof IOException) {
            this.this$0.exitWithMessage("Check your internet connectivity");
        } else {
            this.this$0.exitWithMessage("Internal error");
        }
    }
}
