package com.urbanairship.channel;

import android.content.Context;
import com.urbanairship.AirshipComponent;
import com.urbanairship.Logger;
import com.urbanairship.PreferenceDataStore;
import com.urbanairship.UAirship;
import com.urbanairship.channel.AirshipChannel;
import com.urbanairship.channel.ChannelRegistrationPayload;
import com.urbanairship.config.AirshipRuntimeConfig;
import com.urbanairship.http.RequestException;
import com.urbanairship.http.Response;
import com.urbanairship.job.JobDispatcher;
import com.urbanairship.job.JobInfo;
import com.urbanairship.util.Clock;
import com.urbanairship.util.UAStringUtil;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class NamedUser extends AirshipComponent {
    static final String ACTION_UPDATE_NAMED_USER = "ACTION_UPDATE_NAMED_USER";
    private static final String ATTRIBUTE_MUTATION_STORE_KEY = "com.urbanairship.nameduser.ATTRIBUTE_MUTATION_STORE_KEY";
    private static final String CHANGE_TOKEN_KEY = "com.urbanairship.nameduser.CHANGE_TOKEN_KEY";
    private static final String LAST_UPDATED_TOKEN_KEY = "com.urbanairship.nameduser.LAST_UPDATED_TOKEN_KEY";
    private static final int MAX_NAMED_USER_ID_LENGTH = 128;
    private static final String NAMED_USER_ID_KEY = "com.urbanairship.nameduser.NAMED_USER_ID_KEY";
    private static final String TAG_GROUP_MUTATIONS_KEY = "com.urbanairship.nameduser.PENDING_TAG_GROUP_MUTATIONS_KEY";
    private final AirshipChannel airshipChannel;
    /* access modifiers changed from: private */
    public final AttributeRegistrar attributeRegistrar;
    private final Clock clock;
    private final Object idLock;
    private final JobDispatcher jobDispatcher;
    private final NamedUserApiClient namedUserApiClient;
    private final List<NamedUserListener> namedUserListeners;
    private final PreferenceDataStore preferenceDataStore;
    /* access modifiers changed from: private */
    public final TagGroupRegistrar tagGroupRegistrar;

    public int getComponentGroup() {
        return 5;
    }

    public NamedUser(Context context, PreferenceDataStore preferenceDataStore2, AirshipRuntimeConfig airshipRuntimeConfig, AirshipChannel airshipChannel2) {
        this(context, preferenceDataStore2, airshipChannel2, JobDispatcher.shared(context), Clock.DEFAULT_CLOCK, new NamedUserApiClient(airshipRuntimeConfig), new AttributeRegistrar(AttributeApiClient.namedUserClient(airshipRuntimeConfig), new PendingAttributeMutationStore(preferenceDataStore2, ATTRIBUTE_MUTATION_STORE_KEY)), new TagGroupRegistrar(TagGroupApiClient.namedUserClient(airshipRuntimeConfig), new PendingTagGroupMutationStore(preferenceDataStore2, TAG_GROUP_MUTATIONS_KEY)));
    }

    NamedUser(Context context, PreferenceDataStore preferenceDataStore2, AirshipChannel airshipChannel2, JobDispatcher jobDispatcher2, Clock clock2, NamedUserApiClient namedUserApiClient2, AttributeRegistrar attributeRegistrar2, TagGroupRegistrar tagGroupRegistrar2) {
        super(context, preferenceDataStore2);
        this.idLock = new Object();
        this.namedUserListeners = new CopyOnWriteArrayList();
        this.preferenceDataStore = preferenceDataStore2;
        this.airshipChannel = airshipChannel2;
        this.jobDispatcher = jobDispatcher2;
        this.clock = clock2;
        this.namedUserApiClient = namedUserApiClient2;
        this.attributeRegistrar = attributeRegistrar2;
        this.tagGroupRegistrar = tagGroupRegistrar2;
    }

    /* access modifiers changed from: protected */
    public void init() {
        super.init();
        this.tagGroupRegistrar.setId(getId(), false);
        this.attributeRegistrar.setId(getId(), false);
        this.airshipChannel.addChannelListener(new AirshipChannelListener() {
            public void onChannelUpdated(String str) {
            }

            public void onChannelCreated(String str) {
                NamedUser.this.dispatchNamedUserUpdateJob();
            }
        });
        this.airshipChannel.addChannelRegistrationPayloadExtender(new AirshipChannel.ChannelRegistrationPayloadExtender() {
            public ChannelRegistrationPayload.Builder extend(ChannelRegistrationPayload.Builder builder) {
                return builder.setNamedUserId(NamedUser.this.getId());
            }
        });
        if (this.airshipChannel.getId() == null) {
            return;
        }
        if (!isIdUpToDate() || getId() != null) {
            dispatchNamedUserUpdateJob();
        }
    }

    public int onPerformJob(UAirship uAirship, JobInfo jobInfo) {
        if (ACTION_UPDATE_NAMED_USER.equals(jobInfo.getAction())) {
            return onUpdateNamedUser();
        }
        return 0;
    }

    public List<TagGroupsMutation> getPendingTagUpdates() {
        return this.tagGroupRegistrar.getPendingMutations();
    }

    public List<AttributeMutation> getPendingAttributeUpdates() {
        return this.attributeRegistrar.getPendingMutations();
    }

    public String getId() {
        return this.preferenceDataStore.getString(NAMED_USER_ID_KEY, (String) null);
    }

    public void forceUpdate() {
        Logger.debug("NamedUser - force named user update.", new Object[0]);
        updateChangeToken();
        dispatchNamedUserUpdateJob();
    }

    public void setId(String str) {
        if (str == null || isDataCollectionEnabled()) {
            String str2 = null;
            if (!UAStringUtil.isEmpty(str)) {
                str2 = str.trim();
                if (UAStringUtil.isEmpty(str2) || str2.length() > 128) {
                    Logger.error("Failed to set named user ID. The named user ID must be composedof non-whitespace characters and be less than 129 characters in length.", new Object[0]);
                    return;
                }
            }
            synchronized (this.idLock) {
                if (!UAStringUtil.equals(getId(), str2)) {
                    this.preferenceDataStore.put(NAMED_USER_ID_KEY, str2);
                    updateChangeToken();
                    this.attributeRegistrar.setId(getId(), true);
                    this.tagGroupRegistrar.setId(getId(), true);
                    dispatchNamedUserUpdateJob();
                    if (str2 != null) {
                        this.airshipChannel.updateRegistration();
                    }
                    for (NamedUserListener onNamedUserIdChanged : this.namedUserListeners) {
                        onNamedUserIdChanged.onNamedUserIdChanged(str2);
                    }
                } else {
                    Logger.debug("NamedUser - Skipping update. Named user ID trimmed already matches existing named user: %s", getId());
                }
            }
            return;
        }
        Logger.debug("NamedUser - Data collection is disabled, ignoring named user association.", new Object[0]);
    }

    public TagGroupsEditor editTagGroups() {
        return new TagGroupsEditor() {
            /* access modifiers changed from: protected */
            public void onApply(List<TagGroupsMutation> list) {
                if (!NamedUser.this.isDataCollectionEnabled()) {
                    Logger.warn("NamedUser - Unable to apply tag group edits when data collection is disabled.", new Object[0]);
                } else if (!list.isEmpty()) {
                    NamedUser.this.tagGroupRegistrar.addPendingMutations(list);
                    NamedUser.this.dispatchNamedUserUpdateJob();
                }
            }
        };
    }

    public AttributeEditor editAttributes() {
        return new AttributeEditor(this.clock) {
            /* access modifiers changed from: protected */
            public void onApply(List<AttributeMutation> list) {
                if (!NamedUser.this.isDataCollectionEnabled()) {
                    Logger.info("Ignore attributes, data opted out.", new Object[0]);
                } else if (!list.isEmpty()) {
                    NamedUser.this.attributeRegistrar.addPendingMutations(list);
                    NamedUser.this.dispatchNamedUserUpdateJob();
                }
            }
        };
    }

    public void addTagGroupListener(TagGroupListener tagGroupListener) {
        this.tagGroupRegistrar.addTagGroupListener(tagGroupListener);
    }

    public void addAttributeListener(AttributeListener attributeListener) {
        this.attributeRegistrar.addAttributeListener(attributeListener);
    }

    public void addNamedUserListener(NamedUserListener namedUserListener) {
        this.namedUserListeners.add(namedUserListener);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0026, code lost:
        return r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isIdUpToDate() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.idLock
            monitor-enter(r0)
            java.lang.String r1 = r5.getChangeToken()     // Catch:{ all -> 0x0027 }
            com.urbanairship.PreferenceDataStore r2 = r5.preferenceDataStore     // Catch:{ all -> 0x0027 }
            java.lang.String r3 = "com.urbanairship.nameduser.LAST_UPDATED_TOKEN_KEY"
            r4 = 0
            java.lang.String r2 = r2.getString(r3, r4)     // Catch:{ all -> 0x0027 }
            java.lang.String r3 = r5.getId()     // Catch:{ all -> 0x0027 }
            r4 = 1
            if (r3 != 0) goto L_0x001b
            if (r1 != 0) goto L_0x001b
            monitor-exit(r0)     // Catch:{ all -> 0x0027 }
            return r4
        L_0x001b:
            if (r2 == 0) goto L_0x0024
            boolean r1 = r2.equals(r1)     // Catch:{ all -> 0x0027 }
            if (r1 == 0) goto L_0x0024
            goto L_0x0025
        L_0x0024:
            r4 = 0
        L_0x0025:
            monitor-exit(r0)     // Catch:{ all -> 0x0027 }
            return r4
        L_0x0027:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0027 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.urbanairship.channel.NamedUser.isIdUpToDate():boolean");
    }

    private String getChangeToken() {
        return this.preferenceDataStore.getString(CHANGE_TOKEN_KEY, (String) null);
    }

    private void updateChangeToken() {
        this.preferenceDataStore.put(CHANGE_TOKEN_KEY, UUID.randomUUID().toString());
    }

    /* access modifiers changed from: package-private */
    public void dispatchNamedUserUpdateJob() {
        this.jobDispatcher.dispatch(JobInfo.newBuilder().setAction(ACTION_UPDATE_NAMED_USER).setId(2).setNetworkAccessRequired(true).setAirshipComponent((Class<? extends AirshipComponent>) NamedUser.class).build());
    }

    /* access modifiers changed from: protected */
    public void onDataCollectionEnabledChanged(boolean z) {
        if (!z) {
            this.attributeRegistrar.clearPendingMutations();
            this.tagGroupRegistrar.clearPendingMutations();
            setId((String) null);
        }
    }

    private int onUpdateNamedUser() {
        int updateNamedUserId;
        String id = this.airshipChannel.getId();
        if (UAStringUtil.isEmpty(id)) {
            Logger.verbose("NamedUser - The channel ID does not exist. Will retry when channel ID is available.", new Object[0]);
            return 0;
        } else if (!isIdUpToDate() && (updateNamedUserId = updateNamedUserId(id)) != 0) {
            return updateNamedUserId;
        } else {
            if (isIdUpToDate() && getId() != null) {
                boolean uploadPendingMutations = this.attributeRegistrar.uploadPendingMutations();
                boolean uploadPendingMutations2 = this.tagGroupRegistrar.uploadPendingMutations();
                if (!uploadPendingMutations || !uploadPendingMutations2) {
                    return 1;
                }
            }
            return 0;
        }
    }

    private int updateNamedUserId(String str) {
        String changeToken;
        String id;
        Response<Void> response;
        synchronized (this.idLock) {
            changeToken = getChangeToken();
            id = getId();
        }
        if (id == null) {
            try {
                response = this.namedUserApiClient.disassociate(str);
            } catch (RequestException e) {
                Logger.debug(e, "NamedUser - Update named user failed, will retry.", new Object[0]);
                return 1;
            }
        } else {
            response = this.namedUserApiClient.associate(id, str);
        }
        if (response.isServerError() || response.isTooManyRequestsError()) {
            Logger.debug("Update named user failed. Too many requests. Will retry.", new Object[0]);
            return 1;
        } else if (response.getStatus() == 403) {
            Logger.debug("Update named user failed with response: %s.This action is not allowed when the app is in server-only mode.", response);
            return 0;
        } else if (response.isSuccessful()) {
            Logger.debug("Update named user succeeded with status: %s", Integer.valueOf(response.getStatus()));
            this.preferenceDataStore.put(LAST_UPDATED_TOKEN_KEY, changeToken);
            return 0;
        } else {
            Logger.debug("Update named user failed with response: %s", response);
            return 0;
        }
    }
}
