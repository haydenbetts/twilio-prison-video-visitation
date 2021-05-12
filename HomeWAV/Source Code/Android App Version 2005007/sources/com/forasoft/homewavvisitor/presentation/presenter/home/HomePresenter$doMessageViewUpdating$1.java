package com.forasoft.homewavvisitor.presentation.presenter.home;

import com.urbanairship.messagecenter.Inbox;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "onFinished"}, k = 3, mv = {1, 1, 16})
/* compiled from: HomePresenter.kt */
final class HomePresenter$doMessageViewUpdating$1 implements Inbox.FetchMessagesCallback {
    final /* synthetic */ HomePresenter this$0;

    HomePresenter$doMessageViewUpdating$1(HomePresenter homePresenter) {
        this.this$0 = homePresenter;
    }

    public final void onFinished(boolean z) {
        HomePresenter.updateMessageView$default(this.this$0, false, 1, (Object) null);
    }
}
