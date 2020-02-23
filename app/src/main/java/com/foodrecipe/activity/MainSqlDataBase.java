package com.foodrecipe.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.foodrecipe.R;


public class MainSqlDataBase extends AppCompatActivity {
    SqlDatabase myDb;
    EditText et_name,et_ingredient,et_process ,editTextId;
    Button btn_reg;
    Button btn_retr;
    Button btnDelete;

    Button btnviewUpdate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_base_add_recipies_);
        myDb = new SqlDatabase(this);

        et_name = (EditText)findViewById(R.id.et_name);
        et_ingredient = (EditText)findViewById(R.id.et_ingredient);
        et_process = (EditText)findViewById(R.id.et_process);

        //editTextId = (EditText)findViewById(R.id.editText_id);
        btn_reg = (Button)findViewById(R.id.btn_reg);
        btn_retr = (Button)findViewById(R.id.btn_retr);

        //btnviewUpdate= (Button)findViewById(R.id.button_update);
        //btnDelete= (Button)findViewById(R.id.button_delete);
        //DeleteData();
        //UpdateData();
        AddData();
        viewAll();
    }
   /* public void DeleteData() {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteData(editTextId.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(MainSqlDataBase.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainSqlDataBase.this,"Data not Deleted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public void UpdateData() {
        btnviewUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDb.updateData(editTextId.getText().toString(),
                                editName.getText().toString(),
                                editSurname.getText().toString(),editMarks.getText().toString());
                        if(isUpdate == true)
                            Toast.makeText(MainSqlDataBase.this,"Data Update",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainSqlDataBase.this,"Data not Updated",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }*/
    public  void AddData() {
        btn_reg.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(et_name.getText().toString(),
                                et_ingredient.getText().toString(),
                                et_process.getText().toString() );
                        if(isInserted == true)
                            Toast.makeText(MainSqlDataBase.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainSqlDataBase.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void viewAll() {
        btn_retr.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :"+ res.getString(0)+"\n");
                            buffer.append("Food Item Name :"+ res.getString(1)+"\n");
                            buffer.append("Ingridients :"+ res.getString(2)+"\n");
                            buffer.append("Recipe Process :"+ res.getString(3)+"\n\n");
                        }

                        // Show all data
                        showMessage("Data",buffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }



}

