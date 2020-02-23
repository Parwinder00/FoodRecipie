package com.foodrecipe;


import com.foodrecipe.model.EditProfilePojo;
import com.foodrecipe.model.MyuRecipiesPojo;
import com.foodrecipe.model.ReceipPojo;
import com.foodrecipe.model.UploadObject;
import com.foodrecipe.model.ViewCountriesPojo;
import com.foodrecipe.model.ViewRecipiesPojo;
import com.foodrecipe.model.ViewRecipiesPojo1;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

public interface EndPointUrl {
    @GET("FoodRecipes/user_registration.php?")
    Call<ResponseData> user_registration(
            @Query("name") String name,
            @Query("phone") String phone,
            @Query("emailid") String emailid,
            @Query("uname1") String uname1,
            @Query("pwd1") String pwd

    );

    @GET("FoodRecipes/user_login.php?")
    Call<ResponseData> user_login(
            @Query("uname") String uname,
            @Query("pwd") String pwd

    );
    @GET("/FoodRecipes/forgotPassword.php?")
    Call<ResponseData> forgotPassword
            (
                    @Query("uname") String uname,
                    @Query("emailid") String emailid
            );


    @GET("FoodRecipes/getMyProfile.php?")
    Call<List<EditProfilePojo>> getMyProfile
            (@Query("uname") String uname);



    @GET("FoodRecipes/update_user_profile.php?")
    Call<ResponseData> admin_update_profile(
            @Query("name") String name,
            @Query("phone") String phone,
            @Query("emailid") String emailid,
            @Query("pwd1") String pwd1,
            @Query("uname1") String uname1
    );

@GET("FoodRecipes/update_myrecipe.php?")
    Call<ResponseData> update_my_recipe(
        @Query("recipe_name") String recipe_name,
        @Query("ingredients") String ingredients,
        @Query("recipe_procedure") String recipe_procedure,
        @Query("id") String id
);
    @Multipart
    @POST("FoodRecipes/add_recipe.php?")
    Call<UploadObject> add_recipe(
            @Part MultipartBody.Part file,
            @PartMap Map<String, String> partMap

    );

    @GET("/FoodRecipes/getAllSearchReciep.php?")
    Call<List<ReceipPojo>> getAllSearchRecieps();

      @GET("/FoodRecipes/getAllRecipes.php")
    Call<List<ViewRecipiesPojo1>> getAllRecipes(@Query("country_name") String country_name);

    @GET("FoodRecipes/getRecipieCategory.php")
    Call<List<ViewCountriesPojo>> getRecipieCategory();

    @GET("/FoodRecipes/myrecipes.php?")
    Call<List<MyuRecipiesPojo>> myrecipes(@Query("uname") String uname);


    @GET("/FoodRecipes/update_rating.php?")
    Call<ResponseData> update_rating(
            @Query("id") String id,
            @Query("rcount") String rcount

    );

}
