package com.foodrecipe.model;

import com.google.gson.annotations.SerializedName;

public class ViewRecipiesPojo {
    @SerializedName("id")
    private String id;


    @SerializedName("img_url")
    private String img_url;


    @SerializedName("ingredients")
    private String ingredients;

    @SerializedName("recipe_name")
    private String recipe_name;

    @SerializedName("recipe_procedure")
    private String recipe_procedure;





    public ViewRecipiesPojo(String id, String img_url, String ingredients, String recipe_name,String recipe_procedure) {
        this.setId(id);
        this.setImg_url(img_url);
        this.setIngredients(ingredients);
        this.setRecipe_name(recipe_name);
        this.setRecipe_procedure(recipe_procedure);
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

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getRecipe_name() {
        return recipe_name;
    }

    public void setRecipe_name(String recipe_name) {
        this.recipe_name = recipe_name;
    }

    public String getRecipe_procedure() {
        return recipe_procedure;
    }

    public void setRecipe_procedure(String recipe_procedure) {
        this.recipe_procedure = recipe_procedure;
    }

}
