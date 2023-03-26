package com.example.purchasebd.bd;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Embedded;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
import java.util.Map;

@Dao
public interface PurchaseDao {

    @Query("SELECT * FROM Purchase")
    List<Purchase> getPurchase();

    @Query("SELECT * FROM Purchase WHERE idPurchase = :id")
    Purchase getId(int id);

    @Query("SELECT * FROM Purchase JOIN product ON Product.idProduct= Purchase.productId ")
    List<PurchaseWithProduct> getStatistic();

    @Insert
    void insertPurchase(Purchase... purchases);

    @Update
    void updatePurchase(Purchase... purchases);

    @Delete
    void deletePurchase(Purchase... purchases);
}

