package com.urbanairship.analytics;

import com.urbanairship.analytics.CustomEvent;
import java.math.BigDecimal;

public class MediaEventTemplate {
    private static final String AUTHOR = "author";
    public static final String BROWSED_CONTENT_EVENT = "browsed_content";
    private static final String CATEGORY = "category";
    public static final String CONSUMED_CONTENT_EVENT = "consumed_content";
    private static final String DESCRIPTION = "description";
    private static final String FEATURE = "feature";
    private static final String ID = "id";
    private static final String LIFETIME_VALUE = "ltv";
    public static final String MEDIA_EVENT_TEMPLATE = "media";
    private static final String MEDIUM = "medium";
    private static final String PUBLISHED_DATE = "published_date";
    public static final String SHARED_CONTENT_EVENT = "shared_content";
    private static final String SOURCE = "source";
    public static final String STARRED_CONTENT_EVENT = "starred_content";
    private static final String TYPE = "type";
    private String author;
    private String category;
    private String description;
    private final String eventName;
    private boolean feature;
    private boolean featureSet;
    private String id;
    private String medium;
    private String publishedDate;
    private String source;
    private String type;
    private BigDecimal value;

    private MediaEventTemplate(String str, BigDecimal bigDecimal) {
        this.eventName = str;
        this.value = bigDecimal;
    }

    private MediaEventTemplate(String str, String str2, String str3) {
        this.eventName = str;
        this.source = str2;
        this.medium = str3;
    }

    public static MediaEventTemplate newStarredTemplate() {
        return new MediaEventTemplate(STARRED_CONTENT_EVENT, (BigDecimal) null);
    }

    public static MediaEventTemplate newSharedTemplate() {
        return new MediaEventTemplate(SHARED_CONTENT_EVENT, (BigDecimal) null);
    }

    public static MediaEventTemplate newSharedTemplate(String str, String str2) {
        return new MediaEventTemplate(SHARED_CONTENT_EVENT, str, str2);
    }

    public static MediaEventTemplate newConsumedTemplate() {
        return new MediaEventTemplate(CONSUMED_CONTENT_EVENT, (BigDecimal) null);
    }

    public static MediaEventTemplate newBrowsedTemplate() {
        return new MediaEventTemplate(BROWSED_CONTENT_EVENT, (BigDecimal) null);
    }

    public static MediaEventTemplate newConsumedTemplate(BigDecimal bigDecimal) {
        return new MediaEventTemplate(CONSUMED_CONTENT_EVENT, bigDecimal);
    }

    public static MediaEventTemplate newConsumedTemplate(double d) {
        return new MediaEventTemplate(CONSUMED_CONTENT_EVENT, BigDecimal.valueOf(d));
    }

    public static MediaEventTemplate newConsumedTemplate(String str) {
        if (str == null || str.length() == 0) {
            return new MediaEventTemplate(CONSUMED_CONTENT_EVENT, (BigDecimal) null);
        }
        return new MediaEventTemplate(CONSUMED_CONTENT_EVENT, new BigDecimal(str));
    }

    public static MediaEventTemplate newConsumedTemplate(int i) {
        return new MediaEventTemplate(CONSUMED_CONTENT_EVENT, new BigDecimal(i));
    }

    public MediaEventTemplate setId(String str) {
        this.id = str;
        return this;
    }

    public MediaEventTemplate setCategory(String str) {
        this.category = str;
        return this;
    }

    public MediaEventTemplate setType(String str) {
        this.type = str;
        return this;
    }

    public MediaEventTemplate setDescription(String str) {
        this.description = str;
        return this;
    }

    public MediaEventTemplate setFeature(boolean z) {
        this.feature = z;
        this.featureSet = true;
        return this;
    }

    public MediaEventTemplate setAuthor(String str) {
        this.author = str;
        return this;
    }

    public MediaEventTemplate setPublishedDate(String str) {
        this.publishedDate = str;
        return this;
    }

    public CustomEvent createEvent() {
        CustomEvent.Builder newBuilder = CustomEvent.newBuilder(this.eventName);
        BigDecimal bigDecimal = this.value;
        if (bigDecimal != null) {
            newBuilder.setEventValue(bigDecimal);
            newBuilder.addProperty(LIFETIME_VALUE, true);
        } else {
            newBuilder.addProperty(LIFETIME_VALUE, false);
        }
        String str = this.id;
        if (str != null) {
            newBuilder.addProperty("id", str);
        }
        String str2 = this.category;
        if (str2 != null) {
            newBuilder.addProperty(CATEGORY, str2);
        }
        String str3 = this.description;
        if (str3 != null) {
            newBuilder.addProperty(DESCRIPTION, str3);
        }
        String str4 = this.type;
        if (str4 != null) {
            newBuilder.addProperty("type", str4);
        }
        if (this.featureSet) {
            newBuilder.addProperty(FEATURE, this.feature);
        }
        String str5 = this.author;
        if (str5 != null) {
            newBuilder.addProperty(AUTHOR, str5);
        }
        String str6 = this.publishedDate;
        if (str6 != null) {
            newBuilder.addProperty(PUBLISHED_DATE, str6);
        }
        String str7 = this.source;
        if (str7 != null) {
            newBuilder.addProperty("source", str7);
        }
        String str8 = this.medium;
        if (str8 != null) {
            newBuilder.addProperty("medium", str8);
        }
        newBuilder.setTemplateType("media");
        return newBuilder.build();
    }
}
