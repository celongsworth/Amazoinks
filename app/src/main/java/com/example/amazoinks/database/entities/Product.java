package com.example.amazoinks.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.amazoinks.database.AppDatabase;

import java.util.Objects;

@Entity(tableName = AppDatabase.PRODUCTS_TABLE)
public class Product {

    @PrimaryKey(autoGenerate = true)
    private int productId;
    private String description;
    private int quantity;
    private double price;
    private String category;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return getProductId() == product.getProductId() && getQuantity() == product.getQuantity() && Double.compare(getPrice(), product.getPrice()) == 0 && Objects.equals(getDescription(), product.getDescription()) && Objects.equals(getCategory(), product.getCategory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductId(), getDescription(), getQuantity(), getPrice(), getCategory());
    }
}
