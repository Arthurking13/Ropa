package com.example.ropa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


public class MainActivity2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        TextView txtDetails = findViewById(R.id.textView2);
        ImageView imageView = findViewById(R.id.imageView);
        Button clickLogin = findViewById(R.id.button);
        if (intent != null) {
            String category = intent.getStringExtra("CATEGORY");
            String title = intent.getStringExtra("TITLE");
            double price = intent.getDoubleExtra("PRICE", 0.0);
            String imageUrl = intent.getStringExtra("IMAGE_URL");
            txtDetails.setText("Category: " + category +
                    "\n" + title +
                    "\nTotal a pagar: " + price);
            Glide.with(this).load(imageUrl).into(imageView);
        }

    }
}