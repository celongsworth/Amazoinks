package com.example.amazoinks.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.amazoinks.database.entities.Product;
import com.example.amazoinks.database.entities.User;

import java.util.List;

@Dao
public interface ProductDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Product... product);

    @Delete
    void delete(Product product);

    @Query("Select * from " + AppDatabase.PRODUCT_TABLE)
    LiveData<List<Product>> getAllProducts();

    @Query("Select * from " + AppDatabase.PRODUCT_TABLE + " where id == :productID")
    LiveData<Product> getProductByID(int productID);

    @Query("Select * from " + AppDatabase.PRODUCT_TABLE + " where itemName == :itemName")
    LiveData<Product> getItemByName(String itemName);



}
