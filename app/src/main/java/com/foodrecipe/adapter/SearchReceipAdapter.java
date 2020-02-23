package com.foodrecipe.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.foodrecipe.R;
import com.foodrecipe.activity.DetailsAboutViewRecipiesActivity;
import com.foodrecipe.model.ReceipPojo;


import java.util.ArrayList;
import java.util.List;

public class SearchReceipAdapter extends RecyclerView.Adapter<SearchReceipAdapter.MyviewHolder> {

    Context context;
    List<ReceipPojo> a1,orgData;

    public SearchReceipAdapter(Context context, List<ReceipPojo> dataList) {
        this.context = context;
        this.a1 = dataList;
        orgData=new ArrayList<>();
        orgData.addAll(dataList);
        //this.orgData=dataList;
    }

    public void setMovieList(List<ReceipPojo> a1) {
        this.a1 = a1;
        notifyDataSetChanged();
    }

    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_search,parent,false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, final int position) {
        holder.tv_name.setText(a1.get(position).getRecipe_name());
        Glide.with(context).load("http://foodrecipeapp.com/FoodRecipes/"+a1.get(position).getImg_url())
                .thumbnail(0.5f)

                .into(holder.image);


        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, DetailsAboutViewRecipiesActivity.class);
                intent.putExtra("FoodName",a1.get(position).getRecipe_name());
                intent.putExtra("Procedure",a1.get(position).getRecipe_procedure());
                intent.putExtra("Ingridients",a1.get(position).getIngredients());
                intent.putExtra("Image",a1.get(position).getImg_url());
                intent.putExtra("id",a1.get(position).getId());
                intent.putExtra("rating",a1.get(position).getRating());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        if (a1 != null) {
            return a1.size();
        }
        return 0;

    }

    public void filterList(ArrayList<ReceipPojo> filterdNames) {
        this.a1 = filterdNames;
        notifyDataSetChanged();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        ImageView image;


        public MyviewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            image = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}