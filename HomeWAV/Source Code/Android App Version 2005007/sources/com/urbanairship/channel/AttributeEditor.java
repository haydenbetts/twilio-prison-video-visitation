package com.urbanairship.channel;

import com.urbanairship.Logger;
import com.urbanairship.json.JsonValue;
import com.urbanairship.util.Clock;
import com.urbanairship.util.DateUtils;
import com.urbanairship.util.UAStringUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class AttributeEditor {
    private static final long MAX_ATTRIBUTE_FIELD_LENGTH = 1024;
    private final Clock clock;
    private final List<PartialAttributeMutation> partialMutations = new ArrayList();

    /* access modifiers changed from: package-private */
    public abstract void onApply(List<AttributeMutation> list);

    AttributeEditor(Clock clock2) {
        this.clock = clock2;
    }

    public AttributeEditor setAttribute(String str, String str2) {
        if (!isInvalidField(str) && !isInvalidField(str2)) {
            this.partialMutations.add(new PartialAttributeMutation(str, str2));
        }
        return this;
    }

    public AttributeEditor setAttribute(String str, int i) {
        if (isInvalidField(str)) {
            return this;
        }
        this.partialMutations.add(new PartialAttributeMutation(str, Integer.valueOf(i)));
        return this;
    }

    public AttributeEditor setAttribute(String str, long j) {
        if (isInvalidField(str)) {
            return this;
        }
        this.partialMutations.add(new PartialAttributeMutation(str, Long.valueOf(j)));
        return this;
    }

    public AttributeEditor setAttribute(String str, float f) throws NumberFormatException {
        if (isInvalidField(str)) {
            return this;
        }
        if (Float.isNaN(f) || Float.isInfinite(f)) {
            throw new NumberFormatException("Infinity or NaN: " + f);
        }
        this.partialMutations.add(new PartialAttributeMutation(str, Float.valueOf(f)));
        return this;
    }

    public AttributeEditor setAttribute(String str, double d) throws NumberFormatException {
        if (isInvalidField(str)) {
            return this;
        }
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            throw new NumberFormatException("Infinity or NaN: " + d);
        }
        this.partialMutations.add(new PartialAttributeMutation(str, Double.valueOf(d)));
        return this;
    }

    public AttributeEditor setAttribute(String str, Date date) {
        if (isInvalidField(str)) {
            return this;
        }
        this.partialMutations.add(new PartialAttributeMutation(str, DateUtils.createIso8601TimeStamp(date.getTime())));
        return this;
    }

    public AttributeEditor removeAttribute(String str) {
        if (isInvalidField(str)) {
            return this;
        }
        this.partialMutations.add(new PartialAttributeMutation(str, (Object) null));
        return this;
    }

    private boolean isInvalidField(String str) {
        if (UAStringUtil.isEmpty(str)) {
            Logger.error("Attribute fields cannot be empty.", new Object[0]);
            return true;
        } else if (((long) str.length()) <= 1024) {
            return false;
        } else {
            Logger.error("Attribute field inputs cannot be greater than %s characters in length", 1024L);
            return true;
        }
    }

    public void apply() {
        if (this.partialMutations.size() != 0) {
            long currentTimeMillis = this.clock.currentTimeMillis();
            ArrayList arrayList = new ArrayList();
            for (PartialAttributeMutation mutation : this.partialMutations) {
                try {
                    arrayList.add(mutation.toMutation(currentTimeMillis));
                } catch (IllegalArgumentException e) {
                    Logger.error(e, "Invalid attribute mutation.", new Object[0]);
                }
            }
            onApply(arrayList);
        }
    }

    private class PartialAttributeMutation {
        String key;
        Object value;

        PartialAttributeMutation(String str, Object obj) {
            this.key = str;
            this.value = obj;
        }

        /* access modifiers changed from: package-private */
        public AttributeMutation toMutation(long j) {
            Object obj = this.value;
            if (obj != null) {
                return AttributeMutation.newSetAttributeMutation(this.key, JsonValue.wrapOpt(obj), j);
            }
            return AttributeMutation.newRemoveAttributeMutation(this.key, j);
        }
    }
}
