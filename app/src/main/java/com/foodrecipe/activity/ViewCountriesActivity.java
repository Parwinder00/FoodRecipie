package com.foodrecipe.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import com.foodrecipe.EndPointUrl;
import com.foodrecipe.R;
import com.foodrecipe.RetrofitInstance;
import com.foodrecipe.adapter.ViewCountriesAdapter;
import com.foodrecipe.adapter.ViewRecipiesAdapter;
import com.foodrecipe.model.ViewCountriesPojo;
import com.foodrecipe.model.ViewRecipiesPojo;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewCountriesActivity extends AppCompatActivity {
    ListView list_view;
    ProgressDialog progressDialog;
    List<ViewCountriesPojo> a1;
    SharedPreferences sharedPreferences;
    String uname;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipies);

       // sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        //uname = sharedPreferences.getString("user_name", "");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout =findViewById(R.id.draw_layout);
        navigationView=findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId() ){
                    case R.id.nav_all:
                        Intent all = new Intent(getApplicationContext(), ViewCountriesActivity.class);
                       // all.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(all);
                        break;
                    case R.id.nav_add:
                        Intent addrecipe = new Intent(getApplicationContext(), AddRecipiesActivity.class);
                        startActivity(addrecipe);
                        break;
                    case R.id.nav_my:
                        Intent myrecipe = new Intent(getApplicationContext(), MyRecipiesActivity.class);
                        startActivity(myrecipe);
                        break;
                    case R.id.nav_edit:
                        Intent edit_profile = new Intent(getApplicationContext(), EditProfileActivity.class);
                        startActivity(edit_profile);
                        break;
                    case R.id.nav_search:
                        Intent search_recipe = new Intent(getApplicationContext(), SearchRecipesActivity.class);
                        startActivity(search_recipe);
                        break;
                    case R.id.nav_his:
                        Intent edit_his = new Intent(getApplicationContext(), OfflineDataActivity.class);
                        startActivity(edit_his);
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

// we can open nav drawer by creating buuton in menu in tool bar and open it for animation
        //we are using ActionBarDrawerToggle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if(savedInstanceState==null) {

            //navigationView.setCheckedItem(R.id.nav_all);

        }
        /*getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/

        list_view=(ListView)findViewById(R.id.list_view);
        a1= new ArrayList<>();
        serverData();
    }
    public void serverData(){
        progressDialog = new ProgressDialog(ViewCountriesActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        EndPointUrl service = RetrofitInstance.getRetrofitInstance().create(EndPointUrl.class);
        Call<List<ViewCountriesPojo>> call = service.getRecipieCategory();
        call.enqueue(new Callback<List<ViewCountriesPojo>>() {
            @Override
            public void onResponse(Call<List<ViewCountriesPojo>> call, Response<List<ViewCountriesPojo>> response) {
                progressDialog.dismiss();
                if(response.body()==null){
                    Toast.makeText(ViewCountriesActivity.this,"No data found",Toast.LENGTH_SHORT).show();
                }else {
                    a1 = response.body();
                    list_view.setAdapter(new ViewCountriesAdapter(a1, ViewCountriesActivity.this));


                }
            }

            @Override
            public void onFailure(Call<List<ViewCountriesPojo>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ViewCountriesActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }
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

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }

    }
    @Override
    protected void onResume() {
        super.onResume();
       // navigationView.setCheckedItem(R.id.nav_all);
    }
}


