package com.pusher.client.channel;

import com.google.gson.Gson;

public class User {
    private static final Gson GSON = new Gson();
    private final String id;
    private final String jsonData;

    public User(String str, String str2) {
        this.id = str;
        this.jsonData = str2;
    }

    public String getId() {
        return this.id;
    }

    public String getInfo() {
        return this.jsonData;
    }

    public <V> V getInfo(Class<V> cls) {
        return GSON.fromJson(this.jsonData, cls);
    }

    public String toString() {
        return String.format("[User id=%s, data=%s]", new Object[]{this.id, this.jsonData});
    }

    public int hashCode() {
        int hashCode = this.id.hashCode();
        String str = this.jsonData;
        return hashCode + (str != null ? str.hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof User)) {
            return false;
        }
        User user = (User) obj;
        if (!getId().equals(user.getId()) || !getInfo().equals(user.getInfo())) {
            return false;
        }
        return true;
    }
}
