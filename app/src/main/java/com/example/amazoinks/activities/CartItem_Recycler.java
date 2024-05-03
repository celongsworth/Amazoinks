package com.example.amazoinks.activities;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amazoinks.R;
import com.example.amazoinks.database.AppDatabase;
import com.example.amazoinks.database.AppRepository;
import com.example.amazoinks.database.entities.CartItem;
import com.example.amazoinks.database.entities.Product;
import com.example.amazoinks.databinding.ActivityShopItemsBinding;

import java.util.List;

class CartItem_Recycler extends RecyclerView.Adapter<CartItem_Recycler.MyViewHolder> {
    Context context;
    List<CartItem> userCartItems;
    AppRepository repository;
    public CartItem_Recycler(Context context, List<CartItem> userCartItems, AppRepository appRepository) {
        this.context = context;
        this.userCartItems = userCartItems;
        this.repository = appRepository;
    }
    @NonNull
    @Override
    public CartItem_Recycler.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);
        return new CartItem_Recycler.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItem_Recycler.MyViewHolder holder, int position) {
        Product product = repository.getProductByID(userCartItems.get(position).getItemID()).getValue();
        if (product == null) {
            holder.quantity.setText("NULL");
            holder.name.setText("NULL");
            holder.price.setText("NULL");
            return;
        }
        holder.quantity.setText(String.valueOf(product.getQuantity()));
        holder.name.setText(product.getItemName());
        holder.price.setText(String.valueOf(product.getPrice()));
    }

    @Override
    public int getItemCount() {
        return userCartItems.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name, price, quantity;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            name = itemView.findViewById(R.id.itemName);
            price = itemView.findViewById(R.id.itemPrice);
            quantity = itemView.findViewById(R.id.itemQuantity);
        }
    }
}
