package com.urbanairship.analytics;

import com.urbanairship.Logger;
import com.urbanairship.UAirship;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.json.JsonValue;
import com.urbanairship.push.PushMessage;
import com.urbanairship.util.UAStringUtil;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CustomEvent extends Event implements JsonSerializable {
    public static final String CONVERSION_METADATA = "conversion_metadata";
    public static final String CONVERSION_SEND_ID = "conversion_send_id";
    public static final String EVENT_NAME = "event_name";
    public static final String EVENT_VALUE = "event_value";
    public static final String INTERACTION_ID = "interaction_id";
    public static final String INTERACTION_TYPE = "interaction_type";
    public static final String LAST_RECEIVED_METADATA = "last_received_metadata";
    public static final int MAX_CHARACTER_LENGTH = 255;
    public static final int MAX_TOTAL_PROPERTIES_SIZE = 65536;
    private static final BigDecimal MAX_VALUE = new BigDecimal(Integer.MAX_VALUE);
    public static final String MCRAP_TRANSACTION_TYPE = "ua_mcrap";
    private static final BigDecimal MIN_VALUE = new BigDecimal(Integer.MIN_VALUE);
    public static final String PROPERTIES = "properties";
    public static final String TEMPLATE_TYPE = "template_type";
    public static final String TRANSACTION_ID = "transaction_id";
    static final String TYPE = "enhanced_custom_event";
    private final String eventName;
    private final BigDecimal eventValue;
    private final String interactionId;
    private final String interactionType;
    private final JsonMap properties;
    private final String sendId;
    private final String templateType;
    private final String transactionId;

    public final String getType() {
        return TYPE;
    }

    private CustomEvent(Builder builder) {
        this.eventName = builder.eventName;
        this.eventValue = builder.value;
        String str = null;
        this.transactionId = UAStringUtil.isEmpty(builder.transactionId) ? null : builder.transactionId;
        this.interactionType = UAStringUtil.isEmpty(builder.interactionType) ? null : builder.interactionType;
        this.interactionId = !UAStringUtil.isEmpty(builder.interactionId) ? builder.interactionId : str;
        this.sendId = builder.pushSendId;
        this.templateType = builder.templateType;
        this.properties = new JsonMap(builder.properties);
    }

    public static Builder newBuilder(String str) {
        return new Builder(str);
    }

    public String getEventName() {
        return this.eventName;
    }

    public BigDecimal getEventValue() {
        return this.eventValue;
    }

    public String getTransactionId() {
        return this.transactionId;
    }

    public String getInteractionType() {
        return this.interactionType;
    }

    public String getInteractionId() {
        return this.interactionId;
    }

    public JsonMap getProperties() {
        return this.properties;
    }

    public final JsonMap getEventData() {
        JsonMap.Builder newBuilder = JsonMap.newBuilder();
        String conversionSendId = UAirship.shared().getAnalytics().getConversionSendId();
        String conversionMetadata = UAirship.shared().getAnalytics().getConversionMetadata();
        newBuilder.put(EVENT_NAME, this.eventName);
        newBuilder.put(INTERACTION_ID, this.interactionId);
        newBuilder.put(INTERACTION_TYPE, this.interactionType);
        newBuilder.put(TRANSACTION_ID, this.transactionId);
        newBuilder.put(TEMPLATE_TYPE, this.templateType);
        BigDecimal bigDecimal = this.eventValue;
        if (bigDecimal != null) {
            newBuilder.put(EVENT_VALUE, bigDecimal.movePointRight(6).longValue());
        }
        if (!UAStringUtil.isEmpty(this.sendId)) {
            newBuilder.put(CONVERSION_SEND_ID, this.sendId);
        } else {
            newBuilder.put(CONVERSION_SEND_ID, conversionSendId);
        }
        if (conversionMetadata != null) {
            newBuilder.put(CONVERSION_METADATA, conversionMetadata);
        } else {
            newBuilder.put(LAST_RECEIVED_METADATA, UAirship.shared().getPushManager().getLastReceivedMetadata());
        }
        if (this.properties.getMap().size() > 0) {
            newBuilder.put(PROPERTIES, (JsonSerializable) this.properties);
        }
        return newBuilder.build();
    }

    public JsonValue toJsonValue() {
        JsonMap.Builder put = JsonMap.newBuilder().put(EVENT_NAME, this.eventName).put(INTERACTION_ID, this.interactionId).put(INTERACTION_TYPE, this.interactionType).put(TRANSACTION_ID, this.transactionId).put(PROPERTIES, (JsonSerializable) JsonValue.wrapOpt(this.properties));
        BigDecimal bigDecimal = this.eventValue;
        if (bigDecimal != null) {
            put.putOpt(EVENT_VALUE, Double.valueOf(bigDecimal.doubleValue()));
        }
        return put.build().toJsonValue();
    }

    public boolean isValid() {
        boolean z;
        if (UAStringUtil.isEmpty(this.eventName) || this.eventName.length() > 255) {
            Logger.error("Event name must not be null, empty, or larger than %s characters.", 255);
            z = false;
        } else {
            z = true;
        }
        BigDecimal bigDecimal = this.eventValue;
        if (bigDecimal != null) {
            BigDecimal bigDecimal2 = MAX_VALUE;
            if (bigDecimal.compareTo(bigDecimal2) > 0) {
                Logger.error("Event value is bigger than %s", bigDecimal2);
            } else {
                BigDecimal bigDecimal3 = this.eventValue;
                BigDecimal bigDecimal4 = MIN_VALUE;
                if (bigDecimal3.compareTo(bigDecimal4) < 0) {
                    Logger.error("Event value is smaller than %s", bigDecimal4);
                }
            }
            z = false;
        }
        String str = this.transactionId;
        if (str != null && str.length() > 255) {
            Logger.error("Transaction ID is larger than %s characters.", 255);
            z = false;
        }
        String str2 = this.interactionId;
        if (str2 != null && str2.length() > 255) {
            Logger.error("Interaction ID is larger than %s characters.", 255);
            z = false;
        }
        String str3 = this.interactionType;
        if (str3 != null && str3.length() > 255) {
            Logger.error("Interaction type is larger than %s characters.", 255);
            z = false;
        }
        String str4 = this.templateType;
        if (str4 != null && str4.length() > 255) {
            Logger.error("Template type is larger than %s characters.", 255);
            z = false;
        }
        int length = this.properties.toJsonValue().toString().getBytes().length;
        if (length <= 65536) {
            return z;
        }
        Logger.error("Total custom properties size (%s bytes) exceeds maximum size of %s bytes.", Integer.valueOf(length), 65536);
        return false;
    }

    public CustomEvent track() {
        UAirship.shared().getAnalytics().addEvent(this);
        return this;
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public final String eventName;
        /* access modifiers changed from: private */
        public String interactionId;
        /* access modifiers changed from: private */
        public String interactionType;
        /* access modifiers changed from: private */
        public Map<String, JsonValue> properties = new HashMap();
        /* access modifiers changed from: private */
        public String pushSendId;
        /* access modifiers changed from: private */
        public String templateType;
        /* access modifiers changed from: private */
        public String transactionId;
        /* access modifiers changed from: private */
        public BigDecimal value;

        public Builder(String str) {
            this.eventName = str;
        }

        public Builder setProperties(JsonMap jsonMap) {
            if (jsonMap == null) {
                this.properties.clear();
                return this;
            }
            this.properties = jsonMap.getMap();
            return this;
        }

        public Builder setEventValue(BigDecimal bigDecimal) {
            if (bigDecimal == null) {
                this.value = null;
                return this;
            }
            this.value = bigDecimal;
            return this;
        }

        public Builder setEventValue(double d) {
            return setEventValue(BigDecimal.valueOf(d));
        }

        public Builder setEventValue(String str) {
            if (!UAStringUtil.isEmpty(str)) {
                return setEventValue(new BigDecimal(str));
            }
            this.value = null;
            return this;
        }

        public Builder setEventValue(int i) {
            return setEventValue(new BigDecimal(i));
        }

        public Builder setTransactionId(String str) {
            this.transactionId = str;
            return this;
        }

        public Builder setMessageCenterInteraction(String str) {
            this.interactionType = CustomEvent.MCRAP_TRANSACTION_TYPE;
            this.interactionId = str;
            return this;
        }

        public Builder setInteraction(String str, String str2) {
            this.interactionId = str2;
            this.interactionType = str;
            return this;
        }

        public Builder setAttribution(PushMessage pushMessage) {
            if (pushMessage != null) {
                this.pushSendId = pushMessage.getSendId();
            }
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setTemplateType(String str) {
            this.templateType = str;
            return this;
        }

        public Builder addProperty(String str, JsonSerializable jsonSerializable) {
            this.properties.put(str, jsonSerializable.toJsonValue());
            return this;
        }

        public Builder addProperty(String str, String str2) {
            this.properties.put(str, JsonValue.wrap(str2));
            return this;
        }

        public Builder addProperty(String str, int i) {
            this.properties.put(str, JsonValue.wrap(i));
            return this;
        }

        public Builder addProperty(String str, long j) {
            this.properties.put(str, JsonValue.wrap(j));
            return this;
        }

        public Builder addProperty(String str, double d) {
            if (Double.isNaN(d) || Double.isInfinite(d)) {
                throw new NumberFormatException("Infinity or NaN: " + d);
            }
            this.properties.put(str, JsonValue.wrap(d));
            return this;
        }

        public Builder addProperty(String str, boolean z) {
            this.properties.put(str, JsonValue.wrap(z));
            return this;
        }

        public CustomEvent build() {
            return new CustomEvent(this);
        }
    }
}
