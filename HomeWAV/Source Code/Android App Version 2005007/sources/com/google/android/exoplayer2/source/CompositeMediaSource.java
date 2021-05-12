package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.util.Assertions;
import java.io.IOException;
import java.util.HashMap;

public abstract class CompositeMediaSource<T> implements MediaSource {
    private final HashMap<T, MediaSource> childSources = new HashMap<>();
    private ExoPlayer player;

    /* access modifiers changed from: protected */
    public abstract void onChildSourceInfoRefreshed(T t, MediaSource mediaSource, Timeline timeline, Object obj);

    protected CompositeMediaSource() {
    }

    public void prepareSource(ExoPlayer exoPlayer, boolean z, MediaSource.Listener listener) {
        this.player = exoPlayer;
    }

    public void maybeThrowSourceInfoRefreshError() throws IOException {
        for (MediaSource maybeThrowSourceInfoRefreshError : this.childSources.values()) {
            maybeThrowSourceInfoRefreshError.maybeThrowSourceInfoRefreshError();
        }
    }

    public void releaseSource() {
        for (MediaSource releaseSource : this.childSources.values()) {
            releaseSource.releaseSource();
        }
        this.childSources.clear();
        this.player = null;
    }

    /* access modifiers changed from: protected */
    public void prepareChildSource(final T t, final MediaSource mediaSource) {
        Assertions.checkArgument(!this.childSources.containsKey(t));
        this.childSources.put(t, mediaSource);
        mediaSource.prepareSource(this.player, false, new MediaSource.Listener() {
            public void onSourceInfoRefreshed(MediaSource mediaSource, Timeline timeline, Object obj) {
                CompositeMediaSource.this.onChildSourceInfoRefreshed(t, mediaSource, timeline, obj);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void releaseChildSource(T t) {
        this.childSources.remove(t).releaseSource();
    }
}
