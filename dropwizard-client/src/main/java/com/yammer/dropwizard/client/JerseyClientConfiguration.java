package com.yammer.dropwizard.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.validation.ValidationMethod;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@SuppressWarnings("UnusedDeclaration")
public class JerseyClientConfiguration extends HttpClientConfiguration {
    @Min(1)
    @Max(16 * 1024)
    @JsonProperty
    private int minThreads = 1;

    @Min(1)
    @Max(16 * 1024)
    @JsonProperty
    private int maxThreads = 128;

    @JsonProperty
    private boolean gzipEnabled = true;

    @JsonProperty
    private boolean compressRequestEntity = true;

    public int getMinThreads() {
        return minThreads;
    }

    public void setMinThreads(int minThreads) {
        this.minThreads = minThreads;
    }

    public int getMaxThreads() {
        return maxThreads;
    }

    public void setMaxThreads(int maxThreads) {
        this.maxThreads = maxThreads;
    }

    public boolean isGzipEnabled() {
        return gzipEnabled;
    }

    public void setGzipEnabled(boolean enable) {
        this.gzipEnabled = enable;
    }

    public boolean isCompressRequestEntity() {
        return compressRequestEntity;
    }

    public void setCompressRequestEntity(boolean compressRequestEntity) {
        this.compressRequestEntity = compressRequestEntity;
    }

    @ValidationMethod(message = ".minThreads must be less than or equal to maxThreads")
    public boolean isThreadPoolSizedCorrectly() {
        return minThreads <= maxThreads;
    }

    @ValidationMethod(message = ".compressRequestEntity requires gzipEnabled set to true")
    public boolean isCompressionConfigurationValid() {
        return !compressRequestEntity || gzipEnabled;
    }
}
