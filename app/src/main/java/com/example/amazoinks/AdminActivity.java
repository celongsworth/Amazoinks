package com.example.amazoinks;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.example.amazoinks.database.AppRepository;
import com.example.amazoinks.database.entities.User;
import com.example.amazoinks.databinding.ActivityAdminBinding;

public class AdminActivity extends AppCompatActivity {

    public static final String TAG = "DTT_AMAZOINKS";
    private static final String MAIN_ACTIVITY_USER_ID = "com.example.amazoinks.MAIN_ACTIVITY_USER_ID";


    ActivityAdminBinding binding;
    User user;
    private AppRepository repository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = AppRepository.getRepository(getApplication());

        binding.adminUsersMenuButton.setVisibility(View.VISIBLE);

        Intent intent = MainActivity.mainActivityIntentFactory(getApplicationContext(),  1);
        //Toast.makeText(this, intent.toString(), Toast.LENGTH_LONG).show();
        int userId = intent.getIntExtra(MAIN_ACTIVITY_USER_ID, 1);
        LiveData<User> userData = repository.getUserByUserId(userId);
        user = userData.getValue();


        binding.adminUsersMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = AdminUsersActivity.adminUsersIntentFactory(getApplicationContext());
                startActivity(intent1);
            }
        });
    }

    static Intent adminIntentFactory(Context context){
        return new Intent(context, AdminActivity.class);
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




}