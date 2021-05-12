package com.google.android.exoplayer2.util;

import android.os.SystemClock;
import android.util.Log;
import android.view.Surface;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.audio.AudioRendererEventListener;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.drm.DefaultDrmSessionManager;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.MetadataOutput;
import com.google.android.exoplayer2.metadata.emsg.EventMessage;
import com.google.android.exoplayer2.metadata.id3.ApicFrame;
import com.google.android.exoplayer2.metadata.id3.CommentFrame;
import com.google.android.exoplayer2.metadata.id3.GeobFrame;
import com.google.android.exoplayer2.metadata.id3.Id3Frame;
import com.google.android.exoplayer2.metadata.id3.PrivFrame;
import com.google.android.exoplayer2.metadata.id3.TextInformationFrame;
import com.google.android.exoplayer2.metadata.id3.UrlLinkFrame;
import com.google.android.exoplayer2.metadata.scte35.SpliceCommand;
import com.google.android.exoplayer2.source.MediaSourceEventListener;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.ads.AdsMediaSource;
import com.google.android.exoplayer2.trackselection.MappingTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.video.VideoRendererEventListener;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

public class EventLogger implements Player.EventListener, MetadataOutput, AudioRendererEventListener, VideoRendererEventListener, MediaSourceEventListener, AdsMediaSource.EventListener, DefaultDrmSessionManager.EventListener {
    private static final int MAX_TIMELINE_ITEM_LINES = 3;
    private static final String TAG = "EventLogger";
    private static final NumberFormat TIME_FORMAT;
    private final Timeline.Period period = new Timeline.Period();
    private final long startTimeMs = SystemClock.elapsedRealtime();
    private final MappingTrackSelector trackSelector;
    private final Timeline.Window window = new Timeline.Window();

    private static String getAdaptiveSupportString(int i, int i2) {
        return i < 2 ? "N/A" : i2 != 0 ? i2 != 8 ? i2 != 16 ? "?" : "YES" : "YES_NOT_SEAMLESS" : "NO";
    }

    private static String getDiscontinuityReasonString(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? "?" : "INTERNAL" : "AD_INSERTION" : "SEEK_ADJUSTMENT" : "SEEK" : "PERIOD_TRANSITION";
    }

    private static String getFormatSupportString(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? "?" : "YES" : "NO_EXCEEDS_CAPABILITIES" : "NO_UNSUPPORTED_DRM" : "NO_UNSUPPORTED_TYPE" : "NO";
    }

    private static String getRepeatModeString(int i) {
        return i != 0 ? i != 1 ? i != 2 ? "?" : "ALL" : "ONE" : "OFF";
    }

    private static String getStateString(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? "?" : ExifInterface.LONGITUDE_EAST : "R" : "B" : "I";
    }

    private static String getTimelineChangeReasonString(int i) {
        return i != 0 ? i != 1 ? i != 2 ? "?" : "DYNAMIC" : "RESET" : "PREPARED";
    }

    private static String getTrackStatusString(boolean z) {
        return z ? "[X]" : "[ ]";
    }

    public void onAdClicked() {
    }

    public void onAdTapped() {
    }

    public void onDownstreamFormatChanged(int i, Format format, int i2, Object obj, long j) {
    }

    public void onLoadCanceled(DataSpec dataSpec, int i, int i2, Format format, int i3, Object obj, long j, long j2, long j3, long j4, long j5) {
    }

    public void onLoadCompleted(DataSpec dataSpec, int i, int i2, Format format, int i3, Object obj, long j, long j2, long j3, long j4, long j5) {
    }

    public void onLoadStarted(DataSpec dataSpec, int i, int i2, Format format, int i3, Object obj, long j, long j2, long j3) {
    }

    public void onUpstreamDiscarded(int i, long j, long j2) {
    }

    static {
        NumberFormat instance = NumberFormat.getInstance(Locale.US);
        TIME_FORMAT = instance;
        instance.setMinimumFractionDigits(2);
        instance.setMaximumFractionDigits(2);
        instance.setGroupingUsed(false);
    }

    public EventLogger(MappingTrackSelector mappingTrackSelector) {
        this.trackSelector = mappingTrackSelector;
    }

    public void onLoadingChanged(boolean z) {
        Log.d(TAG, "loading [" + z + "]");
    }

    public void onPlayerStateChanged(boolean z, int i) {
        Log.d(TAG, "state [" + getSessionTimeString() + ", " + z + ", " + getStateString(i) + "]");
    }

    public void onRepeatModeChanged(int i) {
        Log.d(TAG, "repeatMode [" + getRepeatModeString(i) + "]");
    }

    public void onShuffleModeEnabledChanged(boolean z) {
        Log.d(TAG, "shuffleModeEnabled [" + z + "]");
    }

    public void onPositionDiscontinuity(int i) {
        Log.d(TAG, "positionDiscontinuity [" + getDiscontinuityReasonString(i) + "]");
    }

    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
        Log.d(TAG, "playbackParameters " + String.format("[speed=%.2f, pitch=%.2f]", new Object[]{Float.valueOf(playbackParameters.speed), Float.valueOf(playbackParameters.pitch)}));
    }

    public void onTimelineChanged(Timeline timeline, Object obj, int i) {
        int periodCount = timeline.getPeriodCount();
        int windowCount = timeline.getWindowCount();
        Log.d(TAG, "timelineChanged [periodCount=" + periodCount + ", windowCount=" + windowCount + ", reason=" + getTimelineChangeReasonString(i));
        for (int i2 = 0; i2 < Math.min(periodCount, 3); i2++) {
            timeline.getPeriod(i2, this.period);
            Log.d(TAG, "  period [" + getTimeString(this.period.getDurationMs()) + "]");
        }
        if (periodCount > 3) {
            Log.d(TAG, "  ...");
        }
        for (int i3 = 0; i3 < Math.min(windowCount, 3); i3++) {
            timeline.getWindow(i3, this.window);
            Log.d(TAG, "  window [" + getTimeString(this.window.getDurationMs()) + ", " + this.window.isSeekable + ", " + this.window.isDynamic + "]");
        }
        if (windowCount > 3) {
            Log.d(TAG, "  ...");
        }
        Log.d(TAG, "]");
    }

    public void onPlayerError(ExoPlaybackException exoPlaybackException) {
        Log.e(TAG, "playerFailed [" + getSessionTimeString() + "]", exoPlaybackException);
    }

    public void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
        String str;
        String str2;
        EventLogger eventLogger;
        EventLogger eventLogger2 = this;
        MappingTrackSelector.MappedTrackInfo currentMappedTrackInfo = eventLogger2.trackSelector.getCurrentMappedTrackInfo();
        if (currentMappedTrackInfo == null) {
            Log.d(TAG, "Tracks []");
            return;
        }
        Log.d(TAG, "Tracks [");
        int i = 0;
        while (true) {
            str = "  ]";
            str2 = " [";
            if (i >= currentMappedTrackInfo.length) {
                break;
            }
            TrackGroupArray trackGroups = currentMappedTrackInfo.getTrackGroups(i);
            TrackSelection trackSelection = trackSelectionArray.get(i);
            if (trackGroups.length > 0) {
                Log.d(TAG, "  Renderer:" + i + str2);
                int i2 = 0;
                while (i2 < trackGroups.length) {
                    TrackGroup trackGroup = trackGroups.get(i2);
                    TrackGroupArray trackGroupArray2 = trackGroups;
                    String str3 = str;
                    String adaptiveSupportString = getAdaptiveSupportString(trackGroup.length, currentMappedTrackInfo.getAdaptiveSupport(i, i2, false));
                    Log.d(TAG, "    Group:" + i2 + ", adaptive_supported=" + adaptiveSupportString + str2);
                    int i3 = 0;
                    while (i3 < trackGroup.length) {
                        String trackStatusString = getTrackStatusString(trackSelection, trackGroup, i3);
                        String formatSupportString = getFormatSupportString(currentMappedTrackInfo.getTrackFormatSupport(i, i2, i3));
                        String str4 = str2;
                        Log.d(TAG, "      " + trackStatusString + " Track:" + i3 + ", " + Format.toLogString(trackGroup.getFormat(i3)) + ", supported=" + formatSupportString);
                        i3++;
                        str2 = str4;
                    }
                    String str5 = str2;
                    Log.d(TAG, "    ]");
                    i2++;
                    trackGroups = trackGroupArray2;
                    str = str3;
                }
                String str6 = str;
                if (trackSelection != null) {
                    int i4 = 0;
                    while (true) {
                        if (i4 >= trackSelection.length()) {
                            break;
                        }
                        Metadata metadata = trackSelection.getFormat(i4).metadata;
                        if (metadata != null) {
                            Log.d(TAG, "    Metadata [");
                            eventLogger = this;
                            eventLogger.printMetadata(metadata, "      ");
                            Log.d(TAG, "    ]");
                            break;
                        }
                        i4++;
                    }
                    Log.d(TAG, str6);
                }
                eventLogger = this;
                Log.d(TAG, str6);
            } else {
                eventLogger = eventLogger2;
            }
            i++;
            eventLogger2 = eventLogger;
        }
        EventLogger eventLogger3 = eventLogger2;
        String str7 = str;
        String str8 = str2;
        TrackGroupArray unassociatedTrackGroups = currentMappedTrackInfo.getUnassociatedTrackGroups();
        if (unassociatedTrackGroups.length > 0) {
            Log.d(TAG, "  Renderer:None [");
            int i5 = 0;
            while (i5 < unassociatedTrackGroups.length) {
                StringBuilder sb = new StringBuilder();
                sb.append("    Group:");
                sb.append(i5);
                String str9 = str8;
                sb.append(str9);
                Log.d(TAG, sb.toString());
                TrackGroup trackGroup2 = unassociatedTrackGroups.get(i5);
                int i6 = 0;
                while (i6 < trackGroup2.length) {
                    String trackStatusString2 = getTrackStatusString(false);
                    TrackGroupArray trackGroupArray3 = unassociatedTrackGroups;
                    String formatSupportString2 = getFormatSupportString(0);
                    Log.d(TAG, "      " + trackStatusString2 + " Track:" + i6 + ", " + Format.toLogString(trackGroup2.getFormat(i6)) + ", supported=" + formatSupportString2);
                    i6++;
                    unassociatedTrackGroups = trackGroupArray3;
                }
                TrackGroupArray trackGroupArray4 = unassociatedTrackGroups;
                Log.d(TAG, "    ]");
                i5++;
                str8 = str9;
            }
            Log.d(TAG, str7);
        }
        Log.d(TAG, "]");
    }

    public void onSeekProcessed() {
        Log.d(TAG, "seekProcessed");
    }

    public void onMetadata(Metadata metadata) {
        Log.d(TAG, "onMetadata [");
        printMetadata(metadata, "  ");
        Log.d(TAG, "]");
    }

    public void onAudioEnabled(DecoderCounters decoderCounters) {
        Log.d(TAG, "audioEnabled [" + getSessionTimeString() + "]");
    }

    public void onAudioSessionId(int i) {
        Log.d(TAG, "audioSessionId [" + i + "]");
    }

    public void onAudioDecoderInitialized(String str, long j, long j2) {
        Log.d(TAG, "audioDecoderInitialized [" + getSessionTimeString() + ", " + str + "]");
    }

    public void onAudioInputFormatChanged(Format format) {
        Log.d(TAG, "audioFormatChanged [" + getSessionTimeString() + ", " + Format.toLogString(format) + "]");
    }

    public void onAudioDisabled(DecoderCounters decoderCounters) {
        Log.d(TAG, "audioDisabled [" + getSessionTimeString() + "]");
    }

    public void onAudioSinkUnderrun(int i, long j, long j2) {
        printInternalError("audioTrackUnderrun [" + i + ", " + j + ", " + j2 + "]", (Exception) null);
    }

    public void onVideoEnabled(DecoderCounters decoderCounters) {
        Log.d(TAG, "videoEnabled [" + getSessionTimeString() + "]");
    }

    public void onVideoDecoderInitialized(String str, long j, long j2) {
        Log.d(TAG, "videoDecoderInitialized [" + getSessionTimeString() + ", " + str + "]");
    }

    public void onVideoInputFormatChanged(Format format) {
        Log.d(TAG, "videoFormatChanged [" + getSessionTimeString() + ", " + Format.toLogString(format) + "]");
    }

    public void onVideoDisabled(DecoderCounters decoderCounters) {
        Log.d(TAG, "videoDisabled [" + getSessionTimeString() + "]");
    }

    public void onDroppedFrames(int i, long j) {
        Log.d(TAG, "droppedFrames [" + getSessionTimeString() + ", " + i + "]");
    }

    public void onVideoSizeChanged(int i, int i2, int i3, float f) {
        Log.d(TAG, "videoSizeChanged [" + i + ", " + i2 + "]");
    }

    public void onRenderedFirstFrame(Surface surface) {
        Log.d(TAG, "renderedFirstFrame [" + surface + "]");
    }

    public void onDrmSessionManagerError(Exception exc) {
        printInternalError("drmSessionManagerError", exc);
    }

    public void onDrmKeysRestored() {
        Log.d(TAG, "drmKeysRestored [" + getSessionTimeString() + "]");
    }

    public void onDrmKeysRemoved() {
        Log.d(TAG, "drmKeysRemoved [" + getSessionTimeString() + "]");
    }

    public void onDrmKeysLoaded() {
        Log.d(TAG, "drmKeysLoaded [" + getSessionTimeString() + "]");
    }

    public void onLoadError(DataSpec dataSpec, int i, int i2, Format format, int i3, Object obj, long j, long j2, long j3, long j4, long j5, IOException iOException, boolean z) {
        printInternalError("loadError", iOException);
    }

    public void onAdLoadError(IOException iOException) {
        printInternalError("adLoadError", iOException);
    }

    public void onInternalAdLoadError(RuntimeException runtimeException) {
        printInternalError("internalAdLoadError", runtimeException);
    }

    private void printInternalError(String str, Exception exc) {
        Log.e(TAG, "internalError [" + getSessionTimeString() + ", " + str + "]", exc);
    }

    private void printMetadata(Metadata metadata, String str) {
        for (int i = 0; i < metadata.length(); i++) {
            Metadata.Entry entry = metadata.get(i);
            if (entry instanceof TextInformationFrame) {
                TextInformationFrame textInformationFrame = (TextInformationFrame) entry;
                Log.d(TAG, str + String.format("%s: value=%s", new Object[]{textInformationFrame.id, textInformationFrame.value}));
            } else if (entry instanceof UrlLinkFrame) {
                UrlLinkFrame urlLinkFrame = (UrlLinkFrame) entry;
                Log.d(TAG, str + String.format("%s: url=%s", new Object[]{urlLinkFrame.id, urlLinkFrame.url}));
            } else if (entry instanceof PrivFrame) {
                PrivFrame privFrame = (PrivFrame) entry;
                Log.d(TAG, str + String.format("%s: owner=%s", new Object[]{privFrame.id, privFrame.owner}));
            } else if (entry instanceof GeobFrame) {
                GeobFrame geobFrame = (GeobFrame) entry;
                Log.d(TAG, str + String.format("%s: mimeType=%s, filename=%s, description=%s", new Object[]{geobFrame.id, geobFrame.mimeType, geobFrame.filename, geobFrame.description}));
            } else if (entry instanceof ApicFrame) {
                ApicFrame apicFrame = (ApicFrame) entry;
                Log.d(TAG, str + String.format("%s: mimeType=%s, description=%s", new Object[]{apicFrame.id, apicFrame.mimeType, apicFrame.description}));
            } else if (entry instanceof CommentFrame) {
                CommentFrame commentFrame = (CommentFrame) entry;
                Log.d(TAG, str + String.format("%s: language=%s, description=%s", new Object[]{commentFrame.id, commentFrame.language, commentFrame.description}));
            } else if (entry instanceof Id3Frame) {
                Log.d(TAG, str + String.format("%s", new Object[]{((Id3Frame) entry).id}));
            } else if (entry instanceof EventMessage) {
                EventMessage eventMessage = (EventMessage) entry;
                Log.d(TAG, str + String.format("EMSG: scheme=%s, id=%d, value=%s", new Object[]{eventMessage.schemeIdUri, Long.valueOf(eventMessage.id), eventMessage.value}));
            } else if (entry instanceof SpliceCommand) {
                String format = String.format("SCTE-35 splice command: type=%s.", new Object[]{entry.getClass().getSimpleName()});
                Log.d(TAG, str + format);
            }
        }
    }

    private String getSessionTimeString() {
        return getTimeString(SystemClock.elapsedRealtime() - this.startTimeMs);
    }

    private static String getTimeString(long j) {
        return j == C.TIME_UNSET ? "?" : TIME_FORMAT.format((double) (((float) j) / 1000.0f));
    }

    private static String getTrackStatusString(TrackSelection trackSelection, TrackGroup trackGroup, int i) {
        return getTrackStatusString((trackSelection == null || trackSelection.getTrackGroup() != trackGroup || trackSelection.indexOf(i) == -1) ? false : true);
    }
}
