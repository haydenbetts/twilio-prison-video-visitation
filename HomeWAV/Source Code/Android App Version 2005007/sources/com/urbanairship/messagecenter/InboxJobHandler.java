package com.urbanairship.messagecenter;

import android.content.Context;
import com.microsoft.appcenter.http.DefaultHttpClient;
import com.urbanairship.Logger;
import com.urbanairship.PreferenceDataStore;
import com.urbanairship.channel.AirshipChannel;
import com.urbanairship.config.AirshipRuntimeConfig;
import com.urbanairship.config.AirshipUrlConfig;
import com.urbanairship.config.UrlBuilder;
import com.urbanairship.http.RequestFactory;
import com.urbanairship.http.Response;
import com.urbanairship.job.JobInfo;
import com.urbanairship.json.JsonException;
import com.urbanairship.json.JsonList;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.json.JsonValue;
import com.urbanairship.util.UAStringUtil;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

class InboxJobHandler {
    static final String ACTION_RICH_PUSH_MESSAGES_UPDATE = "ACTION_RICH_PUSH_MESSAGES_UPDATE";
    static final String ACTION_RICH_PUSH_USER_UPDATE = "ACTION_RICH_PUSH_USER_UPDATE";
    static final String ACTION_SYNC_MESSAGE_STATE = "ACTION_SYNC_MESSAGE_STATE";
    private static final String CHANNEL_ID_HEADER = "X-UA-Channel-ID";
    private static final String DELETE_MESSAGES_KEY = "delete";
    private static final String DELETE_MESSAGES_PATH = "messages/delete/";
    static final String EXTRA_FORCEFULLY = "EXTRA_FORCEFULLY";
    static final String LAST_MESSAGE_REFRESH_TIME = "com.urbanairship.user.LAST_MESSAGE_REFRESH_TIME";
    private static final String LAST_UPDATE_TIME = "com.urbanairship.user.LAST_UPDATE_TIME";
    private static final String MARK_READ_MESSAGES_KEY = "mark_as_read";
    private static final String MARK_READ_MESSAGES_PATH = "messages/unread/";
    private static final String MESSAGES_PATH = "messages/";
    private static final String MESSAGE_PATH = "messages/message/";
    private static final String PAYLOAD_ADD_KEY = "add";
    private static final String PAYLOAD_AMAZON_CHANNELS_KEY = "amazon_channels";
    private static final String PAYLOAD_ANDROID_CHANNELS_KEY = "android_channels";
    private static final String USER_API_PATH = "api/user/";
    private static final long USER_UPDATE_INTERVAL_MS = 86400000;
    private final AirshipChannel channel;
    private final PreferenceDataStore dataStore;
    private final Inbox inbox;
    private final RequestFactory requestFactory;
    private final MessageCenterResolver resolver;
    private final AirshipRuntimeConfig runtimeConfig;
    private final User user;

    InboxJobHandler(Context context, Inbox inbox2, User user2, AirshipChannel airshipChannel, AirshipRuntimeConfig airshipRuntimeConfig, PreferenceDataStore preferenceDataStore) {
        this(inbox2, user2, airshipChannel, airshipRuntimeConfig, preferenceDataStore, RequestFactory.DEFAULT_REQUEST_FACTORY, new MessageCenterResolver(context));
    }

    InboxJobHandler(Inbox inbox2, User user2, AirshipChannel airshipChannel, AirshipRuntimeConfig airshipRuntimeConfig, PreferenceDataStore preferenceDataStore, RequestFactory requestFactory2, MessageCenterResolver messageCenterResolver) {
        this.inbox = inbox2;
        this.user = user2;
        this.channel = airshipChannel;
        this.dataStore = preferenceDataStore;
        this.runtimeConfig = airshipRuntimeConfig;
        this.requestFactory = requestFactory2;
        this.resolver = messageCenterResolver;
    }

    /* access modifiers changed from: package-private */
    public int performJob(JobInfo jobInfo) {
        String action = jobInfo.getAction();
        action.hashCode();
        char c = 65535;
        switch (action.hashCode()) {
            case -2142275554:
                if (action.equals(ACTION_SYNC_MESSAGE_STATE)) {
                    c = 0;
                    break;
                }
                break;
            case -1764334927:
                if (action.equals(ACTION_RICH_PUSH_MESSAGES_UPDATE)) {
                    c = 1;
                    break;
                }
                break;
            case 1960281554:
                if (action.equals(ACTION_RICH_PUSH_USER_UPDATE)) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                onSyncMessages();
                break;
            case 1:
                onUpdateMessages();
                break;
            case 2:
                onUpdateUser(jobInfo.getExtras().opt(EXTRA_FORCEFULLY).getBoolean(false));
                break;
        }
        return 0;
    }

    private void onUpdateMessages() {
        if (!this.user.isUserCreated()) {
            Logger.debug("InboxJobHandler - User has not been created, canceling messages update", new Object[0]);
            this.inbox.onUpdateMessagesFinished(false);
            return;
        }
        boolean updateMessages = updateMessages();
        this.inbox.refresh(true);
        this.inbox.onUpdateMessagesFinished(updateMessages);
        syncReadMessageState();
        syncDeletedMessageState();
    }

    private void onSyncMessages() {
        syncReadMessageState();
        syncDeletedMessageState();
    }

    private void onUpdateUser(boolean z) {
        boolean z2;
        if (!z) {
            long j = this.dataStore.getLong(LAST_UPDATE_TIME, 0);
            long currentTimeMillis = System.currentTimeMillis();
            if (j <= currentTimeMillis && j + USER_UPDATE_INTERVAL_MS >= currentTimeMillis) {
                return;
            }
        }
        if (!this.user.isUserCreated()) {
            z2 = createUser();
        } else {
            z2 = updateUser();
        }
        this.user.onUserUpdated(z2);
    }

    private boolean updateMessages() {
        int i;
        Logger.info("Refreshing inbox messages.", new Object[0]);
        URL userApiUrl = getUserApiUrl(this.runtimeConfig.getUrlConfig(), this.user.getId(), MESSAGES_PATH);
        if (userApiUrl == null) {
            Logger.debug("User URL null, unable to update message.", new Object[0]);
            return false;
        }
        Logger.verbose("InboxJobHandler - Fetching inbox messages.", new Object[0]);
        Response<Void> safeExecute = this.requestFactory.createRequest(DefaultHttpClient.METHOD_GET, userApiUrl).setCredentials(this.user.getId(), this.user.getPassword()).setHeader("Accept", "application/vnd.urbanairship+json; version=3;").setHeader(CHANNEL_ID_HEADER, this.channel.getId()).setIfModifiedSince(this.dataStore.getLong(LAST_MESSAGE_REFRESH_TIME, 0)).safeExecute();
        Logger.verbose("InboxJobHandler - Fetch inbox messages response: %s", safeExecute);
        if (safeExecute == null) {
            i = -1;
        } else {
            i = safeExecute.getStatus();
        }
        if (i == 304) {
            Logger.debug("Inbox messages already up-to-date. ", new Object[0]);
            return true;
        } else if (i == 200) {
            JsonList jsonList = null;
            try {
                JsonMap map = JsonValue.parseString(safeExecute.getResponseBody()).getMap();
                if (map != null) {
                    jsonList = map.opt("messages").getList();
                }
                if (jsonList == null) {
                    Logger.debug("Inbox message list is empty.", new Object[0]);
                } else {
                    Logger.info("Received %s inbox messages.", Integer.valueOf(jsonList.size()));
                    updateInbox(jsonList);
                    this.dataStore.put(LAST_MESSAGE_REFRESH_TIME, safeExecute.getLastModifiedTime());
                }
                return true;
            } catch (JsonException unused) {
                Logger.error("Failed to update inbox. Unable to parse response body: %s", safeExecute.getResponseBody());
                return false;
            }
        } else {
            Logger.debug("Unable to update inbox messages.", new Object[0]);
            return false;
        }
    }

    private void updateInbox(JsonList jsonList) {
        ArrayList arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        Iterator<JsonValue> it = jsonList.iterator();
        while (it.hasNext()) {
            JsonValue next = it.next();
            if (!next.isJsonMap()) {
                Logger.error("InboxJobHandler - Invalid message payload: %s", next);
            } else {
                String string = next.optMap().opt("message_id").getString();
                if (string == null) {
                    Logger.error("InboxJobHandler - Invalid message payload, missing message ID: %s", next);
                } else {
                    hashSet.add(string);
                    if (this.resolver.updateMessage(string, next) != 1) {
                        arrayList.add(next);
                    }
                }
            }
        }
        if (arrayList.size() > 0) {
            this.resolver.insertMessages(arrayList);
        }
        Set<String> messageIds = this.resolver.getMessageIds();
        messageIds.removeAll(hashSet);
        this.resolver.deleteMessages(messageIds);
    }

    private void syncDeletedMessageState() {
        Set<String> deletedMessageIds = this.resolver.getDeletedMessageIds();
        if (deletedMessageIds.size() != 0) {
            AirshipUrlConfig urlConfig = this.runtimeConfig.getUrlConfig();
            URL userApiUrl = getUserApiUrl(urlConfig, this.user.getId(), DELETE_MESSAGES_PATH);
            if (userApiUrl == null) {
                Logger.debug("User URL null, unable to sync deleted messages.", new Object[0]);
                return;
            }
            Logger.verbose("InboxJobHandler - Found %s messages to delete.", Integer.valueOf(deletedMessageIds.size()));
            JsonMap buildMessagesPayload = buildMessagesPayload(urlConfig, DELETE_MESSAGES_KEY, deletedMessageIds);
            Logger.verbose("InboxJobHandler - Deleting inbox messages with payload: %s", buildMessagesPayload);
            Response<Void> safeExecute = this.requestFactory.createRequest(DefaultHttpClient.METHOD_POST, userApiUrl).setCredentials(this.user.getId(), this.user.getPassword()).setRequestBody(buildMessagesPayload.toString(), "application/json").setHeader(CHANNEL_ID_HEADER, this.channel.getId()).setHeader("Accept", "application/vnd.urbanairship+json; version=3;").safeExecute();
            Logger.verbose("InboxJobHandler - Delete inbox messages response: %s", safeExecute);
            if (safeExecute != null && safeExecute.getStatus() == 200) {
                this.resolver.deleteMessages(deletedMessageIds);
            }
        }
    }

    private void syncReadMessageState() {
        Set<String> readUpdatedMessageIds = this.resolver.getReadUpdatedMessageIds();
        if (readUpdatedMessageIds.size() != 0) {
            AirshipUrlConfig urlConfig = this.runtimeConfig.getUrlConfig();
            URL userApiUrl = getUserApiUrl(urlConfig, this.user.getId(), MARK_READ_MESSAGES_PATH);
            if (userApiUrl == null) {
                Logger.debug("User URL null, unable to sync read messages.", new Object[0]);
                return;
            }
            Logger.verbose("InboxJobHandler - Found %s messages to mark read.", Integer.valueOf(readUpdatedMessageIds.size()));
            JsonMap buildMessagesPayload = buildMessagesPayload(urlConfig, MARK_READ_MESSAGES_KEY, readUpdatedMessageIds);
            Logger.verbose("InboxJobHandler - Marking inbox messages read request with payload: %s", buildMessagesPayload);
            Response<Void> safeExecute = this.requestFactory.createRequest(DefaultHttpClient.METHOD_POST, userApiUrl).setCredentials(this.user.getId(), this.user.getPassword()).setRequestBody(buildMessagesPayload.toString(), "application/json").setHeader(CHANNEL_ID_HEADER, this.channel.getId()).setHeader("Accept", "application/vnd.urbanairship+json; version=3;").safeExecute();
            Logger.verbose("InboxJobHandler - Mark inbox messages read response: %s", safeExecute);
            if (safeExecute != null && safeExecute.getStatus() == 200) {
                this.resolver.markMessagesReadOrigin(readUpdatedMessageIds);
            }
        }
    }

    private JsonMap buildMessagesPayload(AirshipUrlConfig airshipUrlConfig, String str, Set<String> set) {
        ArrayList arrayList = new ArrayList();
        String id = this.user.getId();
        for (String str2 : set) {
            URL userApiUrl = getUserApiUrl(airshipUrlConfig, id, MESSAGE_PATH, str2);
            if (userApiUrl != null) {
                arrayList.add(userApiUrl.toString());
            }
        }
        JsonMap build = JsonMap.newBuilder().put(str, (JsonSerializable) JsonValue.wrapOpt(arrayList)).build();
        Logger.verbose(build.toString(), new Object[0]);
        return build;
    }

    private boolean createUser() {
        String str;
        String id = this.channel.getId();
        if (UAStringUtil.isEmpty(id)) {
            Logger.debug("InboxJobHandler - No Channel. User will be created after channel registrations finishes.", new Object[0]);
            return false;
        }
        URL userApiUrl = getUserApiUrl(this.runtimeConfig.getUrlConfig(), new String[0]);
        if (userApiUrl == null) {
            Logger.debug("User URL null, unable to create user.", new Object[0]);
            return false;
        }
        String createNewUserPayload = createNewUserPayload(id);
        Logger.verbose("InboxJobHandler - Creating Rich Push user with payload: %s", createNewUserPayload);
        Response<Void> safeExecute = this.requestFactory.createRequest(DefaultHttpClient.METHOD_POST, userApiUrl).setCredentials(this.runtimeConfig.getConfigOptions().appKey, this.runtimeConfig.getConfigOptions().appSecret).setRequestBody(createNewUserPayload, "application/json").setHeader("Accept", "application/vnd.urbanairship+json; version=3;").safeExecute();
        if (safeExecute == null || safeExecute.getStatus() != 201) {
            Logger.debug("InboxJobHandler - Rich Push user creation failed: %s", safeExecute);
            return false;
        }
        try {
            JsonMap map = JsonValue.parseString(safeExecute.getResponseBody()).getMap();
            String str2 = null;
            if (map != null) {
                str2 = map.opt("user_id").getString();
                str = map.opt("password").getString();
            } else {
                str = null;
            }
            if (UAStringUtil.isEmpty(str2) || UAStringUtil.isEmpty(str)) {
                Logger.debug("InboxJobHandler - Rich Push user creation failed: %s", safeExecute);
                return false;
            }
            Logger.info("Created Rich Push user: %s", str2);
            this.dataStore.put(LAST_UPDATE_TIME, System.currentTimeMillis());
            this.dataStore.remove(LAST_MESSAGE_REFRESH_TIME);
            this.user.onCreated(str2, str, id);
            return true;
        } catch (JsonException unused) {
            Logger.error("InboxJobHandler - Unable to parse Rich Push user response: %s", safeExecute);
            return false;
        }
    }

    private boolean updateUser() {
        String id = this.channel.getId();
        if (UAStringUtil.isEmpty(id)) {
            Logger.debug("InboxJobHandler - No Channel. Skipping Rich Push user update.", new Object[0]);
            return false;
        }
        URL userApiUrl = getUserApiUrl(this.runtimeConfig.getUrlConfig(), this.user.getId());
        if (userApiUrl == null) {
            Logger.debug("User URL null, unable to update user.", new Object[0]);
            return false;
        }
        String createUpdateUserPayload = createUpdateUserPayload(id);
        Logger.verbose("InboxJobHandler - Updating user with payload: %s", createUpdateUserPayload);
        Response<Void> safeExecute = this.requestFactory.createRequest(DefaultHttpClient.METHOD_POST, userApiUrl).setCredentials(this.user.getId(), this.user.getPassword()).setRequestBody(createUpdateUserPayload, "application/json").setHeader("Accept", "application/vnd.urbanairship+json; version=3;").safeExecute();
        Logger.verbose("InboxJobHandler - Update Rich Push user response: %s", safeExecute);
        if (safeExecute == null || safeExecute.getStatus() != 200) {
            this.dataStore.put(LAST_UPDATE_TIME, 0);
            return false;
        }
        Logger.info("Rich Push user updated.", new Object[0]);
        this.dataStore.put(LAST_UPDATE_TIME, System.currentTimeMillis());
        this.user.onUpdated(id);
        return true;
    }

    private String createNewUserPayload(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put(getPayloadChannelsKey(), Collections.singletonList(str));
        return JsonValue.wrapOpt(hashMap).toString();
    }

    private String createUpdateUserPayload(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put(PAYLOAD_ADD_KEY, Collections.singletonList(str));
        HashMap hashMap2 = new HashMap();
        hashMap2.put(getPayloadChannelsKey(), hashMap);
        return JsonValue.wrapOpt(hashMap2).toString();
    }

    private String getPayloadChannelsKey() {
        return this.runtimeConfig.getPlatform() == 1 ? PAYLOAD_AMAZON_CHANNELS_KEY : PAYLOAD_ANDROID_CHANNELS_KEY;
    }

    private URL getUserApiUrl(AirshipUrlConfig airshipUrlConfig, String... strArr) {
        UrlBuilder appendEncodedPath = airshipUrlConfig.deviceUrl().appendEncodedPath(USER_API_PATH);
        for (String str : strArr) {
            if (!str.endsWith("/")) {
                str = str + "/";
            }
            appendEncodedPath.appendEncodedPath(str);
        }
        return appendEncodedPath.build();
    }
}
