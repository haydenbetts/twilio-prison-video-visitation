package com.urbanairship.automation;

import com.urbanairship.UAirship;
import com.urbanairship.app.ActivityMonitor;
import com.urbanairship.app.SimpleApplicationListener;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.json.JsonValue;
import com.urbanairship.reactive.Function;
import com.urbanairship.reactive.Observable;
import com.urbanairship.reactive.Observer;
import com.urbanairship.reactive.Schedulers;
import com.urbanairship.reactive.Subscription;
import com.urbanairship.reactive.Supplier;
import com.urbanairship.util.VersionUtils;

class TriggerObservables {
    TriggerObservables() {
    }

    public static Observable<JsonSerializable> foregrounded(final ActivityMonitor activityMonitor) {
        return Observable.create(new Function<Observer<JsonSerializable>, Subscription>() {
            public Subscription apply(Observer<JsonSerializable> observer) {
                if (activityMonitor.isAppForegrounded()) {
                    observer.onNext(JsonValue.NULL);
                }
                observer.onCompleted();
                return Subscription.empty();
            }
        }).subscribeOn(Schedulers.main());
    }

    public static Observable<JsonSerializable> newSession(final ActivityMonitor activityMonitor) {
        return Observable.create(new Function<Observer<JsonSerializable>, Subscription>() {
            public Subscription apply(final Observer<JsonSerializable> observer) {
                final AnonymousClass1 r0 = new SimpleApplicationListener() {
                    public void onForeground(long j) {
                        observer.onNext(JsonValue.NULL);
                    }
                };
                activityMonitor.addApplicationListener(r0);
                return Subscription.create(new Runnable() {
                    public void run() {
                        activityMonitor.removeApplicationListener(r0);
                    }
                });
            }
        }).subscribeOn(Schedulers.main());
    }

    public static Observable<JsonSerializable> appVersionUpdated() {
        return Observable.defer(new Supplier<Observable<JsonSerializable>>() {
            public Observable<JsonSerializable> apply() {
                if (UAirship.shared().getApplicationMetrics().getAppVersionUpdated()) {
                    return Observable.just(VersionUtils.createVersionObject());
                }
                return Observable.empty();
            }
        });
    }
}
