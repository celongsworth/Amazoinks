package com.example.amazoinks.database;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.amazoinks.activities.MainActivity;
import com.example.amazoinks.database.entities.CartItem;
import com.example.amazoinks.database.entities.CartViewItem;
import com.example.amazoinks.database.entities.Product;
import com.example.amazoinks.database.entities.User;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AppRepository {

    private final UserDAO userDAO;
    private final ProductDAO productDAO;
    private final CartDAO cartDAO;

    private static AppRepository repository;
    public AppRepository(Application application){
        AppDatabase db = AppDatabase.getDatabase(application);
        this.userDAO = db.userDAO();
        this.productDAO = db.productDAO();
        this.cartDAO = db.cartDAO();
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

    public void deleteUser(User user){
        //userDAO.deleteUserByUsername(username);
        AppDatabase.databaseWriteExecutor.execute(()-> {
            userDAO.delete(user);
        });
    }

    public void deleteUserByUsername(String username){
        AppDatabase.databaseWriteExecutor.execute(()->{
            userDAO.deleteUserByUsername(username);
        });
    }

    public LiveData<List<User>> getAllUsers(){
        return userDAO.getAllUsers();
    }

    public LiveData<User> getUserByUserId(int userId) {
        return userDAO.getUserByUserId(userId);
    }

    public LiveData<Product> getItemByItemName(String itemName) {
        return productDAO.getItemByName(itemName);
    }

    public void insertProduct(Product... product){
        AppDatabase.databaseWriteExecutor.execute(()-> {
            productDAO.insert(product);
        });
    }

    public LiveData<List<Product>> getAllProducts() {
        return productDAO.getAllProducts();
    }

    public LiveData<List<CartViewItem>> getCartItemsForUser(int userID) {
        return cartDAO.getCartItemsForUser(userID);
    }

    public LiveData<Product> getProductByID(int productID) {
        return productDAO.getProductByID(productID);
    }

    public LiveData<List<CartItem>> getAllCartItems() {
        return cartDAO.getAllCartItems();
    }

    public void insertCartItem(CartItem... cartItem){
        AppDatabase.databaseWriteExecutor.execute(()-> {
            cartDAO.insert(cartItem);
        });
    }

}
