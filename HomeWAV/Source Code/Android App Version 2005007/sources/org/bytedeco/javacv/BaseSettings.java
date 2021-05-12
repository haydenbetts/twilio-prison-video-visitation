package org.bytedeco.javacv;

import java.beans.PropertyChangeListener;
import java.beans.beancontext.BeanContextSupport;
import java.util.Arrays;

public class BaseSettings extends BeanContextSupport implements Comparable<BaseSettings> {
    /* access modifiers changed from: protected */
    public String getName() {
        return "";
    }

    public void addPropertyChangeListener(PropertyChangeListener propertyChangeListener) {
        this.pcSupport.addPropertyChangeListener(propertyChangeListener);
        for (Object obj : toArray()) {
            if (obj instanceof BaseChildSettings) {
                ((BaseChildSettings) obj).addPropertyChangeListener(propertyChangeListener);
            } else if (obj instanceof BaseSettings) {
                ((BaseSettings) obj).addPropertyChangeListener(propertyChangeListener);
            }
        }
    }

    public void removePropertyChangeListener(PropertyChangeListener propertyChangeListener) {
        this.pcSupport.removePropertyChangeListener(propertyChangeListener);
        for (Object obj : toArray()) {
            if (obj instanceof BaseChildSettings) {
                ((BaseChildSettings) obj).removePropertyChangeListener(propertyChangeListener);
            } else if (obj instanceof BaseSettings) {
                ((BaseSettings) obj).addPropertyChangeListener(propertyChangeListener);
            }
        }
    }

    public int compareTo(BaseSettings baseSettings) {
        return getName().compareTo(baseSettings.getName());
    }

    public Object[] toArray() {
        Object[] array = BaseSettings.super.toArray();
        Arrays.sort(array);
        return array;
    }

    public Object[] toArray(Object[] objArr) {
        Object[] array = BaseSettings.super.toArray(objArr);
        Arrays.sort(array);
        return array;
    }
}
