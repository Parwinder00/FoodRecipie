package com.foodrecipe.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;

import com.foodrecipe.EndPointUrl;
import com.foodrecipe.R;
import com.foodrecipe.RetrofitInstance;
import com.foodrecipe.Utils;
import com.foodrecipe.adapter.SearchReceipAdapter;
import com.foodrecipe.model.ReceipPojo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchRecipesActivity extends AppCompatActivity {
    List<ReceipPojo> a1;
    RecyclerView recyclerView;
    SearchReceipAdapter recyclerAdapter;
    SharedPreferences sharedPreferences;
    String uname;
EditText search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_parent);

        sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        uname=sharedPreferences.getString("user_name","");
        search=(EditText)findViewById(R.id.search);
        search.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                //recyclerAdapter.getFilter().filter(cs);

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });

        getSupportActionBar().setTitle("Search Recipes");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        a1 = new ArrayList<>();
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        GridLayoutManager manager = new GridLayoutManager(this, 3, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        recyclerAdapter = new SearchReceipAdapter(SearchRecipesActivity.this,a1);
        recyclerView.setAdapter(recyclerAdapter);

        EndPointUrl apiService = RetrofitInstance.getRetrofitInstance().create(EndPointUrl.class);
        Call<List<ReceipPojo>> call = apiService.getAllSearchRecieps();
        call.enqueue(new Callback<List<ReceipPojo>>() {
            @Override
            public void onResponse(Call<List<ReceipPojo>> call, Response<List<ReceipPojo>> response) {
                a1 = response.body();
                Log.d("TAG","Response = "+a1);
                recyclerAdapter.setMovieList(a1);
            }

            @Override
            public void onFailure(Call<List<ReceipPojo>> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void filter(String text) {
        //new array list that will hold the filtered data
        ArrayList<ReceipPojo> filterdNames = new ArrayList<>();

        //looping through existing elements
        for (ReceipPojo s : a1) {
            //if the existing elements contains the search input
            if (s.getRecipe_name().toLowerCase().contains(text.toLowerCase())||s.getIngredients().toLowerCase().contains(text.toLowerCase())) {
                //adding the element to filtered list
                filterdNames.add(s);
            }
        }

        //calling a method of the adapter class and passing the filtered list
        recyclerAdapter.filterList(filterdNames);
    }
}