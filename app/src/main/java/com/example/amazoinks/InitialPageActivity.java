package com.example.amazoinks;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.amazoinks.databinding.ActivityInitialPageBinding;

public class InitialPageActivity extends AppCompatActivity {

    private ActivityInitialPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInitialPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = LoginActivity.loginIntentFactory(getApplicationContext());
                startActivity(intent);
            }
        });

        binding.signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = SignUpActivity.signUpIntentFactory(getApplicationContext());
                startActivity(intent);
            }
        });

    }

    static Intent initialPageIntentFactory(Context context){
        return new Intent(context, InitialPageActivity.class);
    }
}