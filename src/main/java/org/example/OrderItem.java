package org.example;

import java.util.ArrayList;
import java.util.List;

public class OrderItem {
    private MenuItem item;
    private int quantity;
    public OrderItem(MenuItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }


    public MenuItem getItem() {
        return item;
    }

    public void setItem(MenuItem item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean validItemAndQuantity(MenuItem item, int quantity) {
        if(item== null){
            System.out.println("Item cannot be null");
            return false;
        }
        if(item.getPrice() <= 0){
            System.out.println("Price cannot be negative or zero");
            return false;
        }
        if(quantity <= 0){
            System.out.println("Quantity cannot be negative or zero");
            return false;
        }

        return true;

    }

    public double calculateSubtotal(MenuItem item, int quantity) {
        if( validItemAndQuantity(item, quantity)){
            return item.getPrice() * quantity;
        }
        return -1;
    }
    public void displayItem() {
        System.out.println("Item: " + item);
        System.out.println("Quantity: " + quantity);
        System.out.println("Subtotal: " + item+" x "+ quantity + " " +calculateSubtotal(item, quantity));
    }

}
