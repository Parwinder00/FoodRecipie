package com.foodrecipe.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.foodrecipe.R;
import com.foodrecipe.activity.DetailsAboutViewRecipiesActivity;
import com.foodrecipe.activity.ViewRecipiesActivity;
import com.foodrecipe.model.ViewCountriesPojo;
import com.foodrecipe.model.ViewRecipiesPojo;

import java.util.List;

public class ViewCountriesAdapter extends BaseAdapter {
    List<ViewCountriesPojo> ar;
    Context cnt;
    String string="http://foodrecipeapp.com/";
    public ViewCountriesAdapter(List<ViewCountriesPojo> ar, Context cnt)
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
        View obj2=obj1.inflate(R.layout.list_view_countries,null);

        ImageView image_view=(ImageView)obj2.findViewById(R.id.image_view);
        Glide.with(cnt).load(string+ar.get(pos).getImg_url()).into(image_view);

        TextView tv_name=(TextView)obj2.findViewById(R.id.tv_name);
        tv_name.setText(ar.get(pos).getCountry_name());




        image_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(cnt, ViewRecipiesActivity.class);
                intent.putExtra("Image",ar.get(pos).getImg_url());
                intent.putExtra("country_name",ar.get(pos).getCountry_name());
                cnt.startActivity(intent);
            }
        });


        return obj2;

    }
  
}
