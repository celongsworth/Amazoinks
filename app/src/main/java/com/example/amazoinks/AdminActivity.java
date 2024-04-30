package com.example.amazoinks;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.amazoinks.database.AppRepository;
import com.example.amazoinks.databinding.ActivityAdminBinding;

public class AdminActivity extends AppCompatActivity {

    ActivityAdminBinding binding;

    private AppRepository repository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = AppRepository.getRepository(getApplication());

        binding.adminUsersMenuButton.setVisibility(View.VISIBLE);

    }

    static Intent adminIntentFactory(Context context){
        return new Intent(context, AdminActivity.class);
    }
}