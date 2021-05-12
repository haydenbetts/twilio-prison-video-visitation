package com.forasoft.homewavvisitor.ui.fragment.calls;

import com.twilio.video.RemoteAudioTrack;
import com.twilio.video.RemoteAudioTrackPublication;
import com.twilio.video.RemoteDataTrack;
import com.twilio.video.RemoteDataTrackPublication;
import com.twilio.video.RemoteParticipant;
import com.twilio.video.RemoteVideoTrack;
import com.twilio.video.RemoteVideoTrackPublication;
import com.twilio.video.TwilioException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J \u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0016J \u0010\r\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J \u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0018\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J \u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J \u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J \u0010\u001a\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0018\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u0018\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u0018\u0010\u001f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J \u0010 \u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010!\u001a\u00020\"H\u0016J \u0010#\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010$\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J \u0010%\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010!\u001a\u00020\"H\u0016¨\u0006&"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/calls/SimpleRemoteParticipantListener;", "Lcom/twilio/video/RemoteParticipant$Listener;", "onAudioTrackDisabled", "", "remoteParticipant", "Lcom/twilio/video/RemoteParticipant;", "remoteAudioTrackPublication", "Lcom/twilio/video/RemoteAudioTrackPublication;", "onAudioTrackEnabled", "onAudioTrackPublished", "onAudioTrackSubscribed", "remoteAudioTrack", "Lcom/twilio/video/RemoteAudioTrack;", "onAudioTrackSubscriptionFailed", "twilioException", "Lcom/twilio/video/TwilioException;", "onAudioTrackUnpublished", "onAudioTrackUnsubscribed", "onDataTrackPublished", "remoteDataTrackPublication", "Lcom/twilio/video/RemoteDataTrackPublication;", "onDataTrackSubscribed", "remoteDataTrack", "Lcom/twilio/video/RemoteDataTrack;", "onDataTrackSubscriptionFailed", "onDataTrackUnpublished", "onDataTrackUnsubscribed", "onVideoTrackDisabled", "remoteVideoTrackPublication", "Lcom/twilio/video/RemoteVideoTrackPublication;", "onVideoTrackEnabled", "onVideoTrackPublished", "onVideoTrackSubscribed", "remoteVideoTrack", "Lcom/twilio/video/RemoteVideoTrack;", "onVideoTrackSubscriptionFailed", "onVideoTrackUnpublished", "onVideoTrackUnsubscribed", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: SimpleRemoteParticipantListener.kt */
public interface SimpleRemoteParticipantListener extends RemoteParticipant.Listener {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* compiled from: SimpleRemoteParticipantListener.kt */
    public static final class DefaultImpls {
        public static void onAudioTrackDisabled(SimpleRemoteParticipantListener simpleRemoteParticipantListener, RemoteParticipant remoteParticipant, RemoteAudioTrackPublication remoteAudioTrackPublication) {
            Intrinsics.checkParameterIsNotNull(remoteParticipant, "remoteParticipant");
            Intrinsics.checkParameterIsNotNull(remoteAudioTrackPublication, "remoteAudioTrackPublication");
        }

        public static void onAudioTrackEnabled(SimpleRemoteParticipantListener simpleRemoteParticipantListener, RemoteParticipant remoteParticipant, RemoteAudioTrackPublication remoteAudioTrackPublication) {
            Intrinsics.checkParameterIsNotNull(remoteParticipant, "remoteParticipant");
            Intrinsics.checkParameterIsNotNull(remoteAudioTrackPublication, "remoteAudioTrackPublication");
        }

        public static void onAudioTrackPublished(SimpleRemoteParticipantListener simpleRemoteParticipantListener, RemoteParticipant remoteParticipant, RemoteAudioTrackPublication remoteAudioTrackPublication) {
            Intrinsics.checkParameterIsNotNull(remoteParticipant, "remoteParticipant");
            Intrinsics.checkParameterIsNotNull(remoteAudioTrackPublication, "remoteAudioTrackPublication");
        }

        public static void onAudioTrackSubscribed(SimpleRemoteParticipantListener simpleRemoteParticipantListener, RemoteParticipant remoteParticipant, RemoteAudioTrackPublication remoteAudioTrackPublication, RemoteAudioTrack remoteAudioTrack) {
            Intrinsics.checkParameterIsNotNull(remoteParticipant, "remoteParticipant");
            Intrinsics.checkParameterIsNotNull(remoteAudioTrackPublication, "remoteAudioTrackPublication");
            Intrinsics.checkParameterIsNotNull(remoteAudioTrack, "remoteAudioTrack");
        }

        public static void onAudioTrackSubscriptionFailed(SimpleRemoteParticipantListener simpleRemoteParticipantListener, RemoteParticipant remoteParticipant, RemoteAudioTrackPublication remoteAudioTrackPublication, TwilioException twilioException) {
            Intrinsics.checkParameterIsNotNull(remoteParticipant, "remoteParticipant");
            Intrinsics.checkParameterIsNotNull(remoteAudioTrackPublication, "remoteAudioTrackPublication");
            Intrinsics.checkParameterIsNotNull(twilioException, "twilioException");
        }

        public static void onAudioTrackUnpublished(SimpleRemoteParticipantListener simpleRemoteParticipantListener, RemoteParticipant remoteParticipant, RemoteAudioTrackPublication remoteAudioTrackPublication) {
            Intrinsics.checkParameterIsNotNull(remoteParticipant, "remoteParticipant");
            Intrinsics.checkParameterIsNotNull(remoteAudioTrackPublication, "remoteAudioTrackPublication");
        }

        public static void onAudioTrackUnsubscribed(SimpleRemoteParticipantListener simpleRemoteParticipantListener, RemoteParticipant remoteParticipant, RemoteAudioTrackPublication remoteAudioTrackPublication, RemoteAudioTrack remoteAudioTrack) {
            Intrinsics.checkParameterIsNotNull(remoteParticipant, "remoteParticipant");
            Intrinsics.checkParameterIsNotNull(remoteAudioTrackPublication, "remoteAudioTrackPublication");
            Intrinsics.checkParameterIsNotNull(remoteAudioTrack, "remoteAudioTrack");
        }

        public static void onDataTrackPublished(SimpleRemoteParticipantListener simpleRemoteParticipantListener, RemoteParticipant remoteParticipant, RemoteDataTrackPublication remoteDataTrackPublication) {
            Intrinsics.checkParameterIsNotNull(remoteParticipant, "remoteParticipant");
            Intrinsics.checkParameterIsNotNull(remoteDataTrackPublication, "remoteDataTrackPublication");
        }

        public static void onDataTrackSubscribed(SimpleRemoteParticipantListener simpleRemoteParticipantListener, RemoteParticipant remoteParticipant, RemoteDataTrackPublication remoteDataTrackPublication, RemoteDataTrack remoteDataTrack) {
            Intrinsics.checkParameterIsNotNull(remoteParticipant, "remoteParticipant");
            Intrinsics.checkParameterIsNotNull(remoteDataTrackPublication, "remoteDataTrackPublication");
            Intrinsics.checkParameterIsNotNull(remoteDataTrack, "remoteDataTrack");
        }

        public static void onDataTrackSubscriptionFailed(SimpleRemoteParticipantListener simpleRemoteParticipantListener, RemoteParticipant remoteParticipant, RemoteDataTrackPublication remoteDataTrackPublication, TwilioException twilioException) {
            Intrinsics.checkParameterIsNotNull(remoteParticipant, "remoteParticipant");
            Intrinsics.checkParameterIsNotNull(remoteDataTrackPublication, "remoteDataTrackPublication");
            Intrinsics.checkParameterIsNotNull(twilioException, "twilioException");
        }

        public static void onDataTrackUnpublished(SimpleRemoteParticipantListener simpleRemoteParticipantListener, RemoteParticipant remoteParticipant, RemoteDataTrackPublication remoteDataTrackPublication) {
            Intrinsics.checkParameterIsNotNull(remoteParticipant, "remoteParticipant");
            Intrinsics.checkParameterIsNotNull(remoteDataTrackPublication, "remoteDataTrackPublication");
        }

        public static void onDataTrackUnsubscribed(SimpleRemoteParticipantListener simpleRemoteParticipantListener, RemoteParticipant remoteParticipant, RemoteDataTrackPublication remoteDataTrackPublication, RemoteDataTrack remoteDataTrack) {
            Intrinsics.checkParameterIsNotNull(remoteParticipant, "remoteParticipant");
            Intrinsics.checkParameterIsNotNull(remoteDataTrackPublication, "remoteDataTrackPublication");
            Intrinsics.checkParameterIsNotNull(remoteDataTrack, "remoteDataTrack");
        }

        public static void onVideoTrackDisabled(SimpleRemoteParticipantListener simpleRemoteParticipantListener, RemoteParticipant remoteParticipant, RemoteVideoTrackPublication remoteVideoTrackPublication) {
            Intrinsics.checkParameterIsNotNull(remoteParticipant, "remoteParticipant");
            Intrinsics.checkParameterIsNotNull(remoteVideoTrackPublication, "remoteVideoTrackPublication");
        }

        public static void onVideoTrackEnabled(SimpleRemoteParticipantListener simpleRemoteParticipantListener, RemoteParticipant remoteParticipant, RemoteVideoTrackPublication remoteVideoTrackPublication) {
            Intrinsics.checkParameterIsNotNull(remoteParticipant, "remoteParticipant");
            Intrinsics.checkParameterIsNotNull(remoteVideoTrackPublication, "remoteVideoTrackPublication");
        }

        public static void onVideoTrackPublished(SimpleRemoteParticipantListener simpleRemoteParticipantListener, RemoteParticipant remoteParticipant, RemoteVideoTrackPublication remoteVideoTrackPublication) {
            Intrinsics.checkParameterIsNotNull(remoteParticipant, "remoteParticipant");
            Intrinsics.checkParameterIsNotNull(remoteVideoTrackPublication, "remoteVideoTrackPublication");
        }

        public static void onVideoTrackSubscribed(SimpleRemoteParticipantListener simpleRemoteParticipantListener, RemoteParticipant remoteParticipant, RemoteVideoTrackPublication remoteVideoTrackPublication, RemoteVideoTrack remoteVideoTrack) {
            Intrinsics.checkParameterIsNotNull(remoteParticipant, "remoteParticipant");
            Intrinsics.checkParameterIsNotNull(remoteVideoTrackPublication, "remoteVideoTrackPublication");
            Intrinsics.checkParameterIsNotNull(remoteVideoTrack, "remoteVideoTrack");
        }

        public static void onVideoTrackSubscriptionFailed(SimpleRemoteParticipantListener simpleRemoteParticipantListener, RemoteParticipant remoteParticipant, RemoteVideoTrackPublication remoteVideoTrackPublication, TwilioException twilioException) {
            Intrinsics.checkParameterIsNotNull(remoteParticipant, "remoteParticipant");
            Intrinsics.checkParameterIsNotNull(remoteVideoTrackPublication, "remoteVideoTrackPublication");
            Intrinsics.checkParameterIsNotNull(twilioException, "twilioException");
        }

        public static void onVideoTrackUnpublished(SimpleRemoteParticipantListener simpleRemoteParticipantListener, RemoteParticipant remoteParticipant, RemoteVideoTrackPublication remoteVideoTrackPublication) {
            Intrinsics.checkParameterIsNotNull(remoteParticipant, "remoteParticipant");
            Intrinsics.checkParameterIsNotNull(remoteVideoTrackPublication, "remoteVideoTrackPublication");
        }

        public static void onVideoTrackUnsubscribed(SimpleRemoteParticipantListener simpleRemoteParticipantListener, RemoteParticipant remoteParticipant, RemoteVideoTrackPublication remoteVideoTrackPublication, RemoteVideoTrack remoteVideoTrack) {
            Intrinsics.checkParameterIsNotNull(remoteParticipant, "remoteParticipant");
            Intrinsics.checkParameterIsNotNull(remoteVideoTrackPublication, "remoteVideoTrackPublication");
            Intrinsics.checkParameterIsNotNull(remoteVideoTrack, "remoteVideoTrack");
        }
    }

    void onAudioTrackDisabled(RemoteParticipant remoteParticipant, RemoteAudioTrackPublication remoteAudioTrackPublication);

    void onAudioTrackEnabled(RemoteParticipant remoteParticipant, RemoteAudioTrackPublication remoteAudioTrackPublication);

    void onAudioTrackPublished(RemoteParticipant remoteParticipant, RemoteAudioTrackPublication remoteAudioTrackPublication);

    void onAudioTrackSubscribed(RemoteParticipant remoteParticipant, RemoteAudioTrackPublication remoteAudioTrackPublication, RemoteAudioTrack remoteAudioTrack);

    void onAudioTrackSubscriptionFailed(RemoteParticipant remoteParticipant, RemoteAudioTrackPublication remoteAudioTrackPublication, TwilioException twilioException);

    void onAudioTrackUnpublished(RemoteParticipant remoteParticipant, RemoteAudioTrackPublication remoteAudioTrackPublication);

    void onAudioTrackUnsubscribed(RemoteParticipant remoteParticipant, RemoteAudioTrackPublication remoteAudioTrackPublication, RemoteAudioTrack remoteAudioTrack);

    void onDataTrackPublished(RemoteParticipant remoteParticipant, RemoteDataTrackPublication remoteDataTrackPublication);

    void onDataTrackSubscribed(RemoteParticipant remoteParticipant, RemoteDataTrackPublication remoteDataTrackPublication, RemoteDataTrack remoteDataTrack);

    void onDataTrackSubscriptionFailed(RemoteParticipant remoteParticipant, RemoteDataTrackPublication remoteDataTrackPublication, TwilioException twilioException);

    void onDataTrackUnpublished(RemoteParticipant remoteParticipant, RemoteDataTrackPublication remoteDataTrackPublication);

    void onDataTrackUnsubscribed(RemoteParticipant remoteParticipant, RemoteDataTrackPublication remoteDataTrackPublication, RemoteDataTrack remoteDataTrack);

    void onVideoTrackDisabled(RemoteParticipant remoteParticipant, RemoteVideoTrackPublication remoteVideoTrackPublication);

    void onVideoTrackEnabled(RemoteParticipant remoteParticipant, RemoteVideoTrackPublication remoteVideoTrackPublication);

    void onVideoTrackPublished(RemoteParticipant remoteParticipant, RemoteVideoTrackPublication remoteVideoTrackPublication);

    void onVideoTrackSubscribed(RemoteParticipant remoteParticipant, RemoteVideoTrackPublication remoteVideoTrackPublication, RemoteVideoTrack remoteVideoTrack);

    void onVideoTrackSubscriptionFailed(RemoteParticipant remoteParticipant, RemoteVideoTrackPublication remoteVideoTrackPublication, TwilioException twilioException);

    void onVideoTrackUnpublished(RemoteParticipant remoteParticipant, RemoteVideoTrackPublication remoteVideoTrackPublication);

    void onVideoTrackUnsubscribed(RemoteParticipant remoteParticipant, RemoteVideoTrackPublication remoteVideoTrackPublication, RemoteVideoTrack remoteVideoTrack);
}
