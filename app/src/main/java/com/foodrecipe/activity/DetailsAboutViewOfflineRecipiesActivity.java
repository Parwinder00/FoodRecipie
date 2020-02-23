package com.foodrecipe.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.foodrecipe.R;

public class DetailsAboutViewOfflineRecipiesActivity extends AppCompatActivity {
    TextView tv_name,tv_process,tv_ingdrints;
    ImageView imageView;
    String string="http://foodrecipeapp.com/FoodRecipes/";
    Button btn_save_offline;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view_recipie);


        getSupportActionBar().setTitle("Details About Recipies");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tv_name=(TextView)findViewById(R.id.tv_name);
        tv_process=(TextView)findViewById(R.id.tv_process);
        tv_ingdrints=(TextView)findViewById(R.id.tv_ingdrints);
        btn_save_offline=(Button)findViewById(R.id.btn_save_offline);

        imageView=(ImageView)findViewById(R.id.image_view);
        // Glide.with(cnt).load(string+ar.get(pos).getImg_url()).into(image_view);
        Glide.with(DetailsAboutViewOfflineRecipiesActivity.this).load(string+getIntent().getStringExtra("Image")).into(imageView);



        tv_name.setText("Food Iteam Name  :"+getIntent().getStringExtra("FoodName"));
        tv_process.setText("Producure  :"+getIntent().getStringExtra("Procedure"));
        tv_ingdrints.setText("Ingridients  :"+getIntent().getStringExtra("Ingridients"));
        btn_save_offline.setVisibility(View.GONE);




       /*  tv_process=(TextView)findViewById(R.id.tv_process);
        tv_process.setText("Procedure of The Food  :"+getIntent().getStringExtra("Procedure"));
        Toast.makeText(DetailsAboutViewRecipiesActivity.this, ""+getIntent().getStringExtra("Procedure"), Toast.LENGTH_SHORT).show();

        tv_ingdrints=(TextView)findViewById(R.id.tv_ingdrints);
        tv_ingdrints.setText("Ingridients  :"+getIntent().getStringExtra("Ingridients"));
        Toast.makeText(DetailsAboutViewRecipiesActivity.this, ""+getIntent().getStringExtra("Ingridients"), Toast.LENGTH_SHORT).show();
*/
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
