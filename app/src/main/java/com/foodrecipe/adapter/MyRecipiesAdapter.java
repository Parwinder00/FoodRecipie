package com.foodrecipe.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.foodrecipe.R;
import com.foodrecipe.activity.EditRecipiesActivity;
import com.foodrecipe.model.MyuRecipiesPojo;

import java.util.List;

public class MyRecipiesAdapter extends BaseAdapter {
    List<MyuRecipiesPojo> ar;
    Context cnt;
    String string="http://foodrecipeapp.com/FoodRecipes/";
    public MyRecipiesAdapter(List<MyuRecipiesPojo> ar, Context cnt)
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
        View obj2=obj1.inflate(R.layout.list_view_my_recipie,null);

        ImageView image_view=(ImageView)obj2.findViewById(R.id.image_view);


        Glide.with(cnt).load(string+ar.get(pos).getImg_url()).into(image_view);



        TextView tv_name=(TextView)obj2.findViewById(R.id.tv_name);
        tv_name.setText("Food Iteam Name  :"+ar.get(pos).getRecipe_name());


        TextView tv_process=(TextView)obj2.findViewById(R.id.tv_process);
        tv_process.setText("Procedure of The Food  :"+ar.get(pos).getRecipe_procedure());

        TextView tv_ingdrints=(TextView)obj2.findViewById(R.id.tv_ingdrints);
        tv_ingdrints.setText("Ingridients  :"+ar.get(pos).getIngredients());
        Button btn_EditRecipe=(Button)obj2.findViewById(R.id.btn_EditRecipe);
        btn_EditRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                Intent intent=new Intent(cnt, EditRecipiesActivity.class);
                intent.putExtra("id",ar.get(pos).getId());
                intent.putExtra("rname",ar.get(pos).getRecipe_name());
                intent.putExtra("rproc",ar.get(pos).getRecipe_procedure());
                intent.putExtra("ringr",ar.get(pos).getIngredients());

                cnt.startActivity(intent);

            }
        });



        return obj2;

    }
  
}
