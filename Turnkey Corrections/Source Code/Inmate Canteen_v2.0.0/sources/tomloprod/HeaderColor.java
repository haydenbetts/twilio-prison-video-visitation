package tomloprod;

import android.app.ActivityManager;
import android.graphics.Color;
import android.os.Build;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

public class HeaderColor extends CordovaPlugin {
    public void initialize(CordovaInterface cordovaInterface, CordovaWebView cordovaWebView) {
        super.initialize(cordovaInterface, cordovaWebView);
        setColor(this.preferences.getString("HeaderColor", "#000000"));
    }

    public boolean execute(String str, JSONArray jSONArray, CallbackContext callbackContext) throws JSONException {
        if (str.equals("tint")) {
            setColor(jSONArray.getString(0));
        }
        return false;
    }

    private void setColor(String str) {
        int parseColor = Color.parseColor(str);
        if (Build.VERSION.SDK_INT >= 21) {
            for (ActivityManager.AppTask next : ((ActivityManager) this.cordova.getActivity().getSystemService("activity")).getAppTasks()) {
                if (next.getTaskInfo().id == this.cordova.getActivity().getTaskId()) {
                    ActivityManager.TaskDescription taskDescription = next.getTaskInfo().taskDescription;
                    this.cordova.getActivity().setTaskDescription(new ActivityManager.TaskDescription(taskDescription.getLabel(), taskDescription.getIcon(), parseColor));
                }
            }
        }
    }
}
