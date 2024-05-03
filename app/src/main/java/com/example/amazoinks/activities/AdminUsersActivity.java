package com.example.amazoinks.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.example.amazoinks.R;
import com.example.amazoinks.database.AppRepository;
import com.example.amazoinks.database.entities.User;
import com.example.amazoinks.databinding.ActivityAdminUsersBinding;

import java.util.List;

public class AdminUsersActivity extends AppCompatActivity {

    ActivityAdminUsersBinding binding;

    private AppRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminUsersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = AppRepository.getRepository(getApplication());

        binding.usersDisplayTextView.setMovementMethod(new ScrollingMovementMethod());
        listAllUsers();

        binding.userDeletionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = binding.userDeletionInputEditText.getText().toString();
                //Toast.makeText(AdminUsersActivity.this, username, Toast.LENGTH_SHORT).show();
                deleteUser(username);
                Toast.makeText(AdminUsersActivity.this, "Deleted " + username, Toast.LENGTH_SHORT).show();
                listAllUsers();
            }
        });

    }

    static Intent adminUsersIntentFactory(Context context, int userId){
        return new Intent(context, AdminUsersActivity.class);
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

    public void listAllUsers(){
        LiveData<List<User>> usersObserver =  repository.getAllUsers();
        usersObserver.observe(this, this::updateDisplay);
    }

    private void updateDisplay(List<User> users){
        StringBuilder sb = new StringBuilder();
        for(User user : users){
            sb.append(user);
        }
        binding.usersDisplayTextView.setText(sb.toString());
    }

    private void deleteUser(String username){
        repository.deleteUserByUsername(username);
    }
}
