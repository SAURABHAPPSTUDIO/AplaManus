package com.saurabhjadhavcse.aplamanus.Users.Admin.Models;

public class VegetableModel {

    private String modelId;
    private String vegetableUrl;
    private String vegetableTextUrl;
    private String vegetablePriceUrl;


    public VegetableModel() {

    }

    public VegetableModel(String modelId, String vegetableUrl, String vegetableTextUrl, String vegetablePriceUrl) {
        this.modelId = modelId;
        this.vegetableUrl = vegetableUrl;
        this.vegetableTextUrl = vegetableTextUrl;
        this.vegetablePriceUrl = vegetablePriceUrl;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getVegetableUrl() {
        return vegetableUrl;
    }

    public void setVegetableUrl(String vegetableUrl) {
        this.vegetableUrl = vegetableUrl;
    }

    public String getVegetableTextUrl() {
        return vegetableTextUrl;
    }

    public void setVegetableTextUrl(String vegetableTextUrl) {
        this.vegetableTextUrl = vegetableTextUrl;
    }

    public String getVegetablePriceUrl() {
        return vegetablePriceUrl;
    }

    public void setVegetablePriceUrl(String vegetablePriceUrl) {
        this.vegetablePriceUrl = vegetablePriceUrl;
    }
}