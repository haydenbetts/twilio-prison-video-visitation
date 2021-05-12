package com.forasoft.homewavvisitor.presentation.view.calls;

import com.forasoft.homewavvisitor.presentation.view.calls.CallView;
import com.twilio.video.LocalVideoTrack;
import com.twilio.video.RemoteVideoTrack;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\u0012\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0007H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH&¨\u0006\u000b"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/view/calls/TwilioCallView;", "Lcom/forasoft/homewavvisitor/presentation/view/calls/CallView;", "displayInmateVideo", "", "videoTrack", "Lcom/twilio/video/RemoteVideoTrack;", "displayVisitorVideo", "Lcom/twilio/video/LocalVideoTrack;", "updateRemainingTime", "remainingTime", "", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: TwilioCallView.kt */
public interface TwilioCallView extends CallView {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* compiled from: TwilioCallView.kt */
    public static final class DefaultImpls {
        public static void showProgress(TwilioCallView twilioCallView, boolean z) {
            CallView.DefaultImpls.showProgress(twilioCallView, z);
        }

        public static void updateNotificationMenu(TwilioCallView twilioCallView, int i) {
            CallView.DefaultImpls.updateNotificationMenu(twilioCallView, i);
        }
    }

    void displayInmateVideo(RemoteVideoTrack remoteVideoTrack);

    void displayVisitorVideo(LocalVideoTrack localVideoTrack);

    void updateRemainingTime(long j);
}
