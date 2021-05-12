package com.urbanairship.automation.tags;

import com.urbanairship.channel.AirshipChannel;
import com.urbanairship.channel.AttributeListener;
import com.urbanairship.channel.AttributeMutation;
import com.urbanairship.channel.NamedUser;
import com.urbanairship.channel.TagGroupListener;
import com.urbanairship.channel.TagGroupsMutation;
import com.urbanairship.util.Clock;
import java.util.ArrayList;
import java.util.List;

class AudienceHistorian {
    private final List<MutationRecord<AttributeMutation>> attributeRecords = new ArrayList();
    private final AirshipChannel channel;
    /* access modifiers changed from: private */
    public final Clock clock;
    private final NamedUser namedUser;
    private final List<MutationRecord<TagGroupsMutation>> tagRecords = new ArrayList();

    AudienceHistorian(AirshipChannel airshipChannel, NamedUser namedUser2, Clock clock2) {
        this.channel = airshipChannel;
        this.namedUser = namedUser2;
        this.clock = clock2;
    }

    /* access modifiers changed from: package-private */
    public void init() {
        this.channel.addTagGroupListener(new TagGroupListener() {
            public void onTagGroupsMutationUploaded(String str, TagGroupsMutation tagGroupsMutation) {
                AudienceHistorian audienceHistorian = AudienceHistorian.this;
                audienceHistorian.recordTagGroup(new MutationRecord(0, str, audienceHistorian.clock.currentTimeMillis(), tagGroupsMutation));
            }
        });
        this.channel.addAttributeListener(new AttributeListener() {
            public void onAttributeMutationsUploaded(String str, List<AttributeMutation> list) {
                long currentTimeMillis = AudienceHistorian.this.clock.currentTimeMillis();
                for (AttributeMutation mutationRecord : list) {
                    AudienceHistorian.this.recordAttribute(new MutationRecord(0, str, currentTimeMillis, mutationRecord));
                }
            }
        });
        this.namedUser.addTagGroupListener(new TagGroupListener() {
            public void onTagGroupsMutationUploaded(String str, TagGroupsMutation tagGroupsMutation) {
                AudienceHistorian audienceHistorian = AudienceHistorian.this;
                audienceHistorian.recordTagGroup(new MutationRecord(1, str, audienceHistorian.clock.currentTimeMillis(), tagGroupsMutation));
            }
        });
        this.namedUser.addAttributeListener(new AttributeListener() {
            public void onAttributeMutationsUploaded(String str, List<AttributeMutation> list) {
                long currentTimeMillis = AudienceHistorian.this.clock.currentTimeMillis();
                for (AttributeMutation mutationRecord : list) {
                    AudienceHistorian.this.recordAttribute(new MutationRecord(1, str, currentTimeMillis, mutationRecord));
                }
            }
        });
    }

    public List<TagGroupsMutation> getTagGroupHistory(long j) {
        List<TagGroupsMutation> filterHistory;
        synchronized (this.tagRecords) {
            filterHistory = filterHistory(this.tagRecords, j);
        }
        return filterHistory;
    }

    public List<AttributeMutation> getAttributeHistory(long j) {
        List<AttributeMutation> filterHistory;
        synchronized (this.attributeRecords) {
            filterHistory = filterHistory(this.attributeRecords, j);
        }
        return filterHistory;
    }

    private <T> List<T> filterHistory(List<MutationRecord<T>> list, long j) {
        ArrayList arrayList = new ArrayList();
        String id = this.namedUser.getId();
        for (MutationRecord next : list) {
            if (next.time >= j && (next.source == 0 || next.identifier.equals(id))) {
                arrayList.add(next.mutation);
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public void recordTagGroup(MutationRecord<TagGroupsMutation> mutationRecord) {
        synchronized (this.tagRecords) {
            this.tagRecords.add(mutationRecord);
        }
    }

    /* access modifiers changed from: private */
    public void recordAttribute(MutationRecord<AttributeMutation> mutationRecord) {
        synchronized (this.attributeRecords) {
            this.attributeRecords.add(mutationRecord);
        }
    }

    private static class MutationRecord<T> {
        static final int SOURCE_CHANNEL = 0;
        static final int SOURCE_NAMED_USER = 1;
        final String identifier;
        final T mutation;
        final int source;
        final long time;

        MutationRecord(int i, String str, long j, T t) {
            this.source = i;
            this.time = j;
            this.identifier = str;
            this.mutation = t;
        }
    }
}
