package com.urbanairship.app;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ForwardingApplicationListener implements ApplicationListener {
    private final List<ApplicationListener> listeners = new ArrayList();

    public void addListener(ApplicationListener applicationListener) {
        synchronized (this.listeners) {
            this.listeners.add(applicationListener);
        }
    }

    public void removeListener(ApplicationListener applicationListener) {
        synchronized (this.listeners) {
            this.listeners.remove(applicationListener);
        }
    }

    public void onForeground(long j) {
        Iterator it = new ArrayList(this.listeners).iterator();
        while (it.hasNext()) {
            ((ApplicationListener) it.next()).onForeground(j);
        }
    }

    public void onBackground(long j) {
        Iterator it = new ArrayList(this.listeners).iterator();
        while (it.hasNext()) {
            ((ApplicationListener) it.next()).onBackground(j);
        }
    }
}
