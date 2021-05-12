package com.urbanairship.util;

import androidx.arch.core.util.Function;
import com.urbanairship.PreferenceDataStore;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.json.JsonValue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JsonDataStoreQueue<T> {
    private final PreferenceDataStore dataStore;
    private final Function<JsonValue, T> deserializer;
    private final Function<T, ? extends JsonSerializable> serializer;
    private final String storeKey;

    public JsonDataStoreQueue(PreferenceDataStore preferenceDataStore, String str, Function<T, ? extends JsonSerializable> function, Function<JsonValue, T> function2) {
        this.dataStore = preferenceDataStore;
        this.storeKey = str;
        this.serializer = function;
        this.deserializer = function2;
    }

    public void removeAll() {
        synchronized (this.storeKey) {
            this.dataStore.remove(this.storeKey);
        }
    }

    public void addAll(List<T> list) {
        if (!list.isEmpty()) {
            synchronized (this.storeKey) {
                List<JsonValue> list2 = this.dataStore.getJsonValue(this.storeKey).optList().getList();
                for (T apply : list) {
                    list2.add(((JsonSerializable) this.serializer.apply(apply)).toJsonValue());
                }
                this.dataStore.put(this.storeKey, JsonValue.wrapOpt(list2));
            }
        }
    }

    public void add(T t) {
        synchronized (this.storeKey) {
            List<JsonValue> list = this.dataStore.getJsonValue(this.storeKey).optList().getList();
            list.add(((JsonSerializable) this.serializer.apply(t)).toJsonValue());
            this.dataStore.put(this.storeKey, JsonValue.wrapOpt(list));
        }
    }

    public T pop() {
        synchronized (this.storeKey) {
            List<JsonValue> list = this.dataStore.getJsonValue(this.storeKey).optList().getList();
            if (list.isEmpty()) {
                return null;
            }
            this.dataStore.put(this.storeKey, JsonValue.wrapOpt(list));
            T apply = this.deserializer.apply(list.remove(0));
            return apply;
        }
    }

    public T peek() {
        List<JsonValue> list = this.dataStore.getJsonValue(this.storeKey).optList().getList();
        if (list.isEmpty()) {
            return null;
        }
        return this.deserializer.apply(list.get(0));
    }

    public List<T> getList() {
        ArrayList arrayList;
        synchronized (this.storeKey) {
            arrayList = new ArrayList();
            Iterator<JsonValue> it = this.dataStore.getJsonValue(this.storeKey).optList().iterator();
            while (it.hasNext()) {
                arrayList.add(this.deserializer.apply(it.next()));
            }
        }
        return arrayList;
    }

    public void apply(Function<List<T>, List<T>> function) {
        synchronized (this.storeKey) {
            this.dataStore.put(this.storeKey, JsonValue.wrapOpt(function.apply(getList())));
        }
    }
}
