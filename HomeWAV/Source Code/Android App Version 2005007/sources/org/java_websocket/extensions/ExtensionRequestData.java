package org.java_websocket.extensions;

import java.util.LinkedHashMap;
import java.util.Map;

public class ExtensionRequestData {
    public static String EMPTY_VALUE = "";
    private String extensionName;
    private Map<String, String> extensionParameters = new LinkedHashMap();

    private ExtensionRequestData() {
    }

    public static ExtensionRequestData parseExtensionRequest(String str) {
        ExtensionRequestData extensionRequestData = new ExtensionRequestData();
        String[] split = str.split(";");
        extensionRequestData.extensionName = split[0].trim();
        for (int i = 1; i < split.length; i++) {
            String[] split2 = split[i].split("=");
            String str2 = EMPTY_VALUE;
            if (split2.length > 1) {
                str2 = split2[1].trim();
                if ((str2.startsWith("\"") && str2.endsWith("\"")) || (str2.startsWith("'") && str2.endsWith("'") && str2.length() > 2)) {
                    str2 = str2.substring(1, str2.length() - 1);
                }
            }
            extensionRequestData.extensionParameters.put(split2[0].trim(), str2);
        }
        return extensionRequestData;
    }

    public String getExtensionName() {
        return this.extensionName;
    }

    public Map<String, String> getExtensionParameters() {
        return this.extensionParameters;
    }
}
