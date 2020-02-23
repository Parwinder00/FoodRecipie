package com.foodrecipe.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.foodrecipe.EndPointUrl;
import com.foodrecipe.R;
import com.foodrecipe.Utils;
import com.foodrecipe.model.UploadObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddRecipiesActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {
    EditText et_name, et_ingredient, et_process;
    TextView tv_name,tv_ingridnts,tv_procss,tv_ingredient;
    Button btn_reg;
    ProgressDialog progressDialog;
    SharedPreferences sharedPreferences;
    String uname;
    Button select_image;
    Spinner spinner_country;

    private static final String TAG = AddRecipiesActivity.class.getSimpleName();
    private static final int REQUEST_GALLERY_CODE = 200;
    private static final int READ_REQUEST_CODE = 300;
    private static final String SERVER_PATH = "http://foodrecipeapp.com/";
    private Uri uri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipies);

        sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        uname = sharedPreferences.getString("user_name", "");
       // Toast.makeText(getApplicationContext(), "" + uname, Toast.LENGTH_LONG).show();
        select_image = (Button) findViewById(R.id.select_image);
        select_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK);
                openGalleryIntent.setType("image/*");
                startActivityForResult(openGalleryIntent, REQUEST_GALLERY_CODE);
            }
        });


        getSupportActionBar().setTitle("Add Recipies");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_ingridnts = (TextView) findViewById(R.id.tv_ingridnts);
        tv_procss = (TextView) findViewById(R.id.tv_procss);


        btn_reg = (Button) findViewById(R.id.btn_reg);

        et_name = (EditText) findViewById(R.id.et_name);
        et_ingredient = (EditText) findViewById(R.id.et_ingredient);
        et_process = (EditText) findViewById(R.id.et_process);
        spinner_country=(Spinner)findViewById(R.id.spinner_country);

        tv_ingredient= (TextView) findViewById(R.id.tv_ingredient);
        tv_ingredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                multicheckbox();

            }
        });


        Typeface fontstyle = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Lato-Medium.ttf");
        tv_name.setTypeface(fontstyle);
        tv_ingridnts.setTypeface(fontstyle);
        tv_procss.setTypeface(fontstyle);
        btn_reg.setTypeface(fontstyle);
        et_name.setTypeface(fontstyle);
        et_ingredient.setTypeface(fontstyle);
        et_process.setTypeface(fontstyle);


        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* if (et_name.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Name Should not be Empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (et_phno.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Phone Number Should not be Empty", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (et_uname.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "User Name Should not be Empty", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (et_password.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Password Should not be Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
*/
               Intent intent=new Intent(getApplicationContext(),ViewCountriesActivity.class);
                Toast.makeText(getApplicationContext(), "Added SuccussFully", Toast.LENGTH_SHORT).show();
               startActivity(intent);
                finish();

            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, AddRecipiesActivity.this);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_GALLERY_CODE && resultCode == Activity.RESULT_OK){
            uri = data.getData();
            if(EasyPermissions.hasPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                String filePath = getRealPathFromURIPath(uri, AddRecipiesActivity.this);
                File file = new File(filePath);
                Log.d(TAG, "Filename " + file.getName());
                Map<String, String> map = new HashMap<>();
                map.put("recipe_name",et_name.getText().toString());
                map.put("ingredients",tv_ingredient.getText().toString());
                map.put("recipe_procedure",et_process.getText().toString());
                map.put("country_name",spinner_country.getSelectedItem().toString());
                map.put("created_by",uname);


                //RequestBody mFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), file);
                MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), mFile);
                RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(SERVER_PATH)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                EndPointUrl uploadImage = retrofit.create(EndPointUrl.class);
                Call<UploadObject> fileUpload = uploadImage.add_recipe(fileToUpload, map);
                fileUpload.enqueue(new Callback<UploadObject>() {
                    @Override
                    public void onResponse(Call<UploadObject> call, Response<UploadObject> response) {
                        Toast.makeText(AddRecipiesActivity.this, "Response " + response.raw().message(), Toast.LENGTH_LONG).show();
                        Toast.makeText(AddRecipiesActivity.this, "Success " + response.body().getSuccess(), Toast.LENGTH_LONG).show();
                        // Intent intent=new Intent(ProfileImageUploadActivity.this,IDProofUploadActivity.class);
                        //intent.putExtra("APCODE",getIntent().getStringExtra("apcode"));
                        //startActivity(intent);

                    }
                    @Override
                    public void onFailure(Call<UploadObject> call, Throwable t) {
                        Log.d(TAG, "Error " + t.getMessage());
                    }
                });
            }else{
                EasyPermissions.requestPermissions(this, getString(R.string.read_file), READ_REQUEST_CODE, Manifest.permission.READ_EXTERNAL_STORAGE);
            }
        }
    }
    private String getRealPathFromURIPath(Uri contentURI, Activity activity) {
        Cursor cursor = activity.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }


    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if(uri != null){
            String filePath = getRealPathFromURIPath(uri, AddRecipiesActivity.this);
            File file = new File(filePath);
            RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), file);
            Map<String, String> map = new HashMap<>();
            map.put("recipe_name",et_name.getText().toString());
            map.put("ingredients",tv_ingredient.getText().toString());
            map.put("recipe_procedure",et_process.getText().toString());
            map.put("country_name",spinner_country.getSelectedItem().toString());
            map.put("created_by",uname);



            MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), mFile);
            RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(SERVER_PATH)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            EndPointUrl uploadImage = retrofit.create(EndPointUrl.class);
            Call<UploadObject> fileUpload = uploadImage.add_recipe(fileToUpload, map);
            fileUpload.enqueue(new Callback<UploadObject>() {
                @Override
                public void onResponse(Call<UploadObject> call, Response<UploadObject> response) {
                    Toast.makeText(AddRecipiesActivity.this, "Success " + response.message(), Toast.LENGTH_LONG).show();
                    Toast.makeText(AddRecipiesActivity.this, "Success " + response.body().toString(), Toast.LENGTH_LONG).show();
                    //Intent intent=new Intent(ProfileImageUploadActivity.this,IDProofUploadActivity.class);
                    // intent.putExtra("APCODE",getIntent().getStringExtra("apcode"));
                    //startActivity(intent);
                    finish();
                }
                @Override
                public void onFailure(Call<UploadObject> call, Throwable t) {
                    Log.d(TAG, "Error " + t.getMessage());
                }
            });
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.d(TAG, "Permission has been denied");

    }

    @Override
    //add this method in your program
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void multicheckbox(){
        Dialog dialog;
        final String[] items = {" Onions", "Tomoto", " Chilli", "Ginger Garlic", "Rice"};
        final ArrayList itemsSelected = new ArrayList();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Ingridients : ");
        builder.setMultiChoiceItems(items, null,
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int selectedItemId,
                                        boolean isSelected) {
                        if (isSelected) {
                            itemsSelected.add(items[selectedItemId]);
                            Toast.makeText(getApplicationContext(), items[selectedItemId], Toast.LENGTH_SHORT).show();
                        } else if (itemsSelected.contains(items[selectedItemId])) {
                            itemsSelected.remove(items[selectedItemId]);
                        }
                    }
                })
                .setPositiveButton("Done!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //Your logic when OK button is clicked

                        tv_ingredient.setText(itemsSelected.toString());


                        }

                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        dialog = builder.create();
        dialog.show();
    }

}
