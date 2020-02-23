package com.foodrecipe.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.foodrecipe.R;
import com.foodrecipe.adapter.ViewOfflineRecipiesAdapter;
import com.foodrecipe.adapter.ViewRecipiesAdapter;

public class OfflineDataActivity  extends AppCompatActivity {
    ListView list_view;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recipies);

        getSupportActionBar().setTitle("OffLine Data");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        list_view=(ListView)findViewById(R.id.list_view);
        DatabaseHandler db=new DatabaseHandler(OfflineDataActivity.this);
        list_view.setAdapter(new ViewOfflineRecipiesAdapter(db.getRecipes(), OfflineDataActivity.this));

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
}
