package com.urbanairship.channel;

import androidx.arch.core.util.Function;
import com.urbanairship.PreferenceDataStore;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.json.JsonValue;
import com.urbanairship.util.JsonDataStoreQueue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class PendingAttributeMutationStore extends JsonDataStoreQueue<List<AttributeMutation>> {
    PendingAttributeMutationStore(PreferenceDataStore preferenceDataStore, String str) {
        super(preferenceDataStore, str, new Function<List<AttributeMutation>, JsonSerializable>() {
            public JsonSerializable apply(List<AttributeMutation> list) {
                return JsonValue.wrapOpt(list);
            }
        }, new Function<JsonValue, List<AttributeMutation>>() {
            public List<AttributeMutation> apply(JsonValue jsonValue) {
                return AttributeMutation.fromJsonList(jsonValue.optList());
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void collapseAndSaveMutations() {
        apply(new Function<List<List<AttributeMutation>>, List<List<AttributeMutation>>>() {
            public List<List<AttributeMutation>> apply(List<List<AttributeMutation>> list) {
                ArrayList arrayList = new ArrayList();
                for (List<AttributeMutation> addAll : list) {
                    arrayList.addAll(addAll);
                }
                if (arrayList.isEmpty()) {
                    return Collections.emptyList();
                }
                return Collections.singletonList(AttributeMutation.collapseMutations(arrayList));
            }
        });
    }
}
