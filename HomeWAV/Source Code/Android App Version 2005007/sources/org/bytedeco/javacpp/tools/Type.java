package org.bytedeco.javacpp.tools;

class Type {
    String annotations = "";
    boolean anonymous = false;
    Type[] arguments = null;
    Attribute[] attributes = null;
    boolean constExpr = false;
    boolean constPointer = false;
    boolean constValue = false;
    boolean constructor = false;
    String cppName = "";
    boolean destructor = false;
    boolean friend = false;
    int indirections = 0;
    String javaName = "";
    String[] javaNames = null;
    boolean operator = false;
    boolean reference = false;
    boolean simple = false;
    boolean staticMember = false;
    boolean typedef = false;
    boolean using = false;
    boolean value = false;
    boolean virtual = false;

    Type() {
    }

    Type(String str) {
        this.javaName = str;
        this.cppName = str;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        Type type = (Type) obj;
        if (!this.cppName.equals(type.cppName) || !this.javaName.equals(type.javaName)) {
            return false;
        }
        return true;
    }
}
