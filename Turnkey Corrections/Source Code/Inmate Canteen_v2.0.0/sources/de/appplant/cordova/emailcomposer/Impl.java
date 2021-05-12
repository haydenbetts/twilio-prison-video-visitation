package de.appplant.cordova.emailcomposer;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Parcelable;
import android.text.Html;
import android.util.Log;
import android.util.Patterns;
import java.util.ArrayList;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

class Impl {
    private static final String MAILTO_SCHEME = "mailto:";
    private final Context ctx;

    Impl(Context context) {
        this.ctx = context;
    }

    /* access modifiers changed from: package-private */
    public boolean[] canSendMail(String str) {
        return new boolean[]{isEmailAccountConfigured(), isAppInstalled(str)};
    }

    /* access modifiers changed from: package-private */
    public Intent getDraft(JSONObject jSONObject) {
        Intent emailIntent = getEmailIntent();
        String optString = jSONObject.optString("app", MAILTO_SCHEME);
        if (jSONObject.has("subject")) {
            setSubject(jSONObject, emailIntent);
        }
        if (jSONObject.has("body")) {
            setBody(jSONObject, emailIntent);
        }
        if (jSONObject.has("to")) {
            setRecipients(jSONObject, emailIntent);
        }
        if (jSONObject.has("cc")) {
            setCcRecipients(jSONObject, emailIntent);
        }
        if (jSONObject.has("bcc")) {
            setBccRecipients(jSONObject, emailIntent);
        }
        if (jSONObject.has("attachments")) {
            setAttachments(jSONObject, emailIntent);
        }
        if (jSONObject.has("type")) {
            setType(jSONObject, emailIntent);
        }
        if (!optString.equals(MAILTO_SCHEME) && isAppInstalled(optString)) {
            emailIntent.setPackage(optString);
        }
        return emailIntent;
    }

    private void setSubject(JSONObject jSONObject, Intent intent) {
        intent.putExtra("android.intent.extra.SUBJECT", jSONObject.optString("subject"));
    }

    private void setBody(JSONObject jSONObject, Intent intent) {
        String optString = jSONObject.optString("body");
        CharSequence charSequence = optString;
        if (jSONObject.optBoolean("isHtml")) {
            charSequence = Html.fromHtml(optString);
        }
        intent.putExtra("android.intent.extra.TEXT", charSequence);
    }

    private void setRecipients(JSONObject jSONObject, Intent intent) {
        insertRecipients(intent, jSONObject, "to", "android.intent.extra.EMAIL");
    }

    private void setCcRecipients(JSONObject jSONObject, Intent intent) {
        insertRecipients(intent, jSONObject, "cc", "android.intent.extra.CC");
    }

    private void setBccRecipients(JSONObject jSONObject, Intent intent) {
        insertRecipients(intent, jSONObject, "bcc", "android.intent.extra.BCC");
    }

    private void insertRecipients(Intent intent, JSONObject jSONObject, String str, String str2) {
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        String[] strArr = new String[optJSONArray.length()];
        for (int i = 0; i < optJSONArray.length(); i++) {
            strArr[i] = optJSONArray.optString(i);
        }
        intent.putExtra(str2, strArr);
    }

    private void setAttachments(JSONObject jSONObject, Intent intent) {
        JSONArray optJSONArray = jSONObject.optJSONArray("attachments");
        ArrayList arrayList = new ArrayList();
        AssetUtil assetUtil = new AssetUtil(this.ctx);
        for (int i = 0; i < optJSONArray.length(); i++) {
            Uri parse = assetUtil.parse(optJSONArray.optString(i));
            if (!(parse == null || parse == Uri.EMPTY)) {
                arrayList.add(parse);
            }
        }
        if (!arrayList.isEmpty()) {
            intent.setAction("android.intent.action.SEND_MULTIPLE").setType("message/rfc822").addFlags(1).putExtra("android.intent.extra.STREAM", arrayList);
            if (arrayList.size() <= 1) {
                intent.setAction("android.intent.action.SEND").putExtra("android.intent.extra.STREAM", (Parcelable) arrayList.get(0));
            }
        }
    }

    private void setType(JSONObject jSONObject, Intent intent) {
        intent.setType(jSONObject.optString("type", "message/rfc822"));
    }

    private boolean isEmailAccountConfigured() {
        AccountManager accountManager = AccountManager.get(this.ctx);
        try {
            Pattern pattern = Patterns.EMAIL_ADDRESS;
            for (Account account : accountManager.getAccounts()) {
                if (pattern.matcher(account.name).matches()) {
                    return true;
                }
            }
        } catch (Exception unused) {
            Log.w("EmailComposer", "Missing GET_ACCOUNTS permission.");
        }
        return false;
    }

    private boolean isAppInstalled(String str) {
        if (str.equalsIgnoreCase(MAILTO_SCHEME)) {
            if (this.ctx.getPackageManager().queryIntentActivities(getEmailIntent(), 0).size() > 0) {
                return true;
            }
            return false;
        }
        try {
            this.ctx.getPackageManager().getPackageInfo(str, 0);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    private static Intent getEmailIntent() {
        Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse(MAILTO_SCHEME));
        intent.addFlags(268435456);
        return intent;
    }
}
