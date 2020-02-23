package com.foodrecipe.model;

import com.google.gson.annotations.SerializedName;

public class ViewCountriesPojo {
    @SerializedName("id")
    private String id;


    @SerializedName("img_url")
    private String img_url;


    @SerializedName("country_name")
    private String country_name;

    public ViewCountriesPojo(String id, String img_url, String country_name) {
        this.setId(id);
        this.setImg_url(img_url);
        this.setCountry_name(country_name);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }
}
