package com.urbanairship.reactive;

import com.urbanairship.Predicate;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Observable<T> {
    protected final Function<Observer<T>, Subscription> onSubscribe;

    protected Observable() {
        this((Function) null);
    }

    protected Observable(Function<Observer<T>, Subscription> function) {
        this.onSubscribe = function;
    }

    public static <T> Observable<T> create(Function<Observer<T>, Subscription> function) {
        return new Observable<>(function);
    }

    public static <T> Observable<T> just(final T t) {
        return create(new Function<Observer<T>, Subscription>() {
            public Subscription apply(Observer<T> observer) {
                observer.onNext(t);
                observer.onCompleted();
                return Subscription.empty();
            }
        });
    }

    public static <T> Observable<T> empty() {
        return create(new Function<Observer<T>, Subscription>() {
            public Subscription apply(Observer<T> observer) {
                observer.onCompleted();
                return Subscription.empty();
            }
        });
    }

    public static <T> Observable<T> never() {
        return create(new Function<Observer<T>, Subscription>() {
            public Subscription apply(Observer<T> observer) {
                return Subscription.empty();
            }
        });
    }

    public static <T> Observable<T> error(final Exception exc) {
        return create(new Function<Observer<T>, Subscription>() {
            public Subscription apply(Observer<T> observer) {
                observer.onError(exc);
                return Subscription.empty();
            }
        });
    }

    public static <T> Observable<T> from(final Collection<T> collection) {
        return create(new Function<Observer<T>, Subscription>() {
            public Subscription apply(Observer<T> observer) {
                for (Object onNext : collection) {
                    observer.onNext(onNext);
                }
                observer.onCompleted();
                return Subscription.empty();
            }
        });
    }

    public Subscription subscribe(Observer<T> observer) {
        Function<Observer<T>, Subscription> function = this.onSubscribe;
        if (function != null) {
            return function.apply(observer);
        }
        return Subscription.empty();
    }

    public <R> Observable<R> flatMap(final Function<T, Observable<R>> function) {
        return bind(new Function<T, Observable<R>>() {
            public Observable<R> apply(T t) {
                return (Observable) function.apply(t);
            }
        });
    }

    public <R> Observable<R> map(final Function<T, R> function) {
        return flatMap(new Function<T, Observable<R>>() {
            public Observable<R> apply(T t) {
                return Observable.just(function.apply(t));
            }
        });
    }

    public Observable<T> filter(final Predicate<T> predicate) {
        return flatMap(new Function<T, Observable<T>>() {
            public Observable<T> apply(T t) {
                if (predicate.apply(t)) {
                    return Observable.just(t);
                }
                return Observable.empty();
            }
        });
    }

    public Observable<T> distinctUntilChanged() {
        final Holder holder = new Holder();
        return bind(new Function<T, Observable<T>>() {
            public Observable<T> apply(T t) {
                if (holder.getValue() != null && t.equals(holder.getValue())) {
                    return Observable.empty();
                }
                holder.setValue(t);
                return Observable.just(t);
            }
        });
    }

    public Observable<T> defaultIfEmpty(final T t) {
        return create(new Function<Observer<T>, Subscription>() {
            public Subscription apply(final Observer<T> observer) {
                final AtomicBoolean atomicBoolean = new AtomicBoolean(true);
                return Observable.this.subscribe(new Observer<T>() {
                    public void onNext(T t) {
                        observer.onNext(t);
                        atomicBoolean.set(false);
                    }

                    public void onCompleted() {
                        if (atomicBoolean.get()) {
                            observer.onNext(t);
                        }
                        observer.onCompleted();
                    }

                    public void onError(Exception exc) {
                        observer.onCompleted();
                    }
                });
            }
        });
    }

    public Observable<T> observeOn(final Scheduler scheduler) {
        return create(new Function<Observer<T>, Subscription>() {
            public Subscription apply(final Observer<T> observer) {
                final SerialSubscription serialSubscription = new SerialSubscription();
                serialSubscription.setSubscription(Observable.this.subscribe(new Observer<T>() {
                    public void onNext(final T t) {
                        scheduler.schedule(new Runnable() {
                            public void run() {
                                if (!serialSubscription.isCancelled()) {
                                    observer.onNext(t);
                                }
                            }
                        });
                    }

                    public void onCompleted() {
                        scheduler.schedule(new Runnable() {
                            public void run() {
                                if (!serialSubscription.isCancelled()) {
                                    observer.onCompleted();
                                }
                            }
                        });
                    }

                    public void onError(final Exception exc) {
                        scheduler.schedule(new Runnable() {
                            public void run() {
                                if (!serialSubscription.isCancelled()) {
                                    observer.onError(exc);
                                }
                            }
                        });
                    }
                }));
                return serialSubscription;
            }
        });
    }

    public Observable<T> subscribeOn(final Scheduler scheduler) {
        return create(new Function<Observer<T>, Subscription>() {
            public Subscription apply(final Observer<T> observer) {
                final CompoundSubscription compoundSubscription = new CompoundSubscription();
                compoundSubscription.add(scheduler.schedule(new Runnable() {
                    public void run() {
                        compoundSubscription.add(Observable.this.subscribe(observer));
                    }
                }));
                return compoundSubscription;
            }
        });
    }

    public static <T> Observable<T> merge(Observable<T> observable, final Observable<T> observable2) {
        return create(new Function<Observer<T>, Subscription>(observable) {
            final /* synthetic */ Observable val$lh;

            {
                this.val$lh = r1;
            }

            public Subscription apply(final Observer<T> observer) {
                final AtomicInteger atomicInteger = new AtomicInteger(0);
                final CompoundSubscription compoundSubscription = new CompoundSubscription();
                AnonymousClass1 r2 = new Observer<T>() {
                    public void onNext(T t) {
                        synchronized (observer) {
                            observer.onNext(t);
                        }
                    }

                    public void onCompleted() {
                        synchronized (observer) {
                            if (atomicInteger.incrementAndGet() == 2) {
                                observer.onCompleted();
                            }
                        }
                    }

                    public void onError(Exception exc) {
                        synchronized (observer) {
                            compoundSubscription.cancel();
                            observer.onError(exc);
                        }
                    }
                };
                compoundSubscription.add(this.val$lh.subscribe(r2));
                compoundSubscription.add(observable2.subscribe(r2));
                return compoundSubscription;
            }
        });
    }

    public static <T> Observable<T> merge(Collection<Observable<T>> collection) {
        Observable<T> empty = empty();
        for (Observable<T> merge : collection) {
            empty = merge(empty, merge);
        }
        return empty;
    }

    public static <T> Observable<T> concat(final Observable<T> observable, final Observable<T> observable2) {
        final CompoundSubscription compoundSubscription = new CompoundSubscription();
        return create(new Function<Observer<T>, Subscription>() {
            public Subscription apply(final Observer<T> observer) {
                compoundSubscription.add(observable.subscribe(new Observer<T>() {
                    public void onNext(T t) {
                        observer.onNext(t);
                    }

                    public void onCompleted() {
                        compoundSubscription.add(observable2.subscribe(observer));
                    }

                    public void onError(Exception exc) {
                        observer.onError(exc);
                    }
                }));
                return Subscription.create(new Runnable() {
                    public void run() {
                        compoundSubscription.cancel();
                    }
                });
            }
        });
    }

    public static <T> Observable<T> defer(final Supplier<Observable<T>> supplier) {
        return create(new Function<Observer<T>, Subscription>() {
            public Subscription apply(Observer<T> observer) {
                return ((Observable) supplier.apply()).subscribe(observer);
            }
        });
    }

    public static <T, R> Observable<R> zip(final Observable<T> observable, final Observable<T> observable2, final BiFunction<T, T, R> biFunction) {
        return create(new Function<Observer<R>, Subscription>() {
            public Subscription apply(Observer<R> observer) {
                CompoundSubscription compoundSubscription = new CompoundSubscription();
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                Holder holder = new Holder(false);
                Holder holder2 = new Holder(false);
                final Holder holder3 = new Holder(false);
                final Observer<R> observer2 = observer;
                final Holder holder4 = holder;
                final ArrayList arrayList3 = arrayList;
                final Holder holder5 = holder2;
                final ArrayList arrayList4 = arrayList2;
                final CompoundSubscription compoundSubscription2 = compoundSubscription;
                AnonymousClass1 r0 = new Runnable() {
                    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0054, code lost:
                        return;
                     */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void run() {
                        /*
                            r3 = this;
                            com.urbanairship.reactive.Observer r0 = r2
                            monitor-enter(r0)
                            com.urbanairship.reactive.Observable$Holder r1 = r3     // Catch:{ all -> 0x0055 }
                            java.lang.Object r1 = r1.getValue()     // Catch:{ all -> 0x0055 }
                            java.lang.Boolean r1 = (java.lang.Boolean) r1     // Catch:{ all -> 0x0055 }
                            boolean r1 = r1.booleanValue()     // Catch:{ all -> 0x0055 }
                            if (r1 == 0) goto L_0x0013
                            monitor-exit(r0)     // Catch:{ all -> 0x0055 }
                            return
                        L_0x0013:
                            com.urbanairship.reactive.Observable$Holder r1 = r4     // Catch:{ all -> 0x0055 }
                            java.lang.Object r1 = r1.getValue()     // Catch:{ all -> 0x0055 }
                            java.lang.Boolean r1 = (java.lang.Boolean) r1     // Catch:{ all -> 0x0055 }
                            boolean r1 = r1.booleanValue()     // Catch:{ all -> 0x0055 }
                            if (r1 == 0) goto L_0x0053
                            java.util.ArrayList r1 = r5     // Catch:{ all -> 0x0055 }
                            int r1 = r1.size()     // Catch:{ all -> 0x0055 }
                            if (r1 != 0) goto L_0x0053
                            com.urbanairship.reactive.Observable$Holder r1 = r6     // Catch:{ all -> 0x0055 }
                            java.lang.Object r1 = r1.getValue()     // Catch:{ all -> 0x0055 }
                            java.lang.Boolean r1 = (java.lang.Boolean) r1     // Catch:{ all -> 0x0055 }
                            boolean r1 = r1.booleanValue()     // Catch:{ all -> 0x0055 }
                            if (r1 == 0) goto L_0x0053
                            java.util.ArrayList r1 = r7     // Catch:{ all -> 0x0055 }
                            int r1 = r1.size()     // Catch:{ all -> 0x0055 }
                            if (r1 != 0) goto L_0x0053
                            com.urbanairship.reactive.Observable$Holder r1 = r3     // Catch:{ all -> 0x0055 }
                            r2 = 1
                            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch:{ all -> 0x0055 }
                            r1.setValue(r2)     // Catch:{ all -> 0x0055 }
                            com.urbanairship.reactive.CompoundSubscription r1 = r8     // Catch:{ all -> 0x0055 }
                            r1.cancel()     // Catch:{ all -> 0x0055 }
                            com.urbanairship.reactive.Observer r1 = r2     // Catch:{ all -> 0x0055 }
                            r1.onCompleted()     // Catch:{ all -> 0x0055 }
                        L_0x0053:
                            monitor-exit(r0)     // Catch:{ all -> 0x0055 }
                            return
                        L_0x0055:
                            r1 = move-exception
                            monitor-exit(r0)     // Catch:{ all -> 0x0055 }
                            throw r1
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.urbanairship.reactive.Observable.AnonymousClass16.AnonymousClass1.run():void");
                    }
                };
                final ArrayList arrayList5 = arrayList;
                final ArrayList arrayList6 = arrayList2;
                final AnonymousClass1 r5 = r0;
                final AnonymousClass2 r4 = new Runnable() {
                    public void run() {
                        synchronized (observer2) {
                            if (arrayList5.size() > 0 && arrayList6.size() > 0) {
                                Object apply = biFunction.apply(arrayList5.get(0), arrayList6.get(0));
                                arrayList5.remove(0);
                                arrayList6.remove(0);
                                observer2.onNext(apply);
                                r5.run();
                            }
                        }
                    }
                };
                final Holder holder6 = holder;
                AnonymousClass3 r11 = r0;
                final AnonymousClass1 r6 = r0;
                Observable observable = observable;
                final CompoundSubscription compoundSubscription3 = compoundSubscription;
                AnonymousClass3 r02 = new Subscriber<T>() {
                    public void onNext(T t) {
                        synchronized (observer2) {
                            arrayList5.add(t);
                            r4.run();
                        }
                    }

                    public void onCompleted() {
                        synchronized (observer2) {
                            holder6.setValue(true);
                            r6.run();
                        }
                    }

                    public void onError(Exception exc) {
                        synchronized (observer2) {
                            compoundSubscription3.cancel();
                            observer2.onError(exc);
                        }
                    }
                };
                compoundSubscription.add(observable.subscribe(r11));
                final ArrayList arrayList7 = arrayList2;
                final Holder holder7 = holder2;
                compoundSubscription.add(observable2.subscribe(new Subscriber<T>() {
                    public void onNext(T t) {
                        synchronized (observer2) {
                            arrayList7.add(t);
                            r4.run();
                        }
                    }

                    public void onCompleted() {
                        synchronized (observer2) {
                            holder7.setValue(true);
                            r6.run();
                        }
                    }

                    public void onError(Exception exc) {
                        synchronized (observer2) {
                            compoundSubscription3.cancel();
                            observer2.onError(exc);
                        }
                    }
                }));
                return compoundSubscription;
            }
        });
    }

    private <R> Observable<R> bind(final Function<T, Observable<R>> function) {
        final WeakReference weakReference = new WeakReference(this);
        final CompoundSubscription compoundSubscription = new CompoundSubscription();
        return create(new Function<Observer<R>, Subscription>() {
            public Subscription apply(final Observer<R> observer) {
                final ObservableTracker observableTracker = new ObservableTracker(observer, compoundSubscription);
                Observable observable = (Observable) weakReference.get();
                if (observable == null) {
                    observer.onCompleted();
                    return Subscription.empty();
                }
                final SerialSubscription serialSubscription = new SerialSubscription();
                compoundSubscription.add(serialSubscription);
                serialSubscription.setSubscription(observable.subscribe(new Subscriber<T>() {
                    public void onNext(T t) {
                        if (!compoundSubscription.isCancelled()) {
                            observableTracker.addObservable((Observable) function.apply(t));
                            return;
                        }
                        serialSubscription.cancel();
                        observableTracker.completeObservable(serialSubscription);
                    }

                    public void onCompleted() {
                        observableTracker.completeObservable(serialSubscription);
                    }

                    public void onError(Exception exc) {
                        compoundSubscription.cancel();
                        observer.onError(exc);
                    }
                }));
                return compoundSubscription;
            }
        });
    }

    private static class Holder<T> {
        private T value;

        Holder() {
        }

        Holder(T t) {
            this.value = t;
        }

        /* access modifiers changed from: package-private */
        public T getValue() {
            return this.value;
        }

        /* access modifiers changed from: package-private */
        public void setValue(T t) {
            this.value = t;
        }
    }

    private static class ObservableTracker<T> {
        /* access modifiers changed from: private */
        public final CompoundSubscription compoundSubscription;
        private final AtomicInteger observableCount = new AtomicInteger(1);
        /* access modifiers changed from: private */
        public final Observer<T> observer;

        ObservableTracker(Observer<T> observer2, CompoundSubscription compoundSubscription2) {
            this.observer = observer2;
            this.compoundSubscription = compoundSubscription2;
        }

        /* access modifiers changed from: package-private */
        public void addObservable(Observable<T> observable) {
            this.observableCount.getAndIncrement();
            final SerialSubscription serialSubscription = new SerialSubscription();
            serialSubscription.setSubscription(observable.subscribe(new Observer<T>() {
                public void onNext(T t) {
                    ObservableTracker.this.observer.onNext(t);
                }

                public void onCompleted() {
                    ObservableTracker.this.completeObservable(serialSubscription);
                }

                public void onError(Exception exc) {
                    ObservableTracker.this.compoundSubscription.cancel();
                    ObservableTracker.this.observer.onError(exc);
                }
            }));
        }

        /* access modifiers changed from: package-private */
        public void completeObservable(Subscription subscription) {
            if (this.observableCount.decrementAndGet() == 0) {
                this.observer.onCompleted();
                this.compoundSubscription.cancel();
                return;
            }
            this.compoundSubscription.remove(subscription);
        }
    }
}
