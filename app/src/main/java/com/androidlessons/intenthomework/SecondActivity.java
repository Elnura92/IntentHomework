package com.androidlessons.intenthomework;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class SecondActivity extends AppCompatActivity {
    private Button btnToGallery;
    private ImageView photo;
    private EditText etMessage;
    public static final int REQUEST_CODE_PHOTO = 5;
    public static final String PHOTO_KEY = "PHOTO_KEY";
    public static final String TEXT_KEY = "TEXT_KEY";
    private Uri imageUri;

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initViews();
        openGallery();
        backToMainActivity();

    }

    private void backToMainActivity() {
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra(TEXT_KEY, etMessage.getText().toString());
                intent.putExtra(PHOTO_KEY, imageUri);
                setResult(RESULT_OK, intent);
                finish();

            }
        });
    }


    private void openGallery() {
        btnToGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_CODE_PHOTO);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_PHOTO && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }


    }





    private void initViews() {
        btnToGallery = findViewById(R.id.btnToGallery);
        etMessage = findViewById(R.id.etMessage);
        photo = findViewById(R.id.iv_gallery);
        
        
    }
}