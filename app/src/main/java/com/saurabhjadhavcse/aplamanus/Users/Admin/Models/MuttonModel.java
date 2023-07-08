package com.saurabhjadhavcse.aplamanus.Users.Admin.Models;

public class MuttonModel {

    private String modelId;
    private String muttonImageUrl;
    private String muttonTextUrl;
    private String muttonPriceUrl;

    public MuttonModel() {

    }

    public MuttonModel(String modelId, String muttonImageUrl, String muttonTextUrl, String muttonPriceUrl) {
        this.modelId = modelId;
        this.muttonImageUrl = muttonImageUrl;
        this.muttonTextUrl = muttonTextUrl;
        this.muttonPriceUrl = muttonPriceUrl;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getMuttonImageUrl() {
        return muttonImageUrl;
    }

    public void setMuttonImageUrl(String muttonImageUrl) {
        this.muttonImageUrl = muttonImageUrl;
    }

    public String getMuttonTextUrl() {
        return muttonTextUrl;
    }

    public void setMuttonTextUrl(String muttonTextUrl) {
        this.muttonTextUrl = muttonTextUrl;
    }

    public String getMuttonPriceUrl() {
        return muttonPriceUrl;
    }

    public void setMuttonPriceUrl(String muttonPriceUrl) {
        this.muttonPriceUrl = muttonPriceUrl;
    }
}

