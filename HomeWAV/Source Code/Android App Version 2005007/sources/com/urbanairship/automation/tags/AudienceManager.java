package com.urbanairship.automation.tags;

import com.urbanairship.Logger;
import com.urbanairship.PreferenceDataStore;
import com.urbanairship.channel.AirshipChannel;
import com.urbanairship.channel.AttributeMutation;
import com.urbanairship.channel.NamedUser;
import com.urbanairship.channel.NamedUserListener;
import com.urbanairship.channel.TagGroupsMutation;
import com.urbanairship.config.AirshipRuntimeConfig;
import com.urbanairship.util.Clock;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class AudienceManager implements NamedUserListener {
    public static final long DEFAULT_PREFER_LOCAL_DATA_TIME_MS = 600000;
    private static final String DEVICE_GROUP = "device";
    private static final String ENABLED_KEY = "com.urbanairship.iam.tags.FETCH_ENABLED";
    private static final String PREFER_LOCAL_DATA_TIME_KEY = "com.urbanairship.iam.tags.TAG_PREFER_LOCAL_DATA_TIME";
    private final AirshipChannel airshipChannel;
    private final TagGroupLookupResponseCache cache;
    private final TagGroupLookupApiClient client;
    private final Clock clock;
    private final PreferenceDataStore dataStore;
    private final AudienceHistorian historian;
    private final NamedUser namedUser;
    private RequestTagsCallback requestTagsCallback;

    public interface RequestTagsCallback {
        Map<String, Set<String>> getTags() throws Exception;
    }

    public AudienceManager(AirshipRuntimeConfig airshipRuntimeConfig, AirshipChannel airshipChannel2, NamedUser namedUser2, PreferenceDataStore preferenceDataStore) {
        this(new TagGroupLookupApiClient(airshipRuntimeConfig), airshipChannel2, namedUser2, new TagGroupLookupResponseCache(preferenceDataStore, Clock.DEFAULT_CLOCK), new AudienceHistorian(airshipChannel2, namedUser2, Clock.DEFAULT_CLOCK), preferenceDataStore, Clock.DEFAULT_CLOCK);
    }

    AudienceManager(TagGroupLookupApiClient tagGroupLookupApiClient, AirshipChannel airshipChannel2, NamedUser namedUser2, TagGroupLookupResponseCache tagGroupLookupResponseCache, AudienceHistorian audienceHistorian, PreferenceDataStore preferenceDataStore, Clock clock2) {
        this.client = tagGroupLookupApiClient;
        this.airshipChannel = airshipChannel2;
        this.namedUser = namedUser2;
        this.cache = tagGroupLookupResponseCache;
        this.historian = audienceHistorian;
        this.dataStore = preferenceDataStore;
        this.clock = clock2;
        audienceHistorian.init();
        namedUser2.addNamedUserListener(this);
    }

    public void setEnabled(boolean z) {
        this.dataStore.put(ENABLED_KEY, z);
    }

    public boolean isEnabled() {
        return this.dataStore.getBoolean(ENABLED_KEY, true);
    }

    public void setCacheMaxAgeTime(long j, TimeUnit timeUnit) {
        this.cache.setMaxAgeTime(j, timeUnit);
    }

    public long getCacheMaxAgeTimeMilliseconds() {
        return this.cache.getMaxAgeTimeMilliseconds();
    }

    public void setCacheStaleReadTime(long j, TimeUnit timeUnit) {
        this.cache.setStaleReadTime(j, timeUnit);
    }

    public long getCacheStaleReadTimeMilliseconds() {
        return this.cache.getStaleReadTimeMilliseconds();
    }

    public void setRequestTagsCallback(RequestTagsCallback requestTagsCallback2) {
        this.requestTagsCallback = requestTagsCallback2;
    }

    public long getPreferLocalTagDataTime() {
        return this.dataStore.getLong(PREFER_LOCAL_DATA_TIME_KEY, 600000);
    }

    public void setPreferLocalTagDataTime(long j, TimeUnit timeUnit) {
        this.dataStore.put(PREFER_LOCAL_DATA_TIME_KEY, timeUnit.toMillis(j));
    }

    public synchronized TagGroupResult getTags(Map<String, Set<String>> map) {
        if (this.requestTagsCallback == null) {
            throw new IllegalStateException("RequestTagsCallback not set");
        } else if (!isEnabled()) {
            return new TagGroupResult(false, (Map<String, Set<String>>) null);
        } else if (map.isEmpty()) {
            return new TagGroupResult(true, map);
        } else if (map.size() == 1 && map.containsKey(DEVICE_GROUP) && this.airshipChannel.getChannelTagRegistrationEnabled()) {
            HashMap hashMap = new HashMap();
            hashMap.put(DEVICE_GROUP, this.airshipChannel.getTags());
            return new TagGroupResult(true, hashMap);
        } else if (this.airshipChannel.getId() == null) {
            return new TagGroupResult(false, (Map<String, Set<String>>) null);
        } else {
            long cacheStaleReadTimeMilliseconds = getCacheStaleReadTimeMilliseconds();
            long cacheMaxAgeTimeMilliseconds = getCacheMaxAgeTimeMilliseconds();
            TagGroupResponse response = TagGroupUtils.containsAll(this.cache.getRequestTags(), map) ? this.cache.getResponse() : null;
            long createDate = this.cache.getCreateDate();
            if (response == null || cacheMaxAgeTimeMilliseconds <= this.clock.currentTimeMillis() - createDate) {
                try {
                    refreshCache(map, response);
                    response = this.cache.getResponse();
                    createDate = this.cache.getCreateDate();
                } catch (Exception e) {
                    Logger.error(e, "Failed to refresh tags.", new Object[0]);
                }
                if (response == null) {
                    return new TagGroupResult(false, (Map<String, Set<String>>) null);
                }
                if (cacheStaleReadTimeMilliseconds > 0) {
                    if (cacheStaleReadTimeMilliseconds <= this.clock.currentTimeMillis() - createDate) {
                        return new TagGroupResult(false, (Map<String, Set<String>>) null);
                    }
                }
                return new TagGroupResult(true, generateTags(map, response, createDate));
            }
            return new TagGroupResult(true, generateTags(map, response, createDate));
        }
    }

    public void onNamedUserIdChanged(String str) {
        this.cache.clear();
    }

    private Map<String, Set<String>> generateTags(Map<String, Set<String>> map, TagGroupResponse tagGroupResponse, long j) {
        HashMap hashMap = new HashMap(tagGroupResponse.tags);
        for (TagGroupsMutation apply : getTagGroupOverrides(j - getPreferLocalTagDataTime())) {
            apply.apply(hashMap);
        }
        return TagGroupUtils.intersect(map, hashMap);
    }

    private void refreshCache(Map<String, Set<String>> map, TagGroupResponse tagGroupResponse) throws Exception {
        RequestTagsCallback requestTagsCallback2 = this.requestTagsCallback;
        if (requestTagsCallback2 != null) {
            map = TagGroupUtils.union(map, requestTagsCallback2.getTags());
        }
        if (tagGroupResponse != null && !map.equals(this.cache.getRequestTags())) {
            tagGroupResponse = null;
        }
        TagGroupResponse lookupTagGroups = this.client.lookupTagGroups(this.airshipChannel.getId(), map, tagGroupResponse);
        if (lookupTagGroups == null) {
            Logger.error("Failed to refresh the cache.", new Object[0]);
        } else if (lookupTagGroups.status != 200) {
            Logger.error("Failed to refresh the cache. Status: %s", lookupTagGroups);
        } else {
            Logger.verbose("Refreshed tag group with response: %s", lookupTagGroups);
            this.cache.setResponse(lookupTagGroups, map);
        }
    }

    public List<TagGroupsMutation> getTagOverrides() {
        return getTagGroupOverrides(this.clock.currentTimeMillis() - getPreferLocalTagDataTime());
    }

    private List<TagGroupsMutation> getTagGroupOverrides(long j) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.historian.getTagGroupHistory(j));
        arrayList.addAll(this.namedUser.getPendingTagUpdates());
        arrayList.addAll(this.airshipChannel.getPendingTagUpdates());
        if (this.airshipChannel.getChannelTagRegistrationEnabled()) {
            arrayList.add(TagGroupsMutation.newSetTagsMutation(DEVICE_GROUP, this.airshipChannel.getTags()));
        }
        return TagGroupsMutation.collapseMutations(arrayList);
    }

    public List<AttributeMutation> getAttributeOverrides() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.historian.getAttributeHistory(this.clock.currentTimeMillis() - 600000));
        arrayList.addAll(this.namedUser.getPendingAttributeUpdates());
        arrayList.addAll(this.airshipChannel.getPendingAttributeUpdates());
        return AttributeMutation.collapseMutations(arrayList);
    }
}
