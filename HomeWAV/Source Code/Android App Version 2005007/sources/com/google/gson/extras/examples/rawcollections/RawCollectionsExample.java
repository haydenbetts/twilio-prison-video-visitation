package com.google.gson.extras.examples.rawcollections;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import java.io.PrintStream;
import java.util.ArrayList;

public class RawCollectionsExample {

    static class Event {
        private String name;
        private String source;

        private Event(String str, String str2) {
            this.name = str;
            this.source = str2;
        }

        public String toString() {
            return String.format("(name=%s, source=%s)", new Object[]{this.name, this.source});
        }
    }

    public static void main(String[] strArr) {
        Gson gson = new Gson();
        ArrayList arrayList = new ArrayList();
        arrayList.add("hello");
        arrayList.add(5);
        arrayList.add(new Event("GREETINGS", "guest"));
        String json = gson.toJson((Object) arrayList);
        PrintStream printStream = System.out;
        printStream.println("Using Gson.toJson() on a raw collection: " + json);
        JsonArray asJsonArray = new JsonParser().parse(json).getAsJsonArray();
        int intValue = ((Integer) gson.fromJson(asJsonArray.get(1), Integer.TYPE)).intValue();
        System.out.printf("Using Gson.fromJson() to get: %s, %d, %s", new Object[]{(String) gson.fromJson(asJsonArray.get(0), String.class), Integer.valueOf(intValue), (Event) gson.fromJson(asJsonArray.get(2), Event.class)});
    }
}
