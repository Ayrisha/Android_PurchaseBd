package com.example.purchasebd;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.purchasebd.bd.AppDatabase;
import com.example.purchasebd.bd.Buyer;


public class UpdateBuyer extends AppCompatActivity {

    EditText phone, email;
    TextView name;
    AppDatabase db;
    Buyer buyer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_buyer);

        db = AppDatabase.getDbInstance(this.getApplicationContext());

        phone = findViewById(R.id.updateBuyerPhone);
        email = findViewById(R.id.updateBuyerEmail);
        name = findViewById(R.id.updateBuyerName);

        buyer = (Buyer) getIntent().getSerializableExtra("buyer");
        System.out.println(buyer.buyerName);

        loadBuyer(buyer);

        findViewById(R.id.buttonUpdateBuyer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateBuyer(buyer);
                Intent intent = new Intent(UpdateBuyer.this, MainActivity.class);
                intent.putExtra("name_table", "Buyers");
                startActivity(intent);
            }
        });
    }

    private void loadBuyer(Buyer buyer){
        name.setText(buyer.buyerName);
        phone.setText(buyer.buyerPhone);
        email.setText(buyer.buyerEmail);
    }

    private void updateBuyer(final Buyer buyer){
        final String sName = name.getText().toString();
        final String sPhone = phone.getText().toString();
        final String sEmail= email.getText().toString();

        buyer.buyerName = buyer.buyerName;
        buyer.buyerPhone = sPhone;
        buyer.buyerEmail = sEmail;

        db.buyerDao().updateBuyer(buyer);
        finish();
    }
}