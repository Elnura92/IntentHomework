package com.androidlessons.intenthomework;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private Button btnToSecondActivity;
    private TextView textView;
    private Button btnSendMessage;
    public static final int REQUEST_CODE = 27;
    private String text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        getToAnotherActivity();
        openGmail();

    }

    private void openGmail() {
        btnSendMessage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(Intent.ACTION_VIEW);
               String uriText = "mailto:" + Uri.encode("geektech@gmail.com") +
                       "?subject=" + Uri.encode(" just email") +
                       "&body=" + Uri.encode(text);
               Uri uri  = Uri.parse(uriText);

               intent.setData(uri);
               startActivity(Intent.createChooser(intent, "Send mail.."));



            }
        });

    }

    private void getToAnotherActivity() {
        btnToSecondActivity.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getParcelableExtra(SecondActivity.PHOTO_KEY);
            imageView.setImageURI(uri);
            text = data.getStringExtra(SecondActivity.TEXT_KEY);
            textView.setText(text);


        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void initViews() {
        btnToSecondActivity = findViewById(R.id.btnToSecondActivity);
        btnSendMessage = findViewById(R.id.btnSendMessage);
        imageView = findViewById(R.id.iv);
        textView = findViewById(R.id.tv);
    }
}