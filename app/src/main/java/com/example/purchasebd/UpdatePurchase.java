package com.example.purchasebd;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.purchasebd.bd.AppDatabase;
import com.example.purchasebd.bd.Buyer;
import com.example.purchasebd.bd.Product;
import com.example.purchasebd.bd.Purchase;

import java.util.ArrayList;
import java.util.List;

public class UpdatePurchase extends AppCompatActivity {
    EditText name, amount;
    Spinner id;
    AppDatabase db;
    Purchase purchase;
    List<Integer> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_purchase);

        db = AppDatabase.getDbInstance(this.getApplicationContext());

        name = findViewById(R.id.updateBuyerName);
        amount = findViewById(R.id.updateBuyerEmail);
        id = findViewById(R.id.updatespinner);

        List<Product> productList = db.productDao().getAllProduct();
        list = new ArrayList<Integer>(productList.size());
        for(Product c: productList) list.add(c.idProduct);

        System.out.println(list);

        purchase = (Purchase) getIntent().getSerializableExtra("purchase");

        loadPurchase(purchase);

        findViewById(R.id.buttonUpdatePurchase).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePurchase(purchase);
                Intent intent = new Intent(UpdatePurchase.this, MainActivity.class);
                intent.putExtra("name_table", "Purchases");
                startActivity(intent);
            }
        });

        findViewById(R.id.buttonDeletePurchase).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(UpdatePurchase.this);
                builder.setTitle("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        db.purchaseDao().deletePurchase(purchase);
                        Intent intent = new Intent(UpdatePurchase.this, MainActivity.class);
                        intent.putExtra("name_table", "Purchases");
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });

                AlertDialog ad = builder.create();
                ad.show();
            }
        });
    }

    private void loadPurchase(Purchase purchase){
        name.setText(purchase.buyerName);
        id.setSelection(purchase.productId);
        amount.setText(String.valueOf(purchase.productAmount));
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        id.setAdapter(adapter);
    }

    private void updatePurchase(final Purchase purchase){
        final String sName = name.getText().toString();
        final String sId = id.getSelectedItem().toString();
        final String sAmount = amount.getText().toString();

        if (sName.isEmpty()){
            name.setError("Task required");
            name.requestFocus();
            return;
        }

        if (sId.isEmpty()){
            assert false;
            ((TextView)id.getSelectedView()).setError("Error message");
            return;
        }

        if (sAmount.isEmpty()){
            amount.setError("Task required");
            amount.requestFocus();
            return;
        }

        List<Buyer> buyerList = db.buyerDao().getAllBuyer();
        List<String> buyerName = new ArrayList<String>(buyerList.size());
        for(Buyer c: buyerList) buyerName.add(c.buyerName);

        int k = 0;

        for (String name : buyerName) {
            if (name.equals(sName)) {
                k = 1;
                break;
            }
        }

        if (k == 0){
            Buyer buyer = new Buyer();
            buyer.buyerName = sName;
            buyer.buyerPhone = "";
            buyer.buyerEmail = "";
            db.buyerDao().insertBuyer(buyer);
        }

        purchase.buyerName = sName;
        purchase.productId = Integer.parseInt(sId);
        purchase.productAmount = Integer.parseInt(sAmount);

        db.purchaseDao().updatePurchase(purchase);
        finish();
    }
}