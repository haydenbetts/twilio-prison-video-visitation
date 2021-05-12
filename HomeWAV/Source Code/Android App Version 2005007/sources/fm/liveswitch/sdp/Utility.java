package fm.liveswitch.sdp;

import fm.liveswitch.ArrayListExtensions;
import fm.liveswitch.StringExtensions;
import java.util.ArrayList;

class Utility {
    public static String[] splitAndClean(String str) {
        ArrayList createArray = ArrayListExtensions.createArray((T[]) StringExtensions.split(str, new char[]{10}));
        int i = 0;
        while (i < ArrayListExtensions.getCount(createArray)) {
            ArrayListExtensions.getItem(createArray).set(i, ((String) ArrayListExtensions.getItem(createArray).get(i)).trim());
            if (StringExtensions.isNullOrEmpty((String) ArrayListExtensions.getItem(createArray).get(i))) {
                ArrayListExtensions.removeAt(createArray, i);
                i--;
            }
            i++;
        }
        return (String[]) createArray.toArray(new String[0]);
    }
}
