package com.forasoft.homewavvisitor.presentation.presenter.account;

import com.forasoft.homewavvisitor.presentation.view.account.TestVideoView;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "accept", "(Ljava/lang/Long;)V"}, k = 3, mv = {1, 1, 16})
/* compiled from: TestVideoPresenter.kt */
final class TestVideoPresenter$onRecordingStarted$1<T> implements Consumer<Long> {
    final /* synthetic */ TestVideoPresenter this$0;

    TestVideoPresenter$onRecordingStarted$1(TestVideoPresenter testVideoPresenter) {
        this.this$0 = testVideoPresenter;
    }

    public final void accept(Long l) {
        this.this$0.videoLength = (int) l.longValue();
        Intrinsics.checkExpressionValueIsNotNull(l, "it");
        ((TestVideoView) this.this$0.getViewState()).updateRemainingTime((int) (((long) this.this$0.maxLength) - l.longValue()));
    }
}
