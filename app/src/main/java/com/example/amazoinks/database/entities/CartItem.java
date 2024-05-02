package com.example.amazoinks.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.amazoinks.database.AppDatabase;

import java.util.Objects;

@Entity(tableName = AppDatabase.SHOPPING_CART_TABLE)
public class CartItem {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int userID;
    private int itemID;
    private int itemQuantity;

    public CartItem(int userID, int itemID, int itemQuantity) {
        this.userID = userID;
        this.itemID = itemID;
        this.itemQuantity = itemQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem that = (CartItem) o;
        return id == that.id && userID == that.userID && itemID == that.itemID && itemQuantity == that.itemQuantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userID, itemID, itemQuantity);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "userID=" + userID +
                ", itemID=" + itemID +
                ", itemQuantity=" + itemQuantity +
                '}';
    }
}
