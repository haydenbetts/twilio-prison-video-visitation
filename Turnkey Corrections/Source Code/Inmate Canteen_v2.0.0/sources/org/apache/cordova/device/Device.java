package org.apache.cordova.device;

import android.os.Build;
import android.provider.Settings;
import java.util.TimeZone;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Device extends CordovaPlugin {
    private static final String AMAZON_DEVICE = "Amazon";
    private static final String AMAZON_PLATFORM = "amazon-fireos";
    private static final String ANDROID_PLATFORM = "Android";
    public static final String TAG = "Device";
    public static String platform;
    public static String uuid;

    public void initialize(CordovaInterface cordovaInterface, CordovaWebView cordovaWebView) {
        super.initialize(cordovaInterface, cordovaWebView);
        uuid = getUuid();
    }

    public boolean execute(String str, JSONArray jSONArray, CallbackContext callbackContext) throws JSONException {
        if (!"getDeviceInfo".equals(str)) {
            return false;
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("uuid", uuid);
        jSONObject.put("version", getOSVersion());
        jSONObject.put("platform", getPlatform());
        jSONObject.put("model", getModel());
        jSONObject.put("manufacturer", getManufacturer());
        jSONObject.put("isVirtual", isVirtual());
        jSONObject.put("serial", getSerialNumber());
        callbackContext.success(jSONObject);
        return true;
    }

    public String getPlatform() {
        return isAmazonDevice() ? AMAZON_PLATFORM : ANDROID_PLATFORM;
    }

    public String getUuid() {
        return Settings.Secure.getString(this.cordova.getActivity().getContentResolver(), "android_id");
    }

    public String getModel() {
        return Build.MODEL;
    }

    public String getProductName() {
        return Build.PRODUCT;
    }

    public String getManufacturer() {
        return Build.MANUFACTURER;
    }

    public String getSerialNumber() {
        return Build.SERIAL;
    }

    public String getOSVersion() {
        return Build.VERSION.RELEASE;
    }

    public String getSDKVersion() {
        return Build.VERSION.SDK;
    }

    public String getTimeZoneID() {
        return TimeZone.getDefault().getID();
    }

    public boolean isAmazonDevice() {
        return Build.MANUFACTURER.equals(AMAZON_DEVICE);
    }

    public boolean isVirtual() {
        return Build.FINGERPRINT.contains("generic") || Build.PRODUCT.contains("sdk");
    }
}
