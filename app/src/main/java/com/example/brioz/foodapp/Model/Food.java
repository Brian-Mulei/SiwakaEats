package com.example.brioz.foodapp.Model;

public class Food {


    private String Name,image,Description,Price,MenuID,Discount;

    public Food() {
    }

    public Food(String name, String image, String description, String price, String menuID, String discount) {
        Name = name;
        this.image = image;
        Description = description;
        Price = price;
        MenuID = menuID;
        Discount = discount;
    }

    public String getName() {
        return Name;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getMenuID() {
        return MenuID;
    }

    public void setMenuID(String menuID) {
        MenuID = menuID;
    }
}
