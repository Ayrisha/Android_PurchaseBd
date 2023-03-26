package com.example.purchasebd.bd;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class PurchaseWithProduct {
    @Embedded
    public Purchase purchase;

    @Relation(parentColumn = "ProductId", entityColumn = "idProduct")
    public List<Product> statisticProduct;
}
