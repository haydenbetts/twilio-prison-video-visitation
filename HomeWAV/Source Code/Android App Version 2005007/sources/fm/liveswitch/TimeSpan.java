package fm.liveswitch;

import androidx.work.WorkRequest;
import com.urbanairship.automation.tags.TagGroupLookupResponseCache;

public class TimeSpan {
    int _hours;
    int _milliseconds;
    int _minutes;
    int _seconds;

    public TimeSpan(long j) {
        long j2 = j / WorkRequest.MIN_BACKOFF_MILLIS;
        int i = (int) (j2 % 1000);
        this._milliseconds = i;
        long j3 = (j2 - ((long) i)) / 1000;
        int i2 = (int) (j3 % 60);
        this._seconds = i2;
        long j4 = (j3 - ((long) i2)) / 60;
        int i3 = (int) (j4 % 60);
        this._minutes = i3;
        this._hours = (int) ((j4 - ((long) i3)) / 60);
    }

    public TimeSpan(int i, int i2, int i3) {
        this._hours = i;
        this._minutes = i2;
        this._seconds = i3;
    }

    public double getTotalSeconds() {
        return (double) ((((long) this._hours) * 3600) + (((long) this._minutes) * 60) + ((long) this._seconds));
    }

    public double getTotalMilliseconds() {
        return (double) ((((long) this._hours) * TagGroupLookupResponseCache.DEFAULT_STALE_READ_TIME_MS) + (((long) this._minutes) * 60000) + (((long) this._seconds) * 1000) + ((long) this._milliseconds));
    }
}
