package com.urbanairship.images;

public class ImageRequestOptions {
    private final int placeHolder;
    private final String url;

    private ImageRequestOptions(Builder builder) {
        this.url = builder.url;
        this.placeHolder = builder.placeHolder;
    }

    public int getPlaceHolder() {
        return this.placeHolder;
    }

    public String getUrl() {
        return this.url;
    }

    public static Builder newBuilder(String str) {
        return new Builder(str);
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public int placeHolder;
        /* access modifiers changed from: private */
        public String url;

        private Builder(String str) {
            this.url = str;
        }

        public Builder setPlaceHolder(int i) {
            this.placeHolder = i;
            return this;
        }

        public ImageRequestOptions build() {
            return new ImageRequestOptions(this);
        }
    }
}
