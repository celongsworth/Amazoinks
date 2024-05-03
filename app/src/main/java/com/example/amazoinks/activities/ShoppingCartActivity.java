package com.example.amazoinks.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amazoinks.R;
import com.example.amazoinks.database.AppDatabase;
import com.example.amazoinks.database.AppRepository;
import com.example.amazoinks.database.entities.CartItem;
import com.example.amazoinks.database.entities.Product;

import java.util.List;

public class ShoppingCartActivity extends AppCompatActivity {

    private AppRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        RecyclerView recyclerView = findViewById(R.id.mRecyclerView);

        repository = AppRepository.getRepository(getApplication());
        AppDatabase appDatabase = AppDatabase.getDatabase(this);

        LiveData<List<CartItem>> cartItemListObserver = repository.getAllCartItems();
        cartItemListObserver.observe(this, items -> {
            if (items != null && !items.isEmpty()) {
                CartItem_Recycler adapter = new CartItem_Recycler(this, items, repository);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
            }
        });

    }

    static Intent shoppingCartIntentFactory(Context context, int userId){
        Intent intent = new Intent(context, ShoppingCartActivity.class);
        intent.putExtra("USER_ID", userId);
        return intent;
    }
}