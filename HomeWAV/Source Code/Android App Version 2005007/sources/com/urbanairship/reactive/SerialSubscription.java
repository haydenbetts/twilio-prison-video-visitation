package com.urbanairship.reactive;

public class SerialSubscription extends Subscription {
    private Subscription subscription;

    public synchronized void setSubscription(Subscription subscription2) {
        if (!isCancelled()) {
            this.subscription = subscription2;
        } else {
            subscription2.cancel();
        }
    }

    public synchronized void cancel() {
        Subscription subscription2 = this.subscription;
        if (!isCancelled()) {
            super.cancel();
            this.subscription = null;
        }
        if (subscription2 != null) {
            subscription2.cancel();
        }
    }
}
