package com.bumptech.glide.provider;

import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import java.io.File;
import java.util.Objects;

public class FixedLoadProvider<A, T, Z, R> implements LoadProvider<A, T, Z, R> {
    private final DataLoadProvider<T, Z> dataLoadProvider;
    private final ModelLoader<A, T> modelLoader;
    private final ResourceTranscoder<Z, R> transcoder;

    public FixedLoadProvider(ModelLoader<A, T> modelLoader2, ResourceTranscoder<Z, R> resourceTranscoder, DataLoadProvider<T, Z> dataLoadProvider2) {
        Objects.requireNonNull(modelLoader2, "ModelLoader must not be null");
        this.modelLoader = modelLoader2;
        Objects.requireNonNull(resourceTranscoder, "Transcoder must not be null");
        this.transcoder = resourceTranscoder;
        Objects.requireNonNull(dataLoadProvider2, "DataLoadProvider must not be null");
        this.dataLoadProvider = dataLoadProvider2;
    }

    public ModelLoader<A, T> getModelLoader() {
        return this.modelLoader;
    }

    public ResourceTranscoder<Z, R> getTranscoder() {
        return this.transcoder;
    }

    public ResourceDecoder<File, Z> getCacheDecoder() {
        return this.dataLoadProvider.getCacheDecoder();
    }

    public ResourceDecoder<T, Z> getSourceDecoder() {
        return this.dataLoadProvider.getSourceDecoder();
    }

    public Encoder<T> getSourceEncoder() {
        return this.dataLoadProvider.getSourceEncoder();
    }

    public ResourceEncoder<Z> getEncoder() {
        return this.dataLoadProvider.getEncoder();
    }
}
