package com.urbanairship.wallet;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.urbanairship.Logger;
import com.urbanairship.json.JsonValue;
import com.urbanairship.util.UAStringUtil;

public class Pass implements Parcelable {
    public static final Parcelable.Creator<Pass> CREATOR = new Parcelable.Creator<Pass>() {
        public Pass createFromParcel(Parcel parcel) {
            return new Pass(parcel);
        }

        public Pass[] newArray(int i) {
            return new Pass[i];
        }
    };
    private static final String ID_KEY = "id";
    private static final String PUBLIC_URL_KEY = "publicUrl";
    private static final String PUBLIC_URL_PATH_KEY = "path";
    private final String id;
    private final Uri publicUri;

    public int describeContents() {
        return 0;
    }

    Pass(Uri uri, String str) {
        this.publicUri = uri;
        this.id = str;
    }

    protected Pass(Parcel parcel) {
        this.publicUri = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.id = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.publicUri, i);
        parcel.writeString(this.id);
    }

    public void requestToSavePass(Context context) {
        context.startActivity(new Intent("android.intent.action.VIEW").setData(this.publicUri).setFlags(268435456));
    }

    public Uri getPublicUri() {
        return this.publicUri;
    }

    public String getId() {
        return this.id;
    }

    static Pass parsePass(JsonValue jsonValue) {
        String string = jsonValue.optMap().opt("id").getString();
        String string2 = jsonValue.optMap().opt(PUBLIC_URL_KEY).optMap().opt(PUBLIC_URL_PATH_KEY).getString();
        if (!UAStringUtil.isEmpty(string2)) {
            return new Pass(Uri.parse(string2), string);
        }
        Logger.error("Pass - unable to parse URI from %s", jsonValue);
        return null;
    }
}
