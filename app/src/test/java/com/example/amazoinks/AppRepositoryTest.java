package com.example.amazoinks;

import static org.junit.Assert.assertEquals;

import android.app.Application;

import com.example.amazoinks.database.AppRepository;
import com.example.amazoinks.database.CartDAO;
import com.example.amazoinks.database.ProductDAO;
import com.example.amazoinks.database.UserDAO;
import com.example.amazoinks.database.entities.Product;

import org.junit.Before;
import org.junit.Test;

public class AppRepositoryTest {
    private AppRepository repository;
    ProductDAO productDAO;
    Product fish, shirt;

    @Before
    public void setup() {
//        repository = AppRepository.getRepository()
        fish = new Product("Fish", "goldfish, it swim",
                5, 20.20, "Aquatic");
        shirt = new Product("Shirt", "A fun colorful short sleeve t-shirt",
                7, 19.99, "Clothing");
    }

    @Test
    public void testInsertProduct() {
        productDAO.insert(fish);

    }

//    @Test
//    public void testGetProductByID() {
//        productDAO.getProductByID(1);
//        assertEquals()
//    }
}
