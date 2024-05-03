package com.example.amazoinks.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.example.amazoinks.R;
import com.example.amazoinks.database.AppRepository;
import com.example.amazoinks.database.entities.Product;
import com.example.amazoinks.databinding.ActivityAdminInventoryBinding;

import java.util.List;

public class AdminInventoryActivity extends AppCompatActivity {

    ActivityAdminInventoryBinding binding;
    AppRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminInventoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = AppRepository.getRepository(getApplication());

        binding.itemsDisplayTextView.setMovementMethod(new ScrollingMovementMethod());
        listAllProducts();

        binding.addInventoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.inventoryNameInputEditText.getText().toString();
                String description = binding.inventoryDescriptionInputEditText.getText().toString();
                double price = Double.parseDouble(binding.inventoryPriceInputEditText.getText().toString());
                int quantity = Integer.parseInt(binding.inventoryQuantityInputEditText.getText().toString());
                String category = binding.inventoryCategoryInputEditText.getText().toString();

                Product product = new Product(name, description, quantity, price, category);
                repository.insertProduct(product);
                listAllProducts();
                binding.inventoryNameInputEditText.setText("");
                binding.inventoryDescriptionInputEditText.setText("");
                binding.inventoryPriceInputEditText.setText("");
                binding.inventoryQuantityInputEditText.setText("");
                binding.inventoryCategoryInputEditText.setText("");
            }
        });

    }

    static Intent adminInventoryIntentFactory(Context context, int userId){
        Intent intent = new Intent(context, AdminInventoryActivity.class);
        intent.putExtra("USER_ID", userId);
        return intent;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.logoutMenuItem);
        item.setVisible(true);

        item.setTitle("HOME");

        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem item) {

                Intent intent =  MainActivity.mainActivityIntentFactory(getApplicationContext(), 1);
                startActivity(intent);
                return false;
            }
        });
        return true;
    }


    public void listAllProducts(){
        LiveData<List<Product>> productsObserver = repository.getAllProducts();
        productsObserver.observe(this, this::updateDisplay);
    }

    public void updateDisplay(List<Product> products){
        StringBuilder sb = new StringBuilder();
        for(Product product : products){
            sb.append(product);
        }
        binding.itemsDisplayTextView.setText(sb.toString());
    }
}