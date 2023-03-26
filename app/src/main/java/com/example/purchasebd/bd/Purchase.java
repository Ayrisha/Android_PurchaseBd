package com.example.purchasebd.bd;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Purchase implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int idPurchase;

    @ColumnInfo(name = "Buyer")
    public String buyerName;

    @ColumnInfo(name = "ProductId")
    public int productId;

    @ColumnInfo(name = "Amount of product")
    public int productAmount;
}
