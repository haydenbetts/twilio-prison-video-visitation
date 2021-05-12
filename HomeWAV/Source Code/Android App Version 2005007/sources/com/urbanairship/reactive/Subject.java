package com.urbanairship.reactive;

import java.util.ArrayList;
import java.util.Iterator;

public class Subject<T> extends Observable<T> implements Observer<T> {
    private boolean completed = false;
    private Exception error;
    /* access modifiers changed from: private */
    public final ArrayList<Observer<T>> observers = new ArrayList<>();

    protected Subject() {
    }

    public static <T> Subject<T> create() {
        return new Subject<>();
    }

    /* access modifiers changed from: package-private */
    public synchronized boolean hasError() {
        return this.error != null;
    }

    /* access modifiers changed from: package-private */
    public synchronized boolean isCompleted() {
        return this.completed;
    }

    /* access modifiers changed from: package-private */
    public synchronized boolean hasObservers() {
        return this.observers.size() > 0;
    }

    public synchronized void onNext(T t) {
        Iterator it = new ArrayList(this.observers).iterator();
        while (it.hasNext()) {
            ((Observer) it.next()).onNext(t);
        }
    }

    public synchronized void onCompleted() {
        this.completed = true;
        Iterator it = new ArrayList(this.observers).iterator();
        while (it.hasNext()) {
            ((Observer) it.next()).onCompleted();
        }
    }

    public synchronized void onError(Exception exc) {
        this.error = exc;
        Iterator it = new ArrayList(this.observers).iterator();
        while (it.hasNext()) {
            ((Observer) it.next()).onError(exc);
        }
    }

    public synchronized Subscription subscribe(final Observer<T> observer) {
        if (!isCompleted() && !hasError()) {
            this.observers.add(observer);
        }
        return Subscription.create(new Runnable() {
            public void run() {
                if (Subject.this.hasObservers()) {
                    Subject.this.observers.remove(observer);
                }
            }
        });
    }
}
