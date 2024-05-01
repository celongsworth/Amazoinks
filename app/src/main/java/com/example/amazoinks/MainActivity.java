package com.example.amazoinks;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.example.amazoinks.database.AppDatabase;
import com.example.amazoinks.database.AppRepository;
import com.example.amazoinks.database.entities.User;
import com.example.amazoinks.databinding.ActivityLoginBinding;
import com.example.amazoinks.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    public static final String TAG = "DTT_AMAZOINKS";
    private static final String MAIN_ACTIVITY_USER_ID = "com.example.amazoinks.MAIN_ACTIVITY_USER_ID";
    public static final String SHARED_PREFERENCE_USERID_KEY = "com.example.amazoinks.SHARED_PREFERENCE_USERID_KEY";
    private static final String SHARED_PREFERENCE_USERID_VALUE = "com.example.amazoinks.SHARED_PREFERENCE_USERID_VALUE";

    private static final int LOGGED_OUT = -1;

    private ActivityMainBinding binding;
    private AppRepository repository;

    private int loggedInUserId = -1;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = AppRepository.getRepository(getApplication());
        assert repository != null;
        repository.invokeDB();

        loginUser();

        if(loggedInUserId == LOGGED_OUT){
            Intent intent = InitialPageActivity.initialPageIntentFactory(getApplicationContext());
            startActivity(intent);
        }
        binding.shopNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = ItemBrowsingActivity.itemBrowsingIntentFactory(getApplicationContext());
                Bundle bundle = new Bundle();
                bundle.putInt("USER_ID", user.getId());
                startActivity(intent, bundle);
            }
        });



    }

    private void loginUser() {

        //check shared preference for logged in user
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(SHARED_PREFERENCE_USERID_KEY,
                Context.MODE_PRIVATE);
        loggedInUserId = sharedPreferences.getInt(SHARED_PREFERENCE_USERID_VALUE, LOGGED_OUT);
        if(loggedInUserId == LOGGED_OUT){
            //return;
            loggedInUserId = getIntent().getIntExtra(MAIN_ACTIVITY_USER_ID, LOGGED_OUT);

        }
        //check intent for logged in user
        //loggedInUserId = getIntent().getIntExtra(MAIN_ACTIVITY_USER_ID, LOGGED_OUT);

        if(loggedInUserId == LOGGED_OUT){
            //Toast.makeText(this, "loggedInUserId: " + loggedInUserId, Toast.LENGTH_SHORT).show();
            return;
        }
        LiveData<User> userObserver = repository.getUserByUserId(loggedInUserId);
        userObserver.observe(this, user -> {
            this.user = user;
            //Toast.makeText(this, user.toString(), Toast.LENGTH_SHORT).show();
            if(this.user != null){
                if(this.user.isAdmin()){
                    Toast.makeText(this, "User is an admin", Toast.LENGTH_SHORT).show();
                    binding.adminButton.setVisibility((View.VISIBLE));
                } else {
                    binding.adminButton.setVisibility(View.INVISIBLE);
                }
                invalidateOptionsMenu();

            } else {
                logout();
            }
        });
    }

    static Intent mainActivityIntentFactory(Context context, int userId){
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(MAIN_ACTIVITY_USER_ID, userId);
//        context.deleteDatabase(AppDatabase.DATABASE_NAME);
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
        if(user == null){
            return false;
        }
        item.setTitle(user.getUsername());

        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem item) {

                showLogoutDialog();
                return false;
            }
        });
        return true;
    }

    private void showLogoutDialog(){
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
        final AlertDialog alertDialog = alertBuilder.create();

        alertBuilder.setMessage("Ready to Log Out?");

        alertBuilder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                logout();
            }
        });

        alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });

        alertBuilder.create().show();

    }

    private void logout() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(SHARED_PREFERENCE_USERID_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor sharedPrefEditor = sharedPreferences.edit();
        sharedPrefEditor.putInt(SHARED_PREFERENCE_USERID_KEY,LOGGED_OUT);
        sharedPrefEditor.apply();

        getIntent().putExtra(MAIN_ACTIVITY_USER_ID, LOGGED_OUT);
        startActivity(InitialPageActivity.initialPageIntentFactory(getApplicationContext()));

    }


}