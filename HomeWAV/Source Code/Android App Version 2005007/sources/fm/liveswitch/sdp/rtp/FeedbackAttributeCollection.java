package fm.liveswitch.sdp.rtp;

import fm.liveswitch.HashMapExtensions;
import fm.liveswitch.Holder;
import fm.liveswitch.IntegerExtensions;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.sdp.rtcp.FeedbackAttribute;
import java.util.ArrayList;
import java.util.HashMap;

class FeedbackAttributeCollection {
    private HashMap<String, FeedbackAttribute> __attributes = new HashMap<>();

    public boolean addAttribute(FeedbackAttribute feedbackAttribute) {
        String calculateFeedbackAttributeKey = calculateFeedbackAttributeKey(feedbackAttribute.getPayloadType(), feedbackAttribute.getType(), feedbackAttribute.getSubType());
        if (this.__attributes.containsKey(calculateFeedbackAttributeKey)) {
            return false;
        }
        HashMapExtensions.add(this.__attributes, calculateFeedbackAttributeKey, feedbackAttribute);
        return true;
    }

    private String calculateFeedbackAttributeKey(int i, String str, String str2) {
        if (StringExtensions.isNullOrEmpty(str)) {
            str = "null";
        }
        if (StringExtensions.isNullOrEmpty(str2)) {
            str2 = "null";
        }
        return StringExtensions.concat((Object[]) new String[]{str, "&", str2, "&", IntegerExtensions.toString(Integer.valueOf(i))});
    }

    public void clear() {
        this.__attributes.clear();
    }

    public boolean remove(FeedbackAttribute feedbackAttribute) {
        return HashMapExtensions.remove(this.__attributes, calculateFeedbackAttributeKey(feedbackAttribute.getPayloadType(), feedbackAttribute.getType(), feedbackAttribute.getSubType()));
    }

    public FeedbackAttribute[] toArray() {
        ArrayList arrayList = new ArrayList();
        for (FeedbackAttribute add : HashMapExtensions.getValues(this.__attributes)) {
            arrayList.add(add);
        }
        return (FeedbackAttribute[]) arrayList.toArray(new FeedbackAttribute[0]);
    }

    public boolean tryGetFeedbackAttribute(int i, String str, String str2, Holder<FeedbackAttribute> holder) {
        String calculateFeedbackAttributeKey = calculateFeedbackAttributeKey(i, str, str2);
        Holder holder2 = new Holder(null);
        boolean tryGetValue = HashMapExtensions.tryGetValue(this.__attributes, calculateFeedbackAttributeKey, holder2);
        FeedbackAttribute feedbackAttribute = (FeedbackAttribute) holder2.getValue();
        if (tryGetValue) {
            holder.setValue(feedbackAttribute);
            return true;
        }
        holder.setValue(null);
        return false;
    }
}
