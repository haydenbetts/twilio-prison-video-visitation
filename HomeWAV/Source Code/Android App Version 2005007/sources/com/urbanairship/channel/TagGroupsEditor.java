package com.urbanairship.channel;

import com.urbanairship.Logger;
import com.urbanairship.util.UAStringUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TagGroupsEditor {
    private final List<TagGroupsMutation> mutations = new ArrayList();

    /* access modifiers changed from: protected */
    public boolean allowTagGroupChange(String str) {
        return true;
    }

    /* access modifiers changed from: protected */
    public void onApply(List<TagGroupsMutation> list) {
    }

    public TagGroupsEditor addTag(String str, String str2) {
        return addTags(str, Collections.singleton(str2));
    }

    public TagGroupsEditor addTags(String str, Set<String> set) {
        String trim = str.trim();
        if (UAStringUtil.isEmpty(trim)) {
            Logger.error("The tag group ID string cannot be null.", new Object[0]);
            return this;
        } else if (!allowTagGroupChange(trim)) {
            return this;
        } else {
            Set<String> normalizeTags = TagUtils.normalizeTags(set);
            if (normalizeTags.isEmpty()) {
                return this;
            }
            this.mutations.add(TagGroupsMutation.newAddTagsMutation(trim, normalizeTags));
            return this;
        }
    }

    public TagGroupsEditor setTag(String str, String str2) {
        return setTags(str, Collections.singleton(str2));
    }

    public TagGroupsEditor setTags(String str, Set<String> set) {
        Set<String> set2;
        String trim = str.trim();
        if (UAStringUtil.isEmpty(trim)) {
            Logger.error("The tag group ID string cannot be null.", new Object[0]);
            return this;
        } else if (!allowTagGroupChange(trim)) {
            return this;
        } else {
            if (set == null) {
                set2 = new HashSet<>();
            } else {
                set2 = TagUtils.normalizeTags(set);
            }
            this.mutations.add(TagGroupsMutation.newSetTagsMutation(trim, set2));
            return this;
        }
    }

    public TagGroupsEditor removeTag(String str, String str2) {
        return removeTags(str, Collections.singleton(str2));
    }

    public TagGroupsEditor removeTags(String str, Set<String> set) {
        String trim = str.trim();
        if (UAStringUtil.isEmpty(trim)) {
            Logger.error("The tag group ID string cannot be null.", new Object[0]);
            return this;
        } else if (!allowTagGroupChange(trim)) {
            return this;
        } else {
            Set<String> normalizeTags = TagUtils.normalizeTags(set);
            if (normalizeTags.isEmpty()) {
                return this;
            }
            this.mutations.add(TagGroupsMutation.newRemoveTagsMutation(trim, normalizeTags));
            return this;
        }
    }

    public void apply() {
        onApply(TagGroupsMutation.collapseMutations(this.mutations));
    }
}
