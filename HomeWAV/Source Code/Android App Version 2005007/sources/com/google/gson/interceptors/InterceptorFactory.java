package com.google.gson.interceptors;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

public final class InterceptorFactory implements TypeAdapterFactory {
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        Intercept intercept = (Intercept) typeToken.getRawType().getAnnotation(Intercept.class);
        if (intercept == null) {
            return null;
        }
        return new InterceptorAdapter(gson.getDelegateAdapter(this, typeToken), intercept);
    }

    static class InterceptorAdapter<T> extends TypeAdapter<T> {
        private final TypeAdapter<T> delegate;
        private final JsonPostDeserializer<T> postDeserializer;

        public InterceptorAdapter(TypeAdapter<T> typeAdapter, Intercept intercept) {
            try {
                this.delegate = typeAdapter;
                this.postDeserializer = (JsonPostDeserializer) intercept.postDeserialize().newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        public void write(JsonWriter jsonWriter, T t) throws IOException {
            this.delegate.write(jsonWriter, t);
        }

        public T read(JsonReader jsonReader) throws IOException {
            T read = this.delegate.read(jsonReader);
            this.postDeserializer.postDeserialize(read);
            return read;
        }
    }
}
