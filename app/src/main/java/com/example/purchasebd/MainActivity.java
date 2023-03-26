package com.example.purchasebd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.purchasebd.adapter.BuyerAdapter;
import com.example.purchasebd.adapter.ProductAdapter;
import com.example.purchasebd.adapter.PurchaseAdapter;
import com.example.purchasebd.adapter.StatisticAdapter;
import com.example.purchasebd.bd.AppDatabase;
import com.example.purchasebd.bd.Buyer;
import com.example.purchasebd.bd.Product;
import com.example.purchasebd.bd.Purchase;
import com.example.purchasebd.bd.PurchaseWithProduct;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = AppDatabase.getDbInstance(this.getApplicationContext());

        int k = 1;

        List<Product> productList = db.productDao().getAllProduct();
        for (int i = 0; i < productList.size(); i++){
            if (productList.get(i).idProduct == 1){
                k = 0;
                break;
            }
        }

        if (k == 1){
            Product pr = new Product();
            pr.idProduct = 1;
            pr.productName = "table";
            pr.productPrice = 6000;
            db.productDao().insertProduct(pr);
        }


        FloatingActionButton researchButton = findViewById(R.id.floatingActionButtonReserch);
        FloatingActionButton showButton = findViewById(R.id.floatingActionButtonShow);
        FloatingActionButton addButton = findViewById(R.id.floatingActionButtonAdd);

        Bundle argument = getIntent().getExtras();

        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChooseTable.class);
                startActivity(intent);
            }
        });
        if (argument != null) {
            if (argument.get("name_table") != null) {
                if (argument.get("name_table").toString().equals("Statistic for product")) {
                    loadStatistic();
                }
                if (argument.get("name_table").toString().equals("Purchases")) {
                    loadPurchaseList();
                }
                if (argument.get("name_table").toString().equals("Products")) {
                    loadProductList();
                }
                if (argument.get("name_table").toString().equals("Buyers")) {
                    loadBuyerList();
                }
            }
            if (argument.getSerializable("research_purchase") != null ){
                    Purchase pr = (Purchase) argument.getSerializable("research_purchase");
                    System.out.println(pr);
                    loadPurchase(pr);
            }
            if (argument.getSerializable("research_product") != null ){
                Product pr = (Product) argument.getSerializable("research_product");
                System.out.println(pr);
                loadProduct(pr);
            }
            if (argument.getSerializable("research_buyer") != null ){
                Buyer pr = (Buyer) argument.getSerializable("research_buyer");
                System.out.println(pr);
                loadBuyer(pr);
            }
        }
        else{
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "Please choose table!", Toast.LENGTH_LONG).show();
                }
            });
            researchButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "Please choose table!", Toast.LENGTH_LONG).show();
                }
            });
        }

        if (argument != null) {
            if (argument.get("name_table") != null) {
                if (argument.get("name_table").toString().equals("Purchases")) {
                    addButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(MainActivity.this, AddPurchase.class);
                            startActivity(intent);
                        }
                    });
                    researchButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(MainActivity.this, ResearchPurchase.class);
                            startActivity(intent);
                        }
                    });
                }
                if (argument.get("name_table").toString().equals("Products")) {
                    addButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(MainActivity.this, AddProduct.class);
                            startActivity(intent);
                        }
                    });
                    researchButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(MainActivity.this, ResearchProduct.class);
                            startActivity(intent);
                        }
                    });
                }
                if (argument.get("name_table").toString().equals("Buyers")) {
                    addButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(MainActivity.this, AddBuyer.class);
                            startActivity(intent);
                        }
                    });
                    researchButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(MainActivity.this, ResearchBuyer.class);
                            startActivity(intent);
                        }
                    });
                }
            }
        }
    }

    private void loadPurchaseList(){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        PurchaseAdapter purchaseListAdapter = new PurchaseAdapter(this);
        recyclerView.setAdapter(purchaseListAdapter);

        List<Purchase> purchaseList = db.purchaseDao().getPurchase();
        purchaseListAdapter.setPurchaseList(purchaseList);
    }

    private void loadProductList(){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        ProductAdapter productListAdapter = new ProductAdapter(this);
        recyclerView.setAdapter(productListAdapter);

        List<Product> productList = db.productDao().getAllProduct();
        productListAdapter.setProductsList(productList);
    }

    private void loadBuyerList(){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        BuyerAdapter buyerAdapter = new BuyerAdapter(this);
        recyclerView.setAdapter(buyerAdapter);

        List<Buyer> buyerList = db.buyerDao().getAllBuyer();
        buyerAdapter.setBuyersList(buyerList);
    }

    private void loadStatistic(){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        StatisticAdapter statistic = new StatisticAdapter(this);
        recyclerView.setAdapter(statistic);

        List<PurchaseWithProduct> list = db.purchaseDao().getStatistic();
        System.out.println(list);
        statistic.setStatistic(list);
    }

    private void loadPurchase(Purchase pr){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        PurchaseAdapter purchaseListAdapter = new PurchaseAdapter(this);
        recyclerView.setAdapter(purchaseListAdapter);
        List<Purchase> list = new ArrayList<Purchase>(Collections.singleton(pr));
        purchaseListAdapter.setPurchaseList(list);
    }

    private void loadProduct(Product pr){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        ProductAdapter productListAdapter = new ProductAdapter(this);
        recyclerView.setAdapter(productListAdapter);
        List<Product> list = new ArrayList<Product>(Collections.singleton(pr));
        productListAdapter.setProductsList(list);
    }

    private void loadBuyer(Buyer pr){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        BuyerAdapter buyerListAdapter = new BuyerAdapter(this);
        recyclerView.setAdapter(buyerListAdapter);
        List<Buyer> list = new ArrayList<Buyer>(Collections.singleton(pr));
        buyerListAdapter.setBuyersList(list);
    }
}