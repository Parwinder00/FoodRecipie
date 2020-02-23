package com.foodrecipe.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.foodrecipe.EndPointUrl;
import com.foodrecipe.R;
import com.foodrecipe.ResponseData;
import com.foodrecipe.RetrofitInstance;
import com.foodrecipe.Utils;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPasswordActivity extends AppCompatActivity {
    EditText et_email,et_otp,et_pwd,et_funame;
    Button btn_submit,btn_otp,btn_pwd;
    SharedPreferences sharedPreferences;
    String uname;
    String sendotp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);

        sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        uname=sharedPreferences.getString("user_name","");
        et_funame=(EditText)findViewById(R.id.et_funame);
        btn_submit=(Button)findViewById(R.id.btn_submit);
        et_email=(EditText)findViewById(R.id.et_email);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_email.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Enter your Email",Toast.LENGTH_LONG).show();
                    return;
                }
                submitdata();
            }
        });

    }
    public  void submitdata()
    {
        String email=et_email.getText().toString();
        EndPointUrl apiService = RetrofitInstance.getRetrofitInstance().create(EndPointUrl.class);
        Call<ResponseData> call = apiService.forgotPassword(et_funame.getText().toString(),email);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {

                if (response.body().status.equals("true")) {
                    Toast.makeText(ForgetPasswordActivity.this, response.body().message, Toast.LENGTH_LONG).show();

                    Log.i("msg", "" + response.body().message);

                    finish();
                } else {
                    Toast.makeText(ForgetPasswordActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });
    }

    public void randomnum()
    {
        Random rn = new Random();
        int maximum=99999;
        int minimum=0;
        int range = maximum - minimum + 1;
        int randomNum =  rn.nextInt(range) + minimum;
        sendotp=randomNum+"";
        //Toast.makeText(getApplicationContext(),randomNum+" hahgdgdh",Toast.LENGTH_LONG).show();
    }
}
