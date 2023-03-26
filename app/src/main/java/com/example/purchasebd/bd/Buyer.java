package com.example.purchasebd.bd;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Buyer implements Serializable {

    @PrimaryKey
    @NonNull
    public String buyerName;

    @ColumnInfo(name = "Phone")
    public String buyerPhone;

    @ColumnInfo(name = "E-mail")
    public String buyerEmail;
}
