package com.example.amazoinks;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.amazoinks.database.AppDatabase;
import com.example.amazoinks.database.ProductDAO;
import com.example.amazoinks.database.entities.Product;
import com.example.amazoinks.databinding.ActivityItemBrowsingBinding;
import com.example.amazoinks.databinding.ActivityShopItemsBinding;

import java.util.ArrayList;
import java.util.List;

public class ShopItems extends AppCompatActivity {

    private ActivityShopItemsBinding binding;
    private ProductDAO productDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        @NonNull ActivityShopItemsBinding binding = ActivityShopItemsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AppDatabase appDatabase = AppDatabase.getDatabase(this);
        productDAO = appDatabase.productDAO();

        LiveData<List<Product>> productList = productDAO.getAllProducts();
        productDAO.getAllProducts().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                if (productList != null && productList.getValue() != null) {
                    Product product = productList.getValue().get(0);
                    binding.itemName.setText(product.getItemName());
                    binding.itemPrice.setText(String.valueOf(product.getPrice()));
                    binding.itemQuant.setText(product.getQuantity());
                }
            }
        });

    }

    static Intent shopItemsIntentFactory(Context context){
        return new Intent(context, ShopItems.class);
    }
}