package com.example.purchasebd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.purchasebd.bd.AppDatabase;
import com.example.purchasebd.bd.Buyer;
import com.example.purchasebd.bd.Product;

import java.io.Serializable;
import java.util.List;

public class ResearchBuyer extends AppCompatActivity {

    EditText name;
    TextView text;
    String sName;
    List<Buyer> buyerList;
    int k = 0;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research_buyer);

        db = AppDatabase.getDbInstance(this.getApplicationContext());
        buyerList = db.buyerDao().getAllBuyer();

        text = findViewById(R.id.textShow);
        name = findViewById(R.id.editTextResearchPurchase);
        findViewById(R.id.buttonResearch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sName = name.getText().toString();
                if (sName.isEmpty()){
                    Intent intent = new Intent(ResearchBuyer.this, MainActivity.class);
                    intent.putExtra("name_table", "Buyers");
                    startActivity(intent);
                }
                else{
                    researchBuyer();
                }
            }
        });
    }

    private void researchBuyer(){
        for (int i = 0; i < buyerList.size(); i++){
            if (buyerList.get(i).buyerName.equals(sName)){
                k = 1;
                break;
            }
        }
        if (k == 0){
            Toast.makeText(this, "Not found.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ResearchBuyer.this, MainActivity.class);
            intent.putExtra("name_table", "Buyers");
            startActivity(intent);
        }
        else {
            Buyer buyer = db.buyerDao().getName(sName);

            Intent intent = new Intent(ResearchBuyer.this, MainActivity.class);
            intent.putExtra("research_buyer", (Serializable) buyer);
            startActivity(intent);
        }
    }
}