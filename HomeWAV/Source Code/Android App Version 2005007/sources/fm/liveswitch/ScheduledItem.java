package fm.liveswitch;

import java.util.HashMap;

class ScheduledItem {
    private HashMap<Integer, Long> __invocationTimes = new HashMap<>();
    private long __lastInvocationTime = ((long) getUnset());
    private boolean __suspended;
    private IAction1<ScheduledItem> _defaultCallback;
    private int _delay;
    private String _id;
    private int _interval;
    private float _intervalBackoffMultiplier;
    private int _invocationCount;
    private int _invocationCountLimit;
    private int _invocationLifetimeLimit;
    private boolean _lastDefaultInvocationOccurred;
    private long _lastResumedTime;
    private long _lastSuspendedTime;
    private long _nextInvocationTime;
    private IAction1<ScheduledItem> _onSuspendStatusChanged;
    private long _originalInvocationTime;
    private boolean _recordDetailedInvocationTimes;
    private Object _state;
    private IAction1<ScheduledItem> _timeoutCallback;
    private long _totalSuspendedDuration;

    public static int getUnset() {
        return -1;
    }

    public IAction1<ScheduledItem> getDefaultCallback() {
        return this._defaultCallback;
    }

    public int getDelay() {
        return this._delay;
    }

    public String getId() {
        return this._id;
    }

    public int getInterval() {
        return this._interval;
    }

    public float getIntervalBackoffMultiplier() {
        return this._intervalBackoffMultiplier;
    }

    public int getInvocationCount() {
        return this._invocationCount;
    }

    public int getInvocationCountLimit() {
        return this._invocationCountLimit;
    }

    public int getInvocationLifetimeLimit() {
        return this._invocationLifetimeLimit;
    }

    public long getInvocationTime(int i) {
        if (this.__invocationTimes.containsKey(Integer.valueOf(i))) {
            return HashMapExtensions.getItem(this.__invocationTimes).get(Integer.valueOf(i)).longValue();
        }
        return -1;
    }

    public boolean getLastDefaultInvocationOccurred() {
        return this._lastDefaultInvocationOccurred;
    }

    /* access modifiers changed from: package-private */
    public long getLastInvocationTime() {
        return this.__lastInvocationTime;
    }

    public long getLastResumedTime() {
        return this._lastResumedTime;
    }

    public long getLastSuspendedTime() {
        return this._lastSuspendedTime;
    }

    /* access modifiers changed from: package-private */
    public long getNextInvocationTime() {
        return this._nextInvocationTime;
    }

    public IAction1<ScheduledItem> getOnSuspendStatusChanged() {
        return this._onSuspendStatusChanged;
    }

    /* access modifiers changed from: package-private */
    public long getOriginalInvocationTime() {
        return this._originalInvocationTime;
    }

    public boolean getRecordDetailedInvocationTimes() {
        return this._recordDetailedInvocationTimes;
    }

    public Object getState() {
        return this._state;
    }

    public boolean getSuspended() {
        return this.__suspended;
    }

    public IAction1<ScheduledItem> getTimeoutCallback() {
        return this._timeoutCallback;
    }

    public long getTotalSuspendedDuration() {
        return this._totalSuspendedDuration;
    }

    public void reset() {
        setDefaultCallback((IAction1<ScheduledItem>) null);
        setState((Object) null);
        setOriginalInvocationTime((long) getUnset());
        setInvocationCount(0);
        setDelay(0);
        setInvocationCountLimit(getUnset());
        setInvocationLifetimeLimit(getUnset());
        setNextInvocationTime((long) getUnset());
        setIntervalBackoffMultiplier(1.0f);
        setLastDefaultInvocationOccurred(false);
        setSuspended(false);
        setLastResumedTime((long) getUnset());
        setLastSuspendedTime((long) getUnset());
        setTotalSuspendedDuration(0);
        this.__invocationTimes.clear();
    }

    public ScheduledItem(IAction1<ScheduledItem> iAction1, int i, int i2, int i3, int i4) {
        setId(Utility.generateId());
        setRecordDetailedInvocationTimes(false);
        setDefaultCallback(iAction1);
        setInterval(i2);
        setDelay(i);
        setInvocationLifetimeLimit(i3);
        setInvocationCountLimit(i4);
        setInvocationCount(0);
        setIntervalBackoffMultiplier(1.0f);
        setOriginalInvocationTime((long) getUnset());
        setLastDefaultInvocationOccurred(false);
        setSuspended(false);
        setLastResumedTime((long) getUnset());
        setLastSuspendedTime((long) getUnset());
        setTotalSuspendedDuration(0);
    }

    public void setDefaultCallback(IAction1<ScheduledItem> iAction1) {
        this._defaultCallback = iAction1;
    }

    public void setDelay(int i) {
        this._delay = i;
    }

    private void setId(String str) {
        this._id = str;
    }

    public void setInterval(int i) {
        this._interval = i;
    }

    public void setIntervalBackoffMultiplier(float f) {
        this._intervalBackoffMultiplier = f;
    }

    /* access modifiers changed from: package-private */
    public void setInvocationCount(int i) {
        this._invocationCount = i;
    }

    public void setInvocationCountLimit(int i) {
        this._invocationCountLimit = i;
    }

    public void setInvocationLifetimeLimit(int i) {
        this._invocationLifetimeLimit = i;
    }

    public void setInvocationTime(int i, long j) {
        HashMapExtensions.set(HashMapExtensions.getItem(this.__invocationTimes), Integer.valueOf(i), Long.valueOf(j));
    }

    /* access modifiers changed from: package-private */
    public void setLastDefaultInvocationOccurred(boolean z) {
        this._lastDefaultInvocationOccurred = z;
    }

    /* access modifiers changed from: package-private */
    public void setLastInvocationTime(long j) {
        this.__lastInvocationTime = j;
    }

    public void setLastResumedTime(long j) {
        this._lastResumedTime = j;
    }

    public void setLastSuspendedTime(long j) {
        this._lastSuspendedTime = j;
    }

    /* access modifiers changed from: package-private */
    public void setNextInvocationTime(long j) {
        this._nextInvocationTime = j;
    }

    public void setOnSuspendStatusChanged(IAction1<ScheduledItem> iAction1) {
        this._onSuspendStatusChanged = iAction1;
    }

    /* access modifiers changed from: package-private */
    public void setOriginalInvocationTime(long j) {
        this._originalInvocationTime = j;
    }

    public void setRecordDetailedInvocationTimes(boolean z) {
        this._recordDetailedInvocationTimes = z;
    }

    public void setState(Object obj) {
        this._state = obj;
    }

    public void setSuspended(boolean z) {
        if (!Global.equals(Boolean.valueOf(this.__suspended), Boolean.valueOf(z))) {
            this.__suspended = z;
            IAction1<ScheduledItem> onSuspendStatusChanged = getOnSuspendStatusChanged();
            if (onSuspendStatusChanged != null) {
                onSuspendStatusChanged.invoke(this);
            }
        }
    }

    public void setTimeoutCallback(IAction1<ScheduledItem> iAction1) {
        this._timeoutCallback = iAction1;
    }

    public void setTotalSuspendedDuration(long j) {
        this._totalSuspendedDuration = j;
    }
}
