package com.trig.trigapp;


import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import static com.trig.trigapp.Fragments.ProfileFragment.profileImg;

public class MainActivity extends AppCompatActivity {

    private NavController navController;
    Toolbar toolbar;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
//            getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
            setContentView(R.layout.activity_main);

            navController = Navigation.findNavController(this, R.id.navHostFragment);
        } catch (Exception e) {
            Log.e(TAG, "onCreate: " + e.getMessage() );
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {
                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                        Log.d("tushar", "onActivityResult: " + profileImg);
                        if(profileImg != null) {
                            profileImg.setImageBitmap(selectedImage);
                        }
                    }

                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                        Uri selectedImage = data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        if (selectedImage != null) {
                            Cursor cursor = getContentResolver().query(selectedImage,
                                    filePathColumn, null, null, null);
                            if (cursor != null) {
                                cursor.moveToFirst();

                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                String picturePath = cursor.getString(columnIndex);
                                Log.d("tushar", "onActivityResult: " + profileImg);
                                if(profileImg != null) {
                                    profileImg.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                                }
                                cursor.close();
                            }
                        }

                    }
                    break;
            }
        }
    }
}
