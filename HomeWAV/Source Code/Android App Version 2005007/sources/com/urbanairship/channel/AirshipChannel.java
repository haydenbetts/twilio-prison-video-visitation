package com.urbanairship.channel;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.stripe.android.view.ShippingInfoWidget;
import com.urbanairship.AirshipComponent;
import com.urbanairship.Logger;
import com.urbanairship.PreferenceDataStore;
import com.urbanairship.UAirship;
import com.urbanairship.channel.ChannelRegistrationPayload;
import com.urbanairship.config.AirshipRuntimeConfig;
import com.urbanairship.http.RequestException;
import com.urbanairship.http.Response;
import com.urbanairship.job.JobDispatcher;
import com.urbanairship.job.JobInfo;
import com.urbanairship.json.JsonException;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.json.JsonValue;
import com.urbanairship.locale.LocaleChangedListener;
import com.urbanairship.locale.LocaleManager;
import com.urbanairship.util.Clock;
import com.urbanairship.util.UAStringUtil;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.CopyOnWriteArrayList;

public class AirshipChannel extends AirshipComponent {
    public static final String ACTION_CHANNEL_CREATED = "com.urbanairship.CHANNEL_CREATED";
    private static final String ACTION_UPDATE_CHANNEL = "ACTION_UPDATE_CHANNEL";
    private static final String APID_KEY = "com.urbanairship.push.APID";
    private static final String ATTRIBUTE_DATASTORE_KEY = "com.urbanairship.push.ATTRIBUTE_DATA_STORE";
    private static final String CHANNEL_ID_KEY = "com.urbanairship.push.CHANNEL_ID";
    private static final long CHANNEL_REREGISTRATION_INTERVAL_MS = 86400000;
    private static final String LAST_REGISTRATION_PAYLOAD_KEY = "com.urbanairship.push.LAST_REGISTRATION_PAYLOAD";
    private static final String LAST_REGISTRATION_TIME_KEY = "com.urbanairship.push.LAST_REGISTRATION_TIME";
    private static final String TAGS_KEY = "com.urbanairship.push.TAGS";
    private static final String TAG_GROUP_DATASTORE_KEY = "com.urbanairship.push.PENDING_TAG_GROUP_MUTATIONS";
    private final String DEFAULT_TAG_GROUP;
    private final List<AirshipChannelListener> airshipChannelListeners;
    /* access modifiers changed from: private */
    public final AttributeRegistrar attributeRegistrar;
    private final ChannelApiClient channelApiClient;
    private boolean channelCreationDelayEnabled;
    private final List<ChannelRegistrationPayloadExtender> channelRegistrationPayloadExtenders;
    /* access modifiers changed from: private */
    public boolean channelTagRegistrationEnabled;
    private final Clock clock;
    private final JobDispatcher jobDispatcher;
    private final LocaleManager localeManager;
    private final AirshipRuntimeConfig runtimeConfig;
    /* access modifiers changed from: private */
    public final TagGroupRegistrar tagGroupRegistrar;
    /* access modifiers changed from: private */
    public final Object tagLock;

    public interface ChannelRegistrationPayloadExtender {
        ChannelRegistrationPayload.Builder extend(ChannelRegistrationPayload.Builder builder);
    }

    public int getComponentGroup() {
        return 7;
    }

    public AirshipChannel(Context context, PreferenceDataStore preferenceDataStore, AirshipRuntimeConfig airshipRuntimeConfig, LocaleManager localeManager2) {
        this(context, preferenceDataStore, airshipRuntimeConfig, localeManager2, JobDispatcher.shared(context), Clock.DEFAULT_CLOCK, new ChannelApiClient(airshipRuntimeConfig), new AttributeRegistrar(AttributeApiClient.channelClient(airshipRuntimeConfig), new PendingAttributeMutationStore(preferenceDataStore, ATTRIBUTE_DATASTORE_KEY)), new TagGroupRegistrar(TagGroupApiClient.channelClient(airshipRuntimeConfig), new PendingTagGroupMutationStore(preferenceDataStore, TAG_GROUP_DATASTORE_KEY)));
    }

    AirshipChannel(Context context, PreferenceDataStore preferenceDataStore, AirshipRuntimeConfig airshipRuntimeConfig, LocaleManager localeManager2, JobDispatcher jobDispatcher2, Clock clock2, ChannelApiClient channelApiClient2, AttributeRegistrar attributeRegistrar2, TagGroupRegistrar tagGroupRegistrar2) {
        super(context, preferenceDataStore);
        this.DEFAULT_TAG_GROUP = "device";
        this.airshipChannelListeners = new CopyOnWriteArrayList();
        this.channelRegistrationPayloadExtenders = new CopyOnWriteArrayList();
        this.tagLock = new Object();
        this.channelTagRegistrationEnabled = true;
        this.runtimeConfig = airshipRuntimeConfig;
        this.localeManager = localeManager2;
        this.jobDispatcher = jobDispatcher2;
        this.channelApiClient = channelApiClient2;
        this.attributeRegistrar = attributeRegistrar2;
        this.tagGroupRegistrar = tagGroupRegistrar2;
        this.clock = clock2;
    }

    /* access modifiers changed from: protected */
    public void init() {
        super.init();
        boolean z = false;
        this.tagGroupRegistrar.setId(getId(), false);
        this.attributeRegistrar.setId(getId(), false);
        if (Logger.getLogLevel() < 7 && !UAStringUtil.isEmpty(getId())) {
            Log.d(UAirship.getAppName() + " Channel ID", getId());
        }
        if (getId() == null && this.runtimeConfig.getConfigOptions().channelCreationDelayEnabled) {
            z = true;
        }
        this.channelCreationDelayEnabled = z;
    }

    /* access modifiers changed from: protected */
    public void onAirshipReady(UAirship uAirship) {
        super.onAirshipReady(uAirship);
        this.localeManager.addListener(new LocaleChangedListener() {
            public void onLocaleChanged(Locale locale) {
                AirshipChannel.this.dispatchUpdateJob();
            }
        });
        if (getId() != null || !this.channelCreationDelayEnabled) {
            dispatchUpdateJob();
        }
    }

    public void addChannelRegistrationPayloadExtender(ChannelRegistrationPayloadExtender channelRegistrationPayloadExtender) {
        this.channelRegistrationPayloadExtenders.add(channelRegistrationPayloadExtender);
    }

    public int onPerformJob(UAirship uAirship, JobInfo jobInfo) {
        if (!ACTION_UPDATE_CHANNEL.equals(jobInfo.getAction())) {
            return 0;
        }
        if (getId() != null || !this.channelCreationDelayEnabled) {
            return onUpdateChannel();
        }
        Logger.debug("AirshipChannel - Channel registration is currently disabled.", new Object[0]);
        return 0;
    }

    public void onComponentEnableChange(boolean z) {
        if (z) {
            dispatchUpdateJob();
        }
    }

    public String getId() {
        return getDataStore().getString(CHANNEL_ID_KEY, (String) null);
    }

    public void addChannelListener(AirshipChannelListener airshipChannelListener) {
        this.airshipChannelListeners.add(airshipChannelListener);
    }

    public void removeChannelListener(AirshipChannelListener airshipChannelListener) {
        this.airshipChannelListeners.remove(airshipChannelListener);
    }

    public void addTagGroupListener(TagGroupListener tagGroupListener) {
        this.tagGroupRegistrar.addTagGroupListener(tagGroupListener);
    }

    public void addAttributeListener(AttributeListener attributeListener) {
        this.attributeRegistrar.addAttributeListener(attributeListener);
    }

    public TagEditor editTags() {
        return new TagEditor() {
            /* access modifiers changed from: protected */
            public void onApply(boolean z, Set<String> set, Set<String> set2) {
                if (AirshipChannel.this.isDataCollectionEnabled()) {
                    synchronized (AirshipChannel.this.tagLock) {
                        Set hashSet = z ? new HashSet() : AirshipChannel.this.getTags();
                        hashSet.addAll(set);
                        hashSet.removeAll(set2);
                        AirshipChannel.this.setTags(hashSet);
                    }
                    return;
                }
                Logger.warn("AirshipChannel - Unable to apply tag edits when opted out of data collection.", new Object[0]);
            }
        };
    }

    public TagGroupsEditor editTagGroups() {
        return new TagGroupsEditor() {
            /* access modifiers changed from: protected */
            public boolean allowTagGroupChange(String str) {
                if (!AirshipChannel.this.channelTagRegistrationEnabled || !"device".equals(str)) {
                    return true;
                }
                Logger.error("Unable to add tags to `%s` tag group when `channelTagRegistrationEnabled` is true.", "device");
                return false;
            }

            /* access modifiers changed from: protected */
            public void onApply(List<TagGroupsMutation> list) {
                if (!AirshipChannel.this.isDataCollectionEnabled()) {
                    Logger.warn("AirshipChannel - Unable to apply tag group edits when opted out of data collection.", new Object[0]);
                } else if (!list.isEmpty()) {
                    AirshipChannel.this.tagGroupRegistrar.addPendingMutations(list);
                    AirshipChannel.this.dispatchUpdateJob();
                }
            }
        };
    }

    public AttributeEditor editAttributes() {
        return new AttributeEditor(this.clock) {
            /* access modifiers changed from: protected */
            public void onApply(List<AttributeMutation> list) {
                if (!AirshipChannel.this.isDataCollectionEnabled()) {
                    Logger.info("Ignore attributes, data opted out.", new Object[0]);
                } else if (!list.isEmpty()) {
                    AirshipChannel.this.attributeRegistrar.addPendingMutations(list);
                    AirshipChannel.this.dispatchUpdateJob();
                }
            }
        };
    }

    public void setTags(Set<String> set) {
        if (isDataCollectionEnabled()) {
            synchronized (this.tagLock) {
                getDataStore().put(TAGS_KEY, JsonValue.wrapOpt(TagUtils.normalizeTags(set)));
            }
            dispatchUpdateJob();
            return;
        }
        Logger.warn("AirshipChannel - Unable to set tags when opted out of data collection.", new Object[0]);
    }

    public Set<String> getTags() {
        Set<String> normalizeTags;
        synchronized (this.tagLock) {
            HashSet hashSet = new HashSet();
            JsonValue jsonValue = getDataStore().getJsonValue(TAGS_KEY);
            if (jsonValue.isJsonList()) {
                Iterator<JsonValue> it = jsonValue.optList().iterator();
                while (it.hasNext()) {
                    JsonValue next = it.next();
                    if (next.isString()) {
                        hashSet.add(next.getString());
                    }
                }
            }
            normalizeTags = TagUtils.normalizeTags(hashSet);
            if (hashSet.size() != normalizeTags.size()) {
                setTags(normalizeTags);
            }
        }
        return normalizeTags;
    }

    public void enableChannelCreation() {
        if (isChannelCreationDelayEnabled()) {
            this.channelCreationDelayEnabled = false;
            dispatchUpdateJob();
        }
    }

    public void updateRegistration() {
        dispatchUpdateJob();
    }

    private ChannelRegistrationPayload getNextChannelRegistrationPayload() {
        boolean channelTagRegistrationEnabled2 = getChannelTagRegistrationEnabled();
        ChannelRegistrationPayload.Builder apid = new ChannelRegistrationPayload.Builder().setTags(channelTagRegistrationEnabled2, channelTagRegistrationEnabled2 ? getTags() : null).setApid(getDataStore().getString(APID_KEY, (String) null));
        int platform = this.runtimeConfig.getPlatform();
        if (platform == 1) {
            apid.setDeviceType(ChannelRegistrationPayload.AMAZON_DEVICE_TYPE);
        } else if (platform == 2) {
            apid.setDeviceType(ChannelRegistrationPayload.ANDROID_DEVICE_TYPE);
        }
        apid.setTimezone(TimeZone.getDefault().getID());
        Locale locale = this.localeManager.getLocale();
        if (!UAStringUtil.isEmpty(locale.getCountry())) {
            apid.setCountry(locale.getCountry());
        }
        if (!UAStringUtil.isEmpty(locale.getLanguage())) {
            apid.setLanguage(locale.getLanguage());
        }
        if (UAirship.getPackageInfo() != null) {
            apid.setAppVersion(UAirship.getPackageInfo().versionName);
        }
        apid.setSdkVersion(UAirship.getVersion());
        if (isDataCollectionEnabled()) {
            TelephonyManager telephonyManager = (TelephonyManager) UAirship.getApplicationContext().getSystemService(ShippingInfoWidget.PHONE_FIELD);
            if (telephonyManager != null) {
                apid.setCarrier(telephonyManager.getNetworkOperatorName());
            }
            apid.setDeviceModel(Build.MODEL);
            apid.setApiVersion(Integer.valueOf(Build.VERSION.SDK_INT));
        }
        for (ChannelRegistrationPayloadExtender extend : this.channelRegistrationPayloadExtenders) {
            apid = extend.extend(apid);
        }
        return apid.build();
    }

    public boolean getChannelTagRegistrationEnabled() {
        return this.channelTagRegistrationEnabled;
    }

    public void setChannelTagRegistrationEnabled(boolean z) {
        this.channelTagRegistrationEnabled = z;
    }

    public List<TagGroupsMutation> getPendingTagUpdates() {
        return this.tagGroupRegistrar.getPendingMutations();
    }

    public List<AttributeMutation> getPendingAttributeUpdates() {
        return this.attributeRegistrar.getPendingMutations();
    }

    /* access modifiers changed from: package-private */
    public boolean isChannelCreationDelayEnabled() {
        return this.channelCreationDelayEnabled;
    }

    private boolean shouldUpdateRegistration(ChannelRegistrationPayload channelRegistrationPayload) {
        ChannelRegistrationPayload lastRegistrationPayload = getLastRegistrationPayload();
        if (lastRegistrationPayload == null) {
            Logger.verbose("AirshipChannel - Should update registration. Last payload is null.", new Object[0]);
            return true;
        } else if (System.currentTimeMillis() - getLastRegistrationTime() >= CHANNEL_REREGISTRATION_INTERVAL_MS) {
            Logger.verbose("AirshipChannel - Should update registration. Time since last registration time is greater than 24 hours.", new Object[0]);
            return true;
        } else if (channelRegistrationPayload.equals(lastRegistrationPayload)) {
            return false;
        } else {
            Logger.verbose("AirshipChannel - Should update registration. Channel registration payload has changed.", new Object[0]);
            return true;
        }
    }

    private void setLastRegistrationPayload(ChannelRegistrationPayload channelRegistrationPayload) {
        getDataStore().put(LAST_REGISTRATION_PAYLOAD_KEY, (JsonSerializable) channelRegistrationPayload);
        getDataStore().put(LAST_REGISTRATION_TIME_KEY, System.currentTimeMillis());
    }

    private ChannelRegistrationPayload getLastRegistrationPayload() {
        JsonValue jsonValue = getDataStore().getJsonValue(LAST_REGISTRATION_PAYLOAD_KEY);
        if (jsonValue.isNull()) {
            return null;
        }
        try {
            return ChannelRegistrationPayload.fromJson(jsonValue);
        } catch (JsonException e) {
            Logger.error(e, "AirshipChannel - Failed to parse payload from JSON.", new Object[0]);
            return null;
        }
    }

    private long getLastRegistrationTime() {
        long j = getDataStore().getLong(LAST_REGISTRATION_TIME_KEY, 0);
        if (j <= System.currentTimeMillis()) {
            return j;
        }
        Logger.verbose("Resetting last registration time.", new Object[0]);
        getDataStore().put(LAST_REGISTRATION_TIME_KEY, 0);
        return 0;
    }

    private int onCreateChannel() {
        ChannelRegistrationPayload nextChannelRegistrationPayload = getNextChannelRegistrationPayload();
        try {
            Response<String> createChannelWithPayload = this.channelApiClient.createChannelWithPayload(nextChannelRegistrationPayload);
            if (createChannelWithPayload.isSuccessful()) {
                String result = createChannelWithPayload.getResult();
                Logger.info("Airship channel created: %s", result);
                getDataStore().put(CHANNEL_ID_KEY, result);
                this.tagGroupRegistrar.setId(result, false);
                this.attributeRegistrar.setId(result, false);
                setLastRegistrationPayload(nextChannelRegistrationPayload);
                for (AirshipChannelListener onChannelCreated : this.airshipChannelListeners) {
                    onChannelCreated.onChannelCreated(result);
                }
                if (this.runtimeConfig.getConfigOptions().extendedBroadcastsEnabled) {
                    Intent addCategory = new Intent(ACTION_CHANNEL_CREATED).setPackage(UAirship.getPackageName()).addCategory(UAirship.getPackageName());
                    addCategory.putExtra("channel_id", result);
                    getContext().sendBroadcast(addCategory);
                }
                return 0;
            } else if (createChannelWithPayload.isServerError() || createChannelWithPayload.isTooManyRequestsError()) {
                Logger.debug("Channel registration failed with status: %s, will retry", Integer.valueOf(createChannelWithPayload.getStatus()));
                return 1;
            } else {
                Logger.debug("Channel registration failed with status: %s", Integer.valueOf(createChannelWithPayload.getStatus()));
                return 0;
            }
        } catch (RequestException e) {
            Logger.debug(e, "Channel registration failed, will retry", new Object[0]);
            return 1;
        }
    }

    private int onUpdateChannel() {
        String id = getId();
        int onCreateChannel = id == null ? onCreateChannel() : updateChannelRegistration(id);
        if (onCreateChannel != 0) {
            return onCreateChannel;
        }
        if (getId() == null) {
            return 0;
        }
        return (!this.attributeRegistrar.uploadPendingMutations() || !this.tagGroupRegistrar.uploadPendingMutations()) ? 1 : 0;
    }

    private int updateChannelRegistration(String str) {
        ChannelRegistrationPayload nextChannelRegistrationPayload = getNextChannelRegistrationPayload();
        if (!shouldUpdateRegistration(nextChannelRegistrationPayload)) {
            Logger.verbose("AirshipChannel - Channel already up to date.", new Object[0]);
            return 0;
        }
        Logger.verbose("AirshipChannel - Performing channel registration.", new Object[0]);
        try {
            Response<Void> updateChannelWithPayload = this.channelApiClient.updateChannelWithPayload(str, nextChannelRegistrationPayload.minimizedPayload(getLastRegistrationPayload()));
            if (updateChannelWithPayload.isSuccessful()) {
                Logger.info("Airship channel updated.", new Object[0]);
                setLastRegistrationPayload(nextChannelRegistrationPayload);
                for (AirshipChannelListener onChannelUpdated : this.airshipChannelListeners) {
                    onChannelUpdated.onChannelUpdated(getId());
                }
                return 0;
            } else if (updateChannelWithPayload.isServerError() || updateChannelWithPayload.isTooManyRequestsError()) {
                Logger.debug("Channel registration failed with status: %s, will retry", Integer.valueOf(updateChannelWithPayload.getStatus()));
                return 1;
            } else if (updateChannelWithPayload.getStatus() == 409) {
                Logger.debug("Channel registration failed with status: %s, will clear channel ID and create a new channel.", Integer.valueOf(updateChannelWithPayload.getStatus()));
                setLastRegistrationPayload((ChannelRegistrationPayload) null);
                getDataStore().remove(CHANNEL_ID_KEY);
                return onCreateChannel();
            } else {
                Logger.debug("Channel registration failed with status: %s", Integer.valueOf(updateChannelWithPayload.getStatus()));
                return 0;
            }
        } catch (RequestException e) {
            Logger.debug(e, "Channel registration failed, will retry", new Object[0]);
            return 1;
        }
    }

    /* access modifiers changed from: private */
    public void dispatchUpdateJob() {
        this.jobDispatcher.dispatch(JobInfo.newBuilder().setAction(ACTION_UPDATE_CHANNEL).setId(5).setNetworkAccessRequired(true).setAirshipComponent((Class<? extends AirshipComponent>) AirshipChannel.class).build());
    }

    /* access modifiers changed from: protected */
    public void onDataCollectionEnabledChanged(boolean z) {
        if (!z) {
            synchronized (this.tagLock) {
                getDataStore().remove(TAGS_KEY);
            }
            this.tagGroupRegistrar.clearPendingMutations();
            this.attributeRegistrar.clearPendingMutations();
        }
        updateRegistration();
    }
}
