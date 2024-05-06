package com.example.amazoinks.database.entities;

import androidx.annotation.NonNull;

public class CartViewItem {
    private String itemName;
    private double price;
    private String category;
    private int itemQuantity;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    @NonNull
    @Override
    public String toString() {
        return "{" +
                "itemName='" + itemName + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", itemQuantity=" + itemQuantity +
                '}';
    }
}
