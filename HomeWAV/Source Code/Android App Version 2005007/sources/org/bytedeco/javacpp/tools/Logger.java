package org.bytedeco.javacpp.tools;

import java.io.PrintStream;

public class Logger {
    static boolean debug = false;

    public boolean isErrorEnabled() {
        return true;
    }

    public boolean isInfoEnabled() {
        return true;
    }

    public boolean isWarnEnabled() {
        return true;
    }

    public static Logger create(Class cls) {
        String lowerCase = System.getProperty("org.bytedeco.javacpp.logger", "").toLowerCase();
        if (lowerCase.equals("slf4j") || lowerCase.equals("slf4jlogger")) {
            return new Slf4jLogger(cls);
        }
        return new Logger();
    }

    static {
        String lowerCase = System.getProperty("org.bytedeco.javacpp.logger.debug", "false").toLowerCase();
        debug = lowerCase.equals("true") || lowerCase.equals("t") || lowerCase.equals("");
    }

    public boolean isDebugEnabled() {
        return debug;
    }

    public void debug(String str) {
        PrintStream printStream = System.err;
        printStream.println("Debug: " + str);
    }

    public void info(String str) {
        PrintStream printStream = System.err;
        printStream.println("Info: " + str);
    }

    public void warn(String str) {
        PrintStream printStream = System.err;
        printStream.println("Warning: " + str);
    }

    public void error(String str) {
        PrintStream printStream = System.err;
        printStream.println("Error: " + str);
    }
}
