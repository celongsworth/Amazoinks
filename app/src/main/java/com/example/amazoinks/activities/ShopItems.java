package com.example.amazoinks.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.amazoinks.R;
import com.example.amazoinks.database.AppDatabase;
import com.example.amazoinks.database.AppRepository;
import com.example.amazoinks.database.entities.Product;
import com.example.amazoinks.databinding.ActivityItemBrowsingBinding;
import com.example.amazoinks.databinding.ActivityShopItemsBinding;

import java.util.List;

public class ShopItems extends AppCompatActivity {

    private ActivityShopItemsBinding binding;
    private AppRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_items);

        this.binding = ActivityShopItemsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = AppRepository.getRepository(getApplication());
        AppDatabase appDatabase = AppDatabase.getDatabase(this);


        /*
        LiveData<List<User>> usersObserver = repository.getAllUsers();
        usersObserver.observe(this, users -> {
            for (User user : users){
                if(username.equals(user.getUsername())){
                    Toast.makeText(this, "Username already taken! Choose Again.", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
         */
        LiveData<List<Product>> productListObserver = repository.getAllProducts();
        productListObserver.observe(this, products -> {
            if (products != null && !products.isEmpty()) {
                Toast.makeText(this, products.toString(), Toast.LENGTH_LONG).show();
                addTableRows(products);
            }
        });
//        addTableRow();
    }

    public void addTableRows(List<Product> products) {
        TableLayout stk = (TableLayout) findViewById(R.id.mainTable);
        TableRow tableRow = new TableRow(this);

        //TABLE HEAEDERS
        TextView itemHeader = new TextView(this);
        itemHeader.setText("|  Item  |");
        itemHeader.setTextColor(Color.BLACK);
        itemHeader.setTextSize(20);
        itemHeader.setPadding(10, 5, 10, 5);
        tableRow.addView(itemHeader);

        TextView priceHeader = new TextView(this);
        priceHeader.setText("  Price  ");
        priceHeader.setTextColor(Color.BLACK);
        priceHeader.setTextSize(20);
        priceHeader.setPadding(10, 5, 10, 5);
        tableRow.addView(priceHeader);

        TextView countHeader = new TextView(this);
        countHeader.setText("|  Quantity  |");
        countHeader.setTextColor(Color.BLACK);
        countHeader.setTextSize(20);
        countHeader.setPadding(10, 5, 10, 5);
        tableRow.addView(countHeader);

//        TextView tv3 = new TextView(this);
//        tv3.setText(" Address ");
//        tv3.setTextColor(Color.BLACK);
//        tableRow.addView(tv3);

        stk.addView(tableRow);

        for (Product product : products) {

            String item = product.getItemName();
            Log.i(MainActivity.TAG, item);
            double price = product.getPrice();
            Log.i(MainActivity.TAG, String.valueOf(price));

            int quantity = product.getQuantity();
            Log.i(MainActivity.TAG, String.valueOf(quantity));

            TableRow tbrow = new TableRow(this);
            TextView itemName = new TextView(this);
            itemName.setText(item);
//            itemName.setText("ITEM");
            itemName.setTextColor(Color.BLACK);
            itemName.setGravity(Gravity.CENTER);
            itemName.setTextSize(20);
            tbrow.addView(itemName);

            TextView priceText = new TextView(this);
            priceText.setText(String.valueOf(price));
//            priceText.setText("$100");
            priceText.setTextColor(Color.BLACK);
            priceText.setGravity(Gravity.CENTER);
            priceText.setTextSize(20);
            tbrow.addView(priceText);

            TextView quantityText = new TextView(this);
            quantityText.setText(String.valueOf(quantity));
//            quantityText.setText("15");
            quantityText.setTextColor(Color.BLACK);
            quantityText.setGravity(Gravity.CENTER);
            quantityText.setTextSize(20);
            tbrow.addView(quantityText);

//            TextView t4v = new TextView(this);
//            t4v.setText("");
//            t4v.setTextColor(Color.BLACK);
//            t4v.setGravity(Gravity.CENTER);
//            tbrow.addView(t4v);

            stk.addView(tbrow);
        }
    }

    static Intent shopItemsIntentFactory(Context context) {
        return new Intent(context, ShopItems.class);
    }
}