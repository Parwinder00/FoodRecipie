package com.foodrecipe.activity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.foodrecipe.model.ViewRecipiesPojo;

public class DatabaseHandler extends SQLiteOpenHelper{
    public DatabaseHandler(Context context) {
        super(context, "recipes.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {//recipe_name,ingredients,recipe_procedure,id
        db.execSQL("create table tb_recipes(id INTEGER PRIMARY KEY AUTOINCREMENT,recipe_name TEXT,ingredients TEXT,recipe_procedure TEXT,img_url TEXT);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tb_employee");
        onCreate(db);
    }
    public long addRecipes(String recipe_name,String ingredients,String recipe_procedure,String img_url) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("recipe_name", recipe_name);
        values.put("ingredients", ingredients);
        values.put("recipe_procedure", recipe_procedure);
        values.put("img_url", img_url);

        // Inserting Row
        long a = db.insert("tb_recipes", null, values);
        db.close(); // Closing database connection
        return a;
    }
    public List<ViewRecipiesPojo> getRecipes() {
        List<ViewRecipiesPojo> ll= new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM tb_recipes;";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ll.add(new ViewRecipiesPojo("1",cursor.getString(4),cursor.getString(2),cursor.getString(1),cursor.getString(3)));

            } while (cursor.moveToNext());
        }

        // return contact list
        return ll;
    }
}
