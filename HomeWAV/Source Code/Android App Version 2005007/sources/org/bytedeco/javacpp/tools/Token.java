package org.bytedeco.javacpp.tools;

import com.microsoft.appcenter.Constants;
import com.urbanairship.iam.DisplayContent;
import com.urbanairship.iam.InAppMessage;
import java.io.File;

class Token implements Comparable<Token> {
    static final Token AUTO = new Token(5, "auto");
    static final Token CLASS = new Token(5, "class");
    static final int COMMENT = 4;
    static final Token CONST = new Token(5, "const");
    static final Token CONSTEXPR = new Token(5, "constexpr");
    static final Token DECLTYPE = new Token(5, "decltype");
    static final Token DEFAULT = new Token(5, InAppMessage.DISPLAY_BEHAVIOR_DEFAULT);
    static final Token DEFINE = new Token(5, "define");
    static final Token DELETE = new Token(5, "delete");
    static final Token ELIF = new Token(5, "elif");
    static final Token ELSE = new Token(5, "else");
    static final Token ENDIF = new Token(5, "endif");
    static final Token ENUM = new Token(5, "enum");
    static final Token EOF = new Token(-1, "EOF");
    static final Token EXPLICIT = new Token(5, "explicit");
    static final Token EXTERN = new Token(5, "extern");
    static final Token FINAL = new Token(5, "final");
    static final int FLOAT = 2;
    static final Token FRIEND = new Token(5, "friend");
    static final int IDENTIFIER = 5;
    static final Token IF = new Token(5, "if");
    static final Token IFDEF = new Token(5, "ifdef");
    static final Token IFNDEF = new Token(5, "ifndef");
    static final Token INLINE = new Token(5, "inline");
    static final int INTEGER = 1;
    static final Token INTERFACE = new Token(5, "interface");
    static final Token MUTABLE = new Token(5, "mutable");
    static final Token NAMESPACE = new Token(5, "namespace");
    static final Token NEW = new Token(5, "new");
    static final Token OPERATOR = new Token(5, "operator");
    static final Token OVERRIDE = new Token(5, "override");
    static final Token PRIVATE = new Token(5, "private");
    static final Token PROTECTED = new Token(5, "protected");
    static final Token PUBLIC = new Token(5, "public");
    static final Token REGISTER = new Token(5, "register");
    static final Token STATIC = new Token(5, "static");
    static final int STRING = 3;
    static final Token STRUCT = new Token(5, "struct");
    static final int SYMBOL = 6;
    static final Token TEMPLATE = new Token(5, DisplayContent.TEMPLATE_KEY);
    static final Token THREAD_LOCAL = new Token(5, "thread_local");
    static final Token TYPEDEF = new Token(5, "typedef");
    static final Token TYPENAME = new Token(5, "typename");
    static final Token UNDEF = new Token(5, "undef");
    static final Token UNION = new Token(5, "union");
    static final Token USING = new Token(5, "using");
    static final Token VIRTUAL = new Token(5, "virtual");
    static final Token VOLATILE = new Token(5, "volatile");
    static final Token __CONST = new Token(5, "__const");
    static final Token __INTERFACE = new Token(5, "__interface");
    File file = null;
    int lineNumber = 0;
    String spacing = "";
    String text = null;
    int type = -1;
    String value = "";

    Token() {
    }

    Token(int i, String str) {
        this.type = i;
        this.value = str;
    }

    Token(Token token) {
        this.file = token.file;
        this.text = token.text;
        this.lineNumber = token.lineNumber;
        this.type = token.type;
        this.spacing = token.spacing;
        this.value = token.value;
    }

    /* access modifiers changed from: package-private */
    public boolean match(Object... objArr) {
        boolean z = false;
        for (Object equals : objArr) {
            z = z || equals(equals);
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public Token expect(Object... objArr) throws ParserException {
        String str;
        if (match(objArr)) {
            return this;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.file);
        sb.append(Constants.COMMON_SCHEMA_PREFIX_SEPARATOR);
        sb.append(this.lineNumber);
        sb.append(Constants.COMMON_SCHEMA_PREFIX_SEPARATOR);
        if (this.text != null) {
            str = "\"" + this.text + "\": ";
        } else {
            str = "";
        }
        sb.append(str);
        sb.append("Unexpected token '");
        sb.append(toString());
        sb.append("'");
        throw new ParserException(sb.toString());
    }

    /* access modifiers changed from: package-private */
    public boolean isEmpty() {
        return this.type == -1 && this.spacing.isEmpty();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj.getClass() == Integer.class) {
            if (this.type == ((Integer) obj).intValue()) {
                return true;
            }
            return false;
        } else if (obj.getClass() == Character.class) {
            if (this.type == ((Character) obj).charValue()) {
                return true;
            }
            return false;
        } else if (obj.getClass() == String.class) {
            return obj.equals(this.value);
        } else {
            if (obj.getClass() != getClass()) {
                return false;
            }
            Token token = (Token) obj;
            if (this.type == token.type) {
                String str = this.value;
                if (str == null && token.value == null) {
                    return true;
                }
                if (str == null || !str.equals(token.value)) {
                    return false;
                }
                return true;
            }
            return false;
        }
    }

    public int hashCode() {
        return this.type;
    }

    public String toString() {
        String str = this.value;
        return (str == null || str.length() <= 0) ? String.valueOf((char) this.type) : this.value;
    }

    public int compareTo(Token token) {
        return toString().compareTo(token.toString());
    }
}
