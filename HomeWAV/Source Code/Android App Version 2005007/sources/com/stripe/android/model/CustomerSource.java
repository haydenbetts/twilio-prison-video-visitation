package com.stripe.android.model;

import com.stripe.android.utils.ObjectUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class CustomerSource extends StripeJsonModel implements StripePaymentSource {
    private final StripePaymentSource mStripePaymentSource;

    private CustomerSource(StripePaymentSource stripePaymentSource) {
        this.mStripePaymentSource = stripePaymentSource;
    }

    public StripePaymentSource getStripePaymentSource() {
        return this.mStripePaymentSource;
    }

    public String getId() {
        return this.mStripePaymentSource.getId();
    }

    public Source asSource() {
        StripePaymentSource stripePaymentSource = this.mStripePaymentSource;
        if (stripePaymentSource instanceof Source) {
            return (Source) stripePaymentSource;
        }
        return null;
    }

    public String getTokenizationMethod() {
        Source asSource = asSource();
        Card asCard = asCard();
        if (asSource != null && "card".equals(asSource.getType())) {
            SourceCardData sourceCardData = (SourceCardData) asSource.getSourceTypeModel();
            if (sourceCardData != null) {
                return sourceCardData.getTokenizationMethod();
            }
            return null;
        } else if (asCard != null) {
            return asCard.getTokenizationMethod();
        } else {
            return null;
        }
    }

    public Card asCard() {
        StripePaymentSource stripePaymentSource = this.mStripePaymentSource;
        if (stripePaymentSource instanceof Card) {
            return (Card) stripePaymentSource;
        }
        return null;
    }

    public String getSourceType() {
        StripePaymentSource stripePaymentSource = this.mStripePaymentSource;
        if (stripePaymentSource instanceof Card) {
            return "card";
        }
        return stripePaymentSource instanceof Source ? ((Source) stripePaymentSource).getType() : "unknown";
    }

    public static CustomerSource fromString(String str) {
        try {
            return fromJson(new JSONObject(str));
        } catch (JSONException unused) {
            return null;
        }
    }

    public static CustomerSource fromJson(JSONObject jSONObject) {
        StripePaymentSource stripePaymentSource;
        if (jSONObject == null) {
            return null;
        }
        String optString = StripeJsonUtils.optString(jSONObject, "object");
        if ("card".equals(optString)) {
            stripePaymentSource = Card.fromJson(jSONObject);
        } else {
            stripePaymentSource = "source".equals(optString) ? Source.fromJson(jSONObject) : null;
        }
        if (stripePaymentSource == null) {
            return null;
        }
        return new CustomerSource(stripePaymentSource);
    }

    public Map<String, Object> toMap() {
        StripePaymentSource stripePaymentSource = this.mStripePaymentSource;
        if (stripePaymentSource instanceof Source) {
            return ((Source) stripePaymentSource).toMap();
        }
        if (stripePaymentSource instanceof Card) {
            return ((Card) stripePaymentSource).toMap();
        }
        return new HashMap();
    }

    public JSONObject toJson() {
        StripePaymentSource stripePaymentSource = this.mStripePaymentSource;
        if (stripePaymentSource instanceof Source) {
            return ((Source) stripePaymentSource).toJson();
        }
        if (stripePaymentSource instanceof Card) {
            return ((Card) stripePaymentSource).toJson();
        }
        return new JSONObject();
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof CustomerSource) && typedEquals((CustomerSource) obj));
    }

    private boolean typedEquals(CustomerSource customerSource) {
        return ObjectUtils.equals(this.mStripePaymentSource, customerSource.mStripePaymentSource);
    }

    public int hashCode() {
        return ObjectUtils.hash(this.mStripePaymentSource);
    }
}
