package com.urbanairship.channel;

import androidx.arch.core.util.Function;
import com.urbanairship.PreferenceDataStore;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.json.JsonValue;
import com.urbanairship.util.JsonDataStoreQueue;
import java.util.List;

class PendingTagGroupMutationStore extends JsonDataStoreQueue<TagGroupsMutation> {
    PendingTagGroupMutationStore(PreferenceDataStore preferenceDataStore, String str) {
        super(preferenceDataStore, str, new Function<TagGroupsMutation, JsonSerializable>() {
            public JsonSerializable apply(TagGroupsMutation tagGroupsMutation) {
                return tagGroupsMutation;
            }
        }, new Function<JsonValue, TagGroupsMutation>() {
            public TagGroupsMutation apply(JsonValue jsonValue) {
                return TagGroupsMutation.fromJsonValue(jsonValue);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void collapseAndSaveMutations() {
        apply(new Function<List<TagGroupsMutation>, List<TagGroupsMutation>>() {
            public List<TagGroupsMutation> apply(List<TagGroupsMutation> list) {
                return TagGroupsMutation.collapseMutations(list);
            }
        });
    }
}
