package com.forasoft.homewavvisitor.presentation.view.calls;

import com.forasoft.homewavvisitor.model.liveswitch.CameraLocalMedia;
import com.forasoft.homewavvisitor.model.liveswitch.RemoteMedia;
import com.forasoft.homewavvisitor.presentation.view.calls.CallView;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH&J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH&J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000fH&J\u0010\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0012H&¨\u0006\u0013"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/view/calls/LiveswitchCallView;", "Lcom/forasoft/homewavvisitor/presentation/view/calls/CallView;", "displayInmateVideo", "", "remoteMedia", "Lcom/forasoft/homewavvisitor/model/liveswitch/RemoteMedia;", "displayVisitorVideo", "localMedia", "Lcom/forasoft/homewavvisitor/model/liveswitch/CameraLocalMedia;", "removeInmateVideo", "mediaId", "", "removeVisitorVideo", "showConnectionState", "resId", "", "updateRemainingTime", "remainingTime", "", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: LiveswitchCallView.kt */
public interface LiveswitchCallView extends CallView {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* compiled from: LiveswitchCallView.kt */
    public static final class DefaultImpls {
        public static void showProgress(LiveswitchCallView liveswitchCallView, boolean z) {
            CallView.DefaultImpls.showProgress(liveswitchCallView, z);
        }

        public static void updateNotificationMenu(LiveswitchCallView liveswitchCallView, int i) {
            CallView.DefaultImpls.updateNotificationMenu(liveswitchCallView, i);
        }
    }

    void displayInmateVideo(RemoteMedia remoteMedia);

    void displayVisitorVideo(CameraLocalMedia cameraLocalMedia);

    void removeInmateVideo(String str);

    void removeVisitorVideo(String str);

    void showConnectionState(int i);

    void updateRemainingTime(long j);
}
