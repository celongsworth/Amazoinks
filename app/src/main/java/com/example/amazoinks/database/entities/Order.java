package com.example.amazoinks.database.entities;

import androidx.room.PrimaryKey;

import java.time.LocalDate;

public class Order {
    @PrimaryKey(autoGenerate = true)
    private int orderId;
    private int userId;
    private LocalDate date;
}
