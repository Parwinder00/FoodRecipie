package com.foodrecipe.activity;

import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.foodrecipe.EndPointUrl;
import com.foodrecipe.R;
import com.foodrecipe.ResponseData;
import com.foodrecipe.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EditRecipiesActivity extends AppCompatActivity {
    EditText et_name, et_ingredient, et_process;
    TextView tv_name,tv_ingridnts,tv_procss;
    Button btn_reg;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_recipies);

        getSupportActionBar().setTitle("Edit Recipies");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tv_name=(TextView)findViewById(R.id.tv_name);
        tv_ingridnts=(TextView)findViewById(R.id.tv_ingridnts);
        tv_procss=(TextView)findViewById(R.id.tv_procss);


        btn_reg = (Button) findViewById(R.id.btn_reg);

        et_name = (EditText) findViewById(R.id.et_name);
        et_ingredient = (EditText) findViewById(R.id.et_ingredient);
        et_process = (EditText) findViewById(R.id.et_process);


        Typeface fontstyle=Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/Lato-Medium.ttf");
        tv_name.setTypeface(fontstyle);
        tv_ingridnts.setTypeface(fontstyle);
        tv_procss.setTypeface(fontstyle);
        btn_reg.setTypeface(fontstyle);
        et_name.setTypeface(fontstyle);
        et_name.setText(getIntent().getExtras().getString("rname"));

        et_ingredient.setTypeface(fontstyle);
        et_ingredient.setText(getIntent().getExtras().getString("ringr"));
        et_process.setTypeface(fontstyle);
        et_process.setText(getIntent().getExtras().getString("rproc"));


        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitData();


            }
        });

    }

   private void submitData() {

        progressDialog = new ProgressDialog(EditRecipiesActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        EndPointUrl service = RetrofitInstance.getRetrofitInstance().create(EndPointUrl.class);
        Call<ResponseData> call = service.update_my_recipe(et_name.getText().toString(),et_ingredient.getText().toString(),et_process.getText().toString(),getIntent().getExtras().getString("id"));
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {

                if (response.body().status.equals("true")) {
                    progressDialog.dismiss();
                    Toast.makeText(EditRecipiesActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                    finish();

                } else {
                    Toast.makeText(EditRecipiesActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(EditRecipiesActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
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
}
