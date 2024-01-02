package com.example.ropa;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import WebServices.Asynchtask;
import WebServices.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws = new
                WebService("https://fakestoreapi.com/products",
                datos, MainActivity.this, MainActivity.this);
        ws.execute("GET", "Public-Merchant-Id", "84e1d0de1fbf437e9779fd6a52a9ca18");
    }
    @Override
    public void processFinish(String result) {
        try {
            Log.d("JSON_RESULT", result);
            JSONArray JSONlista = new JSONArray(result);
            int cardViewCount = 5;
            for (int i = 0; i < cardViewCount; i++) {
                JSONObject producto = JSONlista.getJSONObject(i);
                String category = producto.optString("category", "N/A");
                String title = producto.optString("title", "N/A");
                double price = producto.optDouble("price", 0.0);
                String description = producto.optString("description", "N/A");
                String imageUrl = producto.optString("image", "");
                int cardViewId = getResources().getIdentifier("cardView" + (i + 1), "id", getPackageName());
                int imageButtonId = getResources().getIdentifier("imageButton" + (i + 1), "id", getPackageName());
                int txtRopaId = getResources().getIdentifier("txtRopa" + (i + 1), "id", getPackageName());
                CardView cardView = findViewById(cardViewId);
                ImageButton imageButton = findViewById(imageButtonId);
                TextView txtRopa = findViewById(txtRopaId);
                setupImageButtonClick(imageButton, category, title, price, imageUrl); 
                StringBuilder productDetails = new StringBuilder();
                productDetails.append("Category: ").append(category)
                        .append("\n").append(title)
                        .append("\nPrice: ").append(price)
                        .append("\n").append(description);
                txtRopa.setText(productDetails.toString());
                Glide.with(this).load(imageUrl).into(imageButton);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            TextView txtRopa1 = findViewById(R.id.txtRopa1);
            txtRopa1.setText("Error al procesar el JSON: " + e.getMessage());
        }
    }

    private void setupImageButtonClick(ImageButton imageButton, String category, String title, double price, String imageUrl) {
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("CATEGORY", category);
                intent.putExtra("TITLE", title);
                intent.putExtra("PRICE", price);
                intent.putExtra("IMAGE_URL", imageUrl);
                startActivity(intent);
            }
        });
    }
}
