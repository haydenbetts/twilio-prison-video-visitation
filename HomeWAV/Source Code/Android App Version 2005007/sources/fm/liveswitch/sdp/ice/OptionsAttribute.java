package fm.liveswitch.sdp.ice;

import fm.liveswitch.ArrayListExtensions;
import fm.liveswitch.Global;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.sdp.Attribute;
import fm.liveswitch.sdp.AttributeCategory;
import fm.liveswitch.sdp.AttributeType;
import java.util.ArrayList;
import java.util.Iterator;

public class OptionsAttribute extends Attribute {
    private ArrayList<OptionTag> __tags;

    public static OptionsAttribute fromAttributeValue(String str) {
        String[] split = StringExtensions.split(str, new char[]{' '});
        ArrayList arrayList = new ArrayList();
        for (String parse : split) {
            OptionTag parse2 = OptionTag.parse(parse);
            if (parse2 != null) {
                arrayList.add(parse2);
            }
        }
        OptionsAttribute optionsAttribute = new OptionsAttribute();
        optionsAttribute.setTags(arrayList);
        return optionsAttribute;
    }

    /* access modifiers changed from: protected */
    public String getAttributeValue() {
        if (getTags() == null) {
            return "";
        }
        int count = ArrayListExtensions.getCount(getTags());
        String[] strArr = new String[count];
        for (int i = 0; i < count; i++) {
            strArr[i] = ((OptionTag) ArrayListExtensions.getItem(getTags()).get(i)).toString();
        }
        return StringExtensions.join(" ", strArr);
    }

    public ArrayList<OptionTag> getTags() {
        return this.__tags;
    }

    public boolean getTrickleOptionSet() {
        Iterator<OptionTag> it = getTags().iterator();
        while (it.hasNext()) {
            if (Global.equals(it.next().getType(), OptionTagType.Trickle)) {
                return true;
            }
        }
        return false;
    }

    private OptionsAttribute() {
        this.__tags = new ArrayList<>();
        super.setAttributeType(AttributeType.IceOptionsAttribute);
        super.setMultiplexingCategory(AttributeCategory.Normal);
    }

    public OptionsAttribute(ArrayList<OptionTag> arrayList) {
        this();
        setTags(arrayList == null ? new ArrayList<>() : arrayList);
    }

    public void setTags(ArrayList<OptionTag> arrayList) {
        this.__tags = arrayList;
    }

    public void setTrickleOptionSet(boolean z) {
        if (z) {
            Iterator<OptionTag> it = getTags().iterator();
            while (it.hasNext()) {
                if (Global.equals(it.next().getType(), OptionTagType.Trickle)) {
                    return;
                }
            }
            getTags().add(new TrickleIceOptionTag());
            return;
        }
        OptionTag optionTag = null;
        Iterator<OptionTag> it2 = getTags().iterator();
        while (it2.hasNext()) {
            OptionTag next = it2.next();
            if (Global.equals(next.getType(), OptionTagType.Trickle)) {
                optionTag = next;
            }
        }
        getTags().remove(optionTag);
    }
}
