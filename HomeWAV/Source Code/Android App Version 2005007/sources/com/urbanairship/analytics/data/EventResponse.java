package com.urbanairship.analytics.data;

import androidx.work.Data;
import com.urbanairship.http.Response;
import com.urbanairship.util.UAMathUtil;
import java.util.List;

class EventResponse {
    static final int MAX_BATCH_INTERVAL_MS = 604800000;
    static final int MAX_BATCH_SIZE_BYTES = 512000;
    static final int MAX_TOTAL_DB_SIZE_BYTES = 5242880;
    static final int MIN_BATCH_INTERVAL_MS = 60000;
    static final int MIN_BATCH_SIZE_BYTES = 10240;
    static final int MIN_TOTAL_DB_SIZE_BYTES = 10240;
    private final Response<Void> response;

    public EventResponse(Response<Void> response2) {
        this.response = response2;
    }

    public int getStatus() {
        return this.response.getStatus();
    }

    /* access modifiers changed from: package-private */
    public int getMaxTotalSize() {
        List list;
        if (this.response.getResponseHeaders() == null || (list = this.response.getResponseHeaders().get("X-UA-Max-Total")) == null || list.size() <= 0) {
            return Data.MAX_DATA_BYTES;
        }
        return UAMathUtil.constrain(Integer.parseInt((String) list.get(0)) * 1024, Data.MAX_DATA_BYTES, MAX_TOTAL_DB_SIZE_BYTES);
    }

    /* access modifiers changed from: package-private */
    public int getMaxBatchSize() {
        List list;
        if (this.response.getResponseHeaders() == null || (list = this.response.getResponseHeaders().get("X-UA-Max-Batch")) == null || list.size() <= 0) {
            return Data.MAX_DATA_BYTES;
        }
        return UAMathUtil.constrain(Integer.parseInt((String) list.get(0)) * 1024, Data.MAX_DATA_BYTES, MAX_BATCH_SIZE_BYTES);
    }

    /* access modifiers changed from: package-private */
    public int getMinBatchInterval() {
        List list;
        if (this.response.getResponseHeaders() == null || (list = this.response.getResponseHeaders().get("X-UA-Min-Batch-Interval")) == null || list.size() <= 0) {
            return 60000;
        }
        return UAMathUtil.constrain(Integer.parseInt((String) list.get(0)), 60000, 604800000);
    }
}
