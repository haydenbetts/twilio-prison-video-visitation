package com.urbanairship.messagecenter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.urbanairship.AirshipComponent;
import com.urbanairship.AirshipExecutors;
import com.urbanairship.Cancelable;
import com.urbanairship.CancelableOperation;
import com.urbanairship.Logger;
import com.urbanairship.Predicate;
import com.urbanairship.PreferenceDataStore;
import com.urbanairship.UAirship;
import com.urbanairship.app.ActivityMonitor;
import com.urbanairship.app.ApplicationListener;
import com.urbanairship.app.GlobalActivityMonitor;
import com.urbanairship.channel.AirshipChannel;
import com.urbanairship.channel.AirshipChannelListener;
import com.urbanairship.channel.ChannelRegistrationPayload;
import com.urbanairship.job.JobDispatcher;
import com.urbanairship.job.JobInfo;
import com.urbanairship.json.JsonMap;
import com.urbanairship.messagecenter.User;
import com.urbanairship.util.UAStringUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;

public class Inbox {
    private static final SentAtRichPushMessageComparator MESSAGE_COMPARATOR = new SentAtRichPushMessageComparator();
    private static final Object inboxLock = new Object();
    private final ActivityMonitor activityMonitor;
    private final AirshipChannel airshipChannel;
    private final Context context;
    private final PreferenceDataStore dataStore;
    private final Set<String> deletedMessageIds;
    private final Executor executor;
    private final Handler handler;
    private InboxJobHandler inboxJobHandler;
    private boolean isFetchingMessages;
    private final JobDispatcher jobDispatcher;
    private final ApplicationListener listener;
    /* access modifiers changed from: private */
    public final List<InboxListener> listeners;
    /* access modifiers changed from: private */
    public final MessageCenterResolver messageCenterResolver;
    private final Map<String, Message> messageUrlMap;
    private final List<PendingFetchMessagesCallback> pendingFetchCallbacks;
    private final Map<String, Message> readMessages;
    private final Map<String, Message> unreadMessages;
    /* access modifiers changed from: private */
    public final User user;

    public interface FetchMessagesCallback {
        void onFinished(boolean z);
    }

    public Inbox(Context context2, PreferenceDataStore preferenceDataStore, AirshipChannel airshipChannel2) {
        this(context2, preferenceDataStore, JobDispatcher.shared(context2), new User(preferenceDataStore, airshipChannel2), new MessageCenterResolver(context2), AirshipExecutors.newSerialExecutor(), GlobalActivityMonitor.shared(context2), airshipChannel2);
    }

    Inbox(Context context2, PreferenceDataStore preferenceDataStore, final JobDispatcher jobDispatcher2, User user2, MessageCenterResolver messageCenterResolver2, Executor executor2, ActivityMonitor activityMonitor2, AirshipChannel airshipChannel2) {
        this.listeners = new CopyOnWriteArrayList();
        this.deletedMessageIds = new HashSet();
        this.unreadMessages = new HashMap();
        this.readMessages = new HashMap();
        this.messageUrlMap = new HashMap();
        this.handler = new Handler(Looper.getMainLooper());
        this.isFetchingMessages = false;
        this.pendingFetchCallbacks = new ArrayList();
        this.context = context2.getApplicationContext();
        this.dataStore = preferenceDataStore;
        this.user = user2;
        this.messageCenterResolver = messageCenterResolver2;
        this.executor = executor2;
        this.jobDispatcher = jobDispatcher2;
        this.airshipChannel = airshipChannel2;
        this.listener = new ApplicationListener() {
            public void onForeground(long j) {
                jobDispatcher2.dispatch(JobInfo.newBuilder().setAction("ACTION_RICH_PUSH_MESSAGES_UPDATE").setAirshipComponent((Class<? extends AirshipComponent>) MessageCenter.class).build());
            }

            public void onBackground(long j) {
                jobDispatcher2.dispatch(JobInfo.newBuilder().setAction("ACTION_SYNC_MESSAGE_STATE").setId(9).setAirshipComponent((Class<? extends AirshipComponent>) MessageCenter.class).build());
            }
        };
        this.activityMonitor = activityMonitor2;
    }

    /* access modifiers changed from: package-private */
    public void init() {
        if (UAStringUtil.isEmpty(this.user.getId())) {
            this.user.addListener(new User.Listener() {
                public void onUserUpdated(boolean z) {
                    if (z) {
                        Inbox.this.user.removeListener(this);
                        Inbox.this.fetchMessages();
                    }
                }
            });
        }
        refresh(false);
        this.activityMonitor.addApplicationListener(this.listener);
        this.airshipChannel.addChannelListener(new AirshipChannelListener() {
            public void onChannelUpdated(String str) {
            }

            public void onChannelCreated(String str) {
                Inbox.this.dispatchUpdateUserJob(true);
            }
        });
        if (this.user.shouldUpdate()) {
            dispatchUpdateUserJob(true);
        }
        this.airshipChannel.addChannelRegistrationPayloadExtender(new AirshipChannel.ChannelRegistrationPayloadExtender() {
            public ChannelRegistrationPayload.Builder extend(ChannelRegistrationPayload.Builder builder) {
                return builder.setUserId(Inbox.this.getUser().getId());
            }
        });
    }

    /* access modifiers changed from: package-private */
    public int onPerformJob(UAirship uAirship, JobInfo jobInfo) {
        if (this.inboxJobHandler == null) {
            this.inboxJobHandler = new InboxJobHandler(this.context, this, getUser(), this.airshipChannel, uAirship.getRuntimeConfig(), this.dataStore);
        }
        return this.inboxJobHandler.performJob(jobInfo);
    }

    /* access modifiers changed from: package-private */
    public void tearDown() {
        this.activityMonitor.removeApplicationListener(this.listener);
    }

    public User getUser() {
        return this.user;
    }

    public void addListener(InboxListener inboxListener) {
        this.listeners.add(inboxListener);
    }

    public void removeListener(InboxListener inboxListener) {
        this.listeners.remove(inboxListener);
    }

    public void fetchMessages() {
        fetchMessages((Looper) null, (FetchMessagesCallback) null);
    }

    public Cancelable fetchMessages(FetchMessagesCallback fetchMessagesCallback) {
        return fetchMessages((Looper) null, fetchMessagesCallback);
    }

    public Cancelable fetchMessages(Looper looper, FetchMessagesCallback fetchMessagesCallback) {
        PendingFetchMessagesCallback pendingFetchMessagesCallback = new PendingFetchMessagesCallback(fetchMessagesCallback, looper);
        synchronized (this.pendingFetchCallbacks) {
            this.pendingFetchCallbacks.add(pendingFetchMessagesCallback);
            if (!this.isFetchingMessages) {
                this.jobDispatcher.dispatch(JobInfo.newBuilder().setAction("ACTION_RICH_PUSH_MESSAGES_UPDATE").setId(8).setAirshipComponent((Class<? extends AirshipComponent>) MessageCenter.class).build());
            }
            this.isFetchingMessages = true;
        }
        return pendingFetchMessagesCallback;
    }

    /* access modifiers changed from: package-private */
    public void onUpdateMessagesFinished(boolean z) {
        synchronized (this.pendingFetchCallbacks) {
            for (PendingFetchMessagesCallback next : this.pendingFetchCallbacks) {
                next.result = z;
                next.run();
            }
            this.isFetchingMessages = false;
            this.pendingFetchCallbacks.clear();
        }
    }

    public int getCount() {
        int size;
        synchronized (inboxLock) {
            size = this.unreadMessages.size() + this.readMessages.size();
        }
        return size;
    }

    public Set<String> getMessageIds() {
        HashSet hashSet;
        synchronized (inboxLock) {
            hashSet = new HashSet(getCount());
            hashSet.addAll(this.readMessages.keySet());
            hashSet.addAll(this.unreadMessages.keySet());
        }
        return hashSet;
    }

    public int getReadCount() {
        int size;
        synchronized (inboxLock) {
            size = this.readMessages.size();
        }
        return size;
    }

    public int getUnreadCount() {
        int size;
        synchronized (inboxLock) {
            size = this.unreadMessages.size();
        }
        return size;
    }

    private Collection<Message> filterMessages(Collection<Message> collection, Predicate<Message> predicate) {
        ArrayList arrayList = new ArrayList();
        if (predicate == null) {
            return collection;
        }
        for (Message next : collection) {
            if (predicate.apply(next)) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public List<Message> getMessages(Predicate<Message> predicate) {
        ArrayList arrayList;
        synchronized (inboxLock) {
            arrayList = new ArrayList();
            arrayList.addAll(filterMessages(this.unreadMessages.values(), predicate));
            arrayList.addAll(filterMessages(this.readMessages.values(), predicate));
            Collections.sort(arrayList, MESSAGE_COMPARATOR);
        }
        return arrayList;
    }

    public List<Message> getMessages() {
        return getMessages((Predicate<Message>) null);
    }

    public List<Message> getUnreadMessages(Predicate<Message> predicate) {
        ArrayList arrayList;
        synchronized (inboxLock) {
            arrayList = new ArrayList(filterMessages(this.unreadMessages.values(), predicate));
            Collections.sort(arrayList, MESSAGE_COMPARATOR);
        }
        return arrayList;
    }

    public List<Message> getUnreadMessages() {
        return getUnreadMessages((Predicate<Message>) null);
    }

    public List<Message> getReadMessages(Predicate<Message> predicate) {
        ArrayList arrayList;
        synchronized (inboxLock) {
            arrayList = new ArrayList(filterMessages(this.readMessages.values(), predicate));
            Collections.sort(arrayList, MESSAGE_COMPARATOR);
        }
        return arrayList;
    }

    public List<Message> getReadMessages() {
        return getReadMessages((Predicate<Message>) null);
    }

    public Message getMessage(String str) {
        if (str == null) {
            return null;
        }
        synchronized (inboxLock) {
            if (this.unreadMessages.containsKey(str)) {
                Message message = this.unreadMessages.get(str);
                return message;
            }
            Message message2 = this.readMessages.get(str);
            return message2;
        }
    }

    public Message getMessageByUrl(String str) {
        Message message;
        if (str == null) {
            return null;
        }
        synchronized (inboxLock) {
            message = this.messageUrlMap.get(str);
        }
        return message;
    }

    public void markMessagesRead(final Set<String> set) {
        this.executor.execute(new Runnable() {
            public void run() {
                Inbox.this.messageCenterResolver.markMessagesRead(set);
            }
        });
        synchronized (inboxLock) {
            for (String next : set) {
                Message message = this.unreadMessages.get(next);
                if (message != null) {
                    message.unreadClient = false;
                    this.unreadMessages.remove(next);
                    this.readMessages.put(next, message);
                }
            }
            notifyInboxUpdated();
        }
    }

    public void markMessagesUnread(final Set<String> set) {
        this.executor.execute(new Runnable() {
            public void run() {
                Inbox.this.messageCenterResolver.markMessagesUnread(set);
            }
        });
        synchronized (inboxLock) {
            for (String next : set) {
                Message message = this.readMessages.get(next);
                if (message != null) {
                    message.unreadClient = true;
                    this.readMessages.remove(next);
                    this.unreadMessages.put(next, message);
                }
            }
        }
        notifyInboxUpdated();
    }

    public void deleteMessages(final Set<String> set) {
        this.executor.execute(new Runnable() {
            public void run() {
                Inbox.this.messageCenterResolver.markMessagesDeleted(set);
            }
        });
        synchronized (inboxLock) {
            for (String next : set) {
                Message message = getMessage(next);
                if (message != null) {
                    message.deleted = true;
                    this.unreadMessages.remove(next);
                    this.readMessages.remove(next);
                    this.deletedMessageIds.add(next);
                }
            }
        }
        notifyInboxUpdated();
    }

    /* access modifiers changed from: package-private */
    public void refresh(boolean z) {
        List<Message> messages = this.messageCenterResolver.getMessages();
        synchronized (inboxLock) {
            HashSet hashSet = new HashSet(this.unreadMessages.keySet());
            HashSet hashSet2 = new HashSet(this.readMessages.keySet());
            HashSet hashSet3 = new HashSet(this.deletedMessageIds);
            this.unreadMessages.clear();
            this.readMessages.clear();
            this.messageUrlMap.clear();
            for (Message next : messages) {
                if (!next.isDeleted()) {
                    if (!hashSet3.contains(next.getMessageId())) {
                        if (next.isExpired()) {
                            this.deletedMessageIds.add(next.getMessageId());
                        } else {
                            this.messageUrlMap.put(next.getMessageBodyUrl(), next);
                            if (hashSet.contains(next.getMessageId())) {
                                next.unreadClient = true;
                                this.unreadMessages.put(next.getMessageId(), next);
                            } else if (hashSet2.contains(next.getMessageId())) {
                                next.unreadClient = false;
                                this.readMessages.put(next.getMessageId(), next);
                            } else if (next.unreadClient) {
                                this.unreadMessages.put(next.getMessageId(), next);
                            } else {
                                this.readMessages.put(next.getMessageId(), next);
                            }
                        }
                    }
                }
                this.deletedMessageIds.add(next.getMessageId());
            }
        }
        if (z) {
            notifyInboxUpdated();
        }
    }

    private void notifyInboxUpdated() {
        this.handler.post(new Runnable() {
            public void run() {
                for (InboxListener onInboxUpdated : Inbox.this.listeners) {
                    onInboxUpdated.onInboxUpdated();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void dispatchUpdateUserJob(boolean z) {
        Logger.debug("RichPushInbox - Updating user.", new Object[0]);
        this.jobDispatcher.dispatch(JobInfo.newBuilder().setAction("ACTION_RICH_PUSH_USER_UPDATE").setId(7).setAirshipComponent((Class<? extends AirshipComponent>) MessageCenter.class).setExtras(JsonMap.newBuilder().put("EXTRA_FORCEFULLY", z).build()).build());
    }

    static class SentAtRichPushMessageComparator implements Comparator<Message> {
        SentAtRichPushMessageComparator() {
        }

        public int compare(Message message, Message message2) {
            if (message2.getSentDateMS() == message.getSentDateMS()) {
                return message.getMessageId().compareTo(message2.getMessageId());
            }
            return Long.valueOf(message2.getSentDateMS()).compareTo(Long.valueOf(message.getSentDateMS()));
        }
    }

    static class PendingFetchMessagesCallback extends CancelableOperation {
        private final FetchMessagesCallback callback;
        boolean result;

        PendingFetchMessagesCallback(FetchMessagesCallback fetchMessagesCallback, Looper looper) {
            super(looper);
            this.callback = fetchMessagesCallback;
        }

        /* access modifiers changed from: protected */
        public void onRun() {
            FetchMessagesCallback fetchMessagesCallback = this.callback;
            if (fetchMessagesCallback != null) {
                fetchMessagesCallback.onFinished(this.result);
            }
        }
    }
}
