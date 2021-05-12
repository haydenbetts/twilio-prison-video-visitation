package com.forasoft.homewavvisitor.presentation.presenter.conversations;

import com.forasoft.homewavvisitor.presentation.view.conversations.VideoRecordView;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "accept", "(Ljava/lang/Long;)V"}, k = 3, mv = {1, 1, 16})
/* compiled from: VideoRecordPresenter.kt */
final class VideoRecordPresenter$onRecordingStarted$1<T> implements Consumer<Long> {
    final /* synthetic */ VideoRecordPresenter this$0;

    VideoRecordPresenter$onRecordingStarted$1(VideoRecordPresenter videoRecordPresenter) {
        this.this$0 = videoRecordPresenter;
    }

    public final void accept(Long l) {
        this.this$0.videoLength = (int) l.longValue();
        VideoRecordView videoRecordView = (VideoRecordView) this.this$0.getViewState();
        Integer access$getMaxLength$p = this.this$0.maxLength;
        if (access$getMaxLength$p == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(l, "it");
        videoRecordView.updateRemainingTime((int) (((long) access$getMaxLength$p.intValue()) - l.longValue()));
    }
}
