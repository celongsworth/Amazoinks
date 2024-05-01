package com.example.amazoinks.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.amazoinks.database.entities.Product;

import java.util.List;

@Dao
public interface ProductDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Product... product);

    @Query("Select * from " + AppDatabase.PRODUCT_TABLE)
    List<Product> getAllProducts();


}
