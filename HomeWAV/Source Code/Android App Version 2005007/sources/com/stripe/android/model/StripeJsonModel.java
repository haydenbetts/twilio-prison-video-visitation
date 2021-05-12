package com.stripe.android.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class StripeJsonModel {
    public abstract JSONObject toJson();

    public abstract Map<String, Object> toMap();

    static void putStripeJsonModelMapIfNotNull(Map<String, Object> map, String str, StripeJsonModel stripeJsonModel) {
        if (stripeJsonModel != null) {
            map.put(str, stripeJsonModel.toMap());
        }
    }

    static void putStripeJsonModelIfNotNull(JSONObject jSONObject, String str, StripeJsonModel stripeJsonModel) {
        if (stripeJsonModel != null) {
            try {
                jSONObject.put(str, stripeJsonModel.toJson());
            } catch (JSONException unused) {
            }
        }
    }

    static void putStripeJsonModelListIfNotNull(Map<String, Object> map, String str, List<? extends StripeJsonModel> list) {
        if (list != null) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                arrayList.add(((StripeJsonModel) list.get(i)).toMap());
            }
            map.put(str, arrayList);
        }
    }

    static void putStripeJsonModelListIfNotNull(JSONObject jSONObject, String str, List<? extends StripeJsonModel> list) {
        if (list != null) {
            try {
                JSONArray jSONArray = new JSONArray();
                for (StripeJsonModel json : list) {
                    jSONArray.put(json.toJson());
                }
                jSONObject.put(str, jSONArray);
            } catch (JSONException unused) {
            }
        }
    }
}
