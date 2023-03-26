package com.example.purchasebd.bd;

import android.content.Context;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Purchase.class, Product.class, Buyer.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract PurchaseDao purchaseDao();

    public abstract ProductDao productDao();

    public abstract BuyerDao buyerDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDbInstance(Context context){

        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "MyPurchases7").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
}
