package org.example;

public class MenuItem {
    private final int id;
    private final String name;
    private double price;
    private String category;
    private static int countId = 9550;
    public MenuItem(String name, double price, String category) {
        this.id = countId++;
        this.name = name;
        this.price = price;
        this.category = category;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }


    public void setPrice(double price) {

        this.price = price;
    }


    public void setCategory(String category) {
        this.category = category;
    }


    public void DetailsItem(){
        System.out.println("id: " +id);
        System.out.println("name: " +name);
        System.out.println("price: " +price);
        System.out.println("category: " +category);
    }


    @Override
    public String toString() {
        return name + " (id:" + id + ", " + price + " EGP, " + category + ")";
    }
}
