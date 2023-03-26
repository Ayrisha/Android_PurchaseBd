package com.example.purchasebd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.purchasebd.bd.AppDatabase;
import com.example.purchasebd.bd.Buyer;
import com.example.purchasebd.bd.Product;

public class UpdateProduct extends AppCompatActivity {

    EditText name, price;
    TextView id;
    AppDatabase db;
    Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);

        db = AppDatabase.getDbInstance(this.getApplicationContext());

        name = findViewById(R.id.updateBuyerPhone);
        price = findViewById(R.id.updateBuyerEmail);
        id = findViewById(R.id.updateBuyerName);

        product = (Product) getIntent().getSerializableExtra("product");

        loadProduct(product);

        findViewById(R.id.addPurchaseNew).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProduct(product);
                Intent intent = new Intent(UpdateProduct.this, MainActivity.class);
                intent.putExtra("name_table", "Products");
                startActivity(intent);
            }
        });
    }

    private void loadProduct(Product product){
        name.setText(product.productName);
        price.setText(String.valueOf(product.productPrice));
        id.setText(String.valueOf(product.idProduct));
    }

    private void updateProduct(final Product product){
        final String sName = name.getText().toString();
        final String sPrice = price.getText().toString();

        if (sPrice.isEmpty()){
            price.setError("Task required");
            price.requestFocus();
            return;
        }

        product.idProduct = product.idProduct;
        product.productName = sName;
        product.productPrice = Integer.parseInt(sPrice);

        db.productDao().updateProduct(product);
        finish();
    }
}