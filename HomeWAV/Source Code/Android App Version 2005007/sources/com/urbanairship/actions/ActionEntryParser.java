package com.urbanairship.actions;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import com.urbanairship.Logger;
import com.urbanairship.actions.ActionRegistry;
import com.urbanairship.util.UAStringUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;

class ActionEntryParser {
    private static final String ACTION_ENTRY_TAG = "ActionEntry";
    private static final String CLASS_ATTRIBUTE = "class";
    private static final String NAME_TAG = "name";
    private static final String PREDICATE_ATTRIBUTE = "predicate";

    ActionEntryParser() {
    }

    public static List<ActionRegistry.Entry> fromXml(Context context, int i) {
        XmlResourceParser xml = context.getResources().getXml(i);
        try {
            return parseEntries(xml);
        } catch (Resources.NotFoundException | IOException | NullPointerException | XmlPullParserException e) {
            Logger.error(e, "Failed to parse action entries.", new Object[0]);
            return new ArrayList();
        } finally {
            xml.close();
        }
    }

    private static List<ActionRegistry.Entry> parseEntries(XmlResourceParser xmlResourceParser) throws IOException, XmlPullParserException {
        ActionRegistry.Entry parseEntry;
        ArrayList arrayList = new ArrayList();
        while (xmlResourceParser.next() != 1) {
            int eventType = xmlResourceParser.getEventType();
            String name = xmlResourceParser.getName();
            if (eventType == 2 && ACTION_ENTRY_TAG.equals(name) && (parseEntry = parseEntry(xmlResourceParser)) != null) {
                arrayList.add(parseEntry);
            }
        }
        return arrayList;
    }

    private static ActionRegistry.Entry parseEntry(XmlResourceParser xmlResourceParser) throws IOException, XmlPullParserException {
        String parseName;
        String attributeValue = xmlResourceParser.getAttributeValue((String) null, CLASS_ATTRIBUTE);
        String attributeValue2 = xmlResourceParser.getAttributeValue((String) null, PREDICATE_ATTRIBUTE);
        ArrayList arrayList = new ArrayList();
        while (xmlResourceParser.next() != 1) {
            int eventType = xmlResourceParser.getEventType();
            String name = xmlResourceParser.getName();
            if (eventType == 2 && "name".equals(name) && (parseName = parseName(xmlResourceParser)) != null) {
                arrayList.add(parseName);
            }
            if (eventType == 3 && ACTION_ENTRY_TAG.equals(name)) {
                break;
            }
        }
        if (arrayList.isEmpty()) {
            Logger.error("Action names not found.", new Object[0]);
            return null;
        }
        try {
            ActionRegistry.Entry entry = new ActionRegistry.Entry((Class) Class.forName(attributeValue).asSubclass(Action.class), (List<String>) arrayList);
            if (!UAStringUtil.isEmpty(attributeValue2)) {
                try {
                    entry.setPredicate((ActionRegistry.Predicate) Class.forName(attributeValue2).asSubclass(ActionRegistry.Predicate.class).newInstance());
                } catch (Exception unused) {
                    Logger.error("Predicate class %s not found. Skipping predicate.", attributeValue2);
                }
            }
            return entry;
        } catch (ClassNotFoundException unused2) {
            Logger.error("Action class %s not found.", attributeValue);
            return null;
        }
    }

    private static String parseName(XmlResourceParser xmlResourceParser) throws IOException, XmlPullParserException {
        while (xmlResourceParser.next() != 1) {
            int eventType = xmlResourceParser.getEventType();
            String name = xmlResourceParser.getName();
            if (eventType == 4) {
                return xmlResourceParser.getText();
            }
            if (eventType == 3 && "name".equals(name)) {
                return null;
            }
        }
        return null;
    }
}
