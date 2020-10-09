package com.example.dietapp;

public class Lunch {

    private String foodName;
    private String foodQuantity;

    public Lunch() {

    }

    public Lunch(String foodName, String foodQuantity) {
        this.foodName = foodName;
        this.foodQuantity = foodQuantity;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodQuantity() {
        return foodQuantity;
    }

    public void setFoodQuantity(String foodQuantity) {
        this.foodQuantity = foodQuantity;
    }
}
