package fm.liveswitch;

import java.util.HashMap;

public class EventBatch {
    private EventInfo[] _events;

    /* access modifiers changed from: protected */
    public void deserializeProperty(String str, String str2) {
        if (str != null && Global.equals(str, "batch")) {
            setEvents(EventInfo.fromJsonArray(str2));
        }
    }

    public static EventBatch fromJson(String str) {
        return (EventBatch) JsonSerializer.deserializeObject(str, new IFunction0<EventBatch>() {
            public EventBatch invoke() {
                return new EventBatch();
            }
        }, new IAction3<EventBatch, String, String>() {
            public void invoke(EventBatch eventBatch, String str, String str2) {
                eventBatch.deserializeProperty(str, str2);
            }
        });
    }

    public EventInfo[] getEvents() {
        return this._events;
    }

    /* access modifiers changed from: protected */
    public void serializeProperties(HashMap<String, String> hashMap) {
        if (getEvents() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "batch", EventInfo.toJsonArray(getEvents()));
        }
    }

    public void setEvents(EventInfo[] eventInfoArr) {
        this._events = eventInfoArr;
    }

    public static String toJson(EventBatch eventBatch) {
        return JsonSerializer.serializeObject(eventBatch, new IAction2<EventBatch, HashMap<String, String>>() {
            public void invoke(EventBatch eventBatch, HashMap<String, String> hashMap) {
                eventBatch.serializeProperties(hashMap);
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }
}
