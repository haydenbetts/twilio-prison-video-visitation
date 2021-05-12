package com.urbanairship.actions;

public class ActionValueException extends Exception {
    public ActionValueException(String str, Exception exc) {
        super(str, exc);
    }
}
