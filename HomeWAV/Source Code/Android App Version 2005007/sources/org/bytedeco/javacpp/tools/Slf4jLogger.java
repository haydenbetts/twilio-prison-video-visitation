package org.bytedeco.javacpp.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jLogger extends Logger {
    final Logger logger;

    public Slf4jLogger(Class cls) {
        this.logger = LoggerFactory.getLogger((Class<?>) cls);
    }

    public boolean isDebugEnabled() {
        return this.logger.isDebugEnabled();
    }

    public boolean isInfoEnabled() {
        return this.logger.isInfoEnabled();
    }

    public boolean isWarnEnabled() {
        return this.logger.isWarnEnabled();
    }

    public boolean isErrorEnabled() {
        return this.logger.isErrorEnabled();
    }

    public void debug(String str) {
        this.logger.debug(str);
    }

    public void info(String str) {
        this.logger.info(str);
    }

    public void warn(String str) {
        this.logger.warn(str);
    }

    public void error(String str) {
        this.logger.error(str);
    }
}
