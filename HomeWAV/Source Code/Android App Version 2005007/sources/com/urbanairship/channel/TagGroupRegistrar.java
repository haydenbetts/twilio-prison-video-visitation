package com.urbanairship.channel;

import com.urbanairship.Logger;
import com.urbanairship.http.RequestException;
import com.urbanairship.http.Response;
import com.urbanairship.util.UAStringUtil;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

class TagGroupRegistrar {
    private final TagGroupApiClient apiClient;
    private final Object idLock = new Object();
    private String identifier;
    private final PendingTagGroupMutationStore pendingTagGroupMutationStore;
    private final List<TagGroupListener> tagGroupListeners = new CopyOnWriteArrayList();

    TagGroupRegistrar(TagGroupApiClient tagGroupApiClient, PendingTagGroupMutationStore pendingTagGroupMutationStore2) {
        this.apiClient = tagGroupApiClient;
        this.pendingTagGroupMutationStore = pendingTagGroupMutationStore2;
        pendingTagGroupMutationStore2.collapseAndSaveMutations();
    }

    /* access modifiers changed from: package-private */
    public void addPendingMutations(List<TagGroupsMutation> list) {
        this.pendingTagGroupMutationStore.addAll(list);
    }

    /* access modifiers changed from: package-private */
    public void setId(String str, boolean z) {
        synchronized (this.idLock) {
            if (z) {
                if (!UAStringUtil.equals(this.identifier, str)) {
                    this.pendingTagGroupMutationStore.removeAll();
                }
            }
            this.identifier = str;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean uploadPendingMutations() {
        TagGroupsMutation tagGroupsMutation;
        String str;
        while (true) {
            synchronized (this.idLock) {
                this.pendingTagGroupMutationStore.collapseAndSaveMutations();
                tagGroupsMutation = (TagGroupsMutation) this.pendingTagGroupMutationStore.peek();
                str = this.identifier;
            }
            if (UAStringUtil.isEmpty(str) || tagGroupsMutation == null) {
                return true;
            }
            try {
                Response<Void> updateTags = this.apiClient.updateTags(str, tagGroupsMutation);
                Logger.debug("Updated tag group response: %s", updateTags);
                if (updateTags.isServerError() || updateTags.isTooManyRequestsError()) {
                    return false;
                }
                if (updateTags.isClientError()) {
                    Logger.error("Dropping tag group update %s due to error: %s message: %s", tagGroupsMutation, Integer.valueOf(updateTags.getStatus()), updateTags.getResponseBody());
                } else {
                    for (TagGroupListener onTagGroupsMutationUploaded : this.tagGroupListeners) {
                        onTagGroupsMutationUploaded.onTagGroupsMutationUploaded(str, tagGroupsMutation);
                    }
                }
                synchronized (this.idLock) {
                    if (tagGroupsMutation.equals(this.pendingTagGroupMutationStore.peek()) && str.equals(this.identifier)) {
                        this.pendingTagGroupMutationStore.pop();
                    }
                }
            } catch (RequestException e) {
                Logger.debug(e, "Failed to update tag groups", new Object[0]);
                return false;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void clearPendingMutations() {
        this.pendingTagGroupMutationStore.removeAll();
    }

    /* access modifiers changed from: package-private */
    public List<TagGroupsMutation> getPendingMutations() {
        return this.pendingTagGroupMutationStore.getList();
    }

    /* access modifiers changed from: package-private */
    public void addTagGroupListener(TagGroupListener tagGroupListener) {
        this.tagGroupListeners.add(tagGroupListener);
    }
}
