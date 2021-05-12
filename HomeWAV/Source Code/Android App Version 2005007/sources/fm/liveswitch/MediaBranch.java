package fm.liveswitch;

import fm.liveswitch.IMediaElement;
import fm.liveswitch.IMediaInput;
import fm.liveswitch.IMediaInputCollection;
import fm.liveswitch.IMediaOutput;
import fm.liveswitch.IMediaOutputCollection;
import fm.liveswitch.MediaBranch;
import fm.liveswitch.MediaBuffer;
import fm.liveswitch.MediaBufferCollection;
import fm.liveswitch.MediaFormat;
import fm.liveswitch.MediaFrame;
import fm.liveswitch.MediaPipe;
import fm.liveswitch.MediaSink;
import fm.liveswitch.MediaSource;
import fm.liveswitch.MediaTrack;
import java.util.ArrayList;

public abstract class MediaBranch<TIOutput extends IMediaOutput<TIOutput, TIInput, TFrame, TBuffer, TBufferCollection, TFormat>, TIOutputCollection extends IMediaOutputCollection<TIOutput, TIInput, TFrame, TBuffer, TBufferCollection, TFormat, TIOutputCollection>, TIInput extends IMediaInput<TIOutput, TIInput, TFrame, TBuffer, TBufferCollection, TFormat>, TIInputCollection extends IMediaInputCollection<TIOutput, TIInput, TFrame, TBuffer, TBufferCollection, TFormat, TIInputCollection>, TIElement extends IMediaElement, TSource extends MediaSource<TIOutput, TIInput, TIInputCollection, TSource, TFrame, TBuffer, TBufferCollection, TFormat>, TSink extends MediaSink<TIOutput, TIOutputCollection, TIInput, TSink, TFrame, TBuffer, TBufferCollection, TFormat>, TPipe extends MediaPipe<TIOutput, TIOutputCollection, TIInput, TIInputCollection, TPipe, TFrame, TBuffer, TBufferCollection, TFormat>, TTrack extends MediaTrack<TIOutput, TIOutputCollection, TIInput, TIInputCollection, TIElement, TSource, TSink, TPipe, TTrack, TBranch, TFrame, TBuffer, TBufferCollection, TFormat>, TBranch extends MediaBranch<TIOutput, TIOutputCollection, TIInput, TIInputCollection, TIElement, TSource, TSink, TPipe, TTrack, TBranch, TFrame, TBuffer, TBufferCollection, TFormat>, TFrame extends MediaFrame<TBuffer, TBufferCollection, TFormat, TFrame>, TBuffer extends MediaBuffer<TFormat, TBuffer>, TBufferCollection extends MediaBufferCollection<TBuffer, TBufferCollection, TFormat>, TFormat extends MediaFormat<TFormat>> extends Dynamic implements IMediaElement, IElement {
    private String __id = Utility.generateId();
    private ArrayList<TTrack> __tracks = new ArrayList<>();
    private Object _destroyLock;
    private boolean _destroyed;
    private String _externalId;
    private boolean _persistent;

    /* access modifiers changed from: protected */
    public abstract TTrack[] arrayFromTracks(ArrayList<TTrack> arrayList);

    public abstract String getLabel();

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0013, code lost:
        if (r2 >= r3) goto L_0x0023;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0015, code lost:
        r4 = r0[r2];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001b, code lost:
        if (r4.getPersistent() != false) goto L_0x0020;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001d, code lost:
        r4.destroy();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0020, code lost:
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0023, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x000e, code lost:
        r0 = getTracks();
        r3 = r0.length;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean destroy() {
        /*
            r6 = this;
            java.lang.Object r0 = r6._destroyLock
            monitor-enter(r0)
            boolean r1 = r6._destroyed     // Catch:{ all -> 0x0024 }
            r2 = 0
            if (r1 == 0) goto L_0x000a
            monitor-exit(r0)     // Catch:{ all -> 0x0024 }
            return r2
        L_0x000a:
            r1 = 1
            r6._destroyed = r1     // Catch:{ all -> 0x0024 }
            monitor-exit(r0)     // Catch:{ all -> 0x0024 }
            fm.liveswitch.MediaTrack[] r0 = r6.getTracks()
            int r3 = r0.length
        L_0x0013:
            if (r2 >= r3) goto L_0x0023
            r4 = r0[r2]
            boolean r5 = r4.getPersistent()
            if (r5 != 0) goto L_0x0020
            r4.destroy()
        L_0x0020:
            int r2 = r2 + 1
            goto L_0x0013
        L_0x0023:
            return r1
        L_0x0024:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0024 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.MediaBranch.destroy():boolean");
    }

    public TTrack getActiveTrack() {
        return (MediaTrack) Utility.firstOrDefault((T[]) getActiveTracks());
    }

    public TTrack[] getActiveTracks() {
        ArrayList arrayList = new ArrayList();
        for (MediaTrack mediaTrack : getTracks()) {
            if (!mediaTrack.getDisabled()) {
                arrayList.add(mediaTrack);
            }
        }
        return arrayFromTracks(arrayList);
    }

    public boolean getDeactivated() {
        MediaTrack[] activeTracks = getActiveTracks();
        if (activeTracks == null || ArrayExtensions.getLength((Object[]) activeTracks) <= 0) {
            return false;
        }
        for (MediaTrack deactivated : activeTracks) {
            if (!deactivated.getDeactivated()) {
                return false;
            }
        }
        return true;
    }

    public boolean getDisabled() {
        MediaTrack[] activeTracks = getActiveTracks();
        if (activeTracks == null || ArrayExtensions.getLength((Object[]) activeTracks) <= 0) {
            return false;
        }
        for (MediaTrack disabled : activeTracks) {
            if (!disabled.getDisabled()) {
                return false;
            }
        }
        return true;
    }

    public String getExternalId() {
        return this._externalId;
    }

    public String getId() {
        return this.__id;
    }

    public boolean getMuted() {
        for (MediaTrack muted : getTracks()) {
            if (muted.getMuted()) {
                return true;
            }
        }
        return false;
    }

    public boolean getPaused() {
        MediaTrack[] activeTracks = getActiveTracks();
        if (activeTracks == null || ArrayExtensions.getLength((Object[]) activeTracks) <= 0) {
            return false;
        }
        for (MediaTrack paused : activeTracks) {
            if (!paused.getPaused()) {
                return false;
            }
        }
        return true;
    }

    public boolean getPersistent() {
        return this._persistent;
    }

    public String getPipelineJson() {
        ArrayList arrayList = new ArrayList();
        for (MediaTrack pipelineJson : getTracks()) {
            arrayList.add(pipelineJson.getPipelineJson());
        }
        return StringExtensions.concat("[", StringExtensions.join(", ", (String[]) arrayList.toArray(new String[0])), "]");
    }

    public TTrack getTrack() {
        TTrack[] tracks = getTracks();
        if (tracks == null || ArrayExtensions.getLength((Object[]) tracks) <= 0) {
            return getTrack();
        }
        return tracks[0];
    }

    public TTrack[] getTracks() {
        return arrayFromTracks(this.__tracks);
    }

    public MediaBranch(TTrack[] ttrackArr) {
        this._destroyed = false;
        this._destroyLock = new Object();
        if (ttrackArr != null) {
            for (TTrack ttrack : ttrackArr) {
                if (ttrack != null) {
                    this.__tracks.add(ttrack);
                }
            }
        }
    }

    public void setDeactivated(boolean z) {
        MediaTrack[] activeTracks = getActiveTracks();
        if (activeTracks != null) {
            for (MediaTrack deactivated : activeTracks) {
                deactivated.setDeactivated(z);
            }
        }
    }

    public void setExternalId(String str) {
        this._externalId = str;
    }

    public void setMuted(boolean z) {
        for (MediaTrack muted : getTracks()) {
            muted.setMuted(z);
        }
    }

    public void setPersistent(boolean z) {
        this._persistent = z;
    }

    public String toString() {
        return getLabel();
    }
}
