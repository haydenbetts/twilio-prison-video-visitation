package org.bytedeco.javacpp.tools;

public class Info {
    String[] annotations = null;
    String base = null;
    boolean cast = false;
    String[] cppNames = null;
    String cppText = null;
    String[] cppTypes = null;
    boolean define = false;
    boolean enumerate = false;
    boolean flatten = false;
    String[] javaNames = null;
    String javaText = null;
    String[] linePatterns = null;
    boolean objectify = false;
    String[] pointerTypes = null;
    boolean purify = false;
    boolean skip = false;
    boolean skipDefaults = false;
    boolean translate = false;
    String[] valueTypes = null;
    boolean virtualize = false;

    public Info() {
    }

    public Info(String... strArr) {
        this.cppNames = strArr;
    }

    public Info(Info info) {
        String[] strArr = null;
        String[] strArr2 = info.cppNames;
        this.cppNames = strArr2 != null ? (String[]) strArr2.clone() : null;
        String[] strArr3 = info.javaNames;
        this.javaNames = strArr3 != null ? (String[]) strArr3.clone() : null;
        String[] strArr4 = info.annotations;
        this.annotations = strArr4 != null ? (String[]) strArr4.clone() : null;
        String[] strArr5 = info.cppTypes;
        this.cppTypes = strArr5 != null ? (String[]) strArr5.clone() : null;
        String[] strArr6 = info.valueTypes;
        this.valueTypes = strArr6 != null ? (String[]) strArr6.clone() : null;
        String[] strArr7 = info.pointerTypes;
        this.pointerTypes = strArr7 != null ? (String[]) strArr7.clone() : null;
        String[] strArr8 = info.linePatterns;
        this.linePatterns = strArr8 != null ? (String[]) strArr8.clone() : strArr;
        this.cast = info.cast;
        this.define = info.define;
        this.enumerate = info.enumerate;
        this.flatten = info.flatten;
        this.objectify = info.objectify;
        this.translate = info.translate;
        this.skip = info.skip;
        this.skipDefaults = info.skipDefaults;
        this.purify = info.purify;
        this.virtualize = info.virtualize;
        this.base = info.base;
        this.cppText = info.cppText;
        this.javaText = info.javaText;
    }

    public Info cppNames(String... strArr) {
        this.cppNames = strArr;
        return this;
    }

    public Info javaNames(String... strArr) {
        this.javaNames = strArr;
        return this;
    }

    public Info annotations(String... strArr) {
        this.annotations = strArr;
        return this;
    }

    public Info cppTypes(String... strArr) {
        this.cppTypes = strArr;
        return this;
    }

    public Info valueTypes(String... strArr) {
        this.valueTypes = strArr;
        return this;
    }

    public Info pointerTypes(String... strArr) {
        this.pointerTypes = strArr;
        return this;
    }

    public Info linePatterns(String... strArr) {
        this.linePatterns = strArr;
        return this;
    }

    public Info cast() {
        this.cast = true;
        return this;
    }

    public Info cast(boolean z) {
        this.cast = z;
        return this;
    }

    public Info define() {
        this.define = true;
        return this;
    }

    public Info define(boolean z) {
        this.define = z;
        return this;
    }

    public Info enumerate() {
        this.enumerate = true;
        return this;
    }

    public Info enumerate(boolean z) {
        this.enumerate = z;
        return this;
    }

    public Info flatten() {
        this.flatten = true;
        return this;
    }

    public Info flatten(boolean z) {
        this.flatten = z;
        return this;
    }

    public Info objectify() {
        this.objectify = true;
        return this;
    }

    public Info objectify(boolean z) {
        this.objectify = z;
        return this;
    }

    public Info translate() {
        this.translate = true;
        return this;
    }

    public Info translate(boolean z) {
        this.translate = z;
        return this;
    }

    public Info skip() {
        this.skip = true;
        return this;
    }

    public Info skip(boolean z) {
        this.skip = z;
        return this;
    }

    public Info skipDefaults() {
        this.skipDefaults = true;
        return this;
    }

    public Info skipDefaults(boolean z) {
        this.skipDefaults = z;
        return this;
    }

    public Info purify() {
        this.purify = true;
        return this;
    }

    public Info purify(boolean z) {
        this.purify = z;
        return this;
    }

    public Info virtualize() {
        this.virtualize = true;
        return this;
    }

    public Info virtualize(boolean z) {
        this.virtualize = z;
        return this;
    }

    public Info base(String str) {
        this.base = str;
        return this;
    }

    public Info cppText(String str) {
        this.cppText = str;
        return this;
    }

    public Info javaText(String str) {
        this.javaText = str;
        return this;
    }
}
