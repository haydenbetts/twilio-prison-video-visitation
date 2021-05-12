package com.urbanairship.reactive;

public interface Observer<T> {
    void onCompleted();

    void onError(Exception exc);

    void onNext(T t);
}
