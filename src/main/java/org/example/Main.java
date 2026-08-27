package org.example;

import java.awt.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        Restaurant restaurant = new Restaurant();
        int option;

        do {
            restaurantMene();
            option = readInt(in);
            switch (option) {
                case 1 -> addMenuItem(in, restaurant);
                case 2 -> removeMenuItem(in, restaurant);
                case 3 -> displayMenuItem(restaurant);
                case 4 -> searchMenuItem(in, restaurant);
                case 5 -> createOrder(in, restaurant);
                case 6 -> addOrder(in, restaurant);
                case 7 -> removeOrder(in, restaurant);
                case 8 -> displayOrders(restaurant);
                case 9 -> addOrderToKitchenQueue(in, restaurant);
                case 10 -> processOrder(restaurant);
                case 11 -> searchOrder(in, restaurant);
                case 12 -> checkStatus(in, restaurant);
                case 13 -> displayCompletedOrder( restaurant);
                case 14 -> cancelOrder(in, restaurant);
                case 0 -> exist();


            }


        }
        while (option != 0);


    }

    static int readInt(Scanner in) {
        while (!in.hasNextInt()) {
            System.out.println("Invalid input. please enter a number ");
            in.next();
        }
        int value = in.nextInt();
        in.nextLine();
        return value;
    }

    static double readDouble(Scanner in) {
        while (!in.hasNextDouble()) {
            System.out.println("Invalid input. please enter a number ");
            in.next();
        }
        double value = in.nextDouble();
        in.nextLine();
        return value;
    }

    static String readString(Scanner in) {
        while (!in.hasNextLine()) {
            System.out.println("Invalid input. please enter a line ");
            in.next();
        }
        return in.nextLine();

    }

    static void restaurantMene() {
        System.out.println("********************* Welcome to Restaurant Mene *********************");
        System.out.println("1. Add Menu Item");
        System.out.println("2. Remove Menu Item");
        System.out.println("3. Display Menu");
        System.out.println("4. Search Menu Item");
        System.out.println("5. Create Order");
        System.out.println("6. Add Item to Order");
        System.out.println("7. Remove Item from Order");
        System.out.println("8. Display Order");
        System.out.println("9. Add Order to Kitchen Queue");
        System.out.println("10. Process Next Order");
        System.out.println("11. Search Order");
        System.out.println("12. Check Order Status");
        System.out.println("13. Display Completed Orders");
        System.out.println("14. Cancel Order");
        System.out.println("0. Exit");
        System.out.println("**********************************************************************");
    }

    static void addMenuItem(Scanner in, Restaurant restaurant) {
        System.out.println("Enter Name Item.");
        String name = readString(in);
        System.out.println("Enter Price Item.");
        double price = readDouble(in);
        System.out.println("Enter Category Item.");
        String category = readString(in);
        if (restaurant.addMenuItem(name, price, category)) {
            System.out.println("Menu Item added successfully");
        } else {
            System.out.println("failed to add menu item");
        }

    }

    static void removeMenuItem(Scanner in, Restaurant restaurant) {
        System.out.println("Enter id item to remove.");
        int id = readInt(in);
        if (restaurant.removeMenuItem(id)) {
            System.out.println("Menu Item removed successfully");
        } else {
            System.out.println("failed to remove menu item");
        }
    }

    static void displayMenuItem(Restaurant restaurant) {
        System.out.println("*********************************************************************");
        restaurant.displayMenu();
        System.out.println("*********************************************************************");
    }

    static void searchMenuItem(Scanner in, Restaurant restaurant) {
        System.out.println("Enter id item to search.");
        int id = readInt(in);
        restaurant.searchMenuItem(id);
    }

    static void createOrder(Scanner in, Restaurant restaurant) {

        System.out.println("Enter customer name.");
        String name = readString(in);
        if(restaurant.createOrder(name)) {
            System.out.println("Order created successfully");
        }else{
            System.out.println("failed to create order");
        }
    }

    static void addOrder(Scanner in, Restaurant restaurant) {
        System.out.println("Enter id item to add.");
        int id = readInt(in);
        System.out.println("Enter menu item id. ");
        int menuItemId = readInt(in);
        System.out.println("Enter quantity to add.");
        int quantity = readInt(in);

        if(restaurant.addItemToOrder(id, menuItemId, quantity)) {
            System.out.println("Order added successfully");
        }
        else {
            System.out.println("failed to add order");
        }
    }

    static void removeOrder(Scanner in, Restaurant restaurant) {
        System.out.println("Enter id item to remove.");
        int id = readInt(in);
        System.out.println("Enter menu item id. ");
        int menuItemId = readInt(in);
        if(restaurant.removeItemFromOrder(id, menuItemId)) {
            System.out.println("Order removed successfully");
        }
        else {
            System.out.println("failed to remove order");
        }
    }

    static void displayOrders( Restaurant restaurant) {
        System.out.println("***********************************************************************");
        restaurant.displayOrders();
    }

    static void addOrderToKitchenQueue(Scanner in, Restaurant restaurant) {
        System.out.println("Enter id item to add to the Kitchen Queue.");
        int id = readInt(in);
        if(restaurant.addOrderToKitchenQueue(id)) {
            System.out.println("Order added successfully");
        }
        else {
            System.out.println("failed to add order");
        }

    }

    static void processOrder( Restaurant restaurant) {
        if(restaurant.processNextOrder()){
            System.out.println("Order processed successfully");
        }
        else {
            System.out.println("failed to process order");
        }
    }

    static void searchOrder(Scanner in, Restaurant restaurant) {
        System.out.println("Enter id item to search.");
        int id = readInt(in);
        Order order =restaurant.findOrderById(id);
        if ( order != null) {
            System.out.println("Order found successfully");
            order.displayDetails();
        }
        else {
            System.out.println("failed to find order");

        }

    }

    static void checkStatus(Scanner in, Restaurant restaurant) {
        System.out.println("Enter id item to check.");
        int id = readInt(in);
        if(restaurant.checkOrderStatus(id)){
            System.out.println("Order check successfully");
        }
        else {
            System.out.println("failed to check order");
        }

    }

    static void displayCompletedOrder( Restaurant restaurant) {
        System.out.println("***********************************************************************");
        restaurant.displayCompletedOrders();

    }

    static void cancelOrder(Scanner in, Restaurant restaurant) {
        System.out.println("Enter id item to cancel.");
        int id = readInt(in);
        if(restaurant.cancelOrder(id)) {
            System.out.println("Order cancelled successfully");
        }
        else {
            System.out.println("failed to cancel order");
        }
    }

    static void exist() {
        System.out.println("============================================");
        System.out.println("Thank you for using Restaurant App");
        System.out.println("Final Summary:");
        System.out.println("Total Orders: "+  Restaurant.getTotalOrders());
        System.out.println("Completed Orders: "+ Restaurant.getCompleteOrders());
        System.out.println("Cancelled Orders: "+  Restaurant.getCancelledOrders());
        System.out.println("Good Bye");
        System.out.println("============================================");


    }
    
}
