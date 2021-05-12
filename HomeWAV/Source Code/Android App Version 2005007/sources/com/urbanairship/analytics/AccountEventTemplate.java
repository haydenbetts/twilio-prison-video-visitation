package com.urbanairship.analytics;

import com.urbanairship.analytics.CustomEvent;
import java.math.BigDecimal;

public class AccountEventTemplate {
    public static final String ACCOUNT_EVENT_TEMPLATE = "account";
    private static final String CATEGORY = "category";
    private static final String LIFETIME_VALUE = "ltv";
    public static final String REGISTERED_ACCOUNT_EVENT = "registered_account";
    private String category;
    private String transactionId;
    private BigDecimal value;

    private AccountEventTemplate() {
    }

    public static AccountEventTemplate newRegisteredTemplate() {
        return new AccountEventTemplate();
    }

    public AccountEventTemplate setTransactionId(String str) {
        this.transactionId = str;
        return this;
    }

    public AccountEventTemplate setValue(BigDecimal bigDecimal) {
        this.value = bigDecimal;
        return this;
    }

    public AccountEventTemplate setValue(double d) {
        return setValue(BigDecimal.valueOf(d));
    }

    public AccountEventTemplate setValue(String str) {
        if (str != null && str.length() != 0) {
            return setValue(new BigDecimal(str));
        }
        this.value = null;
        return this;
    }

    public AccountEventTemplate setValue(int i) {
        return setValue(new BigDecimal(i));
    }

    public AccountEventTemplate setCategory(String str) {
        this.category = str;
        return this;
    }

    public CustomEvent createEvent() {
        CustomEvent.Builder newBuilder = CustomEvent.newBuilder(REGISTERED_ACCOUNT_EVENT);
        BigDecimal bigDecimal = this.value;
        if (bigDecimal != null) {
            newBuilder.setEventValue(bigDecimal);
            newBuilder.addProperty(LIFETIME_VALUE, true);
        } else {
            newBuilder.addProperty(LIFETIME_VALUE, false);
        }
        String str = this.transactionId;
        if (str != null) {
            newBuilder.setTransactionId(str);
        }
        String str2 = this.category;
        if (str2 != null) {
            newBuilder.addProperty(CATEGORY, str2);
        }
        newBuilder.setTemplateType("account");
        return newBuilder.build();
    }
}
