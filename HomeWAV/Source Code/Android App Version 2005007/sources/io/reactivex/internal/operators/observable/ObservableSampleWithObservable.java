package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.observers.SerializedObserver;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableSampleWithObservable<T> extends AbstractObservableWithUpstream<T, T> {
    final boolean emitLast;
    final ObservableSource<?> other;

    public ObservableSampleWithObservable(ObservableSource<T> observableSource, ObservableSource<?> observableSource2, boolean z) {
        super(observableSource);
        this.other = observableSource2;
        this.emitLast = z;
    }

    public void subscribeActual(Observer<? super T> observer) {
        SerializedObserver serializedObserver = new SerializedObserver(observer);
        if (this.emitLast) {
            this.source.subscribe(new SampleMainEmitLast(serializedObserver, this.other));
        } else {
            this.source.subscribe(new SampleMainNoLast(serializedObserver, this.other));
        }
    }

    static abstract class SampleMainObserver<T> extends AtomicReference<T> implements Observer<T>, Disposable {
        private static final long serialVersionUID = -3517602651313910099L;
        final Observer<? super T> actual;
        final AtomicReference<Disposable> other = new AtomicReference<>();
        Disposable s;
        final ObservableSource<?> sampler;

        /* access modifiers changed from: package-private */
        public abstract void completeMain();

        /* access modifiers changed from: package-private */
        public abstract void completeOther();

        /* access modifiers changed from: package-private */
        public abstract void run();

        SampleMainObserver(Observer<? super T> observer, ObservableSource<?> observableSource) {
            this.actual = observer;
            this.sampler = observableSource;
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.s, disposable)) {
                this.s = disposable;
                this.actual.onSubscribe(this);
                if (this.other.get() == null) {
                    this.sampler.subscribe(new SamplerObserver(this));
                }
            }
        }

        public void onNext(T t) {
            lazySet(t);
        }

        public void onError(Throwable th) {
            DisposableHelper.dispose(this.other);
            this.actual.onError(th);
        }

        public void onComplete() {
            DisposableHelper.dispose(this.other);
            completeMain();
        }

        /* access modifiers changed from: package-private */
        public boolean setOther(Disposable disposable) {
            return DisposableHelper.setOnce(this.other, disposable);
        }

        public void dispose() {
            DisposableHelper.dispose(this.other);
            this.s.dispose();
        }

        public boolean isDisposed() {
            return this.other.get() == DisposableHelper.DISPOSED;
        }

        public void error(Throwable th) {
            this.s.dispose();
            this.actual.onError(th);
        }

        public void complete() {
            this.s.dispose();
            completeOther();
        }

        /* access modifiers changed from: package-private */
        public void emit() {
            Object andSet = getAndSet((Object) null);
            if (andSet != null) {
                this.actual.onNext(andSet);
            }
        }
    }

    static final class SamplerObserver<T> implements Observer<Object> {
        final SampleMainObserver<T> parent;

        SamplerObserver(SampleMainObserver<T> sampleMainObserver) {
            this.parent = sampleMainObserver;
        }

        public void onSubscribe(Disposable disposable) {
            this.parent.setOther(disposable);
        }

        public void onNext(Object obj) {
            this.parent.run();
        }

        public void onError(Throwable th) {
            this.parent.error(th);
        }

        public void onComplete() {
            this.parent.complete();
        }
    }

    static final class SampleMainNoLast<T> extends SampleMainObserver<T> {
        private static final long serialVersionUID = -3029755663834015785L;

        SampleMainNoLast(Observer<? super T> observer, ObservableSource<?> observableSource) {
            super(observer, observableSource);
        }

        /* access modifiers changed from: package-private */
        public void completeMain() {
            this.actual.onComplete();
        }

        /* access modifiers changed from: package-private */
        public void completeOther() {
            this.actual.onComplete();
        }

        /* access modifiers changed from: package-private */
        public void run() {
            emit();
        }
    }

    static final class SampleMainEmitLast<T> extends SampleMainObserver<T> {
        private static final long serialVersionUID = -3029755663834015785L;
        volatile boolean done;
        final AtomicInteger wip = new AtomicInteger();

        SampleMainEmitLast(Observer<? super T> observer, ObservableSource<?> observableSource) {
            super(observer, observableSource);
        }

        /* access modifiers changed from: package-private */
        public void completeMain() {
            this.done = true;
            if (this.wip.getAndIncrement() == 0) {
                emit();
                this.actual.onComplete();
            }
        }

        /* access modifiers changed from: package-private */
        public void completeOther() {
            this.done = true;
            if (this.wip.getAndIncrement() == 0) {
                emit();
                this.actual.onComplete();
            }
        }

        /* access modifiers changed from: package-private */
        public void run() {
            if (this.wip.getAndIncrement() == 0) {
                do {
                    boolean z = this.done;
                    emit();
                    if (z) {
                        this.actual.onComplete();
                        return;
                    }
                } while (this.wip.decrementAndGet() != 0);
            }
        }
    }
}
