package com.example.purchasebd.bd;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
import java.util.Map;

@Dao
public interface ProductDao {

    @Query("SELECT * FROM product")
    List<Product> getAllProduct();

    @Query("SELECT * FROM Product WHERE idProduct = :id")
    Product getId(int id);

    @Insert
    void insertProduct(Product... products);

    @Update
    void updateProduct(Product... products);

    @Delete
    void deleteProduct(Product... products);
}
