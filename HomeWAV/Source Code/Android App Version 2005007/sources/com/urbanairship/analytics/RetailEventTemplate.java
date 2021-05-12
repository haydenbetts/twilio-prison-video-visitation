package com.urbanairship.analytics;

import com.urbanairship.analytics.CustomEvent;
import java.math.BigDecimal;

public class RetailEventTemplate {
    public static final String ADDED_TO_CART_EVENT = "added_to_cart";
    private static final String BRAND = "brand";
    public static final String BROWSED_PRODUCT_EVENT = "browsed";
    private static final String CATEGORY = "category";
    private static final String DESCRIPTION = "description";
    private static final String ID = "id";
    private static final String LIFETIME_VALUE = "ltv";
    private static final String MEDIUM = "medium";
    private static final String NEW_ITEM = "new_item";
    public static final String PURCHASED_EVENT = "purchased";
    public static final String RETAIL_EVENT_TEMPLATE = "retail";
    public static final String SHARED_PRODUCT_EVENT = "shared_product";
    private static final String SOURCE = "source";
    public static final String STARRED_PRODUCT_EVENT = "starred_product";
    private String brand;
    private String category;
    private String description;
    private final String eventName;
    private String id;
    private String medium;
    private boolean newItem;
    private boolean newItemSet;
    private String source;
    private String transactionId;
    private BigDecimal value;

    private RetailEventTemplate(String str) {
        this.eventName = str;
    }

    private RetailEventTemplate(String str, String str2, String str3) {
        this.eventName = str;
        this.source = str2;
        this.medium = str3;
    }

    public static RetailEventTemplate newBrowsedTemplate() {
        return new RetailEventTemplate(BROWSED_PRODUCT_EVENT);
    }

    public static RetailEventTemplate newAddedToCartTemplate() {
        return new RetailEventTemplate(ADDED_TO_CART_EVENT);
    }

    public static RetailEventTemplate newStarredProductTemplate() {
        return new RetailEventTemplate(STARRED_PRODUCT_EVENT);
    }

    public static RetailEventTemplate newSharedProductTemplate() {
        return new RetailEventTemplate(SHARED_PRODUCT_EVENT);
    }

    public static RetailEventTemplate newSharedProductTemplate(String str, String str2) {
        return new RetailEventTemplate(SHARED_PRODUCT_EVENT, str, str2);
    }

    public static RetailEventTemplate newPurchasedTemplate() {
        return new RetailEventTemplate(PURCHASED_EVENT);
    }

    public RetailEventTemplate setTransactionId(String str) {
        this.transactionId = str;
        return this;
    }

    public RetailEventTemplate setValue(BigDecimal bigDecimal) {
        this.value = bigDecimal;
        return this;
    }

    public RetailEventTemplate setValue(double d) {
        return setValue(BigDecimal.valueOf(d));
    }

    public RetailEventTemplate setValue(String str) {
        if (str != null && str.length() != 0) {
            return setValue(new BigDecimal(str));
        }
        this.value = null;
        return this;
    }

    public RetailEventTemplate setValue(int i) {
        return setValue(new BigDecimal(i));
    }

    public RetailEventTemplate setId(String str) {
        this.id = str;
        return this;
    }

    public RetailEventTemplate setCategory(String str) {
        this.category = str;
        return this;
    }

    public RetailEventTemplate setDescription(String str) {
        this.description = str;
        return this;
    }

    public RetailEventTemplate setBrand(String str) {
        this.brand = str;
        return this;
    }

    public RetailEventTemplate setNewItem(boolean z) {
        this.newItem = z;
        this.newItemSet = true;
        return this;
    }

    public CustomEvent createEvent() {
        CustomEvent.Builder newBuilder = CustomEvent.newBuilder(this.eventName);
        BigDecimal bigDecimal = this.value;
        if (bigDecimal != null) {
            newBuilder.setEventValue(bigDecimal);
        }
        if (!PURCHASED_EVENT.equals(this.eventName) || this.value == null) {
            newBuilder.addProperty(LIFETIME_VALUE, false);
        } else {
            newBuilder.addProperty(LIFETIME_VALUE, true);
        }
        String str = this.transactionId;
        if (str != null) {
            newBuilder.setTransactionId(str);
        }
        String str2 = this.id;
        if (str2 != null) {
            newBuilder.addProperty("id", str2);
        }
        String str3 = this.category;
        if (str3 != null) {
            newBuilder.addProperty(CATEGORY, str3);
        }
        String str4 = this.description;
        if (str4 != null) {
            newBuilder.addProperty(DESCRIPTION, str4);
        }
        String str5 = this.brand;
        if (str5 != null) {
            newBuilder.addProperty(BRAND, str5);
        }
        if (this.newItemSet) {
            newBuilder.addProperty(NEW_ITEM, this.newItem);
        }
        String str6 = this.source;
        if (str6 != null) {
            newBuilder.addProperty("source", str6);
        }
        String str7 = this.medium;
        if (str7 != null) {
            newBuilder.addProperty("medium", str7);
        }
        newBuilder.setTemplateType(RETAIL_EVENT_TEMPLATE);
        return newBuilder.build();
    }
}
