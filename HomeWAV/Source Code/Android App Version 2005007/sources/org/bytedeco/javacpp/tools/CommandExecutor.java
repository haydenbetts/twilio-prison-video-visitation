package org.bytedeco.javacpp.tools;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.bytedeco.javacpp.Loader;

public class CommandExecutor {
    final Logger logger;

    public CommandExecutor(Logger logger2) {
        this.logger = logger2;
    }

    public int executeCommand(List<String> list, File file, Map<String, String> map) throws IOException, InterruptedException {
        String str;
        String str2;
        boolean startsWith = Loader.getPlatform().startsWith("windows");
        int i = 0;
        while (true) {
            str = "";
            if (i >= list.size()) {
                break;
            }
            String str3 = list.get(i);
            if (str3 != null) {
                str = str3;
            }
            if (str.trim().isEmpty() && startsWith) {
                str = "\"\"";
            }
            list.set(i, str);
            i++;
        }
        for (String next : list) {
            boolean z = next.indexOf(" ") > 0 || next.isEmpty();
            String str4 = "\"";
            if (z) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                if (startsWith) {
                    str2 = str4;
                } else {
                    str2 = "'";
                }
                sb.append(str2);
                str = sb.toString();
            }
            String str5 = str + next;
            if (z) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str5);
                if (!startsWith) {
                    str4 = "'";
                }
                sb2.append(str4);
                str5 = sb2.toString();
            }
            str = str5 + " ";
        }
        this.logger.info(str);
        ProcessBuilder processBuilder = new ProcessBuilder(list);
        if (file != null) {
            processBuilder.directory(file);
        }
        if (map != null) {
            for (Map.Entry next2 : map.entrySet()) {
                if (!(next2.getKey() == null || next2.getValue() == null)) {
                    processBuilder.environment().put(next2.getKey(), next2.getValue());
                }
            }
        }
        return processBuilder.inheritIO().start().waitFor();
    }
}
