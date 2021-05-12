package com.pusher.client.channel;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.Map;

public class PusherEventDeserializer implements JsonDeserializer<PusherEvent> {
    private final Gson GSON = new Gson();

    public PusherEvent deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return new PusherEvent((Map) this.GSON.fromJson(jsonElement, Map.class));
    }
}
