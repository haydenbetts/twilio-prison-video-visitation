package com.urbanairship.messagecenter;

import android.os.Bundle;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonValue;
import com.urbanairship.util.DateUtils;
import com.urbanairship.util.UAStringUtil;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public class Message implements Comparable<Message> {
    static final String EXTRA_KEY = "extra";
    static final String MESSAGE_BODY_URL_KEY = "message_body_url";
    static final String MESSAGE_EXPIRY_KEY = "message_expiry";
    static final String MESSAGE_ID_KEY = "message_id";
    static final String MESSAGE_READ_URL_KEY = "message_read_url";
    static final String MESSAGE_SENT_KEY = "message_sent";
    static final String MESSAGE_URL_KEY = "message_url";
    static final String TITLE_KEY = "title";
    static final String UNREAD_KEY = "unread";
    boolean deleted = false;
    private Long expirationMS;
    private Map<String, String> extrasMap;
    private String messageBodyUrl;
    private String messageId;
    private String messageReadUrl;
    private String messageUrl;
    private JsonValue rawJson;
    private long sentMS;
    private String title;
    boolean unreadClient;
    private boolean unreadOrigin;

    protected Message() {
    }

    static Message create(JsonValue jsonValue, boolean z, boolean z2) {
        String string;
        String string2;
        String string3;
        String string4;
        JsonMap map = jsonValue.getMap();
        if (map == null || (string = map.opt("message_id").getString()) == null || (string2 = map.opt("message_url").getString()) == null || (string3 = map.opt("message_body_url").getString()) == null || (string4 = map.opt("message_read_url").getString()) == null) {
            return null;
        }
        Message message = new Message();
        message.messageId = string;
        message.messageUrl = string2;
        message.messageBodyUrl = string3;
        message.messageReadUrl = string4;
        message.title = map.opt("title").optString();
        message.unreadOrigin = map.opt("unread").getBoolean(true);
        message.rawJson = jsonValue;
        String string5 = map.opt(MESSAGE_SENT_KEY).getString();
        if (UAStringUtil.isEmpty(string5)) {
            message.sentMS = System.currentTimeMillis();
        } else {
            message.sentMS = DateUtils.parseIso8601(string5, System.currentTimeMillis());
        }
        String string6 = map.opt(MESSAGE_EXPIRY_KEY).getString();
        if (!UAStringUtil.isEmpty(string6)) {
            message.expirationMS = Long.valueOf(DateUtils.parseIso8601(string6, Long.MAX_VALUE));
        }
        HashMap hashMap = new HashMap();
        Iterator<Map.Entry<String, JsonValue>> it = map.opt("extra").optMap().iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            if (((JsonValue) next.getValue()).isString()) {
                hashMap.put(next.getKey(), ((JsonValue) next.getValue()).getString());
            } else {
                hashMap.put(next.getKey(), ((JsonValue) next.getValue()).toString());
            }
        }
        message.extrasMap = hashMap;
        message.deleted = z2;
        message.unreadClient = z;
        return message;
    }

    public String getMessageId() {
        return this.messageId;
    }

    public String getMessageUrl() {
        return this.messageUrl;
    }

    public String getMessageBodyUrl() {
        return this.messageBodyUrl;
    }

    public String getMessageReadUrl() {
        return this.messageReadUrl;
    }

    public String getTitle() {
        return this.title;
    }

    public boolean isRead() {
        return !this.unreadClient;
    }

    public Date getSentDate() {
        return new Date(this.sentMS);
    }

    public long getSentDateMS() {
        return this.sentMS;
    }

    public Date getExpirationDate() {
        if (this.expirationMS != null) {
            return new Date(this.expirationMS.longValue());
        }
        return null;
    }

    public Long getExpirationDateMS() {
        return this.expirationMS;
    }

    public boolean isExpired() {
        return this.expirationMS != null && System.currentTimeMillis() >= this.expirationMS.longValue();
    }

    public Bundle getExtras() {
        Bundle bundle = new Bundle();
        for (Map.Entry next : this.extrasMap.entrySet()) {
            bundle.putString((String) next.getKey(), (String) next.getValue());
        }
        return bundle;
    }

    public Map<String, String> getExtrasMap() {
        return this.extrasMap;
    }

    public void markRead() {
        if (this.unreadClient) {
            this.unreadClient = false;
            HashSet hashSet = new HashSet();
            hashSet.add(this.messageId);
            MessageCenter.shared().getInbox().markMessagesRead(hashSet);
        }
    }

    public void markUnread() {
        if (!this.unreadClient) {
            this.unreadClient = true;
            HashSet hashSet = new HashSet();
            hashSet.add(this.messageId);
            MessageCenter.shared().getInbox().markMessagesUnread(hashSet);
        }
    }

    public void delete() {
        if (!this.deleted) {
            this.deleted = true;
            HashSet hashSet = new HashSet();
            hashSet.add(this.messageId);
            MessageCenter.shared().getInbox().deleteMessages(hashSet);
        }
    }

    public JsonValue getRawMessageJson() {
        return this.rawJson;
    }

    public boolean isDeleted() {
        return this.deleted;
    }

    public String getListIconUrl() {
        JsonValue opt = getRawMessageJson().optMap().opt("icons");
        if (opt.isJsonMap()) {
            return opt.optMap().opt("list_icon").getString();
        }
        return null;
    }

    public int compareTo(Message message) {
        return getMessageId().compareTo(message.getMessageId());
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Message)) {
            return false;
        }
        Message message = (Message) obj;
        if (this == message) {
            return true;
        }
        String str = this.messageId;
        if (str == null) {
            if (message.messageId != null) {
                return false;
            }
        } else if (!str.equals(message.messageId)) {
            return false;
        }
        String str2 = this.messageBodyUrl;
        if (str2 == null) {
            if (message.messageBodyUrl != null) {
                return false;
            }
        } else if (!str2.equals(message.messageBodyUrl)) {
            return false;
        }
        String str3 = this.messageReadUrl;
        if (str3 == null) {
            if (message.messageReadUrl != null) {
                return false;
            }
        } else if (!str3.equals(message.messageReadUrl)) {
            return false;
        }
        String str4 = this.messageUrl;
        if (str4 == null) {
            if (message.messageUrl != null) {
                return false;
            }
        } else if (!str4.equals(message.messageUrl)) {
            return false;
        }
        Map<String, String> map = this.extrasMap;
        if (map == null) {
            if (message.extrasMap != null) {
                return false;
            }
        } else if (!map.equals(message.extrasMap)) {
            return false;
        }
        if (this.unreadClient == message.unreadClient && this.unreadOrigin == message.unreadOrigin && this.deleted == message.deleted && this.sentMS == message.sentMS) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        String str = this.messageId;
        int i = 0;
        int hashCode = (629 + (str == null ? 0 : str.hashCode())) * 37;
        String str2 = this.messageBodyUrl;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 37;
        String str3 = this.messageReadUrl;
        int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 37;
        String str4 = this.messageUrl;
        int hashCode4 = (hashCode3 + (str4 == null ? 0 : str4.hashCode())) * 37;
        Map<String, String> map = this.extrasMap;
        if (map != null) {
            i = map.hashCode();
        }
        return ((((((((hashCode4 + i) * 37) + (this.unreadClient ^ true ? 1 : 0)) * 37) + (this.unreadOrigin ^ true ? 1 : 0)) * 37) + (this.deleted ^ true ? 1 : 0)) * 37) + Long.valueOf(this.sentMS).hashCode();
    }
}
