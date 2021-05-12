package org.appspot.apprtc;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.util.Log;
import org.appspot.apprtc.util.AppRTCUtils;
import tvi.webrtc.ThreadUtils;

public class AppRTCProximitySensor implements SensorEventListener {
    private static final String TAG = "AppRTCProximitySensor";
    private boolean lastStateReportIsNear;
    private final Runnable onSensorStateListener;
    private Sensor proximitySensor;
    private final SensorManager sensorManager;
    private final ThreadUtils.ThreadChecker threadChecker = new ThreadUtils.ThreadChecker();

    static AppRTCProximitySensor create(Context context, Runnable runnable) {
        return new AppRTCProximitySensor(context, runnable);
    }

    private AppRTCProximitySensor(Context context, Runnable runnable) {
        Log.d(TAG, TAG + AppRTCUtils.getThreadInfo());
        this.onSensorStateListener = runnable;
        this.sensorManager = (SensorManager) context.getSystemService("sensor");
    }

    public boolean start() {
        this.threadChecker.checkIsOnValidThread();
        Log.d(TAG, TtmlNode.START + AppRTCUtils.getThreadInfo());
        if (!initDefaultSensor()) {
            return false;
        }
        this.sensorManager.registerListener(this, this.proximitySensor, 3);
        return true;
    }

    public void stop() {
        this.threadChecker.checkIsOnValidThread();
        Log.d(TAG, "stop" + AppRTCUtils.getThreadInfo());
        Sensor sensor = this.proximitySensor;
        if (sensor != null) {
            this.sensorManager.unregisterListener(this, sensor);
        }
    }

    public boolean sensorReportsNearState() {
        this.threadChecker.checkIsOnValidThread();
        return this.lastStateReportIsNear;
    }

    public final void onAccuracyChanged(Sensor sensor, int i) {
        this.threadChecker.checkIsOnValidThread();
        AppRTCUtils.assertIsTrue(sensor.getType() == 8);
        if (i == 0) {
            Log.e(TAG, "The values returned by this sensor cannot be trusted");
        }
    }

    public final void onSensorChanged(SensorEvent sensorEvent) {
        this.threadChecker.checkIsOnValidThread();
        AppRTCUtils.assertIsTrue(sensorEvent.sensor.getType() == 8);
        if (sensorEvent.values[0] < this.proximitySensor.getMaximumRange()) {
            Log.d(TAG, "Proximity sensor => NEAR state");
            this.lastStateReportIsNear = true;
        } else {
            Log.d(TAG, "Proximity sensor => FAR state");
            this.lastStateReportIsNear = false;
        }
        Runnable runnable = this.onSensorStateListener;
        if (runnable != null) {
            runnable.run();
        }
        Log.d(TAG, "onSensorChanged" + AppRTCUtils.getThreadInfo() + ": accuracy=" + sensorEvent.accuracy + ", timestamp=" + sensorEvent.timestamp + ", distance=" + sensorEvent.values[0]);
    }

    private boolean initDefaultSensor() {
        if (this.proximitySensor != null) {
            return true;
        }
        Sensor defaultSensor = this.sensorManager.getDefaultSensor(8);
        this.proximitySensor = defaultSensor;
        if (defaultSensor == null) {
            return false;
        }
        logProximitySensorInfo();
        return true;
    }

    private void logProximitySensorInfo() {
        if (this.proximitySensor != null) {
            StringBuilder sb = new StringBuilder("Proximity sensor: ");
            sb.append("name=");
            sb.append(this.proximitySensor.getName());
            sb.append(", vendor: ");
            sb.append(this.proximitySensor.getVendor());
            sb.append(", power: ");
            sb.append(this.proximitySensor.getPower());
            sb.append(", resolution: ");
            sb.append(this.proximitySensor.getResolution());
            sb.append(", max range: ");
            sb.append(this.proximitySensor.getMaximumRange());
            sb.append(", min delay: ");
            sb.append(this.proximitySensor.getMinDelay());
            if (Build.VERSION.SDK_INT >= 20) {
                sb.append(", type: ");
                sb.append(this.proximitySensor.getStringType());
            }
            if (Build.VERSION.SDK_INT >= 21) {
                sb.append(", max delay: ");
                sb.append(this.proximitySensor.getMaxDelay());
                sb.append(", reporting mode: ");
                sb.append(this.proximitySensor.getReportingMode());
                sb.append(", isWakeUpSensor: ");
                sb.append(this.proximitySensor.isWakeUpSensor());
            }
            Log.d(TAG, sb.toString());
        }
    }
}
