package org.example;

import java.util.*;

public class Restaurant {
    private final List<MenuItem> menuItems = new ArrayList<>();
    private final List<Order> kitchenQueue = new LinkedList<>();
    private final HashMap<Integer, Order> orders = new HashMap<>();
    private final HashMap<Integer, Order> completedOrders = new LinkedHashMap<>();
    private static int totalOrders = 0;
    private static int completeOrders = 0;
    private static int cancelledOrders = 0;



    public static int getTotalOrders() {
        return totalOrders;
    }

    public static int getCompleteOrders() {
        return completeOrders;
    }

    public static int getCancelledOrders() {
        return cancelledOrders;
    }


    public boolean addMenuItem( String name , double price, String category) {

        if(name == null || name.isEmpty()){
            System.out.println("name cannot be null  or empty");
            return false;
        }
        if(price <= 0){
            System.out.println("price cannot be negative");
            return false;
        }
        if(category == null || category.isEmpty()){
            System.out.println("category cannot be null  or empty");
            return false;
        }
        MenuItem item = new MenuItem( name,  price,  category);
        menuItems.add(item);
        return true;
    }

    public boolean removeMenuItem(int id) {
        if(findMenuItemById(id) != null){
            menuItems.remove(findMenuItemById(id));
            return true;
        }
        return false;
    }

    public void displayMenu() {
        System.out.println("Menu");
        for (MenuItem item : menuItems) {
            item.DetailsItem();
            System.out.println("-----------------------------");
        }
    }

    public void searchMenuItem(int id) {
        if(findMenuItemById(id)!= null){
            findMenuItemById(id).DetailsItem();
        }
        else{
            System.out.println("Item not found");
        }
    }

    public boolean createOrder( String customerName){

        if(customerName == null || customerName.isEmpty()){
            System.out.println("Customer name cannot be null");
            return false;
        }
        totalOrders++;
        Order order = new Order(customerName);
        order.setStatus(OrderStatus.PENDING);
        orders.put(order.getId(),order);
        return true;
    }

    public boolean addItemToOrder(int orderId,int menuItemId, int quantity){
        Order order = findOrderById(orderId);
        if(order== null){
            System.out.println("Order not found");
            return false;
        }
        MenuItem menuItem = findMenuItemById(menuItemId);
        if(menuItem == null){
            System.out.println("Item not found");
            return false;
        }
        if(quantity <= 0){
            System.out.println("Quantity must be greater than 0");
            return false;
        }

        if(order.getStatus().equals(OrderStatus.COMPLETED)){
            System.out.println(" Ops! order is completed cannot add item");
            return false;
        }
        if(order.getStatus().equals(OrderStatus.CANCELLED)){
            System.out.println(" Ops! order is cancelled cannot add item");
            return false;
        }
        OrderItem orderItem = new OrderItem(menuItem, quantity);
        return order.addItem(orderItem);

    }

    public boolean removeItemFromOrder(int orderId,int menuItemId){
        Order order = findOrderById(orderId);
        if(order== null){
            System.out.println("Order not found");
            return false;
        }
        if(order.getStatus().equals(OrderStatus.COMPLETED)){
            System.out.println(" Ops! order is completed cannot remove item");
            return false;
        }
        if(order.getStatus().equals(OrderStatus.CANCELLED)){
            System.out.println(" Ops! order is cancelled cannot remove item");
            return false;
        }

        OrderItem itemRemove = order.findItem(menuItemId);
        if(itemRemove == null){
            System.out.println("Item not found");
            return false;
        }
        order.getItems().remove(itemRemove);
        return true;
    }

    public void displayOrders(){
        if(orders.isEmpty()){
            System.out.println("No orders found");
        }
        else {
            for (Order order : orders.values()){
                order.displayDetails();
                System.out.println("---------------------------------");
            }
        }

    }


    public boolean addOrderToKitchenQueue(int orderId){
        Order order = findOrderById(orderId);
        if(order == null){
            System.out.println("Order not found");
            return false;
        }
        if(order.getStatus().equals(OrderStatus.COMPLETED)){
            System.out.println(" Ops! order is completed cannot add order");
            return false;
        }
        if(order.getStatus().equals(OrderStatus.CANCELLED)){
            System.out.println(" Ops! order is cancelled cannot add order");
            return false;
        }

        if(findOrderInKitchenQueue(orderId)){
            System.out.println(" Order already exists");
            return false;
        }
        kitchenQueue.add(order);
        order.setStatus(OrderStatus.IN_KITCHEN);
        return true;

    }


    public boolean processNextOrder(){
        if(kitchenQueue.isEmpty()){
            System.out.println("No kitchen orders found");
            return false;
        }
        completeOrders++;
        Order nextOrder = kitchenQueue.getFirst();
        nextOrder.setStatus(OrderStatus.COMPLETED);
        completedOrders.put(nextOrder.getId(),nextOrder);
        kitchenQueue.remove(nextOrder);
        return true;
    }


    public boolean checkOrderStatus(int orderId){
        Order order = findOrderById(orderId);
        if(order == null){
            System.out.println("Order not found");
            return false;
        }
        System.out.println("Order Status: " + order.getStatus());
        return true;
    }



    public void displayCompletedOrders(){
        if(completedOrders.isEmpty()){
            System.out.println("No orders completed found");
        }
        else{
            for (Order order : completedOrders.values()){
                   order.displayDetails();
                System.out.println("-------------------------------");
            }
        }
    }


    public boolean cancelOrder(int orderId){
        Order order = findOrderById(orderId);
        if(order == null){
            System.out.println("Order not found");
            return false;
        }
        if(order.getStatus().equals(OrderStatus.COMPLETED)){
            System.out.println(" Ops! order is completed cannot cancel order");
            return false;
        }
        if(order.getStatus().equals(OrderStatus.CANCELLED)){
            System.out.println(" Ops! order is cancelled cannot cancel order");
            return false;
        }
        cancelledOrders++;
        order.setStatus(OrderStatus.CANCELLED);
        kitchenQueue.remove(order);
        return true;
    }


    public Order findOrderById(int orderId){

        return orders.getOrDefault(orderId, null);

    }

    public MenuItem findMenuItemById(int id){
        for (MenuItem item : menuItems){
            if(item.getId() == id){
                return item ;
            }
        }
        return null;
    }

    public boolean findOrderInKitchenQueue(int orderId){
        for (Order order : kitchenQueue){
            if(order.getId() == orderId){
                return true;
            }
        }
        return false;
    }


}
