package org.bytedeco.javacv;

import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import org.bytedeco.javacv.CameraDevice;

public class CameraSettings extends BaseSettings {
    boolean calibrated;
    Class<? extends FrameGrabber> frameGrabber;
    double monitorWindowsScale;

    public CameraSettings() {
        this(false);
    }

    public CameraSettings(boolean z) {
        this.calibrated = false;
        this.monitorWindowsScale = 1.0d;
        this.frameGrabber = null;
        this.calibrated = z;
    }

    public int getQuantity() {
        return size();
    }

    public void setQuantity(int i) throws PropertyVetoException {
        int max = Math.max(1, i);
        CameraDevice.Settings[] array = toArray();
        int length = array.length;
        while (length > max) {
            remove(array[length - 1]);
            length--;
        }
        while (length < max) {
            CameraDevice.Settings calibratedSettings = this.calibrated ? new CameraDevice.CalibratedSettings() : new CameraDevice.CalibrationSettings();
            calibratedSettings.setName("Camera " + String.format("%2d", new Object[]{Integer.valueOf(length)}));
            calibratedSettings.setDeviceNumber(Integer.valueOf(length));
            calibratedSettings.setFrameGrabber(this.frameGrabber);
            add(calibratedSettings);
            length++;
        }
        this.pcSupport.firePropertyChange("quantity", array.length, max);
    }

    public double getMonitorWindowsScale() {
        return this.monitorWindowsScale;
    }

    public void setMonitorWindowsScale(double d) {
        this.monitorWindowsScale = d;
    }

    public Class<? extends FrameGrabber> getFrameGrabber() {
        return this.frameGrabber;
    }

    public void setFrameGrabber(Class<? extends FrameGrabber> cls) {
        PropertyChangeSupport propertyChangeSupport = this.pcSupport;
        Class<? extends FrameGrabber> cls2 = this.frameGrabber;
        this.frameGrabber = cls;
        propertyChangeSupport.firePropertyChange("frameGrabber", cls2, cls);
    }

    public CameraDevice.Settings[] toArray() {
        return (CameraDevice.Settings[]) toArray(new CameraDevice.Settings[size()]);
    }
}
