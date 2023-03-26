package com.example.purchasebd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.purchasebd.bd.AppDatabase;
import com.example.purchasebd.bd.Buyer;
import com.example.purchasebd.bd.Product;
import com.example.purchasebd.bd.Purchase;

import java.util.ArrayList;
import java.util.List;

public class AddPurchase extends AppCompatActivity {
    EditText name, amount;
    Spinner id;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = AppDatabase.getDbInstance(this.getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_purchase);

        name = findViewById(R.id.updateBuyerName);
        amount = findViewById(R.id.updateBuyerEmail);
        id = findViewById(R.id.updatespinner);

        List<Product> productList = db.productDao().getAllProduct();
        List<Integer> list = new ArrayList<Integer>(productList.size());
        for(Product c: productList) list.add(c.idProduct);

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        id.setAdapter(adapter);


        Button add = findViewById(R.id.addPurchaseNew);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePurchase();
                Intent intent = new Intent(AddPurchase.this, MainActivity.class);
                intent.putExtra("name_table", "Purchases");
                startActivity(intent);
            }
        });
    }

    private void savePurchase(){
        String sName = name.getText().toString();
        String sAmount = amount.getText().toString();
        if (id.getSelectedItem() == null){
            assert false;
            ((TextView)id.getSelectedView()).setError("Error message");
            return;
        }

        String item = id.getSelectedItem().toString();;

        if (sName.isEmpty()){
            name.setError("Task required");
            name.requestFocus();
            return;
        }

        if (item.isEmpty()){
            assert false;
            ((TextView)id.getSelectedView()).setError("Error message");
            return;
        }

        if (sAmount.isEmpty()){
            amount.setError("Task required");
            amount.requestFocus();
            return;
        }

        int sAmountInt = Integer.parseInt(sAmount);
        int itemInt = Integer.parseInt(item);

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
            buyer.buyerPhone = "-";
            buyer.buyerEmail = "-";
            db.buyerDao().insertBuyer(buyer);
        }

        Purchase purchase = new Purchase();

        purchase.buyerName = sName;
        purchase.productId = itemInt;
        purchase.productAmount = sAmountInt;

        db.purchaseDao().insertPurchase(purchase);
        finish();
    }
}