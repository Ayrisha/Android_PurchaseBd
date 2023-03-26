package com.example.purchasebd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.purchasebd.bd.AppDatabase;
import com.example.purchasebd.bd.Buyer;

import java.util.List;

public class AddBuyer extends AppCompatActivity {

    EditText nameBuyer, phone, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_buyer);

        nameBuyer = findViewById(R.id.updateBuyerName);
        phone = findViewById(R.id.updateBuyerPhone);
        email = findViewById(R.id.updateBuyerEmail);

        Button add = findViewById(R.id.addPurchaseNew);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBuyer();
                Intent intent = new Intent(AddBuyer.this, MainActivity.class);
                intent.putExtra("name_table", "Buyers");
                startActivity(intent);
            }
        });
    }

    private void saveBuyer(){
        String sName = nameBuyer.getText().toString();
        String sPhone = phone.getText().toString();
        String sEmail = email.getText().toString();

        if (sName.isEmpty()){
            nameBuyer.setError("Task required");
            nameBuyer.requestFocus();
            return;
        }

        if (sPhone.isEmpty()){
            phone.setError("Task required");
            phone.requestFocus();
            return;
        }

        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        List<Buyer> buyerList = db.buyerDao().getAllBuyer();

        if (buyerList.size() != 0) {
            for (int i = 0; i < buyerList.size(); i++) {
                if (sName.equals(buyerList.get(i).buyerName)) {
                    nameBuyer.setError("There is such a name");
                    nameBuyer.requestFocus();
                    return;
                }
            }
        }

        Buyer buyer = new Buyer();
        buyer.buyerName = sName;
        buyer.buyerPhone = sPhone;
        buyer.buyerEmail = sEmail;
        db.buyerDao().insertBuyer(buyer);
        finish();
    }
}