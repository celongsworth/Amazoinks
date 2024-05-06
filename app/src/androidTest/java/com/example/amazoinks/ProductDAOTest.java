package com.example.amazoinks;

import static org.junit.Assert.assertEquals;

import android.app.Instrumentation;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.example.amazoinks.database.AppDatabase;
import com.example.amazoinks.database.ProductDAO;
import com.example.amazoinks.database.entities.Product;

import org.junit.After;
import org.junit.Before;

import java.io.IOException;
import java.util.List;

public class ProductDAOTest {
    private AppDatabase db;
    private ProductDAO productDAO;
    private Product product;

    @Before
    public void createDB() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        productDAO = db.productDAO();

        product = new Product("Dinosaur", "A cute green stegosaurus",15,
                15.99, "Prehistoric");
    }

    @After
    public void closeDB() throws IOException {
        db.close();
    }

    public void insertAndQuery() throws Exception {
        productDAO.insert(product);
        LiveData<List<Product>> allProductsQuery = productDAO.getAllProducts();
        List<Product> allProducts = allProductsQuery.getValue();
        assert allProducts != null;
        assertEquals(allProducts.size(), 1);

//        LiveData<Product> queryRes = productDAO.getProductByID(product.getId());
//        Product resultProd = queryRes.getValue();
//        assertEquals(product, resultProd);

    }
}
