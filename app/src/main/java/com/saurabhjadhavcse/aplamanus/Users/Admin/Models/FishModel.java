package com.saurabhjadhavcse.aplamanus.Users.Admin.Models;

public class FishModel {
    private String modelId;
    private String fishImageUrl;
    private String fishItemTextUrl;
    private String fishPriceTextUrl;

    public FishModel() {

    }

    public FishModel(String modelId, String fishImageUrl, String fishItemTextUrl, String fishPriceTextUrl) {
        this.modelId = modelId;
        this.fishImageUrl = fishImageUrl;
        this.fishItemTextUrl = fishItemTextUrl;
        this.fishPriceTextUrl = fishPriceTextUrl;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getFishImageUrl() {
        return fishImageUrl;
    }

    public void setFishImageUrl(String fishImageUrl) {
        this.fishImageUrl = fishImageUrl;
    }

    public String getFishItemTextUrl() {
        return fishItemTextUrl;
    }

    public void setFishItemTextUrl(String fishItemTextUrl) {
        this.fishItemTextUrl = fishItemTextUrl;
    }

    public String getFishPriceTextUrl() {
        return fishPriceTextUrl;
    }

    public void setFishPriceTextUrl(String fishPriceTextUrl) {
        this.fishPriceTextUrl = fishPriceTextUrl;
    }
}