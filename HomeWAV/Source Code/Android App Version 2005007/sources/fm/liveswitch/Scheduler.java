package fm.liveswitch;

import java.util.HashMap;

class Scheduler {
    /* access modifiers changed from: private */
    public static ILog __log = Log.getLogger(Scheduler.class);
    private volatile boolean __active = false;
    /* access modifiers changed from: private */
    public ManagedCondition __callbackCondition = new ManagedCondition();
    private int __callbackCounter;
    private ManagedCondition __condition = new ManagedCondition();
    private Promise<Object> __exited;
    private Object __lock;
    private HashMap<String, ScheduledItem> __scheduledItems = new HashMap<>();
    private boolean __started = false;
    private ManagedThread __thread;

    private static int getUnset() {
        return -1;
    }

    static /* synthetic */ int access$306(Scheduler scheduler) {
        int i = scheduler.__callbackCounter - 1;
        scheduler.__callbackCounter = i;
        return i;
    }

    public void add(ScheduledItem scheduledItem) {
        synchronized (this.__condition) {
            if (!this.__scheduledItems.containsKey(scheduledItem.getId())) {
                scheduledItem.setNextInvocationTime(getCurrentTime() + ((long) scheduledItem.getDelay()));
                HashMapExtensions.set(HashMapExtensions.getItem(this.__scheduledItems), scheduledItem.getId(), scheduledItem);
                scheduledItem.setOnSuspendStatusChanged(new IActionDelegate1<ScheduledItem>() {
                    public String getId() {
                        return "fm.liveswitch.Scheduler.processSuspendedStatusChanged";
                    }

                    public void invoke(ScheduledItem scheduledItem) {
                        Scheduler.this.processSuspendedStatusChanged(scheduledItem);
                    }
                });
            } else if (scheduledItem.getNextInvocationTime() > getCurrentTime() + ((long) scheduledItem.getDelay())) {
                scheduledItem.setNextInvocationTime(getCurrentTime() + ((long) scheduledItem.getDelay()));
            }
            if (!scheduledItem.getSuspended()) {
                this.__condition.pulse();
            }
        }
    }

    private void executeCallback(final IAction1<ScheduledItem> iAction1, final ScheduledItem scheduledItem, final boolean z) {
        if (iAction1 != null) {
            ManagedThread.dispatch(new IAction0() {
                public void invoke() {
                    try {
                        iAction1.invoke(scheduledItem);
                    } catch (Exception e) {
                        Scheduler.__log.error("Scheduled callback threw unexpected exception.", e);
                    }
                    if (z) {
                        synchronized (Scheduler.this.__callbackCondition) {
                            if (Scheduler.access$306(Scheduler.this) <= 0) {
                                Scheduler.this.__callbackCondition.pulse();
                            }
                        }
                    }
                }
            });
        } else if (z) {
            synchronized (this.__callbackCondition) {
                int i = this.__callbackCounter - 1;
                this.__callbackCounter = i;
                if (i <= 0) {
                    this.__callbackCondition.pulse();
                }
            }
        }
    }

    public static long getCurrentTime() {
        return UnixTimestamp.getUtcNowMillis();
    }

    public boolean itemIsScheduled(ScheduledItem scheduledItem) {
        boolean containsKey;
        synchronized (this.__condition) {
            containsKey = this.__scheduledItems.containsKey(scheduledItem.getId());
        }
        return containsKey;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:100:0x01cf, code lost:
        r1.__callbackCounter = fm.liveswitch.ArrayListExtensions.getCount(r0);
        r4 = r0.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x01dd, code lost:
        if (r4.hasNext() == false) goto L_0x01ff;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x01df, code lost:
        r6 = (fm.liveswitch.ScheduledItem) r4.next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x01e5, code lost:
        if (r6 == null) goto L_0x01d9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x01e7, code lost:
        r9 = r6.getDefaultCallback();
        r6.setLastInvocationTime(r7);
        executeCallback(r9, r6, true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x01f5, code lost:
        if (r6.getRecordDetailedInvocationTimes() == false) goto L_0x01d9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x01f7, code lost:
        r6.setInvocationTime(r6.getInvocationCount(), r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x01ff, code lost:
        r0.clear();
        r4 = r2.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x020a, code lost:
        if (r4.hasNext() == false) goto L_0x021f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x020c, code lost:
        r6 = (fm.liveswitch.ScheduledItem) r4.next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x0212, code lost:
        if (r6 == null) goto L_0x021d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x0214, code lost:
        executeCallback(r6.getTimeoutCallback(), r6, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x021f, code lost:
        r2.clear();
        r4 = r1.__callbackCondition;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x0224, code lost:
        monitor-enter(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x0227, code lost:
        if (r1.__callbackCounter <= 0) goto L_0x022e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x0229, code lost:
        r1.__callbackCondition.halt();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x022e, code lost:
        monitor-exit(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x022f, code lost:
        r21.loopEnd();
        r4 = null;
     */
    /* JADX WARNING: Removed duplicated region for block: B:151:0x01b2 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00ec  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0105  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0107  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x010a A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x012b  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x018a  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x019e  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x01af  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void loop(fm.liveswitch.ManagedThread r21) {
        /*
            r20 = this;
            r1 = r20
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            r4 = 0
            r5 = r4
        L_0x0013:
            boolean r6 = r1.__active
            if (r6 == 0) goto L_0x023b
            r21.loopBegin()
            fm.liveswitch.ManagedCondition r6 = r1.__condition
            monitor-enter(r6)
            long r7 = getCurrentTime()     // Catch:{ all -> 0x0238 }
            int r9 = getUnset()     // Catch:{ all -> 0x0238 }
            java.util.HashMap<java.lang.String, fm.liveswitch.ScheduledItem> r10 = r1.__scheduledItems     // Catch:{ all -> 0x0238 }
            java.util.Set r10 = fm.liveswitch.HashMapExtensions.getKeys(r10)     // Catch:{ all -> 0x0238 }
            java.util.Iterator r10 = r10.iterator()     // Catch:{ all -> 0x0238 }
        L_0x002f:
            boolean r11 = r10.hasNext()     // Catch:{ all -> 0x0238 }
            r12 = 0
            if (r11 == 0) goto L_0x006c
            java.lang.Object r11 = r10.next()     // Catch:{ all -> 0x0238 }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ all -> 0x0238 }
            fm.liveswitch.Holder r13 = new fm.liveswitch.Holder     // Catch:{ all -> 0x0238 }
            r13.<init>(r5)     // Catch:{ all -> 0x0238 }
            java.util.HashMap<java.lang.String, fm.liveswitch.ScheduledItem> r5 = r1.__scheduledItems     // Catch:{ all -> 0x0238 }
            boolean r5 = fm.liveswitch.HashMapExtensions.tryGetValue(r5, r11, r13)     // Catch:{ all -> 0x0238 }
            java.lang.Object r11 = r13.getValue()     // Catch:{ all -> 0x0238 }
            fm.liveswitch.ScheduledItem r11 = (fm.liveswitch.ScheduledItem) r11     // Catch:{ all -> 0x0238 }
            if (r5 == 0) goto L_0x006a
            if (r11 == 0) goto L_0x006a
            boolean r5 = r11.getSuspended()     // Catch:{ all -> 0x0238 }
            if (r5 != 0) goto L_0x006a
            long r13 = r11.getNextInvocationTime()     // Catch:{ all -> 0x0238 }
            long r13 = r13 - r7
            int r5 = (int) r13     // Catch:{ all -> 0x0238 }
            int r5 = fm.liveswitch.MathAssistant.max((int) r12, (int) r5)     // Catch:{ all -> 0x0238 }
            int r12 = getUnset()     // Catch:{ all -> 0x0238 }
            if (r9 == r12) goto L_0x0069
            if (r9 <= r5) goto L_0x006a
        L_0x0069:
            r9 = r5
        L_0x006a:
            r5 = r11
            goto L_0x002f
        L_0x006c:
            int r7 = getUnset()     // Catch:{ all -> 0x0238 }
            if (r9 == r7) goto L_0x0078
            fm.liveswitch.ManagedCondition r7 = r1.__condition     // Catch:{ all -> 0x0238 }
            r7.halt(r9)     // Catch:{ all -> 0x0238 }
            goto L_0x007d
        L_0x0078:
            fm.liveswitch.ManagedCondition r7 = r1.__condition     // Catch:{ all -> 0x0238 }
            r7.halt()     // Catch:{ all -> 0x0238 }
        L_0x007d:
            boolean r7 = r1.__active     // Catch:{ all -> 0x0238 }
            if (r7 != 0) goto L_0x0088
            fm.liveswitch.Promise<java.lang.Object> r0 = r1.__exited     // Catch:{ all -> 0x0238 }
            r0.resolve(r4)     // Catch:{ all -> 0x0238 }
            monitor-exit(r6)     // Catch:{ all -> 0x0238 }
            return
        L_0x0088:
            long r7 = getCurrentTime()     // Catch:{ all -> 0x0238 }
            r0.clear()     // Catch:{ all -> 0x0238 }
            r2.clear()     // Catch:{ all -> 0x0238 }
            java.util.HashMap<java.lang.String, fm.liveswitch.ScheduledItem> r9 = r1.__scheduledItems     // Catch:{ all -> 0x0238 }
            java.util.Set r9 = fm.liveswitch.HashMapExtensions.getKeys(r9)     // Catch:{ all -> 0x0238 }
            java.util.Iterator r9 = r9.iterator()     // Catch:{ all -> 0x0238 }
        L_0x009c:
            boolean r10 = r9.hasNext()     // Catch:{ all -> 0x0238 }
            r11 = 1
            if (r10 == 0) goto L_0x01b7
            java.lang.Object r10 = r9.next()     // Catch:{ all -> 0x0238 }
            java.lang.String r10 = (java.lang.String) r10     // Catch:{ all -> 0x0238 }
            fm.liveswitch.Holder r13 = new fm.liveswitch.Holder     // Catch:{ all -> 0x0238 }
            r13.<init>(r5)     // Catch:{ all -> 0x0238 }
            java.util.HashMap<java.lang.String, fm.liveswitch.ScheduledItem> r5 = r1.__scheduledItems     // Catch:{ all -> 0x0238 }
            boolean r5 = fm.liveswitch.HashMapExtensions.tryGetValue(r5, r10, r13)     // Catch:{ all -> 0x0238 }
            java.lang.Object r10 = r13.getValue()     // Catch:{ all -> 0x0238 }
            fm.liveswitch.ScheduledItem r10 = (fm.liveswitch.ScheduledItem) r10     // Catch:{ all -> 0x0238 }
            if (r5 == 0) goto L_0x01b2
            if (r10 == 0) goto L_0x01b2
            boolean r5 = r10.getSuspended()     // Catch:{ all -> 0x0238 }
            if (r5 != 0) goto L_0x01b2
            int r5 = r10.getInvocationCount()     // Catch:{ all -> 0x0238 }
            if (r5 != 0) goto L_0x00cc
            r5 = 1
            goto L_0x00cd
        L_0x00cc:
            r5 = 0
        L_0x00cd:
            int r13 = r10.getInvocationCountLimit()     // Catch:{ all -> 0x0238 }
            int r14 = fm.liveswitch.ScheduledItem.getUnset()     // Catch:{ all -> 0x0238 }
            if (r13 == r14) goto L_0x00d9
            r13 = 1
            goto L_0x00da
        L_0x00d9:
            r13 = 0
        L_0x00da:
            if (r13 == 0) goto L_0x00e9
            int r14 = r10.getInvocationCountLimit()     // Catch:{ all -> 0x0238 }
            int r15 = r10.getInvocationCount()     // Catch:{ all -> 0x0238 }
            if (r14 <= r15) goto L_0x00e7
            goto L_0x00e9
        L_0x00e7:
            r14 = 0
            goto L_0x00ea
        L_0x00e9:
            r14 = 1
        L_0x00ea:
            if (r13 == 0) goto L_0x00fa
            int r13 = r10.getInvocationCountLimit()     // Catch:{ all -> 0x0238 }
            int r15 = r10.getInvocationCount()     // Catch:{ all -> 0x0238 }
            int r15 = r15 + r11
            if (r13 <= r15) goto L_0x00f8
            goto L_0x00fa
        L_0x00f8:
            r13 = 0
            goto L_0x00fb
        L_0x00fa:
            r13 = 1
        L_0x00fb:
            int r15 = r10.getInvocationLifetimeLimit()     // Catch:{ all -> 0x0238 }
            int r4 = fm.liveswitch.ScheduledItem.getUnset()     // Catch:{ all -> 0x0238 }
            if (r15 == r4) goto L_0x0107
            r4 = 1
            goto L_0x0108
        L_0x0107:
            r4 = 0
        L_0x0108:
            if (r4 == 0) goto L_0x0126
            if (r5 != 0) goto L_0x0126
            long r16 = r10.getOriginalInvocationTime()     // Catch:{ all -> 0x0238 }
            long r16 = r7 - r16
            long r18 = r10.getTotalSuspendedDuration()     // Catch:{ all -> 0x0238 }
            long r16 = r16 - r18
            int r15 = r10.getInvocationLifetimeLimit()     // Catch:{ all -> 0x0238 }
            r19 = r13
            long r12 = (long) r15     // Catch:{ all -> 0x0238 }
            int r15 = (r16 > r12 ? 1 : (r16 == r12 ? 0 : -1))
            if (r15 >= 0) goto L_0x0124
            goto L_0x0128
        L_0x0124:
            r12 = 0
            goto L_0x0129
        L_0x0126:
            r19 = r13
        L_0x0128:
            r12 = 1
        L_0x0129:
            if (r14 == 0) goto L_0x01a7
            if (r12 != 0) goto L_0x012f
            goto L_0x01a7
        L_0x012f:
            long r12 = r10.getNextInvocationTime()     // Catch:{ all -> 0x0238 }
            int r14 = (r12 > r7 ? 1 : (r12 == r7 ? 0 : -1))
            if (r14 > 0) goto L_0x01b2
            r0.add(r10)     // Catch:{ all -> 0x0238 }
            if (r5 == 0) goto L_0x013f
            r10.setOriginalInvocationTime(r7)     // Catch:{ all -> 0x0238 }
        L_0x013f:
            int r5 = r10.getInterval()     // Catch:{ all -> 0x0238 }
            float r5 = (float) r5     // Catch:{ all -> 0x0238 }
            float r12 = r10.getIntervalBackoffMultiplier()     // Catch:{ all -> 0x0238 }
            float r5 = r5 * r12
            double r12 = (double) r5     // Catch:{ all -> 0x0238 }
            double r12 = fm.liveswitch.MathAssistant.floor(r12)     // Catch:{ all -> 0x0238 }
            int r5 = (int) r12     // Catch:{ all -> 0x0238 }
            r10.setInterval(r5)     // Catch:{ all -> 0x0238 }
            int r5 = r10.getInvocationCount()     // Catch:{ all -> 0x0238 }
            int r5 = r5 + r11
            r10.setInvocationCount(r5)     // Catch:{ all -> 0x0238 }
            if (r4 == 0) goto L_0x0179
            long r4 = r10.getOriginalInvocationTime()     // Catch:{ all -> 0x0238 }
            long r4 = r7 - r4
            long r12 = r10.getTotalSuspendedDuration()     // Catch:{ all -> 0x0238 }
            long r4 = r4 - r12
            int r12 = r10.getInvocationLifetimeLimit()     // Catch:{ all -> 0x0238 }
            int r13 = r10.getInterval()     // Catch:{ all -> 0x0238 }
            int r12 = r12 - r13
            long r12 = (long) r12     // Catch:{ all -> 0x0238 }
            int r14 = (r4 > r12 ? 1 : (r4 == r12 ? 0 : -1))
            if (r14 > 0) goto L_0x0177
            goto L_0x0179
        L_0x0177:
            r4 = 0
            goto L_0x017a
        L_0x0179:
            r4 = 1
        L_0x017a:
            if (r4 == 0) goto L_0x0188
            if (r19 == 0) goto L_0x0188
            int r4 = r10.getInterval()     // Catch:{ all -> 0x0238 }
            long r4 = (long) r4     // Catch:{ all -> 0x0238 }
            long r4 = r4 + r7
            r10.setNextInvocationTime(r4)     // Catch:{ all -> 0x0238 }
            goto L_0x01b2
        L_0x0188:
            if (r4 != 0) goto L_0x019e
            r10.setLastDefaultInvocationOccurred(r11)     // Catch:{ all -> 0x0238 }
            long r4 = r10.getOriginalInvocationTime()     // Catch:{ all -> 0x0238 }
            int r11 = r10.getInvocationLifetimeLimit()     // Catch:{ all -> 0x0238 }
            long r11 = (long) r11     // Catch:{ all -> 0x0238 }
            long r4 = r4 + r11
            r11 = 1
            long r4 = r4 + r11
            r10.setNextInvocationTime(r4)     // Catch:{ all -> 0x0238 }
            goto L_0x01b2
        L_0x019e:
            if (r19 != 0) goto L_0x01b2
            r3.add(r10)     // Catch:{ all -> 0x0238 }
            r10.setLastDefaultInvocationOccurred(r11)     // Catch:{ all -> 0x0238 }
            goto L_0x01b2
        L_0x01a7:
            r3.add(r10)     // Catch:{ all -> 0x0238 }
            r10.setLastDefaultInvocationOccurred(r11)     // Catch:{ all -> 0x0238 }
            if (r12 != 0) goto L_0x01b2
            r2.add(r10)     // Catch:{ all -> 0x0238 }
        L_0x01b2:
            r5 = r10
            r4 = 0
            r12 = 0
            goto L_0x009c
        L_0x01b7:
            java.util.Iterator r4 = r3.iterator()     // Catch:{ all -> 0x0238 }
        L_0x01bb:
            boolean r9 = r4.hasNext()     // Catch:{ all -> 0x0238 }
            if (r9 == 0) goto L_0x01cb
            java.lang.Object r9 = r4.next()     // Catch:{ all -> 0x0238 }
            fm.liveswitch.ScheduledItem r9 = (fm.liveswitch.ScheduledItem) r9     // Catch:{ all -> 0x0238 }
            r1.remove(r9)     // Catch:{ all -> 0x0238 }
            goto L_0x01bb
        L_0x01cb:
            r3.clear()     // Catch:{ all -> 0x0238 }
            monitor-exit(r6)     // Catch:{ all -> 0x0238 }
            int r4 = fm.liveswitch.ArrayListExtensions.getCount(r0)
            r1.__callbackCounter = r4
            java.util.Iterator r4 = r0.iterator()
        L_0x01d9:
            boolean r6 = r4.hasNext()
            if (r6 == 0) goto L_0x01ff
            java.lang.Object r6 = r4.next()
            fm.liveswitch.ScheduledItem r6 = (fm.liveswitch.ScheduledItem) r6
            if (r6 == 0) goto L_0x01d9
            fm.liveswitch.IAction1 r9 = r6.getDefaultCallback()
            r6.setLastInvocationTime(r7)
            r1.executeCallback(r9, r6, r11)
            boolean r9 = r6.getRecordDetailedInvocationTimes()
            if (r9 == 0) goto L_0x01d9
            int r9 = r6.getInvocationCount()
            r6.setInvocationTime(r9, r7)
            goto L_0x01d9
        L_0x01ff:
            r0.clear()
            java.util.Iterator r4 = r2.iterator()
        L_0x0206:
            boolean r6 = r4.hasNext()
            if (r6 == 0) goto L_0x021f
            java.lang.Object r6 = r4.next()
            fm.liveswitch.ScheduledItem r6 = (fm.liveswitch.ScheduledItem) r6
            if (r6 == 0) goto L_0x021d
            fm.liveswitch.IAction1 r7 = r6.getTimeoutCallback()
            r8 = 0
            r1.executeCallback(r7, r6, r8)
            goto L_0x0206
        L_0x021d:
            r8 = 0
            goto L_0x0206
        L_0x021f:
            r2.clear()
            fm.liveswitch.ManagedCondition r4 = r1.__callbackCondition
            monitor-enter(r4)
            int r6 = r1.__callbackCounter     // Catch:{ all -> 0x0235 }
            if (r6 <= 0) goto L_0x022e
            fm.liveswitch.ManagedCondition r6 = r1.__callbackCondition     // Catch:{ all -> 0x0235 }
            r6.halt()     // Catch:{ all -> 0x0235 }
        L_0x022e:
            monitor-exit(r4)     // Catch:{ all -> 0x0235 }
            r21.loopEnd()
            r4 = 0
            goto L_0x0013
        L_0x0235:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0235 }
            throw r0
        L_0x0238:
            r0 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x0238 }
            throw r0
        L_0x023b:
            fm.liveswitch.Promise<java.lang.Object> r0 = r1.__exited
            r2 = 0
            r0.resolve(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.Scheduler.loop(fm.liveswitch.ManagedThread):void");
    }

    /* access modifiers changed from: private */
    public void processSuspendedStatusChanged(ScheduledItem scheduledItem) {
        if (scheduledItem.getSuspended()) {
            scheduledItem.setLastSuspendedTime(getCurrentTime());
            return;
        }
        synchronized (this.__condition) {
            scheduledItem.setLastResumedTime(getCurrentTime());
            if (scheduledItem.getLastSuspendedTime() != ((long) ScheduledItem.getUnset())) {
                long lastResumedTime = scheduledItem.getLastResumedTime() - scheduledItem.getLastSuspendedTime();
                if (lastResumedTime > 0 && scheduledItem.getInvocationCount() > 0) {
                    scheduledItem.setTotalSuspendedDuration(scheduledItem.getTotalSuspendedDuration() + lastResumedTime);
                    scheduledItem.setNextInvocationTime(scheduledItem.getNextInvocationTime() + lastResumedTime);
                }
            }
            this.__condition.pulse();
        }
    }

    public boolean remove(ScheduledItem scheduledItem) {
        if (scheduledItem == null) {
            return false;
        }
        synchronized (this.__condition) {
            String id = scheduledItem.getId();
            Holder holder = new Holder(scheduledItem);
            boolean tryGetValue = HashMapExtensions.tryGetValue(this.__scheduledItems, id, holder);
            ScheduledItem scheduledItem2 = (ScheduledItem) holder.getValue();
            if (!tryGetValue) {
                return false;
            }
            scheduledItem2.setOnSuspendStatusChanged((IAction1<ScheduledItem>) null);
            HashMapExtensions.remove(this.__scheduledItems, id);
            return true;
        }
    }

    public Scheduler(Object obj) {
        this.__lock = obj;
    }

    public boolean start() {
        synchronized (this.__lock) {
            if (this.__started) {
                return false;
            }
            this.__started = true;
            this.__active = true;
            ManagedThread managedThread = new ManagedThread(new IActionDelegate1<ManagedThread>() {
                public String getId() {
                    return "fm.liveswitch.Scheduler.loop";
                }

                public void invoke(ManagedThread managedThread) {
                    Scheduler.this.loop(managedThread);
                }
            });
            this.__thread = managedThread;
            managedThread.start();
            return true;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001b, code lost:
        monitor-enter(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001e, code lost:
        if (r4.__active != false) goto L_0x0031;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0020, code lost:
        r0 = new fm.liveswitch.Promise();
        r0.reject(new java.lang.Exception("Scheduler has already been stopped."));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002f, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0030, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0031, code lost:
        r4.__exited = new fm.liveswitch.Promise<>();
        r4.__active = false;
        r4.__condition.pulse();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0040, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0043, code lost:
        return r4.__exited;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0019, code lost:
        r1 = r4.__condition;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public fm.liveswitch.Future<java.lang.Object> stop() {
        /*
            r4 = this;
            java.lang.Object r0 = r4.__lock
            monitor-enter(r0)
            boolean r1 = r4.__started     // Catch:{ all -> 0x0047 }
            if (r1 != 0) goto L_0x0018
            fm.liveswitch.Promise r1 = new fm.liveswitch.Promise     // Catch:{ all -> 0x0047 }
            r1.<init>()     // Catch:{ all -> 0x0047 }
            java.lang.Exception r2 = new java.lang.Exception     // Catch:{ all -> 0x0047 }
            java.lang.String r3 = "Scheduler has never been started."
            r2.<init>(r3)     // Catch:{ all -> 0x0047 }
            r1.reject(r2)     // Catch:{ all -> 0x0047 }
            monitor-exit(r0)     // Catch:{ all -> 0x0047 }
            return r1
        L_0x0018:
            monitor-exit(r0)     // Catch:{ all -> 0x0047 }
            fm.liveswitch.ManagedCondition r1 = r4.__condition
            monitor-enter(r1)
            boolean r0 = r4.__active     // Catch:{ all -> 0x0044 }
            if (r0 != 0) goto L_0x0031
            fm.liveswitch.Promise r0 = new fm.liveswitch.Promise     // Catch:{ all -> 0x0044 }
            r0.<init>()     // Catch:{ all -> 0x0044 }
            java.lang.Exception r2 = new java.lang.Exception     // Catch:{ all -> 0x0044 }
            java.lang.String r3 = "Scheduler has already been stopped."
            r2.<init>(r3)     // Catch:{ all -> 0x0044 }
            r0.reject(r2)     // Catch:{ all -> 0x0044 }
            monitor-exit(r1)     // Catch:{ all -> 0x0044 }
            return r0
        L_0x0031:
            fm.liveswitch.Promise r0 = new fm.liveswitch.Promise     // Catch:{ all -> 0x0044 }
            r0.<init>()     // Catch:{ all -> 0x0044 }
            r4.__exited = r0     // Catch:{ all -> 0x0044 }
            r0 = 0
            r4.__active = r0     // Catch:{ all -> 0x0044 }
            fm.liveswitch.ManagedCondition r0 = r4.__condition     // Catch:{ all -> 0x0044 }
            r0.pulse()     // Catch:{ all -> 0x0044 }
            monitor-exit(r1)     // Catch:{ all -> 0x0044 }
            fm.liveswitch.Promise<java.lang.Object> r0 = r4.__exited
            return r0
        L_0x0044:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0044 }
            throw r0
        L_0x0047:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0047 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.Scheduler.stop():fm.liveswitch.Future");
    }

    public void trigger(ScheduledItem scheduledItem) {
        synchronized (this.__condition) {
            remove(scheduledItem);
            scheduledItem.setDelay(0);
            add(scheduledItem);
        }
    }
}
