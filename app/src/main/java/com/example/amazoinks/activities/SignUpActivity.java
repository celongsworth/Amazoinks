package com.example.amazoinks.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.example.amazoinks.database.AppRepository;
import com.example.amazoinks.database.entities.User;
import com.example.amazoinks.databinding.ActivitySignUpBinding;

import java.util.List;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;

    private AppRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = AppRepository.getRepository(getApplication());

        binding.signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewUser();
            }
        });
    }

    /*
    LiveData<User> userObserver = repository.getUserByUserName(username);
    userObserver.observe(this, user -> {});
     */
    private void createNewUser(){
        String username = binding.usernameSignupEditText.getText().toString();
        String password = binding.passwordSignupEditText.getText().toString();

        LiveData<List<User>> usersObserver = repository.getAllUsers();
        usersObserver.observe(this, users -> {
            for (User user : users){
                if(username.equals(user.getUsername())){
                    Toast.makeText(this, "Username already taken! Choose Again.", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            User newUser = new User(username, password);
            repository.insertUser(newUser);
            Toast.makeText(this, "New user added", Toast.LENGTH_SHORT).show();
            Intent intent = LoginActivity.loginIntentFactory(getApplicationContext());
            startActivity(intent);

        });

    }

    static Intent signUpIntentFactory(Context context){
        return new Intent(context, SignUpActivity.class);
    }
}