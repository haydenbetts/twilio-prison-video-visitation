package org.apache.cordova;

import android.util.Pair;
import android.util.SparseArray;

public class CallbackMap {
    private SparseArray<Pair<CordovaPlugin, Integer>> callbacks = new SparseArray<>();
    private int currentCallbackId = 0;

    public synchronized int registerCallback(CordovaPlugin cordovaPlugin, int i) {
        int i2;
        i2 = this.currentCallbackId;
        this.currentCallbackId = i2 + 1;
        this.callbacks.put(i2, new Pair(cordovaPlugin, Integer.valueOf(i)));
        return i2;
    }

    public synchronized Pair<CordovaPlugin, Integer> getAndRemoveCallback(int i) {
        Pair<CordovaPlugin, Integer> pair;
        pair = this.callbacks.get(i);
        this.callbacks.remove(i);
        return pair;
    }
}
