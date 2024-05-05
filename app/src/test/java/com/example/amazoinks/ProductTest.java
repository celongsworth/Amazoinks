package com.example.amazoinks;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import com.example.amazoinks.database.entities.Product;

import org.junit.Before;
import org.junit.Test;

public class ProductTest {
    Product product;
    Product product2;
    Product product3;

    private String itemName;
    private String description;
    private int quantity;
    private double price;
    private String category;

    private String itemName2;
    private String description2;
    private int quantity2;
    private double price2;
    private String category2;

    @Before
    public void setUp() {
        itemName = "Fish";
        description = "The goldest of fish";
        quantity = 5;
        price = 13.37;
        category = "Aquatic";

        itemName2 = "Dinosaur";
        description2 = "A cute green stegosaurus";
        quantity2 = 15;
        price2 = 15.99;
        category2 = "Prehistoric";

        product = new Product(itemName, description, quantity, price, category);
        product2 = new Product(itemName2, description2, quantity2, price2, category2);
        product3 = new Product(itemName, description, quantity, price, category);
    }

    @Test
    public void getItemName() {
        assertEquals(product.getItemName(), itemName);
        assertNotEquals(product.getItemName(), product2.getItemName());
        assertEquals(product.getItemName(), product3.getItemName());
    }

    @Test
    public void setItemName() {
        product.setItemName(itemName2);
        assertNotEquals(product.getItemName(), itemName);
        assertEquals(product.getItemName(), itemName2);
    }

    @Test
    public void getDescription() {
        assertEquals(product.getDescription(), description);
        assertNotEquals(product.getDescription(), product2.getDescription());
        assertEquals(product.getDescription(), product3.getDescription());
    }

    @Test
    public void setDescription() {
        product.setDescription(description2);
        assertNotEquals(product.getDescription(), description);
        assertEquals(product.getDescription(), description2);
    }

    @Test
    public void getQuantity() {
        assertEquals(product.getQuantity(), quantity);
        assertNotEquals(product.getQuantity(), product2.getQuantity());
        assertEquals(product.getQuantity(), product3.getQuantity());
    }

    @Test
    public void setQuantity() {
        product.setQuantity(quantity2);
        assertNotEquals(product.getQuantity(), quantity);
        assertEquals(product.getQuantity(), quantity2);
    }

    @Test
    public void getPrice() {
        assertEquals(product.getPrice(), price, 0.01);
        assertNotEquals(product.getPrice(), product2.getPrice());
        assertEquals(product.getPrice(), product3.getPrice(), 0.01);
    }

    @Test
    public void setPrice() {
        product.setPrice(price2);
        assertNotEquals(product.getPrice(), price);
        assertEquals(product.getPrice(), price2, 0.01);
    }

    @Test
    public void getCategory() {
        assertEquals(product.getCategory(), category);
        assertNotEquals(product.getCategory(), product2.getCategory());
        assertEquals(product.getCategory(), product3.getCategory());
    }

    @Test
    public void setCategory() {
        product.setCategory(category2);
        assertNotEquals(product.getCategory(), category);
        assertEquals(product.getCategory(), category2);
    }

}