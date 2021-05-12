package com.urbanairship;

public interface Cancelable {
    boolean cancel();

    boolean cancel(boolean z);

    boolean isCancelled();

    boolean isDone();
}
