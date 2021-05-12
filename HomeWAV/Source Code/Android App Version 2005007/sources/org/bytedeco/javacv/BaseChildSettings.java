package org.bytedeco.javacv;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.beancontext.BeanContextChildSupport;
import java.util.ListResourceBundle;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class BaseChildSettings extends BeanContextChildSupport implements Comparable<BaseChildSettings> {
    /* access modifiers changed from: protected */
    public String getName() {
        return "";
    }

    public void addPropertyChangeListener(PropertyChangeListener propertyChangeListener) {
        this.pcSupport.addPropertyChangeListener(propertyChangeListener);
    }

    public void removePropertyChangeListener(PropertyChangeListener propertyChangeListener) {
        this.pcSupport.removePropertyChangeListener(propertyChangeListener);
    }

    public int compareTo(BaseChildSettings baseChildSettings) {
        return getName().compareTo(baseChildSettings.getName());
    }

    public static class PropertyVetoExceptionThatNetBeansLikes extends PropertyVetoException implements Callable {
        public PropertyVetoExceptionThatNetBeansLikes(String str, PropertyChangeEvent propertyChangeEvent) {
            super(str, propertyChangeEvent);
        }

        public Object call() throws Exception {
            LogRecord logRecord = new LogRecord(Level.ALL, getMessage());
            logRecord.setResourceBundle(new ListResourceBundle() {
                /* access modifiers changed from: protected */
                public Object[][] getContents() {
                    return new Object[][]{new Object[]{PropertyVetoExceptionThatNetBeansLikes.this.getMessage(), PropertyVetoExceptionThatNetBeansLikes.this.getMessage()}};
                }
            });
            return new LogRecord[]{logRecord};
        }
    }
}
