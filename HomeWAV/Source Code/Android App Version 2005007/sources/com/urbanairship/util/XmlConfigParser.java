package com.urbanairship.util;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.Xml;
import java.io.Closeable;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

public class XmlConfigParser extends AttributeSetConfigParser implements Closeable {
    private final XmlResourceParser parser;

    private XmlConfigParser(Context context, AttributeSet attributeSet, XmlResourceParser xmlResourceParser) {
        super(context, attributeSet);
        this.parser = xmlResourceParser;
    }

    public static XmlConfigParser parseElement(Context context, int i, String str) throws IOException, XmlPullParserException {
        AttributeSet attributeSet;
        XmlResourceParser xml = context.getResources().getXml(i);
        while (true) {
            try {
                int next = xml.next();
                if (next != 2 || !xml.getName().equals(str)) {
                    if (next == 1) {
                        attributeSet = null;
                        break;
                    }
                } else {
                    attributeSet = Xml.asAttributeSet(xml);
                    break;
                }
            } catch (IOException | XmlPullParserException e) {
                xml.close();
                throw e;
            }
        }
        if (attributeSet != null) {
            return new XmlConfigParser(context, attributeSet, xml);
        }
        xml.close();
        throw new IOException("Element " + str + " not found");
    }

    public void close() {
        XmlResourceParser xmlResourceParser = this.parser;
        if (xmlResourceParser != null) {
            xmlResourceParser.close();
        }
    }
}
