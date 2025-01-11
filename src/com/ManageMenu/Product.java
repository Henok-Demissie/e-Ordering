package com.ManageMenu;

/**
 * Product.java
 * This is a model class that represents a Product entity.
 * It encapsulates details about a product such as its ID, name, category, price, and image.
 */
public class Product {
    private int id;
    private String name;
    private String category;
    private double price;
    private String image;

    /**
     * Default constructor.
     */
    public Product() {
    }

    /**
     * Constructor to initialize a Product with name, category, price, and image.
     * 
     * @param name     the name of the product.
     * @param category the category of the product.
     * @param price    the price of the product.
     * @param image    the image URL or path of the product.
     */
    public Product(String name, String category, double price, String image) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.image = image;
    }

    /**
     * Constructor to initialize a Product with all fields.
     * 
     * @param id       the unique identifier of the product.
     * @param name     the name of the product.
     * @param category the category of the product.
     * @param price    the price of the product.
     * @param image    the image URL or path of the product.
     */
    public Product(int id, String name, String category, double price, String image) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.image = image;
    }

    /**
     * Gets the ID of the product.
     * 
     * @return the product ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the product.
     * 
     * @param id the product ID to set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the product.
     * 
     * @return the product name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the product.
     * 
     * @param name the product name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the category of the product.
     * 
     * @return the product category.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the category of the product.
     * 
     * @param category the product category to set.
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Gets the price of the product.
     * 
     * @return the product price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the product.
     * 
     * @param price the product price to set.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the image URL or path of the product.
     * 
     * @return the product image.
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets the image URL or path of the product.
     * 
     * @param image the product image to set.
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Returns a string representation of the Product object.
     * 
     * @return a formatted string containing the product details.
     */
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                '}';
    }
}
