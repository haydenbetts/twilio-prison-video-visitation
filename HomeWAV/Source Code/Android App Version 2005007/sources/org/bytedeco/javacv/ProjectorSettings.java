package org.bytedeco.javacv;

import java.beans.PropertyChangeListener;
import org.bytedeco.javacv.ProjectorDevice;

public class ProjectorSettings extends BaseSettings {
    boolean calibrated;

    public ProjectorSettings() {
        this(false);
    }

    public ProjectorSettings(boolean z) {
        this.calibrated = false;
        this.calibrated = z;
    }

    public int getQuantity() {
        return size();
    }

    public void setQuantity(int i) {
        ProjectorDevice.Settings[] array = toArray();
        int length = array.length;
        while (length > i) {
            remove(array[length - 1]);
            length--;
        }
        while (length < i) {
            ProjectorDevice.Settings calibratedSettings = this.calibrated ? new ProjectorDevice.CalibratedSettings() : new ProjectorDevice.CalibrationSettings();
            StringBuilder sb = new StringBuilder();
            sb.append("Projector ");
            sb.append(String.format("%2d", new Object[]{Integer.valueOf(length)}));
            calibratedSettings.setName(sb.toString());
            calibratedSettings.setScreenNumber(calibratedSettings.getScreenNumber() + length);
            add(calibratedSettings);
            for (PropertyChangeListener addPropertyChangeListener : this.pcSupport.getPropertyChangeListeners()) {
                ((BaseChildSettings) calibratedSettings).addPropertyChangeListener(addPropertyChangeListener);
            }
            length++;
        }
        this.pcSupport.firePropertyChange("quantity", array.length, i);
    }

    public ProjectorDevice.Settings[] toArray() {
        return (ProjectorDevice.Settings[]) toArray(new ProjectorDevice.Settings[size()]);
    }
}
