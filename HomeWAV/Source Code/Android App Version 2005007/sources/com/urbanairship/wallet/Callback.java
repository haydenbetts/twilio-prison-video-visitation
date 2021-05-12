package com.urbanairship.wallet;

public interface Callback {
    void onError(int i);

    void onResult(Pass pass);
}
