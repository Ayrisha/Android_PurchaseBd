package com.example.purchasebd.bd;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BuyerDao {

    @Query("SELECT * FROM buyer")
    List<Buyer> getAllBuyer();

    @Query("SELECT * FROM Buyer WHERE buyerName = :name")
    Buyer getName(String name);

    @Insert
    void insertBuyer(Buyer... buyers);

    @Update
    void updateBuyer(Buyer... buyers);

    @Delete
    void deleteBuyer(Buyer... buyers);
}
