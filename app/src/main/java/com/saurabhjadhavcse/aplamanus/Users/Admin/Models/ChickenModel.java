package com.saurabhjadhavcse.aplamanus.Users.Admin.Models;

public class ChickenModel {

    private String modelId;
    private String chickenImageUrl;
    private String chickenTextUrl;
    private String chickenPriceUrl;

    public ChickenModel() {

    }

    public ChickenModel(String modelId, String chickenImageUrl, String chickenTextUrl, String chickenPriceUrl) {
        this.modelId = modelId;
        this.chickenImageUrl = chickenImageUrl;
        this.chickenTextUrl = chickenTextUrl;
        this.chickenPriceUrl = chickenPriceUrl;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getChickenImageUrl() {
        return chickenImageUrl;
    }

    public void setChickenImageUrl(String chickenImageUrl) {
        this.chickenImageUrl = chickenImageUrl;
    }

    public String getChickenTextUrl() {
        return chickenTextUrl;
    }

    public void setChickenTextUrl(String chickenTextUrl) {
        this.chickenTextUrl = chickenTextUrl;
    }

    public String getChickenPriceUrl() {
        return chickenPriceUrl;
    }

    public void setChickenPriceUrl(String chickenPriceUrl) {
        this.chickenPriceUrl = chickenPriceUrl;
    }
}
