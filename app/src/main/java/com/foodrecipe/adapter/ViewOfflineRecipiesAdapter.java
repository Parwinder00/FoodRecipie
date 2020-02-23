package com.foodrecipe.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.foodrecipe.R;
import com.foodrecipe.activity.DetailsAboutViewOfflineRecipiesActivity;
import com.foodrecipe.activity.DetailsAboutViewRecipiesActivity;
import com.foodrecipe.model.ViewRecipiesPojo;

import java.util.List;


public class ViewOfflineRecipiesAdapter extends BaseAdapter {
    List<ViewRecipiesPojo> ar;
    Context cnt;
    String string="http://foodrecipeapp.com/FoodRecipes/";
    public ViewOfflineRecipiesAdapter(List<ViewRecipiesPojo> ar, Context cnt)
    {
        this.ar=ar;
        this.cnt=cnt;
    }
    @Override
    public int getCount() {
        return ar.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int pos, View view, ViewGroup viewGroup)
    {
        LayoutInflater obj1 = (LayoutInflater)cnt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View obj2=obj1.inflate(R.layout.list_view_recipies,null);

        ImageView image_view=(ImageView)obj2.findViewById(R.id.image_view);

        //Toast.makeText(cnt,""+string+ar.get(pos).getImg_url(),Toast.LENGTH_LONG).show();


        Glide.with(cnt).load(string+ar.get(pos).getImg_url()).into(image_view);



        TextView tv_name=(TextView)obj2.findViewById(R.id.tv_name);
        tv_name.setText(ar.get(pos).getRecipe_name());


        image_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(cnt, DetailsAboutViewOfflineRecipiesActivity.class);
                intent.putExtra("FoodName",ar.get(pos).getRecipe_name());
                intent.putExtra("Procedure",ar.get(pos).getRecipe_procedure());
                intent.putExtra("Ingridients",ar.get(pos).getIngredients());
                intent.putExtra("Image",ar.get(pos).getImg_url());
                cnt.startActivity(intent);
            }
        });


        return obj2;

    }

}
