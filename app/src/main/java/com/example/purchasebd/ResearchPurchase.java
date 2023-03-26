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

public class ResearchPurchase extends AppCompatActivity {
    EditText id;
    TextView text;
    String sId;
    List<Purchase> purchaseList;
    int k = 0;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research_purchase);

        db = AppDatabase.getDbInstance(this.getApplicationContext());
        purchaseList = db.purchaseDao().getPurchase();

        text = findViewById(R.id.textShow);
        id = findViewById(R.id.editTextResearchPurchase);
        findViewById(R.id.buttonResearch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sId = id.getText().toString();
                if (sId.isEmpty()){
                    Intent intent = new Intent(ResearchPurchase.this, MainActivity.class);
                    intent.putExtra("name_table", "Purchases");
                    startActivity(intent);
                }
                else{
                    researchPurchase();
                }
            }
        });
    }

    private void researchPurchase(){
        for (int i = 0; i < purchaseList.size(); i++){
            if (purchaseList.get(i).idPurchase == Integer.parseInt(sId)){
                k = 1;
                break;
            }
        }
        if (k == 0){
            Toast.makeText(this, "Not found.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ResearchPurchase.this, MainActivity.class);
            intent.putExtra("name_table", "Purchases");
            startActivity(intent);
        }
        else {
            Purchase purchase = db.purchaseDao().getId(Integer.parseInt(sId));

            Intent intent = new Intent(ResearchPurchase.this, MainActivity.class);
            intent.putExtra("research_purchase", (Serializable) purchase);
            startActivity(intent);
        }
    }
}