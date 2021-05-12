package org.apache.cordova;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Pattern;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class ConfigXmlParser {
    private static String TAG = "ConfigXmlParser";
    boolean insideFeature = false;
    private String launchUrl = "file:///android_asset/www/index.html";
    boolean onload = false;
    String paramType = "";
    String pluginClass = "";
    private ArrayList<PluginEntry> pluginEntries = new ArrayList<>(20);
    private CordovaPreferences prefs = new CordovaPreferences();
    String service = "";

    public CordovaPreferences getPreferences() {
        return this.prefs;
    }

    public ArrayList<PluginEntry> getPluginEntries() {
        return this.pluginEntries;
    }

    public String getLaunchUrl() {
        return this.launchUrl;
    }

    public void parse(Context context) {
        int identifier = context.getResources().getIdentifier("config", "xml", context.getClass().getPackage().getName());
        if (identifier == 0 && (identifier = context.getResources().getIdentifier("config", "xml", context.getPackageName())) == 0) {
            LOG.e(TAG, "res/xml/config.xml is missing!");
        } else {
            parse((XmlPullParser) context.getResources().getXml(identifier));
        }
    }

    public void parse(XmlPullParser xmlPullParser) {
        int i = -1;
        while (i != 1) {
            if (i == 2) {
                handleStartTag(xmlPullParser);
            } else if (i == 3) {
                handleEndTag(xmlPullParser);
            }
            try {
                i = xmlPullParser.next();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    public void handleStartTag(XmlPullParser xmlPullParser) {
        String attributeValue;
        String name = xmlPullParser.getName();
        if (name.equals("feature")) {
            this.insideFeature = true;
            this.service = xmlPullParser.getAttributeValue((String) null, "name");
        } else if (this.insideFeature && name.equals("param")) {
            this.paramType = xmlPullParser.getAttributeValue((String) null, "name");
            if (this.paramType.equals(NotificationCompat.CATEGORY_SERVICE)) {
                this.service = xmlPullParser.getAttributeValue((String) null, "value");
            } else if (this.paramType.equals("package") || this.paramType.equals("android-package")) {
                this.pluginClass = xmlPullParser.getAttributeValue((String) null, "value");
            } else if (this.paramType.equals("onload")) {
                this.onload = "true".equals(xmlPullParser.getAttributeValue((String) null, "value"));
            }
        } else if (name.equals("preference")) {
            this.prefs.set(xmlPullParser.getAttributeValue((String) null, "name").toLowerCase(Locale.ENGLISH), xmlPullParser.getAttributeValue((String) null, "value"));
        } else if (name.equals("content") && (attributeValue = xmlPullParser.getAttributeValue((String) null, "src")) != null) {
            setStartUrl(attributeValue);
        }
    }

    public void handleEndTag(XmlPullParser xmlPullParser) {
        if (xmlPullParser.getName().equals("feature")) {
            this.pluginEntries.add(new PluginEntry(this.service, this.pluginClass, this.onload));
            this.service = "";
            this.pluginClass = "";
            this.insideFeature = false;
            this.onload = false;
        }
    }

    private void setStartUrl(String str) {
        if (Pattern.compile("^[a-z-]+://").matcher(str).find()) {
            this.launchUrl = str;
            return;
        }
        if (str.charAt(0) == '/') {
            str = str.substring(1);
        }
        this.launchUrl = "file:///android_asset/www/" + str;
    }
}
