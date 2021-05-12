package com.forasoft.homewavvisitor.presentation.view.calls;

import com.forasoft.homewavvisitor.presentation.view.calls.CallView;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&¨\u0006\n"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/view/calls/RtmpCallView;", "Lcom/forasoft/homewavvisitor/presentation/view/calls/CallView;", "displayInmateVideo", "", "rtmpUrl", "", "displayVisitorVideo", "updateRemainingTime", "remainingTime", "", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: RtmpCallView.kt */
public interface RtmpCallView extends CallView {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* compiled from: RtmpCallView.kt */
    public static final class DefaultImpls {
        public static void showProgress(RtmpCallView rtmpCallView, boolean z) {
            CallView.DefaultImpls.showProgress(rtmpCallView, z);
        }

        public static void updateNotificationMenu(RtmpCallView rtmpCallView, int i) {
            CallView.DefaultImpls.updateNotificationMenu(rtmpCallView, i);
        }
    }

    void displayInmateVideo(String str);

    void displayVisitorVideo(String str);

    void updateRemainingTime(long j);
}
