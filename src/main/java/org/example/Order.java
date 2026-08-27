package org.example;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private final int id;
    private final String CustomerName;
    private double total = 0 ;
    private final List<OrderItem> items = new ArrayList<>();
    private OrderStatus status;
    private static int countId = 0;

    public Order(String CustomerName) {
        this.id = countId++;
        this.CustomerName = CustomerName;


    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public boolean addItem(OrderItem item) {
        if (item == null) {
            System.out.println("item cannot be null");
            return false;
        }
        items.add(item);
        return true;

    }


    public int getId() {
        return id;
    }

    public String getCustomerName() {
        return CustomerName;
    }


    public void updateStatus(OrderStatus status) {
        this.status = status;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public List<OrderItem> getItems() {
        return items;
    }


    public OrderItem findItem(int menuItemId) {
        for (OrderItem item : items) {
            if (item.getItem().getId() == menuItemId) {
                return item;
            }
        }
        return null;
    }
    public  void displayDetails() {
        System.out.println("Order ID: " + id);
        System.out.println("Customer Name: " + CustomerName);
        System.out.println("Status: " + status);
        for (OrderItem item : items) {
            item.displayItem();
            System.out.println("--------------------");
        }
       double finalTotal = calculateTotal();
        System.out.println("Final Total: " +finalTotal );


    }


    public double calculateTotal(){
        total = 0;
        for (OrderItem item : items) {
            double subtotal = item.calculateSubtotal(item.getItem(), item.getQuantity());
            if(subtotal > 0){
                total += subtotal;
            }
        }
        return total;
    }
    public double getTotal() {
        return total;
    }













}


