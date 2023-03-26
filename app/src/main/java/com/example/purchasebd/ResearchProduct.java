package com.example.purchasebd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.purchasebd.bd.AppDatabase;
import com.example.purchasebd.bd.Product;
import com.example.purchasebd.bd.Purchase;

import java.io.Serializable;
import java.util.List;

public class ResearchProduct extends AppCompatActivity {

    EditText id;
    TextView text;
    String sId;
    List<Product> productList;
    int k = 0;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research_product);

        db = AppDatabase.getDbInstance(this.getApplicationContext());
        productList = db.productDao().getAllProduct();

        text = findViewById(R.id.textShow);
        id = findViewById(R.id.editTextResearchPurchase);
        findViewById(R.id.buttonResearch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sId = id.getText().toString();
                if (sId.isEmpty()){
                    Intent intent = new Intent(ResearchProduct.this, MainActivity.class);
                    intent.putExtra("name_table", "Products");
                    startActivity(intent);
                }
                else{
                    researchProduct();
                }
            }
        });
    }

    private void researchProduct(){
        for (int i = 0; i < productList.size(); i++){
            if (productList.get(i).idProduct == Integer.parseInt(sId)){
                k = 1;
                break;
            }
        }
        if (k == 0){
            Toast.makeText(this, "Not found.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ResearchProduct.this, MainActivity.class);
            intent.putExtra("name_table", "Products");
            startActivity(intent);
        }
        else {
            Product product = db.productDao().getId(Integer.parseInt(sId));

            Intent intent = new Intent(ResearchProduct.this, MainActivity.class);
            intent.putExtra("research_product", (Serializable) product);
            startActivity(intent);
        }
    }
}