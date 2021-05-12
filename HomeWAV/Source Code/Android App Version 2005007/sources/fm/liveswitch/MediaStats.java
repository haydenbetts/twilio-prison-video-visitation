package fm.liveswitch;

import java.util.HashMap;

public class MediaStats extends BaseStats {
    private MediaTrackStats[] _tracks;

    /* access modifiers changed from: protected */
    public void deserializeProperties(String str, String str2) {
        super.deserializeProperties(str, str2);
        if (str != null && Global.equals(str, "tracks")) {
            setTracks(MediaTrackStats.fromJsonArray(str2));
        }
    }

    public static MediaStats fromJson(String str) {
        return (MediaStats) JsonSerializer.deserializeObject(str, new IFunction0<MediaStats>() {
            public MediaStats invoke() {
                return new MediaStats();
            }
        }, new IAction3<MediaStats, String, String>() {
            public void invoke(MediaStats mediaStats, String str, String str2) {
                mediaStats.deserializeProperties(str, str2);
            }
        });
    }

    public MediaTrackStats getTrack() {
        return (MediaTrackStats) Utility.firstOrDefault((T[]) getTracks());
    }

    public MediaTrackStats getTrack(String str) {
        for (MediaTrackStats mediaTrackStats : getTracks()) {
            if (Global.equals(mediaTrackStats.getId(), str)) {
                return mediaTrackStats;
            }
        }
        return null;
    }

    public MediaTrackStats[] getTracks() {
        return this._tracks;
    }

    /* access modifiers changed from: protected */
    public void serializeProperties(HashMap<String, String> hashMap) {
        super.serializeProperties(hashMap);
        if (getTracks() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "tracks", MediaTrackStats.toJsonArray(getTracks()));
        }
    }

    /* access modifiers changed from: package-private */
    public void setTracks(MediaTrackStats[] mediaTrackStatsArr) {
        this._tracks = mediaTrackStatsArr;
    }

    public static String toJson(MediaStats mediaStats) {
        return JsonSerializer.serializeObject(mediaStats, new IAction2<MediaStats, HashMap<String, String>>() {
            public void invoke(MediaStats mediaStats, HashMap<String, String> hashMap) {
                mediaStats.serializeProperties(hashMap);
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }
}
