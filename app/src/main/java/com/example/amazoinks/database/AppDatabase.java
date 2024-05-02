package com.example.amazoinks.database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;


import com.example.amazoinks.activities.MainActivity;
//import com.example.amazoinks.database.entities.User;
import com.example.amazoinks.database.entities.Product;
import com.example.amazoinks.database.entities.CartItem;
import com.example.amazoinks.database.entities.User;
import com.example.amazoinks.database.typeConverters.LocalDateTypeConverter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@TypeConverters(LocalDateTypeConverter.class)
@Database(entities = {User.class, Product.class, CartItem.class}, version = 6, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public static final String USER_TABLE = "usertable";
    public static final String SHOPPING_CART_TABLE = "shopping_cart";
    public static final String PRODUCT_TABLE = "product";
    public static final String DATABASE_NAME = "AppDatabase";
    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    public static AppDatabase getDatabase(final Context context){
        Log.i(MainActivity.TAG, "getDatabase called");
        if(INSTANCE == null){
            Log.i(MainActivity.TAG, "INSTANCE equals null pt1");
            synchronized (AppDatabase.class){
                if (INSTANCE == null){
                    Log.i(MainActivity.TAG, "INSTANCE equals null pt2");
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    AppDatabase.class,
                                    DATABASE_NAME
                            )
                            .fallbackToDestructiveMigration()
                            .addCallback(addDefaultValues)
                            .build();

//                    INSTANCE.query("select 1", null);
                }
            }
        }
        return INSTANCE;
    }

    private static final RoomDatabase.Callback addDefaultValues = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);
            Log.i(MainActivity.TAG, "DATABASE CREATED!");
            databaseWriteExecutor.execute(() -> {
                UserDAO dao = INSTANCE.userDAO();
                dao.deleteAll();
                User admin = new User("admin2", "admin2");
                admin.setAdmin(true);
                dao.insert(admin);
                User testUser1 = new User("testuser1", "testuser1");
                dao.insert(testUser1);
                Log.i(MainActivity.TAG, "User DATABASE Entries added!");
                // instantiate and insert predefined products
                ProductDAO prodDAO = INSTANCE.productDAO();
                Product shirt = new Product("Shirt","A fun colorful short sleeve t-shirt",
                        7, 19.99, "Clothing");
                Product pants = new Product("Pants","Horrendously ugly pants, but they fit",
                        10, 26.99, "Clothing");
                Product plate = new Product("Plate","A plate shaped like a tiger",
                        15, 9.99, "Dishes");
                prodDAO.insert(shirt, pants, plate);
                Log.i(MainActivity.TAG, "Product DATABASE Entries added!");
            });
        }
    };

    public abstract UserDAO userDAO();

     public abstract CartDAO cartDAO();
     public abstract ProductDAO productDAO();
}

