package com.forasoft.homewavvisitor.ui.fragment.calls;

import com.twilio.video.RemoteParticipant;
import com.twilio.video.Room;
import com.twilio.video.TwilioException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u001a\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\u0018\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0018\u0010\r\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0018\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0012"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/calls/SimpleRoomListener;", "Lcom/twilio/video/Room$Listener;", "onConnectFailure", "", "room", "Lcom/twilio/video/Room;", "twilioException", "Lcom/twilio/video/TwilioException;", "onConnected", "onDisconnected", "onParticipantConnected", "remoteParticipant", "Lcom/twilio/video/RemoteParticipant;", "onParticipantDisconnected", "onReconnected", "onReconnecting", "onRecordingStarted", "onRecordingStopped", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: SimpleRoomListener.kt */
public interface SimpleRoomListener extends Room.Listener {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* compiled from: SimpleRoomListener.kt */
    public static final class DefaultImpls {
        public static void onConnectFailure(SimpleRoomListener simpleRoomListener, Room room, TwilioException twilioException) {
            Intrinsics.checkParameterIsNotNull(room, "room");
            Intrinsics.checkParameterIsNotNull(twilioException, "twilioException");
        }

        public static void onConnected(SimpleRoomListener simpleRoomListener, Room room) {
            Intrinsics.checkParameterIsNotNull(room, "room");
        }

        public static void onDisconnected(SimpleRoomListener simpleRoomListener, Room room, TwilioException twilioException) {
            Intrinsics.checkParameterIsNotNull(room, "room");
        }

        public static void onParticipantConnected(SimpleRoomListener simpleRoomListener, Room room, RemoteParticipant remoteParticipant) {
            Intrinsics.checkParameterIsNotNull(room, "room");
            Intrinsics.checkParameterIsNotNull(remoteParticipant, "remoteParticipant");
        }

        public static void onParticipantDisconnected(SimpleRoomListener simpleRoomListener, Room room, RemoteParticipant remoteParticipant) {
            Intrinsics.checkParameterIsNotNull(room, "room");
            Intrinsics.checkParameterIsNotNull(remoteParticipant, "remoteParticipant");
        }

        public static void onReconnected(SimpleRoomListener simpleRoomListener, Room room) {
            Intrinsics.checkParameterIsNotNull(room, "room");
        }

        public static void onReconnecting(SimpleRoomListener simpleRoomListener, Room room, TwilioException twilioException) {
            Intrinsics.checkParameterIsNotNull(room, "room");
            Intrinsics.checkParameterIsNotNull(twilioException, "twilioException");
        }

        public static void onRecordingStarted(SimpleRoomListener simpleRoomListener, Room room) {
            Intrinsics.checkParameterIsNotNull(room, "room");
        }

        public static void onRecordingStopped(SimpleRoomListener simpleRoomListener, Room room) {
            Intrinsics.checkParameterIsNotNull(room, "room");
        }
    }

    void onConnectFailure(Room room, TwilioException twilioException);

    void onConnected(Room room);

    void onDisconnected(Room room, TwilioException twilioException);

    void onParticipantConnected(Room room, RemoteParticipant remoteParticipant);

    void onParticipantDisconnected(Room room, RemoteParticipant remoteParticipant);

    void onReconnected(Room room);

    void onReconnecting(Room room, TwilioException twilioException);

    void onRecordingStarted(Room room);

    void onRecordingStopped(Room room);
}
