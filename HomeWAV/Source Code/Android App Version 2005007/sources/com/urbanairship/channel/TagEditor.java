package com.urbanairship.channel;

import java.util.HashSet;
import java.util.Set;

public abstract class TagEditor {
    private boolean clear = false;
    private final Set<String> tagsToAdd = new HashSet();
    private final Set<String> tagsToRemove = new HashSet();

    /* access modifiers changed from: protected */
    public abstract void onApply(boolean z, Set<String> set, Set<String> set2);

    protected TagEditor() {
    }

    public TagEditor addTag(String str) {
        this.tagsToRemove.remove(str);
        this.tagsToAdd.add(str);
        return this;
    }

    public TagEditor addTags(Set<String> set) {
        this.tagsToRemove.removeAll(set);
        this.tagsToAdd.addAll(set);
        return this;
    }

    public TagEditor removeTag(String str) {
        this.tagsToAdd.remove(str);
        this.tagsToRemove.add(str);
        return this;
    }

    public TagEditor removeTags(Set<String> set) {
        this.tagsToAdd.removeAll(set);
        this.tagsToRemove.addAll(set);
        return this;
    }

    public TagEditor clear() {
        this.clear = true;
        return this;
    }

    public void apply() {
        onApply(this.clear, this.tagsToAdd, this.tagsToRemove);
    }
}
