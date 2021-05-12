package com.forasoft.homewavvisitor.presentation.view.account;

import com.forasoft.homewavvisitor.presentation.view.BaseView;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&¨\u0006\u0007"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/view/account/TestVideoView;", "Lcom/forasoft/homewavvisitor/presentation/view/BaseView;", "stopRecording", "", "updateRemainingTime", "remaining", "", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: TestVideoView.kt */
public interface TestVideoView extends BaseView {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* compiled from: TestVideoView.kt */
    public static final class DefaultImpls {
        public static void showProgress(TestVideoView testVideoView, boolean z) {
            BaseView.DefaultImpls.showProgress(testVideoView, z);
        }

        public static void updateNotificationMenu(TestVideoView testVideoView, int i) {
            BaseView.DefaultImpls.updateNotificationMenu(testVideoView, i);
        }
    }

    void stopRecording();

    void updateRemainingTime(int i);
}
