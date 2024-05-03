package com.example.amazoinks.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.amazoinks.database.entities.CartItem;

import java.util.List;

@Dao
public interface CartDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CartItem... carts);

    @Query("Select * from " + AppDatabase.SHOPPING_CART_TABLE)
    LiveData<List<CartItem>> getAllCartItems();

    @Query("Select * from " + AppDatabase.SHOPPING_CART_TABLE + " where userID == :userID")
    LiveData<List<CartItem>> getCartItemsForUser(int userID);
    // should probably return map that holds product and product quantity



}
