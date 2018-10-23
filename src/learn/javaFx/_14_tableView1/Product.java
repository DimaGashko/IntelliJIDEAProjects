package learn.javaFx._14_tableView1;

import java.util.UUID;

public class Product {
    private final UUID id = UUID.randomUUID();
    private String name = "No Name";
    private double width = 300;
    private double height = 250;
    private double price = 0;

    public Product() {

    }

    public Product(String name) {
        setName(name);
    }

    public Product(String name, double price) {
        setName(name);
        setPrice(price);
    }

    public Product(String name, double width, double height) {
        setName(name);
        setWidth(width);
        setHeight(height);
    }

    public Product(String name, double width, double height, double price) {
        setName(name);
        setWidth(width);
        setHeight(height);
        setPrice(price);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public UUID getId() {
        return id;
    }


}
