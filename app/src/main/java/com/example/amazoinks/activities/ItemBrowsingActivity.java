package com.example.amazoinks.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.amazoinks.databinding.ActivityItemBrowsingBinding;
import com.example.amazoinks.databinding.ActivityLoginBinding;

public class ItemBrowsingActivity extends AppCompatActivity {
    private ActivityItemBrowsingBinding binding;
    private int userID = getIntent().getIntExtra("USER_ID", 0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityItemBrowsingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the user id
                // get the current itemID
                // add item to shopping cart with current userId and item selected

            }
        });

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = MainActivity.mainActivityIntentFactory(getApplicationContext(), getIntent().getIntExtra("USER_ID", 0));
                startActivity(intent);
            }
        });
    }

    static Intent itemBrowsingIntentFactory(Context context, int userID){
        Intent intent = new Intent(context, ItemBrowsingActivity.class);
        intent.putExtra("USER_ID", userID);
        return intent;
    }
}