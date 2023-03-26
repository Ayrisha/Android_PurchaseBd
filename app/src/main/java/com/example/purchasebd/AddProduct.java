package com.example.purchasebd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.purchasebd.bd.AppDatabase;
import com.example.purchasebd.bd.Product;

import java.util.List;

public class AddProduct extends AppCompatActivity {
    EditText id, name, price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        id = findViewById(R.id.updateBuyerName);
        name = findViewById(R.id.updateBuyerPhone);
        price = findViewById(R.id.updateBuyerEmail);

        Button add = findViewById(R.id.buttonUpdateBuyer);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProduct();
                Intent intent = new Intent(AddProduct.this, MainActivity.class);
                intent.putExtra("name_table", "Products");
                startActivity(intent);
            }
        });
    }

    private void saveProduct(){
        String sID = id.getText().toString();
        String sName = name.getText().toString();
        String sPrice = price.getText().toString();

        if (sID.isEmpty()){
            id.setError("Task required");
            id.requestFocus();
            return;
        }

        if (sName.isEmpty()){
            name.setError("Task required");
            name.requestFocus();
            return;
        }

        if (sPrice.isEmpty()){
            price.setError("Task required");
            price.requestFocus();
            return;
        }

        int sIDInt = Integer.parseInt(id.getText().toString());
        int sPriceInt = Integer.parseInt(price.getText().toString());

        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        Product product = new Product();
        List<Product> productList = db.productDao().getAllProduct();

        if (productList.size() != 0) {
            for (int i = 0; i < productList.size(); i++) {
                if (sIDInt == (productList.get(i).idProduct)) {
                    id.setError("There is such a ID");
                    id.requestFocus();
                    return;
                }
            }
        }

        product.idProduct = sIDInt;
        product.productName = sName;
        product.productPrice = sPriceInt;
        db.productDao().insertProduct(product);
        finish();
    }
}