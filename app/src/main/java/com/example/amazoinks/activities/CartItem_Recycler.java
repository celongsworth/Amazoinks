package com.example.amazoinks.activities;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amazoinks.R;
import com.example.amazoinks.database.AppRepository;
import com.example.amazoinks.database.entities.CartViewItem;

import java.util.List;

class CartItem_Recycler extends RecyclerView.Adapter<CartItem_Recycler.MyViewHolder> {
    Context context;
    List<CartViewItem> userCartItems;
    AppRepository repository;
    public CartItem_Recycler(Context context, List<CartViewItem> userCartItems, AppRepository appRepository) {
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
        CartViewItem product = userCartItems.get(position);
        Toast.makeText(context, product.toString(), Toast.LENGTH_SHORT).show();
        if (product == null) {
            holder.quantity.setText("NULL");
            holder.name.setText("NULL");
            holder.price.setText("NULL");
            return;
        }
        holder.quantity.setText(String.valueOf(product.getItemQuantity()));
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
            Log.i(MainActivity.TAG, "MyViewHolder is getting instantiated");
        }
    }
}
