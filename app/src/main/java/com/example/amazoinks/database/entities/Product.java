package com.example.amazoinks.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.amazoinks.database.AppDatabase;

import java.util.Objects;

@Entity(tableName = AppDatabase.PRODUCT_TABLE)

public class Product {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String itemName;
    private String description;
    private int quantity;
    private double price;
    private String category;

    public Product(String itemName, String description, int quantity, double price, String category) {
        this.itemName = itemName;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && quantity == product.quantity && Double.compare(price, product.price) == 0 && Objects.equals(description, product.description) && Objects.equals(category, product.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, quantity, price, category);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    @Override
    public String toString() {
        return "{ id=" + id +
                "| name='" + itemName + '\'' +
                "| description='" + description + '\'' +
                "| quantity=" + quantity +
                "| price=" + price +
                "| category='" + category + '\'' +
                '}' + "\n";
    }
}
