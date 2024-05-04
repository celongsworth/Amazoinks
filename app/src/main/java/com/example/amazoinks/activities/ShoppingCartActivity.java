package com.example.amazoinks.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amazoinks.R;
import com.example.amazoinks.database.AppDatabase;
import com.example.amazoinks.database.AppRepository;
import com.example.amazoinks.database.entities.CartItem;
import com.example.amazoinks.database.entities.CartViewItem;

import java.util.List;

public class ShoppingCartActivity extends AppCompatActivity {

    private AppRepository repository;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        RecyclerView recyclerView = findViewById(R.id.mRecyclerView);

        repository = AppRepository.getRepository(getApplication());
        AppDatabase appDatabase = AppDatabase.getDatabase(this);

        LiveData<List<CartViewItem>> cartItemListObserver = repository.getCartItemsForUser(userId);
        cartItemListObserver.observe(this, items -> {
            if (items != null && !items.isEmpty()) {
                CartItem_Recycler adapter = new CartItem_Recycler(this, items, repository);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
            }
        });

        userId = getIntent().getIntExtra("USER_ID", -1);
        Toast.makeText(this, "userId: " + userId, Toast.LENGTH_LONG).show();
    }

    static Intent shoppingCartIntentFactory(Context context, int userId){
        Intent intent = new Intent(context, ShoppingCartActivity.class);
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
                Toast.makeText(ShoppingCartActivity.this, userId, Toast.LENGTH_SHORT).show();
                Intent intent =  MainActivity.mainActivityIntentFactory(getApplicationContext(), userId);
                Toast.makeText(ShoppingCartActivity.this, userId, Toast.LENGTH_SHORT).show();
                startActivity(intent);
                return false;
            }
        });
        return true;
    }
}