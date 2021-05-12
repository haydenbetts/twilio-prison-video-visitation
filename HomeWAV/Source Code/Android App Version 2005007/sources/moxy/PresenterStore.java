package moxy;

import android.util.Log;
import java.util.HashMap;
import java.util.Map;

public class PresenterStore {
    private Map<String, MvpPresenter> presenters = new HashMap();

    public <T extends MvpPresenter> void add(String str, T t) {
        this.presenters.put(str, t);
    }

    public MvpPresenter get(String str) {
        return this.presenters.get(str);
    }

    public MvpPresenter remove(String str) {
        return this.presenters.remove(str);
    }

    public void logPresenters() {
        for (Map.Entry next : this.presenters.entrySet()) {
            Log.d("PresenterStore", ((String) next.getKey()) + " -> " + next.getValue());
        }
    }
}
