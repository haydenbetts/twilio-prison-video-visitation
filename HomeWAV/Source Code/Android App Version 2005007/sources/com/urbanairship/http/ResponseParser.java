package com.urbanairship.http;

import java.util.List;
import java.util.Map;

public interface ResponseParser<T> {
    T parseResponse(int i, Map<String, List<String>> map, String str) throws Exception;
}
