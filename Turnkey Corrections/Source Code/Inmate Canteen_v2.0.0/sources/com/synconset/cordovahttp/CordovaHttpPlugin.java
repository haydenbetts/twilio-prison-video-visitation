package com.synconset.cordovahttp;

import com.github.kevinsawicki.http.HttpRequest;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

public class CordovaHttpPlugin extends CordovaPlugin {
    private static final String TAG = "CordovaHTTP";

    public void initialize(CordovaInterface cordovaInterface, CordovaWebView cordovaWebView) {
        super.initialize(cordovaInterface, cordovaWebView);
    }

    public boolean execute(String str, JSONArray jSONArray, CallbackContext callbackContext) throws JSONException {
        String str2 = str;
        JSONArray jSONArray2 = jSONArray;
        if (str2.equals("post")) {
            this.cordova.getThreadPool().execute(new CordovaHttpPost(jSONArray2.getString(0), jSONArray2.get(1), jSONArray2.getString(2), jSONArray2.getJSONObject(3), jSONArray2.getInt(4) * 1000, callbackContext));
        } else if (str2.equals("get")) {
            this.cordova.getThreadPool().execute(new CordovaHttpGet(jSONArray2.getString(0), jSONArray2.get(1), jSONArray2.getJSONObject(2), jSONArray2.getInt(3) * 1000, callbackContext));
        } else if (str2.equals("put")) {
            this.cordova.getThreadPool().execute(new CordovaHttpPut(jSONArray2.getString(0), jSONArray2.get(1), jSONArray2.getString(2), jSONArray2.getJSONObject(3), jSONArray2.getInt(4) * 1000, callbackContext));
        } else if (str2.equals("patch")) {
            this.cordova.getThreadPool().execute(new CordovaHttpPatch(jSONArray2.getString(0), jSONArray2.get(1), jSONArray2.getString(2), jSONArray2.getJSONObject(3), jSONArray2.getInt(4) * 1000, callbackContext));
        } else if (str2.equals("delete")) {
            this.cordova.getThreadPool().execute(new CordovaHttpDelete(jSONArray2.getString(0), jSONArray2.get(1), jSONArray2.getJSONObject(2), jSONArray2.getInt(3) * 1000, callbackContext));
        } else if (str2.equals("head")) {
            this.cordova.getThreadPool().execute(new CordovaHttpHead(jSONArray2.getString(0), jSONArray2.get(1), jSONArray2.getJSONObject(2), jSONArray2.getInt(3) * 1000, callbackContext));
        } else if (str2.equals("setSSLCertMode")) {
            String string = jSONArray2.getString(0);
            if (string.equals(HttpRequest.CERT_MODE_DEFAULT)) {
                HttpRequest.setSSLCertMode(HttpRequest.CERT_MODE_DEFAULT);
                callbackContext.success();
            } else if (string.equals("nocheck")) {
                HttpRequest.setSSLCertMode(HttpRequest.CERT_MODE_TRUSTALL);
                callbackContext.success();
            } else if (string.equals(HttpRequest.CERT_MODE_PINNED)) {
                try {
                    loadSSLCerts();
                    HttpRequest.setSSLCertMode(HttpRequest.CERT_MODE_PINNED);
                    callbackContext.success();
                } catch (Exception e) {
                    e.printStackTrace();
                    callbackContext.error("There was an error setting up ssl pinning");
                }
            }
        } else {
            CallbackContext callbackContext2 = callbackContext;
            if (str2.equals("uploadFile")) {
                this.cordova.getThreadPool().execute(new CordovaHttpUpload(jSONArray2.getString(0), jSONArray2.get(1), jSONArray2.getJSONObject(2), jSONArray2.getString(3), jSONArray2.getString(4), jSONArray2.getInt(5) * 1000, callbackContext));
            } else if (str2.equals("downloadFile")) {
                this.cordova.getThreadPool().execute(new CordovaHttpDownload(jSONArray2.getString(0), jSONArray2.get(1), jSONArray2.getJSONObject(2), jSONArray2.getString(3), jSONArray2.getInt(4) * 1000, callbackContext));
            } else if (!str2.equals("disableRedirect")) {
                return false;
            } else {
                CordovaHttp.disableRedirect(jSONArray2.getBoolean(0));
                callbackContext.success();
            }
        }
        return true;
    }

    private void loadSSLCerts() throws GeneralSecurityException, IOException {
        String[] list = this.cordova.getActivity().getAssets().list("www/certificates");
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.length; i++) {
            int lastIndexOf = list[i].lastIndexOf(46);
            if (lastIndexOf != -1 && list[i].substring(lastIndexOf).equals(".cer")) {
                arrayList.add("www/certificates/" + list[i]);
            }
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            HttpRequest.addCert((InputStream) new BufferedInputStream(this.cordova.getActivity().getAssets().open((String) arrayList.get(i2))));
        }
    }
}
