package com.urbanairship.channel;

import com.urbanairship.Logger;
import com.urbanairship.http.RequestException;
import com.urbanairship.http.Response;
import com.urbanairship.util.UAStringUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

class AttributeRegistrar {
    private final AttributeApiClient apiClient;
    private final List<AttributeListener> attributeListeners = new CopyOnWriteArrayList();
    private final Object idLock = new Object();
    private String identifier;
    private final PendingAttributeMutationStore mutationStore;

    AttributeRegistrar(AttributeApiClient attributeApiClient, PendingAttributeMutationStore pendingAttributeMutationStore) {
        this.apiClient = attributeApiClient;
        this.mutationStore = pendingAttributeMutationStore;
    }

    /* access modifiers changed from: package-private */
    public void addPendingMutations(List<AttributeMutation> list) {
        this.mutationStore.add(list);
    }

    /* access modifiers changed from: package-private */
    public void setId(String str, boolean z) {
        synchronized (this.idLock) {
            if (z) {
                if (!UAStringUtil.equals(this.identifier, str)) {
                    this.mutationStore.removeAll();
                }
            }
            this.identifier = str;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean uploadPendingMutations() {
        List list;
        String str;
        synchronized (this.idLock) {
            this.mutationStore.collapseAndSaveMutations();
            list = (List) this.mutationStore.peek();
            str = this.identifier;
        }
        if (str == null || list == null || list.isEmpty()) {
            return true;
        }
        try {
            Response<Void> updateAttributes = this.apiClient.updateAttributes(str, list);
            Logger.debug("Updated attributes response: %s", updateAttributes);
            if (updateAttributes.isServerError() || updateAttributes.isTooManyRequestsError()) {
                return false;
            }
            if (updateAttributes.isClientError()) {
                Logger.error("Dropping attributes %s due to error: %s message: %s", list, Integer.valueOf(updateAttributes.getStatus()), updateAttributes.getResponseBody());
            } else {
                for (AttributeListener onAttributeMutationsUploaded : this.attributeListeners) {
                    onAttributeMutationsUploaded.onAttributeMutationsUploaded(str, list);
                }
            }
            synchronized (this.idLock) {
                if (list.equals(this.mutationStore.peek()) && str.equals(this.identifier)) {
                    this.mutationStore.pop();
                }
            }
            return true;
        } catch (RequestException e) {
            Logger.debug(e, "Failed to update attributes", new Object[0]);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public void clearPendingMutations() {
        this.mutationStore.removeAll();
    }

    /* access modifiers changed from: package-private */
    public void addAttributeListener(AttributeListener attributeListener) {
        this.attributeListeners.add(attributeListener);
    }

    /* access modifiers changed from: package-private */
    public List<AttributeMutation> getPendingMutations() {
        ArrayList arrayList = new ArrayList();
        for (List addAll : this.mutationStore.getList()) {
            arrayList.addAll(addAll);
        }
        return arrayList;
    }
}
