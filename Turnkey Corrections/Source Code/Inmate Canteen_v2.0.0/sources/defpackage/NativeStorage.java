package defpackage;

import android.content.SharedPreferences;
import android.util.Log;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

/* renamed from: NativeStorage  reason: default package */
public class NativeStorage extends CordovaPlugin {
    public static final String TAG = "Native Storage";
    /* access modifiers changed from: private */
    public SharedPreferences.Editor editor;
    /* access modifiers changed from: private */
    public SharedPreferences sharedPref;

    public void initialize(CordovaInterface cordovaInterface, CordovaWebView cordovaWebView) {
        super.initialize(cordovaInterface, cordovaWebView);
        Log.v(TAG, "Init NativeStorage");
        this.sharedPref = cordovaInterface.getActivity().getSharedPreferences(this.preferences.getString("NativeStorageSharedPreferencesName", "NativeStorage"), 0);
        this.editor = this.sharedPref.edit();
    }

    public boolean execute(String str, final JSONArray jSONArray, final CallbackContext callbackContext) throws JSONException {
        if ("remove".equals(str)) {
            this.cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    try {
                        NativeStorage.this.editor.remove(jSONArray.getString(0));
                        if (NativeStorage.this.editor.commit()) {
                            callbackContext.success();
                        } else {
                            callbackContext.error("Remove operation failed");
                        }
                    } catch (Exception e) {
                        Log.e(NativeStorage.TAG, "Removing failed :", e);
                        callbackContext.error(e.getMessage());
                    }
                }
            });
            return true;
        } else if ("clear".equals(str)) {
            this.cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    try {
                        NativeStorage.this.editor.clear();
                        if (NativeStorage.this.editor.commit()) {
                            callbackContext.success();
                        } else {
                            callbackContext.error("Clear operation failed");
                        }
                    } catch (Exception e) {
                        Log.e(NativeStorage.TAG, "Clearing failed :", e);
                        callbackContext.error(e.getMessage());
                    }
                }
            });
            return true;
        } else if ("putBoolean".equals(str)) {
            this.cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    try {
                        String string = jSONArray.getString(0);
                        Boolean valueOf = Boolean.valueOf(jSONArray.getBoolean(1));
                        NativeStorage.this.editor.putBoolean(string, valueOf.booleanValue());
                        if (NativeStorage.this.editor.commit()) {
                            callbackContext.success(String.valueOf(valueOf));
                        } else {
                            callbackContext.error("Write failed");
                        }
                    } catch (Exception e) {
                        Log.e(NativeStorage.TAG, "PutBoolean failed :", e);
                        callbackContext.error(e.getMessage());
                    }
                }
            });
            return true;
        } else if ("getBoolean".equals(str)) {
            this.cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    try {
                        callbackContext.success(String.valueOf(Boolean.valueOf(NativeStorage.this.sharedPref.getBoolean(jSONArray.getString(0), false))));
                    } catch (Exception e) {
                        Log.e(NativeStorage.TAG, "PutBoolean failed :", e);
                        callbackContext.error(e.getMessage());
                    }
                }
            });
            return true;
        } else if ("putInt".equals(str)) {
            this.cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    try {
                        String string = jSONArray.getString(0);
                        int i = jSONArray.getInt(1);
                        NativeStorage.this.editor.putInt(string, i);
                        if (NativeStorage.this.editor.commit()) {
                            callbackContext.success(i);
                        } else {
                            callbackContext.error("Write failed");
                        }
                    } catch (Exception e) {
                        Log.e(NativeStorage.TAG, "PutInt failed :", e);
                        callbackContext.error(e.getMessage());
                    }
                }
            });
            return true;
        } else if ("getInt".equals(str)) {
            this.cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    try {
                        callbackContext.success(NativeStorage.this.sharedPref.getInt(jSONArray.getString(0), -1));
                    } catch (Exception e) {
                        Log.e(NativeStorage.TAG, "GetInt failed :", e);
                        callbackContext.error(e.getMessage());
                    }
                }
            });
            return true;
        } else if ("putDouble".equals(str)) {
            this.cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    try {
                        String string = jSONArray.getString(0);
                        float f = (float) jSONArray.getDouble(1);
                        NativeStorage.this.editor.putFloat(string, f);
                        if (NativeStorage.this.editor.commit()) {
                            callbackContext.success(Float.toString(f));
                        } else {
                            callbackContext.error("Write failed");
                        }
                    } catch (Exception e) {
                        Log.e(NativeStorage.TAG, "PutFloat failed :", e);
                        callbackContext.error(e.getMessage());
                    }
                }
            });
            return true;
        } else if ("getDouble".equals(str)) {
            this.cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    try {
                        callbackContext.success(Float.toString(NativeStorage.this.sharedPref.getFloat(jSONArray.getString(0), -1.0f)));
                    } catch (Exception e) {
                        Log.e(NativeStorage.TAG, "GetFloat failed :", e);
                        callbackContext.error(e.getMessage());
                    }
                }
            });
            return true;
        } else if ("putString".equals(str)) {
            this.cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    try {
                        String string = jSONArray.getString(0);
                        String string2 = jSONArray.getString(1);
                        NativeStorage.this.editor.putString(string, string2);
                        if (NativeStorage.this.editor.commit()) {
                            callbackContext.success(string2);
                        } else {
                            callbackContext.error("Write failed");
                        }
                    } catch (Exception e) {
                        Log.e(NativeStorage.TAG, "PutString failed :", e);
                        callbackContext.error(e.getMessage());
                    }
                }
            });
            return true;
        } else if ("getString".equals(str)) {
            this.cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    try {
                        callbackContext.success(NativeStorage.this.sharedPref.getString(jSONArray.getString(0), "null"));
                    } catch (Exception e) {
                        Log.e(NativeStorage.TAG, "GetString failed :", e);
                        callbackContext.error(e.getMessage());
                    }
                }
            });
            return true;
        } else if ("setItem".equals(str)) {
            this.cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    try {
                        String string = jSONArray.getString(0);
                        String string2 = jSONArray.getString(1);
                        NativeStorage.this.editor.putString(string, string2);
                        if (NativeStorage.this.editor.commit()) {
                            callbackContext.success(string2);
                        } else {
                            callbackContext.error(1);
                        }
                    } catch (Exception e) {
                        Log.e(NativeStorage.TAG, "setItem :", e);
                        callbackContext.error(e.getMessage());
                    }
                }
            });
            return true;
        } else if ("setItemWithPassword".equals(str)) {
            this.cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    try {
                        String string = jSONArray.getString(0);
                        String string2 = jSONArray.getString(1);
                        String str = "";
                        try {
                            str = Crypto.encrypt(string2, jSONArray.getString(2));
                        } catch (InvalidKeySpecException e) {
                            e.printStackTrace();
                            callbackContext.error(e.getMessage());
                        } catch (NoSuchAlgorithmException e2) {
                            e2.printStackTrace();
                            callbackContext.error(e2.getMessage());
                        } catch (NoSuchPaddingException e3) {
                            e3.printStackTrace();
                            callbackContext.error(e3.getMessage());
                        } catch (InvalidAlgorithmParameterException e4) {
                            e4.printStackTrace();
                            callbackContext.error(e4.getMessage());
                        } catch (InvalidKeyException e5) {
                            e5.printStackTrace();
                            callbackContext.error(e5.getMessage());
                        } catch (UnsupportedEncodingException e6) {
                            e6.printStackTrace();
                            callbackContext.error(e6.getMessage());
                        } catch (BadPaddingException e7) {
                            e7.printStackTrace();
                            callbackContext.error(e7.getMessage());
                        } catch (IllegalBlockSizeException e8) {
                            e8.printStackTrace();
                            callbackContext.error(e8.getMessage());
                        }
                        if (!str.equals("")) {
                            NativeStorage.this.editor.putString(string, str);
                            if (NativeStorage.this.editor.commit()) {
                                callbackContext.success(string2);
                            } else {
                                callbackContext.error(1);
                            }
                        } else {
                            callbackContext.error("Encryption failed");
                        }
                    } catch (Exception e9) {
                        Log.e(NativeStorage.TAG, "setItem :", e9);
                        e9.printStackTrace();
                        callbackContext.error(e9.getMessage());
                    }
                }
            });
            return true;
        } else if ("getItem".equals(str)) {
            this.cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    try {
                        String string = NativeStorage.this.sharedPref.getString(jSONArray.getString(0), "nativestorage_null");
                        if (string.equals("nativestorage_null")) {
                            callbackContext.error(2);
                        } else {
                            callbackContext.success(string);
                        }
                    } catch (Exception e) {
                        Log.e(NativeStorage.TAG, "getItem failed :", e);
                        callbackContext.error(e.getMessage());
                    }
                }
            });
            return true;
        } else if ("getItemWithPassword".equals(str)) {
            this.cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    try {
                        String string = jSONArray.getString(0);
                        String string2 = jSONArray.getString(1);
                        String string3 = NativeStorage.this.sharedPref.getString(string, "nativestorage_null");
                        if (string3.equals("nativestorage_null")) {
                            callbackContext.error(2);
                            return;
                        }
                        try {
                            callbackContext.success(Crypto.decryptPbkdf2(string3, string2));
                        } catch (InvalidKeySpecException e) {
                            e.printStackTrace();
                            callbackContext.error(e.getMessage());
                        } catch (NoSuchAlgorithmException e2) {
                            e2.printStackTrace();
                            callbackContext.error(e2.getMessage());
                        } catch (NoSuchPaddingException e3) {
                            e3.printStackTrace();
                            callbackContext.error(e3.getMessage());
                        } catch (InvalidAlgorithmParameterException e4) {
                            e4.printStackTrace();
                            callbackContext.error(e4.getMessage());
                        } catch (InvalidKeyException e5) {
                            e5.printStackTrace();
                            callbackContext.error(e5.getMessage());
                        } catch (UnsupportedEncodingException e6) {
                            e6.printStackTrace();
                            callbackContext.error(e6.getMessage());
                        } catch (BadPaddingException e7) {
                            e7.printStackTrace();
                            callbackContext.error(e7.getMessage());
                        } catch (IllegalBlockSizeException e8) {
                            e8.printStackTrace();
                            callbackContext.error(e8.getMessage());
                        }
                    } catch (Exception e9) {
                        Log.e(NativeStorage.TAG, "getItem failed :", e9);
                        callbackContext.error(e9.getMessage());
                    }
                }
            });
            return true;
        } else if (!"keys".equals(str)) {
            return false;
        } else {
            this.cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    try {
                        callbackContext.success(new JSONArray(NativeStorage.this.sharedPref.getAll().keySet()));
                    } catch (Exception e) {
                        Log.e(NativeStorage.TAG, "Get keys failed :", e);
                        callbackContext.error(e.getMessage());
                    }
                }
            });
            return true;
        }
    }
}
