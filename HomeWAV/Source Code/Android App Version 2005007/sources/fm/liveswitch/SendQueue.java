package fm.liveswitch;

import java.util.ArrayList;
import java.util.Iterator;

class SendQueue {
    private static ILog __log = Log.getLogger(SendQueue.class);
    private Object __lock = new Object();
    private ArrayList<SendItem> __pending = new ArrayList<>();
    private ISendQueueTransport __sendQueueTransport;
    private ArrayList<SendItem> __sending = new ArrayList<>();
    private TimeoutTimer _deferrer = null;

    public void clear() {
        ArrayList arrayList = new ArrayList();
        synchronized (this.__lock) {
            ArrayListExtensions.addRange(arrayList, this.__sending);
            ArrayListExtensions.addRange(arrayList, this.__pending);
            this.__sending.clear();
            this.__pending.clear();
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((SendItem) it.next()).getPromise().reject(new Exception("Request cancelled."));
        }
    }

    private void doSend(final SendItem sendItem) {
        this.__sendQueueTransport.send(sendItem).then(new IAction1<SendItem>() {
            public void invoke(SendItem sendItem) {
                SendQueue.this.sendItemSuccessOrRetry(sendItem);
            }
        }, (IAction1<Exception>) new IAction1<Exception>() {
            public void invoke(Exception exc) {
                SendQueue.this.sendItemFail(sendItem, exc);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public SendItem[] getPendingItems() {
        SendItem[] sendItemArr;
        synchronized (this.__lock) {
            sendItemArr = (SendItem[]) this.__pending.toArray(new SendItem[0]);
        }
        return sendItemArr;
    }

    /* access modifiers changed from: package-private */
    public SendItem[] getSendingItems() {
        SendItem[] sendItemArr;
        synchronized (this.__lock) {
            sendItemArr = (SendItem[]) this.__sending.toArray(new SendItem[0]);
        }
        return sendItemArr;
    }

    /* access modifiers changed from: private */
    public void retrySending(int i) {
        TimeoutTimer timeoutTimer = new TimeoutTimer(new IAction1<Object>() {
            public void invoke(Object obj) {
                SendQueue.this.trySendPending();
            }
        }, (Object) null);
        this._deferrer = timeoutTimer;
        try {
            timeoutTimer.start(i);
        } catch (Exception e) {
            Log.error("Could not start SendQueue deferrer timer.", e);
        }
    }

    public Future<Message> send(Message message) {
        SendItem sendItem = new SendItem();
        sendItem.setMessage(message);
        sendItem.setPromise(new Promise());
        sendItem.setSendBackoff(100);
        synchronized (this.__lock) {
            this.__pending.add(sendItem);
        }
        trySendPending();
        return sendItem.getPromise();
    }

    private void sendItemComplete(final SendItem sendItem) {
        if (sendItem.getLastInBatch()) {
            ManagedThread.dispatch(new IAction0() {
                public void invoke() {
                    int sendBackoff = sendItem.getSendBackoff();
                    SendItem sendItem = sendItem;
                    sendItem.setSendBackoff(MathAssistant.min(1000, sendItem.getSendBackoff() * 2));
                    SendQueue.this.retrySending(sendBackoff);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void sendItemFail(SendItem sendItem, Exception exc) {
        synchronized (this.__lock) {
            ArrayListExtensions.removeAt(this.__sending, 0);
            Iterator<SendItem> it = this.__sending.iterator();
            while (it.hasNext()) {
                SendItem next = it.next();
                next.setResendIndex(next.getResendIndex() - 1);
            }
        }
        __log.debug("An unrecoverable exception was encountered while sending a message. Will abandon message.", exc);
        sendItem.getPromise().reject(exc);
        sendItemComplete(sendItem);
    }

    /* access modifiers changed from: private */
    public void sendItemSuccessOrRetry(SendItem sendItem) {
        boolean z;
        boolean z2;
        if (!sendItem.getRetry()) {
            z2 = false;
            z = true;
        } else {
            z2 = sendItem.getSendCounter() < 50;
            z = false;
        }
        synchronized (this.__lock) {
            ArrayListExtensions.removeAt(this.__sending, 0);
            if (z2) {
                __log.debug("A recoverable exception was encountered while sending a message. Will attempt to resend after reconnection.", sendItem.getException());
                ArrayListExtensions.insert(this.__pending, sendItem.getResendIndex(), sendItem);
            } else {
                Iterator<SendItem> it = this.__sending.iterator();
                while (it.hasNext()) {
                    SendItem next = it.next();
                    next.setResendIndex(next.getResendIndex() - 1);
                }
            }
        }
        if (z) {
            sendItem.getPromise().resolve(sendItem.getMessage());
        } else if (!z2) {
            __log.debug("An unrecoverable exception was encountered while sending a message. Will abandon message.", sendItem.getException());
            sendItem.getPromise().reject(sendItem.getException());
        }
        sendItemComplete(sendItem);
    }

    public SendQueue(ISendQueueTransport iSendQueueTransport) {
        this.__sendQueueTransport = iSendQueueTransport;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003d, code lost:
        if (__log.getIsVerboseEnabled() == false) goto L_0x0056;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003f, code lost:
        __log.verbose(fm.liveswitch.StringExtensions.format("Starting flush of send queue with {0} items.", (java.lang.Object) fm.liveswitch.IntegerExtensions.toString(java.lang.Integer.valueOf(fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r1)))));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0056, code lost:
        r6.__sendQueueTransport.startBatch();
        r0 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0061, code lost:
        if (r0 >= fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r1)) goto L_0x0083;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0063, code lost:
        r3 = r1[r0];
        r3.setResendIndex(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x006d, code lost:
        if (r0 != (fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r1) - 1)) goto L_0x0071;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x006f, code lost:
        r5 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0071, code lost:
        r5 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0072, code lost:
        r3.setLastInBatch(r5);
        r3.setSendCounter(r3.getSendCounter() + 1);
        doSend(r3);
        r0 = r0 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0083, code lost:
        r6.__sendQueueTransport.endBatch();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x008e, code lost:
        if (__log.getIsVerboseEnabled() == false) goto L_0x00a7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0090, code lost:
        __log.verbose(fm.liveswitch.StringExtensions.format("Finished flush of send queue with {0} items.", (java.lang.Object) fm.liveswitch.IntegerExtensions.toString(java.lang.Integer.valueOf(fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r1)))));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00a7, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean trySendPending() {
        /*
            r6 = this;
            java.lang.Object r0 = r6.__lock
            monitor-enter(r0)
            fm.liveswitch.ISendQueueTransport r1 = r6.__sendQueueTransport     // Catch:{ all -> 0x00aa }
            boolean r1 = r1.getIsConnected()     // Catch:{ all -> 0x00aa }
            r2 = 0
            if (r1 != 0) goto L_0x000e
            monitor-exit(r0)     // Catch:{ all -> 0x00aa }
            return r2
        L_0x000e:
            java.util.ArrayList<fm.liveswitch.SendItem> r1 = r6.__sending     // Catch:{ all -> 0x00aa }
            int r1 = fm.liveswitch.ArrayListExtensions.getCount(r1)     // Catch:{ all -> 0x00aa }
            if (r1 > 0) goto L_0x00a8
            java.util.ArrayList<fm.liveswitch.SendItem> r1 = r6.__pending     // Catch:{ all -> 0x00aa }
            int r1 = fm.liveswitch.ArrayListExtensions.getCount(r1)     // Catch:{ all -> 0x00aa }
            if (r1 != 0) goto L_0x0020
            goto L_0x00a8
        L_0x0020:
            java.util.ArrayList<fm.liveswitch.SendItem> r1 = r6.__sending     // Catch:{ all -> 0x00aa }
            java.util.ArrayList<fm.liveswitch.SendItem> r3 = r6.__pending     // Catch:{ all -> 0x00aa }
            fm.liveswitch.ArrayListExtensions.addRange(r1, r3)     // Catch:{ all -> 0x00aa }
            java.util.ArrayList<fm.liveswitch.SendItem> r1 = r6.__pending     // Catch:{ all -> 0x00aa }
            r1.clear()     // Catch:{ all -> 0x00aa }
            java.util.ArrayList<fm.liveswitch.SendItem> r1 = r6.__sending     // Catch:{ all -> 0x00aa }
            fm.liveswitch.SendItem[] r3 = new fm.liveswitch.SendItem[r2]     // Catch:{ all -> 0x00aa }
            java.lang.Object[] r1 = r1.toArray(r3)     // Catch:{ all -> 0x00aa }
            fm.liveswitch.SendItem[] r1 = (fm.liveswitch.SendItem[]) r1     // Catch:{ all -> 0x00aa }
            monitor-exit(r0)     // Catch:{ all -> 0x00aa }
            fm.liveswitch.ILog r0 = __log
            boolean r0 = r0.getIsVerboseEnabled()
            if (r0 == 0) goto L_0x0056
            int r0 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r1)
            fm.liveswitch.ILog r3 = __log
            java.lang.String r4 = "Starting flush of send queue with {0} items."
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            java.lang.String r0 = fm.liveswitch.IntegerExtensions.toString(r0)
            java.lang.String r0 = fm.liveswitch.StringExtensions.format((java.lang.String) r4, (java.lang.Object) r0)
            r3.verbose(r0)
        L_0x0056:
            fm.liveswitch.ISendQueueTransport r0 = r6.__sendQueueTransport
            r0.startBatch()
            r0 = 0
        L_0x005c:
            int r3 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r1)
            r4 = 1
            if (r0 >= r3) goto L_0x0083
            r3 = r1[r0]
            r3.setResendIndex(r0)
            int r5 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r1)
            int r5 = r5 - r4
            if (r0 != r5) goto L_0x0071
            r5 = 1
            goto L_0x0072
        L_0x0071:
            r5 = 0
        L_0x0072:
            r3.setLastInBatch(r5)
            int r5 = r3.getSendCounter()
            int r5 = r5 + r4
            r3.setSendCounter(r5)
            r6.doSend(r3)
            int r0 = r0 + 1
            goto L_0x005c
        L_0x0083:
            fm.liveswitch.ISendQueueTransport r0 = r6.__sendQueueTransport
            r0.endBatch()
            fm.liveswitch.ILog r0 = __log
            boolean r0 = r0.getIsVerboseEnabled()
            if (r0 == 0) goto L_0x00a7
            int r0 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r1)
            fm.liveswitch.ILog r1 = __log
            java.lang.String r2 = "Finished flush of send queue with {0} items."
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            java.lang.String r0 = fm.liveswitch.IntegerExtensions.toString(r0)
            java.lang.String r0 = fm.liveswitch.StringExtensions.format((java.lang.String) r2, (java.lang.Object) r0)
            r1.verbose(r0)
        L_0x00a7:
            return r4
        L_0x00a8:
            monitor-exit(r0)     // Catch:{ all -> 0x00aa }
            return r2
        L_0x00aa:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00aa }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.SendQueue.trySendPending():boolean");
    }
}
