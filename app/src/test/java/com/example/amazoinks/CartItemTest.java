package com.example.amazoinks;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import com.example.amazoinks.database.entities.CartItem;

import org.junit.Before;
import org.junit.Test;

public class CartItemTest {
    CartItem item;
    CartItem item2;
    CartItem item3;

    private int UserID;
    private int ItemID;
    private int itemQuantity;

    private int UserID2;
    private int ItemID2;
    private int itemQuantity2;


    @Before
    public void setUp() {
        UserID = 1;
        ItemID = 4;
        itemQuantity = 5;

        UserID2 = 3;
        ItemID2 = 2;
        itemQuantity2 = 8;

        item = new CartItem(UserID, ItemID, itemQuantity);
        item2 = new CartItem(UserID2, ItemID2, itemQuantity2);
        item3 = new CartItem(UserID, ItemID, itemQuantity);
    }

    @Test
    public void getUserID() {
        assertEquals(item.getUserID(), UserID);
        assertNotEquals(item.getUserID(), item2.getUserID());
        assertEquals(item.getUserID(), item3.getUserID());
    }

    @Test
    public void setUserID() {
        item.setUserID(UserID2);
        assertNotEquals(item.getUserID(), UserID);
        assertEquals(item.getUserID(), UserID2);
    }

    @Test
    public void getItemID() {
        assertEquals(item.getItemID(), ItemID);
        assertNotEquals(item.getItemID(), item2.getItemID());
        assertEquals(item.getItemID(), item3.getItemID());
    }

    @Test
    public void setItemID() {
        item.setItemID(ItemID2);
        assertNotEquals(item.getItemID(), ItemID);
        assertEquals(item.getItemID(), ItemID2);
    }

    @Test
    public void getItemQuantity() {
        assertEquals(item.getItemQuantity(), itemQuantity);
        assertNotEquals(item.getItemQuantity(), item2.getItemQuantity());
        assertEquals(item.getItemQuantity(), item3.getItemQuantity());
    }

    @Test
    public void setItemQuantity() {
        item.setItemQuantity(itemQuantity2);
        assertNotEquals(item.getItemQuantity(), itemQuantity);
        assertEquals(item.getItemQuantity(), itemQuantity2);
    }

}