package com.example.amazoinks.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.amazoinks.database.entities.CartItem;
import com.example.amazoinks.database.entities.CartViewItem;

import java.util.List;

@Dao
public interface CartDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CartItem... carts);

    @Query("Select * from " + AppDatabase.SHOPPING_CART_TABLE)
    LiveData<List<CartItem>> getAllCartItems();

    @Query("Select itemName, price, category, " + AppDatabase.SHOPPING_CART_TABLE + ".itemQuantity from " + AppDatabase.PRODUCT_TABLE + " inner join " + AppDatabase.SHOPPING_CART_TABLE + " on " + AppDatabase.PRODUCT_TABLE + ".id = " + AppDatabase.SHOPPING_CART_TABLE + ".itemID where " + AppDatabase.SHOPPING_CART_TABLE + ".userID == :userID")
    LiveData<List<CartViewItem>> getCartItemsForUser(int userID);

}
