package com.urbanairship.job;

class SchedulerException extends Exception {
    SchedulerException(String str, Exception exc) {
        super(str, exc);
    }

    SchedulerException(String str) {
        super(str);
    }
}
