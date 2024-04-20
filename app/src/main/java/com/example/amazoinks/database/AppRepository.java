package com.example.amazoinks.database;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.amazoinks.MainActivity;
import com.example.amazoinks.database.entities.User;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AppRepository {

    private final UserDAO userDAO;

    private static AppRepository repository;
    private AppRepository(Application application){
        AppDatabase db = AppDatabase.getDatabase(application);
        this.userDAO = db.userDAO();
    }

    public static AppRepository getRepository(Application application){
        if(repository != null){
            return repository;
        }
        Future<AppRepository> future = AppDatabase.databaseWriteExecutor.submit(
                new Callable<AppRepository>() {
                    @Override
                    public AppRepository call() throws Exception {
                        return new AppRepository(application);
                    }
                }
        );
        try{
            return future.get();
        }catch (InterruptedException | ExecutionException e){
            Log.d(MainActivity.TAG, "Problem getting gym log repository thread error");
        }
        return null;
    }

    public void insertUser(User... user){
        AppDatabase.databaseWriteExecutor.execute(()-> {
            userDAO.insert(user);
        });
    }

    public void invokeDB(){
        AppDatabase.databaseWriteExecutor.execute(userDAO::getAllUsers);
    }

    public LiveData<User> getUserByUserName(String username) {
        return userDAO.getUserByUserName(username);
    }

    public LiveData<User> getUserByUserId(int userId) {
        return userDAO.getUserByUserId(userId);
    }
}
