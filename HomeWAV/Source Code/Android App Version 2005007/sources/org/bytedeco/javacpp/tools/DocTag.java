package org.bytedeco.javacpp.tools;

import java.util.regex.Pattern;

class DocTag {
    static DocTag[] docTags;
    static String[][] docTagsStr;
    Pattern pattern;
    String replacement;

    static {
        String[][] strArr = {new String[]{"\\\\", "\\\\"}, new String[]{"@", "{@literal @}"}, new String[]{"(?s)f\\[(.*?)[@\\\\]f\\]", "<pre>{@code \\\\[$1\\\\]}</pre>"}, new String[]{"(?s)f\\{([^\\}]*)\\}\\s*\\{(.*?)[@\\\\]f\\}", "<pre>{@code \\\\begin{$1}$2\\\\end{$1}}</pre>"}, new String[]{"(?s)f\\$(.*?)[@\\\\]f\\$", "{@code $1}"}, new String[]{"authors?\\b", "@author"}, new String[]{"deprecated\\b", "@deprecated"}, new String[]{"(?:exception|throws?)\\b", "@throws"}, new String[]{"param\\s*(\\[[a-z,\\s]+\\])\\s+([a-zA-Z\\$_]+)", "@param $2 $1"}, new String[]{"param\\b", "@param"}, new String[]{"(?:returns?|result)\\b", "@return"}, new String[]{"(?:see|sa)\\b", "@see"}, new String[]{"since\\b", "@since"}, new String[]{"version\\b", "@version"}};
        docTagsStr = strArr;
        docTags = new DocTag[strArr.length];
        for (int i = 0; i < docTagsStr.length; i++) {
            DocTag[] docTagArr = docTags;
            String[][] strArr2 = docTagsStr;
            docTagArr[i] = new DocTag(strArr2[i][0], strArr2[i][1]);
        }
    }

    DocTag(String str, String str2) {
        this.pattern = Pattern.compile(str);
        this.replacement = str2;
    }
}
