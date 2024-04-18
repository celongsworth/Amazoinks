package com.example.amazoinks;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.amazoinks.database.AppRepository;
import com.example.amazoinks.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    public static final String TAG = "DTT_AMAZOINKS";

    private ActivityMainBinding binding;
    private AppRepository repository;

    int loggedInUser = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = AppRepository.getRepository(getApplication());
        repository.invokeDB();
        setContentView(R.layout.activity_main);

    }
}