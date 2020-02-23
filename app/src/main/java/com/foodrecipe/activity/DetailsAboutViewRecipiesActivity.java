package com.foodrecipe.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.foodrecipe.EndPointUrl;
import com.foodrecipe.R;
import com.foodrecipe.ResponseData;
import com.foodrecipe.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsAboutViewRecipiesActivity extends AppCompatActivity {
    TextView tv_name, tv_process, tv_ingdrints;
    ImageView imageView;
    String string = "http://foodrecipeapp.com/FoodRecipes/";
    Button btn_save_offline,btn_submit;
    String rating;
    RatingBar simpleRatingBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view_recipie);
        ratingBar();


        getSupportActionBar().setTitle(getIntent().getStringExtra("FoodName"));
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //simpleRatingBar.setTe
        simpleRatingBar.setRating(Float.parseFloat(getIntent().getStringExtra("rating")));

        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_process = (TextView) findViewById(R.id.tv_process);
        tv_ingdrints = (TextView) findViewById(R.id.tv_ingdrints);
        btn_save_offline = (Button) findViewById(R.id.btn_save_offline);

        imageView = (ImageView) findViewById(R.id.image_view);
        Glide.with(DetailsAboutViewRecipiesActivity.this).load(string + getIntent().getStringExtra("Image")).into(imageView);


        tv_name.setText(getIntent().getStringExtra("FoodName"));
        tv_process.setText(getIntent().getStringExtra("Procedure"));
        tv_ingdrints.setText(getIntent().getStringExtra("Ingridients"));
        btn_save_offline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHandler db = new DatabaseHandler(DetailsAboutViewRecipiesActivity.this);
                long ll = db.addRecipes(getIntent().getStringExtra("FoodName"), getIntent().getStringExtra("Ingridients"), getIntent().getStringExtra("Procedure"), getIntent().getStringExtra("Image"));
                Toast.makeText(getApplicationContext(), "Data is saved successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

    @Override
    //add this method in your program
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void ratingBar() {
        simpleRatingBar = (RatingBar) findViewById(R.id.simpleRatingBar);
        btn_submit=(Button)findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitData();
            }
        });
        /*simpleRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float value, boolean b) {
                 rating=value+"";
                 //submitData();
                //Toast.makeText(getApplicationContext(),value+"", Toast.LENGTH_SHORT).show();

            }
        });*/

    }
    ProgressDialog progressDialog;
    private void submitData() {


        progressDialog = new ProgressDialog(DetailsAboutViewRecipiesActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        EndPointUrl service = RetrofitInstance.getRetrofitInstance().create(EndPointUrl.class);
        Call<ResponseData> call = service.update_rating(getIntent().getStringExtra("id"),rating);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {

                if (response.body().status.equals("true")) {
                    progressDialog.dismiss();
                    Toast.makeText(DetailsAboutViewRecipiesActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                    //startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                    finish();

                } else {
                    Toast.makeText(DetailsAboutViewRecipiesActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(DetailsAboutViewRecipiesActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


}


