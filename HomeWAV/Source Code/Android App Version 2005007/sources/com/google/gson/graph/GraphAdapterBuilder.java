package com.google.gson.graph;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import com.google.gson.JsonElement;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public final class GraphAdapterBuilder {
    private final ConstructorConstructor constructorConstructor;
    private final Map<Type, InstanceCreator<?>> instanceCreators;

    public GraphAdapterBuilder() {
        HashMap hashMap = new HashMap();
        this.instanceCreators = hashMap;
        this.constructorConstructor = new ConstructorConstructor(hashMap);
    }

    public GraphAdapterBuilder addType(Type type) {
        final ObjectConstructor<?> objectConstructor = this.constructorConstructor.get(TypeToken.get(type));
        return addType(type, new InstanceCreator<Object>() {
            public Object createInstance(Type type) {
                return objectConstructor.construct();
            }
        });
    }

    public GraphAdapterBuilder addType(Type type, InstanceCreator<?> instanceCreator) {
        if (type == null || instanceCreator == null) {
            throw null;
        }
        this.instanceCreators.put(type, instanceCreator);
        return this;
    }

    public void registerOn(GsonBuilder gsonBuilder) {
        Factory factory = new Factory(this.instanceCreators);
        gsonBuilder.registerTypeAdapterFactory(factory);
        for (Map.Entry<Type, InstanceCreator<?>> key : this.instanceCreators.entrySet()) {
            gsonBuilder.registerTypeAdapter((Type) key.getKey(), factory);
        }
    }

    static class Factory implements TypeAdapterFactory, InstanceCreator {
        /* access modifiers changed from: private */
        public final ThreadLocal<Graph> graphThreadLocal = new ThreadLocal<>();
        private final Map<Type, InstanceCreator<?>> instanceCreators;

        Factory(Map<Type, InstanceCreator<?>> map) {
            this.instanceCreators = map;
        }

        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            if (!this.instanceCreators.containsKey(typeToken.getType())) {
                return null;
            }
            final TypeAdapter<T> delegateAdapter = gson.getDelegateAdapter(this, typeToken);
            final TypeAdapter<JsonElement> adapter = gson.getAdapter(JsonElement.class);
            return new TypeAdapter<T>() {
                public void write(JsonWriter jsonWriter, T t) throws IOException {
                    if (t == null) {
                        jsonWriter.nullValue();
                        return;
                    }
                    Graph graph = (Graph) Factory.this.graphThreadLocal.get();
                    boolean z = false;
                    if (graph == null) {
                        z = true;
                        graph = new Graph(new IdentityHashMap());
                    }
                    Element element = (Element) graph.map.get(t);
                    if (element == null) {
                        element = new Element(t, graph.nextName(), delegateAdapter, (JsonElement) null);
                        graph.map.put(t, element);
                        graph.queue.add(element);
                    }
                    if (z) {
                        Factory.this.graphThreadLocal.set(graph);
                        try {
                            jsonWriter.beginObject();
                            while (true) {
                                Element element2 = (Element) graph.queue.poll();
                                if (element2 != null) {
                                    jsonWriter.name(element2.id);
                                    element2.write(jsonWriter);
                                } else {
                                    jsonWriter.endObject();
                                    return;
                                }
                            }
                        } finally {
                            Factory.this.graphThreadLocal.remove();
                        }
                    } else {
                        jsonWriter.value(element.id);
                    }
                }

                public T read(JsonReader jsonReader) throws IOException {
                    String str;
                    if (jsonReader.peek() == JsonToken.NULL) {
                        jsonReader.nextNull();
                        return null;
                    }
                    Graph graph = (Graph) Factory.this.graphThreadLocal.get();
                    boolean z = false;
                    if (graph == null) {
                        graph = new Graph(new HashMap());
                        z = true;
                        jsonReader.beginObject();
                        str = null;
                        while (jsonReader.hasNext()) {
                            String nextName = jsonReader.nextName();
                            if (str == null) {
                                str = nextName;
                            }
                            graph.map.put(nextName, new Element(null, nextName, delegateAdapter, (JsonElement) adapter.read(jsonReader)));
                        }
                        jsonReader.endObject();
                    } else {
                        str = jsonReader.nextString();
                    }
                    if (z) {
                        Factory.this.graphThreadLocal.set(graph);
                    }
                    try {
                        Element element = (Element) graph.map.get(str);
                        if (element.value == null) {
                            TypeAdapter unused = element.typeAdapter = delegateAdapter;
                            element.read(graph);
                        }
                        return element.value;
                    } finally {
                        if (z) {
                            Factory.this.graphThreadLocal.remove();
                        }
                    }
                }
            };
        }

        public Object createInstance(Type type) {
            Graph graph = this.graphThreadLocal.get();
            if (graph == null || graph.nextCreate == null) {
                throw new IllegalStateException("Unexpected call to createInstance() for " + type);
            }
            Object createInstance = this.instanceCreators.get(type).createInstance(type);
            Object unused = graph.nextCreate.value = createInstance;
            Element unused2 = graph.nextCreate = null;
            return createInstance;
        }
    }

    static class Graph {
        /* access modifiers changed from: private */
        public final Map<Object, Element<?>> map;
        /* access modifiers changed from: private */
        public Element nextCreate;
        /* access modifiers changed from: private */
        public final Queue<Element> queue;

        private Graph(Map<Object, Element<?>> map2) {
            this.queue = new LinkedList();
            this.map = map2;
        }

        public String nextName() {
            return "0x" + Integer.toHexString(this.map.size() + 1);
        }
    }

    static class Element<T> {
        private final JsonElement element;
        /* access modifiers changed from: private */
        public final String id;
        /* access modifiers changed from: private */
        public TypeAdapter<T> typeAdapter;
        /* access modifiers changed from: private */
        public T value;

        Element(T t, String str, TypeAdapter<T> typeAdapter2, JsonElement jsonElement) {
            this.value = t;
            this.id = str;
            this.typeAdapter = typeAdapter2;
            this.element = jsonElement;
        }

        /* access modifiers changed from: package-private */
        public void write(JsonWriter jsonWriter) throws IOException {
            this.typeAdapter.write(jsonWriter, this.value);
        }

        /* access modifiers changed from: package-private */
        public void read(Graph graph) throws IOException {
            if (graph.nextCreate == null) {
                Element unused = graph.nextCreate = this;
                T fromJsonTree = this.typeAdapter.fromJsonTree(this.element);
                this.value = fromJsonTree;
                if (fromJsonTree == null) {
                    throw new IllegalStateException("non-null value deserialized to null: " + this.element);
                }
                return;
            }
            throw new IllegalStateException("Unexpected recursive call to read() for " + this.id);
        }
    }
}
