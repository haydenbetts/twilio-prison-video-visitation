package org.bytedeco.javacpp.tools;

import com.microsoft.appcenter.ingestion.models.properties.BooleanTypedProperty;
import com.microsoft.appcenter.ingestion.models.properties.DoubleTypedProperty;
import com.microsoft.appcenter.ingestion.models.properties.LongTypedProperty;
import com.urbanairship.UrbanAirshipProvider;
import com.urbanairship.json.JsonPredicate;
import com.urbanairship.json.matchers.ExactValueMatcher;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import kotlin.text.Typography;

public class InfoMap extends HashMap<String, List<Info>> {
    static final InfoMap defaults = new InfoMap((InfoMap) null).put(new Info("basic/containers").cppTypes("std::array", "std::bitset", "std::deque", "std::list", "std::map", "std::queue", "std::set", "std::stack", "std::vector", "std::valarray", "std::pair", "std::forward_list", "std::priority_queue", "std::unordered_map", "std::unordered_set")).put(new Info("basic/types").cppTypes("signed", "unsigned", "char", "short", "int", LongTypedProperty.TYPE, "bool", "float", DoubleTypedProperty.TYPE, "_Bool", "_Complex", "_Imaginary", "complex", "imaginary")).put(new Info("noexcept").annotations("@NoException")).put(new Info("__COUNTER__").cppText("#define __COUNTER__ 0")).put(new Info(" __attribute__", "__declspec", "static_assert").annotations(new String[0]).skip()).put(new Info("void").valueTypes("void").pointerTypes("Pointer")).put(new Info("std::nullptr_t").valueTypes("Pointer").pointerTypes("PointerPointer")).put(new Info("FILE", "time_t", "va_list", "std::exception", "std::istream", "std::ostream", "std::iostream", "std::ifstream", "std::ofstream", "std::fstream").cast().pointerTypes("Pointer")).put(new Info("int8_t", "__int8", "jbyte", "signed char").valueTypes("byte").pointerTypes("BytePointer", "ByteBuffer", "byte[]")).put(new Info("uint8_t", "unsigned __int8", "char", "unsigned char").cast().valueTypes("byte").pointerTypes("BytePointer", "ByteBuffer", "byte[]")).put(new Info("int16_t", "__int16", "jshort", "short", "signed short", "short int", "signed short int").valueTypes("short").pointerTypes("ShortPointer", "ShortBuffer", "short[]")).put(new Info("uint16_t", "unsigned __int16", "unsigned short", "unsigned short int").cast().valueTypes("short").pointerTypes("ShortPointer", "ShortBuffer", "short[]")).put(new Info("int32_t", "__int32", "jint", "int", "signed int", "signed").valueTypes("int").pointerTypes("IntPointer", "IntBuffer", "int[]")).put(new Info("uint32_t", "unsigned __int32", "unsigned int", "unsigned").cast().valueTypes("int").pointerTypes("IntPointer", "IntBuffer", "int[]")).put(new Info("jlong", "long long", "signed long long", "long long int", "signed long long int").valueTypes(LongTypedProperty.TYPE).pointerTypes("LongPointer", "LongBuffer", "long[]")).put(new Info("int64_t", "__int64", "uint64_t", "unsigned __int64", "unsigned long long", "unsigned long long int").cast().valueTypes(LongTypedProperty.TYPE).pointerTypes("LongPointer", "LongBuffer", "long[]")).put(new Info(LongTypedProperty.TYPE, "signed long", "long int", "signed long int").valueTypes(LongTypedProperty.TYPE).pointerTypes("CLongPointer")).put(new Info("unsigned long", "unsigned long int").cast().valueTypes(LongTypedProperty.TYPE).pointerTypes("CLongPointer")).put(new Info("size_t", "ssize_t", "ptrdiff_t", "intptr_t", "uintptr_t", "off_t").cast().valueTypes(LongTypedProperty.TYPE).pointerTypes("SizeTPointer")).put(new Info("float", "jfloat").valueTypes("float").pointerTypes("FloatPointer", "FloatBuffer", "float[]")).put(new Info(DoubleTypedProperty.TYPE, "jdouble").valueTypes(DoubleTypedProperty.TYPE).pointerTypes("DoublePointer", "DoubleBuffer", "double[]")).put(new Info("long double").cast().valueTypes(DoubleTypedProperty.TYPE).pointerTypes("Pointer")).put(new Info("std::complex<float>", "float _Complex", "float _Imaginary", "float complex", "float imaginary").cast().pointerTypes("FloatPointer", "FloatBuffer", "float[]")).put(new Info("std::complex<double>", "double _Complex", "double _Imaginary", "double complex", "double imaginary").cast().pointerTypes("DoublePointer", "DoubleBuffer", "double[]")).put(new Info("jboolean").valueTypes(BooleanTypedProperty.TYPE).pointerTypes("BooleanPointer", "boolean[]")).put(new Info("_Bool", "bool").cast().valueTypes(BooleanTypedProperty.TYPE).pointerTypes("BoolPointer", "boolean[]")).put(new Info("jchar").valueTypes("char").pointerTypes("CharPointer", "char[]")).put(new Info("char16_t").cast().valueTypes("char").pointerTypes("CharPointer", "char[]")).put(new Info("char32_t").cast().valueTypes("int").pointerTypes("IntPointer", "int[]")).put(new Info("wchar_t", "WCHAR").cast().valueTypes("char", "int").pointerTypes("CharPointer", "IntPointer")).put(new Info("const char").valueTypes("byte").pointerTypes("@Cast(\"const char*\") BytePointer", "String")).put(new Info("boost::shared_ptr", "std::shared_ptr").annotations("@SharedPtr")).put(new Info("boost::movelib::unique_ptr", "std::unique_ptr").annotations("@UniquePtr")).put(new Info("std::string").annotations("@StdString").valueTypes("BytePointer", "String").pointerTypes("BytePointer")).put(new Info("std::u16string").annotations("@StdString(\"char16_t\")").valueTypes("CharPointer").pointerTypes("CharPointer")).put(new Info("std::u32string").annotations("@StdString(\"char32_t\")").valueTypes("IntPointer").pointerTypes("IntPointer")).put(new Info("std::wstring").annotations("@StdWString").valueTypes("CharPointer", "IntPointer").pointerTypes("CharPointer", "IntPointer")).put(new Info("std::vector").annotations("@StdVector")).put(new Info("abstract").javaNames("_abstract")).put(new Info(BooleanTypedProperty.TYPE).javaNames("_boolean")).put(new Info("byte").javaNames("_byte")).put(new Info("extends").javaNames("_extends")).put(new Info("finally").javaNames("_finally")).put(new Info("implements").javaNames("_implements")).put(new Info("import").javaNames("_import")).put(new Info("instanceof").javaNames("_instanceof")).put(new Info("native").javaNames("_native")).put(new Info("null").javaNames("_null")).put(new Info("package").javaNames("_package")).put(new Info("super").javaNames("_super")).put(new Info("synchronized").javaNames("_synchronized")).put(new Info("transient").javaNames("_transient")).put(new Info("operator ->").javaNames("access")).put(new Info("operator ()").javaNames("apply")).put(new Info("operator []").javaNames("get")).put(new Info("operator =").javaNames("put")).put(new Info("operator +").javaNames("add")).put(new Info("operator -").javaNames("subtract")).put(new Info("operator *").javaNames("multiply")).put(new Info("operator /").javaNames("divide")).put(new Info("operator %").javaNames("mod")).put(new Info("operator ++").javaNames("increment")).put(new Info("operator --").javaNames("decrement")).put(new Info("operator ==").javaNames(ExactValueMatcher.EQUALS_VALUE_KEY)).put(new Info("operator !=").javaNames("notEquals")).put(new Info("operator <").javaNames("lessThan")).put(new Info("operator >").javaNames("greaterThan")).put(new Info("operator <=").javaNames("lessThanEquals")).put(new Info("operator >=").javaNames("greaterThanEquals")).put(new Info("operator !").javaNames(JsonPredicate.NOT_PREDICATE_TYPE)).put(new Info("operator &&").javaNames(JsonPredicate.AND_PREDICATE_TYPE)).put(new Info("operator ||").javaNames(JsonPredicate.OR_PREDICATE_TYPE)).put(new Info("operator &").javaNames(JsonPredicate.AND_PREDICATE_TYPE)).put(new Info("operator |").javaNames(JsonPredicate.OR_PREDICATE_TYPE)).put(new Info("operator ^").javaNames("xor")).put(new Info("operator ~").javaNames(JsonPredicate.NOT_PREDICATE_TYPE)).put(new Info("operator <<").javaNames("shiftLeft")).put(new Info("operator >>").javaNames("shiftRight")).put(new Info("operator +=").javaNames("addPut")).put(new Info("operator -=").javaNames("subtractPut")).put(new Info("operator *=").javaNames("multiplyPut")).put(new Info("operator /=").javaNames("dividePut")).put(new Info("operator %=").javaNames("modPut")).put(new Info("operator &=").javaNames("andPut")).put(new Info("operator |=").javaNames("orPut")).put(new Info("operator ^=").javaNames("xorPut")).put(new Info("operator <<=").javaNames("shiftLeftPut")).put(new Info("operator >>=").javaNames("shiftRightPut")).put(new Info("operator new").javaNames("_new")).put(new Info("operator delete").javaNames("_delete")).put(new Info("getClass").javaNames("_getClass")).put(new Info("notify").javaNames("_notify")).put(new Info("notifyAll").javaNames("_notifyAll")).put(new Info("wait").javaNames("_wait")).put(new Info("allocate").javaNames("_allocate")).put(new Info("close").javaNames("_close")).put(new Info("deallocate").javaNames("_deallocate")).put(new Info("free").javaNames("_free")).put(new Info("address").javaNames("_address")).put(new Info("position").javaNames("_position")).put(new Info(UrbanAirshipProvider.QUERY_PARAMETER_LIMIT).javaNames("_limit")).put(new Info("capacity").javaNames("_capacity")).put(new Info("fill").javaNames("_fill")).put(new Info("zero").javaNames("_zero"));
    InfoMap parent;

    public InfoMap() {
        this.parent = null;
        this.parent = defaults;
    }

    public InfoMap(InfoMap infoMap) {
        this.parent = null;
        this.parent = infoMap;
    }

    /* access modifiers changed from: package-private */
    public String normalize(String str, boolean z, boolean z2) {
        int i;
        boolean z3;
        String str2 = str;
        if (str2 == null || str.length() == 0 || str2.startsWith("basic/")) {
            return str2;
        }
        String str3 = null;
        Token[] tokenArr = new Tokenizer(str2, (File) null, 0).tokenize();
        int length = tokenArr.length;
        Info first = getFirst("basic/types");
        String[] strArr = first != null ? first.cppTypes : new String[0];
        Arrays.sort(strArr);
        int i2 = 0;
        boolean z4 = false;
        while (true) {
            if (i2 >= length) {
                z3 = true;
                break;
            }
            if (tokenArr[i2].match(Token.CONST, Token.CONSTEXPR)) {
                for (int i3 = i2 + 1; i3 < length; i3++) {
                    tokenArr[i3 - 1] = tokenArr[i3];
                }
                i2--;
                length--;
                z4 = true;
            } else {
                if (tokenArr[i2].match(Token.CLASS, Token.STRUCT, Token.UNION)) {
                    str3 = tokenArr[i2].value;
                    for (int i4 = i2 + 1; i4 < length; i4++) {
                        tokenArr[i4 - 1] = tokenArr[i4];
                    }
                    i2--;
                    length--;
                } else if (Arrays.binarySearch(strArr, tokenArr[i2].value) < 0) {
                    z3 = false;
                    break;
                }
            }
            i2++;
        }
        String str4 = "const ";
        if (z3) {
            Arrays.sort(tokenArr, 0, length);
            StringBuilder sb = new StringBuilder();
            if (!z4) {
                str4 = "";
            }
            sb.append(str4);
            sb.append(tokenArr[0].value);
            str2 = sb.toString();
            for (i = 1; i < length; i++) {
                str2 = str2 + " " + tokenArr[i].value;
            }
        } else if (z2) {
            int i5 = -1;
            int i6 = 0;
            for (int i7 = 0; i7 < length; i7++) {
                if (tokenArr[i7].match(Character.valueOf(Typography.less))) {
                    if (i6 == 0) {
                        i5 = i7;
                    }
                    i6++;
                } else {
                    if (tokenArr[i7].match(Character.valueOf(Typography.greater)) && i6 - 1 == 0 && i7 + 1 != length) {
                        i5 = -1;
                    }
                }
            }
            if (i5 >= 0) {
                if (!z4) {
                    str4 = "";
                }
                for (int i8 = 0; i8 < i5; i8++) {
                    str4 = str4 + tokenArr[i8].value;
                }
                str2 = str4;
            }
        }
        if (z && z4) {
            str2 = str2.substring(str2.indexOf("const") + 5);
        }
        if (str3 != null) {
            str2 = str2.substring(str2.indexOf(str3) + str3.length());
        }
        return str2.trim();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r0 = r1.parent;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean containsKey(java.lang.Object r2) {
        /*
            r1 = this;
            boolean r0 = super.containsKey(r2)
            if (r0 != 0) goto L_0x0013
            org.bytedeco.javacpp.tools.InfoMap r0 = r1.parent
            if (r0 == 0) goto L_0x0011
            boolean r2 = r0.containsKey(r2)
            if (r2 == 0) goto L_0x0011
            goto L_0x0013
        L_0x0011:
            r2 = 0
            goto L_0x0014
        L_0x0013:
            r2 = 1
        L_0x0014:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacpp.tools.InfoMap.containsKey(java.lang.Object):boolean");
    }

    public List<Info> get(String str) {
        return get(str, true);
    }

    public List<Info> get(String str, boolean z) {
        List<Info> list;
        List<Info> list2 = (List) super.get(normalize(str, false, false));
        boolean z2 = true;
        if (list2 == null) {
            list2 = (List) super.get(normalize(str, true, false));
        }
        if (list2 != null || !z) {
            z2 = false;
        } else {
            list2 = (List) super.get(normalize(str, true, true));
        }
        if (list2 == null) {
            list2 = new ArrayList<>();
        }
        InfoMap infoMap = this.parent;
        if (infoMap == null || (list = infoMap.get(str, z)) == null || list.size() <= 0) {
            return list2;
        }
        ArrayList arrayList = new ArrayList(list2);
        if (z2) {
            arrayList.addAll(0, list);
        } else {
            arrayList.addAll(list);
        }
        return arrayList;
    }

    public Info get(int i, String str) {
        return get(i, str, true);
    }

    public Info get(int i, String str, boolean z) {
        List<Info> list = get(str, z);
        if (list.size() > 0) {
            return list.get(i);
        }
        return null;
    }

    public Info getFirst(String str) {
        return getFirst(str, true);
    }

    public Info getFirst(String str, boolean z) {
        List<Info> list = get(str, z);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public InfoMap put(int i, Info info) {
        for (String str : info.cppNames != null ? info.cppNames : new String[]{null}) {
            String[] strArr = {normalize(str, false, false), normalize(str, false, true)};
            for (int i2 = 0; i2 < 2; i2++) {
                String str2 = strArr[i2];
                List list = (List) super.get(str2);
                if (list == null) {
                    list = new ArrayList();
                    super.put(str2, list);
                }
                if (!list.contains(info)) {
                    if (i != -1) {
                        list.add(i, info);
                    } else {
                        list.add(info);
                    }
                }
            }
        }
        return this;
    }

    public InfoMap put(Info info) {
        return put(-1, info);
    }

    public InfoMap putFirst(Info info) {
        return put(0, info);
    }
}
