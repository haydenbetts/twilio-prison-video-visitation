package com.google.android.exoplayer2.ui;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.google.android.exoplayer2.ControlDispatcher;
import com.google.android.exoplayer2.PlaybackPreparer;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.id3.ApicFrame;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.TextOutput;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.PlayerControlView;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoListener;
import java.util.List;

public class PlayerView extends FrameLayout {
    private static final int SURFACE_TYPE_NONE = 0;
    private static final int SURFACE_TYPE_SURFACE_VIEW = 1;
    private static final int SURFACE_TYPE_TEXTURE_VIEW = 2;
    private final ImageView artworkView;
    private final ComponentListener componentListener;
    /* access modifiers changed from: private */
    public final AspectRatioFrameLayout contentFrame;
    private final PlayerControlView controller;
    private boolean controllerAutoShow;
    /* access modifiers changed from: private */
    public boolean controllerHideDuringAds;
    private boolean controllerHideOnTouch;
    private int controllerShowTimeoutMs;
    private Bitmap defaultArtwork;
    private final FrameLayout overlayFrameLayout;
    private Player player;
    /* access modifiers changed from: private */
    public final View shutterView;
    /* access modifiers changed from: private */
    public final SubtitleView subtitleView;
    /* access modifiers changed from: private */
    public final View surfaceView;
    /* access modifiers changed from: private */
    public int textureViewRotation;
    private boolean useArtwork;
    private boolean useController;

    private boolean isDpadKey(int i) {
        return i == 19 || i == 270 || i == 22 || i == 271 || i == 20 || i == 269 || i == 21 || i == 268 || i == 23;
    }

    public PlayerView(Context context) {
        this(context, (AttributeSet) null);
    }

    public PlayerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* JADX INFO: finally extract failed */
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PlayerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        int i2;
        int i3;
        boolean z;
        int i4;
        boolean z2;
        int i5;
        boolean z3;
        boolean z4;
        int i6;
        boolean z5;
        boolean z6;
        Context context2 = context;
        AttributeSet attributeSet2 = attributeSet;
        if (isInEditMode()) {
            this.contentFrame = null;
            this.shutterView = null;
            this.surfaceView = null;
            this.artworkView = null;
            this.subtitleView = null;
            this.controller = null;
            this.componentListener = null;
            this.overlayFrameLayout = null;
            ImageView imageView = new ImageView(context2);
            if (Util.SDK_INT >= 23) {
                configureEditModeLogoV23(getResources(), imageView);
            } else {
                configureEditModeLogo(getResources(), imageView);
            }
            addView(imageView);
            return;
        }
        int i7 = R.layout.exo_player_view;
        if (attributeSet2 != null) {
            TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet2, R.styleable.PlayerView, 0, 0);
            try {
                z3 = obtainStyledAttributes.hasValue(R.styleable.PlayerView_shutter_background_color);
                i5 = obtainStyledAttributes.getColor(R.styleable.PlayerView_shutter_background_color, 0);
                int resourceId = obtainStyledAttributes.getResourceId(R.styleable.PlayerView_player_layout_id, i7);
                z2 = obtainStyledAttributes.getBoolean(R.styleable.PlayerView_use_artwork, true);
                i4 = obtainStyledAttributes.getResourceId(R.styleable.PlayerView_default_artwork, 0);
                z = obtainStyledAttributes.getBoolean(R.styleable.PlayerView_use_controller, true);
                i3 = obtainStyledAttributes.getInt(R.styleable.PlayerView_surface_type, 1);
                i2 = obtainStyledAttributes.getInt(R.styleable.PlayerView_resize_mode, 0);
                int i8 = obtainStyledAttributes.getInt(R.styleable.PlayerView_show_timeout, 5000);
                boolean z7 = obtainStyledAttributes.getBoolean(R.styleable.PlayerView_hide_on_touch, true);
                boolean z8 = obtainStyledAttributes.getBoolean(R.styleable.PlayerView_auto_show, true);
                int i9 = resourceId;
                boolean z9 = obtainStyledAttributes.getBoolean(R.styleable.PlayerView_hide_during_ads, true);
                obtainStyledAttributes.recycle();
                z4 = z7;
                i6 = i8;
                z5 = z8;
                z6 = z9;
                i7 = i9;
            } catch (Throwable th) {
                obtainStyledAttributes.recycle();
                throw th;
            }
        } else {
            z6 = true;
            z5 = true;
            i6 = 5000;
            z4 = true;
            z3 = false;
            i5 = 0;
            z2 = true;
            i4 = 0;
            z = true;
            i3 = 1;
            i2 = 0;
        }
        LayoutInflater.from(context).inflate(i7, this);
        this.componentListener = new ComponentListener();
        setDescendantFocusability(262144);
        AspectRatioFrameLayout aspectRatioFrameLayout = (AspectRatioFrameLayout) findViewById(R.id.exo_content_frame);
        this.contentFrame = aspectRatioFrameLayout;
        if (aspectRatioFrameLayout != null) {
            setResizeModeRaw(aspectRatioFrameLayout, i2);
        }
        View findViewById = findViewById(R.id.exo_shutter);
        this.shutterView = findViewById;
        if (findViewById != null && z3) {
            findViewById.setBackgroundColor(i5);
        }
        if (aspectRatioFrameLayout == null || i3 == 0) {
            this.surfaceView = null;
        } else {
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);
            View textureView = i3 == 2 ? new TextureView(context2) : new SurfaceView(context2);
            this.surfaceView = textureView;
            textureView.setLayoutParams(layoutParams);
            aspectRatioFrameLayout.addView(textureView, 0);
        }
        this.overlayFrameLayout = (FrameLayout) findViewById(R.id.exo_overlay);
        ImageView imageView2 = (ImageView) findViewById(R.id.exo_artwork);
        this.artworkView = imageView2;
        this.useArtwork = z2 && imageView2 != null;
        if (i4 != 0) {
            this.defaultArtwork = BitmapFactory.decodeResource(context.getResources(), i4);
        }
        SubtitleView subtitleView2 = (SubtitleView) findViewById(R.id.exo_subtitles);
        this.subtitleView = subtitleView2;
        if (subtitleView2 != null) {
            subtitleView2.setUserDefaultStyle();
            subtitleView2.setUserDefaultTextSize();
        }
        PlayerControlView playerControlView = (PlayerControlView) findViewById(R.id.exo_controller);
        View findViewById2 = findViewById(R.id.exo_controller_placeholder);
        if (playerControlView != null) {
            this.controller = playerControlView;
        } else if (findViewById2 != null) {
            PlayerControlView playerControlView2 = new PlayerControlView(context2, (AttributeSet) null, 0, attributeSet2);
            this.controller = playerControlView2;
            playerControlView2.setLayoutParams(findViewById2.getLayoutParams());
            ViewGroup viewGroup = (ViewGroup) findViewById2.getParent();
            int indexOfChild = viewGroup.indexOfChild(findViewById2);
            viewGroup.removeView(findViewById2);
            viewGroup.addView(playerControlView2, indexOfChild);
        } else {
            this.controller = null;
        }
        PlayerControlView playerControlView3 = this.controller;
        this.controllerShowTimeoutMs = playerControlView3 == null ? 0 : i6;
        this.controllerHideOnTouch = z4;
        this.controllerAutoShow = z5;
        this.controllerHideDuringAds = z6;
        this.useController = z && playerControlView3 != null;
        hideController();
    }

    public static void switchTargetView(Player player2, PlayerView playerView, PlayerView playerView2) {
        if (playerView != playerView2) {
            if (playerView2 != null) {
                playerView2.setPlayer(player2);
            }
            if (playerView != null) {
                playerView.setPlayer((Player) null);
            }
        }
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player2) {
        Player player3 = this.player;
        if (player3 != player2) {
            if (player3 != null) {
                player3.removeListener(this.componentListener);
                Player.VideoComponent videoComponent = this.player.getVideoComponent();
                if (videoComponent != null) {
                    videoComponent.removeVideoListener(this.componentListener);
                    View view = this.surfaceView;
                    if (view instanceof TextureView) {
                        videoComponent.clearVideoTextureView((TextureView) view);
                    } else if (view instanceof SurfaceView) {
                        videoComponent.clearVideoSurfaceView((SurfaceView) view);
                    }
                }
                Player.TextComponent textComponent = this.player.getTextComponent();
                if (textComponent != null) {
                    textComponent.removeTextOutput(this.componentListener);
                }
            }
            this.player = player2;
            if (this.useController) {
                this.controller.setPlayer(player2);
            }
            View view2 = this.shutterView;
            if (view2 != null) {
                view2.setVisibility(0);
            }
            SubtitleView subtitleView2 = this.subtitleView;
            if (subtitleView2 != null) {
                subtitleView2.setCues((List<Cue>) null);
            }
            if (player2 != null) {
                Player.VideoComponent videoComponent2 = player2.getVideoComponent();
                if (videoComponent2 != null) {
                    View view3 = this.surfaceView;
                    if (view3 instanceof TextureView) {
                        videoComponent2.setVideoTextureView((TextureView) view3);
                    } else if (view3 instanceof SurfaceView) {
                        videoComponent2.setVideoSurfaceView((SurfaceView) view3);
                    }
                    videoComponent2.addVideoListener(this.componentListener);
                }
                Player.TextComponent textComponent2 = player2.getTextComponent();
                if (textComponent2 != null) {
                    textComponent2.addTextOutput(this.componentListener);
                }
                player2.addListener(this.componentListener);
                maybeShowController(false);
                updateForCurrentTrackSelections();
                return;
            }
            hideController();
            hideArtwork();
        }
    }

    public void setVisibility(int i) {
        super.setVisibility(i);
        View view = this.surfaceView;
        if (view instanceof SurfaceView) {
            view.setVisibility(i);
        }
    }

    public void setResizeMode(int i) {
        Assertions.checkState(this.contentFrame != null);
        this.contentFrame.setResizeMode(i);
    }

    public boolean getUseArtwork() {
        return this.useArtwork;
    }

    public void setUseArtwork(boolean z) {
        Assertions.checkState(!z || this.artworkView != null);
        if (this.useArtwork != z) {
            this.useArtwork = z;
            updateForCurrentTrackSelections();
        }
    }

    public Bitmap getDefaultArtwork() {
        return this.defaultArtwork;
    }

    public void setDefaultArtwork(Bitmap bitmap) {
        if (this.defaultArtwork != bitmap) {
            this.defaultArtwork = bitmap;
            updateForCurrentTrackSelections();
        }
    }

    public boolean getUseController() {
        return this.useController;
    }

    public void setUseController(boolean z) {
        Assertions.checkState(!z || this.controller != null);
        if (this.useController != z) {
            this.useController = z;
            if (z) {
                this.controller.setPlayer(this.player);
                return;
            }
            PlayerControlView playerControlView = this.controller;
            if (playerControlView != null) {
                playerControlView.hide();
                this.controller.setPlayer((Player) null);
            }
        }
    }

    public void setShutterBackgroundColor(int i) {
        View view = this.shutterView;
        if (view != null) {
            view.setBackgroundColor(i);
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        Player player2 = this.player;
        if (player2 == null || !player2.isPlayingAd()) {
            boolean z = isDpadKey(keyEvent.getKeyCode()) && this.useController && !this.controller.isVisible();
            maybeShowController(true);
            if (z || dispatchMediaKeyEvent(keyEvent) || super.dispatchKeyEvent(keyEvent)) {
                return true;
            }
            return false;
        }
        this.overlayFrameLayout.requestFocus();
        return super.dispatchKeyEvent(keyEvent);
    }

    public boolean dispatchMediaKeyEvent(KeyEvent keyEvent) {
        return this.useController && this.controller.dispatchMediaKeyEvent(keyEvent);
    }

    public void showController() {
        showController(shouldShowControllerIndefinitely());
    }

    public void hideController() {
        PlayerControlView playerControlView = this.controller;
        if (playerControlView != null) {
            playerControlView.hide();
        }
    }

    public int getControllerShowTimeoutMs() {
        return this.controllerShowTimeoutMs;
    }

    public void setControllerShowTimeoutMs(int i) {
        Assertions.checkState(this.controller != null);
        this.controllerShowTimeoutMs = i;
        if (this.controller.isVisible()) {
            showController();
        }
    }

    public boolean getControllerHideOnTouch() {
        return this.controllerHideOnTouch;
    }

    public void setControllerHideOnTouch(boolean z) {
        Assertions.checkState(this.controller != null);
        this.controllerHideOnTouch = z;
    }

    public boolean getControllerAutoShow() {
        return this.controllerAutoShow;
    }

    public void setControllerAutoShow(boolean z) {
        this.controllerAutoShow = z;
    }

    public void setControllerHideDuringAds(boolean z) {
        this.controllerHideDuringAds = z;
    }

    public void setControllerVisibilityListener(PlayerControlView.VisibilityListener visibilityListener) {
        Assertions.checkState(this.controller != null);
        this.controller.setVisibilityListener(visibilityListener);
    }

    public void setPlaybackPreparer(PlaybackPreparer playbackPreparer) {
        Assertions.checkState(this.controller != null);
        this.controller.setPlaybackPreparer(playbackPreparer);
    }

    public void setControlDispatcher(ControlDispatcher controlDispatcher) {
        Assertions.checkState(this.controller != null);
        this.controller.setControlDispatcher(controlDispatcher);
    }

    public void setRewindIncrementMs(int i) {
        Assertions.checkState(this.controller != null);
        this.controller.setRewindIncrementMs(i);
    }

    public void setFastForwardIncrementMs(int i) {
        Assertions.checkState(this.controller != null);
        this.controller.setFastForwardIncrementMs(i);
    }

    public void setRepeatToggleModes(int i) {
        Assertions.checkState(this.controller != null);
        this.controller.setRepeatToggleModes(i);
    }

    public void setShowShuffleButton(boolean z) {
        Assertions.checkState(this.controller != null);
        this.controller.setShowShuffleButton(z);
    }

    public void setShowMultiWindowTimeBar(boolean z) {
        Assertions.checkState(this.controller != null);
        this.controller.setShowMultiWindowTimeBar(z);
    }

    public View getVideoSurfaceView() {
        return this.surfaceView;
    }

    public FrameLayout getOverlayFrameLayout() {
        return this.overlayFrameLayout;
    }

    public SubtitleView getSubtitleView() {
        return this.subtitleView;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.useController || this.player == null || motionEvent.getActionMasked() != 0) {
            return false;
        }
        if (!this.controller.isVisible()) {
            maybeShowController(true);
        } else if (this.controllerHideOnTouch) {
            this.controller.hide();
        }
        return true;
    }

    public boolean onTrackballEvent(MotionEvent motionEvent) {
        if (!this.useController || this.player == null) {
            return false;
        }
        maybeShowController(true);
        return true;
    }

    /* access modifiers changed from: private */
    public void maybeShowController(boolean z) {
        if ((!isPlayingAd() || !this.controllerHideDuringAds) && this.useController) {
            boolean z2 = this.controller.isVisible() && this.controller.getShowTimeoutMs() <= 0;
            boolean shouldShowControllerIndefinitely = shouldShowControllerIndefinitely();
            if (z || z2 || shouldShowControllerIndefinitely) {
                showController(shouldShowControllerIndefinitely);
            }
        }
    }

    private boolean shouldShowControllerIndefinitely() {
        Player player2 = this.player;
        if (player2 == null) {
            return true;
        }
        int playbackState = player2.getPlaybackState();
        if (!this.controllerAutoShow || (playbackState != 1 && playbackState != 4 && this.player.getPlayWhenReady())) {
            return false;
        }
        return true;
    }

    private void showController(boolean z) {
        if (this.useController) {
            this.controller.setShowTimeoutMs(z ? 0 : this.controllerShowTimeoutMs);
            this.controller.show();
        }
    }

    /* access modifiers changed from: private */
    public boolean isPlayingAd() {
        Player player2 = this.player;
        return player2 != null && player2.isPlayingAd() && this.player.getPlayWhenReady();
    }

    /* access modifiers changed from: private */
    public void updateForCurrentTrackSelections() {
        Player player2 = this.player;
        if (player2 != null) {
            TrackSelectionArray currentTrackSelections = player2.getCurrentTrackSelections();
            int i = 0;
            while (i < currentTrackSelections.length) {
                if (this.player.getRendererType(i) != 2 || currentTrackSelections.get(i) == null) {
                    i++;
                } else {
                    hideArtwork();
                    return;
                }
            }
            View view = this.shutterView;
            if (view != null) {
                view.setVisibility(0);
            }
            if (this.useArtwork) {
                for (int i2 = 0; i2 < currentTrackSelections.length; i2++) {
                    TrackSelection trackSelection = currentTrackSelections.get(i2);
                    if (trackSelection != null) {
                        int i3 = 0;
                        while (i3 < trackSelection.length()) {
                            Metadata metadata = trackSelection.getFormat(i3).metadata;
                            if (metadata == null || !setArtworkFromMetadata(metadata)) {
                                i3++;
                            } else {
                                return;
                            }
                        }
                        continue;
                    }
                }
                if (setArtworkFromBitmap(this.defaultArtwork)) {
                    return;
                }
            }
            hideArtwork();
        }
    }

    private boolean setArtworkFromMetadata(Metadata metadata) {
        for (int i = 0; i < metadata.length(); i++) {
            Metadata.Entry entry = metadata.get(i);
            if (entry instanceof ApicFrame) {
                byte[] bArr = ((ApicFrame) entry).pictureData;
                return setArtworkFromBitmap(BitmapFactory.decodeByteArray(bArr, 0, bArr.length));
            }
        }
        return false;
    }

    private boolean setArtworkFromBitmap(Bitmap bitmap) {
        if (bitmap != null) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            if (width > 0 && height > 0) {
                AspectRatioFrameLayout aspectRatioFrameLayout = this.contentFrame;
                if (aspectRatioFrameLayout != null) {
                    aspectRatioFrameLayout.setAspectRatio(((float) width) / ((float) height));
                }
                this.artworkView.setImageBitmap(bitmap);
                this.artworkView.setVisibility(0);
                return true;
            }
        }
        return false;
    }

    private void hideArtwork() {
        ImageView imageView = this.artworkView;
        if (imageView != null) {
            imageView.setImageResource(17170445);
            this.artworkView.setVisibility(4);
        }
    }

    private static void configureEditModeLogoV23(Resources resources, ImageView imageView) {
        imageView.setImageDrawable(resources.getDrawable(R.drawable.exo_edit_mode_logo, (Resources.Theme) null));
        imageView.setBackgroundColor(resources.getColor(R.color.exo_edit_mode_background_color, (Resources.Theme) null));
    }

    private static void configureEditModeLogo(Resources resources, ImageView imageView) {
        imageView.setImageDrawable(resources.getDrawable(R.drawable.exo_edit_mode_logo));
        imageView.setBackgroundColor(resources.getColor(R.color.exo_edit_mode_background_color));
    }

    private static void setResizeModeRaw(AspectRatioFrameLayout aspectRatioFrameLayout, int i) {
        aspectRatioFrameLayout.setResizeMode(i);
    }

    /* access modifiers changed from: private */
    public static void applyTextureViewRotation(TextureView textureView, int i) {
        float width = (float) textureView.getWidth();
        float height = (float) textureView.getHeight();
        if (width == 0.0f || height == 0.0f || i == 0) {
            textureView.setTransform((Matrix) null);
            return;
        }
        Matrix matrix = new Matrix();
        float f = width / 2.0f;
        float f2 = height / 2.0f;
        matrix.postRotate((float) i, f, f2);
        RectF rectF = new RectF(0.0f, 0.0f, width, height);
        RectF rectF2 = new RectF();
        matrix.mapRect(rectF2, rectF);
        matrix.postScale(width / rectF2.width(), height / rectF2.height(), f, f2);
        textureView.setTransform(matrix);
    }

    private final class ComponentListener extends Player.DefaultEventListener implements TextOutput, VideoListener, View.OnLayoutChangeListener {
        private ComponentListener() {
        }

        public void onCues(List<Cue> list) {
            if (PlayerView.this.subtitleView != null) {
                PlayerView.this.subtitleView.onCues(list);
            }
        }

        public void onVideoSizeChanged(int i, int i2, int i3, float f) {
            if (PlayerView.this.contentFrame != null) {
                float f2 = (i2 == 0 || i == 0) ? 1.0f : (((float) i) * f) / ((float) i2);
                if (PlayerView.this.surfaceView instanceof TextureView) {
                    if (i3 == 90 || i3 == 270) {
                        f2 = 1.0f / f2;
                    }
                    if (PlayerView.this.textureViewRotation != 0) {
                        PlayerView.this.surfaceView.removeOnLayoutChangeListener(this);
                    }
                    int unused = PlayerView.this.textureViewRotation = i3;
                    if (PlayerView.this.textureViewRotation != 0) {
                        PlayerView.this.surfaceView.addOnLayoutChangeListener(this);
                    }
                    PlayerView.applyTextureViewRotation((TextureView) PlayerView.this.surfaceView, PlayerView.this.textureViewRotation);
                }
                PlayerView.this.contentFrame.setAspectRatio(f2);
            }
        }

        public void onRenderedFirstFrame() {
            if (PlayerView.this.shutterView != null) {
                PlayerView.this.shutterView.setVisibility(4);
            }
        }

        public void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
            PlayerView.this.updateForCurrentTrackSelections();
        }

        public void onPlayerStateChanged(boolean z, int i) {
            if (!PlayerView.this.isPlayingAd() || !PlayerView.this.controllerHideDuringAds) {
                PlayerView.this.maybeShowController(false);
            } else {
                PlayerView.this.hideController();
            }
        }

        public void onPositionDiscontinuity(int i) {
            if (PlayerView.this.isPlayingAd() && PlayerView.this.controllerHideDuringAds) {
                PlayerView.this.hideController();
            }
        }

        public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            PlayerView.applyTextureViewRotation((TextureView) view, PlayerView.this.textureViewRotation);
        }
    }
}
