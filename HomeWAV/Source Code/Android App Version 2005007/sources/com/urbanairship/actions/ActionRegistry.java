package com.urbanairship.actions;

import android.content.Context;
import android.util.SparseArray;
import com.urbanairship.R;
import com.urbanairship.util.UAStringUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ActionRegistry {
    private final Map<String, Entry> actionMap = new HashMap();

    public interface Predicate {
        boolean apply(ActionArguments actionArguments);
    }

    public Entry registerAction(Action action, String... strArr) {
        if (strArr.length != 0) {
            return registerEntry(new Entry(action, (List<String>) new ArrayList(Arrays.asList(strArr))));
        }
        throw new IllegalArgumentException("Unable to register an action without a name.");
    }

    public Entry registerAction(Class<? extends Action> cls, String... strArr) {
        if (strArr.length != 0) {
            return registerEntry(new Entry((Class) cls, (List<String>) new ArrayList(Arrays.asList(strArr))));
        }
        throw new IllegalArgumentException("Unable to register an action without a name.");
    }

    private Entry registerEntry(Entry entry) {
        List<String> names = entry.getNames();
        for (String isEmpty : names) {
            if (UAStringUtil.isEmpty(isEmpty)) {
                throw new IllegalArgumentException("Unable to register action because one or more of the names was null or empty.");
            }
        }
        synchronized (this.actionMap) {
            for (String next : names) {
                if (!UAStringUtil.isEmpty(next)) {
                    Entry remove = this.actionMap.remove(next);
                    if (remove != null) {
                        remove.removeName(next);
                    }
                    this.actionMap.put(next, entry);
                }
            }
        }
        return entry;
    }

    public Entry getEntry(String str) {
        Entry entry;
        if (UAStringUtil.isEmpty(str)) {
            return null;
        }
        synchronized (this.actionMap) {
            entry = this.actionMap.get(str);
        }
        return entry;
    }

    public Set<Entry> getEntries() {
        HashSet hashSet;
        synchronized (this.actionMap) {
            hashSet = new HashSet(this.actionMap.values());
        }
        return hashSet;
    }

    public void unregisterAction(String str) {
        if (!UAStringUtil.isEmpty(str)) {
            synchronized (this.actionMap) {
                Entry entry = getEntry(str);
                if (entry != null) {
                    for (String remove : entry.getNames()) {
                        this.actionMap.remove(remove);
                    }
                }
            }
        }
    }

    public void registerDefaultActions(Context context) {
        registerActions(context, R.xml.ua_default_actions);
    }

    public void registerActions(Context context, int i) {
        for (Entry registerEntry : ActionEntryParser.fromXml(context, i)) {
            registerEntry(registerEntry);
        }
    }

    public static final class Entry {
        private Action defaultAction;
        private Class defaultActionClass;
        private final List<String> names;
        private Predicate predicate;
        private final SparseArray<Action> situationOverrides = new SparseArray<>();

        Entry(Action action, List<String> list) {
            this.defaultAction = action;
            this.names = list;
        }

        Entry(Class cls, List<String> list) {
            this.defaultActionClass = cls;
            this.names = list;
        }

        public Action getActionForSituation(int i) {
            Action action = this.situationOverrides.get(i);
            if (action != null) {
                return action;
            }
            return getDefaultAction();
        }

        public Predicate getPredicate() {
            return this.predicate;
        }

        public void setPredicate(Predicate predicate2) {
            this.predicate = predicate2;
        }

        public Action getDefaultAction() {
            Action action = this.defaultAction;
            if (action != null) {
                return action;
            }
            try {
                Action action2 = (Action) this.defaultActionClass.newInstance();
                this.defaultAction = action2;
                return action2;
            } catch (Exception unused) {
                throw new IllegalArgumentException("Unable to instantiate action class.");
            }
        }

        public void setDefaultAction(Action action) {
            this.defaultAction = action;
        }

        public void setSituationOverride(int i, Action action) {
            if (action == null) {
                this.situationOverrides.remove(i);
            } else {
                this.situationOverrides.put(i, action);
            }
        }

        public List<String> getNames() {
            ArrayList arrayList;
            synchronized (this.names) {
                arrayList = new ArrayList(this.names);
            }
            return arrayList;
        }

        /* access modifiers changed from: private */
        public void removeName(String str) {
            synchronized (this.names) {
                this.names.remove(str);
            }
        }

        private void addName(String str) {
            synchronized (this.names) {
                this.names.add(str);
            }
        }

        public String toString() {
            return "Action Entry: " + this.names;
        }
    }
}
