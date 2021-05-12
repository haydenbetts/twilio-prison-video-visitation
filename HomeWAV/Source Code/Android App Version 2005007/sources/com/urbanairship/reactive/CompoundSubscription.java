package com.urbanairship.reactive;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CompoundSubscription extends Subscription {
    private final List<Subscription> subscriptions = new ArrayList();

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0019, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void add(com.urbanairship.reactive.Subscription r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r2.isCancelled()     // Catch:{ all -> 0x001a }
            if (r0 == 0) goto L_0x0009
            monitor-exit(r1)
            return
        L_0x0009:
            boolean r0 = r1.isCancelled()     // Catch:{ all -> 0x001a }
            if (r0 == 0) goto L_0x0013
            r2.cancel()     // Catch:{ all -> 0x001a }
            goto L_0x0018
        L_0x0013:
            java.util.List<com.urbanairship.reactive.Subscription> r0 = r1.subscriptions     // Catch:{ all -> 0x001a }
            r0.add(r2)     // Catch:{ all -> 0x001a }
        L_0x0018:
            monitor-exit(r1)
            return
        L_0x001a:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.urbanairship.reactive.CompoundSubscription.add(com.urbanairship.reactive.Subscription):void");
    }

    public synchronized void remove(Subscription subscription) {
        if (!isCancelled()) {
            this.subscriptions.remove(subscription);
        }
    }

    public synchronized void cancel() {
        Iterator it = new ArrayList(this.subscriptions).iterator();
        while (it.hasNext()) {
            Subscription subscription = (Subscription) it.next();
            subscription.cancel();
            this.subscriptions.remove(subscription);
        }
        super.cancel();
    }
}
