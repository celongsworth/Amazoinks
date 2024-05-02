package com.example.amazoinks.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.example.amazoinks.database.AppRepository;
import com.example.amazoinks.database.entities.User;
import com.example.amazoinks.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    private AppRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = AppRepository.getRepository(getApplication());

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyUser();
            }
        });

    }


    private void verifyUser(){
        String username = binding.usernameLoginEditText.getText().toString();

        if(username.isEmpty()){
            toastMaker("Username may not be blank.");
            return;
        }
        LiveData<User> userObserver = repository.getUserByUserName(username);
        userObserver.observe(this, user -> {
            //Toast.makeText(this, user.toString(), Toast.LENGTH_LONG).show();
            if(user != null){
                String password =  binding.passwordLoginEditText.getText().toString();
                if(password.equals(user.getPassword())){
                    SharedPreferences sharedPreferences = getApplicationContext()
                            .getSharedPreferences(MainActivity.SHARED_PREFERENCE_USERID_KEY,
                            Context.MODE_PRIVATE);
                    SharedPreferences.Editor sharedPrefEditor = sharedPreferences.edit();
                    sharedPrefEditor.putInt(MainActivity.SHARED_PREFERENCE_USERID_KEY,user.getId());
                    sharedPrefEditor.apply();
                    startActivity(MainActivity.mainActivityIntentFactory(getApplicationContext(), user.getId()));
                    MainActivity.mainActivityIntentFactory(getApplicationContext(), user.getId());

                } else {
                    toastMaker("Invalid password.");
                    //binding.passwordLoginEditText.setSelection(0);
                }
            } else {
                //MainActivity.mainActivityIntentFactory(getApplicationContext(), 0);
                toastMaker(String.format("%s is not a valid username", username));
                //binding.usernameLoginEditText.setSelection(0);
            }
        });
    }

    private void toastMaker(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    static Intent loginIntentFactory(Context context){
        return new Intent(context, LoginActivity.class);
    }

}