package org.apache.cordova.twiliovideo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.twilio.video.CameraCapturer;
import com.twilio.video.ConnectOptions;
import com.twilio.video.LocalAudioTrack;
import com.twilio.video.LocalParticipant;
import com.twilio.video.LocalVideoTrack;
import com.twilio.video.NetworkQualityLevel;
import com.twilio.video.RemoteAudioTrack;
import com.twilio.video.RemoteAudioTrackPublication;
import com.twilio.video.RemoteDataTrack;
import com.twilio.video.RemoteDataTrackPublication;
import com.twilio.video.RemoteParticipant;
import com.twilio.video.RemoteVideoTrack;
import com.twilio.video.RemoteVideoTrackPublication;
import com.twilio.video.Room;
import com.twilio.video.TwilioException;
import com.twilio.video.Video;
import com.twilio.video.VideoRenderer;
import com.twilio.video.VideoTrack;
import com.twilio.video.VideoView;
import java.util.Collections;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import tvi.webrtc.MediaStreamTrack;

public class TwilioVideoActivity extends AppCompatActivity implements CallActionObserver {
    /* access modifiers changed from: private */
    public static FakeR FAKE_R = null;
    private static final String LOCAL_AUDIO_TRACK_NAME = "microphone";
    private static final String LOCAL_VIDEO_TRACK_NAME = "camera";
    private static final int PERMISSIONS_REQUEST_CODE = 1;
    private String accessToken;
    /* access modifiers changed from: private */
    public AudioManager audioManager;
    /* access modifiers changed from: private */
    public CameraCapturerCompat cameraCapturer;
    /* access modifiers changed from: private */
    public CallConfig config;
    private FloatingActionButton connectActionFab;
    /* access modifiers changed from: private */
    public boolean disconnectedFromOnDestroy;
    /* access modifiers changed from: private */
    public LocalAudioTrack localAudioTrack;
    /* access modifiers changed from: private */
    public LocalParticipant localParticipant;
    /* access modifiers changed from: private */
    public FloatingActionButton localVideoActionFab;
    /* access modifiers changed from: private */
    public LocalVideoTrack localVideoTrack;
    private VideoRenderer localVideoView;
    /* access modifiers changed from: private */
    public FloatingActionButton muteActionFab;
    private String participantIdentity;
    private int previousAudioMode;
    private boolean previousMicrophoneMute;
    /* access modifiers changed from: private */
    public VideoView primaryVideoView;
    /* access modifiers changed from: private */
    public Room room;
    private String roomId;
    /* access modifiers changed from: private */
    public FloatingActionButton switchAudioActionFab;
    /* access modifiers changed from: private */
    public FloatingActionButton switchCameraActionFab;
    /* access modifiers changed from: private */
    public VideoView thumbnailVideoView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        TwilioVideoManager.getInstance().setActionListenerObserver(this);
        FAKE_R = new FakeR(this);
        publishEvent(CallEvent.OPENED);
        setContentView(FAKE_R.getLayout("activity_video"));
        this.primaryVideoView = (VideoView) findViewById(FAKE_R.getId("primary_video_view"));
        this.thumbnailVideoView = (VideoView) findViewById(FAKE_R.getId("thumbnail_video_view"));
        this.connectActionFab = (FloatingActionButton) findViewById(FAKE_R.getId("connect_action_fab"));
        this.switchCameraActionFab = (FloatingActionButton) findViewById(FAKE_R.getId("switch_camera_action_fab"));
        this.localVideoActionFab = (FloatingActionButton) findViewById(FAKE_R.getId("local_video_action_fab"));
        this.muteActionFab = (FloatingActionButton) findViewById(FAKE_R.getId("mute_action_fab"));
        this.switchAudioActionFab = (FloatingActionButton) findViewById(FAKE_R.getId("switch_audio_action_fab"));
        setVolumeControlStream(0);
        this.audioManager = (AudioManager) getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);
        this.audioManager.setSpeakerphoneOn(true);
        Intent intent = getIntent();
        this.accessToken = intent.getStringExtra("token");
        this.roomId = intent.getStringExtra("roomId");
        this.config = (CallConfig) intent.getSerializableExtra("config");
        Log.d(TwilioVideo.TAG, "BEFORE REQUEST PERMISSIONS");
        if (!hasPermissionForCameraAndMicrophone()) {
            Log.d(TwilioVideo.TAG, "REQUEST PERMISSIONS");
            requestPermissions();
        } else {
            Log.d(TwilioVideo.TAG, "PERMISSIONS OK. CREATE LOCAL MEDIA");
            createAudioAndVideoTracks();
            connectToRoom();
        }
        initializeUI();
    }

    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        if (i == 1) {
            int length = iArr.length;
            boolean z = true;
            for (int i2 = 0; i2 < length; i2++) {
                z &= iArr[i2] == 0;
            }
            if (z) {
                createAudioAndVideoTracks();
                connectToRoom();
                return;
            }
            publishEvent(CallEvent.PERMISSIONS_REQUIRED);
            handleConnectionError(this.config.getI18nConnectionError());
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.localVideoTrack == null && hasPermissionForCameraAndMicrophone()) {
            this.localVideoTrack = LocalVideoTrack.create((Context) this, true, this.cameraCapturer.getVideoCapturer(), LOCAL_VIDEO_TRACK_NAME);
            this.localVideoTrack.addRenderer(this.thumbnailVideoView);
            LocalParticipant localParticipant2 = this.localParticipant;
            if (localParticipant2 != null) {
                localParticipant2.publishTrack(this.localVideoTrack);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        LocalVideoTrack localVideoTrack2 = this.localVideoTrack;
        if (localVideoTrack2 != null) {
            LocalParticipant localParticipant2 = this.localParticipant;
            if (localParticipant2 != null) {
                localParticipant2.unpublishTrack(localVideoTrack2);
            }
            this.localVideoTrack.release();
            this.localVideoTrack = null;
        }
        super.onPause();
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, 0);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        Room room2 = this.room;
        if (!(room2 == null || room2.getState() == Room.State.DISCONNECTED)) {
            this.room.disconnect();
            this.disconnectedFromOnDestroy = true;
        }
        LocalAudioTrack localAudioTrack2 = this.localAudioTrack;
        if (localAudioTrack2 != null) {
            localAudioTrack2.release();
            this.localAudioTrack = null;
        }
        LocalVideoTrack localVideoTrack2 = this.localVideoTrack;
        if (localVideoTrack2 != null) {
            localVideoTrack2.release();
            this.localVideoTrack = null;
        }
        publishEvent(CallEvent.CLOSED);
        TwilioVideoManager.getInstance().setActionListenerObserver((CallActionObserver) null);
        super.onDestroy();
    }

    private boolean hasPermissionForCameraAndMicrophone() {
        return ContextCompat.checkSelfPermission(this, "android.permission.CAMERA") == 0 && ContextCompat.checkSelfPermission(this, "android.permission.RECORD_AUDIO") == 0;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(this, TwilioVideo.PERMISSIONS_REQUIRED, 1);
    }

    private void createAudioAndVideoTracks() {
        this.localAudioTrack = LocalAudioTrack.create((Context) this, true, LOCAL_AUDIO_TRACK_NAME);
        this.cameraCapturer = new CameraCapturerCompat(this, getAvailableCameraSource());
        this.localVideoTrack = LocalVideoTrack.create((Context) this, true, this.cameraCapturer.getVideoCapturer(), LOCAL_VIDEO_TRACK_NAME);
        moveLocalVideoToThumbnailView();
    }

    private CameraCapturer.CameraSource getAvailableCameraSource() {
        return CameraCapturer.isSourceAvailable(CameraCapturer.CameraSource.FRONT_CAMERA) ? CameraCapturer.CameraSource.FRONT_CAMERA : CameraCapturer.CameraSource.BACK_CAMERA;
    }

    private void connectToRoom() {
        configureAudio(true);
        ConnectOptions.Builder roomName = new ConnectOptions.Builder(this.accessToken).roomName(this.roomId);
        LocalAudioTrack localAudioTrack2 = this.localAudioTrack;
        if (localAudioTrack2 != null) {
            roomName.audioTracks(Collections.singletonList(localAudioTrack2));
        }
        LocalVideoTrack localVideoTrack2 = this.localVideoTrack;
        if (localVideoTrack2 != null) {
            roomName.videoTracks(Collections.singletonList(localVideoTrack2));
        }
        this.room = Video.connect(this, roomName.build(), roomListener());
    }

    private void initializeUI() {
        setDisconnectAction();
        if (this.config.getPrimaryColorHex() != null) {
            this.connectActionFab.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(this.config.getPrimaryColorHex())));
        }
        if (this.config.getSecondaryColorHex() != null) {
            ColorStateList valueOf = ColorStateList.valueOf(Color.parseColor(this.config.getSecondaryColorHex()));
            this.switchCameraActionFab.setBackgroundTintList(valueOf);
            this.localVideoActionFab.setBackgroundTintList(valueOf);
            this.muteActionFab.setBackgroundTintList(valueOf);
            this.switchAudioActionFab.setBackgroundTintList(valueOf);
        }
        this.switchCameraActionFab.show();
        this.switchCameraActionFab.setOnClickListener(switchCameraClickListener());
        this.localVideoActionFab.show();
        this.localVideoActionFab.setOnClickListener(localVideoClickListener());
        this.muteActionFab.show();
        this.muteActionFab.setOnClickListener(muteClickListener());
        this.switchAudioActionFab.show();
        this.switchAudioActionFab.setOnClickListener(switchAudioClickListener());
    }

    private void setDisconnectAction() {
        this.connectActionFab.setImageDrawable(ContextCompat.getDrawable(this, FAKE_R.getDrawable("ic_call_end_white_24px")));
        this.connectActionFab.show();
        this.connectActionFab.setOnClickListener(disconnectClickListener());
    }

    /* access modifiers changed from: private */
    public void addRemoteParticipant(RemoteParticipant remoteParticipant) {
        this.participantIdentity = remoteParticipant.getIdentity();
        if (remoteParticipant.getRemoteVideoTracks().size() > 0) {
            RemoteVideoTrackPublication remoteVideoTrackPublication = remoteParticipant.getRemoteVideoTracks().get(0);
            if (remoteVideoTrackPublication.isTrackSubscribed()) {
                addRemoteParticipantVideo(remoteVideoTrackPublication.getRemoteVideoTrack());
            }
        }
        remoteParticipant.setListener(remoteParticipantListener());
    }

    /* access modifiers changed from: private */
    public void addRemoteParticipantVideo(VideoTrack videoTrack) {
        this.primaryVideoView.setVisibility(0);
        this.primaryVideoView.setMirror(false);
        videoTrack.addRenderer(this.primaryVideoView);
    }

    private void moveLocalVideoToThumbnailView() {
        VideoView videoView;
        if (this.thumbnailVideoView.getVisibility() == 8) {
            boolean z = false;
            this.thumbnailVideoView.setVisibility(0);
            LocalVideoTrack localVideoTrack2 = this.localVideoTrack;
            if (localVideoTrack2 != null) {
                localVideoTrack2.removeRenderer(this.primaryVideoView);
                this.localVideoTrack.addRenderer(this.thumbnailVideoView);
            }
            if (!(this.localVideoView == null || (videoView = this.thumbnailVideoView) == null)) {
                this.localVideoView = videoView;
            }
            VideoView videoView2 = this.thumbnailVideoView;
            if (this.cameraCapturer.getCameraSource() == CameraCapturer.CameraSource.FRONT_CAMERA) {
                z = true;
            }
            videoView2.setMirror(z);
        }
    }

    /* access modifiers changed from: private */
    public void removeRemoteParticipant(RemoteParticipant remoteParticipant) {
        if (remoteParticipant.getIdentity().equals(this.participantIdentity) && remoteParticipant.getRemoteVideoTracks().size() > 0) {
            RemoteVideoTrackPublication remoteVideoTrackPublication = remoteParticipant.getRemoteVideoTracks().get(0);
            if (remoteVideoTrackPublication.isTrackSubscribed()) {
                removeParticipantVideo(remoteVideoTrackPublication.getRemoteVideoTrack());
            }
        }
    }

    /* access modifiers changed from: private */
    public void removeParticipantVideo(VideoTrack videoTrack) {
        this.primaryVideoView.setVisibility(8);
        videoTrack.removeRenderer(this.primaryVideoView);
    }

    private Room.Listener roomListener() {
        return new Room.Listener() {
            public /* synthetic */ void onDominantSpeakerChanged(@NonNull Room room, @Nullable RemoteParticipant remoteParticipant) {
                Room.Listener.CC.$default$onDominantSpeakerChanged(this, room, remoteParticipant);
            }

            public void onConnected(Room room) {
                LocalParticipant unused = TwilioVideoActivity.this.localParticipant = room.getLocalParticipant();
                TwilioVideoActivity.this.publishEvent(CallEvent.CONNECTED);
                List<RemoteParticipant> remoteParticipants = room.getRemoteParticipants();
                if (remoteParticipants != null && !remoteParticipants.isEmpty()) {
                    TwilioVideoActivity.this.addRemoteParticipant(remoteParticipants.get(0));
                }
            }

            public void onConnectFailure(Room room, TwilioException twilioException) {
                TwilioVideoActivity.this.publishEvent(CallEvent.CONNECT_FAILURE);
                TwilioVideoActivity twilioVideoActivity = TwilioVideoActivity.this;
                twilioVideoActivity.handleConnectionError(twilioVideoActivity.config.getI18nConnectionError());
            }

            public void onReconnecting(@NonNull Room room, @NonNull TwilioException twilioException) {
                TwilioVideoActivity.this.publishEvent(CallEvent.RECONNECTING);
            }

            public void onReconnected(@NonNull Room room) {
                TwilioVideoActivity.this.publishEvent(CallEvent.RECONNECTED);
            }

            public void onDisconnected(Room room, TwilioException twilioException) {
                JSONObject jSONObject;
                LocalParticipant unused = TwilioVideoActivity.this.localParticipant = null;
                Room unused2 = TwilioVideoActivity.this.room = null;
                if (TwilioVideoActivity.this.disconnectedFromOnDestroy || twilioException == null) {
                    TwilioVideoActivity.this.publishEvent(CallEvent.DISCONNECTED);
                    return;
                }
                try {
                    jSONObject = new JSONObject();
                    try {
                        jSONObject.put("code", String.valueOf(twilioException.getCode()));
                        jSONObject.put("description", twilioException.getExplanation());
                    } catch (JSONException unused3) {
                    }
                } catch (JSONException unused4) {
                    jSONObject = null;
                    Log.e(TwilioVideo.TAG, "onDisconnected. Error sending error data");
                    TwilioVideoActivity.this.publishEvent(CallEvent.DISCONNECTED_WITH_ERROR, jSONObject);
                    TwilioVideoActivity twilioVideoActivity = TwilioVideoActivity.this;
                    twilioVideoActivity.handleConnectionError(twilioVideoActivity.config.getI18nDisconnectedWithError());
                }
                TwilioVideoActivity.this.publishEvent(CallEvent.DISCONNECTED_WITH_ERROR, jSONObject);
                TwilioVideoActivity twilioVideoActivity2 = TwilioVideoActivity.this;
                twilioVideoActivity2.handleConnectionError(twilioVideoActivity2.config.getI18nDisconnectedWithError());
            }

            public void onParticipantConnected(Room room, RemoteParticipant remoteParticipant) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("identity", remoteParticipant.getIdentity());
                } catch (Exception e) {
                    Log.e("Twilio", e.getMessage());
                }
                TwilioVideoActivity.this.publishEvent(CallEvent.PARTICIPANT_CONNECTED, jSONObject);
                TwilioVideoActivity.this.addRemoteParticipant(remoteParticipant);
            }

            public void onParticipantDisconnected(Room room, RemoteParticipant remoteParticipant) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("identity", remoteParticipant.getIdentity());
                } catch (Exception e) {
                    Log.e("Twilio", e.getMessage());
                }
                TwilioVideoActivity.this.publishEvent(CallEvent.PARTICIPANT_DISCONNECTED, jSONObject);
                TwilioVideoActivity.this.removeRemoteParticipant(remoteParticipant);
            }

            public void onRecordingStarted(Room room) {
                Log.d(TwilioVideo.TAG, "onRecordingStarted");
            }

            public void onRecordingStopped(Room room) {
                Log.d(TwilioVideo.TAG, "onRecordingStopped");
            }
        };
    }

    private RemoteParticipant.Listener remoteParticipantListener() {
        return new RemoteParticipant.Listener() {
            public void onAudioTrackDisabled(RemoteParticipant remoteParticipant, RemoteAudioTrackPublication remoteAudioTrackPublication) {
            }

            public void onAudioTrackEnabled(RemoteParticipant remoteParticipant, RemoteAudioTrackPublication remoteAudioTrackPublication) {
            }

            public /* synthetic */ void onNetworkQualityLevelChanged(@NonNull RemoteParticipant remoteParticipant, @NonNull NetworkQualityLevel networkQualityLevel) {
                RemoteParticipant.Listener.CC.$default$onNetworkQualityLevelChanged(this, remoteParticipant, networkQualityLevel);
            }

            public void onVideoTrackDisabled(RemoteParticipant remoteParticipant, RemoteVideoTrackPublication remoteVideoTrackPublication) {
            }

            public void onVideoTrackEnabled(RemoteParticipant remoteParticipant, RemoteVideoTrackPublication remoteVideoTrackPublication) {
            }

            public void onAudioTrackPublished(RemoteParticipant remoteParticipant, RemoteAudioTrackPublication remoteAudioTrackPublication) {
                Log.i(TwilioVideo.TAG, String.format("onAudioTrackPublished: [RemoteParticipant: identity=%s], [RemoteAudioTrackPublication: sid=%s, enabled=%b, subscribed=%b, name=%s]", new Object[]{remoteParticipant.getIdentity(), remoteAudioTrackPublication.getTrackSid(), Boolean.valueOf(remoteAudioTrackPublication.isTrackEnabled()), Boolean.valueOf(remoteAudioTrackPublication.isTrackSubscribed()), remoteAudioTrackPublication.getTrackName()}));
            }

            public void onAudioTrackUnpublished(RemoteParticipant remoteParticipant, RemoteAudioTrackPublication remoteAudioTrackPublication) {
                Log.i(TwilioVideo.TAG, String.format("onAudioTrackUnpublished: [RemoteParticipant: identity=%s], [RemoteAudioTrackPublication: sid=%s, enabled=%b, subscribed=%b, name=%s]", new Object[]{remoteParticipant.getIdentity(), remoteAudioTrackPublication.getTrackSid(), Boolean.valueOf(remoteAudioTrackPublication.isTrackEnabled()), Boolean.valueOf(remoteAudioTrackPublication.isTrackSubscribed()), remoteAudioTrackPublication.getTrackName()}));
            }

            public void onAudioTrackSubscribed(RemoteParticipant remoteParticipant, RemoteAudioTrackPublication remoteAudioTrackPublication, RemoteAudioTrack remoteAudioTrack) {
                Log.i(TwilioVideo.TAG, String.format("onAudioTrackSubscribed: [RemoteParticipant: identity=%s], [RemoteAudioTrack: enabled=%b, playbackEnabled=%b, name=%s]", new Object[]{remoteParticipant.getIdentity(), Boolean.valueOf(remoteAudioTrack.isEnabled()), Boolean.valueOf(remoteAudioTrack.isPlaybackEnabled()), remoteAudioTrack.getName()}));
                TwilioVideoActivity.this.publishEvent(CallEvent.AUDIO_TRACK_ADDED);
            }

            public void onAudioTrackSubscriptionFailed(RemoteParticipant remoteParticipant, RemoteAudioTrackPublication remoteAudioTrackPublication, TwilioException twilioException) {
                Log.i(TwilioVideo.TAG, String.format("onAudioTrackSubscriptionFailed: [RemoteParticipant: identity=%s], [RemoteAudioTrackPublication: sid=%b, name=%s][TwilioException: code=%d, message=%s]", new Object[]{remoteParticipant.getIdentity(), remoteAudioTrackPublication.getTrackSid(), remoteAudioTrackPublication.getTrackName(), Integer.valueOf(twilioException.getCode()), twilioException.getMessage()}));
            }

            public void onAudioTrackUnsubscribed(RemoteParticipant remoteParticipant, RemoteAudioTrackPublication remoteAudioTrackPublication, RemoteAudioTrack remoteAudioTrack) {
                Log.i(TwilioVideo.TAG, String.format("onAudioTrackUnsubscribed: [RemoteParticipant: identity=%s], [RemoteAudioTrack: enabled=%b, playbackEnabled=%b, name=%s]", new Object[]{remoteParticipant.getIdentity(), Boolean.valueOf(remoteAudioTrack.isEnabled()), Boolean.valueOf(remoteAudioTrack.isPlaybackEnabled()), remoteAudioTrack.getName()}));
                TwilioVideoActivity.this.publishEvent(CallEvent.AUDIO_TRACK_REMOVED);
            }

            public void onVideoTrackPublished(RemoteParticipant remoteParticipant, RemoteVideoTrackPublication remoteVideoTrackPublication) {
                Log.i(TwilioVideo.TAG, String.format("onVideoTrackPublished: [RemoteParticipant: identity=%s], [RemoteVideoTrackPublication: sid=%s, enabled=%b, subscribed=%b, name=%s]", new Object[]{remoteParticipant.getIdentity(), remoteVideoTrackPublication.getTrackSid(), Boolean.valueOf(remoteVideoTrackPublication.isTrackEnabled()), Boolean.valueOf(remoteVideoTrackPublication.isTrackSubscribed()), remoteVideoTrackPublication.getTrackName()}));
            }

            public void onVideoTrackUnpublished(RemoteParticipant remoteParticipant, RemoteVideoTrackPublication remoteVideoTrackPublication) {
                Log.i(TwilioVideo.TAG, String.format("onVideoTrackUnpublished: [RemoteParticipant: identity=%s], [RemoteVideoTrackPublication: sid=%s, enabled=%b, subscribed=%b, name=%s]", new Object[]{remoteParticipant.getIdentity(), remoteVideoTrackPublication.getTrackSid(), Boolean.valueOf(remoteVideoTrackPublication.isTrackEnabled()), Boolean.valueOf(remoteVideoTrackPublication.isTrackSubscribed()), remoteVideoTrackPublication.getTrackName()}));
            }

            public void onVideoTrackSubscribed(RemoteParticipant remoteParticipant, RemoteVideoTrackPublication remoteVideoTrackPublication, RemoteVideoTrack remoteVideoTrack) {
                Log.i(TwilioVideo.TAG, String.format("onVideoTrackSubscribed: [RemoteParticipant: identity=%s], [RemoteVideoTrack: enabled=%b, name=%s]", new Object[]{remoteParticipant.getIdentity(), Boolean.valueOf(remoteVideoTrack.isEnabled()), remoteVideoTrack.getName()}));
                TwilioVideoActivity.this.publishEvent(CallEvent.VIDEO_TRACK_ADDED);
                TwilioVideoActivity.this.addRemoteParticipantVideo(remoteVideoTrack);
            }

            public void onVideoTrackSubscriptionFailed(RemoteParticipant remoteParticipant, RemoteVideoTrackPublication remoteVideoTrackPublication, TwilioException twilioException) {
                Log.i(TwilioVideo.TAG, String.format("onVideoTrackSubscriptionFailed: [RemoteParticipant: identity=%s], [RemoteVideoTrackPublication: sid=%b, name=%s][TwilioException: code=%d, message=%s]", new Object[]{remoteParticipant.getIdentity(), remoteVideoTrackPublication.getTrackSid(), remoteVideoTrackPublication.getTrackName(), Integer.valueOf(twilioException.getCode()), twilioException.getMessage()}));
            }

            public void onVideoTrackUnsubscribed(RemoteParticipant remoteParticipant, RemoteVideoTrackPublication remoteVideoTrackPublication, RemoteVideoTrack remoteVideoTrack) {
                Log.i(TwilioVideo.TAG, String.format("onVideoTrackUnsubscribed: [RemoteParticipant: identity=%s], [RemoteVideoTrack: enabled=%b, name=%s]", new Object[]{remoteParticipant.getIdentity(), Boolean.valueOf(remoteVideoTrack.isEnabled()), remoteVideoTrack.getName()}));
                TwilioVideoActivity.this.publishEvent(CallEvent.VIDEO_TRACK_REMOVED);
                TwilioVideoActivity.this.removeParticipantVideo(remoteVideoTrack);
            }

            public void onDataTrackPublished(RemoteParticipant remoteParticipant, RemoteDataTrackPublication remoteDataTrackPublication) {
                Log.i(TwilioVideo.TAG, String.format("onDataTrackPublished: [RemoteParticipant: identity=%s], [RemoteDataTrackPublication: sid=%s, enabled=%b, subscribed=%b, name=%s]", new Object[]{remoteParticipant.getIdentity(), remoteDataTrackPublication.getTrackSid(), Boolean.valueOf(remoteDataTrackPublication.isTrackEnabled()), Boolean.valueOf(remoteDataTrackPublication.isTrackSubscribed()), remoteDataTrackPublication.getTrackName()}));
            }

            public void onDataTrackUnpublished(RemoteParticipant remoteParticipant, RemoteDataTrackPublication remoteDataTrackPublication) {
                Log.i(TwilioVideo.TAG, String.format("onDataTrackUnpublished: [RemoteParticipant: identity=%s], [RemoteDataTrackPublication: sid=%s, enabled=%b, subscribed=%b, name=%s]", new Object[]{remoteParticipant.getIdentity(), remoteDataTrackPublication.getTrackSid(), Boolean.valueOf(remoteDataTrackPublication.isTrackEnabled()), Boolean.valueOf(remoteDataTrackPublication.isTrackSubscribed()), remoteDataTrackPublication.getTrackName()}));
            }

            public void onDataTrackSubscribed(RemoteParticipant remoteParticipant, RemoteDataTrackPublication remoteDataTrackPublication, RemoteDataTrack remoteDataTrack) {
                Log.i(TwilioVideo.TAG, String.format("onDataTrackSubscribed: [RemoteParticipant: identity=%s], [RemoteDataTrack: enabled=%b, name=%s]", new Object[]{remoteParticipant.getIdentity(), Boolean.valueOf(remoteDataTrack.isEnabled()), remoteDataTrack.getName()}));
            }

            public void onDataTrackSubscriptionFailed(RemoteParticipant remoteParticipant, RemoteDataTrackPublication remoteDataTrackPublication, TwilioException twilioException) {
                Log.i(TwilioVideo.TAG, String.format("onDataTrackSubscriptionFailed: [RemoteParticipant: identity=%s], [RemoteDataTrackPublication: sid=%b, name=%s][TwilioException: code=%d, message=%s]", new Object[]{remoteParticipant.getIdentity(), remoteDataTrackPublication.getTrackSid(), remoteDataTrackPublication.getTrackName(), Integer.valueOf(twilioException.getCode()), twilioException.getMessage()}));
            }

            public void onDataTrackUnsubscribed(RemoteParticipant remoteParticipant, RemoteDataTrackPublication remoteDataTrackPublication, RemoteDataTrack remoteDataTrack) {
                Log.i(TwilioVideo.TAG, String.format("onDataTrackUnsubscribed: [RemoteParticipant: identity=%s], [RemoteDataTrack: enabled=%b, name=%s]", new Object[]{remoteParticipant.getIdentity(), Boolean.valueOf(remoteDataTrack.isEnabled()), remoteDataTrack.getName()}));
            }
        };
    }

    private View.OnClickListener disconnectClickListener() {
        return new View.OnClickListener() {
            public void onClick(View view) {
                if (TwilioVideoActivity.this.config.isHangUpInApp()) {
                    TwilioVideoActivity.this.publishEvent(CallEvent.HANG_UP);
                } else {
                    TwilioVideoActivity.this.onDisconnect();
                }
            }
        };
    }

    private View.OnClickListener switchCameraClickListener() {
        return new View.OnClickListener() {
            public void onClick(View view) {
                if (TwilioVideoActivity.this.cameraCapturer != null) {
                    CameraCapturer.CameraSource cameraSource = TwilioVideoActivity.this.cameraCapturer.getCameraSource();
                    TwilioVideoActivity.this.cameraCapturer.switchCamera();
                    boolean z = true;
                    if (TwilioVideoActivity.this.thumbnailVideoView.getVisibility() == 0) {
                        VideoView access$1200 = TwilioVideoActivity.this.thumbnailVideoView;
                        if (cameraSource != CameraCapturer.CameraSource.BACK_CAMERA) {
                            z = false;
                        }
                        access$1200.setMirror(z);
                        return;
                    }
                    VideoView access$1300 = TwilioVideoActivity.this.primaryVideoView;
                    if (cameraSource != CameraCapturer.CameraSource.BACK_CAMERA) {
                        z = false;
                    }
                    access$1300.setMirror(z);
                }
            }
        };
    }

    private View.OnClickListener switchAudioClickListener() {
        return new View.OnClickListener() {
            public void onClick(View view) {
                FakeR fakeR;
                String str;
                if (TwilioVideoActivity.this.audioManager.isSpeakerphoneOn()) {
                    TwilioVideoActivity.this.audioManager.setSpeakerphoneOn(false);
                } else {
                    TwilioVideoActivity.this.audioManager.setSpeakerphoneOn(true);
                }
                if (TwilioVideoActivity.this.audioManager.isSpeakerphoneOn()) {
                    fakeR = TwilioVideoActivity.FAKE_R;
                    str = "ic_phonelink_ring_white_24dp";
                } else {
                    fakeR = TwilioVideoActivity.FAKE_R;
                    str = "ic_volume_headhphones_white_24dp";
                }
                TwilioVideoActivity.this.switchAudioActionFab.setImageDrawable(ContextCompat.getDrawable(TwilioVideoActivity.this, fakeR.getDrawable(str)));
            }
        };
    }

    private View.OnClickListener localVideoClickListener() {
        return new View.OnClickListener() {
            public void onClick(View view) {
                int i;
                if (TwilioVideoActivity.this.localVideoTrack != null) {
                    boolean z = !TwilioVideoActivity.this.localVideoTrack.isEnabled();
                    TwilioVideoActivity.this.localVideoTrack.enable(z);
                    if (z) {
                        i = TwilioVideoActivity.FAKE_R.getDrawable("ic_videocam_green_24px");
                        TwilioVideoActivity.this.switchCameraActionFab.show();
                    } else {
                        i = TwilioVideoActivity.FAKE_R.getDrawable("ic_videocam_off_red_24px");
                        TwilioVideoActivity.this.switchCameraActionFab.hide();
                    }
                    TwilioVideoActivity.this.localVideoActionFab.setImageDrawable(ContextCompat.getDrawable(TwilioVideoActivity.this, i));
                }
            }
        };
    }

    private View.OnClickListener muteClickListener() {
        return new View.OnClickListener() {
            public void onClick(View view) {
                FakeR fakeR;
                String str;
                if (TwilioVideoActivity.this.localAudioTrack != null) {
                    boolean z = !TwilioVideoActivity.this.localAudioTrack.isEnabled();
                    TwilioVideoActivity.this.localAudioTrack.enable(z);
                    if (z) {
                        fakeR = TwilioVideoActivity.FAKE_R;
                        str = "ic_mic_green_24px";
                    } else {
                        fakeR = TwilioVideoActivity.FAKE_R;
                        str = "ic_mic_off_red_24px";
                    }
                    TwilioVideoActivity.this.muteActionFab.setImageDrawable(ContextCompat.getDrawable(TwilioVideoActivity.this, fakeR.getDrawable(str)));
                }
            }
        };
    }

    private void configureAudio(boolean z) {
        if (z) {
            this.previousAudioMode = this.audioManager.getMode();
            requestAudioFocus();
            this.audioManager.setMode(3);
            this.previousMicrophoneMute = this.audioManager.isMicrophoneMute();
            this.audioManager.setMicrophoneMute(false);
            return;
        }
        this.audioManager.setMode(this.previousAudioMode);
        this.audioManager.abandonAudioFocus((AudioManager.OnAudioFocusChangeListener) null);
        this.audioManager.setMicrophoneMute(this.previousMicrophoneMute);
    }

    private void requestAudioFocus() {
        if (Build.VERSION.SDK_INT >= 26) {
            this.audioManager.requestAudioFocus(new AudioFocusRequest.Builder(2).setAudioAttributes(new AudioAttributes.Builder().setUsage(2).setContentType(1).build()).setAcceptsDelayedFocusGain(true).setOnAudioFocusChangeListener(new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int i) {
                }
            }).build());
            return;
        }
        this.audioManager.requestAudioFocus((AudioManager.OnAudioFocusChangeListener) null, 0, 2);
    }

    /* access modifiers changed from: private */
    public void handleConnectionError(String str) {
        if (this.config.isHandleErrorInApp()) {
            Log.i(TwilioVideo.TAG, "Error handling disabled for the plugin. This error should be handled in the hybrid app");
            finish();
            return;
        }
        Log.i(TwilioVideo.TAG, "Connection error handled by the plugin");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(str).setCancelable(false).setPositiveButton(this.config.getI18nAccept(), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                TwilioVideoActivity.this.finish();
            }
        });
        builder.create().show();
    }

    public void onDisconnect() {
        Room room2 = this.room;
        if (room2 != null) {
            room2.disconnect();
        }
        finish();
    }

    public void finish() {
        configureAudio(false);
        super.finish();
        overridePendingTransition(0, 0);
    }

    /* access modifiers changed from: private */
    public void publishEvent(CallEvent callEvent) {
        TwilioVideoManager.getInstance().publishEvent(callEvent);
    }

    /* access modifiers changed from: private */
    public void publishEvent(CallEvent callEvent, JSONObject jSONObject) {
        TwilioVideoManager.getInstance().publishEvent(callEvent, jSONObject);
    }
}
