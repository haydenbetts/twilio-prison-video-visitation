package fm.liveswitch.sdp;

import fm.liveswitch.ArrayExtensions;
import fm.liveswitch.ArrayListExtensions;
import fm.liveswitch.HashMapExtensions;
import fm.liveswitch.Holder;
import java.util.ArrayList;
import java.util.HashMap;

class AttributeCollection {
    private HashMap<String, ArrayList<Attribute>> __attributes = new HashMap<>();

    public void addAttribute(Attribute attribute) {
        String attributeType = attribute.getAttributeType().toString();
        Holder holder = new Holder(null);
        boolean tryGetValue = HashMapExtensions.tryGetValue(this.__attributes, attributeType, holder);
        ArrayList arrayList = (ArrayList) holder.getValue();
        if (!tryGetValue) {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(attribute);
            HashMapExtensions.add(this.__attributes, attributeType, arrayList2);
            return;
        }
        arrayList.add(attribute);
    }

    public boolean remove(Attribute attribute) {
        Holder holder = new Holder(null);
        boolean tryGetValue = HashMapExtensions.tryGetValue(this.__attributes, attribute.getAttributeType().toString(), holder);
        ArrayList arrayList = (ArrayList) holder.getValue();
        boolean z = false;
        if (tryGetValue) {
            if (arrayList == null) {
                return false;
            }
            z = arrayList.remove(attribute);
            if (ArrayListExtensions.getCount(arrayList) == 0) {
                HashMapExtensions.remove(this.__attributes, attribute.getAttributeType().toString());
            }
        }
        return z;
    }

    public boolean remove(AttributeType attributeType) {
        return HashMapExtensions.remove(this.__attributes, attributeType.toString());
    }

    public void replaceAttribute(Attribute attribute) {
        AttributeType attributeType = attribute.getAttributeType();
        HashMapExtensions.remove(this.__attributes, attributeType.toString());
        ArrayList arrayList = new ArrayList();
        arrayList.add(attribute);
        HashMapExtensions.add(this.__attributes, attributeType.toString(), arrayList);
    }

    public Attribute[] toArray() {
        ArrayList arrayList = new ArrayList();
        for (ArrayList<Attribute> addRange : HashMapExtensions.getValues(this.__attributes)) {
            ArrayListExtensions.addRange(arrayList, addRange);
        }
        return (Attribute[]) arrayList.toArray(new Attribute[0]);
    }

    public boolean tryGetAttribute(AttributeType attributeType, Holder<Attribute> holder) {
        Holder holder2 = new Holder(null);
        boolean tryGetAttributes = tryGetAttributes(attributeType, holder2);
        Attribute[] attributeArr = (Attribute[]) holder2.getValue();
        if (!tryGetAttributes) {
            holder.setValue(null);
            return false;
        } else if (ArrayExtensions.getLength((Object[]) attributeArr) > 0) {
            holder.setValue(attributeArr[0]);
            return true;
        } else {
            holder.setValue(null);
            return false;
        }
    }

    public boolean tryGetAttributes(AttributeType attributeType, Holder<Attribute[]> holder) {
        Holder holder2 = new Holder(null);
        boolean tryGetValue = HashMapExtensions.tryGetValue(this.__attributes, attributeType.toString(), holder2);
        ArrayList arrayList = (ArrayList) holder2.getValue();
        if (tryGetValue) {
            int count = ArrayListExtensions.getCount(arrayList);
            holder.setValue(new Attribute[count]);
            for (int i = 0; i < count; i++) {
                holder.getValue()[i] = (Attribute) ArrayListExtensions.getItem(arrayList).get(i);
            }
            return true;
        }
        holder.setValue(new Attribute[0]);
        return false;
    }
}
