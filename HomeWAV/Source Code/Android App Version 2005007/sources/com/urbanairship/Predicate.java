package com.urbanairship;

public interface Predicate<T> {
    boolean apply(T t);
}
