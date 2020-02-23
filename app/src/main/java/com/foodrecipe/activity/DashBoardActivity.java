package com.foodrecipe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.foodrecipe.R;


public class DashBoardActivity extends AppCompatActivity {
    Button cd_add_recipies,cd_view_recipies,cd_history,cd_edit_recipies,cd_my_recipies,cd_edit_profile,btn_offline_data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        getSupportActionBar().setTitle("User DashBoard");

        cd_add_recipies=(Button)findViewById(R.id.cd_add_recipies);
        cd_add_recipies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DashBoardActivity.this,AddRecipiesActivity.class);
                startActivity(intent);
            }
        });

        cd_view_recipies=(Button)findViewById(R.id.cd_view_recipies);
        cd_view_recipies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DashBoardActivity.this,ViewRecipiesActivity.class);
                startActivity(intent);

            }
        });




        cd_history=(Button)findViewById(R.id.cd_history);
        cd_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DashBoardActivity.this,MainSqlDataBase.class);
                startActivity(intent);

            }
        });
        cd_my_recipies=(Button)findViewById(R.id.cd_my_recipies);
        cd_my_recipies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DashBoardActivity.this,MyRecipiesActivity.class);
                startActivity(intent);

            }
        });

        cd_edit_recipies=(Button)findViewById(R.id.cd_edit_recipies);
        cd_edit_recipies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DashBoardActivity.this,EditRecipiesActivity.class);
                startActivity(intent);

            }
        });


        cd_edit_profile=(Button)findViewById(R.id.cd_edit_profile);
        cd_edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DashBoardActivity.this,EditProfileActivity.class);
                startActivity(intent);

            }
        });
        btn_offline_data=(Button)findViewById(R.id.btn_offline_data);
        btn_offline_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DashBoardActivity.this,OfflineDataActivity.class);
                startActivity(intent);

            }
        });
    }

}
