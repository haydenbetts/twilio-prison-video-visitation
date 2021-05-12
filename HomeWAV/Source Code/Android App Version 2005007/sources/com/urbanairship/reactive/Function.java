package com.urbanairship.reactive;

public interface Function<T, R> {
    R apply(T t);
}
