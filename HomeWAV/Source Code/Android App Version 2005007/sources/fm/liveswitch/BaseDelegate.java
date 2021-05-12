package fm.liveswitch;

import fm.liveswitch.BaseDelegate;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class BaseDelegate<T extends BaseDelegate<T>> {
    ArrayList<T> chain = new ArrayList<>();
    Object lock = new Object();

    public abstract T newInstance();

    public abstract T self();

    /* access modifiers changed from: package-private */
    public T merge(T t) {
        synchronized (this.lock) {
            if (this.chain.size() == 0) {
                T newInstance = newInstance();
                newInstance.chain.add(self());
                newInstance.chain.add(t);
                return newInstance;
            }
            this.chain.add(t);
            T self = self();
            return self;
        }
    }

    /* access modifiers changed from: package-private */
    public T split(T t) {
        synchronized (this.lock) {
            if (this.chain.size() != 0) {
                int i = 0;
                while (true) {
                    if (i >= this.chain.size()) {
                        break;
                    } else if (this.chain.get(i) == t) {
                        this.chain.remove(i);
                        break;
                    } else {
                        i++;
                    }
                }
                if (this.chain.size() == 0) {
                    return null;
                }
                T self = self();
                return self;
            } else if (self() == t) {
                return null;
            } else {
                T self2 = self();
                return self2;
            }
        }
    }

    public ArrayList<T> getDelegates() {
        ArrayList<T> arrayList;
        synchronized (this.lock) {
            arrayList = new ArrayList<>();
            Iterator<T> it = this.chain.iterator();
            while (it.hasNext()) {
                arrayList.add((BaseDelegate) it.next());
            }
        }
        return arrayList;
    }
}
