package com.foodrecipe.activity;
import android.app.Person;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.foodrecipe.EndPointUrl;
import com.foodrecipe.R;
import com.foodrecipe.RetrofitInstance;
import com.foodrecipe.Utils;
import com.foodrecipe.adapter.MyRecipiesAdapter;
import com.foodrecipe.model.MyuRecipiesPojo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyRecipiesActivity extends AppCompatActivity {
    ListView list_view;
    SqlDatabase myDb;
    ProgressDialog progressDialog;
    List<MyuRecipiesPojo> a1;
    SharedPreferences sharedPreferences;
    String uname;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recipies);

        sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        uname = sharedPreferences.getString("user_name", "");

        getSupportActionBar().setTitle("My Recipies");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        list_view=(ListView)findViewById(R.id.list_view);


        a1= new ArrayList<>();
        serverData();
    }
    public void serverData(){
        progressDialog = new ProgressDialog(MyRecipiesActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        EndPointUrl service = RetrofitInstance.getRetrofitInstance().create(EndPointUrl.class);
        Call<List<MyuRecipiesPojo>> call = service.myrecipes(uname);
        call.enqueue(new Callback<List<MyuRecipiesPojo>>() {
            @Override
            public void onResponse(Call<List<MyuRecipiesPojo>> call, Response<List<MyuRecipiesPojo>> response) {
                progressDialog.dismiss();
                if(response.body()==null){
                    Toast.makeText(MyRecipiesActivity.this,"No data found",Toast.LENGTH_SHORT).show();
                }else {
                    a1 = response.body();

                    list_view.setAdapter(new MyRecipiesAdapter(a1, MyRecipiesActivity.this));


                }
            }

            @Override
            public void onFailure(Call<List<MyuRecipiesPojo>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MyRecipiesActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override                                                                                                                    //add this method in your program
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}


