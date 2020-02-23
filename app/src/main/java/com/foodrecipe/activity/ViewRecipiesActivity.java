package com.foodrecipe.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;


import com.foodrecipe.EndPointUrl;
import com.foodrecipe.R;
import com.foodrecipe.RetrofitInstance;
import com.foodrecipe.adapter.ViewRecipiesAdapter;
import com.foodrecipe.model.ViewRecipiesPojo;
import com.foodrecipe.model.ViewRecipiesPojo1;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewRecipiesActivity extends AppCompatActivity {
    ListView list_view;
    ProgressDialog progressDialog;
    List<ViewRecipiesPojo1> a1;
    SharedPreferences sharedPreferences;
    String uname;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipies);

       // sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        //uname = sharedPreferences.getString("user_name", "");

        getSupportActionBar().setTitle("View Recipies");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        list_view=(ListView)findViewById(R.id.list_view);
        a1= new ArrayList<>();
        serverData();
    }
    public void serverData(){
        progressDialog = new ProgressDialog(ViewRecipiesActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        EndPointUrl service = RetrofitInstance.getRetrofitInstance().create(EndPointUrl.class);
        Call<List<ViewRecipiesPojo1>> call = service.getAllRecipes(getIntent().getStringExtra("country_name"));
        call.enqueue(new Callback<List<ViewRecipiesPojo1>>() {
            @Override
            public void onResponse(Call<List<ViewRecipiesPojo1>> call, Response<List<ViewRecipiesPojo1>> response) {
                progressDialog.dismiss();
                if(response.body()==null){
                    Toast.makeText(ViewRecipiesActivity.this,"No data found",Toast.LENGTH_SHORT).show();
                }else {
                    a1 = response.body();
                    list_view.setAdapter(new ViewRecipiesAdapter(a1, ViewRecipiesActivity.this));


                }
            }

            @Override
            public void onFailure(Call<List<ViewRecipiesPojo1>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ViewRecipiesActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    /*@Override                                                                                                                    //add this method in your program
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
//            case R.id.item_addricipe:
//                Intent addrecipe = new Intent(getApplicationContext(), AddRecipiesActivity.class);
//                startActivity(addrecipe);
//                return true;
            case R.id.logout:
                Intent history = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(history);
                return true;
            case android.R.id.home:
                this.finish();
                return true;
//            case R.id.item_my_recipies:
//                Intent myrecipe = new Intent(getApplicationContext(), MyRecipiesActivity.class);
//                startActivity(myrecipe);
//                return true;
//            case R.id.item_offlinedata:
//                Intent offlinedata = new Intent(getApplicationContext(), OfflineDataActivity.class);
//                startActivity(offlinedata);
//                return true;
//            case R.id.item_edit_profile:
//                Intent edit_profile = new Intent(getApplicationContext(), EditProfileActivity.class);
//                startActivity(edit_profile);
//                return true;
//
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}


