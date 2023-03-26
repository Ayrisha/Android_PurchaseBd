package com.example.purchasebd.bd;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Product implements Serializable {

    @PrimaryKey
    public int idProduct;

    @ColumnInfo(name = "Product name")
    public String productName;

    @ColumnInfo(name = "Price per piece")
    public int productPrice;
}

