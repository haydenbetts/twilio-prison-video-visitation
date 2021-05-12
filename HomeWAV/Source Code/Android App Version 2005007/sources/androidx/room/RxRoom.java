package androidx.room;

import androidx.room.InvalidationTracker;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposables;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.Set;
import java.util.concurrent.Callable;

public class RxRoom {
    public static final Object NOTHING = new Object();

    public static Flowable<Object> createFlowable(final RoomDatabase roomDatabase, final String... strArr) {
        return Flowable.create(new FlowableOnSubscribe<Object>() {
            public void subscribe(final FlowableEmitter<Object> flowableEmitter) throws Exception {
                final AnonymousClass1 r0 = new InvalidationTracker.Observer(strArr) {
                    public void onInvalidated(Set<String> set) {
                        if (!flowableEmitter.isCancelled()) {
                            flowableEmitter.onNext(RxRoom.NOTHING);
                        }
                    }
                };
                if (!flowableEmitter.isCancelled()) {
                    roomDatabase.getInvalidationTracker().addObserver(r0);
                    flowableEmitter.setDisposable(Disposables.fromAction(new Action() {
                        public void run() throws Exception {
                            roomDatabase.getInvalidationTracker().removeObserver(r0);
                        }
                    }));
                }
                if (!flowableEmitter.isCancelled()) {
                    flowableEmitter.onNext(RxRoom.NOTHING);
                }
            }
        }, BackpressureStrategy.LATEST);
    }

    public static <T> Flowable<T> createFlowable(RoomDatabase roomDatabase, String[] strArr, Callable<T> callable) {
        Scheduler from = Schedulers.from(roomDatabase.getQueryExecutor());
        final Maybe<T> fromCallable = Maybe.fromCallable(callable);
        return createFlowable(roomDatabase, strArr).observeOn(from).flatMapMaybe(new Function<Object, MaybeSource<T>>() {
            public MaybeSource<T> apply(Object obj) throws Exception {
                return fromCallable;
            }
        });
    }

    public static Observable<Object> createObservable(final RoomDatabase roomDatabase, final String... strArr) {
        return Observable.create(new ObservableOnSubscribe<Object>() {
            public void subscribe(final ObservableEmitter<Object> observableEmitter) throws Exception {
                final AnonymousClass1 r0 = new InvalidationTracker.Observer(strArr) {
                    public void onInvalidated(Set<String> set) {
                        observableEmitter.onNext(RxRoom.NOTHING);
                    }
                };
                roomDatabase.getInvalidationTracker().addObserver(r0);
                observableEmitter.setDisposable(Disposables.fromAction(new Action() {
                    public void run() throws Exception {
                        roomDatabase.getInvalidationTracker().removeObserver(r0);
                    }
                }));
                observableEmitter.onNext(RxRoom.NOTHING);
            }
        });
    }

    public static <T> Observable<T> createObservable(RoomDatabase roomDatabase, String[] strArr, Callable<T> callable) {
        Scheduler from = Schedulers.from(roomDatabase.getQueryExecutor());
        final Maybe<T> fromCallable = Maybe.fromCallable(callable);
        return createObservable(roomDatabase, strArr).observeOn(from).flatMapMaybe(new Function<Object, MaybeSource<T>>() {
            public MaybeSource<T> apply(Object obj) throws Exception {
                return fromCallable;
            }
        });
    }
}
